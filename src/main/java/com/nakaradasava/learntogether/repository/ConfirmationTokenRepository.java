package com.nakaradasava.learntogether.repository;

import com.nakaradasava.learntogether.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {
    public Optional<ConfirmationToken> findConfirmationTokenByConfirmationToken(String token);
}
