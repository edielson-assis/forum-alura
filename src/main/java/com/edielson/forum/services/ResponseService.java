package com.edielson.forum.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.edielson.forum.dto.ResponseDTO;
import com.edielson.forum.dto.ResponseTopicDTO;
import com.edielson.forum.dto.ResponseUpdateDTO;
import com.edielson.forum.entities.Response;

public interface ResponseService {
    
    Response create(ResponseDTO responseDTO);

    Page<ResponseTopicDTO> findAll(Pageable pageable);

    Response findById(Long id);

    void delete(Long id);

    Response update(Long id, ResponseUpdateDTO responseUpdateDTO);
}