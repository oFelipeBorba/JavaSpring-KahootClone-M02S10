package com.example.m02s10.controller.dto;

import com.example.m02s10.entity.AssuntoEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssuntoRequest {

    @NotBlank
    private String nome;

    public AssuntoEntity toNovoAssunto() {
        AssuntoEntity novoAssunto = new AssuntoEntity();
        novoAssunto.setNome(nome);
        return novoAssunto;
    }


}
