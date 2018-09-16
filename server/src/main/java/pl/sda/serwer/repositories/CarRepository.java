package pl.sda.serwer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.sda.commons.entities.Car;
import pl.sda.commons.entities.Client;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    Car findFirstByMark(String mark);

    Car findByCarID(Integer id);

    Car findByVin(String vin);

    @Query("SELECT cl FROM Car c JOIN c.client cl group by cl order by COUNT(c) DESC")
    List<Client> orderClientsByCars();

}