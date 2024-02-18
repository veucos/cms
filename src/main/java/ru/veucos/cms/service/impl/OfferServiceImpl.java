package ru.veucos.cms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.veucos.cms.dto.OfferDto;
import ru.veucos.cms.entity.Offer;

/**
 * Сервис кредитных предложений
 */
@RequiredArgsConstructor
@Service("offerService")
public class OfferServiceImpl extends BaseServiceImpl<Offer, OfferDto, Long> {

}
