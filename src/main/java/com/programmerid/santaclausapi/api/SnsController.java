package com.programmerid.santaclausapi.api;

import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.programmerid.santaclausapi.application.PresentWarehouse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.config.annotation.NotificationMessage;
import org.springframework.cloud.aws.messaging.config.annotation.NotificationSubject;
import org.springframework.cloud.aws.messaging.endpoint.NotificationStatus;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationMessageMapping;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationSubscriptionMapping;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationUnsubscribeConfirmationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/sns-letter-listener")
public class SnsController {

    private final PresentWarehouse presentWarehouse;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @NotificationSubscriptionMapping
    public void confirmSubscribeMessage(
            NotificationStatus notificationStatus) {
        log.info("Sns listener subscribed to a topic");
        notificationStatus.confirmSubscription();
    }

    @NotificationMessageMapping
    public void receiveNotification(@NotificationMessage String message,
                                    @NotificationSubject String subject) throws JsonProcessingException {
        log.info("Sns listener received a notification");
        DynamodbEvent.DynamodbStreamRecord event = this.objectMapper.readValue(message, DynamodbEvent.DynamodbStreamRecord.class);
        String letterUuid = event.getDynamodb().getKeys().get("id").getS();
        Set<String> orderUuids = presentWarehouse.orderPresents(letterUuid);
        log.info("Orders were created {}", orderUuids);
    }

    @NotificationUnsubscribeConfirmationMapping
    public void confirmUnsubscriptionMessage(
            NotificationStatus notificationStatus) {
        log.info("Sns listener unsubscribed from a topic");
        notificationStatus.confirmSubscription();
    }
}
