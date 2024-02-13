package ru.veucos.cms.controller.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.veucos.cms.controller.BaseController;
import ru.veucos.cms.service.BaseService;

import java.util.List;

@Log4j2
public abstract class BaseControllerImpl<O, T, K> implements BaseController<T, K> {

    protected BaseService<O, T, K> service;

    @Autowired
    public void setService(BaseService<O, T, K> service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<List<T>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<T>> getPage(Integer pageNum, Integer pageSize) {
        return new ResponseEntity<>(service.getPage(pageNum, pageSize), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(service.count(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<T> get(K key) {
        return new ResponseEntity<>(service.getById(key), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<T> create(T dto) {

        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<T> update(K key, T dto) {

        return new ResponseEntity<>(service.update(key, dto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<T> delete(K key) {

        return new ResponseEntity<>(service.delete(key), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteAll() {
        return new ResponseEntity<>(service.deleteAll(), HttpStatus.OK);
    }
}
