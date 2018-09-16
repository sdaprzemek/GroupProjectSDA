package pl.sda.client.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.commons.entities.Worker;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WorkerController {

    //localhost:8080/worker/find/1
    @GetMapping("worker/find/{id}")
    public String findByPath(@PathVariable Integer id, Model model) {
        model.addAttribute("worker", createFakeData());
        return "worker";
    }

    //localhost:8080/worker/find?id=1
    @GetMapping("worker/find")
    public String findByParam(@RequestParam Integer id, Model model) {
        model.addAttribute("worker", createFakeData());
        return "worker";
    }

    //localhost:8080/worker/findAll
    @GetMapping("worker/findall")
    public String findAll(Model model) {
        model.addAttribute("workerList", createFakeDataList());
        return "workerList";
    }

    List<Worker> createFakeDataList() {
        List<Worker> list = new ArrayList<>();
        list.add(createFakeData());
        list.add(createFakeData());
        return list;
    }

    private Worker createFakeData() {
        return Worker.builder().workerID(123).name("Jan").lastName("Kowalski").position("123").build();
    }
}

