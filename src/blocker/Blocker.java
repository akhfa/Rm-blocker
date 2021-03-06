/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blocker;

import com.rabbitmq.client.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author akhfa
 *    Comments:
 *    Menerima pesan dari rabbitmq dengan param:
 *    0. host
 *    1. virtualhost
 *    2. username
 *    3. password
 *    4. exchange name
 *    5. queue name
 *    6. routing key
 */
public class Blocker {
    public static int seconds;
    public static String dir="/home/akhfa/.blocker/blockedIP";
    /**
     * @param argv
     */
    public static void main(String[] argv) throws Exception{
        seconds = Integer.parseInt(argv[7]);
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(argv[0]);
        factory.setUsername(argv[2]);
        factory.setPassword(argv[3]);
        factory.setVirtualHost(argv[1]);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(argv[4], "direct", true);
        String queueName = channel.queueDeclare(argv[5], true, false, false, null).getQueue();
        
        //                          exchange  key
        channel.queueBind(queueName, argv[4], argv[6]);

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
          @Override
          public void handleDelivery(String consumerTag, Envelope envelope,
                                     AMQP.BasicProperties properties, byte[] body) throws IOException {
            String message = new String(body, "UTF-8");
            JSONParser parser = new JSONParser();
            
            Thread.UncaughtExceptionHandler h = new Thread.UncaughtExceptionHandler() {
                @Override
                public void uncaughtException(Thread th, Throwable ex) {
                    System.out.println("Uncaught exception: " + ex);
                }
            };
            
            try {
                Object obj = parser.parse(message);
                JSONObject jobj = (JSONObject) obj;
                String IP = (String) jobj.get("clientip");
                Thread t = new Thread(new BlockerThread(IP));
                t.setUncaughtExceptionHandler(h);
                t.start();
            } catch (ParseException ex) {
                Logger.getLogger(Blocker.class.getName()).log(Level.SEVERE, null, ex);
            }
          }
          
        };
        channel.basicConsume(argv[5], true, consumer);
    }
}
