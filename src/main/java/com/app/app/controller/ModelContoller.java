package com.app.app.controller;

import com.app.app.entity.Model;
import com.app.app.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/model")

public class ModelContoller {

    @Autowired
    public ModelContoller(ModelService modelService) {
        this.modelService = modelService;
    }

    private ModelService modelService;


    @PostMapping("/addModel")
    public ResponseEntity<?> addModel(@RequestBody Model model) {
        Model existingOrNewModel = modelService.addModel(model);
        if (existingOrNewModel == model) {
            return new ResponseEntity<>(existingOrNewModel, HttpStatus.CREATED);
        } else if (existingOrNewModel != null) {
            return new ResponseEntity<>(existingOrNewModel, HttpStatus.ALREADY_REPORTED);
        }
        return new ResponseEntity<>("Failed to add model", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/deleteModel")
    public ResponseEntity<?> deleteModel(@RequestBody Model model) {
        Model deletedModel = modelService.deleteModel(model);

        if (deletedModel.getId() == null) {
            return new ResponseEntity<>("Model Not Found: " + model, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>("Model deleted successfully: " + deletedModel, HttpStatus.OK);
    }

    @GetMapping("/getModelByName")
    public ResponseEntity<?> getModel(@RequestParam String name) {
        Model models = modelService.getModel(name);

        if (models == null) {
            return new ResponseEntity<>("Model Not Found: " + name, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(models, HttpStatus.OK);
    }


}
