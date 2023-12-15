package com.circuit.breaker.demo.common.config.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;

import java.io.File;

//@Configuration
public class FileWriterIntegrationConfig {
    //@Bean
    //@Transformer(inputChannel="textInChannel", // Declares a transformer textInChannel라는 빈을 선언하지 않으면 자동으로 생성된다. 하지만 채널을 좀 더 자세히 제어하고 싶으면 맨 아래 주석처럼 Bean을 만들어 주면 된다.
    //        outputChannel="fileWriterChannel")
    public GenericTransformer<String, String> upperCaseTransformer() {
        return text -> text.toUpperCase(); }

    //@Bean
    //@ServiceActivator(inputChannel="fileWriterChannel")
    public FileWritingMessageHandler fileWriter() { // Declares a file writer
        FileWritingMessageHandler handler =
                new FileWritingMessageHandler(new File("/tmp/sia5/files"));

        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);

        return handler;
    }

//    @Bean
//    public MessageChannel textInChannel() {
//        return new DirectChannel();
//    }
//
//    @Bean
//    public MessageChannel fileWriterChannel() {
//        return new DirectChannel();
//    }
}
