package ru.veucos.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.veucos.cms.exception.NotFoundException;
import ru.veucos.cms.mapper.base.BaseMapper;
import ru.veucos.cms.service.BaseService;

import java.util.List;
import java.util.stream.Collectors;

@Service
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
    protected void setMapper(BaseMapper<O, T> mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<T> getAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<T> getPage(Integer pageNum, Integer pageSize) {
        return repository.findAll(PageRequest.of(pageNum, pageSize)).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Long count() {
        return repository.count();
    }

    @Override
    public T getById(K key) {
        return mapper.toDto(getModelById(key));
    }

    @Override
    public O getModelById(K key) {
        return repository.findById(key).orElseThrow(() -> new NotFoundException("Данные по ключу " + key + " не найдены"));
    }

    @Override
    public T create(T dto) {
        return mapper.toDto(repository.save(mapper.fromDto(dto)));
    }

    @Override
    public T update(K key, T dto) {
        O entity = repository.getById(key);
        mapper.update(dto, entity);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public T delete(K key) {
        O entity = repository.getById(key);
        repository.delete(entity);
        return mapper.toDto(entity);
    }

    @Override
    public Void deleteAll() {
        repository.deleteAll();
        return null;
    }
}
