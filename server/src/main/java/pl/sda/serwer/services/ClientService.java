package pl.sda.serwer.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.commons.entities.Client;
import pl.sda.serwer.exceptions.ElementNotFoundException;
import pl.sda.serwer.exceptions.ElementWithSuchIdExistException;
import pl.sda.serwer.repositories.ClientRepository;

import java.util.List;

@Service
@Slf4j
public class ClientService implements ApiService<Client> {

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void delete(Integer id) {
        Client client = findById(id);
        clientRepository.delete(client);
    }

    @Override
    public void add(Client elem) {
        if (clientRepository.findByClientID(elem.getClientID()) == null) {
            clientRepository.save(elem);
        } else {
            throw new ElementWithSuchIdExistException("There is client with id: "+elem.getClientID());
        }
    }

    @Override
    public void update(Client elem) {
        if (doesClientExistInDB(elem.getClientID())) {
            clientRepository.save(elem);
        } else {
            throw new ElementNotFoundException("Cannot do update, client of given id: " + elem.getClientID() + " not found.");
        }
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Long count() {
        return clientRepository.count();
    }

    @Override
    public Client findById(Integer id) {
        Client client = clientRepository.findByClientID(id);
        if (client == null) {
            throw new ElementNotFoundException("Could not find user with given id: " + id);
        }
        return client;
    }

    private boolean doesClientExistInDB(Integer id) {
        boolean isTrue = false;
        try {
            isTrue = findById(id) != null;
        } catch (ElementNotFoundException e) {
            log.error("Could not find user with given id: " + id);
        }
        return isTrue;
    }
}
