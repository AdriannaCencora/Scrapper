package com.scrapper.country;

import com.scrapper.Continect.Continent;
import com.scrapper.country.model.Country;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface CountryService {

    Optional<Country> getCountryById(Integer countryId) throws IllegalArgumentException;

    Optional<Country> getCountryByName(String name) throws IllegalArgumentException;

    Optional<Collection<Country>> getCountryByContinent(Continent continent) throws IllegalArgumentException;

    Collection<Country> getAllCountries();

    Country addCountry(Country country) throws IllegalArgumentException;

    Country addOrCreateCountry(Country country) throws IllegalArgumentException;

    void deleteCountryById(Integer countryId)  throws IllegalArgumentException;
}
