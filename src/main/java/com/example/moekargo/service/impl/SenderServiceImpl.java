package com.example.moekargo.service.impl;

import com.example.moekargo.model.Sender;
import com.example.moekargo.repository.SenderRepository;
import com.example.moekargo.service.SenderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SenderServiceImpl implements SenderService {

    private final SenderRepository senderRepository;

    @Override
    public Sender save(Sender sender) {
        return this.senderRepository.save(sender);
    }

    @Override
    public List<Sender> findAll() {
        return this.senderRepository.findAll();
    }

    @Override
    public Optional<Sender> findById(Long id) {
        return this.senderRepository.findById(id);
    }
}
