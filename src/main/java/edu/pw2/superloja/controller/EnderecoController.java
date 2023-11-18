package edu.pw2.superloja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.pw2.superloja.model.endereco.CepData;
import edu.pw2.superloja.model.endereco.Endereco;
import edu.pw2.superloja.model.endereco.EnderecoRepository;


import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoRepository enderecoRepo;

    @PostMapping
    public ResponseEntity<Endereco> createEndereco(@RequestBody CepData cepData) {
        Endereco endereco = new Endereco(cepData);
        Endereco savedEndereco = enderecoRepo.save(endereco);
        return new ResponseEntity<>(savedEndereco, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> getEnderecoById(@PathVariable Long id) {
        return enderecoRepo.findById(id)
                .map(endereco -> new ResponseEntity<>(endereco, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> getAllEnderecos() {
        List<Endereco> enderecos = enderecoRepo.findAll();
        return new ResponseEntity<>(enderecos, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEndereco(@PathVariable Long id, @RequestBody CepData cepData) {
        return enderecoRepo.findById(id)
                .map(existingEndereco -> {
                    existingEndereco.updateFromCepData(cepData);
                    enderecoRepo.save(existingEndereco);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEndereco(@PathVariable Long id) {
        return enderecoRepo.findById(id)
                .map(existingEndereco -> {
                    enderecoRepo.delete(existingEndereco);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
