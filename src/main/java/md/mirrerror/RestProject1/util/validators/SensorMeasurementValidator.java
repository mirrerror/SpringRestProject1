package md.mirrerror.RestProject1.util.validators;

import md.mirrerror.RestProject1.dto.SensorMeasurementDTO;
import md.mirrerror.RestProject1.models.SensorMeasurement;
import md.mirrerror.RestProject1.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorMeasurementValidator implements Validator {

    private final SensorsService sensorsService;

    @Autowired
    public SensorMeasurementValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(SensorMeasurement.class) || clazz.equals(SensorMeasurementDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(target instanceof SensorMeasurement sensorMeasurement) {
            if(sensorsService.findByName(sensorMeasurement.getSensor().getName()) == null)
                errors.rejectValue("name", "", "Sensor with the specified name doesn't exist");
        }
        if(sensorsService.findByName(((SensorMeasurementDTO) target).getSensor().getName()) == null)
            errors.rejectValue("name", "", "Sensor with the specified name doesn't exist");
    }
}
