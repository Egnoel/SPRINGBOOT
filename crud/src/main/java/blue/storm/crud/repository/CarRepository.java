package blue.storm.crud.repository;

import blue.storm.crud.model.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    public List<CarEntity> findByUserEntityId(Long id);
}
