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
        }
        temp += "</html>";
        vue.jLabel4.setText(temp);
    }
}
