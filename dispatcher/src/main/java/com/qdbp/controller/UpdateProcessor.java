package com.qdbp.controller;

import com.qdbp.configuration.RabbitConfiguration;
import com.qdbp.service.UpdateProducer;
import com.qdbp.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;



@Log4j
@RequiredArgsConstructor
@Component
public class UpdateProcessor {

    private TelegramBot telegramBot;

    private final MessageUtils messageUtils;

    private final UpdateProducer updateProducer;

    private final RabbitConfiguration rabbitConfiguration;

    public void registerBot(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void processUpdate(Update update) {
        if(update == null){
            log.error("Received update is null");
            return;

        }

        if(update.hasMessage()){
            distributeMessagesByType(update);

        }else {
            log.error("Unsupported message type "+update);

        }
    }

    // Распределение по типам
    private void distributeMessagesByType(Update update) {
        var message = update.getMessage();
        if(message.hasText()){

            processTextMessage(update);
        } else if (message.hasDocument()) {
            processDocMessage(update);

        } else if (message.hasPhoto()) {
            processPhotoMessage(update);

        } else {
            setUnsupportedMessageTypeView(update);

        }
    }

    private void setUnsupportedMessageTypeView(Update update) {
        var sendMessage = messageUtils.generateSendMessageWithText(update,

                "Неподдерживаемый тип сообщения!");
        setView(sendMessage);
    }

    private void setFileIsReceivedView(Update update) {
        var sendMessage = messageUtils.generateSendMessageWithText(update,

                "Файл получен, обрабатывается...");
        setView(sendMessage);

    }

    public void setView(SendMessage sendMessage) {

        telegramBot.sendAnswerMessage(sendMessage); // Отправка через бота
    }

    private void processPhotoMessage(Update update) {

        updateProducer.produce(rabbitConfiguration.getPhotoMessageUpdateQueue(), update);
        setFileIsReceivedView(update);
    }

    private void processDocMessage(Update update) {

        updateProducer.produce(rabbitConfiguration.getDocMessageUpdateQueue(), update);
        setFileIsReceivedView(update);
    }

    private void processTextMessage(Update update) {

        updateProducer.produce(rabbitConfiguration.getTextMessageUpdateQueue(), update);
    }
}



