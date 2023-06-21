package br.com.lucasomac.medvol.domain.consumer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface ConsumerRepository extends JpaRepository<Consumer, Long> {
    UserDetails findByLogin(String login);
}