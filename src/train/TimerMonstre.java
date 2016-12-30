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
public class TimerMonstre extends TimerTask {
    
    PlateauModel model;
    int compteur;
    int nbMonstre;

    public TimerMonstre(PlateauModel m) {
        model = m;
        compteur = 0;
    }
    
    @Override
    public void run() {
        nbMonstre = 0;
        for(Monstre m : model.monstres){
            m.nextCase();
            nbMonstre++;
        }
        if(nbMonstre == 0) {
            compteur++;
        }
        if((compteur >= 20) && (nbMonstre == 0)) {
            model.action(new CaseControler(model, 1, 18, 4));
            Monstre monstre = new Monstre(model, 1, 18);
            model.monstres.add(monstre);
            compteur = 0;
        }
        model.monstreAvance();
        model.avertirAllObservateurs();
    }
}
