package com.example.CabBookingMicroservice;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.CabBookingMicroservice.config.FeignService;
import com.example.CabBookingMicroservice.controller.CabBookingRestController;
import com.example.CabBookingMicroservice.model.CabBooking;
import com.example.CabBookingMicroservice.service.CabBookingService;

public class CabBookingRestControllerTest {
	private MockMvc mockMvc;

    @Mock
    private FeignService feignService;

    @Mock
    private CabBookingService cabBookingService;

    @InjectMocks
    private CabBookingRestController cabBookingRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cabBookingRestController).build();
    }

    @Test
    void testGetCabType() throws Exception {
        // Arrange
        Map<String, String> formData = new HashMap<>();
        formData.put(CabBookingService.FROM_LOCATION, "San Ramon");
        formData.put(CabBookingService.To_LOCATION, "San Francisco");
        formData.put("cabType", "Economy");

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("fare", "100");

        when(feignService.calculateFare("San Ramon", "San Francisco", "Economy"))
                .thenReturn(new ResponseEntity<>(responseMap, HttpStatus.OK));

        // Act & Assert
        mockMvc.perform(post("/cabBooking/cabType")
                        .contentType("application/json")
                        .content("{\"fromLocation\": \"San Ramon\", \"toLocation\": \"San Francisco\", \"cabType\": \"Economy\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.fare", is("100")));

        verify(feignService, times(1)).calculateFare("San Ramon", "San Francisco", "Economy");
    }

    

    @Test
    void testGetAllCabsFare() throws Exception {
        // Arrange
        Map<String, String> formData = new HashMap<>();
        formData.put(CabBookingService.FROM_LOCATION, "San Ramon");
        formData.put(CabBookingService.To_LOCATION, "San Francisco");

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("Economy", "100");
        responseMap.put("Luxury", "200");

        when(feignService.displayAllCabsFare("San Ramon", "San Francisco"))
                .thenReturn(new ResponseEntity<>(responseMap, HttpStatus.OK));

        // Act & Assert
        mockMvc.perform(post("/cabBooking/displayFare")
                        .contentType("application/json")
                        .content("{\"fromLocation\": \"San Ramon\", \"toLocation\": \"San Francisco\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Economy", is("100")))
                .andExpect(jsonPath("$.Luxury", is("200")));

        verify(feignService, times(1)).displayAllCabsFare("San Ramon", "San Francisco");
    }
}
