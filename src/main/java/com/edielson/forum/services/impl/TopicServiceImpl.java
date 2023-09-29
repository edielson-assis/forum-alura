package com.edielson.forum.services.impl;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.edielson.forum.dto.CourseTopicDTO;
import com.edielson.forum.dto.TopicDTO;
import com.edielson.forum.dto.TopicResponseDTO;
import com.edielson.forum.dto.TopicUpdateDTO;
import com.edielson.forum.entities.Course;
import com.edielson.forum.entities.Response;
import com.edielson.forum.entities.Topic;
import com.edielson.forum.entities.User;
import com.edielson.forum.entities.enums.StatusTopic;
import com.edielson.forum.repositories.ResponseRepository;
import com.edielson.forum.repositories.TopicRepository;
import com.edielson.forum.security.exceptions.ValidationException;
import com.edielson.forum.services.CourseService;
import com.edielson.forum.services.TopicService;
import com.edielson.forum.services.exceptions.DataBaseException;
import com.edielson.forum.services.exceptions.ObjectNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TopicServiceImpl implements TopicService {

    private TopicRepository repository;
    private ResponseRepository responseRepository;
    private CourseService courseService;

    @Override
    public Topic create(TopicDTO topicDTO) {
        Topic topic = fromDto(topicDTO);
        existByTopic(topic.getTitulo());
        courseService.findById(topic.getCourse().getId());
        return repository.save(topic);
    }

    @Override
    public Page<TopicResponseDTO> findAll(Pageable pageable) {
        Page<Topic> page = repository.findAll(pageable);
        return page.map(TopicResponseDTO::new);
    }

    @Override
    public Topic findById(Long id) {
        Optional<Topic> topic = repository.findById(id);
        return topic.orElseThrow(() -> new ObjectNotFoundException("Tópico não encotrado. Id invalido: " + id));
    }

    @Override
    public void delete(Long id) {
        Topic topic = findById(id);
        try {
            if (topic.getAuthor().equals(userAuthentication())) {
                repository.deleteById(id);
            } else {
                throw new BadCredentialsException("Não autorizado");
            }
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    @Override
    public Topic update(Long id, TopicUpdateDTO topicUpdateDTO) {
        Topic topic = findById(id);
        courseService.findById(id);
        try {
            updateData(topic, topicUpdateDTO);

            List<Response> responses = responseRepository.findByTopic(topic);
            for (Response response : responses) {
                response.updateSolved();
                responseRepository.save(response);
            }
            return repository.save(topic);
        } catch (RuntimeException e) {
            throw new DataBaseException(e.getMessage());
        }
    }

    private Topic fromDto(TopicDTO topicDTO) {
        Instant instant = Instant.now().atOffset(ZoneOffset.ofHours(-3)).toInstant();
        CourseTopicDTO course = new CourseTopicDTO(topicDTO.course().id(), topicDTO.course().name());

        return new Topic(null, topicDTO.titulo(), topicDTO.message(), instant, StatusTopic.NAO_RESPONDIDO, userAuthentication(), new Course(course));
    }

    private void updateData(Topic topic, TopicUpdateDTO topicUpdateDTO) {
        topic.setStatus(topicUpdateDTO.status());
    }

    private synchronized void existByTopic(String titulo) {
        boolean existTopic = repository.existsByTitulo(titulo);
        if (existTopic) {
            throw new ValidationException("Tópico já cadastrado");
        }
    }

    private User userAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }
}