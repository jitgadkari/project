package com.animeserverside.animeseerverside.controller;

import com.animeserverside.animeseerverside.entity.JwtRequest;
import com.animeserverside.animeseerverside.entity.JwtResponse;
import com.animeserverside.animeseerverside.entity.User;
import com.animeserverside.animeseerverside.payload.UserDto;
import com.animeserverside.animeseerverside.repository.UserRepository;
import com.animeserverside.animeseerverside.security.CustomerUserDetailService;
import com.animeserverside.animeseerverside.security.JwtHelper;
import com.animeserverside.animeseerverside.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")

public class AuthController {
    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtHelper helper;
    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = this.userDetailService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        response.setUser(this.modelMapper.map((User)userDetails,UserDto.class));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser( @RequestBody UserDto userDto) {
        UserDto registeredUser = this.userService.registerNewUser(userDto);
        return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            this.authenticationManager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }



    // get loggedin user data
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/current-user")
    public ResponseEntity<UserDto> getUser(Principal principal) {
        User user = this.userRepository.findByEmail(principal.getName()).get();
        return new ResponseEntity<UserDto>(this.modelMapper.map(user, UserDto.class), HttpStatus.OK);
    }
}
