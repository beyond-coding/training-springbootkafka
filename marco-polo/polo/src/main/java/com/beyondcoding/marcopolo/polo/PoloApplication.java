package com.beyondcoding.marcopolo.polo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

@EnableBinding(Processor.class)
@SpringBootApplication
public class PoloApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoloApplication.class, args);
    }

    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public Person hearMarcoAndSayPolo(Person person) {
        System.out.println("-- MESSAGE RECEIVED -- " + person);
        Person polo = new Person("Polo asociado a -  " + person.getName());
        System.out.println("-- MESSAGE SENT -- " + polo);
        return polo;
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
