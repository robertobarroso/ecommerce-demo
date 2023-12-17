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
import com.inditex.ecommercedemo.shared.domain.exception.BusinessException;
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
    public void testIsOkResponse_whenRequiredParametersAreInformed() throws Exception {
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
    public void testIsBadResponse_whenRequiredParametersAreAvoid() throws Exception {
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

    @Test
    public void testIsBadResponse_whenBusinessExceptionIsThrown() throws Exception {
        // When search method is called, simulate an error throwing BusinessException
        when(priceRateSearcher.search(ArgumentMatchers.any(Criteria.class))).thenThrow(new BusinessException(BusinessErrorCode.BE0002.getCode(), BusinessErrorCode.BE0002.getMessage()));

        // Call method under test
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/prices/rates")
                        .params(PriceQueryParamsMother.dummy())
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(BusinessErrorCode.BE0002.getCode()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").isNotEmpty());
    }
 
}