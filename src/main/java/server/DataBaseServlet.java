package server;


import beans.CountryBean;
import beans.VendorBean;
import beans.WatchBean;
import dao.CountryDAO;
import dao.VendorDAO;
import dao.WatchDAO;
import entities.Country;
import entities.Vendor;
import exceptions.CantFindCountryException;
import exceptions.CantFindException;
import exceptions.CantFindVendorException;

import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

@WebServlet(name = "DataBaseServlet", urlPatterns = {"*.html"})
public class DataBaseServlet extends HttpServlet {
    private CountryDAO countryDAO;
    private VendorDAO vendorDAO;
    private WatchDAO watchDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/example");
            Connection connection = ds.getConnection();
            countryDAO = new CountryDAO(connection);
            vendorDAO = new VendorDAO(connection);
            watchDAO = new WatchDAO(connection);
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String URI = request.getRequestURI();
        if (URI.contains("showvendors.html")) {
            showVendors(request, response);
        } else if (URI.contains("showmarks.html")) {
            showMarks(request, response);
        } else {
            showCountries(request, response);
        }
    }

    private void showCountries(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            CountryBean countryBean = new CountryBean();
            countryBean.setCountries(countryDAO.findAll());
            request.setAttribute("countryBean", countryBean);
            request.getRequestDispatcher("showcountries.jsp").forward(request, response);
        } catch (CantFindException e) {
            e.printStackTrace();
        }
    }

    private void showVendors(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int countryId = Integer.parseInt(request.getParameter("countryId"));
        try {
            Country country = countryDAO.findById(countryId);
            VendorBean vendorBean = new VendorBean();
            vendorBean.setVendors(vendorDAO.findByCountry(country));
            CountryBean countryBean = new CountryBean();
            countryBean.setCountry(country);
            request.setAttribute("countryBean", countryBean);
            request.setAttribute("vendorBean", vendorBean);
            request.getRequestDispatcher("showvendors.jsp").forward(request, response);
        } catch (CantFindException e) {
            e.printStackTrace();
        }
    }

    private void showMarks(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int vendorId = Integer.parseInt(request.getParameter("vendorId"));
        try {
            Vendor vendor = vendorDAO.findById(vendorId);
            WatchBean watchBean = new WatchBean();
            watchBean.setWatches(watchDAO.findByVendor(vendor));
            VendorBean vendorBean = new VendorBean();
            vendorBean.setVendor(vendor);
            request.setAttribute("watchBean", watchBean);
            request.setAttribute("vendorBean", vendorBean);
            request.getRequestDispatcher("showwatches.jsp").forward(request, response);
        } catch (CantFindException exc) {
            exc.printStackTrace();
        }
    }
}
