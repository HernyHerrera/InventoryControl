package com.hherrera.inventory.controller;

import com.hherrera.inventory.model.User;
import com.hherrera.inventory.response.user.UserResponseRest;
import com.hherrera.inventory.services.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/inventory")
public class UserRestController {
    @Autowired
    private IUserService service;

    /**
     * Get all user
     * @return response
     * */
    @GetMapping("/users")
    public ResponseEntity<UserResponseRest> serchUsers(){
        ResponseEntity<UserResponseRest> response = service.search();
        return response;
    }
    /**
     * Get user by id
     * @param id
     * @return response
     * */
    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseRest> serchUsersById(@PathVariable Long id){
        ResponseEntity<UserResponseRest> response = service.searchById(id);
        return response;
    }
    /**
     * Save user
     * @param user
     * @return response
     * */
    @PostMapping("/users")
    public ResponseEntity<UserResponseRest> save(@RequestBody User user){
        ResponseEntity<UserResponseRest> response = service.save(user);
        return response;
    }
    /**
     * Delete user by id
     * @param id
     * @return response
     * */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserResponseRest> delete(@PathVariable Long id){
        ResponseEntity<UserResponseRest> response = service.deleteById(id);
        return response;
    }


}
