package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Produit {
    @Id
    private Long id;
    private String nom;
    private double prix;
    private String image;

    public Produit() {}

    public Produit(Long id, String nom, double prix, String image) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.image = image;
    }

    public Long getId() { return id; }
    public String getNom() { return nom; }
    public double getPrix() { return prix; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public void setId(Long id) { this.id = id; }
    public void setNom(String nom) { this.nom = nom; }
    public void setPrix(double prix) { this.prix = prix; }


    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", image='" + image + '\'' +
                '}';
    }

  @Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Produit produit = (Produit) o;
    return Objects.equals(id, produit.id);
}

@Override
public int hashCode() {
    return Objects.hash(id);
}
}
