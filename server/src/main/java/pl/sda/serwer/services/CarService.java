package pl.sda.serwer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.commons.entities.Car;
import pl.sda.commons.entities.Client;
import pl.sda.serwer.exceptions.ElementNotFoundException;
import pl.sda.serwer.exceptions.ElementWithSuchIdExistException;
import pl.sda.serwer.repositories.CarRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService implements ApiService<Car>{

    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void delete(Integer id) {
        Car car = findById(id);
        carRepository.delete(car);
    }

    @Override
    public void add(Car elem) {
        if (carRepository.findByCarID(elem.getCarID()) == null) {
            carRepository.save(elem);
        } else {
            throw new ElementWithSuchIdExistException("There is car with id: "+elem.getCarID());
        }
    }

    @Override
    public void update(Car elem) {
        if (findById(elem.getCarID()) != null) {
            carRepository.save(elem);
        }
    }

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Long count() {
        return carRepository.count();
    }

    @Override
    public Car findById(Integer id) {
        Car car = carRepository.findByCarID(id);
        if (car == null) {
            throw new ElementNotFoundException("There is no car with that id: " + id);
        }
        return car;
    }
}
