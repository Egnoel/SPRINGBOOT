package blue.storm.crud.controller;

import blue.storm.crud.Service.UserService;
import blue.storm.crud.model.UserEntity;
import blue.storm.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/crud")
public class UserController {


    private UserRepository userRepository;

    UserController(UserService service){
        this.userService = service;
    }
    @Autowired
    private UserService userService;

    @PostMapping(path = "/add")
    public UserEntity create(@RequestBody UserEntity entity){
        return userService.add(entity);
    }

    @GetMapping(path = "/all")
    public List findAll(){
        return userService.listAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return userService.lisById(id);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody UserEntity entity){
        return userService.updateById(id, entity);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id){
        return userService.deleteById(id);
    }
}
