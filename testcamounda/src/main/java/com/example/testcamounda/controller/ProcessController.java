package com.example.testcamounda.controller;

import com.example.testcamounda.dto.ProcessRequest;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.DeploymentEvent;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/process/temp")
public class ProcessController {


    private final ZeebeClient zeebeClient;

    @Autowired
    public ProcessController(ZeebeClient zeebeClient) {
        this.zeebeClient = zeebeClient;
    }

    @PostMapping("/start")
    public ResponseEntity<String> startProcess(@Valid @RequestBody ProcessRequest request) {
        zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId("test-process")
                .latestVersion()
                .variables(request)
                .send()
                .join();
        return ResponseEntity.ok("Process started successfully.");
    }
    @PostMapping("/deploy")
    public String deployProcess() {
        DeploymentEvent deploymentEvent = zeebeClient.newDeployCommand()
                .addResourceFromClasspath("temp-process.bpmn")
                .send()
                .join();

        return "Deployed process: " + deploymentEvent.getKey();
    }
}
