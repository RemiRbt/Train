/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package train;

import java.util.TimerTask;

/**
 *
 * @author remir
 */
public class TimerVue extends TimerTask {

    PlateauModel model;
    JFrameTrain vue;
    
    public TimerVue(JFrameTrain j, PlateauModel m) {
        model = m;
        vue = j;
    }
    
    @Override
    public void run() {
        String temp = "<html>";
        for(Ville t : model.villes){
            temp += t.toString();
            temp += "<br>";
            t.creerProduit();
        }
        temp += "</html>";
        vue.jLabel4.setText(temp);
        String tempProduitFini = model.produitFini[0] + ", " + model.produitFini[1] + ", " + model.produitFini[2] + ", " + model.produitFini[3];
        vue.jLabel6.setText(tempProduitFini);
        String tempMonstre = "";
        for(Monstre m : model.monstres){
            tempMonstre += m.hp;
        }
        vue.jLabel9.setText(tempMonstre);
    }
}
