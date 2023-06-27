package com.animeserverside.animeseerverside.controller;

import com.animeserverside.animeseerverside.entity.User;
import com.animeserverside.animeseerverside.payload.UserDto;
import com.animeserverside.animeseerverside.repository.UserRepository;
import com.animeserverside.animeseerverside.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @CrossOrigin
    @PostMapping()
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
    UserDto createdUser= this.userService.createUser(userDto);
    return  new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    @CrossOrigin
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto , @PathVariable Integer userId){
       UserDto updatedUser=this.userService.updateUser(userDto,userId);
       return  new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping()
    public ResponseEntity<List<UserDto>> allUsers(){
        return new ResponseEntity<>(this.userService.getAllUser(),HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer userId){
        UserDto user=this.userService.getUserById(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @CrossOrigin
    @DeleteMapping("/{userId}")
    public void  deleteUser(@PathVariable Integer userId){
        this.userService.deleteUser(userId);
    }

}
