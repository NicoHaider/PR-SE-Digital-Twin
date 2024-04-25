package com.example.DigitalTwin.service;

import com.example.DigitalTwin.model.components.Light;
import com.example.DigitalTwin.repository.LightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LightService {

    private final LightRepository lightRepository;

    @Autowired
    public LightService(LightRepository lightRepository) {
        this.lightRepository = lightRepository;
    }

    public Light saveOrUpdateLight(Light light) {
        return lightRepository.save(light);
    }

    public Optional<Light> findLightById(Long id) {
        return lightRepository.findById(id);
    }

    public List<Light> findAllLights() {
        return lightRepository.findAll();
    }

    public void deleteLightById(Long id) {
        lightRepository.deleteById(id);
    }
}
