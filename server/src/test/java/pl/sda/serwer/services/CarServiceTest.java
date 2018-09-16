package pl.sda.serwer.services;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.commons.entities.Car;
import pl.sda.commons.entities.Client;
import pl.sda.serwer.exceptions.ElementNotFoundException;
import pl.sda.serwer.repositories.CarRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceTest {

    @Autowired
    private CarService carService;

    @Autowired
    private WorkShopService workShopService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void add() {
        carService.add(Car.builder()
                .mark("Toyota")
                .type("Corolla")
                .year(2010)
                .color("white")
                .vin("GSDAGFSDA472875")
                .build());
    }

    @Test(expected = ElementNotFoundException.class)
    public void delete() {
        carService.delete(1000);
    }

    @Test
    public void update() {
        Car car = Car.builder()
                .carID(1)
                .mark("Toyota")
                .type("Corolla")
                .year(2010)
                .color("white")
                .vin("GSDAGFSDA472875")
                .build();
        carService.update(car);
        Car updatedCar = carService.findById(car.getCarID());
        Assert.assertEquals(car.getCarID(), updatedCar.getCarID());
    }

    @Test
    public void isTop3ClientListCorrect() {
        //given
        List<Integer> results = new ArrayList<>();
        results.add(2);
        results.add(3);
        results.add(1);
        //when
        List<Client> top3Clients = workShopService.findTop3Clients();
        //then
        List<Integer> collect = top3Clients.stream().map(Client::getClientID).collect(Collectors.toList());
        Assert.assertTrue(CollectionUtils.containsAll(collect, results));
    }

    @Test
    public void isCarFoundByVIN() {
        //when
        Car car = workShopService.findCarByVin("KNADH4A32A6350851");
        //then
        Assert.assertEquals(1, Integer.toUnsignedLong(car.getCarID()));
    }
}