package com.ecomarket.User_Service.service;




import com.ecomarket.User_Service.model.User;
import com.ecomarket.User_Service.model.Purchase;
import com.ecomarket.User_Service.repository.UserRepository;
import com.ecomarket.User_Service.repository.PurchaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PurchaseRepository purchaseRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepo.findById(id);
    }

    public User createUser(User user) {
        user.setActivo(true); // por defecto activo
        return userRepo.save(user);
    }

    public User updateUser(Long id, User userData) {
        return userRepo.findById(id).map(user -> {
            user.setNombre(userData.getNombre());
            user.setEmail(userData.getEmail());
            user.setDireccionEnvio(userData.getDireccionEnvio());
            user.setRol(userData.getRol());
            user.setActivo(userData.getActivo());
            return userRepo.save(user);
        }).orElse(null);
    }

    public boolean deleteUser(Long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Purchase> getHistorialDeCompras(Long userId) {
        return purchaseRepo.findByUsuarioId(userId);
    }

    // ✅ Nuevo método para agregar una compra a un usuario
    public Purchase agregarCompraAUsuario(Long userId, Purchase purchase) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            purchase.setUsuario(user);
            if (purchase.getFecha() == null) {
                purchase.setFecha(LocalDateTime.now()); // Asigna fecha actual si no se proporciona
            }
            return purchaseRepo.save(purchase);
        } else {
            throw new RuntimeException("Usuario no encontrado con ID: " + userId);
        }
    }
}
