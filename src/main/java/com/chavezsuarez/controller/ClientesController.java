package com.chavezsuarez.controller;

// import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.chavezsuarez.assembler.ClientesAssembler;
import com.chavezsuarez.model.Clientes;
import com.chavezsuarez.service.interfaces.IClientesService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClientesController {
    private final IClientesService service;
    private final ClientesAssembler clientesAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Clientes>> findAll() throws Exception {
        List<EntityModel<Clientes>> clientes = service.findAll()
                .stream()
                .map(clientesAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(clientes);
    }

    @GetMapping("/{id}")
    public EntityModel<Clientes> findById(@PathVariable("id") Integer id) throws Exception {
        Clientes cliente = service.findById(id);
        return clientesAssembler.toModel(cliente);
    }

    @PostMapping
    public EntityModel<Clientes> create(@RequestBody Clientes producto) throws Exception {
        Clientes productos = service.save(producto);
        // URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(productos.getIdCliente()).toUri();
        return clientesAssembler.toModel(productos);

        // return ResponseEntity.created(location).body(productos);
    }

    @PutMapping("/{id}")
    public EntityModel<Clientes> update(@PathVariable("id") Integer id, @RequestBody Clientes producto)
            throws Exception {
        Clientes productos = service.update(producto, id);
        return clientesAssembler.toModel(productos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
