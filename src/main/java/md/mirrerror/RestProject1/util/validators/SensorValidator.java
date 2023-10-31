package md.mirrerror.RestProject1.util.validators;

import md.mirrerror.RestProject1.dto.SensorDTO;
import md.mirrerror.RestProject1.models.Sensor;
import md.mirrerror.RestProject1.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorsService sensorsService;

    @Autowired
    public SensorValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Sensor.class) || clazz.equals(SensorDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(target instanceof Sensor sensor) {
            if(sensorsService.findByName(sensor.getName()) != null)
                errors.rejectValue("name", "", "Sensor with the specified name already exists");
        }
        if(sensorsService.findByName(((SensorDTO) target).getName()) != null)
            errors.rejectValue("name", "", "Sensor with the specified name already exists");
    }
}
