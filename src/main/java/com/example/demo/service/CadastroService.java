package com.example.demo.service;

import com.example.demo.model.Colaborador;
import com.example.demo.repository.CadastroRepository;
import com.example.demo.util.ColaboradorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CadastroService {
    @Autowired
    CadastroRepository cadastroRepository;

    public List<Colaborador> cadastrarColaboradores(){
        List<String> nomesDosColaboradores = this.cadastroRepository.getAll();
        List<Colaborador> colaboradores = nomesDosColaboradores.stream().map(this::criarLogin).toList();


        //resetando o histórico para cada chamada
        ColaboradorUtil.historicoLogins = new ArrayList<>();
        return colaboradores;
    }

    public Colaborador criarLogin(String nome){
        String nomeSemElementos = ColaboradorUtil.removeElementosDeLigacao(nome);
        String[] nomes = nomeSemElementos.split(" ");
        String login = this.criaIdentificador(nomes);

        return new Colaborador(nome, login);
    }

    public String criaIdentificador(String[] nomes){
        String login = this.montaLogin(nomes);

        ColaboradorUtil.historicoLogins.add(login);
        return login;
    }

    private String montaLogin(String[] nomes){
        String parte1 = null;
        String parte2 = null;

        boolean invalidaLogin = true;
        int quantidadeDeNomes = nomes.length;
        int contador = 0;

        while(invalidaLogin){
            if(contador > 3000){
                throw new RuntimeException("Não é possível gerar um usuário dinamicamente com este nome");
            }

            Random random = new Random();
            int aux = random.nextInt(((quantidadeDeNomes - 1)) + 1);
            int aux2 = random.nextInt(((quantidadeDeNomes - 1)) + 1);

            if(nomes[aux].length() >=4){
                parte1 = nomes[aux].substring(0,4);
                if(nomes[aux2].length() >= 3){
                    parte2 = nomes[aux2].substring(0,3);
                }
            }

            invalidaLogin = ColaboradorUtil.invalidaLogin(parte1, parte2);
            contador++;
        }

        return parte1+parte2;
    }






}
