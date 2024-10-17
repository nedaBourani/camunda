package com.example.testcamounda.service;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ServiceTaskBookHotel {
    private static final Logger logger = LoggerFactory.getLogger(ServiceTaskBookHotel.class);

    @JobWorker(type = "bookHotel-service")
    public void processPayment(final JobClient jobClient, final ActivatedJob job) {
        String requestId = job.getVariablesAsMap().get("requestId").toString();
        logger.info("Processing equest: " + requestId);
        try {


            // Simulate processing  and throwing an error for demonstration
            if (requestId.equals("error")) {
                throw new RuntimeException("SimulatedError");
            }

            jobClient.newCompleteCommand(job.getKey()).send().join();
            logger.info(" processed successfully: " + requestId);
        } catch (Exception e) {
            logger.error("Error processing job: " + e.getMessage());
            jobClient.newFailCommand(job.getKey())
                    .retries(0)
                    .errorMessage(" failed, executing compensation...")
                    .send()
                    .join();
        }
    }
}
