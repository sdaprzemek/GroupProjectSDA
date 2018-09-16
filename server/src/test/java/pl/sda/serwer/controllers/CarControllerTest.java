package pl.sda.serwer.controllers;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.sda.commons.entities.Car;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarControllerTest {

    @Autowired
    WebApplicationContext context;

    MockMvc mockMvc;

    @Before
    public void set() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void delete() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/car/delete?id=2"))
                .andExpect(status().isOk());
    }

    @Test
    public void add() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        Car testCar = Car.builder()
                .color("red")
                .mark("Honda")
                .type("Accord")
                .year(2003)
                .vin("16584681368")
                .build();

        Gson gson = new Gson();
        String jsonInString = gson.toJson(testCar);

        mockMvc.perform(MockMvcRequestBuilders.post("/car/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(jsonInString))
                .andExpect(status().isCreated());
    }

    @Ignore // TODO please fix it
    @Test
    public void update() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        mockMvc.perform(MockMvcRequestBuilders.put("/car/update"))
                .andExpect(status().isOk());

    }

    @Test
    public void findAll() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        mockMvc.perform(get("/car/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void count() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        mockMvc.perform(get("/car/count"))
                .andExpect(status().isOk());
    }

    @Test
    public void findById() throws Exception {

        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        mockMvc.perform(get("/car/findById?id=1"))
                .andExpect(status().isOk());

    }
    @Test
    public void findByVin() throws Exception{
        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        mockMvc.perform(get("/workshop/findByVin?vin=KNADH4A32A6350851"))
                .andExpect(status().isOk());
    }
}