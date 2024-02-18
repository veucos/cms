package ru.veucos.cms.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.veucos.cms.dto.CreditDto;
import ru.veucos.cms.entity.Credit;
import ru.veucos.cms.mapper.base.BaseMapper;
import ru.veucos.cms.mapper.base.JsonNullableMapper;

/**
 * Мэппер Кредита
 */
@Mapper(uses = JsonNullableMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface CreditMapper extends BaseMapper<Credit, CreditDto> {
    @Override
    @Mapping(source = "offer.bank.id", target = "bankId")
    @Mapping(source = "offer.bank.name", target = "bankName")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.name", target = "userName")
    @Mapping(source = "offer.name", target = "offerName")
    @Mapping(source = "offer.lim", target = "lim")
    @Mapping(source = "offer.term", target = "term")
    @Mapping(source = "offer.rate", target = "rate")
    CreditDto toDto(Credit source);

    @Override
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "offerId", target = "offer.id")
    Credit fromDto(CreditDto source);
}
