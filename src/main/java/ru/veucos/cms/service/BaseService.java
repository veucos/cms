package ru.veucos.cms.service;

import java.util.List;

/**
 * Основной сервис (интерфейс)
 *
 * @param <O> entity
 * @param <T> dto
 * @param <K> key
 */
public interface BaseService<O, T, K> {
    /**
     * Выбрать всё
     *
     * @return
     */
    List<T> getAll();

    /**
     * Выбрать всё (пагинация)
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<T> getPage(Integer pageNum, Integer pageSize);

    /**
     * Количество записей
     *
     * @return
     */
    Long count();

    /**
     * Выбор DTO по ключу
     *
     * @param key
     * @return
     */
    T getById(K key);

    /**
     * Выбор entity по ключу
     *
     * @param key
     * @return
     */
    O getModelById(K key);

    /**
     * Создание
     *
     * @param dto
     * @return
     */
    T create(T dto);

    /**
     * Обновление по ключу
     *
     * @param key
     * @param dto
     * @return
     */
    T update(K key, T dto);

    /**
     * Удаление по ключу
     *
     * @param key
     * @return
     */
    T delete(K key);

    /**
     * Полная очистка (удаление всех записей)
     *
     * @return
     */
    Void deleteAll();

}
