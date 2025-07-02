package com.app.app.service;

import com.app.app.entity.Model;
import com.app.app.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelService {
    private final ModelRepository modelRepository;

    @Autowired
    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }


    public Model addModel(Model model) {
        Model cheack = modelRepository.findModelByName(model.getName());
        if (cheack == null) {
            return modelRepository.save(model);
        } else {
            return cheack;
        }
    }


    public Model deleteModel(Model model) {
        Model cheack = modelRepository.findModelByName(model.getName());
        Model model2 = new Model();
        if (cheack != null) {
            model2.setId(cheack.getId());
            model2.setName(cheack.getName());
            modelRepository.deleteById(cheack.getId());
            return model2;

        } else {
            return model;
        }

    }

    public Model getModel(String model) {
        return modelRepository.findModelByName(model);// Directly return the list (never null)
    }


}
