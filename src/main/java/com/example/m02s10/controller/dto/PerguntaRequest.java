package com.example.m02s10.controller.dto;

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

}
