package com.usaa.cars;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
public class CarControllerTests {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CarService carService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getCarDetails_exists_returnCar() throws Exception {
        // Arrange
        when(carService.getCarDetails(anyString())).thenReturn(new Car("prius", "hybrid"));
        // Act
        mockMvc.perform(get("/cars/prius"))
                // Assert
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("prius"))
                .andExpect(jsonPath("type").value("hybrid"));
    }

    @Test
    void getCarDetails_notExists_returnNoContent() throws Exception {
        when(carService.getCarDetails(anyString())).thenThrow(new CarNotFoundException());

        mockMvc.perform(get("/cars/prius"))
                .andExpect(status().isNoContent());
    }

    @Test
    void addCar_returnsCar() throws Exception {
        Car car = new Car("rogue", "xover");

        when(carService.addCar(any(Car.class))).thenReturn(car);

        mockMvc.perform(post("/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(car)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("rogue"))
                .andExpect(jsonPath("type").value("xover"));
    }
}
