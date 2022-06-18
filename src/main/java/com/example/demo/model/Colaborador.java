package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Colaborador {
    private String nome;
    private String login;

    @Override
    public String toString() {
        return "==============\nNome: "+nome+"\nLogin: "+login+"\n==============";
    }
}
