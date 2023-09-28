package org.example.commands;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Requeueing {

    public static void requeueingFromOneQueueToAnother(String srcQueue, String targetQueue){

        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost"); // Adresse du serveur RabbitMQ


            // Transférer un message de la queue A à la queue B
            try (Connection connection = factory.newConnection();
                 Channel channel = connection.createChannel()) {
                channel.queueDeclare(targetQueue, false, false, false, null);

                // Obtenir le message de la queue A
                GetResponse response = channel.basicGet(srcQueue, false);
                if (response != null) {
                    String message = new String(response.getBody());

                    // Publier le message dans la queue B
                    channel.basicPublish("", targetQueue, null, message.getBytes());
                    System.out.println("Message transféré de la queue A à la queue B : " + message);

                    // Acknowledge (marquer comme consommé) le message dans la queue A
                    channel.basicAck(response.getEnvelope().getDeliveryTag(), false);
                }
            }
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
