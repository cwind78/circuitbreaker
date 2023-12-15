package com.circuit.breaker.demo.common.config.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

//https://github.com/rura6502/snippets-spring/blob/master/spring-integration/spring_integration_getting_started/src/main/java/io/github/rura6502/spring_integration_getting_started/TicketSummarizeFlow.java
@Configuration
public class TicketSummarizeFlow {
    public String INPUT_DIR = "/tmp/input";
    public String OUTPUT_DIR = "/tmp/output";
    public String FILE_PATTERN = "*.txt";

//  @Bean
//  public MessageChannel fileChannel() {
//    return new DirectChannel();
//  }
//
//  @Bean
//  public MessageChannel fileWriterChannel() {
//    return new DirectChannel();
//  }

    @Bean
    @InboundChannelAdapter(value = "fileChannel", poller = @Poller(fixedDelay = "3000"))
    public MessageSource<File> fileReadingMessageSource() {
        FileReadingMessageSource sourceReader = new FileReadingMessageSource();
        sourceReader.setDirectory(new File(INPUT_DIR));
        sourceReader.setFilter(new SimplePatternFileListFilter(FILE_PATTERN));
        return sourceReader;
    }

    @Bean
    @Transformer(
            inputChannel = "fileChannel"
            , outputChannel = "fileWriterChannel")
    public GenericTransformer<File, File> readAndChangeUpperCaseTransformer() {
        return file -> {

            String content = "";
            try {
                content = Files.readAllLines(file.toPath()).stream().collect(Collectors.joining("\n"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            File newFile = null;
            try {
                newFile = File.createTempFile("temp__", "$$$" + file.getName());
            } catch (IOException e) {
                e.printStackTrace();
            }

            try (FileWriter writer = new FileWriter(newFile)) {
                writer.write(content.toUpperCase());
            } catch (IOException e) {
                e.printStackTrace();
            }
            newFile.deleteOnExit();
            file.delete();
            return newFile;
        };
    }

    @Bean
    @ServiceActivator(inputChannel = "fileWriterChannel")
    public MessageHandler fileWritingMessageHandler() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(OUTPUT_DIR));
        handler.setFileExistsMode(FileExistsMode.REPLACE);
        handler.setFileNameGenerator(message -> {
            File newFile = (File) message.getPayload();
            System.out.println(newFile);
            return LocalDateTime.now().toString().replace(":", "")
                    + "__" + newFile.getName().split("\\$\\$\\$")[1];
        });
        handler.setExpectReply(false);
//    handler.setDeleteSourceFiles(true);
        return handler;
    }

//  @Bean
//  public IntegrationFlow flow() {
//    return IntegrationFlows
//      .from(fileReadingMessageSource())
//      .transform(upperCaseTransformer())
//      .handle(fileWritingMessageHandler())
//      .get();
//  }
}
