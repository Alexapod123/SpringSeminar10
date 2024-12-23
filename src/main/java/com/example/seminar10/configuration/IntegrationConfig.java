package com.example.seminar10.configuration;

import com.example.seminar10.model.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

import java.io.File;
import java.io.FileWriter;

@Configuration
public class IntegrationConfig {

    /**
     *
     * @return канал для входящих текстовых сообщений
     */
    @Bean
    public MessageChannel textInputChanel() {
        return new DirectChannel();
    }

    /**
     *
     * @return канал для исходящих текстовых сообщений
     */
    @Bean
    public MessageChannel fileWriterChanel() {
        return new DirectChannel();
    }

    /**
     *
     * @param mapper объект класса ObjectMapper
     * @return преобразование объекта person в json строку.
     */
    @Bean
    @Transformer(inputChannel = "textInputChannel", outputChannel = "fileWriterChannel")
    public GenericTransformer<Person, String> mainTransformer(ObjectMapper mapper){
        return person-> {
            try {
                return mapper.writeValueAsString(person);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return null;
            }
        };
    }

    /**
     *
     * @return обработчик сообщений, для их записи в текстовый файл
     */
    @Bean
    @ServiceActivator(inputChannel = "fileWriterChannel")
    public FileWritingMessageHandler messageHandler(){
        FileWritingMessageHandler messageHandler = new FileWritingMessageHandler((new File("C:\\gb\\seminar11\\files")));
        messageHandler.setExpectReply(false);
        messageHandler.setFileExistsMode(FileExistsMode.APPEND);
        messageHandler.setAppendNewLine(true);
        return messageHandler;
    }
}
