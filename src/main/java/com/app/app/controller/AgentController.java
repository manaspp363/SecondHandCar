package com.app.app.controller;

import com.app.app.payload.AgentDto;
import com.app.app.service.AgentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/agent")
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping("/addAgent")
    public ResponseEntity<?> addAgent(@RequestBody AgentDto agentDto) {
        try {
            AgentDto response = agentService.addAgent(agentDto);

            if (response == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Map.of("message", "Agent with this email and mobile already exists in the same area."));
            }

            if (response.getEmail() != null && response.getMobile() != null && response.getArea() == null) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Map.of("message", "Agent with this email and mobile exists in a different area.", "agent", response));
            }

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("message", "Agent created successfully.", "agent", response));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An unexpected error occurred.", "details", e.getMessage()));
        }

    }
}
