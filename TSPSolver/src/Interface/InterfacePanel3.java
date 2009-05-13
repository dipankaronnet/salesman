/*
 * InterfacePanel3.java
 *
 * Created on 2009-05-05, 10:39:18
 */

package Interface;

import Algorithm.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import GrafVisualization.*;

/**
 *
 * @author zupa
 */
public class InterfacePanel3 extends javax.swing.JFrame {

     private static final int INF=100000000;
     /** Nieskończoność w odległości miasta i-tego od i-tego*/

    /**Tymczasowa tablica kosztów*/
    private int[][] koszty;

    /**Ilość miast*/
    int ilosc;

    /** Creates new form InterfacePanel3 */
    public InterfacePanel3() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Wpisywanie", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 15))); // NOI18N

        jLabel1.setText("<html>Podaj ilość miast i wciśnij \"OK\".<br>\nNastępnie wpisuj po kolei koszt drogi pomiędzy <br>\nkażdymi dwoma miastami. Poszczególne koszty <br>\nPotwierdzaj klikając \"Dodaj\"<br>\nJeśli chcesz zobaczyć aktualną tabelę kosztów wciśnij \"Podgląd\"<br>\nAby zatwierdzić wciśnij \"Dalej\"</html>");

        jLabel2.setText("Ilość miast:");

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Koszty:");

        jButton2.setText("Dalej");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Koniec");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setText("Miasto początkowe");

        jLabel5.setText("Miasto docelowe");

        jLabel6.setText("Koszt");

        jButton4.setText("Dodaj");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Podgląd");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 294, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton4)
                                    .addComponent(jButton1)))))
                    .addComponent(jButton5))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton2, jButton3});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel3, jLabel4, jLabel5, jLabel6});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton4, jTextField1, jTextField2, jTextField3, jTextField4});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**Kończy program*/
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    /**Wczytuje ilość miast i tworzy tymczasową tablicę kosztów
     *dopisać  wyzerowanie na początek
     * i uniemożliwienie zmiany ilości miast podczas tworzenia
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ilosc = Integer.parseInt(jTextField1.getText());
        koszty = new int[ilosc][];
        for (int i = 0; i<ilosc; i++)
        {
            koszty[i] = new int[ilosc];
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**Dodaje koszt drogi pomiędzy miastami do tabeli kosztów
     *m1 - miasto pierwsze, m2 - miasto drugie, k - koszt
     * nie dodaje, jeśli m1==m2
     * dodaje koszt "w obie strony"
     */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int m1, m2, k;
        m1 = Integer.parseInt(jTextField2.getText());
        m2 = Integer.parseInt(jTextField3.getText());
        k = Integer.parseInt(jTextField4.getText());
        if (m1!=m2)
        {
            koszty[m1-1][m2-1] = k;
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
        }
    }//GEN-LAST:event_jButton4ActionPerformed

/** Tworzy obiekt Costs i wpisuje do niego podane przez użytkownika odległości
 *następnie odpala Solver dla tego grafu - skopiowałam z TEST
 */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
   /*     Costs newCosts = new Costs(ilosc);
        for(int i = 0; i<ilosc; i++)
            for(int j = 0; j<ilosc; j++)
               // if (koszty[i][j]!=0)
                if(i!=j)
                    newCosts.setDistances(i, j, koszty[i][j]);
        Solver a = new Solver(newCosts);
        a.branchAndBound();
        Costs root=(Costs)a.tree.getRoot();
        a.createTreeVisualization();
        a.completePath();
        a.printAnswer();*/
        for(int i=0; i<ilosc; ++i)
        {
            for(int j=0; j<ilosc; ++j)
                System.out.print(koszty[i][j]);
            System.out.println();
        }
        Canvas1 kanwa=new Canvas1(ilosc,koszty);
        JFrame frame2=new JFrame("grafDescription");
        frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame2.getContentPane().add(kanwa);
        frame2.pack();
        frame2.setVisible(true);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    /**Podgląd tabeli
     *narazie nie da sie do niego nic wpisać, ale jeszcze nad tym pomyślę
     */
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        JFrame Podglad = new javax.swing.JFrame();
        JPanel NewRow = new javax.swing.JPanel();
        NewRow.setLayout(new BoxLayout(NewRow, BoxLayout.LINE_AXIS));

        for (int i = 0; i<=ilosc; i++)
        {
            JPanel NewColumn = new javax.swing.JPanel();
            NewColumn.setLayout(new BoxLayout(NewColumn, BoxLayout.PAGE_AXIS));
            for (int j= 0; j<=ilosc; j++)
            {
                if ((i==0)&&(j!=0))
                {
                    NewColumn.add(new javax.swing.JLabel(String.valueOf(j)));
                    NewColumn.add(Box.createRigidArea(new Dimension(10,0)));
                }
                else if ((i!=0)&&(j==0))
                {
                    NewColumn.add(new javax.swing.JLabel(String.valueOf(i)));
                    NewColumn.add(Box.createRigidArea(new Dimension(10,0)));
                }
                else if ((i!=0)&&(j!=0))
                {
                    if(i!=j)
                        NewColumn.add(new javax.swing.JLabel(String.valueOf(koszty[i-1][j-1])));
                    else
                        NewColumn.add(new javax.swing.JLabel("inf"));
                    NewColumn.add(Box.createRigidArea(new Dimension(10,0)));
                }
                else
                {
                    NewColumn.add(new javax.swing.JLabel(" "));
                    NewColumn.add(Box.createRigidArea(new Dimension(10,0)));
                }

            }
            NewRow.add(NewColumn);
            NewRow.add(Box.createRigidArea(new Dimension(0,5)));

        }
        
        Podglad.add(NewRow);
        Podglad.setVisible(true);
        Podglad.pack();

    }//GEN-LAST:event_jButton5ActionPerformed

  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables

}
