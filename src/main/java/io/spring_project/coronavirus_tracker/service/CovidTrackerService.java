package io.spring_project.coronavirus_tracker.service;

import io.spring_project.coronavirus_tracker.model.LocationStats;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

@Service
public class CovidTrackerService {
    private List<LocationStats> allStats = new ArrayList<>();
    private static final String VIRUS_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    @PostConstruct
    public void fetchData() throws IOException, InterruptedException{
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_URL)).build();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(httpResponse.body());

        StringReader reader = new StringReader((String) httpResponse.body());
        List<LocationStats> newStats = new ArrayList<>();
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(reader);

        for (CSVRecord rec: records){
            LocationStats stats = new LocationStats();
            stats.setStats(rec.get("Province/State"));
            stats.setCountry(rec.get("Country/Region"));
            long totalCases = Integer.parseInt(rec.get(rec.size()-1));
            stats.setLatestTotalCases(totalCases);
            stats.setDiffFromPrevDay(totalCases - Long.parseLong(rec.get(rec.size()-2)));
            newStats.add(stats);
        }
        this.allStats = newStats;
    }
    public List<LocationStats> getAllStats(){
        return allStats;
    }
    public void setAllStats(List<LocationStats> allStats){
        this.allStats = allStats;
    }
}
