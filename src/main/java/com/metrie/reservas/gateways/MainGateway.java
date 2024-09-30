package com.metrie.reservas.gateways;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metrie.reservas.controllers.CadastrarRestauranteController;
import com.metrie.reservas.controllers.CadastrarUsuarioController;
import com.metrie.reservas.controllers.ConsultarRestaurantesController;
import com.metrie.reservas.controllers.CriarReservaController;
import com.metrie.reservas.entities.ReservaEntity;
import com.metrie.reservas.entities.RestauranteEntity;
import com.metrie.reservas.entities.UsuarioEntity;

import org.springframework.web.bind.annotation.PostMapping;


/*Criei um fat gateway, se precisarmos dividir no futuro tudo bem */
@RestController
@RequestMapping("/")
public class MainGateway {

    @Autowired
    private CadastrarRestauranteController cadastrarRestauranteController;

    @Autowired
    private CriarReservaController criarReservaController;

    @Autowired
    private CadastrarUsuarioController cadastrarUsuarioController;

    @Autowired
    private ConsultarRestaurantesController consultarRestaurantesController;

    @GetMapping()
    public ResponseEntity<String> HelloWorld() {
        return ResponseEntity.ok("Hello World");
    }

    @PostMapping("/restaurante")
    public ResponseEntity<RestauranteEntity> cadastrarRestaurante(String nomeString, String regiaoString,
            String tipoCozinhaString, String horarioAbertura, String horarioFechamento , String capacidade) {
        return ResponseEntity
                .ok(cadastrarRestauranteController.cadastrarRestaurante(nomeString, regiaoString, tipoCozinhaString,
                        horarioAbertura, horarioFechamento, capacidade));
    }

    @PostMapping("/reserva")
    public ResponseEntity<ReservaEntity> criarReserva(String IDRestaurante, String IDUsuario, String horarioInicio) {
        return ResponseEntity.ok(criarReservaController.criarReserva(IDRestaurante, IDUsuario, horarioInicio));
    }

    @PostMapping("/usuario")
    public ResponseEntity<UsuarioEntity> cadastrarUsuario(String nome) {
        return ResponseEntity.ok(cadastrarUsuarioController.cadastrarUsuario(nome));
    }

    @GetMapping("/restaurantePorNome")
    public ResponseEntity<List<RestauranteEntity>> listaRestaurantePorNome(String nomeString) {
        return ResponseEntity.ok(consultarRestaurantesController.listaRestaurantePorNome(nomeString));
    }
    @GetMapping("/restaurantePorRegiao")
    public ResponseEntity<List<RestauranteEntity>> listaRestaurantePorRegiao(String regiaoString) {
        return ResponseEntity.ok(consultarRestaurantesController.listaRestaurantePorRegiao(regiaoString));
    }
    @GetMapping("/restaurantePorTipo")
    public ResponseEntity<List<RestauranteEntity>> listaRestaurantePorTipoDeCozinha(String tipoDeCozinhaString) {
        return ResponseEntity.ok(consultarRestaurantesController.listaRestaurantePorTipoDeCozinha(tipoDeCozinhaString));
    }
}
