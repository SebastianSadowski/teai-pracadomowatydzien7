package pl.sadowski.teaipracadomowatydzien7.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import pl.sadowski.teaipracadomowatydzien7.aspects.MethodsMarker;
import pl.sadowski.teaipracadomowatydzien7.model.Car;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CarDAOImpl implements CarDAO {


    JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @MethodsMarker(logExecutionTime = true, logObjects = true)
    @Override
    public List<Car> getAll() {
        String sql = "SELECT * FROM cars";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);

        return maps.stream().map(map -> new Car(
                Long.parseLong(String.valueOf(map.get("ID"))),
                String.valueOf(map.get("mark")),
                String.valueOf(map.get("model")),
                String.valueOf(map.get("color")),
                Long.parseLong(String.valueOf(map.get("production_year")))))
                .collect(Collectors.toList());
    }


    @MethodsMarker(logExecutionTime = true, logObjects = false)
    @Override
    public List<Car> findCarsByYear(@Nullable Integer min, @Nullable Integer max) {
        max = max == null ? Integer.valueOf(new SimpleDateFormat("yyyy").format(new Date())) : max;
        min = min == null ? 0 : min;
        String sql = "SELECT * FROM cars " +
                "WHERE production_year >= ? AND production_year <= ? " +
                "ORDER BY production_year ASC";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, min, max);
        return maps.stream().map(map -> new Car(
                Long.parseLong(String.valueOf(map.get("ID"))),
                String.valueOf(map.get("mark")),
                String.valueOf(map.get("model")),
                String.valueOf(map.get("color")),
                Long.parseLong(String.valueOf(map.get("production_year"))))
        ).collect(Collectors.toList());

    }

    @Override
    public int save(Car cars) throws DuplicateKeyException {


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
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int updateCar(Car cars) {
        String sql = "UPDATE cars SET mark = ?, model = ?, color = ?, production_year = ? WHERE ID = ?";
        return jdbcTemplate.update(sql, cars.getMark(), cars.getModel(), cars.getColor(), cars.getProductionYear(), cars.getId());
    }


}