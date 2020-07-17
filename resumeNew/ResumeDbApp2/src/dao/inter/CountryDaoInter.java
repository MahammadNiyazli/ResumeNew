package dao.inter;

import entity.Country;
import entity.EmploymentHistory;

import java.util.List;

public interface CountryDaoInter {

    public List<Country> getAllCountry();
    public boolean addCountry(Country c);
    public boolean updateCountry(Country c);
    public boolean removeCountry(int id);
    public Country getCountryById(int id);
}
