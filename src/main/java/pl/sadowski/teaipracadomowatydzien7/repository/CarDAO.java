package pl.sadowski.teaipracadomowatydzien7.repository;

import org.springframework.dao.DuplicateKeyException;
import pl.sadowski.teaipracadomowatydzien7.model.Car;

import java.util.List;

public interface CarDAO {

    List<Car> getAll();

    List<Car> findCarsByYear(Integer min, Integer max);

    int save(Car cars) throws DuplicateKeyException;

    int deleteCar(long id);

    int updateCar(Car cars);

}
