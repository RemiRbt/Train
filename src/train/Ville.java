/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package train;

import java.util.ArrayList;

/**
 *
 * @author remir
 */
public class Ville {
    
    PlateauModel model;
    
    int x;
    int y;
    int[] stockBase = {0, 0, 0, 0, 0};
    int[] stock;
    int production;
    int debit;
    /*
    0: bois
    1: fer
    2: laine
    3: redstone
    4: pierre
    */
    
    public Ville(PlateauModel m, int _x, int _y, int _production, int _debit) {
        model = m;
        x = _x;
        y = _y;
        stock = stockBase;
        production = _production;
        debit = _debit;
    }
    
    public void creerProduit() {
        if((stock[0] >= 100) && (stock[1] >= 300)) {
            model.produitFini[0]++;
            stock[0] -= 100;
            stock[1] -= 300;
        }
        if((stock[1] >= 100) && (stock[2] >= 250)) {
            model.produitFini[1]++;
            stock[1] -= 100;
            stock[2] -= 250;
        }
        if((stock[0] >= 50) && (stock[3] >= 300)) {
            model.produitFini[2]++;
            stock[0] -= 50;
            stock[3] -= 300;
        }
        if((stock[3] >= 150) && (stock[4] >= 300)) {
            model.produitFini[0]++;
            stock[3] -= 100;
            stock[4] -= 300;
        }
    }
    
    public void production() {
        stock[production] += debit;
    }
    
    public int totalStock() {
        int temp = 0;
        for(int i = 0; i < stock.length ; i++) {
            temp += stock[i];
        }
        return temp;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    @Override
    public String toString() {
        String temp = "Stock: " + stock[0] + ", " + stock[1] + ", " + stock[2] + ", " + stock[3] + ", " + stock[4] + "\n";
        return temp;
    }
    
}
