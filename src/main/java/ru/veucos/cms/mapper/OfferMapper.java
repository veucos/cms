package ru.veucos.cms.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import ru.veucos.cms.dto.OfferDto;
import ru.veucos.cms.entity.Offer;
import ru.veucos.cms.mapper.base.BaseMapper;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface OfferMapper extends BaseMapper<Offer, OfferDto> {
}
