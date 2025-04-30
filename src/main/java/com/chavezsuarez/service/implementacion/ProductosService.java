package com.chavezsuarez.service.implementacion;

import org.springframework.stereotype.Service;

import com.chavezsuarez.model.Productos;
import com.chavezsuarez.repository.IBaseRepo;
import com.chavezsuarez.repository.IProductosRepo;
import com.chavezsuarez.service.interfaces.IProductosService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductosService extends BaseService<Productos, Integer> implements IProductosService {

    protected final IProductosRepo repo;

    @Override
    protected IBaseRepo<Productos, Integer> getRepo() {
        return repo;
    }

}
