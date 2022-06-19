package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(massaDeDados.getPath()), StandardCharsets.ISO_8859_1);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

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
