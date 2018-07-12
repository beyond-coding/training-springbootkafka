package com.beyondcoding.rockscissorspaper.io;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class Input {

    private final Scanner scanner = new Scanner(System.in);

    public String get() {
        return scanner.next();
    }
}
