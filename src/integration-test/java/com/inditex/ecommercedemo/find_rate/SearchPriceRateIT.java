package com.inditex.ecommercedemo.find_rate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SearchPriceRateIT {

    @Autowired
    private MockMvc mockMvc;

    /*
    Get price rate given the following criteria:
    date: 2020-06-14T10:00:00
    productId: 35455
    brandId: 1

    Expected: RateId 1

    Method name pattern: test{number}_{criteria]
    {criteria}={date}_{time}_{productId}_{brandId}
    */
    @Test
    public void test1_20200614_1000_35455_1() throws Exception {
        // Prepare query params
        LinkedMultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("date", "2020-06-14T10:00:00");
        queryParams.add("productId", "35455");
        queryParams.add("brandId", "1");

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/prices/rates")
                        .params(queryParams)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rateId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value("35455"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2020-06-14T00:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value("2020-12-31T23:59:59"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value").value("35.50"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("EUR"));
    }

    /*
    Get price rate given the following criteria:
    date: 2020-06-14T16:00:00
    productId: 35455
    brandId: 1

    Expected: RateId 2

    Method name pattern: test{number}_{criteria]
    {criteria}={date}_{time}_{productId}_{brandId}
    */
    @Test
    public void test2_20200614_1600_35455_1() throws Exception {
        // Prepare query params
        LinkedMultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("date", "2020-06-14T16:00:00");
        queryParams.add("productId", "35455");
        queryParams.add("brandId", "1");

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/prices/rates")
                        .params(queryParams)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rateId").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value("35455"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2020-06-14T15:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value("2020-06-14T18:30:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value").value("25.45"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("EUR"));
    }

    /*
    Get price rate given the following criteria:
    date: 2020-06-14T21:00:00
    productId: 35455
    brandId: 1

    Expected: RateId 1

    Method name pattern: test{number}_{criteria]
    {criteria}={date}_{time}_{productId}_{brandId}
    */
    @Test
    public void test3_20200614_2100_35455_1() throws Exception {
        // Prepare query params
        LinkedMultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("date", "2020-06-14T21:00:00");
        queryParams.add("productId", "35455");
        queryParams.add("brandId", "1");

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/prices/rates")
                        .params(queryParams)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rateId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value("35455"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2020-06-14T00:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value("2020-12-31T23:59:59"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value").value("35.50"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("EUR"));
    }

    /*
    Get price rate given the following criteria:
    date: 2020-06-15T10:00:00
    productId: 35455
    brandId: 1

    Expected: RateId 3

    Method name pattern: test{number}_{criteria]
    {criteria}={date}_{time}_{productId}_{brandId}
    */
    @Test
    public void test4_20200615_1000_35455_1() throws Exception {
        // Prepare query params
        LinkedMultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("date", "2020-06-15T10:00:00");
        queryParams.add("productId", "35455");
        queryParams.add("brandId", "1");

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/prices/rates")
                        .params(queryParams)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rateId").value("3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value("35455"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2020-06-15T00:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value("2020-06-15T11:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value").value("30.50"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("EUR"));
    }

    /*
    Get price rate given the following criteria:
    date: 2020-06-16T21:00:00
    productId: 35455
    brandId: 1

    Expected: RateId 4

    Method name pattern: test{number}_{criteria]
    {criteria}={date}_{time}_{productId}_{brandId}
    */
    @Test
    public void test5_20200616_2100_35455_1() throws Exception {
        // Prepare query params
        LinkedMultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("date", "2020-06-16T21:00:00");
        queryParams.add("productId", "35455");
        queryParams.add("brandId", "1");

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/prices/rates")
                        .params(queryParams)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.rateId").value("4"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productId").value("35455"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.brandId").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startDate").value("2020-06-15T16:00:00"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endDate").value("2020-12-31T23:59:59"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.value").value("38.95"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currency").value("EUR"));
    }
}
