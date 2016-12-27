/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package train;

import java.util.Timer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author remir
 */
public class JFrameTrain extends javax.swing.JFrame implements Observateur {

    private static final ImageIcon BLANC = new ImageIcon("./src/imgs/blanc.png");
    private static final ImageIcon MONTAGNE = new ImageIcon("./src/imgs/montagne.png");
    private static final ImageIcon NENUPHAR = new ImageIcon("./src/imgs/nenuphar.png");
    private static final ImageIcon NONCONSTRU = new ImageIcon("./src/imgs/gris.png");
    private static final ImageIcon MONSTRE = new ImageIcon("./src/imgs/monstre.png");
    private static final ImageIcon VILLE = new ImageIcon("./src/imgs/ville.png");
    
    private static final ImageIcon TRAIN = new ImageIcon("./src/imgs/train.png");
    private static final ImageIcon TRAIN2 = new ImageIcon("./src/imgs/train2.png");
    private static final ImageIcon TRAIN3 = new ImageIcon("./src/imgs/train3.png");
    private static final ImageIcon TRAIN4 = new ImageIcon("./src/imgs/train4.png");
    private static final ImageIcon TRAIN5 = new ImageIcon("./src/imgs/train5.png");
    private static final ImageIcon TRAIN6 = new ImageIcon("./src/imgs/train6.png");
    
    private static final ImageIcon RAIL = new ImageIcon("./src/imgs/rail.png");
    private static final ImageIcon RAIL2 = new ImageIcon("./src/imgs/rail2.png");
    private static final ImageIcon RAIL3 = new ImageIcon("./src/imgs/rail3.png");
    private static final ImageIcon RAIL4 = new ImageIcon("./src/imgs/rail4.png");
    private static final ImageIcon RAIL5 = new ImageIcon("./src/imgs/rail5.png");
    private static final ImageIcon RAIL6 = new ImageIcon("./src/imgs/rail6.png");
    
    final int tailleX = 10;
    final int tailleY = 20;
    JLabel[][] jboard;
    
    PlateauModel model;
        
    public JFrameTrain(PlateauModel _model) {
        model = _model;
        initComponents();
        
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerVue(this, model), 0, 1000);
        
        jeuPanel.setLayout(new java.awt.GridLayout(10, 20));
        
        jboard = new JLabel[tailleX][tailleY];
        
        for(int i=0;i<tailleX;i++){
            for(int j=0;j<tailleY;j++){
                // init case
                jboard[i][j] = new JLabel(BLANC);
                jboard[i][j].setIcon(BLANC);
                jeuPanel.add(jboard[i][j]);
                jboard[i][j].addMouseListener(new CaseControler(model, i, j, 0));
            }
        }
    }
    
    void initBoard() {
        updateBoard();
    }
    
    void updateBoard() {
        for(int i=0;i<tailleX;i++){
            for(int j=0;j<tailleY;j++){
                switch (model.board[i][j]) {
                    case 0:
                        jboard[i][j].setIcon(BLANC);
                        break;
                    case 1:
                        jboard[i][j].setIcon(VILLE);
                        break;
                    case 2:
                        calculRail(i, j);
                        break;
                    case 3:
                        calculTrain(i, j);
                        break;
                    case 4:
                        jboard[i][j].setIcon(MONSTRE);
                        break;
                    case 7:
                        jboard[i][j].setIcon(NENUPHAR);
                        break;
                    case 8:
                        jboard[i][j].setIcon(MONTAGNE);
                        break;
                    case 9:
                        jboard[i][j].setIcon(NONCONSTRU);
                        break;
                    default:
                        break;
                }
            }
        }
    }
    
    public void calculRail(int i, int j) {
        if (((model.board[i+1][j] >= 1) && (model.board[i+1][j] <= 3)) && ((model.board[i][j+1] >= 1) && (model.board[i][j+1] <= 3))) {
            jboard[i][j].setIcon(RAIL3);
        } else if (((model.board[i+1][j] >= 1) && (model.board[i+1][j] <= 3)) && ((model.board[i][j-1] >= 1) && (model.board[i][j-1] <= 3))) {
            jboard[i][j].setIcon(RAIL4);
        } else if (((model.board[i-1][j] >= 1) && (model.board[i-1][j] <= 3)) && ((model.board[i][j-1] >= 1) && (model.board[i][j-1] <= 3))) {
            jboard[i][j].setIcon(RAIL5);
        } else if (((model.board[i-1][j] >= 1) && (model.board[i-1][j] <= 3)) && ((model.board[i][j+1] >= 1) && (model.board[i][j+1] <= 3))) {
            jboard[i][j].setIcon(RAIL6);
        } else if (((model.board[i][j-1] >= 1) && (model.board[i][j-1] <= 3)) || ((model.board[i][j+1] >= 1) && (model.board[i][j+1] <= 3))) {
            jboard[i][j].setIcon(RAIL);
        } else if (((model.board[i-1][j] >= 1) && (model.board[i-1][j] <= 3)) || ((model.board[i+1][j] >= 1) && (model.board[i+1][j] <= 3))) {
            jboard[i][j].setIcon(RAIL2);
        } else {
            jboard[i][j].setIcon(RAIL);
        }
    }
    
    public void calculTrain(int i, int j) {
        if (((model.board[i+1][j] == 2) || (model.board[i+1][j] == 1)) && ((model.board[i][j+1] == 2) | (model.board[i][j+1] == 1))) {
            jboard[i][j].setIcon(TRAIN3);
        } else if (((model.board[i+1][j] == 2) || (model.board[i+1][j] == 1)) && ((model.board[i][j-1] == 2) || (model.board[i][j-1] == 1))) {
            jboard[i][j].setIcon(TRAIN4);
        } else if (((model.board[i-1][j] == 2) || (model.board[i-1][j] == 1)) && ((model.board[i][j-1] == 2) || (model.board[i][j-1] == 1))) {
            jboard[i][j].setIcon(TRAIN5);
        } else if (((model.board[i-1][j] == 2) || (model.board[i-1][j] == 1)) && ((model.board[i][j+1] == 2) || (model.board[i][j+1] == 1))) {
            jboard[i][j].setIcon(TRAIN6);
        } else if (((model.board[i][j-1] == 2) || (model.board[i][j-1] == 1)) || ((model.board[i][j+1] == 2) || (model.board[i][j+1] == 1))) {
            jboard[i][j].setIcon(TRAIN);
        } else if (((model.board[i-1][j] == 2) || (model.board[i-1][j] == 1)) || ((model.board[i+1][j] == 2) || (model.board[i+1][j] == 1))) {
            jboard[i][j].setIcon(TRAIN2);
        } else {
            jboard[i][j].setIcon(TRAIN);
        }
    }
    
    @Override
    public void avertirNewGame() {
        initBoard();
    }
    
    @Override
    public void avertir() {
        updateBoard();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jeuPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jeuPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jeuPanel.setMinimumSize(new java.awt.Dimension(600, 400));

        javax.swing.GroupLayout jeuPanelLayout = new javax.swing.GroupLayout(jeuPanel);
        jeuPanel.setLayout(jeuPanelLayout);
        jeuPanelLayout.setHorizontalGroup(
            jeuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jeuPanelLayout.setVerticalGroup(
            jeuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 398, Short.MAX_VALUE)
        );

        jLabel1.setText("Ressources:");

        jLabel3.setText("Villes | Fer | Bois | Laine | Redstone | Pierre");

        jLabel4.setText("ressources");

        jLabel5.setText("Produits finis :");

        jLabel6.setText("produits");

        jLabel7.setText("Epée | Lit | Boussole | Piston");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jeuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addContainerGap(539, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jeuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(58, 58, 58)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrameTrain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameTrain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameTrain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameTrain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PlateauModel model = new PlateauModel();
                
                JFrameTrain instance1 = new JFrameTrain(model);
                instance1.setVisible(true);
                instance1.setExtendedState(JFrame.MAXIMIZED_BOTH); 
                model.register(instance1);
                model.nouvellePartie();
                System.out.print(model.toString());
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    public javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jeuPanel;
    // End of variables declaration//GEN-END:variables

}
