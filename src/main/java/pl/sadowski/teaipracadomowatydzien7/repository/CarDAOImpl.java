package pl.sadowski.teaipracadomowatydzien7.repository;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import pl.sadowski.teaipracadomowatydzien7.model.Cars;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
@Repository
public class CarDAOImpl implements CarDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Cars> getAll() {
        String sql = "SELECT * FROM cars";
        List<Cars> carsList = new ArrayList<>();
        List<Map<String, Object>> map = jdbcTemplate.queryForList(sql);
        map.forEach(object -> {
            carsList.add(new Cars(
                    Long.parseLong(String.valueOf(object.get("ID"))),
                    String.valueOf(object.get("mark")),
                    String.valueOf(object.get("model")),
                    String.valueOf(object.get("color")),
                    Integer.parseInt(String.valueOf(object.get("production_year")))));
        });
        return carsList;
    }

    @Override
    public int save(Cars cars) throws DuplicateKeyException {


        String sql = "INSERT INTO cars VALUES(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, cars.getId(),
                cars.getMark(),
                cars.getMark(),
                cars.getColor(),
                cars.getProductionYear());
    }

    @Override
    public int deleteCar(long id) {
        String sql = "DELETE FROM cars WHERE ID = ?";
      return  jdbcTemplate.update(sql, id);
    }

    @Override
    public int updateCar(Cars cars) {
        String sql = "UPDATE cars SET mark = ?, model = ?, color = ?, production_year = ? WHERE ID = ?";
        return jdbcTemplate.update(sql, cars.getMark(), cars.getModel(), cars.getColor(), cars.getProductionYear(), cars.getId());
    }


    @EventListener(ApplicationReadyEvent.class)
    public void test() {
        Cars car = new Cars(4, "OPEL", "ASTRA", "GREEN", 2019);
        try {
            save(car);
        } catch (DuplicateKeyException e) {
            log.error(e.getCause().getMessage());
            log.error("I have updated car no.4");
            updateCar(car);
        }


        getAll().stream().forEach(obj -> log.info(obj));

        deleteCar(11);
updateCar(new Cars(4, "DUPA", "DUPADUPA", "DUPADUPADUPA", 1995));
        getAll().stream().forEach(obj -> log.info(obj));

    }
}
