package com.ecomarket.User_Service.repository;



import com.ecomarket.User_Service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Puedes agregar búsquedas personalizadas como findByEmail(String email)
}
