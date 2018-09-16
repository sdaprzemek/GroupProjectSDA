package pl.sda.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;
import pl.sda.client.controllers.ClientController;
import pl.sda.client.fakeData.FakeData;
import pl.sda.commons.entities.Client;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientApplicationTests {

    @Autowired
    ClientController controller;

    @Autowired
    Environment environment;

    @Test
    public void testPropertyLoader() {
        assert environment.getProperty("1") != null;
    }


    @Autowired
    WebApplicationContext context;

    @Test
    public void ClientDeleterTest(Client client){
        FakeData fakeData = new FakeData();
        fakeData.createFakeDataList();
        controller.deleteClient(client);

    }

}
