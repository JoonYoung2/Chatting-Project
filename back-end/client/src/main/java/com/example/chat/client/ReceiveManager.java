package com.example.chat.client;

import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.io.IOException;

@Slf4j
public class ReceiveManager implements Runnable{
    private final DataInputStream input;

    public ReceiveManager(DataInputStream input){
        this.input = input;
    }

    @Override
    public void run() {
        try{
            while(true){
                log.info("{}", input);
            }
        } finally{
            try {
                input.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
