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
    4: 
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
        if((stock[0] >= 100) &&(stock[1] >= 50)) {
            model.produitFini[0]++;
            stock[0] -= 100;
            stock[1] -= 50;
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
