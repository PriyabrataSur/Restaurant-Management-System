package in.sigma.RestaurantSystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Getter
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String name;
    String type;
    double price;
    boolean available;
    Integer totalAvailable;

//    public Menu orElseThrow(Object notAvailable) {
//    }
}
