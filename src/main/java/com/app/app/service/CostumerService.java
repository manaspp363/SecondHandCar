package com.app.app.service;

import com.app.app.entity.Agent;
import com.app.app.entity.Area;
import com.app.app.entity.Costumervisit;
import com.app.app.payload.AgentDto;
import com.app.app.payload.CostumerDto;
import com.app.app.repository.AgentRepository;
import com.app.app.repository.AreaRepository;
import com.app.app.repository.CostumervisitRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CostumerService {
    private AgentRepository agentRepository;
    private CostumervisitRepository costumervisitRepository;
    private AreaRepository areaRepository;
    private SmsService smsService;

    public CostumerService(SmsService smsService, AgentRepository agentRepository, CostumervisitRepository costumervisitRepository, AreaRepository areaRepository) {
        this.agentRepository = agentRepository;
        this.smsService = smsService;
        this.costumervisitRepository = costumervisitRepository;
        this.areaRepository = areaRepository;

    }
    public CostumerDto addCostumer(CostumerDto costumerDto) {
        Costumervisit byMobile = costumervisitRepository.findByMobile(costumerDto.getMobile());
        Costumervisit byEmail = costumervisitRepository.findByEmail(costumerDto.getEmail());

        if (byMobile != null && byEmail != null) {
            costumerDto.setMobile(null);
            costumerDto.setEmail(null);
            return costumerDto;
        } else if (byMobile != null) {
            costumerDto.setMobile(null);
            return costumerDto;
        } else if (byEmail != null) {
            costumerDto.setEmail(null);
            return costumerDto;
        }

        Area areaByPinCode = areaRepository.findAreaByPinCode(costumerDto.getArea());

        // **Fix: Ensure `areaByPinCode` is not null to avoid NullPointerException**
        if (areaByPinCode == null) {
            return null; // You might want to return a specific response here.
        }

        Costumervisit saveCostumer = new Costumervisit();
        saveCostumer.setName(costumerDto.getName());
        saveCostumer.setEmail(costumerDto.getEmail());
        saveCostumer.setMobile(costumerDto.getMobile());
        saveCostumer.setArea(areaByPinCode);
        smsService.sendSms("+916264797904", "Your OTP is: 12345");

        costumervisitRepository.save(saveCostumer);



        // Return response DTO
        CostumerDto responseDto = new CostumerDto();
        responseDto.setName(saveCostumer.getName());
        responseDto.setEmail(saveCostumer.getEmail());
        responseDto.setMobile(saveCostumer.getMobile());
        responseDto.setArea(saveCostumer.getArea().getPinCode()); // Assuming Area has getPinCode()
        return costumerDto;
    }
    public CostumerDto addAgent(Long id, AgentDto agentDto) {
        Optional<Agent> optionalAgent = agentRepository.findById(id);
        Optional<Costumervisit> optionalCostumer = costumervisitRepository.findById(id);

        if (optionalAgent.isPresent() && optionalCostumer.isPresent()) {
            Costumervisit costumervisit = optionalCostumer.get();
            Agent agent = optionalAgent.get();  // Get the Agent entity

            costumervisit.setAgent(agent);  // Set the actual Agent entity
            costumervisitRepository.save(costumervisit);

            // Creating response DTO
            CostumerDto costumerDto = new CostumerDto();
            costumerDto.setName(costumervisit.getName());
            costumerDto.setEmail(costumervisit.getEmail());
            costumerDto.setMobile(costumervisit.getMobile());
            costumerDto.setArea(costumervisit.getArea().getPinCode());  // Assuming Area has getPinCode()
            costumerDto.setAgent(agent.getName());  // Get the agent name from entity

            // SMS Code
            smsService.sendSms("+916264797904", "Your OTP is: 12345");

            return costumerDto;
        }

        if (!optionalAgent.isPresent() && optionalCostumer.isPresent()) {
            throw new RuntimeException("Agent not found with ID: " + id);
        }

        if (optionalAgent.isPresent() && !optionalCostumer.isPresent()) {
            throw new RuntimeException("Customer not found with ID: " + id);
        }

        throw new RuntimeException("Agent and Customer not found.");
    }


}
