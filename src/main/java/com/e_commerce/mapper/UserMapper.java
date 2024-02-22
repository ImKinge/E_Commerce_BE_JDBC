package com.e_commerce.mapper;

import com.e_commerce.dto.UserDto;
import com.e_commerce.model.UserData;

public interface UserMapper {

    UserDto toUserDto (UserData userData);

}
