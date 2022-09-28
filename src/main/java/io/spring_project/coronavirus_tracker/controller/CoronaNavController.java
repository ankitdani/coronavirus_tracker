package io.spring_project.coronavirus_tracker.controller;

import io.spring_project.coronavirus_tracker.model.LocationStats;
import io.spring_project.coronavirus_tracker.service.CovidTrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CoronaNavController {
    @Autowired
    private CovidTrackerService covidService;

    @GetMapping("/")
    public String getdata(Model model){
        List<LocationStats> data = covidService.getAllStats();
        model.addAttribute("totalCases", data);
        model.addAttribute("latestNumberCases", data.stream().mapToLong(LocationStats::getLatestTotalCases).sum());
        model.addAttribute("NewNumberCases", data.stream().mapToLong(LocationStats::getDiffFromPrevDay).sum());

        return "index";
    }
}
