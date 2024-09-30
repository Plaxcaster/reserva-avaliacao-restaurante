package com.metrie.reservas.entities;

import com.metrie.reservas.enums.TipoDeCozinhaEnum;

public class TipoDeCozinhaEntity{

    static public TipoDeCozinhaEnum converteTipoDeCozinha(String tipoString) {

        return TipoDeCozinhaEnum.valueOf(tipoString.toUpperCase().trim());
    }
}
