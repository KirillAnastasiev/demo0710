package dao;

import entities.Country;
import entities.Vendor;
import exceptions.CantFindCountryException;
import exceptions.CantFindVendorException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VendorDAO {
    Connection connection;
    CountryDAO countryDAO;

    public VendorDAO(Connection connection) {
        this.connection = connection;
        countryDAO = new CountryDAO(connection);
    }

    public List<Vendor> findAll() throws CantFindVendorException {
        List<Vendor> result = new ArrayList<>();
        Set<Country> countries = new HashSet<>();
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM vendor ORDER BY id;")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int countryId = rs.getInt("country_id");
                try {
                    Country country = null;
                    for (Country current : countries) {
                        if (current.getId() == countryId) {
                            country = current;
                        }
                    }
                    if (country == null) {
                        country = countryDAO.findById(countryId);
                        countries.add(country);
                    }
                    result.add(new Vendor(id, name, country));
                } catch (CantFindCountryException exc) {
                    result.add(new Vendor(id, name, null));
                }
            }
            rs.close();
            return result;
        } catch (SQLException exc) {
            exc.printStackTrace();
            throw new CantFindVendorException(exc);
        }
    }

    public List<Vendor> findByCountry(Country country) throws CantFindVendorException {
        int countryId = country.getId();
        List<Vendor> result = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM vendor WHERE country_id = ? ORDER BY id;")){
            ps.setInt(1, countryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                result.add(new Vendor(id, name, country));
            }
            rs.close();
            return result;
        } catch (SQLException exc) {
            exc.printStackTrace();
            throw new CantFindVendorException();
        }
    }

    public Vendor findById(int id) throws CantFindVendorException {
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM vendor WHERE id = ?;")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                int countryId = rs.getInt("country_id");
                try {
                    Country country = countryDAO.findById(countryId);
                    rs.close();
                    return new Vendor(id, name, country);
                } catch (CantFindCountryException e) {
                    rs.close();
                    return new Vendor(id, name, null);
                }
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
            throw new CantFindVendorException(exc);
        }
        return null;
    }
}
