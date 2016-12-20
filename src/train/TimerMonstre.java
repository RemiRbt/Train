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

    public TimerMonstre(PlateauModel m) {
        model = m;
    }
    
    @Override
    public void run() {
        model.monstreAvance();
        model.avertirAllObservateurs();
    }
}
