package in.sigma.RestaurantSystem.controller;

import in.sigma.RestaurantSystem.entity.Menu;
import in.sigma.RestaurantSystem.repository.MenuRepo;
import in.sigma.RestaurantSystem.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuRepo menuRepo;

    @Autowired
    private MenuService menuService;

    @PostMapping("/addMenu")
    public ResponseEntity<?> addMenu(@RequestBody Menu data){
        Menu menu = menuRepo.save(data);
//        return new ResponseEntity<>(menu, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("status",200,"message","Order Place Successfully","data",menu));
    }

    @PutMapping("/updateMenu/{id}")
    public ResponseEntity<Menu> updateMenuById(@PathVariable("id") Integer id, @RequestBody Menu data){
        Menu menu = menuService.updateMenuById(id, data);
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @DeleteMapping("/delMenu/{id}")
    public ResponseEntity<Menu> deleteMenuById(@PathVariable("id") Integer id){
        menuRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    // we can make updateMenuByName & deleteMenuByName

    @GetMapping("/getMenu/{id}")
    public Menu getMenuById(@PathVariable("id") Integer id){
        return menuRepo.getById(id);
    }

    @GetMapping("/getAllMenu")
    public List<Menu> getList(){
        return menuRepo.findAll();
    }

//    @GetMapping("/getMenuType/{type}")
//    public List<Menu> getMenuByType(@PathVariable("type") String type){
//        return menuRepo.findByType(type);
//    }

    @GetMapping("/getMenuType/param")
    public List<Menu> getMenuByType(@RequestParam("type") String type){
        return menuRepo.findByType(type);
    }

    @GetMapping("/searchMenu/param")
    public List<Menu> searchByName(@RequestParam("name") String name) {
        return menuRepo.searchByName(name);
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<?> updateStatusById(@PathVariable("id") Integer id){
        Menu menu = menuService.updateStatusById(id);
//        return new ResponseEntity<>(menu, HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("status",200,"message","Order Place Successfully","data",menu));
    }

    @PutMapping("/order/{id}/{quantity}")
    public ResponseEntity<?> OrderById(@PathVariable("id") Integer id, @PathVariable("quantity") Integer order){
        try {
            Menu menu = menuService.order(id,order);
//            return new ResponseEntity<>(menu,HttpStatus.OK);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("status",200,"message","Order Place Successfully","data",menu));

        } catch (RuntimeException e) {
            switch (e.getMessage()){
                case "MENU_NOT_FOUND" :
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("status",404,"message","Menu Not Found"));
                case "MENU_NOT-AVAILABLE" :
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("status",400,"message","Menu Not Available"));
                case "MENU_OUT_OF_STOCK" :
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("status",400,"message","Menu Out of Stock"));

                default: return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("status",500,"message","Something Went Wrong"));
            }
        }

    }
}
