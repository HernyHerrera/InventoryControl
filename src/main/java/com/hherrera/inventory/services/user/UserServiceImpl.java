package com.hherrera.inventory.services.user;

import com.hherrera.inventory.dao.IUserDao;
import com.hherrera.inventory.model.User;
import com.hherrera.inventory.response.user.UserResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private IUserDao userDao;

    /** search all users **/
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<UserResponseRest> search() {
        UserResponseRest response = new UserResponseRest();
        try{
            List<User> user = (List<User>) userDao.findAll();
            response.getUserResponse().setUser(user);
            response.setMetadata("Respuesta OK", "00", "Consulta exitosa");

        }catch (Exception exception){
            response.setMetadata("Respuesta Error", "-1", "Error en consulta");
            exception.getStackTrace();
            return new ResponseEntity<UserResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<UserResponseRest>(response, HttpStatus.OK);
    }

    /** get user by id **/
    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<UserResponseRest> searchById(Long id) {
        UserResponseRest response = new UserResponseRest();
        List<User> list = new ArrayList<>();
        try{
            Optional<User> category = userDao.findById(id);
            if (category.isPresent()){
                list.add(category.get());
                response.setMetadata("Respuesta OK", "00", "Usuario encontrado");
                response.getUserResponse().setUser(list);
            }else{
                response.setMetadata("Respuesta Error", "-1", "Error en consulta por Id");
                return new ResponseEntity<UserResponseRest>(response, HttpStatus.NOT_FOUND);

            }
        }catch (Exception exception){
            response.setMetadata("Respuesta Error", "-1", "Error en consulta");
            exception.getStackTrace();
            return new ResponseEntity<UserResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<UserResponseRest>(response, HttpStatus.OK);
    }

    /** save user **/
    @Override
    @Transactional
    public ResponseEntity<UserResponseRest> save(User user) {
        UserResponseRest response = new UserResponseRest();
        List<User> list = new ArrayList<>();
        try{
            User userSaved = userDao.save(user);
            if(userSaved != null){
                list.add(userSaved);
                response.getUserResponse().setUser(list);
                response.setMetadata("Respuesta OK", "00", "Usuario guardado");
            }else {
                response.setMetadata("Respuesta Error", "-1", "Usuario no guardado");
                return new ResponseEntity<UserResponseRest>(response, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception exception){
            response.setMetadata("Respuesta Error", "-1", "Error al grabar usuario");
            exception.getStackTrace();
            return new ResponseEntity<UserResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<UserResponseRest>(response, HttpStatus.OK);
    }

    /** delete user by id **/
    @Override
    @Transactional
    public ResponseEntity<UserResponseRest> deleteById(Long id) {
        UserResponseRest response = new UserResponseRest();
        try{
            userDao.deleteById(id);
            response.setMetadata("respuesta ok","00","Usuario id:" + id + " eliminado");

        }catch (Exception exception){
            response.setMetadata("Respuesta Error", "-1", "No se encontro usuario con ese id");
            exception.getStackTrace();
            return new ResponseEntity<UserResponseRest>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserResponseRest>(response, HttpStatus.OK);
    }
}
