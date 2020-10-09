package dao;

import entities.Country;
import exceptions.CantFindCountryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO {
    private Connection connection;

    public CountryDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Country> findAll() throws CantFindCountryException {
        List<Country> result = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM country ORDER BY id;")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String shortName = rs.getString("short_name");
                result.add(new Country(id, name, shortName));
            }
            rs.close();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new CantFindCountryException(e);
        }
    }

    public Country findById(int id) throws CantFindCountryException {
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM country WHERE id = ?;")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String shortName = rs.getString("short_name");
                rs.close();
                return new Country(id, name, shortName);
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
            throw new CantFindCountryException(exc);
        }
        return null;
    }
}