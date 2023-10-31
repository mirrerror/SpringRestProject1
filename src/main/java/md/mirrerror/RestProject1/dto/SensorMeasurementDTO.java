package md.mirrerror.RestProject1.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class SensorMeasurementDTO {

    @Min(value = -100, message = "Value shouldn't be less than -100")
    @Max(value = 100, message = "Value shouldn't be greater than 100")
    @NotNull(message = "Value shouldn't be null")
    private Double value;

    @NotNull(message = "Raining value shouldn't be null")
    private Boolean raining;

    @NotNull(message = "Sensor shouldn't be null")
    private SensorDTO sensor;

    public SensorMeasurementDTO(double value, boolean raining, SensorDTO sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public SensorMeasurementDTO() {}

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean isRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

}
