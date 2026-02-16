package in.sigma.RestaurantSystem.repository;

import in.sigma.RestaurantSystem.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepo extends JpaRepository<Menu, Integer> {

    List<Menu> findByType(String type);

    @Query("SELECT m FROM Menu m WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', :search, '%'))")
    List<Menu> searchByName(@Param("search") String search);
}
