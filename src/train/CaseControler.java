/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package train;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author remir
 */
public class CaseControler extends MouseAdapter {
    
    PlateauModel model;
    int x;
    int y;
    int type;
    /*
    types :
    8 : non-constructible
    9 : adjacent ville(non contru)
    0 : rien
    1 : ville
    2 : rail
    3 : train
    4 : monstre   
    */
    
    public CaseControler(PlateauModel _model, int _x, int _y, int _type) {
        model = _model;
        x = _x;
        y = _y;
        type = _type;
    }
    
    public void mouseClicked(MouseEvent e) {
        if(model.board[x][y] == 0 ){
            System.out.println(x + " " + y);
            type = 2;
            model.action(this);
            System.out.print(model.toString());
        } else if (model.board[x][y] == 2) {
            System.out.println(x + " " + y);
            type = 0;
            model.action(this);
            System.out.print(model.toString());
        }
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getType() {
        return type;
    }
    
}
