package com.app.app.controller;

import com.app.app.entity.Agent;
import com.app.app.payload.AgentDto;
import com.app.app.payload.CostumerDto;
import com.app.app.service.CostumerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/costumer")
public class CostumerController {
    private CostumerService costumerService;

    public CostumerController(CostumerService costumerService) {
        this.costumerService = costumerService;
    }

    @PostMapping("/addCostumer")
    public ResponseEntity<?> addCostumer(@RequestBody CostumerDto costumerDto) {
        CostumerDto costumerDto1 = costumerService.addCostumer(costumerDto);

        if (costumerDto1.getEmail() == null && costumerDto1.getMobile() == null) {
            return new ResponseEntity<>("Email and mobile already exist", HttpStatus.BAD_REQUEST);
        } else if (costumerDto1.getEmail() == null) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        } else if (costumerDto1.getMobile() == null) {
            return new ResponseEntity<>("Mobile already exists", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Customer added successfully: " + costumerDto1, HttpStatus.CREATED);
    }

    @PutMapping("/addAgent/{id}")
    public ResponseEntity<?> addAgent(@PathVariable Long id, @RequestBody AgentDto agentDto) {
        try {
            CostumerDto response = costumerService.addAgent(id, agentDto);

            if (response.getAgent() != null) {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(Map.of("message", "Agent assigned successfully", "costumer", response));
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Map.of("message", "Failed to assign agent", "costumer", response));
            }

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

}
