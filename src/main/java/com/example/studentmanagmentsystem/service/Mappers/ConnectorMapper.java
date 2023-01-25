package com.example.studentmanagmentsystem.service.Mappers;

import com.example.studentmanagmentsystem.entity.Connector;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectorMapper implements RowMapper<Connector> {
    @Override
    public Connector mapRow(ResultSet rs, int rowNum) throws SQLException {
       return new Connector(40,17);
    }
}
