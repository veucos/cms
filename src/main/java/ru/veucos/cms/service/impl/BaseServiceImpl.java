package ru.veucos.cms.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.veucos.cms.exception.NotFoundException;
import ru.veucos.cms.mapper.base.BaseMapper;
import ru.veucos.cms.service.BaseService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Основной сервис
 *
 * @param <O> entity
 * @param <T> dto
 * @param <K> key
 */
@Service
@Log4j2
public abstract class BaseServiceImpl<O, T, K> implements BaseService<O, T, K> {
    protected JpaRepository<O, K> repository;
    protected BaseMapper<O, T> mapper;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public void setRepository(JpaRepository<O, K> repository) {
        this.repository = repository;
    }

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public void setMapper(BaseMapper<O, T> mapper) {
        this.mapper = mapper;
    }

    /**
     * Выбрать всё
     *
     * @return
     */
    @Override
    public List<T> getAll() {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    /**
     * Выбрать всё (пагинация)
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public List<T> getPage(Integer pageNum, Integer pageSize) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        return repository.findAll(PageRequest.of(pageNum, pageSize)).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    /**
     * Количество записей
     *
     * @return
     */
    @Override
    public Long count() {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        return repository.count();
    }

    /**
     * Выбор DTO по ключу
     *
     * @param key
     * @return
     */
    @Override
    public T getById(K key) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        return mapper.toDto(getModelById(key));
    }

    /**
     * Выбор entity по ключу
     *
     * @param key
     * @return
     */
    @Override
    public O getModelById(K key) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        return repository.findById(key).orElseThrow(() -> new NotFoundException("Данные по ключу " + key + " не найдены"));
    }

    /**
     * \
     * Создание
     *
     * @param dto
     * @return
     */
    @Override
    public T create(T dto) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        return mapper.toDto(repository.save(mapper.fromDto(dto)));
    }

    /**
     * Обновление по ключу
     *
     * @param key
     * @param dto
     * @return
     */
    @Override
    public T update(K key, T dto) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        O entity = repository.getById(key);
        mapper.update(dto, entity);
        return mapper.toDto(repository.save(entity));
    }

    /**
     * Удаление по ключу
     *
     * @param key
     * @return
     */
    @Override
    public T delete(K key) {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        O entity = repository.getById(key);
        repository.delete(entity);
        return mapper.toDto(entity);
    }

    /**
     * Полная очистка (удаление всех записей)
     *
     * @return
     */
    @Override
    public Void deleteAll() {
        log.info(String.format("Start class %s method %s", this.getClass().getSimpleName(), new Throwable().getStackTrace()[0].getMethodName()));
        repository.deleteAll();
        return null;
    }
}
