package com.example.demo.dto;

import com.example.demo.model.Colaborador;
import lombok.Getter;

@Getter
public class ColaboradorDto {
    private final String nome;
    private final String login;
    public ColaboradorDto(Colaborador colaborador){
        this.nome = colaborador.getNome();
        this.login = colaborador.getLogin();

        System.out.println(colaborador.toString());
    }
}
