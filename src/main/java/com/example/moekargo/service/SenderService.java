package com.example.moekargo.service;

import com.example.moekargo.model.Sender;

import java.util.List;
import java.util.Optional;

public interface SenderService {
    Sender save(Sender sender);
    List<Sender> findAll();
    Optional<Sender> findById(Long id);
}
