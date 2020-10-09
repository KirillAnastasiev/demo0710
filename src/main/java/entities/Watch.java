package entities;

import java.util.Objects;

public class Watch {
    private int id;
    private String mark;
    private String type;
    private Vendor vendor;

    public Watch() {

    }

    public Watch(int id, String mark, String type, Vendor vendor) {
        this.id = id;
        this.mark = mark;
        this.type = type;
        this.vendor = vendor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Watch watch = (Watch) o;
        return id == watch.id &&
                mark.equals(watch.mark) &&
                type.equals(watch.type) &&
                Objects.equals(vendor, watch.vendor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mark, type, vendor);
    }

    @Override
    public String toString() {
        return id + " : " + mark + " : " + type + " : " + vendor;
    }
}
