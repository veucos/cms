package ru.veucos.cms.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.veucos.cms.dto.OfferDto;
import ru.veucos.cms.entity.Offer;
import ru.veucos.cms.mapper.base.BaseMapper;
import ru.veucos.cms.mapper.base.JsonNullableMapper;

@Mapper(uses = JsonNullableMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface OfferMapper extends BaseMapper<Offer, OfferDto> {
    @Override
    @Mapping(source = "bank.id", target = "bankId")
    @Mapping(source = "bank.name", target = "bankName")
    OfferDto toDto(Offer source);

    @Override
    @Mapping(source = "bankId", target = "bank.id")
    Offer fromDto(OfferDto source);
}
