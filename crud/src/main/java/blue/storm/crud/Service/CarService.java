package blue.storm.crud.Service;

import blue.storm.crud.model.CarEntity;
import blue.storm.crud.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public CarEntity addCar(CarEntity entity){

        return carRepository.save(entity);
    }

    public List listAllCar(Long id){
        return carRepository.findByUserEntityId(id);

    }

    public ResponseEntity listCarById(Long id){
        return carRepository.findById(id)
                .map(record->ResponseEntity.ok().body(record)
                ).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity updateCarById(Long id,CarEntity entity){
        return carRepository.findById(id)
                .map(record ->{
                    record.setModel(entity.getModel());
                    record.setColor(entity.getColor());
                    CarEntity update = carRepository.save(record);
                    return ResponseEntity.ok().body(update);
                        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity deleteCar(Long id){
        return carRepository.findById(id)
                .map(record->{
                    carRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }


}
