package com.scrapper.country.impl;

import com.scrapper.Continect.Continent;
import com.scrapper.country.CountryService;
import com.scrapper.country.model.Country;
import com.scrapper.country.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class CountryImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;

    @Override
    public Optional<Country> getCountryById(Integer countryId) throws IllegalArgumentException {
        return countryRepository.findById(countryId);
    }

    @Override
    public Optional<Country> getCountryByName(String name) throws IllegalArgumentException {
        return Optional.ofNullable(countryRepository.getCountryByName(name).get(0));
    }

    @Override
    public Optional<Collection<Country>> getCountryByContinent(Continent continent) throws IllegalArgumentException {
        return Optional.ofNullable(countryRepository.findByContinent(continent));
    }

    @Override
    public Collection<Country> getAllCountries() {
        return StreamSupport.stream(countryRepository.findAll().spliterator(), false).
                collect(Collectors.toList());
    }

    @Override
    public Country addCountry(Country country) throws IllegalArgumentException {
        if(country.getId() == null || !countryRepository.existsById(country.getId()) || !countryRepository.getCountryByName(country.getName()).isEmpty()) {
            try {
                return countryRepository.save(country);
            }
            catch (DataAccessException e){
                throw  new IllegalArgumentException(e);
            }
        }else
            throw new IllegalArgumentException("Country already exist");
    }

    @Override
    public Country addOrCreateCountry(Country country) throws IllegalArgumentException {
        if(country.getId() == null && countryRepository.getCountryByName(country.getName()).isEmpty()) {
            try {
                return countryRepository.save(country);
            }
            catch (DataAccessException e){
                throw  new IllegalArgumentException(e);
            }
        }else{
            if(country.getId() != null)
                return countryRepository.findById(country.getId()).get();
            if(!countryRepository.getCountryByName(country.getName()).isEmpty())
                return countryRepository.getCountryByName(country.getName()).get(0);
        }
            throw new IllegalArgumentException("Country already exist");
    }

    @Override
    public void deleteCountryById(Integer countryId) throws IllegalArgumentException {
        if(countryRepository.existsById(countryId)) {
            try {
                countryRepository.deleteById(countryId);
            }
            catch (DataAccessException e){
                throw  new IllegalArgumentException(e);
            }
        }else
            throw new IllegalArgumentException("Table country already exist");
    }
}
