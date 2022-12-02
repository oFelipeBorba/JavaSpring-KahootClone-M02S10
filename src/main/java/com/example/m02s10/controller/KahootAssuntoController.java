package com.example.m02s10.controller;

import com.example.m02s10.controller.dto.KahootRequest;
import com.example.m02s10.entity.AssuntoEntity;
import com.example.m02s10.entity.KahootEntity;
import com.example.m02s10.repository.AssuntoRepository;
import com.example.m02s10.repository.KahootRepository;
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
@RequestMapping("/kahoot")
public class KahootAssuntoController {

    @Autowired
    private AssuntoRepository assuntoRepository;
    @Autowired
    private KahootRepository kahootRepository;

    @GetMapping
    public String home(KahootRequest kahootRequest, Model model){
        List<AssuntoEntity> listAssunto = assuntoRepository.findAll();
        model.addAttribute("assuntos", listAssunto);
        return "montaKahootAssunto";
    }
    @PostMapping("/novo")
    public String novo(KahootRequest kahootRequest){
        KahootEntity kahootEntity = kahootRequest.toNovoAssunto();
        kahootRepository.save(kahootEntity);
        return "redirect:/montaKahootRespostas";
    }

}
