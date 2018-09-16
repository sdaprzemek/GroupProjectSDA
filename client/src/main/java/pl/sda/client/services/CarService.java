package pl.sda.client.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.sda.commons.entities.Car;

import java.util.Arrays;
import java.util.List;

@Service
public class CarService {

    @Value("${server.controller.car.url}")
    private String ROOT_URI;
    private RestTemplate restTemplate;

    @Autowired
    public CarService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void delete(Integer id) {
        restTemplate.delete(ROOT_URI + "/delete?id=" + id);
    }

    public HttpStatus add(Car car) {
        ResponseEntity<HttpStatus> response = restTemplate.postForEntity(ROOT_URI + "/add", car, HttpStatus.class);
        return response.getBody();
    }

    public void update(Car car) {
        restTemplate.put(ROOT_URI + "/update", car);
    }

    public List<Car> findAll() {
        ResponseEntity<Car[]> response = restTemplate.getForEntity(ROOT_URI + "/all", Car[].class);
        return Arrays.asList(response.getBody());
    }

    public Long count() {
        ResponseEntity<Long> response = restTemplate.getForEntity(ROOT_URI + "/count", Long.class);
        return response.getBody();
    }

    public Car findById(Integer id) {
        ResponseEntity<Car> response = restTemplate.getForEntity(ROOT_URI + "/findById?id=" + id, Car.class);
        return response.getBody();
    }

    public Car findByVIN(String vin) {
        ResponseEntity<Car> response = restTemplate.getForEntity(ROOT_URI + "/findvin?vin=" + vin, Car.class);
        return response.getBody();
    }

}
