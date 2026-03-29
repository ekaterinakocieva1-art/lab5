package managers;

import exeptions.RouteNotFoundException;
import exeptions.ValidationException;
import models.Coordinates;
import models.Location;
import models.Route;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


public class CollectionManager {
    public HashSet<Route> list = new HashSet<>();
    private int counter = 1;
    public CollectionManager(){
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
        route.setCreationDate(LocalDateTime.now());
        route.setId(counter);
        if(!route.validate()){
            throw new ValidationException("некорректный маршрут");
        }
        ++counter;
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
            throw new RouteNotFoundException("Incorrect id, route not found");
        }
        list.remove(route);
    }
    public void clear(){
        counter = 1;
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
        for(Route r : routes){
            if(counter < r.getId()){
                counter = r.getId();
            }
        }
        counter++;
        list = routes;
    }

}
