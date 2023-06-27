package com.animeserverside.animeseerverside.service;

import com.animeserverside.animeseerverside.entity.User;
import com.animeserverside.animeseerverside.payload.UserDto;

import java.util.List;

public interface UserService {
    UserDto registerNewUser(UserDto user);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Integer userId );
    UserDto getUserById(Integer userId );
    List<UserDto> getAllUser();
    void deleteUser(Integer userId);
}
