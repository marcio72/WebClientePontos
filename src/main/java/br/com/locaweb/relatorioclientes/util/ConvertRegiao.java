package br.com.locaweb.relatorioclientes.util;

public class ConvertRegiao {

    public static String exibirNome(int regiao) {
        return switch (regiao) {
            case 1 -> "Norte";
            case 2 -> "Sul";
            case 3 -> "Leste";
            case 4 -> "Oeste";
            case 5 -> "Centro";
            case 6 -> "ABC";
            default -> "----";
        };
    }
}
