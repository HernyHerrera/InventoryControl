package com.hherrera.inventory.services.user;

import com.hherrera.inventory.model.User;
import com.hherrera.inventory.response.user.UserResponseRest;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    public ResponseEntity<UserResponseRest> search();
    public ResponseEntity<UserResponseRest> searchById(Long id);
    public ResponseEntity<UserResponseRest> save(User user);
    public ResponseEntity<UserResponseRest> deleteById(Long id);
}
