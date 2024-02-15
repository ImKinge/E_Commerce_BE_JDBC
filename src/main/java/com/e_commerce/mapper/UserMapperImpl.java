package com.e_commerce.mapper;

import com.e_commerce.dto.UserDataDto;
import com.e_commerce.model.UserData;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper{

    @Override
    public UserDataDto toUserDto(UserData userData) {

        if(userData == null) {
            return null;
        }

        UserDataDto userDto = new UserDataDto();

        userDto.setFiscalCode(userData.getFiscalCode());
        userDto.setAddress(userData.getAddress());
        userDto.setName(userData.getName());
        userDto.setSurname(userData.getSurname());
        userDto.setEmail(userData.getEmail());
        userDto.setUsername(userData.getUsername());
        userDto.setPassword(userData.getPassword());
        userDto.setInfoPhone(userData.getInfoPhone());
        userDto.setDateOfBirth(userData.getDateOfBirth());

        return userDto;
    }
}
