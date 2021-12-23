package com.programmerid.santaclausapi.infrastructure;

import com.programmerid.santaclausapi.domain.PresentOrder;
import com.programmerid.santaclausapi.domain.PresentWrapOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SqsQueuesListener {

    @SqsListener(value = "${app.aws.present_topic_name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void onPresentOrderEvent(PresentOrder event) {
        log.info("Processing new present order {}, {}", event.getOrderUuid(), event.getPresentName());
    }

    @SqsListener(value = "${app.aws.present_topic_name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void onPresentWrapOrderEvent(PresentWrapOrder event) {
        log.info("Processing new present wrap order {}, {}", event.getWrapOrderUuid(), event.getPresentName());
    }
}
