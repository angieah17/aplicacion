package com.midominio.group.app.spring.mongo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.midominio.group.app.spring.mongo.entity.TestLog;
import com.midominio.group.app.spring.mongo.repository.TestLogRepository;

@Service
public class TestLogService {

    private final TestLogRepository testLogRepository;

    public TestLogService(TestLogRepository testLogRepository) {
        this.testLogRepository = testLogRepository;
    }

    public TestLog saveLog(String username, Double nota) {
        TestLog testLog = new TestLog(username, LocalDateTime.now(), nota);
        return testLogRepository.save(testLog);
    }

    public List<TestLog> findAll() {
        return testLogRepository.findAll();
    }

    public List<TestLog> findByUsername(String username) {
        return testLogRepository.findByUsernameOrderByFechaDesc(username);
    }
}
