package dao;

import entities.Vendor;
import entities.Watch;
import exceptions.CantFindVendorException;
import exceptions.CantFindWatchException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WatchDAO {
    Connection connection;
    VendorDAO vendorDAO;

    public WatchDAO(Connection connection) {
        this.connection = connection;
        vendorDAO = new VendorDAO(connection);
    }

    public List<Watch> findAll() throws CantFindWatchException {
        List<Watch> result = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM watch ORDER BY id;")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String mark = rs.getString("mark");
                String type = rs.getString("type");
                int vendorId = rs.getInt("vendor_id");
                try {
                    Vendor vendor = vendorDAO.findById(vendorId);
                    result.add(new Watch(id, mark, type, vendor));
                } catch (CantFindVendorException e) {
                    result.add(new Watch(id, mark, type, null));
                }
            }
            rs.close();
            return result;
        } catch (SQLException exc) {
            exc.printStackTrace();
            throw new CantFindWatchException(exc);
        }
    }

    public List<Watch> findByVendor(Vendor vendor) throws CantFindWatchException {
        List<Watch> result = new ArrayList<>();
        int vendorId = vendor.getId();
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM watch WHERE vendor_id = ? ORDER BY id;")) {
            ps.setInt(1, vendorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String mark = rs.getString("mark");
                String type = rs.getString("type");
                result.add(new Watch(id, mark, type, vendor));
            }
            rs.close();
            return result;
        } catch (SQLException exc) {
            exc.printStackTrace();
            throw new CantFindWatchException(exc);
        }
    }

    public Watch findById(int id) throws CantFindWatchException {
        try (PreparedStatement ps = connection.prepareStatement(
                "SELECT * FROM watch WHERE id = ?;")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String mark = rs.getString("mark");
                String type = rs.getString("type");
                int vendorId = rs.getInt("vendor_id");
                try {
                    Vendor vendor = vendorDAO.findById(vendorId);
                    rs.close();
                    return new Watch(id, mark, type, vendor);
                } catch (CantFindVendorException e) {
                    rs.close();
                    return new Watch(id, mark, type, null);
                }
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
            throw new CantFindWatchException(exc);
        }
        return null;
    }
}
