package com.example.m02s10.controller;

import com.example.m02s10.controller.dto.RespostaRequest;
import com.example.m02s10.entity.RespostaEntity;
import com.example.m02s10.repository.RespostaRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/resposta")
public class RespostaController {

    @Autowired
    private RespostaRepository respostaRepository;

    @GetMapping
    public ResponseEntity<List<RespostaRequest>> buscarTodasRespostas(){
        List<RespostaEntity> listaDasRespostas = respostaRepository.findAll();
        List<RespostaRequest> listaDasRespostasResponse = new ArrayList<>();
        for (RespostaEntity resposta : listaDasRespostas){
            listaDasRespostasResponse.add(
                    new RespostaRequest(resposta.getTexto())
            );
        }
        return ResponseEntity.ok(listaDasRespostasResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RespostaRequest> buscarRespostarPorID(@PathVariable Long id){
        RespostaEntity respostaPorId = respostaRepository.findById(id).get();
        return new ResponseEntity<RespostaRequest>(
          new RespostaRequest(respostaPorId.getTexto()),
                HttpStatus.OK
        );
    }
    @GetMapping("/porpergunta/{id}")
    public ResponseEntity buscarRespostasPorPerguntaId(@PathVariable Long id){
        List<RespostaEntity> respostasPorPergunta = respostaRepository.findRespostasPorId_Pergunta(id);
        return ResponseEntity.ok(respostasPorPergunta);
    }


}
