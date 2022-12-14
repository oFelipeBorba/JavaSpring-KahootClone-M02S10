package com.example.m02s10.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pergunta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerguntaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column
    private String titulo;
    @Column
    private String texto;

    //Em uma entity um FK e um objeto e nao uma coluna, assim usa o JoinCollum e e preciso de um relacionamento.
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_assunto")
    private AssuntoEntity assuntoEntity;
}
