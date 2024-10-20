package pl.edu.wszib.glucoprofile.service;

import pl.edu.wszib.glucoprofile.dto.BmiStatsDto;
import pl.edu.wszib.glucoprofile.model.GlucoReading;

import java.util.List;
import java.util.Map;

public interface GlucoInputsService {

    List<GlucoReading> getAllRecords();

    void saveRecord(GlucoReading glucoReading);

    List<GlucoReading> getRecordsByNameAndBmi(String name, String bmi);

    List<String> getAllNames();

    List<String> getAllBmis();

    Map<String, Double> getAverageReadingByBmi(String name);

    Double getAverageReadingByBmiCategory(String bmiCategory);

    List<BmiStatsDto> getAverageReadingByBmi();

    List<BmiStatsDto> getAverageReadingForAllNames();
}

