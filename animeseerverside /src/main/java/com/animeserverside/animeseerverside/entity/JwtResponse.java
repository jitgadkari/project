package com.animeserverside.animeseerverside.entity;

import com.animeserverside.animeseerverside.payload.UserDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class JwtResponse {
    private String jwtToken;
    private String username;
    private UserDto user;
}
