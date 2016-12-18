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
        model.trainAvance();
        model.avertirAllObservateurs();
        for(Train t : model.train){
            t.nextPair();
        }
        System.out.print(model.toString());
    }
}
