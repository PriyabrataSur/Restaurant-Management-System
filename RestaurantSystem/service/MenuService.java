package in.sigma.RestaurantSystem.service;

import in.sigma.RestaurantSystem.entity.Menu;

public interface MenuService {

    public Menu updateMenuById(Integer id, Menu data);

    public Menu updateStatusById(Integer id);

    public Menu order(Integer id, Integer order);
}
