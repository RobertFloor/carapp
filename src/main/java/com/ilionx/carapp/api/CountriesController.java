package com.ilionx.carapp.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
@RestController
@RequestMapping("api/countries")
public class CountriesController {

    private RestTemplate restTemplate = new RestTemplate();

    final String api = "https://countriesnow.space/api/v0.1/countries/cities/q?country=";

    @GetMapping("/{country}/cities")
    public ResponseEntity<List> getCountries(@PathVariable String country) {
        try {
            ResponseEntity<List> result = this.restTemplate.getForEntity(new URI(this.api + country), List.class);
            return result;

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return ResponseEntity.notFound().build();
    }

}
