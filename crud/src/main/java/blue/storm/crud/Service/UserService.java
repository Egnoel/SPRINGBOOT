package blue.storm.crud.Service;

import blue.storm.crud.model.UserEntity;
import blue.storm.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserService {

    @Autowired
   private UserRepository userRepository;



   public UserEntity add(UserEntity entity){

       return userRepository.save(entity);
   }

   public List listAll(){

       return userRepository.findAll();
   }

    public ResponseEntity lisById( long id){
        return userRepository.findById(id)
                .map(record->ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity updateById(long id, UserEntity entity){
        return userRepository.findById(id)
                .map(record->{
                    record.setFirst_name(entity.getFirst_name());
                    record.setLast_name(entity.getLast_name());
                    record.setEmail(entity.getEmail());
                    UserEntity updated=userRepository.save(record);
                    return  ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> deleteById(long id){
        return userRepository.findById(id)
                .map(record->{
                    userRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
