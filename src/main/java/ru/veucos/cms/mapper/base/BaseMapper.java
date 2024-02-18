package ru.veucos.cms.mapper.base;

import org.mapstruct.MappingTarget;

/**
 * Основной мэпппер
 *
 * @param <O> - entity
 * @param <T> - DTO
 */
public interface BaseMapper<O, T> {
    /**
     * @param source источник entity
     * @return DTO
     */
    T toDto(O source);

    /**
     * @param source источник DTO
     * @return entity
     */
    O fromDto(T source);

    /**
     * @param source источник DTO
     * @param target цель entity
     */
    void update(T source, @MappingTarget O target);
}
