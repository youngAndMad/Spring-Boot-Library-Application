package com.example.studentmanagmentsystem.repository;

import com.example.studentmanagmentsystem.entity.Connector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.logging.LogManager;

public interface ConnectorRepository extends JpaRepository<Connector,Long> {
}
