package ru.veucos.cms.controller.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.veucos.cms.controller.BaseController;
import ru.veucos.cms.service.BaseService;

import java.util.List;

/**
 * Общий контроллер
 */
@Log4j2
public abstract class BaseControllerImpl<O, T, K> implements BaseController<T, K> {

    protected BaseService<O, T, K> service;

    @Autowired
    public void setService(BaseService<O, T, K> service) {
        this.service = service;
    }

    /**
     * Получить всё
     *
     * @return
     */
    @Override
    public ResponseEntity<List<T>> getAll() {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    /**
     * Получить всё (пагинация)
     */
    @Override
    public ResponseEntity<List<T>> getPage(Integer pageNum, Integer pageSize) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        return new ResponseEntity<>(service.getPage(pageNum, pageSize), HttpStatus.OK);
    }

    /**
     * Количество записей
     *
     * @return
     */
    @Override
    public ResponseEntity<Long> count() {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        return new ResponseEntity<>(service.count(), HttpStatus.OK);
    }

    /**
     * Получить по ключу
     *
     * @param key ключ
     * @return
     */
    @Override
    public ResponseEntity<T> get(K key) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        return new ResponseEntity<>(service.getById(key), HttpStatus.OK);
    }

    /**
     * Создать
     *
     * @param createDto данные новой сущности
     * @return
     */
    @Override
    public ResponseEntity<T> create(T dto) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        return new ResponseEntity<>(service.create(dto), HttpStatus.OK);
    }

    /**
     * Изменить
     *
     * @param key       -ключ
     * @param createDto данные измененной сущности
     * @return
     */
    @Override
    public ResponseEntity<T> update(K key, T dto) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        return new ResponseEntity<>(service.update(key, dto), HttpStatus.OK);
    }

    /**
     * Удалить по ключу
     *
     * @param key ключ
     * @return
     */
    @Override
    public ResponseEntity<T> delete(K key) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        return new ResponseEntity<>(service.delete(key), HttpStatus.OK);
    }

    /**
     * Удалить всё
     *
     * @return
     */
    @Override
    public ResponseEntity<Void> deleteAll() {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        return new ResponseEntity<>(service.deleteAll(), HttpStatus.OK);
    }
}
