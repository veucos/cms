package ru.veucos.cms.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.veucos.cms.dto.CreditDto;
import ru.veucos.cms.dto.OfferDto;
import ru.veucos.cms.entity.Credit;
import ru.veucos.cms.entity.Offer;
import ru.veucos.cms.entity.User;
import ru.veucos.cms.security.AuthenticationFacade;
import ru.veucos.cms.service.authentication.AuthenticationService;

import java.util.List;

@RequiredArgsConstructor
@Service("creditService")
public class CreditServiceImpl extends BaseServiceImpl<Credit, CreditDto, Long> {
    private final AuthenticationFacade authenticationFacade;
    private final UserServiceImpl userService;
    @Override
    public List<CreditDto> getAll() {
        return super.getAll();
    }

    @Override
    public List<CreditDto> getPage(Integer pageNum, Integer pageSize) {
        return super.getPage(pageNum, pageSize);
    }

    @Override
    public CreditDto create(CreditDto dto) {
        Credit entity = mapper.fromDto(dto);
        UserServiceImpl userService;
        User user = userService.getByEmail(authenticationFacade.getAuthentication().getName()
        return mapper.toDto(repository.save(entity));
    }
}
