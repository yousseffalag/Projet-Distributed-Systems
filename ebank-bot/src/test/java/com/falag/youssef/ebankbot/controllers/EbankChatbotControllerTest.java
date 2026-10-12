package com.falag.youssef.ebankbot.controllers;

import com.falag.youssef.ebankbot.services.EbankAIAgent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EbankChatbotController.class)
class EbankChatbotControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EbankAIAgent aiAgent;

    @Test
    void testChatGetEndpoint() throws Exception {
        when(aiAgent.chat(anyString())).thenReturn("Bonjour, voici la liste des clients disponibles.");

        mockMvc.perform(get("/chat")
                        .param("question", "Donne moi les clients")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.question").value("Donne moi les clients"))
                .andExpect(jsonPath("$.response").value("Bonjour, voici la liste des clients disponibles."));
    }

    @Test
    void testChatPostEndpoint() throws Exception {
        when(aiAgent.chat(anyString())).thenReturn("Le compte bancaire a un solde positif.");

        String requestBody = "{\"question\": \"Quel est le solde du compte 1 ?\"}";

        mockMvc.perform(post("/chat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.question").value("Quel est le solde du compte 1 ?"))
                .andExpect(jsonPath("$.response").value("Le compte bancaire a un solde positif."));
    }
}
