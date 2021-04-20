package com.nakaradasava.learntogether.repository.token;

import com.nakaradasava.learntogether.entity.token.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Integer> {
    Optional<ConfirmationToken> findConfirmationTokenByConfirmationToken(String token);
}