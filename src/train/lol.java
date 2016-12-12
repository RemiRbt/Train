public class djsivqs {

public void ligneTrain() {
        for(int i=0;i<tailleX;i++){
            for(int j=0;j<tailleY;j++){
                //si case est une ville
                if(board[i][j] == 1) {
                    //tant que la case adjacente n'est pas une ville ou que 3 adjacentes sont différentes de rail ou ville
                    boolean continuer = true;
                    while(continuer) {
                        int x = i;
                        int y = j;
                        LigneTrain ligne1 = new LigneTrain();
                        //parcourt des suite de rails
                        int casePrecedente = board[x][y];
                        if((board[x-1][y] == 2) || (board[x-1][y] == 1) && (board[x-1][y] != casePrecedente)) {
                            int[] pair = {x, y};
                            ligne1.ajouterALigne(pair);
                            x--;
                            casePrecedente = board[x-1][y];
                        }
                        if((board[x+1][y] == 2) || (board[x+1][y] == 1) && (board[x+1][y] != casePrecedente)) {
                            int[] pair = {x, y};
                            ligne1.ajouterALigne(pair);
                            x++;
                            casePrecedente = board[x+1][y];
                        }
                        if((board[x][y-1] == 2) || (board[x][y-1] == 1) && (board[x][y-1] != casePrecedente)) {
                            int[] pair = {x, y};
                            ligne1.ajouterALigne(pair);
                            y--;
                            casePrecedente = board[x][y-1];
                        }
                        if((board[x][y+1] == 2) || (board[x][y+1] == 1) && (board[x][y+1] != casePrecedente)) {
                            int[] pair = {x, y};
                            ligne1.ajouterALigne(pair);
                            y++;
                            casePrecedente = board[x][y+1];
                        }
                        //si plus de 3 case adjacentes au rail sont des cases autre que rail ou ville alors stop
                        int adjacentCompt = 0;
                        if((board[x-1][y] != 2) || (board[x-1][y] != 1)) {
                            adjacentCompt++;
                        }
                        if((board[x+1][y] != 2) || (board[x+1][y] != 1)) {
                            adjacentCompt++;
                        }
                        if((board[x][y-1] != 2) || (board[x][y-1] != 1)) {
                            adjacentCompt++;
                        }
                        if((board[x][y+1] != 2) || (board[x][y+1] != 1)) {
                            adjacentCompt++;
                        }
                        if(adjacentCompt > 2) {
                            continuer = false;
                        } else if ((board[x-1][y] == 1) || (board[x+1][y] == 1) || (board[x][y-1] == 1) || (board[x][y+1] == 1)) {
                            continuer = false;
                            ligne1.ligneFinie();
                            lignes.add(ligne1);
                        }
                    }
                }
            }
        }
    }
}

public void ligneTrain() {
        for(int i=0;i<tailleX;i++){
            for(int j=0;j<tailleY;j++){
                //si case est une ville
                if(board[i][j] == 1) {
                    int x = i;
                    int y = j;
                    if (board[x-1][y] == 2) {
                        LigneTrain ligne1 = new LigneTrain();
                        int[] pair1 = {x-1, y};
                        ligne1.ajouterALigne(pair1);
                        lignes.add(ligne1);
                        System.out.println("Case bonne : " + (x-1) + ", " + y + "#1");
                        x = x-1;
                        for(LigneTrain l : lignes){
                            for(int[] ll: l.ligneTrain) {
                                System.out.println("#3");
                                if ((board[x-1][y] == 2) && (x-1 != ll[0]) && (y != ll[1])) {
                                    int[] pair2 = {x-1, y};
                                    ligne1.ajouterALigne(pair2);
                                    lignes.add(ligne1);
                                    System.out.println("Case bonne : " + (x-1) + ", " + y + "#3");
                                    x = x-1;
                                }
                            }
                        }
                    }
                }
            }
        }
    }