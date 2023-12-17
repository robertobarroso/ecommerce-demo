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
import com.inditex.ecommercedemo.shared.domain.exception.BusinessErrorCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(SpringExtension.class)
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
    public void test_is_ok_response_when_required_parameters_are_informed() throws Exception {
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
              .andExpect(MockMvcResultMatchers.jsonPath("$.rateId").isNotEmpty())
              .andExpect(MockMvcResultMatchers.jsonPath("$.productId").isNotEmpty())
              .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").isNotEmpty())
              .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").isNotEmpty())
              .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").isNotEmpty())
              .andExpect(MockMvcResultMatchers.jsonPath("$.value").isNotEmpty())
              .andExpect(MockMvcResultMatchers.jsonPath("$.currency").isNotEmpty());
    }

    @Test
    public void test_is_bad_response_when_required_parameters_are_avoid() throws Exception {
        // Call method under test
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/prices/rates")
                        .params(PriceQueryParamsMother.incompleteWithoutBrandId())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(BusinessErrorCode.BE0001.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.details ").isNotEmpty());
    }
 
}