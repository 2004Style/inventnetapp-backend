package com.chavezsuarez.service.implementacion;

import java.util.List;

import com.chavezsuarez.errors.ModelNotFoundException;
import com.chavezsuarez.repository.IBaseRepo;
import com.chavezsuarez.service.interfaces.IBaseService;

public abstract class BaseService<T, ID> implements IBaseService<T, ID> {
    
    protected abstract IBaseRepo<T, ID> getRepo();

    @Override
    public T save(T entity) throws Exception {
        return getRepo().save(entity);
    }

    @Override
    public T update(T entity, ID id) throws Exception {
        getRepo().findById(id).orElseThrow(() -> new Exception("No se encontro el objeto con id " + id));
        return getRepo().save(entity);
    }

    @Override
    public List<T> findAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) throws Exception {
        return getRepo().findById(id).orElseThrow(()-> new ModelNotFoundException("ID NOT FOUND: " + id));
    }

    @Override
    public void delete(ID id) throws Exception {
        getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        getRepo().deleteById(id);
    }
}
