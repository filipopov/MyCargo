package com.example.moekargo.service;

import com.example.moekargo.model.Receiver;

import java.util.List;
import java.util.Optional;

public interface ReceiverService {
    Receiver save(Receiver receiver);
    List<Receiver> findAll();
    Optional<Receiver> findById(Long id);
}
