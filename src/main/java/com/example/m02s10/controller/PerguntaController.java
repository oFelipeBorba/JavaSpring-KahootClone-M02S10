package com.example.m02s10.controller;

import com.example.m02s10.controller.dto.PerguntaRequest;
import com.example.m02s10.controller.dto.RespostaRequest;
import com.example.m02s10.entity.AssuntoEntity;
import com.example.m02s10.entity.PerguntaEntity;
import com.example.m02s10.entity.RespostaEntity;
import com.example.m02s10.exception.NotFoundException;
import com.example.m02s10.repository.AssuntoRepository;
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
    @Autowired
    private AssuntoRepository assuntoRepository;

    @GetMapping
    public ResponseEntity<List<PerguntaRequest>> buscarTodasPerguntas(){
        List<PerguntaEntity> perguntaEntityList = perguntaRepository.findAll();
        List<PerguntaRequest> perguntaResponseList = new ArrayList<>();
        for(PerguntaEntity pergunta : perguntaEntityList){
            if (pergunta.getAssuntoEntity() == null){
                throw new NotFoundException("A resposta de id:"+ pergunta.getId()+" não contém id.");
            }else{
            perguntaResponseList.add(
                    new PerguntaRequest(pergunta.getTitulo(), pergunta.getTexto(),pergunta.getAssuntoEntity().getId())
            );
        }}
        return ResponseEntity.ok(perguntaResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PerguntaRequest> buscarPerguntaPorId(@PathVariable Long id){
        PerguntaEntity perguntaDoId = perguntaRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Não foi possível obter o objeto a partir do id:"+id));
        return new ResponseEntity<>(
               new PerguntaRequest(perguntaDoId.getTitulo(), perguntaDoId.getTexto(),perguntaDoId.getAssuntoEntity().getId()),
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
        PerguntaEntity perguntaEntity = new PerguntaEntity();
        AssuntoEntity assuntoEntity = assuntoRepository.findById(perguntaRequest.getId_assunto())
                .orElseThrow(()->new NotFoundException("Não foi possível obter o id_assunto"));
        perguntaEntity.setAssuntoEntity(assuntoEntity);
        perguntaEntity.setTexto(perguntaRequest.getTexto());
        perguntaEntity.setTitulo(perguntaRequest.getTitulo());
        perguntaRepository.save(perguntaEntity);
        return new ResponseEntity<>(
                new PerguntaRequest(perguntaRequest.getTitulo(),perguntaRequest.getTexto(), perguntaRequest.getId_assunto()),
                HttpStatus.CREATED
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<PerguntaRequest> atualizaPerguntaPorId(@PathVariable Long id, @RequestBody PerguntaRequest request){
        PerguntaEntity perguntaEntity = perguntaRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Não foi possível obter o obj por meio do id:"+id));
        AssuntoEntity assuntoEntity = assuntoRepository.findById(request.getId_assunto())
                .orElseThrow(()->new NotFoundException("Não foi possível obter o obj por meio do id:"+id));
        perguntaEntity.setTexto(request.getTexto());
        perguntaEntity.setTitulo(request.getTitulo());
        perguntaEntity.setAssuntoEntity(assuntoEntity);
        perguntaRepository.save(perguntaEntity);
        return new ResponseEntity<>(
                new PerguntaRequest(perguntaEntity.getTexto(), perguntaEntity.getTitulo(), perguntaEntity.getAssuntoEntity().getId()),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletaPerguntaPorId(@PathVariable Long id){
        perguntaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
