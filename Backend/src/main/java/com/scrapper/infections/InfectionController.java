package com.scrapper.infections;

import com.scrapper.country.CountryService;
import com.scrapper.country.model.Country;
import com.scrapper.infections.model.Infection;
import com.scrapper.utilities.Scrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping(value = "/infections")
public class InfectionController {

    @Autowired
    private InfectionService infectionService;

    @Autowired
    private CountryService conutryService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Collection<Infection>> getInfection(){
        return new ResponseEntity<>(infectionService.getAllInfection(), HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Infection> getInfection(@PathVariable Integer id){
        return infectionService.getInfectionById(id).map(u -> new ResponseEntity<>(u, HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Infection> addInfection(@RequestBody Infection infection){
        try {
            Country country = conutryService.getCountryById(infection.getCountry().getId()).orElse(conutryService.addOrCreateCountry(infection.getCountry()));
            infection.setCountry(country);
            return new ResponseEntity<>(infectionService.addInfection(infection), HttpStatus.OK);
        }
        catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/new_data", method = RequestMethod.GET)
    public ResponseEntity<Collection<Infection>> addNewInfection(){
        Collection<Infection> infections = Scrapper.ReadCovid();
        Collection<Infection> newInfections = new ArrayList<Infection>();
        try {
            for(Infection inf : infections){
                Country country = conutryService.addOrCreateCountry(inf.getCountry());
                inf.setCountry(country);
                Infection newInfection = infectionService.addInfection(inf);
                newInfections.add(newInfection);
            }
            return new ResponseEntity<>(newInfections, HttpStatus.OK);
        }
            catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
