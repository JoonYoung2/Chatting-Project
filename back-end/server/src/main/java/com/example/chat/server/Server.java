package com.example.chat.server;

import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

@Slf4j
public class Server implements Runnable {
    private final int PORT = 8082;
    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(PORT);

    }

    public void start() throws IOException{
        log.info("Server Start");
        try{
            while(true){
                this.socket = this.serverSocket.accept();
                this.input = new DataInputStream(socket.getInputStream());
                this.output = new DataOutputStream(socket.getOutputStream());

                log.info("{}", input.readUTF());

                String read = input.readUTF();
                output.writeUTF(read);
                log.info("{}", read);
            }
        }catch(IOException e){

        }finally{
            this.output.close();
            this.input.close();
            this.socket.close();
            this.serverSocket.close();
        }
    }

    @Override
    public void run() {

    }
}
