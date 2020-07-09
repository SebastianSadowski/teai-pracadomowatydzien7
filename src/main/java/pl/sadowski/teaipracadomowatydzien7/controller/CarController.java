package pl.sadowski.teaipracadomowatydzien7.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sadowski.teaipracadomowatydzien7.model.Car;
import pl.sadowski.teaipracadomowatydzien7.service.CarService;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping(value = "/cars", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CarController {

CarService carService;

@Autowired
    public CarController(CarService carService) {
        this.carService = carService;
}

@GetMapping()
public ResponseEntity<List<Car>> getAll(){
    Optional<List<Car>> optional = carService.getAllCars();
    return ResponseEntity.of(optional);
}

@ApiOperation(value = "", notes= "While all args are null works same like getAll()")
@GetMapping("/")
public ResponseEntity<List<Car>> getCarByProductionYear(@RequestParam(required = false) Integer min, @RequestParam(required = false) Integer max){
    Optional<List<Car>> optional = carService.getCarByProductionYear(min, max);
    return ResponseEntity.of(optional);
}
@PostMapping
public ResponseEntity<String> addCar(@RequestBody Car car){
    return carService.saveCar(car)?ResponseEntity.ok("Dodano samochód"):new ResponseEntity("Samochód z podanym ID już istanieje !", HttpStatus.BAD_REQUEST);
}

@DeleteMapping("/{id}")
    public ResponseEntity<String> removeCar(@PathVariable(value="id") long ID){
    int count = carService.deleteCar(ID);
    return count!=0?ResponseEntity.ok("Usunieto liczbe pojazdów o podanym ID:  " + count):new ResponseEntity<>("Nie znaleziono pojazdu o podanym ID", HttpStatus.NOT_FOUND);
}

@PutMapping
    public ResponseEntity<String> modifyCar(@RequestBody Car car){
return    carService.updateCar(car)?ResponseEntity.ok("Usunięto pomyślnie auto: "+ car.toString()):new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
}
}
