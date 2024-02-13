package ru.veucos.cms.service;

import java.util.List;

public interface BaseService<O, T, K> {
    List<T> getAll();

    List<T> getPage(Integer pageNum, Integer pageSize);

    Long count();

    T getById(K key);

    O getModelById(K key);

    T create(T dto);

    T update(K key, T dto);

    T delete(K key);

    Void deleteAll();

}
