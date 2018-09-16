package pl.sda.client.fakeData;

import pl.sda.commons.entities.Car;
import pl.sda.commons.entities.Client;

import java.util.ArrayList;
import java.util.List;

public class FakeData {

    public List<Client> createFakeDataList() {
        List<Client> list = new ArrayList<>();
        list.add(createFakeData());
        list.add(createFakeData());
        return list;
    }

    public List<Car> createCarList() {
        List<Car> carList = new ArrayList<>();
        carList.add(createClientCar());
        return carList;
    }

    private Car createClientCar() {
        Car car = new Car();
        car.setCarID(1);
        car.setColor("red");
        car.setMark("BMW");
        car.setType("E39");
        car.setYear(1999);
        return car;
    }

    private Client createFakeData() {
        Client client = new Client();
        client.setLastName("Kowalski");
        client.setName("Jan");
        client.setPhone(888999333L);
        client.setCars(createCarList());
        return client;
    }
}
