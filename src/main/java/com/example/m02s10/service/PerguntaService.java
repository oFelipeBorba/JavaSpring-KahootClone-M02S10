package com.example.m02s10.service;

import com.example.m02s10.controller.dto.PerguntaRequest;
import com.example.m02s10.entity.PerguntaEntity;
import com.example.m02s10.exception.NotFoundException;
import com.example.m02s10.repository.PerguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PerguntaService {

    @Autowired
    private PerguntaRepository perguntaRepository;

    public PerguntaEntity encontraPorId(@PathVariable Long id){

            PerguntaEntity perguntaDoId = perguntaRepository.findById(id)
                    .orElseThrow(()->new NotFoundException("Não foi possível obter o objeto a partir do id:"+id));

            return perguntaDoId;
        }


    }


