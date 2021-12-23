package com.programmerid.santaclausapi.infrastructure;

import com.programmerid.santaclausapi.application.PresentOrderingDepartment;
import com.programmerid.santaclausapi.application.PresentWrapOrderingDepartment;
import com.programmerid.santaclausapi.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class SqsQueuesClient implements PresentOrderingDepartment, PresentWrapOrderingDepartment {

    private final QueueMessagingTemplate template;
    private final AwsProperties awsProperties;

    @Override
    public String orderPresent(String presentName) {
        PresentOrderUuid presentOrderUuid = new PresentOrderUuid(UUID.randomUUID());
        template.convertAndSend(awsProperties.getPresentTopicName(), new PresentOrder(presentOrderUuid, new PresentName(presentName)));
        return presentOrderUuid.toString();
    }

    @Override
    public String orderPresentWrap(String presentName) {
        PresentWrapOrderUuid presentWrapOrderUuid = new PresentWrapOrderUuid(UUID.randomUUID());
        template.convertAndSend(awsProperties.getPresentWrapTopicName(),
                new PresentWrapOrder(presentWrapOrderUuid, new PresentName(presentName)));
        return presentWrapOrderUuid.toString();
    }
}
