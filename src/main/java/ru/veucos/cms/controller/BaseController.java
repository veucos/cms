package ru.veucos.cms.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BaseController<T, K> {
    @GetMapping("/all")
    @Operation(summary = "Получить всё")
    ResponseEntity<List<T>> getAll();

    @GetMapping("/page")
    @Operation(summary = "Получить всё (пагинация)")
    ResponseEntity<List<T>> getPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize);

    @GetMapping("/count")
    @Operation(summary = "Количество записей")
    ResponseEntity<Long> count();

    @GetMapping
    @Operation(summary = "Получить по ключу")
    ResponseEntity<T> get(K key);

    @PostMapping
    @Operation(summary = "Создать")
    ResponseEntity<T> create(@RequestBody T createDto);

    @PutMapping
    @Operation(summary = "Изменить")
    ResponseEntity<T> update(K key, @RequestBody T createDto);

    @DeleteMapping
    @Operation(summary = "Удалить по ключу")
    ResponseEntity<T> delete(K key);

    @DeleteMapping("/all")
    @Operation(summary = "Удалить всё")
    ResponseEntity<Void> deleteAll();
}
