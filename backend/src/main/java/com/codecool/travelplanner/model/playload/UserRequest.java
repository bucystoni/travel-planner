package com.codecool.travelplanner.model.playload;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String email;
    private String password;
}
