package com.scrapper.country;

import com.scrapper.country.model.Country;
import com.scrapper.infections.InfectionService;
import com.scrapper.infections.model.Infection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private InfectionService infectionService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Collection<Country> getCountries(){
        return countryService.getAllCountries();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Country> getCountry(@PathVariable Integer id){
        return countryService.getCountryById(id).map(u -> new ResponseEntity<>(u, HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "infections/{id}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Infection>> getCountryInfection(@PathVariable Integer id){
        Optional<Country> country = countryService.getCountryById(id);
        if(!country.isEmpty()){
            return new ResponseEntity<>(infectionService.getAllInfectionByCountry(country.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "infections_current/{id}", method = RequestMethod.GET)
    public ResponseEntity<Infection> getCurrentCountryInfection(@PathVariable Integer id){
        Optional<Country> country = countryService.getCountryById(id);
        if(!country.isEmpty()){
            return new ResponseEntity<>(infectionService.getCurrentInfectionByCountry(country.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Country> addCountry(@RequestBody Country country){
        try {
            return new ResponseEntity<>(countryService.addCountry(country), HttpStatus.OK);
        }
        catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Country> deleteCountry(Integer id){
        try {
            countryService.deleteCountryById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
