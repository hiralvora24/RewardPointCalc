package com.retailer.rewards.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.retailer.rewards.entity.Customer;
import com.retailer.rewards.model.Rewards;
import com.retailer.rewards.repository.CustomerRepository;
import com.retailer.rewards.service.RewardsService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RewardsControllerTest {

    @InjectMocks
    private RewardsController rewardsController;

    @Mock
    private RewardsService rewardsService;

    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Mock
    CustomerRepository customerRepository;

    private Gson gson;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders
                .standaloneSetup(rewardsController)
                .setMessageConverters(new GsonHttpMessageConverter()).build();
        objectMapper = new ObjectMapper();
        gson = new Gson();
    }

    @SneakyThrows
    @Test
    public void getRewardsByCustomerIdTest(){
        Customer customer = Customer.builder().customerId(1l).build();
        Rewards rewards = Rewards.builder().customerId(1l).totalRewards(101).lastMonthRewardPoints(90)
                .lastSecondMonthRewardPoints(20).lastThirdMonthRewardPoints(10).build();

        Mockito.when(customerRepository.findByCustomerId(1l)).thenReturn(customer);
        Mockito.when(rewardsService.getRewardsByCustomerId(1l)).thenReturn(rewards);

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get("/customers/{customerId}/rewards",1l)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(rewards)))
                .andReturn();
    }

    @SneakyThrows
    @Test
    public void getRewardsByCustomerIdWhenCustomerNotFoundTest(){
        Rewards rewards = Rewards.builder().customerId(1l).totalRewards(101).lastMonthRewardPoints(90)
                .lastSecondMonthRewardPoints(20).lastThirdMonthRewardPoints(10).build();

        Mockito.when(customerRepository.findByCustomerId(1l)).thenReturn(null);
        Mockito.when(rewardsService.getRewardsByCustomerId(1l)).thenReturn(rewards);

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders.get("/customers/{customerId}/rewards",1l)
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

}
