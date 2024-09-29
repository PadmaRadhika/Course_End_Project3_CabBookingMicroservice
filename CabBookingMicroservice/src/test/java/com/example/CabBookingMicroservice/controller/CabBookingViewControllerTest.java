package com.example.CabBookingMicroservice.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.CabBookingMicroservice.model.CabBooking;
import com.example.CabBookingMicroservice.service.CabBookingService;

public class CabBookingViewControllerTest {
	private MockMvc mockMvc;

    @Mock
    private CabBookingService cabBookingService;

    @InjectMocks
    private CabBookingViewController cabBookingViewController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cabBookingViewController).build();
    }

    @Test
    void testHome() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void testShowCabBookingForm() throws Exception {
        mockMvc.perform(get("/cabBooking"))
                .andExpect(status().isOk())
                .andExpect(view().name("book-cab"))
                .andExpect(model().attributeExists("cabBooking"));
    }

    @Test
    void testShowAllCabsFareForm() throws Exception {
        mockMvc.perform(get("/calculateAllCabsFare"))
                .andExpect(status().isOk())
                .andExpect(view().name("calculate-fare"));
    }

   
}
