package pl.sda.serwer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.commons.entities.Worker;
import pl.sda.serwer.exceptions.ElementNotFoundException;
import pl.sda.serwer.exceptions.ElementWithSuchIdExistException;
import pl.sda.serwer.repositories.WorkerRepository;

import java.util.List;

@Service
public class WorkerService implements ApiService<Worker> {
    private WorkerRepository workerRepository;

    @Autowired
    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public void delete(Integer id) {
        Worker worker = findById(id);
        workerRepository.delete(worker);
    }

    @Override
    public void add(Worker elem) {
        if (workerRepository.findByWorkerID(elem.getWorkerID()) == null) {
            workerRepository.save(elem);
        } else {
            throw new ElementWithSuchIdExistException("There is worker with id: "+elem.getWorkerID());
        }
    }

    @Override
    public void update(Worker updatedWorker) {
        if (workerRepository.findById(updatedWorker.getWorkerID()).isPresent()) {
            workerRepository.save(updatedWorker);
        } else {
            throw new ElementNotFoundException("No worker with such ID found");
        }
    }

    @Override
    public List<Worker> findAll() {
        return workerRepository.findAll();
    }

    @Override
    public Long count() {
        return workerRepository.count();
    }

    @Override
    public Worker findById(Integer id) {
        if (workerRepository.findById(id).isPresent()) {
            return workerRepository.findById(id).get();
        } else {
            throw new ElementNotFoundException("ID not found");
        }
    }
}
