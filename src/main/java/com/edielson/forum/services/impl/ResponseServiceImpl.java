package com.edielson.forum.services.impl;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.edielson.forum.dto.ResponseDTO;
import com.edielson.forum.dto.ResponseTopicDTO;
import com.edielson.forum.dto.ResponseUpdateDTO;
import com.edielson.forum.entities.Response;
import com.edielson.forum.entities.Topic;
import com.edielson.forum.entities.User;
import com.edielson.forum.repositories.ResponseRepository;
import com.edielson.forum.services.ResponseService;
import com.edielson.forum.services.TopicService;
import com.edielson.forum.services.exceptions.DataBaseException;
import com.edielson.forum.services.exceptions.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ResponseServiceImpl implements ResponseService {

    private ResponseRepository repository;
    private TopicService topicService;

    @Override
    public Response create(ResponseDTO responseDTO) {
        Response response = fromDto(responseDTO);
        topicService.findById(response.getTopic().getId());
        return repository.save(response);
    }

    @Override
    public Page<ResponseTopicDTO> findAll(Pageable pageable) {
        Page<Response> page = repository.findAll(pageable);
        return page.map(ResponseTopicDTO::new);
    }

    @Override
    public Response findById(Long id) {
        Optional<Response> response = repository.findById(id);
        return response.orElseThrow(() -> new ObjectNotFoundException("Resposta não encotrada. Id invalido: " + id));
    }

    @Override
    public void delete(Long id) {
        Response response = findById(id);
        try {
            if (response.getAuthor().equals(userAuthentication())) {
                repository.deleteById(id);
            } else {
                throw new BadCredentialsException("Não autorizado");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    @Override
    public Response update(Long id, ResponseUpdateDTO responseUpdateDTO) {
        Response response = findById(id);
        try {
            if (response.getAuthor().equals(userAuthentication())) {
                updateData(response, responseUpdateDTO);
                return repository.save(response);
            } else {
                throw new BadCredentialsException("Não autorizado");
            }
        } catch (RuntimeException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    private Response fromDto(ResponseDTO responseDTO) {
        var topic = new Topic();
        topic.setId(responseDTO.topic());

        Instant instant = Instant.now().atOffset(ZoneOffset.ofHours(-3)).toInstant();

        return new Response(null, responseDTO.message(), topic, instant, userAuthentication(), false);
    }

    private void updateData(Response response, ResponseUpdateDTO responseUpdateDTO) {
        response.setMessage(responseUpdateDTO.message());
    }

    private User userAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}