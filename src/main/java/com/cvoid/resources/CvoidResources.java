package com.cvoid.resources;

import com.cvoid.dto.SearchParamDto;
import com.cvoid.service.CovidService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/cvoid")
@Api(value = "CvoidResources")
@Slf4j
@RequiredArgsConstructor
public class CvoidResources {

    private final CovidService covidService;

    @ApiOperation(value = "Get Cvoid Aggreggated Data by Country/State")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @GetMapping("/search")
    public ResponseEntity search(SearchParamDto searchParamDto) {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(covidService.search(searchParamDto));
    }

    @ApiOperation(value = "Get Cvoid Data for Containment Zone")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request")
    })
    @GetMapping("/containment")
    public ResponseEntity containmentZone() {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(covidService.findContementZones());
    }

}
