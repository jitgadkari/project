package com.animeserverside.animeseerverside.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Data
@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String name;
    private String email;
    private  String password;
    private  String  role;
    private  String img;

//    private Set<RoleDto> roles = new HashSet<>();
//    @JsonIgnore
//    public String getPassword() {
//        return this.password;
//    }

    @JsonProperty
    public void setPassword(String password) {
        this.password=password;
    }
}
