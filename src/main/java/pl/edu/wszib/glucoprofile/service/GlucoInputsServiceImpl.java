package pl.edu.wszib.glucoprofile.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.glucoprofile.dao.GlucoReadingRepository;
import pl.edu.wszib.glucoprofile.dto.BmiStatsDto;
import pl.edu.wszib.glucoprofile.model.GlucoReading;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GlucoInputsServiceImpl implements GlucoInputsService {

    private final GlucoReadingRepository glucoReadingRepository;

    @Autowired
    public GlucoInputsServiceImpl(GlucoReadingRepository glucoReadingRepository) {
        this.glucoReadingRepository = glucoReadingRepository;
    }
    @Override
    public List<GlucoReading> getAllRecords() {
        return glucoReadingRepository.findAll();
    }

    @Override
    public void saveRecord(GlucoReading glucoReading) {
        glucoReadingRepository.save(glucoReading);
    }
    @Override
    public List<GlucoReading> getRecordsByNameAndBmi(String name, String bmi) {
        return glucoReadingRepository.findByNameAndBmi(name, bmi);
    }
    @Override
    public List<String> getAllNames() {
        return glucoReadingRepository.findAll().stream()
                .map(GlucoReading::getName)
                .distinct()
                .collect(Collectors.toList());
    }
    @Override
    public List<String> getAllBmis() {
        return glucoReadingRepository.findAll().stream()
                .map(GlucoReading::getBmi)
                .distinct()
                .collect(Collectors.toList());
    }
    @Override
    public Map<String, Double> getAverageReadingByBmi(String name) {
        List<GlucoReading> records = glucoReadingRepository.findByName(name);
        return records.stream()
                .collect(Collectors.groupingBy(
                        GlucoReading::getBmi,
                        Collectors.averagingDouble(GlucoReading::getReading)
                ));
    }
    @Override
    public Double getAverageReadingByBmiCategory(String bmiCategory) {

        return glucoReadingRepository.findAverageReadingByBmiCategory(bmiCategory);
    }
    @Override
    public List<BmiStatsDto> getAverageReadingByBmi() {
        List<BmiStatsDto> stats = glucoReadingRepository.findAverageReadingForAllBmiCategories();
        System.out.println("Average Reading by BMI: " + stats);
        return stats;
    }
    @Override
    public List<BmiStatsDto> getAverageReadingForAllNames() {
        return glucoReadingRepository.findAverageReadingForAllNames();
    }
}

