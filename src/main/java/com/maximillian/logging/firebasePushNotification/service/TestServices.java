package com.maximillian.logging.firebasePushNotification.service;

import com.maximillian.logging.firebasePushNotification.utils.FireBaseMessageUtils;
import jakarta.annotation.Nonnull;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class TestServices {
    @Nonnull  final FireBaseMessageUtils fireBaseMessageUtils;

    void sendMessageToTopic(){
        Map<String,String> messageMap = Map.of("Greeting", "hello darkness my old friend");
        String topicName = "Greetings_topic";
        fireBaseMessageUtils.sendMessageToTopic(messageMap, topicName);
    }

    @PostConstruct
    void doPostConstruct(){
        System.out.println("===============>> sending message to firebase topic <<================");
        sendMessageToTopic();
    }

}
