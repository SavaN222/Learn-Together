package com.nakaradasava.learntogether.service;

import com.nakaradasava.learntogether.entity.ConfirmationToken;
import com.nakaradasava.learntogether.repository.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfirmationTokenService {

    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public void deleteConfirmationToken(int id) {
        confirmationTokenRepository.deleteById(id);
    }

    public Optional<ConfirmationToken> findConfirmationTokenByToken(String token) {
        return confirmationTokenRepository.findConfirmationTokenByConfirmationToken(token);
    }

}
