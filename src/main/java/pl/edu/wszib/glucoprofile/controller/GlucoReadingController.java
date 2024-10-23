
package pl.edu.wszib.glucoprofile.controller;

import pl.edu.wszib.glucoprofile.dto.BmiStatsDto;
import pl.edu.wszib.glucoprofile.model.GlucoReading;
import pl.edu.wszib.glucoprofile.service.GlucoInputsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/records")
public class GlucoReadingController {

    private final GlucoInputsService glucoInputsService;

    @Autowired
    public GlucoReadingController(GlucoInputsService glucoInputsService) {
        this.glucoInputsService = glucoInputsService;
    }

    // Wyświetlanie wszystkich rekordów
    @GetMapping
    public String listRecords(Model model) {
        model.addAttribute("records", glucoInputsService.getAllRecords());
        return "records";
    }

    // Formularz do wprowadzenia nowego wpisu
    @GetMapping("/new")
    public String showAddRecordForm(Model model) {
        List<String> existingNames = glucoInputsService.getAllNames();
        model.addAttribute("existingNames", existingNames);
        model.addAttribute("record", new GlucoReading());
        return "add-record";
    }
    // Rejestrowanie wpisu
    @PostMapping("/add")
    public String addRecord(@ModelAttribute("record") GlucoReading record) {
        if (record.getName() != null) {
            record.setName(record.getName().trim().replaceAll(",$", ""));
        }
        record.setDateTime(LocalDateTime.now());
        glucoInputsService.saveRecord(record);
        return "redirect:/records";
    }


    @GetMapping("/chart/data")
    @ResponseBody
    public List<GlucoReading> getChartData(@RequestParam(value = "name", required = false) String name,
                                           @RequestParam(value = "bmi", required = false) String bmi) {
        if (name != null && bmi != null) {
            return glucoInputsService.getRecordsByNameAndBmi(name, bmi);
        } else {
            return glucoInputsService.getAllRecords();
        }
    }

    // Zarządzanie wyjątkami
    @ControllerAdvice
    public static class GlobalExceptionHandler {
        @ExceptionHandler(Exception.class)
        public String handleException(Exception ex, Model model) {
            model.addAttribute("message", ex.getMessage());
            return "error"; // Refers to error.html template
        }
    }

    @GetMapping("/chart")
    public String showChart(Model model) {
        List<BmiStatsDto> bmiStats = glucoInputsService.getAverageReadingByBmi(); // Fetch the data

        // Etykiety i średnie odczyty
        List<String> bmiLabels = new ArrayList<>();
        List<Double> averageReadings = new ArrayList<>();

        for (BmiStatsDto stat : bmiStats) {
            bmiLabels.add(stat.getBmi()); // Assuming getBmi() returns a String
            averageReadings.add(stat.getAverageReading()); // Assuming getAverageReading() returns a Double
        }

        // Atrybuty do modelu
        model.addAttribute("bmiLabels", bmiLabels);
        model.addAttribute("averageReadings", averageReadings);

        return "chart";
    }

    @GetMapping("/average-reading-chart")
    public String showAverageReadingChart(Model model) {
        List<BmiStatsDto> averageReadingStats = glucoInputsService.getAverageReadingForAllNames();
        model.addAttribute("averageReadingStats", averageReadingStats);
        return "average-reading-chart";
    }
}