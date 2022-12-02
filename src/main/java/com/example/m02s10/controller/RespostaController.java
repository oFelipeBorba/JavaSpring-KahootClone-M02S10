package com.example.m02s10.controller;

import com.example.m02s10.controller.dto.RespostaRequest;
import com.example.m02s10.entity.PerguntaEntity;
import com.example.m02s10.entity.RespostaEntity;
import com.example.m02s10.exception.NotFoundException;
import com.example.m02s10.repository.PerguntaRepository;
import com.example.m02s10.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/resposta")
public class RespostaController {

    @Autowired
    private RespostaRepository respostaRepository;
    @Autowired
    private PerguntaRepository perguntaRepository;

    @GetMapping
    public ResponseEntity<List<RespostaRequest>> buscarTodasRespostas(){
        List<RespostaEntity> listaDasRespostas = respostaRepository.findAll();
        List<RespostaRequest> listaDasRespostasResponse = new ArrayList<>();
//        try{
            for (RespostaEntity resposta : listaDasRespostas){
                //nesse ponto n da pra fazer.getId() pq o objk do tipo PerguntaEntity nem existe
                if (resposta.getPerguntaEntity() == null){
                    throw new NotFoundException("A resposta de id:"+ resposta.getId()+" não contém id.");
                }else{
                    listaDasRespostasResponse.add(
                            new RespostaRequest(resposta.getTexto(),
                                    resposta.getPerguntaEntity().getId()) // e se aqui vier sem o id?
                    );}
            }
//        }catch (Exception e){
//            System.out.println(e.getCause() + e.getMessage());
//        }
        return ResponseEntity.ok(listaDasRespostasResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RespostaRequest> buscarRespostarPorID(@PathVariable Long id){
        RespostaEntity respostaPorId = respostaRepository.findById(id)
                .orElseThrow(()->new NotFoundException("Não foram obtidos resultados pelo id:"+id));
        return new ResponseEntity<RespostaRequest>(
          new RespostaRequest(respostaPorId.getTexto(), respostaPorId.getPerguntaEntity().getId()),
                HttpStatus.OK
        );
    }
    @GetMapping("/porpergunta/{id}")
    public ResponseEntity buscarRespostasPorPerguntaId(@PathVariable Long id){
        List<RespostaEntity> respostasPorPergunta = respostaRepository.findRespostasPorId_Pergunta(id);
        return ResponseEntity.ok(respostasPorPergunta);
    }

    //observar alterações realizadas nesse post para incluir o obj pergunta
    @PostMapping
    public ResponseEntity<RespostaRequest> salvaNovaResposta(@RequestBody RespostaRequest respostaRequest){
        PerguntaEntity perguntaEntity = perguntaRepository.findById(respostaRequest.getId_pergunta()).get();
        RespostaEntity respostaEntity = new RespostaEntity();
        respostaEntity.setTexto(respostaRequest.getTexto());
        respostaEntity.setPerguntaEntity(perguntaEntity);
        respostaRepository.save(respostaEntity);
        return new ResponseEntity<>(
                new RespostaRequest(respostaRequest.getTexto(),perguntaEntity.getId()),
                HttpStatus.CREATED
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<RespostaRequest> atualizaRespostaPorId(@PathVariable Long id,@RequestBody RespostaRequest respostaRequest){
        RespostaEntity respostaEntity = respostaRepository.findById(id).get();
        respostaEntity.setTexto(respostaRequest.getTexto());
        respostaRepository.save(respostaEntity);
        return new ResponseEntity<>(
                new RespostaRequest(respostaEntity.getTexto()),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletaRespostaPorId(@PathVariable Long id){
        respostaRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
