package md.mirrerror.RestProject1.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.Cascade;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sensor")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Sensor name should not be empty")
    @Size(min = 3, max = 30, message = "Sensor name should contain between 3 and 30 characters")
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "sensor")
    private List<SensorMeasurement> measurements;

    public Sensor(String name) {
        this.name = name;
        this.measurements = new ArrayList<>();
    }

    public Sensor() {
        this.measurements = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SensorMeasurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<SensorMeasurement> measurements) {
        this.measurements = measurements;
    }

}
