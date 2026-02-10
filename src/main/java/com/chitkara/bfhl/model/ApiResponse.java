package com.chitkara.bfhl.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {
    @JsonProperty("is_success")
    private boolean is_success;
    
    @JsonProperty("official_email")
    private String official_email;
    
    @JsonProperty("data")
    private Object data;
    
    @JsonProperty("error")
    private String error;

    public ApiResponse(boolean is_success, String official_email, Object data) {
        this.is_success = is_success;
        this.official_email = official_email;
        this.data = data;
    }

    public ApiResponse(boolean is_success, String official_email, Object data, String error) {
        this.is_success = is_success;
        this.official_email = official_email;
        this.data = data;
        this.error = error;
    }
}
