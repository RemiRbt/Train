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
    
    public Ville(int _x, int _y, int _production, int _debit) {
        x = _x;
        y = _y;
        stock = stockBase;
        production = _production;
        debit = _debit;
    }
    
    public void creerProduit() {
        
    }
    
    public void productionMP() {
        stock[production] += debit;
    }
    
}
