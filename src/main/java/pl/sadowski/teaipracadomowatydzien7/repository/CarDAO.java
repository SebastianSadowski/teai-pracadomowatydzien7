package pl.sadowski.teaipracadomowatydzien7.repository;

import pl.sadowski.teaipracadomowatydzien7.model.Car;

import java.util.List;

public interface CarDAO {

    List<Car> getAll();

    boolean save(Car car);

    boolean deleteCar  (long id);

    boolean updateCar (Car car);

}
