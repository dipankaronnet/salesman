/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * InterfacePanel4.java
 *
 * Created on 2009-05-05, 11:08:58
 */

package Interface; 

import Algorithm.Solver;
import GrafVisualization.Vertex;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author zupa
 */
public class InterfacePanel4 extends javax.swing.JFrame {

    /** Tymczasowa tablica kosztów*/
    private Integer[][] koszty;

    private static final int INF=100000000;

     /**Ilość miast*/
    int ilosc;

    /**Zmienna odpowiedzialna za tryb*/
    private int tryb;

    /**Tu będzie rozwiązanie*/
    Solver rozw;

    /**Ile iteracji - liczy pierwsze wywołanie B&B**/
    int iteracje;

   /**Ktora iteracja jest aktualnie wykonywana w trzecim trybie*/
    int ktoraIteracja = 0;

    /** Creates new form InterfacePanel4 */
    public InterfacePanel4(int t) {
        initComponents();
        tryb = t;
    }

    /**Trzeba dodać:
     * filtr typów
     * obsługę plików exelowych
     * sprawdzanie poprawności pliku
     * obsługę wyjątków
     */

    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jFileChooser1.setControlButtonsAreShown(false);
        jFileChooser1.setCurrentDirectory(null);
        jFileChooser1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Wczytaj z pliku", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 1, 15))); // NOI18N

        jButton1.setText("Dalej");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Powrót");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int m1, m2, k;
        String linia;
        File plik = jFileChooser1.getSelectedFile();
        try
        {
             BufferedReader input =  new BufferedReader(new FileReader(plik));
             ilosc = Integer.parseInt(input.readLine());
             koszty = new Integer[ilosc][];
             for (int i = 0; i<ilosc; i++)
             {
                koszty[i] = new Integer[ilosc];
             }

             for(int j = 0; j < ilosc*(ilosc-1); j++)
             {
                 linia = input.readLine();
                 String[] liczby = linia.split(" ");
                 m1 = Integer.parseInt(liczby[0]);
                 m2 = Integer.parseInt(liczby[1]);
                 k = Integer.parseInt(liczby[2]);

                 if (spr_miasta(m1, m2))
                    koszty[m1-1][m2-1] = k;

             }
             for(int i=0; i<ilosc; ++i)
             {
                 koszty[i][i]=INF;
             }
            
        }
    catch (IOException ex)
        {
        }
    rozw = new Solver(ilosc,koszty);
    iteracje = rozw.branchAndBound()+1;
    rozw.completePath();
    wypiszWynik();
    this.setVisible(false);    
    }//GEN-LAST:event_jButton1ActionPerformed

/**Sprawdza czy miasta są poprawne
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
    Row3.add(new javax.swing.JLabel("Ścieżka:"));
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
        JPanel Row5 = rozw.createTreeVisualization();
        prawyPanel.add(Row5);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFileChooser jFileChooser1;
    // End of variables declaration//GEN-END:variables

}
