

package pl.edu.wszib.glucoprofile.dto;

public class BmiStatsDto {
    private String bmi;

    public void setAverageReading(Double averageReading) {
        this.averageReading = averageReading;
    }


    private Double averageReading;

    public BmiStatsDto(String bmi,  Double averageReading) {
        this.bmi = bmi;
        this.averageReading = averageReading;

    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public Double getAverageReading() {
        return averageReading;
    }


}

