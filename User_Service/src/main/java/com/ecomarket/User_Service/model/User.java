package com.ecomarket.User_Service.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID autoincremental
    private Long id;

    private String nombre;

    @Column(unique = true)
    private String email;

    private String direccionEnvio;

    private String rol; // admin, gerente, cliente, etc.

    private Boolean activo;

    // Relación uno a muchos con historial de compras
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Purchase> historialCompras;
}

