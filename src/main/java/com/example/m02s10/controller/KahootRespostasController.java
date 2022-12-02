package com.example.m02s10.controller;

import com.example.m02s10.controller.dto.KahootRequest;
import com.example.m02s10.entity.AssuntoEntity;
import com.example.m02s10.entity.KahootEntity;
import com.example.m02s10.entity.RespostaEntity;
import com.example.m02s10.repository.AssuntoRepository;
import com.example.m02s10.repository.PerguntaRepository;
import com.example.m02s10.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/montaKahootRespostas")
public class KahootRespostasController {

    @Autowired
    private AssuntoRepository assuntoRepository;
    @Autowired
    private PerguntaRepository perguntaRepository;
    @Autowired
    private RespostaRepository respostaRepository;

    @GetMapping
    public String home(KahootRequest kahootRequest, Model model){
        List<RespostaEntity> listRespostas = respostaRepository.findAll();
        model.addAttribute("respostas", listRespostas);
        return "montaKahootAssunto";
    }
//    @PostMapping("/gamer")
//    public String novo(KahootRequest kahootRequest){
//        KahootEntity kahootEntity = new KahootEntity();
//        kahootEntity.setAssunto(kahootRequest.getAssunto());
//
//        return "redirect:/montaKahootRespostas";
//    }

}
