package com.example.DigitalTwin.service;

import com.example.DigitalTwin.model.components.Window;
import com.example.DigitalTwin.repository.WindowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WindowService {

    private final WindowRepository windowRepository;

    @Autowired
    public WindowService(WindowRepository windowRepository) {
        this.windowRepository = windowRepository;
    }

    public Window saveOrUpdateWindow(Window window) {
        return windowRepository.save(window);
    }

    public Optional<Window> findWindowById(Long id) {
        return windowRepository.findById(id);
    }

    public List<Window> findAllWindows() {
        return windowRepository.findAll();
    }

    public void deleteWindowById(Long id) {
        windowRepository.deleteById(id);
    }
}
