package com.animeserverside.animeseerverside.service;

import com.animeserverside.animeseerverside.config.AppConstants;
import com.animeserverside.animeseerverside.entity.Role;
import com.animeserverside.animeseerverside.entity.User;
import com.animeserverside.animeseerverside.exception.ResourceNotFoundException;
import com.animeserverside.animeseerverside.payload.UserDto;
import com.animeserverside.animeseerverside.repository.RoleRepository;
import com.animeserverside.animeseerverside.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerNewUser(UserDto userDto) {

        User user = this.modelMapper.map(userDto, User.class);

        // encoded the password
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));

        // roles
//        Role roles = this.roleRepository.findById(502).get();
//
//        user.getRoles().add(roles);

        User newUser = this.userRepository.save(user);

        return this.modelMapper.map(newUser, UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {

    User user =this.dtoToUser(userDto);
    User savedUser  =  this.userRepository.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user =this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());
        user.setPassword(userDto.getPassword());
        user.setImg(userDto.getImg());
        User updatedUser= this.userRepository.save(user);
        UserDto userDto1= this.userToDto(updatedUser);
        return  userDto1;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
    List<User> users= this.userRepository.findAll();
    List<UserDto> userDtos=users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer userId) {
    User user =this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
    this.userRepository.delete(user);
    }

    private  User dtoToUser(UserDto userDto){
        User user =this.modelMapper.map(userDto,User.class);
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setRole(userDto.getRole());
//        user.setImg(userDto.getImg());
//        user.setPassword(userDto.getPassword());
        return user;
    }

    private UserDto userToDto(User user){
        UserDto userDto =this.modelMapper.map(user,UserDto.class);
//        userDto.setId(user.getId());
//        userDto.setEmail(user.getEmail());
//        userDto.setRole(user.getRole());
//        userDto.setName(user.getName());
//        userDto.setPassword(user.getPassword());
//        userDto.setImg(user.getImg());
        return userDto;
    }
//    private  User dtoToUser(UserDto userDto){
//        User user =new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setRole(userDto.getRole());
//        user.setImg(userDto.getImg());
//        user.setPassword(userDto.getPassword());
//        return user;
//    }
//
//    private UserDto userToDto(User user){
//        UserDto userDto =new UserDto();
//        userDto.setId(user.getId());
//        userDto.setEmail(user.getEmail());
//        userDto.setRole(user.getRole());
//        userDto.setName(user.getName());
//        userDto.setPassword(user.getPassword());
//        userDto.setImg(user.getImg());
//        return userDto;
//    }
}
