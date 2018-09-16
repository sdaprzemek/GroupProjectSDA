package pl.sda.client.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.commons.SaveCSVFile;
import pl.sda.commons.SaveExcelFile;
import pl.sda.commons.SaveFile;
import pl.sda.commons.SavePdfFile;
import pl.sda.commons.entities.Car;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceIT {

    @Autowired
    CarService carService;
    private Car testCar;

    public Car createCar() {
        testCar = Car.builder()
                .mark("VOLVO").type("V70")
                .color("blue").year(2100)
                .vin("12345678900").build();
        return testCar;
    }

    @Test
    public void addNewCarToDBTest() {
        //given
        long countBeforeAdd = carService.count();
        Car testCar = createCar();

        //when
        carService.add(testCar);

        //then
        Assert.assertEquals(countBeforeAdd, carService.count() - 1L);
    }

    @Test
    public void deleteTest() {
        //given
        Long countBefore = carService.count();

        //when
        carService.delete(1);
        Long countAfter = carService.count();

        //then
        Assert.assertTrue(countBefore > countAfter);
    }

    @Test
    public void updateTest() {
        //TODO implement, but methods in server are complicated, to update you must give whole new object Car with all properties
    }

    @Test
    public void findAllTest() {
        Assert.assertEquals(true, carService.findAll().size() == Math.toIntExact(carService.count()));
    }

    @Test
    public void countTest() {
        //given
        int size = carService.findAll().size();
        int count = Math.toIntExact(carService.count());

        Assert.assertTrue( size == count);
    }

    @Test
    public void findByIdTest() {
        //given
        Car testCar = carService.findById(2);
        //then
        Assert.assertEquals("SALVN2BG0FH316384", testCar.getVin());
    }

    @Test
    public void saveCarsFromDBToPdfFile() throws IOException {
        new SavePdfFile().save(carService.findAll(),"raports/cars");
        new SaveCSVFile().save(carService.findAll(), "raports/cars");
    }
}
