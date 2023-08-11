package br.com.dbc.vemser.tf03spring.model.enums;

import java.util.Arrays;

public enum TipoPeriodo {

    MANHA(1),
    TARDE(2),
    NOITE(3);

    private Integer tipoPeriodo;

    TipoPeriodo(Integer tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }

    public Integer getTipoPeriodo() {
        return tipoPeriodo;
    }

    public static TipoPeriodo ofTipoPeriodo(Integer tipo) {
        if (tipo == 1 || tipo == 2 || tipo == 3) {
            return Arrays.stream(TipoPeriodo.values())
                    .filter(tp -> tp.getTipoPeriodo().equals(tipo))
                    .findFirst()
                    .get();
        }
        throw new IllegalArgumentException("Período inválido: " + tipo);
    }

    public static TipoPeriodo fromString(String strTipo) {
        for (TipoPeriodo tipo : TipoPeriodo.values()) {
            if (tipo.name().equalsIgnoreCase(strTipo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de período inválido: " + strTipo);
    }
}