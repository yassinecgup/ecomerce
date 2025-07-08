package com.example.demo.model;

import java.util.*;

public class Panier {
    private Map<Produit, Integer> items = new HashMap<>();

    public void ajouterProduit(Produit produit) {
        items.put(produit, items.getOrDefault(produit, 0) + 1);
    }

    public void modifierQuantite(Produit produit, int quantite) {
        if (quantite <= 0) {
            items.remove(produit);
        } else {
            items.put(produit, quantite);
        }
    }

    public void supprimerProduit(Produit produit) {
        items.remove(produit);
    }

    public Map<Produit, Integer> getItems() {
        return items;
    }
}