package com.example.DigitalTwin.service;

import com.example.DigitalTwin.model.components.Fan;
import com.example.DigitalTwin.repository.FanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FanService {

    private final FanRepository fanRepository;

    @Autowired
    public FanService(FanRepository fanRepository) {
        this.fanRepository = fanRepository;
    }

    public Fan saveOrUpdateFan(Fan fan) {
        return fanRepository.save(fan);
    }

    public Optional<Fan> findFanById(Long id) {
        return fanRepository.findById(id);
    }

    public List<Fan> findAllFans() {
        return fanRepository.findAll();
    }

    public void deleteFanById(Long id) {
        fanRepository.deleteById(id);
    }
}
