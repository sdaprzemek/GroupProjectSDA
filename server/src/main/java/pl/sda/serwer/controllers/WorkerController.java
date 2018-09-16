package pl.sda.serwer.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import pl.sda.commons.entities.Worker;
import pl.sda.serwer.services.ApiService;
import pl.sda.serwer.services.CacheService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/worker")
public class WorkerController implements ApiController<Worker> {

    private ApiService<Worker> workerService;
    private Gson converter;
    private static final String CACHE_KEY_PREFIX = "worker";

    @Autowired
    public WorkerController(@Qualifier("workerService") ApiService<Worker> workerService, Gson converter) {
        this.workerService = workerService;
        this.converter = converter;
    }

    @DeleteMapping("/delete")
    @Override
    @ResponseStatus(OK)
    public void delete(@RequestParam Integer id) {
        workerService.delete(id);
    }

    @PostMapping("/add")
    @Override
    @ResponseStatus(CREATED)
    public void add(@RequestBody Worker elem) {
        workerService.add(elem);
    }

    @PutMapping("/update")
    @Override
    @ResponseStatus(OK)
    public void update(@RequestBody Worker elem) {
        workerService.update(elem);
        if (CacheService.isCached(CACHE_KEY_PREFIX + elem.getWorkerID())) {
            CacheService.replace(CACHE_KEY_PREFIX + elem.getWorkerID(), elementToJson(elem));
        }
    }

    @GetMapping("/findAll")
    @Override
    @ResponseStatus(OK)
    public List<Worker> findAll() {
        return workerService.findAll();
    }

    @GetMapping("/countAll")
    @Override
    @ResponseStatus(OK)
    public Long count() {
        return workerService.count();
    }

    @GetMapping("/findById")
    @Override
    @ResponseStatus(OK)
    public Worker findById(@RequestParam Integer id) {
        if (CacheService.isCached(CACHE_KEY_PREFIX + id)) {
            return jsonToElement(CacheService.get(CACHE_KEY_PREFIX + id));
        } else {
            Worker worker = workerService.findById(id);
            CacheService.add(CACHE_KEY_PREFIX + id, elementToJson(worker));
            return worker;
        }
    }

    public String elementToJson(Worker worker) {
        return converter.toJson(worker);
    }

    public Worker jsonToElement(String jsonString) {
        return converter.fromJson(jsonString, Worker.class);
    }
}
