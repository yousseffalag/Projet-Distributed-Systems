package com.falag.youssef.ebankbot.services;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Service;

@Service
public class EbankAIAgent {

    private final ChatClient chatClient;

    public EbankAIAgent(ChatModel chatModel, ObjectProvider<ToolCallbackProvider> toolCallbackProvider) {
        ChatClient.Builder builder = ChatClient.builder(chatModel)
                .defaultSystem("Vous êtes un assistant bancaire virtuel intelligent pour l'écosystème E-Bank développé par Falag Youssef. " +
                        "Vous avez accès aux microservices Customer Service et EBank Service via le protocole MCP (Model Context Protocol). " +
                        "Vous pouvez rechercher des clients, consulter les détails et soldes des comptes bancaires, ou assister à la création de comptes. " +
                        "Répondez avec courtoisie, clarté et précision professionnelle.");

        toolCallbackProvider.ifAvailable(builder::defaultTools);
        this.chatClient = builder.build();
    }

    public String chat(String query) {
        return this.chatClient.prompt()
                .user(query)
                .call()
                .content();
    }
}
