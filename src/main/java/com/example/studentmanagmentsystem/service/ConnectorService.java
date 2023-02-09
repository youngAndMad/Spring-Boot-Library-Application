package com.example.studentmanagmentsystem.service;

import com.example.studentmanagmentsystem.entity.Connector;
import com.example.studentmanagmentsystem.repository.ConnectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;


@Service
public class ConnectorService {
    private final ConnectorRepository connectorRepository;
    private final BookService bookService;


    @Autowired
    public ConnectorService(ConnectorRepository connectorRepository,
                            BookService bookService) {
        this.connectorRepository = connectorRepository;
        this.bookService = bookService;
    }

    public void registerBook(Connector connector) {
        connectorRepository.save(connector);
    }

    public List<Connector> getAllOrders() {
       List<Connector> connectors =  connectorRepository.findAll();
       connectors.stream().forEach(connector -> {
            Long time = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) - connector.getTakenAt().toEpochSecond(ZoneOffset.UTC);
            if(time > 86400)
                connector.setExpired(true);});
       return connectors;

    }

    public Optional<Connector> getOrder(Long id) {
        return connectorRepository.findById(id);
    }

    public void deleteById(Long id) {
        bookService.incrementQuantity(getOrder(id).get().getBookId());
        connectorRepository.deleteById(id);
    }

}
