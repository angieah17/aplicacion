package com.midominio.group.app.spring.mongo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.midominio.group.app.spring.mongo.entity.TestLog;
import com.midominio.group.app.spring.mongo.service.TestLogService;

@RestController
@RequestMapping("/api/mongo/logs")
public class TestLogController {

    private final TestLogService testLogService;

    public TestLogController(TestLogService testLogService) {
        this.testLogService = testLogService;
    }
    //http://localhost:8080/api/mongo/logs?nota=8.2
    @PostMapping
    public TestLog saveLog(@RequestParam Double nota, Principal principal) {
        String username = principal.getName();
        return testLogService.saveLog(username, nota);
    }

    @GetMapping
    public List<TestLog> findAll() {
        return testLogService.findAll();
    }

    @GetMapping("/me")
    public List<TestLog> findMine(Principal principal) {
        return testLogService.findByUsername(principal.getName());
    }
}
