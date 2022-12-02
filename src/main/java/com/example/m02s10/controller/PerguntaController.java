package com.example.m02s10.controller;

import com.example.m02s10.controller.dto.PerguntaRequest;
import com.example.m02s10.controller.dto.RespostaRequest;
import com.example.m02s10.entity.AssuntoEntity;
import com.example.m02s10.entity.PerguntaEntity;
import com.example.m02s10.entity.RespostaEntity;
import com.example.m02s10.repository.PerguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/porassunto/{id}")
    public ResponseEntity retornaPerguntasPorAssunto(@PathVariable Long id){
        List<PerguntaEntity> perguntaEntityList =
                perguntaRepository.findPerguntaEntitiesByAssuntoEntity_Id(id);
        return ResponseEntity.ok(perguntaEntityList);
    }

    @PostMapping
    public ResponseEntity<PerguntaRequest> salvaNovaPergunta(@RequestBody PerguntaRequest perguntaRequest){
        PerguntaEntity perguntaEntity = perguntaRequest.toNovaPergunta();
        perguntaRepository.save(perguntaEntity);
        return new ResponseEntity<>(
                new PerguntaRequest(perguntaRequest.getTitulo(),perguntaRequest.getTexto()),
                HttpStatus.CREATED
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<PerguntaRequest> atualizaPerguntaPorId(@PathVariable Long id, @RequestBody PerguntaRequest request){
        PerguntaEntity perguntaEntity = perguntaRepository.findById(id).get();
        perguntaEntity.setTexto(request.getTexto());
        perguntaEntity.setTitulo(request.getTitulo());
        perguntaRepository.save(perguntaEntity);
        return new ResponseEntity<>(
                new PerguntaRequest(perguntaEntity.getTexto(), perguntaEntity.getTitulo()),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletaPerguntaPorId(@PathVariable Long id){
        perguntaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
