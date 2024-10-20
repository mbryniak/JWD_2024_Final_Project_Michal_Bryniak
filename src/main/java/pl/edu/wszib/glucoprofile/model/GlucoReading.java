package pl.edu.wszib.glucoprofile.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "GlucoReadings", schema = "glycemia")
public class GlucoReading {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private Double reading;
    private String bmi;


    private LocalDateTime dateTime;

    public GlucoReading() {}

    public GlucoReading(String name, Double reading, String bmi, LocalDateTime dateTime) {
        this.name = name;
        this.reading = reading;
        this.bmi = bmi;
        this.dateTime = dateTime;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getReading() {
        return reading;
    }

    public void setReading(Double reading) {
        this.reading = reading;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}

