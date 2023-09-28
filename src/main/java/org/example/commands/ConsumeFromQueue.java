package org.example.commands;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumeFromQueue {

    public static void consumeFromParticularQueue(String queueName) {

        // Consommer un message de la queue A
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost"); // Adresse du serveur RabbitMQ

            // Consommer un message de la queue A
            try (Connection connection = factory.newConnection();
                 Channel channel = connection.createChannel()) {
                channel.queueDeclare(queueName, false, false, false, null);
                DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                    String receivedMessage = new String(delivery.getBody());
                    System.out.println("Message reçu de la queue A : " + receivedMessage);
                };
                channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
                });
            }
        }
        catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
