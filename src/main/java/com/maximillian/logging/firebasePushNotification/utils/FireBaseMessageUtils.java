package com.maximillian.logging.firebasePushNotification.utils;

import com.google.firebase.messaging.BatchResponse;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FireBaseMessageUtils {
   final FirebaseMessaging firebaseMessaging;
    // This is the util class that can construct any form of push notification that you would want to construct, be it
    // batch push notifications or to specific device

    /**
     *
     * @param messageData
     * @param topic(topic should be firebase format, no space or camel case but can include underscores as delimiters)
     *
     */
    public void sendMessageToTopic(Map<String, String> messageData, String topic){
        try {
            Message message = Message.builder()
                    .setTopic(topic)
                    .putAllData(messageData)
                    .build();

            firebaseMessaging.send(message);
        }catch(Exception ex){
            throw new RuntimeException("Sending message to topic failed with message %s".formatted(ex.getMessage()));
        }
    }

    public void sendMessageToDevice(Map<String, String> messageData,  String deviceToken){
        //The deviceToken is the registration token of the device that is got from the mobile when it contacts firebase
        //Then you can save in the db as encrypted value and decrypt when you retrieve from the DB against the userId
        // It is better to keep the device token encrypted as an extra level of security
        try {
            Message message = Message.builder()
                    .setToken(deviceToken)
                    .putData("body", "some data")
                    .build();
            firebaseMessaging.send(message);
        }catch (Exception ex){
            throw new RuntimeException("Sending message to client failed with message %s".formatted(ex.getMessage()));
        }
    }

    public void subscribeClientToTopic(String deviceToken, String topic){
        try{
            List<String> deviceRegistrationList = Collections.singletonList(deviceToken);
         firebaseMessaging.subscribeToTopic(deviceRegistrationList, topic);
        }catch(Exception ex){
            throw new RuntimeException("client subscription to topic: %s failed with message %s".formatted(topic, ex.getMessage()));
        }
    }

    public void subscribeClientToTopic(List<String> deviceTokens, String topic){
        try{
            firebaseMessaging.subscribeToTopic(deviceTokens, topic);
        }catch(Exception ex){
            throw new RuntimeException("clients subscription to topic: %s failed with message %s".formatted(topic, ex.getMessage()));
        }
    }
    public void unSubscribeClientFromTopic(List<String> deviceTokens, String topic){
        try{
            //todo: build a TopicManagementResponse Pojo and make use of it to determine the success of the unsubscription
            firebaseMessaging.unsubscribeFromTopic(deviceTokens, topic);
        }catch (Exception ex){
            throw new RuntimeException("clients unsubcription failed");
        }
    }

    public void sendMessageToMultipleClients(Map<String, String> messageData, List<String> registrationTokens){
        try {
            MulticastMessage message = MulticastMessage.builder()
                    .addAllTokens(registrationTokens)
                    .putAllData(messageData)
                    .build();
            BatchResponse batchResponse = firebaseMessaging.sendMulticast(message);
            if(batchResponse.getSuccessCount() > 0){
                System.out.println("this means that message sending is successful");
            }
        }catch (Exception ex){
            throw new RuntimeException("failed to send multicast messages");
        }
    }

}
