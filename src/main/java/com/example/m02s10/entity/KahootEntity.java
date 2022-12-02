package com.example.m02s10.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KahootEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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





}
