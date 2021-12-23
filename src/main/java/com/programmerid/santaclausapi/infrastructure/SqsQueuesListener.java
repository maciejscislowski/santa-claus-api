package com.programmerid.santaclausapi.infrastructure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SqsQueuesListener {

    @SqsListener(value = "${app.aws.present_topic_name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void onPresentOrderEvent(String event) {
        log.info("Processing new present order {}", event);
    }

    @SqsListener(value = "${app.aws.present_topic_name}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void onPresentWrapOrderEvent(String event) {
        log.info("Processing new present wrap order {}", event);
    }
}
