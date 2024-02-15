package ru.veucos.cms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.veucos.cms.dto.BankDto;
import ru.veucos.cms.dto.OfferDto;
import ru.veucos.cms.entity.Bank;
import ru.veucos.cms.entity.Offer;

@RequiredArgsConstructor
@Service("offerService")
public class OfferServiceImpl extends BaseServiceImpl<Offer, OfferDto, Long> {

}
