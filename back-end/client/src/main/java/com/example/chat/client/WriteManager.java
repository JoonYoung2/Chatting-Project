package com.example.chat.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class WriteManager implements Runnable{
    private final DataOutputStream output;
    private final Client client;
    private static final String DELIMITER = "|";

    public WriteManager(DataOutputStream output, Client client){
        this.output = output;
        this.client = client;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        try{
            while (true) {
                String toSend = scanner.nextLine(); // 블로킹
                if (toSend.isEmpty()) {
                    continue;
                }

                if (toSend.equals("/exit")) {
                    output.writeUTF(toSend);
                    break;
                }

                // "/"로 시작하면 명령어, 나머지는 일반 메시지
                if (toSend.startsWith("/")) {
                    output.writeUTF(toSend);
                } else {
                    output.writeUTF("/message" + DELIMITER + toSend);
                }
            }
        }catch(IOException e){

        }finally{
            client.close();
        }

    }
}
