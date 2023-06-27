package com.animeserverside.animeseerverside.payload;

import jakarta.persistence.Id;
import lombok.Data;

@Data
public class RoleDto {
    @Id
    private Integer roleId;
    private String name;
}