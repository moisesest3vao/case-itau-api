package com.example.demo.util;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@UtilityClass
public class ColaboradorUtil {
    public static List<String> historicoLogins = new ArrayList<>();
    public static String removeElementosRegExp = "(\\w)(\\s+)(DO|DA|DE|DOS|DAS)(\\s+)(\\w)";

    public static boolean validaLogin(String parte1, String parte2) {
        if (parte1 != null && parte2 != null) {
            String loginAtual = parte1 + parte2;
            String stringComparacao = parte1.substring(0, 3);

            return historicoLogins.stream().noneMatch(login -> {
                 return Objects.equals(login, loginAtual);
            }) && !stringComparacao.equals(parte2);
        }
        return false;
    }

    public static String removeElementosDeLigacao(String nome) {
        return nome.toUpperCase().replaceAll(removeElementosRegExp, "$1 $5");
    }
}
