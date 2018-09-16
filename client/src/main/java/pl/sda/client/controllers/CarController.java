package pl.sda.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.client.services.CarService;
import pl.sda.commons.entities.Car;

import java.util.ArrayList;
import java.util.List;

import static pl.sda.commons.entities.Car.builder;

@Controller
public class CarController {

    private static final java.util.logging.Logger log = java.util.logging
            .Logger.getLogger("CarController");

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    //localhost:8080/car/find/1
    @GetMapping("car/find/{id}")
    public String findByPath(@PathVariable Integer id, Model model) {
        model.addAttribute("car", carService.findById(id));
        return "car";
    }

    //localhost:8080/car/find?id=1
    @GetMapping("car/find")
    public String findByParam(@RequestParam Integer id, Model model) {
        model.addAttribute("car", carService.findById(id));
        return "car";
    }

    //localhost:8080/car/findall
    @GetMapping("car/findall")
    public String findAll(Model model) {
        model.addAttribute("carList", carService.findAll());
        return "carList";
    }

    //localhost:8080/car/findvin?vin=01234567890
    @GetMapping("car/findvin")
    public String finByParam(@RequestParam String vin, Model model) {
        model.addAttribute("car", carService.findByVIN(vin));
        return "car";
    }

    List<Car> createFakeDataList() {
        List<Car> list = new ArrayList<>();
        list.add(createFakeData());
        list.add(createFakeData());
        return list;
    }

    private Car createFakeData() {
        return builder()
                .carID(1)
                .color("red")
                .year(2018)
                .VIN("2345678956")
                .type("sedan")
                .build();
    }
}
