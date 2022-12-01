package com.example.m02s10.controller.dto;

import com.example.m02s10.entity.AssuntoEntity;
import com.example.m02s10.entity.PerguntaEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerguntaRequest {

    @NotBlank
    private String titulo;
    @NotBlank
    private String texto;

    public PerguntaEntity toNovaPergunta(){
        PerguntaEntity novaPergunta = new PerguntaEntity();
        novaPergunta.setTitulo(titulo);
        novaPergunta.setTexto(texto);
        return novaPergunta;
    }
}
