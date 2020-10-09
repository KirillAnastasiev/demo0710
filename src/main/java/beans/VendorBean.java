package beans;

import entities.Vendor;

import java.util.List;

public class VendorBean {
    private List<Vendor> vendors;
    private Vendor vendor;

    public VendorBean() {
    }

    public List<Vendor> getVendors() {
        return vendors;
    }

    public void setVendors(List<Vendor> vendors) {
        this.vendors = vendors;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
}
