package com.distribuida.bar_spring.dao;

import com.distribuida.bar_spring.model.PedidoGuest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PedidoGuestRepository extends JpaRepository<PedidoGuest, Integer> {
    Optional<PedidoGuest> findByToken(String token);
    boolean existsByToken(String token);
}