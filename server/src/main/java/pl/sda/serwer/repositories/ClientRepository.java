package pl.sda.serwer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.commons.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Client findByClientID(Integer id);
}
