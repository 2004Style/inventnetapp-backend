package com.chavezsuarez.assembler;

import org.springframework.stereotype.Component;

import com.chavezsuarez.controller.ProductosController;
import com.chavezsuarez.model.Productos;

@Component
public class ProductoAssembler extends GenericAssembler<Productos> {

    public ProductoAssembler() {
        super(ProductosController.class);
    }

    @Override
    protected Object getId(Productos entity) {
        return entity.getIdProducto();
    }
}