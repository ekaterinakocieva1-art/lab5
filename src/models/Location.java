package models;

import utility.Validate;

import java.util.Objects;

public class Location implements Validate {
    private Integer x; //Поле не может быть null
    private Double y; //Поле не может быть null
    private double z;
    public Location(Integer x, Double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Location() {

    }

    public Integer getX(){
        return x;
    }
    public Double getY(){
        return y;
    }
    public double getZ(){
        return z;
    }
    @Override
    public boolean validate(){
        if (x == null) return false;
        if (y == null) return false;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(z, location.z) == 0 && Objects.equals(x, location.x) && Objects.equals(y, location.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
