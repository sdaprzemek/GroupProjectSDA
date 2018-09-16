package pl.sda.serwer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.sda.commons.entities.Car;
import pl.sda.commons.entities.Client;
import pl.sda.commons.entities.Worker;
import pl.sda.serwer.repositories.CarRepository;
import pl.sda.serwer.repositories.ClientRepository;
import pl.sda.serwer.repositories.WorkerRepository;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class SimpleDataToH2 {

    private CarRepository carRepository;
    private ClientRepository clientsRepositories;
    private WorkerRepository workerRepository;
    private String isSqlMode;

    @Autowired
    public SimpleDataToH2(CarRepository carRepository,
                          ClientRepository clientsRepositories,
                          WorkerRepository workerRepository,
                          @Value("${spring.jpa.hibernate.ddl-auto}") String isSqlMode) {
        this.carRepository = carRepository;
        this.clientsRepositories = clientsRepositories;
        this.workerRepository = workerRepository;
        this.isSqlMode = isSqlMode;
    }

    @PostConstruct
    public void repositoriesFillIn() {
        if (!isSqlMode.equals("none")) {
            Client client1 = Client.builder()
                    .name("Pioter")
                    .lastName("Sroter")
                    .phone(15648623L)
                    .build();

            Client client2 = Client.builder()
                    .name("Janusz")
                    .lastName("Sreanusz")
                    .phone(1564865456L)
                    .build();

            Client client3 = Client.builder()
                    .name("Uwolnic")
                    .lastName("Orke")
                    .phone(156546365L)
                    .build();
            Car car1 = Car.builder()
                    .color("yellow")
                    .mark("Opel")
                    .type("Kadet")
                    .vin("894966498168496846")
                    .year(2003)
                    .client(client1)
                    .build();

            Car car2 = Car.builder()
                    .color("green")
                    .mark("Tesla")
                    .type("Model3")
                    .vin("89496668468496846")
                    .year(2018)
                    .client(client2)
                    .build();

            Car car3 = Car.builder()
                    .color("black")
                    .mark("Wolkswagen")
                    .type("Passat")
                    .vin("894966498168496846")
                    .year(2003)
                    .client(client2)
                    .build();
            Car car4 = Car.builder()
                    .color("white")
                    .mark("Toyota")
                    .type("Corolla")
                    .vin("8949664FESG46")
                    .year(2011)
                    .client(client3)
                    .build();

            Worker worker1 = Worker.builder()
                    .name("Zenon")
                    .lastName("Brzeczyszczykiewicz")
                    .position("Konserwator powierzchni plaskich (manager)")
                    .cars(Arrays.asList(car1, car2))
                    .build();

            Worker worker2 = Worker.builder()
                    .name("Thor")
                    .lastName("Gromowladny")
                    .position("Coffee machine manager")
                    .cars(Arrays.asList(car1, car2, car3))
                    .build();

            Worker worker3 = Worker.builder()
                    .name("Zulu")
                    .lastName("Gula")
                    .position("Specjalista ds Blahych")
                    .cars(Arrays.asList(car1, car2, car3, car4))
                    .build();

            clientsRepositories.saveAll(Arrays.asList(client1, client2, client3));
            carRepository.saveAll(Arrays.asList(car1, car2, car3, car4));
            workerRepository.saveAll(Arrays.asList(worker1, worker2, worker3));
        }


    }
}
