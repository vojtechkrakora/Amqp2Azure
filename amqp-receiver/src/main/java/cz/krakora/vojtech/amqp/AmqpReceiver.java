package cz.krakora.vojtech.amqp;

import com.microsoft.azure.servicebus.MessageHandlerOptions;
import com.microsoft.azure.servicebus.QueueClient;
import com.microsoft.azure.servicebus.ReceiveMode;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import cz.krakora.vojtech.amqp.common.AmqpCommon;
import cz.krakora.vojtech.amqp.handlers.CustomMessageHandler;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * All sources are based on:
 * https://github.com/Azure/azure-service-bus
 */
public class AmqpReceiver {

    public static void main(String[] args) {
        System.exit(AmqpCommon.runApp(args, (connectionInfo) -> {
            AmqpReceiver app = new AmqpReceiver();
            try {
                app.run(connectionInfo[0],connectionInfo[1]);
                return 0;
            } catch (Exception e) {
                System.out.printf("%s", e.toString());
                return 1;
            }
        }));
    }

    public void run(String connectionString, String queueName) throws Exception {

        // Create a QueueClient instance for receiving using the connection string builder
        // We set the receive mode to "PeekLock", meaning the message is delivered
        // under a lock and must be acknowledged ("completed") to be removed from the queue
        QueueClient receiveClient = new QueueClient(new ConnectionStringBuilder(connectionString, queueName), ReceiveMode.PEEKLOCK);
        // We are using single thread executor as we are only processing one message at a time
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        this.registerReceiver(receiveClient, executorService);

        // wait for ENTER or 10 seconds elapsing
        AmqpCommon.waitForEnter(10);

        // shut down receiver to close the receive loop
        receiveClient.close();
        executorService.shutdown();
    }

    void registerReceiver(QueueClient queueClient, ExecutorService executorService) throws Exception {
        // register the RegisterMessageHandler callback with executor service
        queueClient.registerMessageHandler(new CustomMessageHandler(),
                // 1 concurrent call, messages are auto-completed, auto-renew duration
                new MessageHandlerOptions(1, true, Duration.ofMinutes(1)),
                executorService);
    }
}
