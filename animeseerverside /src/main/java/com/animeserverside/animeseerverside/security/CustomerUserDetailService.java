package com.animeserverside.animeseerverside.security;

import com.animeserverside.animeseerverside.entity.User;
import com.animeserverside.animeseerverside.exception.ResourceNotFoundException;
import com.animeserverside.animeseerverside.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User user =this.userRepository.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("user","email :"+username,0));
        return user;
    }
}
