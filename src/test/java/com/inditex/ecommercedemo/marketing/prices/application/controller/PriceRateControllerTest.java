package com.inditex.ecommercedemo.marketing.prices.application.controller;

import com.inditex.ecommercedemo.marketing.prices.application.dto.PriceQueryParamsMother;
import com.inditex.ecommercedemo.marketing.prices.application.usecase.find_rate.PriceRateSearcher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PriceRateController.class)
public class PriceRateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceRateSearcher priceRateSearcher;

    @Test
    public void test_is_ok_response_when_required_request_params_are_informed() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                      .get("/prices/rates")
                      .params(PriceQueryParamsMother.dummy())
                      .accept(MediaType.APPLICATION_JSON))
              .andDo(print())
              .andExpect(status().isOk())
              .andExpect(MockMvcResultMatchers.jsonPath("$.rateId").exists())
              .andExpect(MockMvcResultMatchers.jsonPath("$.productId").exists())
              .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").exists())
              .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").exists())
              .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").exists())
              .andExpect(MockMvcResultMatchers.jsonPath("$.value").exists())
              .andExpect(MockMvcResultMatchers.jsonPath("$.currency").exists());
    }
 
}