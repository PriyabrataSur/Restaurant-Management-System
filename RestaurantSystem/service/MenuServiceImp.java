package in.sigma.RestaurantSystem.service;

import in.sigma.RestaurantSystem.entity.Menu;
import in.sigma.RestaurantSystem.repository.MenuRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImp implements MenuService{

    @Autowired
    private MenuRepo menuRepo;

    @Override
    public Menu updateMenuById(Integer id, Menu data) {
        Menu newMenu = menuRepo.getById(id);

        newMenu.setTotalAvailable(data.getTotalAvailable());
        newMenu.setPrice(data.getPrice());
        newMenu.setAvailable(data.isAvailable());

        return menuRepo.save(newMenu);
    }

    @Override
    public Menu updateStatusById(Integer id) {
        Menu newStat = menuRepo.getById(id);

        newStat.setAvailable(!newStat.isAvailable());

        return menuRepo.save(newStat);
    }

    @Override
    public Menu order(Integer id, Integer order) {

        Menu newOrder = menuRepo.findById(id).orElseThrow(() -> new RuntimeException("MENU_NOT_FOUND"));

        if(!newOrder.isAvailable()){
            throw new RuntimeException("MENU_NOT-AVAILABLE");
        }if (newOrder.getTotalAvailable()<1){
            throw new RuntimeException("MENU_OUT_OF_STOCK");
        }
        newOrder.setTotalAvailable(newOrder.getTotalAvailable() - order);
        return menuRepo.save(newOrder);

    }
}
