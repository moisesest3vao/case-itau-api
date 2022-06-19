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

    public List<Colaborador> cadastrarColaboradores() {
        List<String> nomesDosColaboradores = this.cadastroRepository.getAll();
        List<Colaborador> colaboradores = nomesDosColaboradores.stream().map(this::gerarLogin).toList();

        /*
           Neste exemplo utilizei chamadas iguais repetidamente. Por isso se faz necessário o reset do
           'ColaboradorUtil.históricoDeLogins' na linha 27 para que seja possível gerar o cadastro com os nomes iguais
           na próxima requisição. Em produção isso não seria necessário.
         */
        ColaboradorUtil.historicoLogins = new ArrayList<>();

        return colaboradores;
    }

    public Colaborador gerarLogin(String nomeCompleto) {
        String nomeSemElementos = ColaboradorUtil.removeElementosDeLigacao(nomeCompleto);
        String[] nomeESobrenomes = nomeSemElementos.split(" ");
        String login = this.montaLogin(nomeESobrenomes);

        return new Colaborador(nomeCompleto, login);
    }

    private String montaLogin(String[] nomeESobrenomes) {
        String parte1 = null;
        String parte2 = null;

        boolean validaLogin = false;
        int quantidadeDeNomes = nomeESobrenomes.length;
        int contador = 0;

        while (!validaLogin) {
            if (contador > 9999) {
                throw new RuntimeException("Não é possível gerar um usuário dinamicamente com este nome");
            }

            Random random = new Random();
            int indexNome1 = random.nextInt(((quantidadeDeNomes - 1)) + 1);
            int indexNome2 = random.nextInt(((quantidadeDeNomes - 1)) + 1);

            parte1 = nomeESobrenomes[indexNome1].length() >= 4 ? nomeESobrenomes[indexNome1].substring(0, 4) : null;
            parte2 = nomeESobrenomes[indexNome2].length() >= 3 ? nomeESobrenomes[indexNome2].substring(0, 3) : null;

            validaLogin = ColaboradorUtil.validaLogin(parte1, parte2);

            contador++;
        }

        String login = parte1+parte2;

        ColaboradorUtil.historicoLogins.add(login);
        return login;
    }


}
