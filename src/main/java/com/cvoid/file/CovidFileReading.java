package com.cvoid.file;

import com.cvoid.dto.CvoidInfoDTO;
import com.cvoid.service.CovidService;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class CovidFileReading {

    private final CovidService covidService;

    @Scheduled(fixedDelay = 36000000, initialDelay = 600000000)
    public void filesProcessing() {
        String PATH = "./COVID-19/csse_covid_19_data/csse_covid_19_daily_reports";
        File myDirectory = new File(PATH);
        List<String> fileNames = Arrays.asList(myDirectory.list()).stream().filter(fileName -> fileName.contains("21.csv")).collect(Collectors.toList());
        for(String fileName : fileNames){
            File input = new File(PATH+"/"+fileName);
            try {
                CsvSchema csv = CsvSchema.emptySchema().withHeader();
                CsvMapper csvMapper = new CsvMapper();
                MappingIterator<CvoidInfoDTO> mappingIterator =  csvMapper.reader().forType(CvoidInfoDTO.class).with(csv).readValues(input);
                List<CvoidInfoDTO> cvoidInfoDTOList = mappingIterator.readAll();
                covidService.save(cvoidInfoDTOList);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
