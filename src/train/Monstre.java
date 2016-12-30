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
public class Monstre {
    
    PlateauModel model;
    
    int x;
    int y;
    int hp;
    int[] pairPosition;
    int[] pairLastPosition;
    boolean actionFaite;
    boolean adjacentRail;
    
    public Monstre(PlateauModel m, int _x, int _y) {
        model = m;
        x = _x;
        y = _y;
        int[] firstPair = {_x, _y};
        pairPosition = firstPair;
        hp = 5;
    }
    
    public void nextCase() {
        actionFaite = false;
        adjacentRail = false;
        pairLastPosition = pairPosition;
        int rand = (int) ((Math.random() * 4) + 1);
        int[] nextPairTop = {x-1, y}; 
        int[] nextPairBottom = {x+1, y}; 
        int[] nextPairLeft = {x, y-1}; 
        int[] nextPairRight = {x, y+1};
        switch (rand) {
            case 1:
                if((model.board[x-1][y] == 0) || (model.board[x-1][y] == 2) || (model.board[x-1][y] == 9)) {
                    if(model.board[x-1][y] == 2) {
                        adjacentRail = true;
                    }
                    pairPosition = nextPairTop;
                    x--;
                    actionFaite = true;
                    break;
                }
                break;
            case 2:
                if((model.board[x+1][y] == 0) || (model.board[x+1][y] == 2) || (model.board[x+1][y] == 9)) {
                    if(model.board[x+1][y] == 2) {
                        adjacentRail = true;
                    }
                    pairPosition = nextPairBottom;
                    x++;
                    actionFaite = true;
                    break;
                }
                break;
            case 3:
                if((model.board[x][y-1] == 0) || (model.board[x][y-1] == 2) || (model.board[x][y-1] == 9)) {
                    if(model.board[x][y-1] == 2) {
                        adjacentRail = true;
                    }
                    pairPosition = nextPairLeft;
                    y--;
                    actionFaite = true;
                    break;
                }
                break;
            case 4:
                if((model.board[x][y+1] == 0) || (model.board[x][y+1] == 2) || (model.board[x][y+1] == 9)) {
                    if(model.board[x][y+1] == 2) {
                        adjacentRail = true;
                    }
                    pairPosition = nextPairRight;
                    y++;
                    actionFaite = true;
                    break;
                }
                break;
            default:
                break;
        }
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
}
