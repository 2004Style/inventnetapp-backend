package com.chavezsuarez.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import lombok.RequiredArgsConstructor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RequiredArgsConstructor
public abstract class GenericAssembler<T> implements RepresentationModelAssembler<T, EntityModel<T>> {

    private final Class<?> controllerClass;

    @Override
    public @org.springframework.lang.NonNull EntityModel<T> toModel(@org.springframework.lang.NonNull T entity) {
        EntityModel<T> model = EntityModel.of(entity);
        model.add(linkTo(controllerClass).slash(getId(entity)).withSelfRel());
        model.add(linkTo(controllerClass).withRel("collection"));
        model.add(linkTo(controllerClass).slash(getId(entity)).withRel("update"));
        model.add(linkTo(controllerClass).slash(getId(entity)).withRel("delete"));
        model.add(linkTo(controllerClass).withRel("create"));
        return model;
    }

    protected abstract Object getId(T entity);
}