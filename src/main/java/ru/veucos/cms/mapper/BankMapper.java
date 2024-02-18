package ru.veucos.cms.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.veucos.cms.dto.BankDto;
import ru.veucos.cms.entity.Bank;
import ru.veucos.cms.mapper.base.BaseMapper;
import ru.veucos.cms.mapper.base.JsonNullableMapper;

/**
 * Мэппер Банка
 */
@Mapper(uses = JsonNullableMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface BankMapper extends BaseMapper<Bank, BankDto> {
}
