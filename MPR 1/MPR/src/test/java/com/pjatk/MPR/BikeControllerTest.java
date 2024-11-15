package com.pjatk.MPR;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BikeControllerTest {
    private MockMvc mockMvc;

    @Mock
    private BikeService service;

    @InjectMocks
    private BikeController controller;

    @BeforeEach
    public void setup()
    {
        this.mockMvc = MockMvcBuilders.standaloneSetup(
              new BikeExceptionHandler(), controller).build();

    }

    @Test
    public void getByIDReturns200WhemBikeIsPresent() throws Exception {
        Bike bike = new Bike("A", "Green");
        when(service.findByColor("Black")).thenReturn(List.of(bike));

        mockMvc.perform(MockMvcRequestBuilders.get("/bike/Black"))
                .andExpect(status().isOk());
    }





}