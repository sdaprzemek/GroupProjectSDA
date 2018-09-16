package pl.sda.serwer.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import pl.sda.commons.entities.Client;
import pl.sda.serwer.services.ApiService;
import pl.sda.serwer.services.CacheService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/client")
public class ClientController implements ApiController<Client> {

    private ApiService<Client> clientService;
    private Gson converter;
    private static final String CACHE_KEY_PREFIX = "client";

    @Autowired
    public ClientController(@Qualifier("clientService") ApiService<Client> clientService, Gson converter) {
        this.clientService = clientService;
        this.converter = converter;
    }

    @Override
    @DeleteMapping("/delete")
    @ResponseStatus(OK)
    public void delete(@RequestParam Integer id) {
        clientService.delete(id);
    }

    @Override
    @PostMapping("/add")
    @ResponseStatus(CREATED)
    public void add(@RequestBody Client elem) {
        clientService.add(elem);
    }

    @Override
    @PutMapping("/update")
    @ResponseStatus(OK)
    public void update(@RequestBody Client elem) {
        clientService.update(elem);
        if (CacheService.isCached(CACHE_KEY_PREFIX + elem.getClientID())) {
            CacheService.replace(CACHE_KEY_PREFIX + elem.getClientID(), elementToJson(elem));
        }
    }

    @Override
    @GetMapping("/findAll")
    @ResponseStatus(OK)
    public List<Client> findAll() {
        return clientService.findAll();
    }

    @Override
    @GetMapping("/count")
    @ResponseStatus(OK)
    public Long count() {
        return clientService.count();
    }

    @Override
    @GetMapping("/findById")
    @ResponseStatus(OK)
    public Client findById(@RequestParam Integer id) {
        if (CacheService.isCached(CACHE_KEY_PREFIX + id)) {
            return jsonToElement(CacheService.get(CACHE_KEY_PREFIX + id));
        } else {
            Client client = clientService.findById(id);
            CacheService.add(CACHE_KEY_PREFIX + id, elementToJson(client));
            return client;
        }
    }

    public String elementToJson(Client client) {
        return converter.toJson(client);
    }

    public Client jsonToElement(String jsonString) {
        return converter.fromJson(jsonString, Client.class);
    }
}
