package com.fiap.spring.Controller;

import com.fiap.reserva.application.controller.UsuarioControllerApplication;
import com.fiap.reserva.domain.exception.BusinessException;
import com.fiap.spring.Controller.Dto.UsuarioDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioControllerSpring {

    private UsuarioControllerApplication usuarioController;
    @PostMapping
    public ResponseEntity<?> criarUsuario(@RequestBody UsuarioDto usuarioDto){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioController.cadastrar(usuarioDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> alterarUsuario(@RequestBody UsuarioDto usuarioDto ){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioController.alterar(usuarioDto));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<?> excluirUsuario(@PathVariable String email ){
        try{
            usuarioController.excluir(email);
            return ResponseEntity.status(HttpStatus.CREATED).body("Sucesso");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> buscarPorUsuario(@PathVariable UsuarioDto usuarioDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    usuarioController.getBuscarPor(usuarioDto)
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{cnpj}")
    public ResponseEntity<?> buscarPorEmail(@PathVariable String cnpj){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    usuarioController.getBuscarPorEmail(cnpj)
            );
        } catch (BusinessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}