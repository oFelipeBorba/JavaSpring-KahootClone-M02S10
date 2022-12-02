package com.example.m02s10.controller.dto;

import com.example.m02s10.entity.RespostaEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RespostaRequest {

    @NotBlank
    private String texto;

    @NotBlank
    private Long id_pergunta;

    public RespostaRequest(String texto) {
    }


    public RespostaEntity toNovaResposta(){
        RespostaEntity novaResposta = new RespostaEntity();
        novaResposta.setTexto(texto);
        return novaResposta;
    }

}
