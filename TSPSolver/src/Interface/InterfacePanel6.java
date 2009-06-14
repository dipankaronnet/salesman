/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

import Algorithm.Solver;
import GrafVisualization.Vertex;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.io.*;

/**
 *
 * @author zupa
 */
public class InterfacePanel6 extends javax.swing.JFrame
{
    /** Tymczasowa tablica kosztów*/
    private Integer[][] koszty;

    /**Nieskończoność*/
    private static final int INF=100000000;

    /**Ilość miast*/
    int ilosc;

    /**Tryb*/
    private int tryb;

    /**Rozwiązanie*/
    Solver rozw;

    /**Ile iteracji - liczy pierwsze wywołanie B&B**/
    int iteracje;

    /**Ktora iteracja jest aktualnie wykonywana w trzecim trybie*/
    int ktoraIteracja = 1;
    Integer lb;

    /**Panele i guziki dostępne dla wszystkich metod w tej klasie*/
    JPanel calyPanel = new javax.swing.JPanel();
    JPanel prawyPanel = new javax.swing.JPanel();
    JPanel lewyPanel = new javax.swing.JPanel();
    JButton nastepny = new javax.swing.JButton();
    JButton poprzedni = new javax.swing.JButton();
    JScrollPane prawyScrollPane;

	String wypis1; //D
	String wypis2; //D
    /** Creates new form InterfacePanel5
     inicjalizacja - przepisanie wartości przekazanych przez poprzedni panel*/
    public InterfacePanel6(int t, int i, Integer[][] k) throws IOException
    {
        initComponents();
        tryb = t;
        ilosc = i;
        koszty = new Integer[ilosc][];
        for (int j = 0; j<ilosc; j++)
        {
           koszty[j] = new Integer[ilosc];
        }
                for (int j = 0; j<ilosc; j++)
            for(int l = 0; l<ilosc; l++)
                koszty[j][l] = k[j][l];
        rozw = new Solver(ilosc,koszty);
        iteracje = rozw.branchAndBound()+1;
        // rozw.completePath(); //D
		wypis1=rozw.printAnswer2(); //D
		wypis2=rozw.printAnswer3(); //D
//<<<<<<< .mine
		//saveCosts();				//D
//=======
	//	saveCosts();				//D
       lb=rozw.answer.getLowerBound();
//>>>>>>> .r56
        ustawParametry();

        wypiszWynik();
    }

    /** Inicjalizacja*/
    private void initComponents()
    {
        nastepny.setText("Następny");
        nastepny.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ktoraIteracja++;
//<<<<<<< .mine
				//rozw.branchAndBound2(ktoraIteracja); // D
//=======
				rozw.branchAndBound2(ktoraIteracja,iteracje); // D
//>>>>>>> .r56
                calyPanel.remove(2);
                calyPanel.add(new JScrollPane(nowyPrawyPanel()));
                calyPanel.repaint();
                calyPanel.validate();
            }
        });

        poprzedni.setText("Poprzedni");
        poprzedni.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ktoraIteracja--;
//<<<<<<< .mine
				//rozw.branchAndBound2(ktoraIteracja); // D
//=======
				 rozw.branchAndBound2(ktoraIteracja,iteracje); // D
//>>>>>>> .r56
                calyPanel.remove(2);
                calyPanel.add(new JScrollPane(nowyPrawyPanel()));
                calyPanel.repaint();
                calyPanel.validate();
            }
        });
    }

    /**Metoda, która ustawia początkowe parametry ramki*/
    private void ustawParametry()
    {
        Dimension wymiary;
        if (tryb == 1)
        {
            wymiary = new Dimension(500, 400);
        }
        else
        {
            calyPanel.setLayout(new BoxLayout(calyPanel, BoxLayout.LINE_AXIS));
            wymiary = new Dimension(1180, 800);
        }
        this.setPreferredSize(wymiary);
       // this.setName("Rozwiązanie");
                //marginesy!!

    }

    /**Metoda, która zapisuje tablicę kosztów i ścieżkę rozwiązania do pliku*/
    public void saveCosts() throws IOException
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
                if(i!=j)
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
        String sciezka=wypis1+wypis2;
        fw.write(sciezka);

        fw.close();
        }catch(IOException e){System.out.println("nie mozna dokonc zapisu");}
    }

    /**Wypisuje tabelkę aktualnych kosztów */
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
                        if((koszty[j-1][i-1]<INF))
                        {
                            if (koszty[j-1][i-1]<0)
                                NewColumn.add(new javax.swing.JLabel(" "));
                            else
                                NewColumn.add(new javax.swing.JLabel(String.valueOf(koszty[j-1][i-1])));
                        }
                        else
                        {
                            if( (koszty[j-1][i-1]==INF)) // D
                            NewColumn.add(new javax.swing.JLabel(Character.toString((char)8734)));
                        }
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



    /**Dodaje do ramki panel z wynikiem, w drugim trybie dodatkowo
     * rysuje drzewko i tabelki, a w trzecim pozwala krok po kroku przejrzeć
     * rozwiązanie. Będzie też opcja zapisu wyniku do pliku, zapisu rysunku i ew.
     * można zrobić cofnięcie się do wpisywania danych i ich modyfikację.
     */
    private void wypiszWynik()
    {
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
        Row4.add(new javax.swing.JLabel(wypis1));
        lewyPanel.add(Row4);
        lewyPanel.add(Box.createRigidArea(new Dimension(0,10)));

        JPanel Row6 = new javax.swing.JPanel();
        Row6.setLayout(new BoxLayout(Row6, BoxLayout.LINE_AXIS));
        Row6.add(new javax.swing.JLabel(wypis2));
        lewyPanel.add(Row6);
        lewyPanel.add(Box.createRigidArea(new Dimension(0,10)));

        JPanel Row9 = new javax.swing.JPanel();
        Row9.setLayout(new BoxLayout(Row9, BoxLayout.LINE_AXIS));
        Row9.add(new javax.swing.JLabel("Koszt: "+String.valueOf(lb)));
        lewyPanel.add(Row9);
        lewyPanel.add(Box.createRigidArea(new Dimension(0,10)));

        JPanel Row7 = new javax.swing.JPanel();
        Row7.setLayout(new BoxLayout(Row7, BoxLayout.LINE_AXIS));
        Row7.add(new javax.swing.JLabel());
        lewyPanel.add(Row7);
        lewyPanel.add(Box.createRigidArea(new Dimension(0,10)));

        JPanel Row8 = new javax.swing.JPanel();
        Row8.setLayout(new BoxLayout(Row8, BoxLayout.LINE_AXIS));
        JButton zapisz = new javax.swing.JButton("Zapisz");
        zapisz.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                try {
                    saveCosts();
                } catch (IOException ex) {
                    Logger.getLogger(InterfacePanel6.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        Row7.add(zapisz);
        lewyPanel.add(Row7);

        calyPanel.add(lewyPanel);

        /*Jesli tryb==2, to poza wypisaniem wyniku tekstowego pojawia się okienko
         z narysowanym drzewkiem dloa ostatecznego wyniku.
         generalnie: prawyPanel przechowuje rysunek i tabelki, a pawy ScrollPane
         jest po to, żeby się dało przewijać w dół, jak jest za długie.
         */
        if(tryb==2)
        {
            calyPanel.add(Box.createHorizontalGlue());
            prawyPanel.setLayout(new BoxLayout(prawyPanel, BoxLayout.PAGE_AXIS));
            JPanel Row5 = rozw.createTreeVisualization();
            prawyPanel.add(Row5);
            prawyPanel.add(Box.createRigidArea(new Dimension(5,0)));
            prawyPanel.add(wypiszTabelki());
            prawyPanel.add(Box.createRigidArea(new Dimension(5,0)));
            prawyScrollPane = new JScrollPane(prawyPanel);
            calyPanel.add(prawyScrollPane);
        }

        /* Podobnie jak w trybie 2, ale dodane są jeszcze 2 guziczki, które pojawiają
         się w zależności od tego, czy zmienna ktoraIteracja jest mniejsza od obliczonej
         przez pierwsze wywołanie funkcji B&B zmiennej Iteracje. Wciśnięcie guziczków
         modyfikuje zmienną któraIteracja i rysuje panel od nowa
        */

        else if(tryb==3)
        {
            calyPanel.add(Box.createHorizontalGlue());
            prawyScrollPane = new JScrollPane(nowyPrawyPanel());
            calyPanel.add(prawyScrollPane);

        }

    this.add(calyPanel);
    this.setVisible(true);
    this.pack();

    }

    /*zwraca Panel ze wszystkimi tabelkami*/
	private JPanel wypiszTabelki()
{
    JPanel tmpPanel = new javax.swing.JPanel();
    tmpPanel.setLayout(new GridLayout(0,3,5,5));
    tmpPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    /******Fragmenty kodu Doroty****/
    Queue <Vertex>kolejka=new LinkedList<Vertex>();//Stos, na który będą wrzucane kolejne wierzchołki
    Collection<Vertex> children = new ArrayList<Vertex>();//lista "dzieci" danego wierzchołka
    Vertex root = rozw.treeVisualization.findRoot();//pierwszy wierzch.
    kolejka.add(root);
    int tableIndex = 1; // nr aktualnie wypisywanej tabelki
    while(!kolejka.isEmpty())
    {
        root = kolejka.poll();
        JPanel tabPanel = new javax.swing.JPanel();//Osobny panel dla każdej tabelki
        tabPanel.setLayout(new BoxLayout(tabPanel, BoxLayout.PAGE_AXIS));
        tabPanel.add(new javax.swing.JLabel("Wierzchołek nr "+Integer.toString(root.getId())));
        tabPanel.add(Box.createRigidArea(new Dimension(0,10)));
        tabPanel.add(new javax.swing.JLabel("LB =  "+Integer.toString(root.getLowerBound())));
        tabPanel.add(Box.createRigidArea(new Dimension(0,10)));
        tabPanel.add(wypiszKoszty(root.getDistancesSize(), root.getDistances()));
        tabPanel.add(Box.createRigidArea(new Dimension(0,10)));
        tmpPanel.add(tabPanel);
        tableIndex++;
        children=rozw.treeVisualization.gv.getSuccessors(root);//wrzucenie na stos "dzieci" węzła
         Iterator<Vertex>it=children.iterator();
        if(children.size()==2)
        {
            Vertex child1=it.next();
            Vertex child2=it.next();
            if(child1.lewy==1 && child2.lewy==0)
            {
                kolejka.add(child1);
                kolejka.add(child2);
            }
            else
            {
                kolejka.add(child2);
                kolejka.add(child1);
            }
        }
        else if(children.size()==1)
        {
            Vertex child=it.next();
            kolejka.add(child);
        }
        else;
     }
        /*  for(Iterator<Vertex>it = children.iterator();it.hasNext();)
        {
            Vertex doKolejki=it.next();
            kolejka.add(doKolejki);
        }*/


    return tmpPanel;
}

    /** Tworzy nowy prawy panel dla trybu trzeciego*/
    private JPanel nowyPrawyPanel()
    {
 //       iteracje = rozw.branchAndBound();
        JPanel tmpPanel = new javax.swing.JPanel();
        tmpPanel.setLayout(new BoxLayout(tmpPanel, BoxLayout.PAGE_AXIS));
        JPanel przyciski = new javax.swing.JPanel();
        przyciski.setLayout(new BoxLayout(przyciski, BoxLayout.LINE_AXIS));

        if (ktoraIteracja>1)
        {
            przyciski.add(poprzedni);
            przyciski.add(Box.createRigidArea(new Dimension(0,5)));
        }

        if (ktoraIteracja<iteracje) // <= -> <
        {
            przyciski.add(nastepny);
        }

        tmpPanel.add(przyciski);
        tmpPanel.add(Box.createRigidArea(new Dimension(5,0)));
        rozw.branchAndBound2(ktoraIteracja,iteracje);
        if (ktoraIteracja>0)
        {
            tmpPanel.add(rozw.createTreeVisualization());
            tmpPanel.add(Box.createRigidArea(new Dimension(5,0)));
            tmpPanel.add(wypiszTabelki());
        }
        tmpPanel.setVisible(true);
        return tmpPanel;
    }

}