package pl.sadowski.teaipracadomowatydzien7.repository;

import org.springframework.dao.DuplicateKeyException;
import pl.sadowski.teaipracadomowatydzien7.model.Cars;

import java.io.IOException;
import java.util.List;

public interface CarDAO {

    List<Cars> getAll();

    int save(Cars cars) throws DuplicateKeyException;

    int deleteCar  (long id);

    int updateCar (Cars cars);

}
