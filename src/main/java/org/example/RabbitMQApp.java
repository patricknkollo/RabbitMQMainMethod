package org.example;

import com.rabbitmq.client.*;
import org.example.commands.ConsumeFromQueue;
import org.example.commands.PushOnQueue;
import org.example.commands.Requeueing;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQApp {

    private static final String QUEUE_A_NAME = "queueA";
    private static final String QUEUE_B_NAME = "queueB";

    public static void main(String[] args) {

        // Publier un message dans la queue A
        PushOnQueue.pushMessageOnQueue("mademoiselle elissa nkollo", QUEUE_A_NAME);

        // Consommer un message de la queue A
        ConsumeFromQueue.consumeFromParticularQueue(QUEUE_A_NAME);

        // Transférer un message de la queue A à la queue B
        Requeueing.requeueingFromOneQueueToAnother(QUEUE_A_NAME,QUEUE_B_NAME);
    }
}
