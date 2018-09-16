package pl.sda.serwer.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.commons.entities.Car;
import pl.sda.commons.entities.Client;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ApplicationContext ctx;

    @Test
    public void whenGetContextReturnNotNull() {
        assertNotNull(ctx);
    }

    @Test
    public void whenCountThenReturnNumberOfCars(){
        assertEquals(20, carRepository.count());
    }

    @Test
    public void whenFindByMarkThenReturnCar(){
        //given
        Car car = Car.builder()
                .carID( 1 )
                .mark("Toyota")
                .type("Corolla")
                .year(2010)
                .color("white")
                .vin("FDASGF634634")
                .build();
        carRepository.save(car);
        //when
        Car carFromDB = carRepository.findFirstByMark("Toyota");
        //then
        assertEquals("Toyota", car.getMark());
        assertEquals("Toyota", carFromDB.getMark());
    }


    @Test
    public void countCarGroupByClientTest() {
        //given
        carRepository.orderClientsByCars().forEach(System.out::println);

        //when
        List<Client> clients = carRepository.orderClientsByCars();

        //then
        // first
        assertEquals("2", clients.get(0).getClientID().toString());
        //last
        assertEquals("4", clients.get(3).getClientID().toString());

    }


}