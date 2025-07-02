package com.app.app.service;

import com.app.app.entity.Transmission;
import com.app.app.repository.TransmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authorization.method.AuthorizeReturnObject;
import org.springframework.stereotype.Service;

@Service


public class TransmissionService {
    private TransmissionRepository transmissionRepository;
@Autowired
    public TransmissionService(TransmissionRepository transmissionRepository) {
        this.transmissionRepository = transmissionRepository;
    }

    public Transmission addTransmission(Transmission transmission) {
        Transmission check = transmissionRepository.findTransmissionByName(transmission.getName());
        if (check == null) {
            return transmissionRepository.save(transmission);
        } else {
            return check;
        }
    }

    public Transmission deleteTransmission(Transmission transmission) {
        Transmission check = transmissionRepository.findTransmissionByName(transmission.getName());
        Transmission transmission2 = new Transmission();
        if (check != null) {
            transmission2.setId(check.getId());
            transmission2.setName(check.getName());
            transmissionRepository.deleteById(check.getId());
            return transmission2;
        } else {
            return transmission;
        }
    }

    public Transmission getTransmission(String transmission) {
        return transmissionRepository.findTransmissionByName(transmission);// Directly return the model (never null)
    }

}
