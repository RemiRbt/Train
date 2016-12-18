/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package train;

/**
 *
 * @author remir
 */
public class Train {
    
    LigneTrain ligne;
    int position;
    int[] pairPosition;
    int lastPosition;
    
    public Train(LigneTrain l) {
        ligne = l;
        position = 0;
        int[] firstPair = {ligne.ligneTrain.get(0)[0], ligne.ligneTrain.get(0)[1]};
        pairPosition = firstPair;
    }
    
    public void nextPosition() {
        if (position == ligne.ligneTrain.size()-1) {
            lastPosition = position;
            position--;
        } else if (position == 0) {
            lastPosition = position;
            position++;
        } else if ((lastPosition < position) && (position != ligne.ligneTrain.size()-1)) {
            lastPosition = position;
            position++;
        } else if ((lastPosition > position) && (position != 0)) {
            lastPosition = position;
            position--;
        }
    }
    
    public void nextPair() {
        int[] temp = {ligne.ligneTrain.get(position)[0], ligne.ligneTrain.get(position)[1]};
        nextPosition();
        pairPosition = temp;
    }
    
    @Override
    public String toString() {
        String temp = "Position: " + lastPosition + " | " + pairPosition[0] + ", " + pairPosition[1];
        return temp;
    }
    
}
