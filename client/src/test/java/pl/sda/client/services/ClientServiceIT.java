package pl.sda.client.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import pl.sda.commons.entities.Client;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceIT {

    @Autowired
    private ClientService clientService;

    @Test
    public void addingNewClient() {

        //given
        long numberOfClientsBeforeAdding = clientService.count();

        Client p = new Client();
        p.setName("Genowefa");
        p.setLastName("Pączek");
        p.setPhone(505505505L);

        //when
        clientService.addClient(p);

        //then
        assertEquals(1, clientService.count() - numberOfClientsBeforeAdding);

    }

    @Test
    public void gettingListOfAllClients() {

        assertTrue(clientService.getAllClients().size() > 0);

    }

    @Test
    public void checkIfFindingClientIsCorrect() {

        assertEquals("Nola", clientService.findById(1).getName());

    }

    @Test
    public void tryToDeleteNonExistingClient() {

        try {
            clientService.deleteClient(120);
            fail();
        } catch (RestClientException e) {
            assertTrue(e.getMessage().contains("404"));
        }
    }

    @Test
    public void tryToUpdateClient() {

        //when
        assertEquals("Marleen", clientService.findById(2).getName());

        //when
        Client client = new Client();
        client.setClientID(2);
        client.setName("Marlena");
        client.setLastName("Jeavons");
        client.setPhone(6653128448L);

        clientService.updateClient(client);

        //then
        assertEquals("Marlena", clientService.findById(2).getName());
    }

}