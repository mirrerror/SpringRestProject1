package md.mirrerror.RestProject1.services;

import md.mirrerror.RestProject1.models.SensorMeasurement;
import md.mirrerror.RestProject1.repositories.SensorMeasurementsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class SensorMeasurementsService {

    private final SensorMeasurementsRepository sensorMeasurementsRepository;

    @Autowired
    public SensorMeasurementsService(SensorMeasurementsRepository sensorMeasurementsRepository) {
        this.sensorMeasurementsRepository = sensorMeasurementsRepository;
    }

    public List<SensorMeasurement> findAll() {
        return sensorMeasurementsRepository.findAll();
    }

    @Transactional
    public void save(SensorMeasurement sensorMeasurement) {
        enrichSensorMeasurement(sensorMeasurement);
        sensorMeasurementsRepository.save(sensorMeasurement);
    }

    private void enrichSensorMeasurement(SensorMeasurement sensorMeasurement) {
        sensorMeasurement.setCreatedAt(LocalDateTime.now());
    }

}
