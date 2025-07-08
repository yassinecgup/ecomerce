package com.example.demo.repository;
// --- REPOSITORY ---


import com.example.demo.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
}