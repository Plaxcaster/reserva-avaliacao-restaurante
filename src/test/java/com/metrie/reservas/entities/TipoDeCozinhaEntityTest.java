package com.metrie.reservas.entities;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.metrie.reservas.enums.TipoDeCozinhaEnum;

public class TipoDeCozinhaEntityTest {
    @Test
    void converteTipoDeCozinhaPrecisaConverterMinuscula() {

        assertThat(TipoDeCozinhaEntity.converteTipoDeCozinha("Hamburguer")).isEqualTo(TipoDeCozinhaEnum.HAMBURGUER);
        assertThat(TipoDeCozinhaEntity.converteTipoDeCozinha("iNDIANA")).isEqualTo(TipoDeCozinhaEnum.INDIANA);
        assertThat(TipoDeCozinhaEntity.converteTipoDeCozinha("chinesa")).isEqualTo(TipoDeCozinhaEnum.CHINESA);
        assertThat(TipoDeCozinhaEntity.converteTipoDeCozinha("SUSHI")).isEqualTo(TipoDeCozinhaEnum.SUSHI);
    }

    @Test
    void converteTipoDeCozinhaPrecisaRetirarEspacosBrancos() {

        assertThat(TipoDeCozinhaEntity.converteTipoDeCozinha("     CHINESA")).isEqualTo(TipoDeCozinhaEnum.CHINESA);
        assertThat(TipoDeCozinhaEntity.converteTipoDeCozinha("CHINESA         ")).isEqualTo(TipoDeCozinhaEnum.CHINESA);
        assertThat(TipoDeCozinhaEntity.converteTipoDeCozinha("           CHINESA         "))
                .isEqualTo(TipoDeCozinhaEnum.CHINESA);
    }
}
