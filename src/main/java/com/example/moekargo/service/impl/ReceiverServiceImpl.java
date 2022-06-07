package com.example.moekargo.service.impl;

import com.example.moekargo.model.Receiver;
import com.example.moekargo.repository.ReceiverRepository;
import com.example.moekargo.service.ReceiverService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReceiverServiceImpl implements ReceiverService {

    private final ReceiverRepository receiverRepository;

    @Override
    public Receiver save(Receiver receiver) {
        return this.receiverRepository.save(receiver);
    }

    @Override
    public List<Receiver> findAll() {
        return this.receiverRepository.findAll();
    }

    @Override
    public Optional<Receiver> findById(Long id) {
        return this.receiverRepository.findById(id);
    }
}
