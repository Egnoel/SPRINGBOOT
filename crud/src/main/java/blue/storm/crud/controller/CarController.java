package blue.storm.crud.controller;

import blue.storm.crud.Service.CarService;
import blue.storm.crud.model.CarEntity;
import blue.storm.crud.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/carCrud")
public class CarController {
    @Autowired
    private CarService carService;

    @PostMapping(path = "/users/{userId}/car")
    public CarEntity createCar(@RequestBody CarEntity carEntity, @PathVariable Long userId){
        carEntity.setUserEntity(new UserEntity(userId, "", "", ""));
        return carService.addCar(carEntity);
    }

    @GetMapping(path = "/users/{userId}/all")
    public List listCar(@PathVariable Long userId){
        return carService.listAllCar(userId);
    }

    @GetMapping(path = {"/users/{userId}/{id}"})
    public ResponseEntity listId(@PathVariable Long id){
        return carService.listCarById(id);
    }

    @PutMapping(value = "/users/{userId}/car/{id}")
    public ResponseEntity update(@PathVariable Long id, @PathVariable Long userId,@RequestBody CarEntity entity){
        entity.setUserEntity(new UserEntity(userId, "", "", ""));
        return carService.updateCarById(id, entity);
    }

    @DeleteMapping(path = {"/users/{userId}/car/{id}"})
    public ResponseEntity delete(@PathVariable Long id){
        return carService.deleteCar(id);
    }

}
