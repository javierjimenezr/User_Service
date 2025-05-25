package com.ecomarket.User_Service.controller;

import com.ecomarket.User_Service.model.User;
import com.ecomarket.User_Service.model.Purchase;
import com.ecomarket.User_Service.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Obtener todos los usuarios
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id).orElse(null);
    }

    // Crear usuario
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // Extra: historial de compras
    @GetMapping("/{id}/historial")
    public List<Purchase> getHistorial(@PathVariable Long id) {
        return userService.getHistorialDeCompras(id);
    }

    // ✅ Nuevo endpoint: agregar compra a usuario
    @PostMapping("/{id}/purchases")
    public Purchase addPurchaseToUser(@PathVariable Long id, @RequestBody Purchase purchase) {
        return userService.agregarCompraAUsuario(id, purchase);
    }
}