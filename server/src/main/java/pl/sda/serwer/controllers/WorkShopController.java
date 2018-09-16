package pl.sda.serwer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.sda.commons.entities.Car;
import pl.sda.commons.entities.Client;
import pl.sda.serwer.services.WorkShopService;
import java.util.List;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/workshop")
public class WorkShopController {

    private WorkShopService workShopService;

    @Autowired
    public WorkShopController(WorkShopService workShopService) {
        this.workShopService = workShopService;
    }

    @GetMapping("/findTop3Clients")
    @ResponseStatus(OK)
    public List<Client> findTop3Clients() {
        return workShopService.findTop3Clients();
    }

   @GetMapping("/findByVin")
    @ResponseStatus(OK)
    public Car findCarByVIN(@RequestParam String vin){
        return workShopService.findCarByVin(vin);
    }
}
