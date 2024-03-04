package com.hherrera.inventory.response.user;

import com.hherrera.inventory.model.User;
import lombok.Data;

import java.util.List;
@Data
public class UserResponse {
    private List<User> user;
}
