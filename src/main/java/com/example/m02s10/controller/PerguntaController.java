package com.example.m02s10.controller;

import com.example.m02s10.controller.dto.PerguntaRequest;
import com.example.m02s10.entity.AssuntoEntity;
import com.example.m02s10.entity.PerguntaEntity;
import com.example.m02s10.repository.PerguntaRepository;
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
@RequestMapping("/pergunta")
public class PerguntaController {

    @Autowired
    private PerguntaRepository perguntaRepository;

    @GetMapping
    public ResponseEntity<List<PerguntaRequest>> buscarTodasPerguntas(){
        List<PerguntaEntity> perguntaEntityList = perguntaRepository.findAll();
        List<PerguntaRequest> perguntaResponseList = new ArrayList<>();
        for(PerguntaEntity pergunta : perguntaEntityList){
            perguntaResponseList.add(
                    new PerguntaRequest(pergunta.getTitulo(), pergunta.getTexto())
            );
        }
        return ResponseEntity.ok(perguntaResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerguntaRequest> buscarPerguntaPorId(@PathVariable Long id){
        PerguntaEntity perguntaDoId = perguntaRepository.findById(id).get();
        return new ResponseEntity<>(
               new PerguntaRequest(perguntaDoId.getTitulo(), perguntaDoId.getTexto()),
                HttpStatus.OK
        );
    }
//    @GetMapping("/{id_assunto}")
//    public ResponseEntity<List<PerguntaRequest>> buscarPerguntaPorAssunto(@PathVariable AssuntoEntity id_assunto){
//        List<PerguntaEntity> listPerguntasPorAssunto = perguntaRepository.findById_assunto(id_assunto);
//        List<PerguntaRequest> responseListPerguntasPorAssunto = new ArrayList<>();
//        for (PerguntaEntity pergunta : listPerguntasPorAssunto ){
//            responseListPerguntasPorAssunto.add(
//                    new PerguntaRequest(pergunta.getTitulo(), pergunta.getTexto())
//            );
//        }
//        return ResponseEntity.ok(responseListPerguntasPorAssunto);
//    }
}
