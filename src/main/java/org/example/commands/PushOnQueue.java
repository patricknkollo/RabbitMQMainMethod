package org.example.commands;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class PushOnQueue {

    public static void pushMessageOnQueue(String message, String queueName){
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost"); // Adresse du serveur RabbitMQ

            // Publier un message dans la queue A
            try (Connection connection = factory.newConnection();
                 Channel channel = connection.createChannel()) {
                channel.queueDeclare(queueName, false, false, false, null);
                channel.basicPublish("", queueName, null, message.getBytes());
                System.out.println("Message publié dans la queue A : " + message);
            }
        }
            catch (IOException | TimeoutException e) {
                e.printStackTrace();
            }
    }
}
