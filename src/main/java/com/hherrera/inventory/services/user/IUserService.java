package com.hherrera.inventory.services.user;

import com.hherrera.inventory.model.User;
import com.hherrera.inventory.response.user.UserResponseRest;
import org.springframework.http.ResponseEntity;

public interface IUserService {

    /** search all users **/
    public ResponseEntity<UserResponseRest> search();
    /** get user by id **/
    public ResponseEntity<UserResponseRest> searchById(Long id);
    /** save user **/
    public ResponseEntity<UserResponseRest> save(User user);
    /** delete user by id **/
    public ResponseEntity<UserResponseRest> deleteById(Long id);
}
