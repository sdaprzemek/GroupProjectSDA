package pl.sda.serwer.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import pl.sda.commons.entities.Car;
import pl.sda.serwer.services.ApiService;
import pl.sda.serwer.services.CacheService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/car")
public class CarController implements ApiController<Car> {

    private ApiService<Car> carService;
    private Gson converter;
    private static final String CACHE_KEY_PREFIX = "car";

    @Autowired
    public CarController(@Qualifier("carService") ApiService<Car> carService, Gson converter) {
        this.carService = carService;
        this.converter = converter;
    }

    @DeleteMapping("/delete")
    @ResponseStatus(OK)
    public void delete(@RequestParam Integer id) {
        carService.delete(id);
    }

    @PostMapping("/add")
    @ResponseStatus(CREATED)
    public void add(@RequestBody Car elem) {
        carService.add(elem);
    }

    @PutMapping("/update")
    @ResponseStatus(OK)
    public void update(@RequestBody Car elem) {
        carService.update(elem);
        if (CacheService.isCached(CACHE_KEY_PREFIX + elem.getCarID())) {
            CacheService.replace(CACHE_KEY_PREFIX + elem.getCarID(), elementToJson(elem));
        }
    }

    @GetMapping("/all")
    @ResponseStatus(OK)
    public List<Car> findAll() {
        return carService.findAll();
    }

    @GetMapping("/count")
    @ResponseStatus(OK)
    public Long count() {
        return carService.count();
    }

    @GetMapping("/findById")
    @ResponseStatus(OK)
    public Car findById(@RequestParam Integer id) {
        if (CacheService.isCached(CACHE_KEY_PREFIX + id)) {
            return jsonToElement(CacheService.get(CACHE_KEY_PREFIX + id));
        } else {
            Car car = carService.findById(id);
            CacheService.add(CACHE_KEY_PREFIX + id, elementToJson(car));
            return car;
        }
    }

    public String elementToJson(Car car) {
        return converter.toJson(car);
    }

    public Car jsonToElement(String jsonString) {
        return converter.fromJson(jsonString, Car.class);
    }
}
