package com.falag.youssef.ebankbot.telegram;

import com.falag.youssef.ebankbot.services.EbankAIAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@ConditionalOnProperty(name = "telegram.bot.token")
public class TelegramBot extends TelegramLongPollingBot {

    private static final Logger log = LoggerFactory.getLogger(TelegramBot.class);

    private final String botToken;
    private final String botUsername;
    private final EbankAIAgent aiAgent;

    public TelegramBot(
            @Value("${telegram.bot.token:}") String botToken,
            @Value("${telegram.bot.username:EbankFalagBot}") String botUsername,
            EbankAIAgent aiAgent) {
        super(botToken);
        this.botToken = botToken;
        this.botUsername = botUsername;
        this.aiAgent = aiAgent;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            log.info("Message Telegram reçu de chatId [{}]: {}", chatId, messageText);

            try {
                // Interrogation de l'Agent IA connecté via MCP
                String response = aiAgent.chat(messageText);

                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(String.valueOf(chatId));
                sendMessage.setText(response);

                execute(sendMessage);
            } catch (TelegramApiException e) {
                log.error("Erreur lors de l'envoi de la réponse Telegram", e);
            } catch (Exception e) {
                log.error("Erreur de traitement IA du message Telegram", e);
            }
        }
    }
}
