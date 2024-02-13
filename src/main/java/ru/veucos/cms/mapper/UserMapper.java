package ru.veucos.cms.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import ru.veucos.cms.dto.UserDto;
import ru.veucos.cms.entity.User;
import ru.veucos.cms.mapper.base.BaseMapper;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDto> {
}
