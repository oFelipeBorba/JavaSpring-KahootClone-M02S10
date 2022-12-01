package com.example.m02s10.controller;

import com.example.m02s10.controller.dto.AssuntoRequest;
import com.example.m02s10.entity.AssuntoEntity;
import com.example.m02s10.entity.PerguntaEntity;
import com.example.m02s10.repository.AssuntoRepository;
import com.example.m02s10.repository.PerguntaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/assunto")
public class AssuntoController {

    @Autowired
    private AssuntoRepository assuntoRepository;
    @Autowired
    PerguntaRepository perguntaRepository;

    @GetMapping
    //get retornando um responseentity por ter marcado esse controler como rest
    //responseentity permite retornar o http, se deu certo ou um err por exemplo
    public ResponseEntity<List<AssuntoRequest>> buscarTodosAssuntos(){
        //crio um alista de assunto entity e uso o metodo do jpa para encontrar todos os dados
        List<AssuntoEntity> entityList = assuntoRepository.findAll();
        //crio uma lista do tipo assunto request, para acessar apenas os dados de interesse e n devolver o entity completo
        List<AssuntoRequest> responseList = new ArrayList<>();
        //faco um for para passar os dados da entity list para dentro da responseList, que e a do assuntorequest
        for (AssuntoEntity assunto: entityList){
            responseList.add(
                    //para cada dado eu crio um obj de assunto request com o dado de interesse
                    new AssuntoRequest(assunto.getNome())
            );
        }
        //retorno o responseEntity com o metodo ok informando que o get foi bem sucedido
        return ResponseEntity.ok(responseList);
    }

    //Get por meio de um id, ficara /assunto/valorDoIdDePesquisa
    @GetMapping("/{id}")
    //Como e so um valor nao retorna a lista, deve ser passado um PathVariable que sera o meio de pesquisa, no caso um Long id
    public ResponseEntity<AssuntoRequest> buscarAssuntosPorId(@PathVariable Long id){
        //Uso o JPA para fazer o findById e dou get nele
        AssuntoEntity assuntoEntity = assuntoRepository.findById(id).get();
        //retorno uma nova response do tipo assunto request
        return new ResponseEntity<>(
                //dentro dela crio o obj do tipo assuntoRequest apenas buscando o nome, depois coloco o httstatus para informar que deu certo
                new AssuntoRequest(assuntoEntity.getNome()),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<AssuntoRequest> salvaNovoAssunto(@RequestBody AssuntoRequest assuntoRequest){
        AssuntoEntity assuntoEntity = assuntoRequest.toNovoAssunto();
        assuntoRepository.save(assuntoEntity);
        return new ResponseEntity<AssuntoRequest>(
                new AssuntoRequest(assuntoEntity.getNome()),
                HttpStatus.CREATED
        );
    }

}
