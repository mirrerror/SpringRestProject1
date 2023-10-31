package md.mirrerror.RestProject1.repositories;

import md.mirrerror.RestProject1.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorsRepository extends JpaRepository<Sensor, Integer> {
    Sensor findByName(String name);
}
