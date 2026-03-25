package models;


import utility.Validate;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;

public class Route implements Validate, Comparable<Route> {

    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Location from; //Поле может быть null
    private Location to; //Поле не может быть null
    private long distance; //Значение поля должно быть больше 1

    public Route(int id,String name, Coordinates coordinates,LocalDateTime creationDate, Location from, Location to, long distance ){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.from = from;
        this.to = to;
        this.distance = distance;
    }
    public Route(int id,String name, Coordinates coordinates, Location from, Location to, long distance){
        this(id, name, coordinates, LocalDateTime.now(), from, to, distance);
    }

    @Override
    public boolean validate(){
        if (id <= 0) return false;
        if (name == null || name.isEmpty()) return false;
        if (coordinates == null) return false;
        if (creationDate == null) return false;
        if (from == null) return false;
        if (to == null) return false;
        if (distance <= 1) return false;
        return true;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    public long getDistance() {
        return distance;
    }
    public void setId(int id){
        this.id = id;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public void setFrom(Location from) {
        this.from = from;
    }

    public void setTo(Location to) {
        this.to = to;
    }
    @Override
    public String toString(){
        return "Models.Route(id = " + id + ", name = " + name + ",distance = " + distance + " , creator = " + creationDate + ")";
    }
    @Override
    public int compareTo(Route o){
        return name.compareTo(o.name);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return id == route.id && distance == route.distance && Objects.equals(name, route.name) && Objects.equals(coordinates, route.coordinates) && Objects.equals(creationDate, route.creationDate) && Objects.equals(from, route.from) && Objects.equals(to, route.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, from, to, distance);
    }

}
