package com.metrie.reservas.gateways;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metrie.reservas.entities.RestauranteEntity;
import com.metrie.reservas.usecases.CadastrarRestauranteUseCases;

import org.springframework.web.bind.annotation.PostMapping;


/*Criei um fat gateway, se precisarmos dividir no futuro tudo bem */
@RestController
@RequestMapping("/")
public class MainGateway {

    @Autowired 
    private CadastrarRestauranteUseCases cadastrarRestauranteUseCase;

    @GetMapping()
    public ResponseEntity<String> HelloWorld() {
        return ResponseEntity.ok("Hello World");
    }

    @PostMapping("/restaurantePadrao")
    public ResponseEntity<RestauranteEntity> postMethodName(String nomeString , String regiaoString , String tipoCozinhaString) {
        return ResponseEntity.ok(cadastrarRestauranteUseCase.cadastrarRestaurante(nomeString , regiaoString, tipoCozinhaString));
    }
}
