package com.app.app.service;

import com.app.app.entity.Agent;
import com.app.app.entity.Area;
import com.app.app.payload.AgentDto;
import com.app.app.repository.AgentRepository;
import com.app.app.repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    private final AgentRepository agentRepository;
    private final AreaRepository areaRepository;

    public AgentService(AgentRepository agentRepository, AreaRepository areaRepository) {
        this.agentRepository = agentRepository;
        this.areaRepository = areaRepository;
    }

    public AgentDto addAgent(AgentDto agentDto) {
        // Check if agent already exists by email & mobile
        Agent existingAgent = agentRepository.findByEmailAndMobile(agentDto.getEmail(), agentDto.getMobile());

        if (existingAgent != null) {
            // Agent exists, check if it's in the same area
            if (existingAgent.getArea().getPinCode().equals(agentDto.getArea())) {
                // Agent already exists in the same area
                return null;  // Will trigger 409 Conflict in Controller
            } else {
                // Agent exists but in a different area
                AgentDto response = new AgentDto();
                response.setEmail(agentDto.getEmail());
                response.setMobile(agentDto.getMobile());
                return response; // Will trigger "exists in a different area" case
            }
        }

        // Check if the given area exists
        Area area = areaRepository.findAreaByPinCode(agentDto.getArea());
        if (area == null) {
            return null; // Will trigger 400 Bad Request in Controller
        }

        // Create and save new agent
        Agent newAgent = new Agent();
        newAgent.setName(agentDto.getName());
        newAgent.setEmail(agentDto.getEmail());
        newAgent.setMobile(agentDto.getMobile());
        newAgent.setArea(area);

        agentRepository.save(newAgent);

        // Return response DTO
        AgentDto responseDto = new AgentDto();
        responseDto.setName(newAgent.getName());
        responseDto.setEmail(newAgent.getEmail());
        responseDto.setMobile(newAgent.getMobile());
        responseDto.setArea(newAgent.getArea().getPinCode());

        return responseDto;
    }
}
