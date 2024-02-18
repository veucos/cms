package ru.veucos.cms.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import ru.veucos.cms.dto.UserDto;
import ru.veucos.cms.entity.User;
import ru.veucos.cms.mapper.base.BaseMapper;
import ru.veucos.cms.mapper.base.JsonNullableMapper;

/**
 * Мэппер пользователя (клиента)
 */
@Mapper(uses = JsonNullableMapper.class,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto> {
}
