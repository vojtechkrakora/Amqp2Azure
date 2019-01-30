package cz.krakora.vojtech.amqp.handlers;

import com.microsoft.azure.servicebus.ExceptionPhase;
import com.microsoft.azure.servicebus.IMessage;
import com.microsoft.azure.servicebus.IMessageHandler;

import java.util.concurrent.CompletableFuture;

public class CustomMessageHandler implements IMessageHandler {
    /**
     * callback invoked when the message handler loop has obtained a message
     * @param message
     * @return
     */
    public CompletableFuture<Void> onMessageAsync(IMessage message) {
        // receives message is passed to callback
        System.out.println("Message was received:");
        System.out.println("- MessageId = " + message.getMessageId());
        System.out.println("- SequenceNumber = " + message.getSequenceNumber());
        System.out.println("- EnqueueTimeUtc = " + message.getEnqueuedTimeUtc());
        System.out.println("- ExpiresAtUtc = " + message.getExpiresAtUtc());
        System.out.println("- ContentType = " + message.getContentType());

        return CompletableFuture.completedFuture(null);
    }

    /**
     * callback invoked when the message handler has an exception to report
     * @param throwable
     * @param exceptionPhase
     */
    public void notifyException(Throwable throwable, ExceptionPhase exceptionPhase) {
        System.out.printf(exceptionPhase + "-" + throwable.getMessage());
    }
}
