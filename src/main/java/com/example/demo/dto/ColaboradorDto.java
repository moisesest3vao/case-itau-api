package com.example.demo.dto;

import com.example.demo.model.Colaborador;
import lombok.Getter;

@Getter
public class ColaboradorDto {
    private String nome;
    private String login;
    public ColaboradorDto(Colaborador colaborador){
        this.nome = colaborador.getNome();
        this.login = colaborador.getLogin();
    }
}
