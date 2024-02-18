package ru.veucos.cms.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Общий контроллер (интерфейс)
 */
public interface BaseController<T, K> {
    /**
     * Получить всё
     *
     * @return
     */
    @GetMapping("/all")
    @Operation(summary = "Получить всё")
    ResponseEntity<List<T>> getAll();

    /**
     * Получить всё (пагинация)
     */
    @GetMapping("/page")
    @Operation(summary = "Получить всё (пагинация)")
    ResponseEntity<List<T>> getPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize);

    /**
     * Количество записей
     *
     * @return
     */
    @GetMapping("/count")
    @Operation(summary = "Количество записей")
    ResponseEntity<Long> count();

    /**
     * Получить по ключу
     *
     * @param key ключ
     * @return
     */
    @GetMapping
    @Operation(summary = "Получить по ключу")
    ResponseEntity<T> get(K key);

    /**
     * Создать
     *
     * @param createDto данные новой сущности
     * @return
     */
    @PostMapping
    @Operation(summary = "Создать")
    ResponseEntity<T> create(@RequestBody T createDto);

    /**
     * Изменить
     *
     * @param key       -ключ
     * @param createDto данные измененной сущности
     * @return
     */
    @PutMapping
    @Operation(summary = "Изменить")
    ResponseEntity<T> update(K key, @RequestBody T createDto);

    /**
     * Удалить по ключу
     *
     * @param key ключ
     * @return
     */
    @DeleteMapping
    @Operation(summary = "Удалить по ключу")
    ResponseEntity<T> delete(K key);

    /**
     * Удалить всё
     *
     * @return
     */
    @DeleteMapping("/all")
    @Operation(summary = "Удалить всё")
    ResponseEntity<Void> deleteAll();
}
