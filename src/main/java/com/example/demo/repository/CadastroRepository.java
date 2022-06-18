package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CadastroRepository {

    public List<String> getAll() {
        List<String> nomes = new ArrayList<>();
        String path = "src/main/java/com/example/demo/repository/Massa de Dados.txt";
        File massaDeDados = new File(path);

        if(massaDeDados.exists()){
            try {
                FileReader fileReader = new FileReader(massaDeDados);
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                while(true){
                    String nome = bufferedReader.readLine();
                    if(nome == null){
                        return nomes;
                    }
                    nomes.add(nome);
                }
            }catch (Exception e){
                System.out.println("Houve um problema durante a leitura do arquivo");
                e.printStackTrace();
            }
        }
        System.out.println("Arquivo não encontrado no caminho especificado");
        return null;
    }
}
