package models;

import utility.Validate;

import java.util.Objects;

public class Coordinates implements Validate {
    private Float x; //Максимальное значение поля: 685, Поле не может быть null
    private Long y; //Значение поля должно быть больше -617, Поле не может быть null
    public Coordinates(Float x, Long y){
        this.x = x;
        this.y = y;
    }
    public Coordinates(){}
    public Float getX(){
        return x;
    }
    public Long getY(){
        return y;
    }
    @Override
    public boolean validate(){
        if (x == null | x > 685) return  false;
        if (y == null | y < -617) return  false;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public void setX(Float x) {
        this.x = x;
    }

    public void setY(Long y) {
        this.y = y;
    }
}
