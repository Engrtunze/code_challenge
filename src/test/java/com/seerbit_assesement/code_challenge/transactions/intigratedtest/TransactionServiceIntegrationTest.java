package com.seerbit_assesement.code_challenge.transactions.intigratedtest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.seerbit_assesement.code_challenge.transactions.dto.TransactionRequest;
import com.seerbit_assesement.code_challenge.transactions.model.Transaction;
import com.seerbit_assesement.code_challenge.transactions.services.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class TransactionServiceIntegrationTest {

    @Autowired
    private  MockMvc mockMvc;

    @Autowired
    private  TransactionService transactionService;
    @Autowired
    private  ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        transactionService.deleteTransactions();
    }

    @Test
     void testAddTransaction() throws Exception {
        TransactionRequest request = new TransactionRequest();
        request.setAmount("10.50");
        request.setTimeStamp(ZonedDateTime.now().toString());

        mockMvc.perform(MockMvcRequestBuilders.post("/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        List<Transaction> transactions = transactionService.getAllTransaction();
        assertEquals(1, transactions.size());
        assertEquals(new BigDecimal("10.50"), transactions.get(0).getAmount());
    }

    @Test
    void testGetAllTransaction() throws Exception {
        TransactionRequest request1 = new TransactionRequest();
        request1.setAmount("10.50");
        request1.setTimeStamp(ZonedDateTime.now().toString());

        TransactionRequest request2 = new TransactionRequest();
        request2.setAmount("20.00");
        request2.setTimeStamp(ZonedDateTime.now().toString());

        mockMvc.perform(MockMvcRequestBuilders.post("/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request1)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request2)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.get("/transaction")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    void testGetStatistics() throws Exception {
        TransactionRequest request1 = new TransactionRequest();
        request1.setAmount("10.50");
        request1.setTimeStamp(ZonedDateTime.now().toString());

        TransactionRequest request2 = new TransactionRequest();
        request2.setAmount("20.00");
        request2.setTimeStamp(ZonedDateTime.now().toString());

        mockMvc.perform(MockMvcRequestBuilders.post("/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request1)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.post("/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request2)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.get("/transaction/statistics")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.sum").value(BigDecimal.valueOf(30.50)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.max").value(BigDecimal.valueOf(20.00)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.min").value(BigDecimal.valueOf(10.50)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.avg").value(BigDecimal.valueOf(15.25)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.count").value(2));
    }

    @Test
    void testDeleteTransaction() throws Exception {
        TransactionRequest request = new TransactionRequest();
        request.setAmount("10.50");
        request.setTimeStamp(ZonedDateTime.now().toString());

        mockMvc.perform(MockMvcRequestBuilders.post("/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders.delete("/transaction")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        mockMvc.perform(MockMvcRequestBuilders.get("/transaction")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }


}
