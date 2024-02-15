package com.e_commerce.mapper;

import com.e_commerce.dto.UserDataDto;
import com.e_commerce.model.UserData;

public interface UserMapper {

    UserDataDto toUserDto (UserData userData);

}
