package io.menzies.exifer.service;

import com.amazonaws.services.s3.event.S3EventNotification;
import io.awspring.cloud.messaging.listener.SqsMessageDeletionPolicy;
import io.awspring.cloud.messaging.listener.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SQSService {

    private static final Logger LOG = LoggerFactory.getLogger(SQSService.class);

    @SqsListener(value = "${cloud.aws.sqs.endpoint}", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receiveS3Event(S3EventNotification s3EventNotificationRecord) {
        S3EventNotification.S3Entity s3Entity = s3EventNotificationRecord.getRecords().get(0).getS3();
        String objectKey = s3Entity.getObject().getKey();
        LOG.info("s3 event::objectKey:: {}", objectKey);
    }
}
