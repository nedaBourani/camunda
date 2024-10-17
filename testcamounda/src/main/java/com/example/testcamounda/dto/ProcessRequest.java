package com.example.testcamounda.dto;

import jakarta.validation.constraints.NotNull;

public class ProcessRequest {
    @NotNull(message = "requestId cannot be null")
    private String requestId;
    private Double amount;
    private boolean approved=true;


    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
