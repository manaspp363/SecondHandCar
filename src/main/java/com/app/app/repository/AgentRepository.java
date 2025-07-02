package com.app.app.repository;

import com.app.app.entity.Agent;
import com.app.app.payload.AgentDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgentRepository extends JpaRepository<Agent, Long> {
    Agent findByEmailAndMobile(String email, String mobile);
}