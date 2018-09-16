package pl.sda.serwer.services;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.sda.commons.entities.Client;
import pl.sda.serwer.exceptions.ElementNotFoundException;
import pl.sda.serwer.repositories.ClientRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    @Test
    public void add() {
        clientService.add(Client.builder()
                .name("Maniek")
                .lastName("Kowalski")
                .phone(456987258L)
                .build());
    }

    @Test(expected = ElementNotFoundException.class)
    public void delete() {
        clientService.delete(1000);
    }

    @Ignore // doesn't make sense because you may have add called before count and it will
    // change your size
    @Test
    public void count() {
        long count = clientRepository.count();
        Assert.assertEquals(4, count);
    }

    @Test
    public void update() {
        //given
        Client client = Client.builder()
                .clientID(4)
                .name("Nola")
                .lastName("Collumbine")
                .phone(2386566503L)
                .build();

        //when
        clientService.update(client);
        Client updatedClient = clientService.findById(client.getClientID());

        //then
        Assert.assertEquals(client.getName(), updatedClient.getName());
    }
}