
package pl.edu.wszib.glucoprofile.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.glucoprofile.dto.BmiStatsDto;
import pl.edu.wszib.glucoprofile.model.GlucoReading;

import java.util.List;

@Repository
public interface GlucoReadingRepository extends JpaRepository<GlucoReading, Long> {

    List<GlucoReading> findByNameAndBmi(String name, String bmi);

    List<GlucoReading> findByName(String name);

    @Query("SELECT AVG(g.reading) FROM GlucoReading g WHERE g.bmi = ?1")
    Double findAverageReadingByBmiCategory(String bmiCategory);

    @Query("SELECT new pl.edu.wszib.glucoprofile.dto.BmiStatsDto(g.bmi, AVG(g.reading)) FROM GlucoReading g GROUP BY g.bmi")
    List<BmiStatsDto> findAverageReadingForAllBmiCategories();

    @Query("SELECT AVG(gr.reading) FROM GlucoReading gr GROUP BY gr.name")
    List<BmiStatsDto> findAverageReadingForAllNames();

    List<Double> findAverageReadingByName(String name);
}

