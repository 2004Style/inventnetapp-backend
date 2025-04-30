package com.chavezsuarez.service.implementacion;

import org.springframework.stereotype.Service;

import com.chavezsuarez.model.Clientes;
import com.chavezsuarez.repository.IBaseRepo;
import com.chavezsuarez.repository.IClientesRepo;
import com.chavezsuarez.service.interfaces.IClientesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientesService extends BaseService<Clientes, Integer> implements IClientesService {

    protected final IClientesRepo repo;

    @Override
    protected IBaseRepo<Clientes, Integer> getRepo() {
        return repo;
    }

}
