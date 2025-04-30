package com.chavezsuarez.assembler;

import org.springframework.stereotype.Component;

import com.chavezsuarez.controller.ClientesController;
import com.chavezsuarez.model.Clientes;

@Component
public class ClientesAssembler extends GenericAssembler<Clientes> {

    public ClientesAssembler() {
        super(ClientesController.class);
    }

    @Override
    protected Object getId(Clientes entity) {
        return entity.getIdCliente();
    }
}