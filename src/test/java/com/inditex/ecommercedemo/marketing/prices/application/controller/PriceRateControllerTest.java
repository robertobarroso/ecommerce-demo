package com.inditex.ecommercedemo.marketing.prices.application.controller;

import com.inditex.ecommercedemo.marketing.prices.application.dto.PriceObjectMother;
import com.inditex.ecommercedemo.marketing.prices.application.dto.PriceQueryParamsMother;
import com.inditex.ecommercedemo.marketing.prices.application.mapper.PriceCriteriaDtoMapper;
import com.inditex.ecommercedemo.marketing.prices.application.mapper.PriceCriteriaDtoMapperImpl;
import com.inditex.ecommercedemo.marketing.prices.application.mapper.PriceDtoMapper;
import com.inditex.ecommercedemo.marketing.prices.application.mapper.PriceDtoMapperImpl;
import com.inditex.ecommercedemo.marketing.prices.application.usecase.find_rate.PriceRateSearcher;
import com.inditex.ecommercedemo.marketing.prices.domain.entity.Price;
import com.inditex.ecommercedemo.shared.domain.criteria.Criteria;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PriceRateController.class)
@Import({PriceCriteriaDtoMapperImpl.class, PriceDtoMapperImpl.class})
public class PriceRateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PriceCriteriaDtoMapper priceCriteriaDtoMapper;

    @Autowired
    private PriceDtoMapper priceDtoMapper;

    @MockBean
    private PriceRateSearcher priceRateSearcher;

    @Test
    public void test_is_ok_response_when_required_request_params_are_informed() throws Exception {
        // Prepare data mocked from database
        Price price = PriceObjectMother.dummy();
        when(priceRateSearcher.search(ArgumentMatchers.any(Criteria.class))).thenReturn(price);

        // Call method under test
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