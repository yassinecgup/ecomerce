package com.example.demo.controller;

import com.example.demo.model.Panier;
import com.example.demo.model.Produit;
import com.example.demo.service.ProduitService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.Map;

@Controller
public class BoutiqueController {

    private final ProduitService produitService;

    public BoutiqueController(ProduitService produitService) {
        this.produitService = produitService;
    }

    @GetMapping("/produits")
    public String listeProduits(Model model) {
        model.addAttribute("produits", produitService.getAllProduits());
         //  session.removeAttribute("message");
        return "produits";
    }

    @PostMapping("/ajouter")
    public String ajouterAuPanier(@RequestParam Long id, HttpSession session) {
        Panier panier = (Panier) session.getAttribute("panier");
        if (panier == null) panier = new Panier();
        Produit produit = produitService.getProduitById(id);
        if (produit != null) {
            panier.ajouterProduit(produit);
            session.setAttribute("message", "Le produit '" + produit.getNom() + "' a été ajouté au panier avec succès!");
        }
  
        session.setAttribute("panier", panier);
     
    
  
        return "redirect:/produits";
    }

    /*@GetMapping("/panier")
    public String voirPanier(HttpSession session, Model model) {
        Panier panier = (Panier) session.getAttribute("panier");
        if (panier == null) panier = new Panier();
        model.addAttribute("panier", panier.getItems());
        return "panier";
    }*/

    @PostMapping("/modifier")
    public String modifierQuantite(@RequestParam Long id, @RequestParam int quantite, HttpSession session) {
        Panier panier = (Panier) session.getAttribute("panier");
        if (panier != null) {
            Produit produit = produitService.getProduitById(id);
            if (produit != null) panier.modifierQuantite(produit, quantite);
            session.setAttribute("panier", panier);
        }
        return "redirect:/panier";
    }

  @PostMapping("/supprimer")
public String supprimerProduit(@RequestParam Long id, HttpSession session) {
    Panier panier = (Panier) session.getAttribute("panier");
    if (panier == null) return "redirect:/panier";

    Produit produit = produitService.getProduitById(id);
    if (produit != null) {
        panier.getItems().remove(produit); // Direct map removal
        session.setAttribute("panier", panier);
    }

    return "redirect:/panier";
}

@GetMapping("/panier")
public String showPanier(HttpSession session, Model model) {
    Panier panier = (Panier) session.getAttribute("panier");
    System.out.println("CART IN VIEW: " + (panier != null ? panier.getItems() : "null"));
    model.addAttribute("panier", panier != null ? panier.getItems() : Collections.emptyMap());
     double total = 0.0;
    
    if (panier != null) {
        for (Map.Entry<Produit, Integer> entry : panier.getItems().entrySet()) {
            total += entry.getKey().getPrix() * entry.getValue();
        }
    }
    
    model.addAttribute("total", total);
    return "panier";
}
}
