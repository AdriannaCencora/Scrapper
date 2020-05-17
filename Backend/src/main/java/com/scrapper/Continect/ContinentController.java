package com.scrapper.Continect;

import com.scrapper.infections.InfectionService;
import com.scrapper.infections.model.Infection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collection;

@RestController
@RequestMapping(value = "/continent")
public class ContinentController {

    @Autowired
    private InfectionService infectionService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Collection<Continent>> getContinents(){
        return new ResponseEntity<>( Arrays.asList(Continent.values()), HttpStatus.OK);
    }

    @RequestMapping(value = "infections/{continent_name}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Infection>> getCountryInfections(@PathVariable String continent_name){
        return new ResponseEntity<>(infectionService.getAllInfectionForContinent(Continent.valueOf(continent_name.toUpperCase())), HttpStatus.OK);
    }

    @RequestMapping(value = "infections_current/{continent_name}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Infection>> getCurrentContinentInfections(@PathVariable String continent_name){
        return new ResponseEntity<>(infectionService.getCurrentInfectionByContinent(Continent.valueOf(continent_name.toUpperCase())), HttpStatus.OK);
    }
}
