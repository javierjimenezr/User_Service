package com.ecomarket.User_Service.repository;


import com.ecomarket.User_Service.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByUsuarioId(Long userId); // devuelve historial de un usuario
}
