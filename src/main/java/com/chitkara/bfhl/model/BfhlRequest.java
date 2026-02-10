package com.chitkara.bfhl.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BfhlRequest {
    private Integer fibonacci;
    private List<Integer> prime;
    private List<Integer> lcm;
    private List<Integer> hcf;
    
    @JsonProperty("AI")
    private String AI;
}
