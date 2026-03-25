package managers;

import models.Coordinates;
import models.Location;
import models.Route;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class CollectionManager {
    public HashSet<Route> list = new HashSet<>();
    private int counter;
    public CollectionManager(){
        Coordinates coords1 = new Coordinates(10.5f, 200L);
        Location from1 = new Location(0, 0.0, 0.0);
        Location to1 = new Location(100, 50.5, 10.0);
        Route route1 = new Route(1,"Москва - Питер", coords1, from1, to1, 700L);
        list.add(route1);

        Coordinates coords2 = new Coordinates(5.0f, -100L);
        Location from2 = new Location(10, 20.0, 5.0);
        Location to2 = new Location(30, 40.0, 15.0);
        Route route2 = new Route(2, "Казань - Уфа", coords2, from2, to2,500L);
        list.add(route2);
    }
    // метод поиска маршрута по его id
    public Route findId(int id){
        for (Route r : list) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }
    public void add(Route route){
        route.setId(++counter);
        list.add(route);
    }

    public void update(int id, Route newRoute){
        list.remove(id);
        newRoute.setId(id);
        list.add(newRoute);

    }
    public void remove(int id){
        Route route = findId(id);
        if(route == null){
            throw new IllegalArgumentException("Incorrect id, route not found");
        }
        list.remove(id);
    }
    public void clear(){
        list.clear();
    }
    public void addIfMax(Route route) {
        if (list.isEmpty()) {
            route.setId(++counter);
            list.add(route);
        } else {
            Route last = null;
            for (Route r : list) {
                last = r;
            }
            if (route.compareTo(last) > 0) {
                route.setId(++counter);
                list.add(route);
            }
        }
    }
    public void removeGreater(Route route) {
        List<Route> routesDelete = new ArrayList<>();
        for(Route r : list) {
            if (r.compareTo(route) > 0) {
                routesDelete.add(r);
            }
        }
        list.removeAll(routesDelete);
    }
    public void removeLower(Route route){
        List<Route> routesToDelete = new ArrayList<>();
        for(Route r : list){
            if (r.compareTo(route)< 0){
                routesToDelete.add(r);
            }
        }
        list.removeAll(routesToDelete);
    }
    public double averageOfDistance(){
        if(list.isEmpty()){
            throw new RuntimeException("Коллекция пустая");
        }
        double sum = 0;
        for(Route route: list){
            sum += route.getDistance();
        }
        double average = sum / list.size();
        return average;
    }


    public HashSet<Route> filterGreaterThanDistance(long maxDist){
        HashSet<Route> routes= new HashSet<>();
        for(Route r : list){
            if(r.getDistance() > maxDist){
                routes.add(r);
            }
        }
        return routes;
    }

    public HashSet<Route> getList() {
        return list;
    }
    public void load(HashSet<Route> routes){
        list = routes;
    }
}
