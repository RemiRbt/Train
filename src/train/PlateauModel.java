/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package train;

import java.util.ArrayList;
import java.util.Timer;

/**
 *
 * @author remir
 */
public class PlateauModel {
    int[][] board;
    final int tailleX = 10;
    final int tailleY = 20;
    
    ArrayList<Observateur> observation;
    ArrayList<LigneTrain> lignes;
    ArrayList<Train> train;
    ArrayList<Ville> villes;
    
    public PlateauModel() {
        board = new int[tailleX][tailleY];
        observation = new ArrayList<Observateur>();
        lignes = new ArrayList<LigneTrain>();
        train = new ArrayList<Train>();
        villes = new ArrayList<Ville>();
        
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTrain(this), 0, 1000);
        timer.scheduleAtFixedRate(new TimerMonstre(this), 0, 1000);
    }
    
    void register(Observateur o) {
        observation.add(o);
    }
    
    void unregister(Observateur o) {
        observation.remove(o);
    }
    
    void avertirAllObservateurs() {
        for(Observateur o : observation){
            o.avertir();
        }
    }
    
    void avertirNewGameAllObservateurs() {
        for(Observateur o : observation){
            o.avertirNewGame();
        }
    }
    
    void nouvellePartie(){
        for(int i=0;i<tailleX;i++){
            for(int j=0;j<tailleY;j++){
                board[i][j] = 0;
                //couronne de montagne autour du plateau 
                if(i==0 || j == 0 || i == tailleX-1|| j == tailleY-1){
                    action(new CaseControler(this, i, j, 8));
                } 
            }
        }
        action(new CaseControler(this, 3, 5, 1));
        Ville ville1 = new Ville(3, 5, 1, 10);
        villes.add(ville1);
        action(new CaseControler(this, 5, 10, 1));
        Ville ville2 = new Ville(5, 10, 2, 10);
        villes.add(ville2);
        action(new CaseControler(this, 2, 12, 1));
        Ville ville3 = new Ville(2, 12, 3, 10);
        villes.add(ville3);
        action(new CaseControler(this, 7, 16, 1));
        Ville ville4 = new Ville(7, 16, 4, 10);
        villes.add(ville4);
        action(new CaseControler(this, 1, 0, 8));
        action(new CaseControler(this, 1, 1, 8));
        action(new CaseControler(this, 1, 2, 8));
        action(new CaseControler(this, 6, 3, 8));
        action(new CaseControler(this, 6, 4, 8));
        action(new CaseControler(this, 3, 8, 8));
        action(new CaseControler(this, 3, 9, 8));
        
        //monstre
        //action(new CaseControler(this, 4, 3, 4));
        
        avertirNewGameAllObservateurs();
    }
    
    public void action(CaseControler c) {
        board[c.getX()][c.getY()] = c.getType();
        updatePlateau();
        ligneTrain();
        avertirAllObservateurs();
    }
    
    public void updatePlateau() {
        for(int i=0;i<tailleX;i++){
            for(int j=0;j<tailleY;j++){
                //si rail : l'entourer de case grise (les 2 cases qui ne sont pas des rails adjacents)
                if(board[i][j] == 2) {
                    int[] adjacents = new int[4];
                    int adjacentCompt = 0;  
                    if((board[i-1][j] == 2) || (board[i-1][j] == 1) || (board[i-1][j] == 3)) {
                        adjacents[0] = 1;
                        adjacentCompt++;
                    }
                    if((board[i+1][j] == 2) || (board[i+1][j] == 1) || (board[i+1][j] == 3)) {
                        adjacents[1] = 1;
                        adjacentCompt++;
                    }
                    if((board[i][j-1] == 2) || (board[i][j-1] == 1) || (board[i][j-1] == 3)) {
                        adjacents[2] = 1;
                        adjacentCompt++;
                    }
                    if((board[i][j+1] == 2) || (board[i][j+1] == 1) || (board[i][j+1] == 3)) {
                        adjacents[3] = 1;
                        adjacentCompt++;
                    }
                    if(adjacentCompt == 2) {
                        for(int k=0;k<adjacents.length;k++) {
                            if(adjacents[0] == 0) {
                                if(board[i-1][j] == 0){
                                    board[i-1][j] = 9;
                                }
                            }
                            if(adjacents[1] == 0) {
                                if(board[i+1][j] == 0){
                                    board[i+1][j] = 9;
                                }
                            }
                            if(adjacents[2] == 0) {
                                if(board[i][j-1] == 0){
                                    board[i][j-1] = 9;
                                }
                            }
                            if(adjacents[3] == 0) {
                                if(board[i][j+1] == 0){
                                    board[i][j+1] = 9;
                                }
                            }
                        }
                    }
                }
                // retirer case grise si plus de rail autour
                if(board[i][j] == 9) {
                    if((board[i-1][j] != 2) && (board[i+1][j] != 2) && (board[i][j-1] != 2) && (board[i][j+1] != 2) && (board[i-1][j] != 3) && (board[i+1][j] != 3) && (board[i][j-1] != 3) && (board[i][j+1] != 3)) {
                        board[i][j] = 0;
                    }
                }
            }
        }
    }
    
    public void ligneTrain() {
        lignes.clear();
        train.clear();
        for(int i=0;i<tailleX;i++){
            for(int j=0;j<tailleY;j++){
                //remplace les train par des rails = on enleve les trains
                if(board[i][j] == 3) {
                    board[i][j] = 2;
                }
                //si case est une ville
                if(board[i][j] == 1) {
                    int x = i;
                    int y = j;
                    // dclt = dernière case ligne train
                    if ((board[x-1][y] == 2) || (board[x-1][y] == 3)) {
                        int[] testPair = {x-1, y};
                        if(!autreLigneContains(testPair)) {
                            LigneTrain ligne1 = new LigneTrain();
                            int[] dclt = {x-1, y};
                            ligne1.ajouterALigne(dclt);
                            lignes.add(ligne1);
                            while(RailAdjacent(dclt, ligne1) == 2) {
                                int[] caseSuivante = ligneTrainCaseSuivante(dclt, ligne1);
                                ligne1.ajouterALigne(caseSuivante);
                                lignes.remove(lignes.get(lignes.size() - 1));
                                lignes.add(ligne1);
                                dclt = caseSuivante;
                                if(ligneTrainFinie(ligne1)) {
                                    ligne1.ligneFinie();
                                }
                            }
                        }
                    }
                    if ((board[x+1][y] == 2) || (board[x+1][y] == 3)) {
                        int[] testPair = {x+1, y};
                        if(!autreLigneContains(testPair)) {
                            LigneTrain ligne1 = new LigneTrain();
                            int[] dclt = {x+1, y};
                            ligne1.ajouterALigne(dclt);
                            lignes.add(ligne1);
                            while(RailAdjacent(dclt, ligne1) == 2) {
                                int[] caseSuivante = ligneTrainCaseSuivante(dclt, ligne1);
                                ligne1.ajouterALigne(caseSuivante);
                                lignes.remove(lignes.get(lignes.size() - 1));
                                lignes.add(ligne1);
                                dclt = caseSuivante;
                                if(ligneTrainFinie(ligne1)) {
                                    ligne1.ligneFinie();
                                }
                            }
                        }
                    }
                    if ((board[x][y-1] == 2) || (board[x][y-1] == 3)) {
                        int[] testPair = {x, y-1};
                        if(!autreLigneContains(testPair)) {
                            LigneTrain ligne1 = new LigneTrain();
                            int[] dclt = {x, y-1};
                            ligne1.ajouterALigne(dclt);
                            lignes.add(ligne1);
                            while(RailAdjacent(dclt, ligne1) == 2) {
                                int[] caseSuivante = ligneTrainCaseSuivante(dclt, ligne1);
                                ligne1.ajouterALigne(caseSuivante);
                                lignes.remove(lignes.get(lignes.size() - 1));
                                lignes.add(ligne1);
                                dclt = caseSuivante;
                                if(ligneTrainFinie(ligne1)) {
                                    ligne1.ligneFinie();
                                }
                            }
                        }
                    }
                    if ((board[x][y+1] == 2) || (board[x][y+1] == 3)) {
                        int[] testPair = {x, y+1};
                        if(!autreLigneContains(testPair)) {
                            LigneTrain ligne1 = new LigneTrain();
                            int[] dclt = {x, y+1};
                            ligne1.ajouterALigne(dclt);
                            lignes.add(ligne1);
                            while(RailAdjacent(dclt, ligne1) == 2) {
                                int[] caseSuivante = ligneTrainCaseSuivante(dclt, ligne1);
                                ligne1.ajouterALigne(caseSuivante);
                                lignes.remove(lignes.get(lignes.size() - 1));
                                lignes.add(ligne1);
                                dclt = caseSuivante;
                                if(ligneTrainFinie(ligne1)) {
                                    ligne1.ligneFinie();
                                }
                            }
                        }
                    }
                }
            }
        }
        for(LigneTrain l : lignes){
            if(l.ligneFinie == true) {
                Train t1 = new Train(l);
                train.add(t1);
            }
        }
    }
    
    public int RailAdjacent(int[] pair, LigneTrain lt) {
        int x = pair[0];
        int y = pair[1];
        int[] nextPairTop = {x-1, y}; 
        int[] nextPairBottom = {x+1, y}; 
        int[] nextPairLeft = {x, y-1}; 
        int[] nextPairRight = {x, y+1}; 
        int temp = 0;
        if((board[x-1][y] == 2) && (!lt.contains(nextPairTop))) {
            temp = board[x-1][y];
        } else if((board[x+1][y] == 2) && (!lt.contains(nextPairBottom))) {
            temp = board[x+1][y];
        } else if((board[x][y-1] == 2) && (!lt.contains(nextPairLeft))) {
            temp = board[x][y-1];
        } else if((board[x][y+1] == 2) && (!lt.contains(nextPairRight))) {
            temp = board[x][y+1];
        }
        
        return temp;
    }
    
    public int[] ligneTrainCaseSuivante(int[] pair, LigneTrain lt) {
        int x = pair[0];
        int y = pair[1];
        int[] nextPairTop = {x-1, y}; 
        int[] nextPairBottom = {x+1, y}; 
        int[] nextPairLeft = {x, y-1}; 
        int[] nextPairRight = {x, y+1}; 
        int[] temp = {1,8};
        if((board[x-1][y] == 2) && (!lt.contains(nextPairTop))) {
            temp = nextPairTop;
        } else if((board[x+1][y] == 2) && (!lt.contains(nextPairBottom))) {
            temp = nextPairBottom;
        } else if((board[x][y-1] == 2) && (!lt.contains(nextPairLeft))) {
            temp = nextPairLeft;
        } else if((board[x][y+1] == 2) && (!lt.contains(nextPairRight))) {
            temp = nextPairRight;
        }
        
        return temp;
    }
    
    public boolean autreLigneContains(int[] pair) {
        boolean temp = false;
        for(LigneTrain l : lignes){
            if(l.contains(pair)) {
                temp = true;
            }
        }
        return temp;
    }
    
    public boolean ligneTrainFinie(LigneTrain lt) {
        int x = lt.getLast()[0];
        int y = lt.getLast()[1];
        boolean temp = false;
        if (board[x-1][y] == 1) {
            temp = true;
        } else if (board[x+1][y] == 1) {
            temp = true;
        } else if (board[x][y-1] == 1) {
            temp = true;
        } else if (board[x][y+1] == 1) {
            temp = true;
        }
        
        return temp;
    }
    
    public void trainAvance() {
        for(Train t : train){
            for(int i = 0; i < t.ligne.ligneTrain.size(); i++){
                board[t.ligne.ligneTrain.get(i)[0]][t.ligne.ligneTrain.get(i)[1]] = 2;
            }
            board[t.pairPosition[0]][t.pairPosition[1]] = 3;
        }
    }
    
    public void monstreAvance() {
        for(int i=0;i<tailleX;i++){
            for(int j=0;j<tailleY;j++){
                int x = i;
                int y = j;
                if(board[x][y] == 4) {
                    
                    //wtf -y marche mais +y fait tout bugger
                    board[x][y+1] = 4;
                    board[x][y] = 0;
                    
                }
            }
        }
    }
    
    @Override
    public String toString() {        
        String temp = "";
        for(int i=0;i<tailleX;i++){
            for(int j=0;j<tailleY;j++){
                temp += "" + board[i][j];
            }
            temp += "\n";
        }
        int nbTemp = 0;
        for(LigneTrain l : lignes){
            if(l.ligneFinie == true) {
                nbTemp++;
                temp += "Ligne de train finie: " + nbTemp + " | " + l.toString();
                temp += "\n";
            }
        }
        return temp;
    }
    
}
