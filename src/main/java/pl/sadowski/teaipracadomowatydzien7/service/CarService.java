package pl.sadowski.teaipracadomowatydzien7.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import pl.sadowski.teaipracadomowatydzien7.model.Car;
import pl.sadowski.teaipracadomowatydzien7.repository.CarDAO;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    CarDAO carDAO;
@Autowired
    public CarService(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    public Optional<List<Car>> getAllCars(){
    return Optional.ofNullable(carDAO.findAll());
    }

    public Optional<List<Car>> getCarByProductionYear(@Nullable Integer min, @Nullable Integer max){
    return Optional.ofNullable(carDAO.findCarsByYear(min, max));
    }

    public boolean saveCar(Car car){

           return carDAO.save(car) != 0;
    }

    public int deleteCar(long ID){
    return carDAO.deleteCar(ID);
    }

    public boolean updateCar(Car car){
    return carDAO.updateCar(car) != 0;
    }


}
