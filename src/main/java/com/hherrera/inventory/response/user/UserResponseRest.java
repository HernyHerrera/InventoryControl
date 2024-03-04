package com.hherrera.inventory.response.user;

import com.hherrera.inventory.response.ResponseRest;
import lombok.Data;

@Data
public class UserResponseRest extends ResponseRest {
    private UserResponse userResponse = new UserResponse();

}
