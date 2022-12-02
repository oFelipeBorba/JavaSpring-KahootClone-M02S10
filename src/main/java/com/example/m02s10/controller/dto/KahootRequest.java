package com.example.m02s10.controller.dto;

import com.example.m02s10.entity.KahootEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KahootRequest {
    private String assunto;
    private String pergunta1;
    private String pergunta2;
    private String pergunta3;
    private String respostaCorretaP1;
    private String respostaCorretaP2;
    private String respostaCorretaP3;
    private String respostaErrada1P1;
    private String respostaErrada2P1;
    private String respostaErrada1P2;
    private String respostaErrada2P2;
    private String respostaErrada1P3;
    private String respostaErrada2P3;

    public KahootEntity toNovoAssunto(){
        KahootEntity kahootEntity= new KahootEntity();
        kahootEntity.setAssunto(assunto);
        return kahootEntity;
    }
}
