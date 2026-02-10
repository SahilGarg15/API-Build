package com.chitkara.bfhl.controller;

import com.chitkara.bfhl.model.ApiResponse;
import com.chitkara.bfhl.model.BfhlRequest;
import com.chitkara.bfhl.service.AIService;
import com.chitkara.bfhl.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class BfhlController {

    @Autowired
    private MathService mathService;

    @Autowired
    private AIService aiService;

    @Value("${app.official.email}")
    private String officialEmail;

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("is_success", true);
        response.put("official_email", officialEmail);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/bfhl")
    public ResponseEntity<ApiResponse> processBfhl(@RequestBody BfhlRequest request) {
        try {
            // Validate that exactly one key is present
            int keyCount = 0;
            if (request.getFibonacci() != null) keyCount++;
            if (request.getPrime() != null) keyCount++;
            if (request.getLcm() != null) keyCount++;
            if (request.getHcf() != null) keyCount++;
            if (request.getAI() != null) keyCount++;

            if (keyCount == 0) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse(false, officialEmail, null, "No valid key provided"));
            }

            if (keyCount > 1) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponse(false, officialEmail, null, "Multiple keys provided. Only one key allowed per request"));
            }

            Object data = null;

            // Process based on the key
            if (request.getFibonacci() != null) {
                data = mathService.generateFibonacci(request.getFibonacci());
            } else if (request.getPrime() != null) {
                data = mathService.filterPrimes(request.getPrime());
            } else if (request.getLcm() != null) {
                data = mathService.calculateLCM(request.getLcm());
            } else if (request.getHcf() != null) {
                data = mathService.calculateHCF(request.getHcf());
            } else if (request.getAI() != null) {
                data = aiService.getAIResponse(request.getAI());
            }

            return ResponseEntity.ok(new ApiResponse(true, officialEmail, data));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse(false, officialEmail, null, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse(false, officialEmail, null, "Internal server error: " + e.getMessage()));
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(false, officialEmail, null, "Unexpected error: " + e.getMessage()));
    }
}
