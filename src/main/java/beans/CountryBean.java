package beans;

import entities.Country;

import java.util.List;

public class CountryBean {
    private List<Country> countries;

    private Country country;

    public List<Country> getCountries() {
        return countries;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
