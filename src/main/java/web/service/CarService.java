package web.service;

import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {
    public List<Car> getCarsByCount(Integer count) {
        List<Car> cars = new ArrayList<>();
        cars.add(new Car(0, "Toyota", 123));
        cars.add(new Car(1, "Lexus", 321));
        cars.add(new Car(2, "Jeep", 789));
        cars.add(new Car(3, "Tesla", 987));
        cars.add(new Car(4, "Lambo", 777));
        return cars.stream().limit(count).collect(Collectors.toList());
    }
}
