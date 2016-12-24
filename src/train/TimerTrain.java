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
public class TimerTrain extends TimerTask {
    
    PlateauModel model;

    public TimerTrain(PlateauModel m) {
        model = m;
    }
    
    @Override
    public void run() {
        for(Ville t : model.villes){
            t.production();
            System.out.print(t.toString());
        }
        model.actionTrainVille();
        model.trainAvance();
        for(Train t : model.train){
            t.nextPair();
        }
        System.out.print(model.toString());
        model.avertirAllObservateurs();
    }
}
