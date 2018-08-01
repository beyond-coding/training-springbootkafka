package com.beyondcoding.marcopolo.marco;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableBinding(Processor.class)
@EnableScheduling
@SpringBootApplication
public class MarcoApplication {

    @Autowired
    Processor processor;

    private int number = 0;

    public static void main(String[] args) {
        SpringApplication.run(MarcoApplication.class, args);
    }

    @Scheduled(fixedRate = 5 * 1000)
    public void sayMarcoEveryFiveSeconds() {
        number++;
        Person marco = new Person("Marco " + number);
        System.out.println("-- MESSAGE SENT -- " + marco);
        Message<Person> message = MessageBuilder.withPayload(marco).build();
        processor.output().send(message);
    }

    @StreamListener(Processor.INPUT)
    public void hearPolo(Person person) {
        System.out.println("-- MESSAGE RECEIVED -- " + person);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Person {

        private String name;

        @Override
        public String toString() {
            return name;
        }
    }

}
