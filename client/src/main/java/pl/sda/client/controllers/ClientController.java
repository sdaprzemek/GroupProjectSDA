package pl.sda.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sda.client.fakeData.FakeData;
import pl.sda.client.services.ClientService;
import pl.sda.commons.entities.Client;


@Controller
public class ClientController {

    private static final java.util.logging.Logger log = java.util.logging
            .Logger.getLogger("ClientController");

    private FakeData fakeData;

    @Autowired
    ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
        this.fakeData = new FakeData();
    }

    //TODO
    @RequestMapping(value = "/show")
    @GetMapping
    public String showClientPage(Model model) {
        model.addAttribute("Client", fakeData.createFakeDataList());
        return "ShowClient";
    }

    //TODO
    @GetMapping
    public String ClientCar(Model model) {
        model.addAttribute("Car", fakeData.createCarList());
        return "ShowClient";
    }

    @RequestMapping(value = "/edit")
    @GetMapping
    public String editClientPage(Model model) {
        model.addAttribute("Client", new Client());
        return "EditClient";
    }

    //TODO
    @PostMapping(value = "/edited")
    public String editClient(Client client) {
        if (client.getClientID() != null) {
            log.info("Find by id");
            clientService.updateClient(client);
        } else if (!StringUtils.isEmpty(client.getLastName())) {
            log.info("Find by lastName");
        } else if (!StringUtils.isEmpty(client.getName())) {
            log.info("Find by name");
        }
        return "redirect:/list-to-edit";
    }

    //TODO
    @RequestMapping(value = "/list-to-edit")
    @GetMapping
    public String editListPage(Model model) {
        model.addAttribute("Client", fakeData.createFakeDataList());
        return "ShowClientToEdit";
    }

    @RequestMapping(value = "/create")
    @GetMapping
    public String createClientPage(Model model) {
        log.info("inside createClientPage class");
        model.addAttribute("Client", new Client());
        return "CreateClient";
    }

    @PostMapping(value = "/created")
    public String saveClient(Client client) {
        log.info(client.toString());
        return "redirect:/create";
    }

    @RequestMapping(value = "/delete")
    @GetMapping
    public String deleteClientPage(Model model) {
        log.info("inside deleteClientPage class");
        model.addAttribute("Client", new Client());
        return "DeleteClient";
    }

    //TODO
    @PostMapping(value = "/deleted")
    public String deleteClient(Client client) {
        log.info(client.toString());
        if (client.getClientID() != null) {
            log.info("Delete by ID");
        } else if (!StringUtils.isEmpty(client.getLastName())) {
            log.info("Delete by last name");
        } else if (!StringUtils.isEmpty(client.getName())) {
            log.info("Delete by name");
        }
        return "redirect:/delete";
    }
}
