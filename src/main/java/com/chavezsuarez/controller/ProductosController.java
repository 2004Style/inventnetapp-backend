package com.chavezsuarez.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chavezsuarez.assembler.ProductoAssembler;
import com.chavezsuarez.model.Productos;
import com.chavezsuarez.service.interfaces.IProductosService;

import lombok.RequiredArgsConstructor;

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

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductosController {

    private final IProductosService service;
    private final ProductoAssembler productoAssembler;

    @GetMapping
    public CollectionModel<EntityModel<Productos>> findAll() throws Exception {
        List<EntityModel<Productos>> productos = service.findAll()
                .stream()
                .map(productoAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(productos);
    }

    @GetMapping("/{id}")
    public EntityModel<Productos> findById(@PathVariable("id") Integer id) throws Exception {
        Productos producto = service.findById(id);
        return productoAssembler.toModel(producto);
    }

    @PostMapping
    public EntityModel<Productos> create(@RequestBody Productos producto) throws Exception {
        Productos productos = service.save(producto);
        return productoAssembler.toModel(productos);
    }

    @PutMapping("/{id}")
    public EntityModel<Productos> update(@PathVariable("id") Integer id, @RequestBody Productos producto)
            throws Exception {
        Productos productos = service.update(producto, id);
        return productoAssembler.toModel(productos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
