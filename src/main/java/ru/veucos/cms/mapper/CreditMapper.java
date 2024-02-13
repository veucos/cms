package ru.veucos.cms.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import ru.veucos.cms.dto.CreditDto;
import ru.veucos.cms.entity.Credit;
import ru.veucos.cms.mapper.base.BaseMapper;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface CreditMapper extends BaseMapper<Credit, CreditDto> {
}
