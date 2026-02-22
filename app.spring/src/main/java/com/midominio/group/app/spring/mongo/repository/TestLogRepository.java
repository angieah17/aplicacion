package com.midominio.group.app.spring.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.midominio.group.app.spring.mongo.entity.TestLog;

public interface TestLogRepository extends MongoRepository<TestLog, String> {
	List<TestLog> findByUsernameOrderByFechaDesc(String username);
}
