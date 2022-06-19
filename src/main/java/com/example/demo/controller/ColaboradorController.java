package com.example.demo.controller;

import com.example.demo.dto.ColaboradorDto;
import com.example.demo.model.Colaborador;
import com.example.demo.service.CadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cadastro")
public class ColaboradorController {
    @Autowired
    CadastroService cadastroService;

    //Responsável por receber a requisição do front-end e tratá-la
    @GetMapping
    public ResponseEntity<?> cadastrar(){
        List<Colaborador> colaboradores = cadastroService.cadastrarColaboradores();
        return ResponseEntity.ok(colaboradores.stream().map(ColaboradorDto::new).collect(Collectors.toList()));
    }
}
