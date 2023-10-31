package md.mirrerror.RestProject1.repositories;

import md.mirrerror.RestProject1.models.SensorMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorMeasurementsRepository extends JpaRepository<SensorMeasurement, Integer> {
}
