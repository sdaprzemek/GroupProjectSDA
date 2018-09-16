package pl.sda.serwer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.commons.entities.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {

    Worker findByWorkerID(Integer id);
}
