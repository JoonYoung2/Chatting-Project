package com.example.chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private String ip = "localhost";
    private int port = 8082;
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    public Client() throws IOException {
        this.socket = new Socket(ip, port);
    }

    public void start() throws IOException{
        this.input = new DataInputStream(this.socket.getInputStream());
        this.output = new DataOutputStream(this.socket.getOutputStream());
        WriteManager writeManager = new WriteManager(output, this);
        ReceiveManager receiveManager = new ReceiveManager(input);

        Thread receiveThread = new Thread(receiveManager, "readThread");
        Thread writeThread = new Thread(writeManager, "writeThread");
        receiveThread.start();
        writeThread.start();
    }

    public void close(){
        try {
            output.close();
            input.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
