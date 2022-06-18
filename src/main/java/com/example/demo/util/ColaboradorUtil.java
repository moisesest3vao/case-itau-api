package com.example.demo.util;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@UtilityClass
public class ColaboradorUtil {
    public static List<String> historicoLogins = new ArrayList<>();
    public static String expressaoRegular = "(\\w)(\\s+)(DO|DA|DE|DOS|DAS)(\\s+)(\\w)";

    public static boolean invalidaLogin(String parte1, String parte2) {
        if(parte1 != null && parte2 != null ){
            String loginAtual = parte1+parte2;
            String stringComparacao = parte1.substring(0, 3);
            /*
            System.out.println(parte1.substring(0,3));
            System.out.println(parte2);
            */
            return historicoLogins.stream().anyMatch( login -> {
                return Objects.equals(login, loginAtual) || stringComparacao.equals(parte2);
            });
        }
        return true;
    }

    public static String removeElementosDeLigacao(String nome) {
        return nome.toUpperCase().replaceAll(expressaoRegular, "$1 $5");
    }
}
