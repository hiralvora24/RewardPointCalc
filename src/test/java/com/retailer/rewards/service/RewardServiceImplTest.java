package com.retailer.rewards.service;

import com.retailer.rewards.entity.Transaction;
import com.retailer.rewards.model.Rewards;
import com.retailer.rewards.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Timestamp;

import lombok.SneakyThrows;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RewardServiceImplTest {
     @Mock
     private TransactionRepository transactionRepository;
      @InjectMocks
      private RewardsServiceImpl rewardsService;

      @BeforeEach
      @SneakyThrows
      public void setUp() {     }

      @SneakyThrows
      @Test
      public void getRewardsByCustomerIdTest() {

          List<Transaction> lastMonthTransactions = new ArrayList<>();

          Transaction t = Transaction.builder().transactionId(10001l).customerId(1L)
            .transactionAmount(120L).transactionDate(Timestamp.valueOf("2022-09-12 10:04:56")).build();
          lastMonthTransactions.add(t);

          Mockito.when(transactionRepository.findAllByCustomerIdAndTransactionDateBetween(
                Mockito.anyLong(), Mockito.any(), Mockito.any()))
               .thenReturn(lastMonthTransactions);

          Rewards rewards = rewardsService.getRewardsByCustomerId(1L);

          assertEquals(rewards.getLastMonthRewardPoints(),90L);
      }

      @SneakyThrows
      @Test
      public void calculateRewardsWhenTransactionIsBetweenFirstAndSecondRewardAmountTest(){

        Transaction t = Transaction.builder().customerId(1L).transactionId(1L).transactionDate(Timestamp.valueOf("2022-09-12 10:04:56")).transactionAmount(120L).build();

        Long rewardAmount = rewardsService.calculateRewards(t);

        assertEquals(Optional.ofNullable(rewardAmount), Optional.ofNullable(90L));
      }

      @SneakyThrows
      @Test
      public void calculateRewardsWhenTransactionIsLessThenSecondRewardAmountTest(){

        Transaction t = Transaction.builder().customerId(1L).transactionId(1L).transactionDate(Timestamp.valueOf("2022-08-04 10:04:56")).transactionAmount(75L).build();
        Long rewardAmount = rewardsService.calculateRewards(t);

        assertEquals(Optional.ofNullable(rewardAmount), Optional.ofNullable(25L));
      }
}
