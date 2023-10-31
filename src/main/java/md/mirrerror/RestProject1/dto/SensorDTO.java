package md.mirrerror.RestProject1.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SensorDTO {

    @NotEmpty(message = "Sensor name should not be empty")
    @Size(min = 3, max = 30, message = "Sensor name should contain between 3 and 30 characters")
    private String name;

    public SensorDTO(String name) {
        this.name = name;
    }

    public SensorDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
