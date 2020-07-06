package pl.sadowski.teaipracadomowatydzien7.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Car {

    private long id;
    private String mark;
    private String model;
    private String color;
    private String dateProduction;
}
