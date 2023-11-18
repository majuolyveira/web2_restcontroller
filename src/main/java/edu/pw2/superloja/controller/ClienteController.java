package edu.pw2.superloja.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.pw2.superloja.model.cliente.Cliente;
import edu.pw2.superloja.model.cliente.ClienteData;
import edu.pw2.superloja.model.cliente.ClienteDataResumo;
import edu.pw2.superloja.model.cliente.ClienteRepository;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepo;

    @GetMapping
    public Page<ClienteData> getAllClientes(@PageableDefault(sort = {"nome"}, size = 10) Pageable paginacao) {
        return clienteRepo.findAll(paginacao).map(ClienteData::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteData> getCliente(@PathVariable Long id) {
        return clienteRepo.findById(id)
                .map(cliente -> new ResponseEntity<>(new ClienteData(cliente), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> saveCliente(@RequestBody ClienteData dados) {
        Cliente c = new Cliente(dados);
        clienteRepo.save(c);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void deleteCliente(@PathVariable Long id) {
        if (id != null) {
            clienteRepo.deleteById(id);
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public void updateCliente(@PathVariable Long id, @RequestBody ClienteDataResumo data) {
        clienteRepo.findById(id).ifPresent(cliente -> cliente.update(data));
    }
}
