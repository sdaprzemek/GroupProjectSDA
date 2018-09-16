package pl.sda.serwer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.commons.entities.Car;
import pl.sda.commons.entities.Client;
import pl.sda.serwer.repositories.CarRepository;
import pl.sda.serwer.repositories.ClientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkShopService {

    private CarRepository carRepository;

    @Autowired
    public WorkShopService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Client> findTop3Clients() {
        return carRepository.orderClientsByCars().stream()
                .limit(3)
                .collect(Collectors.toList());
    }

    public Car findCarByVin(String vin) {
        return carRepository.findByVin(vin);
    }
}
