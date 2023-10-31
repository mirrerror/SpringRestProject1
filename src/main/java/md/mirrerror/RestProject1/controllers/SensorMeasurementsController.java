package md.mirrerror.RestProject1.controllers;

import jakarta.validation.Valid;
import md.mirrerror.RestProject1.dto.SensorMeasurementDTO;
import md.mirrerror.RestProject1.models.SensorMeasurement;
import md.mirrerror.RestProject1.services.SensorMeasurementsService;
import md.mirrerror.RestProject1.util.errorResponses.ErrorResponse;
import md.mirrerror.RestProject1.util.exceptions.SensorMeasurementNotCreatedException;
import md.mirrerror.RestProject1.util.validators.SensorMeasurementValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class SensorMeasurementsController {

    private final SensorMeasurementsService sensorMeasurementsService;
    private final ModelMapper modelMapper;
    private final SensorMeasurementValidator sensorMeasurementValidator;

    @Autowired
    public SensorMeasurementsController(SensorMeasurementsService sensorMeasurementsService, ModelMapper modelMapper, SensorMeasurementValidator sensorMeasurementValidator) {
        this.sensorMeasurementsService = sensorMeasurementsService;
        this.modelMapper = modelMapper;
        this.sensorMeasurementValidator = sensorMeasurementValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> createNewSensorMeasurement(@RequestBody @Valid SensorMeasurementDTO sensorMeasurementDTO,
                                                      BindingResult bindingResult) {
        sensorMeasurementValidator.validate(sensorMeasurementDTO, bindingResult);

        if(bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError fieldError : errors)
                errorMessage.append(fieldError.getField())
                        .append(" - ")
                        .append(fieldError.getDefaultMessage())
                        .append(";");

            throw new SensorMeasurementNotCreatedException(errorMessage.toString());
        }

        sensorMeasurementsService.save(convertToSensorMeasurement(sensorMeasurementDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public List<SensorMeasurementDTO> getAllMeasurements() {
        return sensorMeasurementsService.findAll()
                .stream().map(this::convertToSensorMeasurementDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public long getRainyDaysCount() {
        return sensorMeasurementsService.findAll()
                .stream().filter(SensorMeasurement::isRaining)
                .count();
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorMeasurementNotCreatedException e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private SensorMeasurement convertToSensorMeasurement(SensorMeasurementDTO sensorMeasurementDTO) {
        return modelMapper.map(sensorMeasurementDTO, SensorMeasurement.class);
    }

    private SensorMeasurementDTO convertToSensorMeasurementDTO(SensorMeasurement sensorMeasurement) {
        return modelMapper.map(sensorMeasurement, SensorMeasurementDTO.class);
    }

}
