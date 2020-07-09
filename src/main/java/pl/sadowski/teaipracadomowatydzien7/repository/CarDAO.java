package pl.sadowski.teaipracadomowatydzien7.repository;

import org.springframework.dao.DuplicateKeyException;
import pl.sadowski.teaipracadomowatydzien7.model.Car;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface CarDAO {

    List<Car> findAll();

    List<Car> findCarsByYear(Integer min, Integer max);

    int save(Car cars);

    int deleteCar(long id);

    int updateCar(Car cars);

}
