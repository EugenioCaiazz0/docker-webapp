package com.dockergit.backend.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterController {

    @GetMapping("/api/counter")
    public Map<String, String> counter() { // Map perché Spring la converte automaticamente in JSON

        return Map.of(
                "message", "Backend raggiunto correttamente"
        );

    }

}