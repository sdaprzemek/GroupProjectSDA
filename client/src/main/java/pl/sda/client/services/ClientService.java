package pl.sda.client.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.sda.commons.entities.Client;

import java.util.List;

import static java.util.Arrays.asList;

@Service
public class ClientService {

    @Value("${server.controller.client.url}")
    private String ROOT_URI;

    private RestTemplate restTemplate;

    @Autowired
    public ClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Client> getAllClients() {
        ResponseEntity<Client[]> response = restTemplate.getForEntity(ROOT_URI + "/findAll", Client[].class);
        return asList(response.getBody());
    }

    public Client findById(Integer id) {
        ResponseEntity<Client> response = restTemplate.getForEntity(ROOT_URI + "/findById?id=" + id, Client.class);
        return response.getBody();
    }

    public HttpStatus addClient(Client client) {
        ResponseEntity<HttpStatus> response = restTemplate.postForEntity(ROOT_URI + "/add", client, HttpStatus.class);
        return response.getBody();
    }

    public void updateClient(Client client) {
        restTemplate.put(ROOT_URI + "/update", client);
    }

    public void deleteClient(Integer id) {
        restTemplate.delete(ROOT_URI + "/delete?id=" + id);
    }

    public Long count() {
        ResponseEntity<Long> response = restTemplate.getForEntity(ROOT_URI + "/count", Long.class);
        return response.getBody();
    }

}