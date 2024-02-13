package ru.veucos.cms.mapper.base;

import org.mapstruct.MappingTarget;


public interface BaseMapper<O, T> {
    T toDto(O source);

    O fromDto(T source);

    void update(T source, @MappingTarget O target);
}
