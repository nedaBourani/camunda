package com.example.testcamounda.service;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CompensateHotelBookingService {

    private static final Logger logger = LoggerFactory.getLogger(CompensateHotelBookingService.class);

    @JobWorker(type = "compensateHotelBooking-service", timeout = 30000)
    public void compensate(final JobClient jobClient, final ActivatedJob job) {
        String requestId = job.getVariablesAsMap().get("requestId").toString();
        logger.info("Compensating requestId: " + requestId);

        // Simulate compensation logic
        jobClient.newCompleteCommand(job.getKey()).send().join();
        logger.info(" compensated successfully: " + requestId);
    }


}
