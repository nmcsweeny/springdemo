package com.example.demo.services;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import com.example.demo.models.FuelStats;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
@Service
public class DataService {

    private static String url = "https://raw.githubusercontent.com/datasets/co2-fossil-by-nation/master/data/fossil-fuel-co2-emissions-by-nation.csv";
    private List<FuelStats> allStats = new ArrayList<>();

    public List<FuelStats> getAllStats(){
        return allStats;
    }

    @PostConstruct
    public void fetchData() throws IOException, InterruptedException{
        List<FuelStats> newStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build();
        HttpResponse<String> s = client.send(request, HttpResponse.BodyHandlers.ofString()); 
        // System.out.println(s.body());  
        StringReader csvBodyReader = new StringReader(s.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        
        for(CSVRecord record : records){
            FuelStats fuelStat = new FuelStats();
            
                fuelStat.setYear(Integer.parseInt(record.get(record.size()-10)));
                fuelStat.setCountry(record.get("Country"));
                fuelStat.setTotal(Integer.parseInt(record.get(record.size()-8)));
                System.out.println(fuelStat);
                if(fuelStat.getYear() == 2014){
                    newStats.add(fuelStat);
                }    
            
            
        }
        this.allStats = newStats;
    }
    
}
