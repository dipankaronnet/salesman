/*
 * InterfacePanel3.java
 *
 * Created on 2009-05-05, 10:39:18
 */

package Interface;

import Algorithm.Solver;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import GrafVisualization.*;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.io.*;

/**
 *
 * @author zupa
 */
public class InterfacePanel3 extends javax.swing.JFrame {


    /** Nieskończoność w odległości miasta i-tego od i-tego*/
    private static final int INF=100000000;

    /**Tymczasowa tablica kosztów*/
    private Integer[][] koszty;

    /**Ilość miast*/
    int ilosc;

    /**Zmienna odpowiedzialna za tryb*/
    private int tryb;

    /**Tu będzie rozwiązanie*/
    Solver rozw;

    /**Ile iteracji - liczy pierwsze wywołanie B&B**/
    int iteracje;

    /**Ktora iteracja jest aktualnie wykonywana w trzecim trybie*/
    int ktoraIteracja = 1;

    /** Creates new form InterfacePanel3 */
    public InterfacePanel3(int t) {
        initComponents();
        tryb = t;
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
        Dalej = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        dodaj = new javax.swing.JButton();
        podglad = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

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

        Dalej.setText("Dalej");
        Dalej.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DalejActionPerformed(evt);
            }
        });

        jButton3.setText("Powrót");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setText("Miasto początkowe");

        jLabel5.setText("Miasto docelowe");

        jLabel6.setText("Koszt");

        dodaj.setText("Dodaj");
        dodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodajActionPerformed(evt);
            }
        });

        podglad.setText("Podgląd");
        podglad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                podgladActionPerformed(evt);
            }
        });

        jLabel7.setText(" ");

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
                        .addComponent(Dalej)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 292, Short.MAX_VALUE)
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
                                    .addComponent(dodaj)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel7))))))
                    .addComponent(podglad))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {Dalej, jButton3});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel3, jLabel4, jLabel5, jLabel6});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {dodaj, jButton1, jTextField1, jTextField2, jTextField3, jTextField4});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jLabel7))
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
                    .addComponent(dodaj))
                .addGap(18, 18, 18)
                .addComponent(podglad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Dalej, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jButton3.getAccessibleContext().setAccessibleName("");

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
        this.setVisible(false);
}//GEN-LAST:event_jButton3ActionPerformed

    /**Wczytuje ilość miast i tworzy tymczasową tablicę kosztów
     *dopisać  wyzerowanie na początek
     * i uniemożliwienie zmiany ilości miast podczas tworzenia
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        ilosc = Integer.parseInt(jTextField1.getText());
        jLabel7.setText(jTextField1.getText());
        jTextField1.setText(" ");
        koszty = new Integer[ilosc][];
        for (int i = 0; i<ilosc; i++)
        {
            koszty[i] = new Integer[ilosc];
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**Dodaje koszt drogi pomiędzy miastami do tabeli kosztów
     *m1 - miasto pierwsze, m2 - miasto drugie, k - koszt
     * nie dodaje, jeśli m1==m2
     * dodaje koszt "w obie strony"
     */
    private void dodajActionPerformed(java.awt.event.ActionEvent evt) {                                      
        int m1, m2, k;
        m1 = Integer.parseInt(jTextField2.getText());
        m2 = Integer.parseInt(jTextField3.getText());
        k = Integer.parseInt(jTextField4.getText());
        if (spr_miasta(m1, m2))
        {
            koszty[m1-1][m2-1] = k;
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
        }

        else
        {
            //napisać, ze jest źle
        }
    }                                        

                                     

   public void saveCosts()
    {
        try
        {
        FileWriter fw=new FileWriter("wynik.txt");
        fw.write("Dla danej macierzy kosztów:\r\n\r\n");
        for(int i=0; i<ilosc; ++i)
        {
            String linia=new String();
            for(int j=0; j<ilosc; ++j)
            {
                if(koszty[i][j]!=100000000)
                 linia+=koszty[i][j];
                else
                    linia+="inf";
                linia+=" ";
            }
            linia+="\r\n";
            fw.write(linia);

        }
        fw.write("\r\n");
        fw.write("Najkrótszą ścieżką jest:\r\n\r\n");
        String sciezka=rozw.printAnswer2();
        fw.write(sciezka);

        fw.close();
        }catch(IOException e){System.out.println("nie mozna dokonc zapisu");}
    }
/** Wypisuje rozwiązanie w zależności od wybranego trybu
 */
    private void DalejActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DalejActionPerformed
    rozw = new Solver(ilosc,koszty);
    iteracje = rozw.branchAndBound()+1;
   // rozw.completePath();
    saveCosts();
    wypiszWynik();
    this.setVisible(false);
}//GEN-LAST:event_DalejActionPerformed


/**Podgląd tabeli
*narazie nie da sie do niego nic wpisać, ale jeszcze nad tym pomyślę
*/
    private void podgladActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_podgladActionPerformed
        JFrame Podglad = new javax.swing.JFrame();
        JPanel NewRow = wypiszKoszty(ilosc, koszty);
        Podglad.add(NewRow);
        Podglad.setVisible(true);
        Podglad.pack();

}//GEN-LAST:event_podgladActionPerformed

/**Sprawdza, czy podane miasta są dobre
 */
private boolean spr_miasta (int a, int b)
{
    if ((a <= ilosc)&&(b <= ilosc)&&(a != b))
        return true;
    return false;
}

/**Wypisuje tabelkę aktualnych kosztów
 */
private JPanel wypiszKoszty(int ilosc, Integer[][] koszty)
{
    JPanel tmpPanel = new javax.swing.JPanel();
    tmpPanel.setLayout(new BoxLayout(tmpPanel, BoxLayout.LINE_AXIS));

        for (int i = 0; i<=ilosc; i++)
        {
            JPanel NewColumn = new javax.swing.JPanel();
            NewColumn.setLayout(new BoxLayout(NewColumn, BoxLayout.PAGE_AXIS));
            for (int j= 0; j<=ilosc; j++)
            {
                if ((i==0)&&(j!=0))
                {
                    NewColumn.add(new javax.swing.JLabel(String.valueOf(j)));
                    NewColumn.add(Box.createRigidArea(new Dimension(20,0)));
                }
                else if ((i!=0)&&(j==0))
                {
                    NewColumn.add(new javax.swing.JLabel(String.valueOf(i)));
                    NewColumn.add(Box.createRigidArea(new Dimension(0,20)));
                }
                else if ((i!=0)&&(j!=0))
                {
                    if((i!=j)&&(koszty[i-1][j-1]<100000))
                    {
                        if (koszty[i-1][j-1]<0)
                            NewColumn.add(new javax.swing.JLabel(" "));
                        else
                            NewColumn.add(new javax.swing.JLabel(String.valueOf(koszty[i-1][j-1])));
                    }
                    else
                        NewColumn.add(new javax.swing.JLabel("inf"));
                    NewColumn.add(Box.createRigidArea(new Dimension(10,0)));
                }
                else
                {
                    NewColumn.add(new javax.swing.JLabel(" "));
                    NewColumn.add(Box.createRigidArea(new Dimension(20,20)));
                }

            }
            tmpPanel.add(NewColumn);
            tmpPanel.add(Box.createRigidArea(new Dimension(10,0)));

        }
    return tmpPanel;
}

/**Tworzy nowe okienko, w którym wypisuje wynik, w drugim trybie dodatkowo
 * rysuje drzewko i tabelki, a w trzecim pozwala krok po kroku przejrzeć
 * rozwiązanie. Będzie też opcja zapisu wyniku do pliku, zapisu rysunku i ew.
 * można zrobić cofnięcie się do wpisywania danych i ich modyfikację.
 */
private void wypiszWynik()
{
    final JFrame Wynik = new javax.swing.JFrame();//Otwarcie nowego okienka
    Wynik.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    JPanel calyPanel = new javax.swing.JPanel();
    calyPanel.setLayout(new BoxLayout(calyPanel, BoxLayout.LINE_AXIS));
    JPanel lewyPanel = new javax.swing.JPanel();//stworzenie lewego panelu z wynikiewm tekstowym - wszystkie tryby
    lewyPanel.setLayout(new BoxLayout(lewyPanel, BoxLayout.PAGE_AXIS));

    JPanel Row1 = new javax.swing.JPanel();
    Row1.setLayout(new BoxLayout(Row1, BoxLayout.LINE_AXIS));
    Row1.add(new javax.swing.JLabel("Wynik otrzymany dla kosztów:"));
    lewyPanel.add(Row1);
    lewyPanel.add(Box.createRigidArea(new Dimension(0,20)));

    JPanel Row2 = wypiszKoszty(ilosc, koszty);
    lewyPanel.add(Row2);
    lewyPanel.add(Box.createRigidArea(new Dimension(0,20)));

    JPanel Row3 = new javax.swing.JPanel();
    Row3.setLayout(new BoxLayout(Row3, BoxLayout.LINE_AXIS));
    Row3.add(new javax.swing.JLabel("Została wybrana ścieżka "));
    lewyPanel.add(Row3);
    lewyPanel.add(Box.createRigidArea(new Dimension(0,10)));

    JPanel Row4 = new javax.swing.JPanel();
    Row4.setLayout(new BoxLayout(Row4, BoxLayout.LINE_AXIS));
    Row4.add(new javax.swing.JLabel(rozw.printAnswer2()));
    lewyPanel.add(Row4);

    JPanel Row6 = new javax.swing.JPanel();
    Row6.setLayout(new BoxLayout(Row6, BoxLayout.LINE_AXIS));
    Row6.add(new javax.swing.JLabel(rozw.printAnswer3()));
    lewyPanel.add(Row6);

    calyPanel.add(lewyPanel);
    calyPanel.add(Box.createHorizontalGlue());

    /*Jesli tryb==2, to poza wypisaniem wyniku tekstowego pojawia się okienko
     z narysowanym drzewkiem dloa ostatecznego wyniku.
     generalnie: prawyPanel przechowuje rysunek i tabelki, a pawy ScrollPane
     jest po to, żeby się dało przewijać w dół, jak jest za długie.
     */
    if(tryb==2)
    {
        JPanel prawyPanel = new javax.swing.JPanel();
        prawyPanel.setLayout(new BoxLayout(prawyPanel, BoxLayout.PAGE_AXIS));
        JPanel Row7 = rozw.createTreeVisualization();
        prawyPanel.add(Row7);
        prawyPanel.add(Box.createRigidArea(new Dimension(5,0)));
        prawyPanel.add(wypiszTabelki());
        prawyPanel.add(Box.createRigidArea(new Dimension(5,0)));
        JScrollPane prawyScrollPane = new JScrollPane(prawyPanel);
        calyPanel.add(prawyScrollPane);
    }

    /* Podobnie jak w trybie 2, ale dodane są jeszcze 2 guziczki, które pojawiają
     się w zależności od tego, czy zmienna ktoraIteracja jest mniejsza od obliczonej
     przez pierwsze wywołanie funkcji B&B zmiennej Iteracje. Wciśnięcie guziczków
     modyfikuje zmienną któraIteracja i rysuje panel od nowa
     !!! Przez to jest migotanie, z którym nie wiem co zrobić!
     */
    else if(tryb==3)
    {
    JButton nastepny = new javax.swing.JButton();
    nastepny.setText("Następny");
    nastepny.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ktoraIteracja++;
                Wynik.setVisible(false);
                wypiszWynik();
            }
        });
    JButton poprzedni = new javax.swing.JButton();
    poprzedni.setText("Poprzedni");
    poprzedni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ktoraIteracja--;
                Wynik.setVisible(false);
                wypiszWynik();
            }
        });
    JPanel prawyPanel = new javax.swing.JPanel();
    prawyPanel.setLayout(new BoxLayout(prawyPanel, BoxLayout.PAGE_AXIS));
    JPanel przyciski = new javax.swing.JPanel();
    przyciski.setLayout(new BoxLayout(przyciski, BoxLayout.LINE_AXIS));
    if (ktoraIteracja>1)
    {
        przyciski.add(poprzedni);
        przyciski.add(Box.createRigidArea(new Dimension(0,5)));
    }
    if (ktoraIteracja<=iteracje)
    {
        przyciski.add(nastepny);
    }

    prawyPanel.add(przyciski);
    prawyPanel.add(Box.createRigidArea(new Dimension(5,0)));
    rozw.branchAndBound2(ktoraIteracja);
    if (ktoraIteracja>0)
    {
        JPanel Drzewko = rozw.createTreeVisualization();
        prawyPanel.add(Drzewko);
        prawyPanel.add(Box.createRigidArea(new Dimension(5,0)));
        prawyPanel.add(wypiszTabelki());
    }
    prawyPanel.add(Box.createRigidArea(new Dimension(5,0)));
    JScrollPane prawyScrollPane = new JScrollPane(prawyPanel);
    calyPanel.add(prawyScrollPane);

    }

Wynik.add(calyPanel);
Wynik.setVisible(true);
Wynik.pack();


}

/*Tworzy i zwraca panel z wszystkimi tabelkami, które są aktualnie do wypisania
 */
private JPanel wypiszTabelki()
{
    JPanel tmpPanel = new javax.swing.JPanel();//Panel z wszystkimi tabelkami, który zostanie zwrócony
    tmpPanel.setLayout(new GridLayout(0,3,5,5));
    tmpPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    /******Fragmenty kodu Doroty****/
    Stack <Vertex>stos=new Stack<Vertex>();//Stos, na który będą wrzucane kolejne wierzchołki
    Collection<Vertex> children = new ArrayList<Vertex>();//lista "dzieci" danego wierzchołka
    Vertex root = rozw.treeVisualization.findRoot();//pierwszy wierzch.
    stos.push(root);
    int tableIndex = 1; // nr aktualnie wypisywanej tabelki
    while(!stos.isEmpty())
    {
        root = stos.pop();
        JPanel tabPanel = new javax.swing.JPanel();//Osobny panel dla każdej tabelki
        tabPanel.setLayout(new BoxLayout(tabPanel, BoxLayout.PAGE_AXIS));
        tabPanel.add(new javax.swing.JLabel("Wierzchołek nr "+Integer.toString(root.getId())));
        tabPanel.add(Box.createRigidArea(new Dimension(0,10)));
        tabPanel.add(wypiszKoszty(root.getDistancesSize(), root.getDistances()));
        tabPanel.add(Box.createRigidArea(new Dimension(0,10)));
        tmpPanel.add(tabPanel);
        tableIndex++;
        children=rozw.treeVisualization.gv.getSuccessors(root);//wrzucenie na stos "dzieci" węzła
        for(Iterator<Vertex>it = children.iterator();it.hasNext();)
        {
            Vertex naStos=it.next();
            stos.push(naStos);
        }

    }
    return tmpPanel;
}
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Dalej;
    private javax.swing.JButton dodaj;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JButton podglad;
    // End of variables declaration//GEN-END:variables

}
