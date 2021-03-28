package blue.storm.crud.controller;

import blue.storm.crud.model.UserEntity;
import blue.storm.crud.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/crud")
public class UserController {

    private UserRepository userRepository;
    UserController(UserRepository repoInterface)
    {
        this.userRepository = repoInterface;
    }

    @PostMapping(path = "/add")
    public UserEntity create(@RequestBody UserEntity entity){
        return userRepository.save(entity);
    }

    @GetMapping(path = "/all")
    public List findAll(){
        return userRepository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return userRepository.findById(id)
                .map(record->ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping(value = "{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody UserEntity entity){
        return userRepository.findById(id)
                .map(record->{
                    record.setFirst_name(entity.getFirst_name());
                    record.setLast_name(entity.getLast_name());
                    record.setEmail(entity.getEmail());
                    UserEntity updated=userRepository.save(record);
                    return  ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id){
        return userRepository.findById(id)
                .map(record->{
                    userRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
