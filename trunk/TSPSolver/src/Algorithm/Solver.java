package Algorithm;

import edu.uci.ics.jung.graph.*;
import java.util.*;
import GrafVisualization.*;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.7B791AD3-C503-B12A-7E83-B53FCB270ABD]
// </editor-fold>
/**
 * odpowiada za rozwiązanie problemu komiwojazera dla danej macierzy
 * @author Dorota
 */
public class Solver {

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.8A6DF046-5FC7-EA1E-4FF4-3F050ADD01D4]
    // </editor-fold> 
    private Costs root;
    private static final int INF=100000000;
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C1E7AE27-A76C-E768-4E77-FD363CEBCC94]
    // </editor-fold> 
    private Costs leftChild;
    private Costs minLeaf;
    boolean newMinLeaf=false;
    public Graf treeVisualization;
    Costs rootNiezmienialny;
    int id=1;
    int ileKrokow=0;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3042A9A7-FDFE-5E68-5152-3AFC6E761C5E]
    // </editor-fold> 
    private Costs rightChild;
    private DirectedGraph<Costs,Integer>g; // dane zebrane w graf
    public DelegateTree tree; // do grafu podpiete drzewo  zeby graf sie stał drzewem były dzieci i rodzice itd
    public Costs answer;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E00E93BC-F2F5-204E-DAF9-148AB083C5B1]
    // </editor-fold>
    /**
     * dana macierz jest rootem drzewa. Drzewo skałda sie z Costsów
     * tworzymy roota i  drzewo wstawiamy roota do drzewa
     * @param n
     * @param tab
     */
    public Solver(int n, Integer tab[][])
    {
        root=new Costs(n);
        rootNiezmienialny=new Costs(n);
        for(int i = 0; i<n; i++)
            for(int j = 0; j<n; j++)
                if(i!=j)
                {
                    root.setDistances(i, j, tab[i][j]);
                    rootNiezmienialny.setDistances(i, j, tab[i][j]);
                }
        g = new DirectedSparseGraph();
        tree=new DelegateTree(g);
        boolean ok=tree.addVertex(root);
        //do v3
     

    }
    /**
     * podobnedziałanie jak powyższego konstruktowa chyba niewykorzystywany
     * @param root
     */
    public Solver (Costs root)
    {
        g = new DirectedSparseGraph();
        tree=new DelegateTree(g);        
        boolean ok=tree.addVertex(root);
    }
    /* rkurencyjnie przekazywac rooota jako argument branch and bound sprawdzac czy jakis inny lisc
     * nie bedize mniejszy i jego jako argument i tak pare razy 
     */
     
    /**
     * główny algorytm
     */
    public int branchAndBound ()
    {
        int edgeCounter=0;
        root =(Costs)tree.getRoot();
        int size=root.getSize();
        root.setLowerBoundAndReduce(0); //redukujemy routa jako lb jego parenta 0
        int min=INF;
        int counter=0;
        // root zmienny w trakcie algorytmu po 1 przejsciu algorytmu znajduje najlepsze dolne
        // ograniczenie i pelnasciezke ale moze sie tak zdazyc ze jakas inna niepelna sciezka
        // bedzie miała mniejsze ograniczenie od naszej - pojawia sie nadzieja wtedy ten
        // element staje się nowym rootem i dla niego zaczynamy wyliczenia
        // w pechowej sytuacji mozemy tak do  niesk dlatego konczymy po 5 iteracjach
        // 1 iteracja algorytmu ro redukcja roota kolejne podziały az dochodzimy do momentu
        // gdy lewa podmacierz marozmiar 2 i konczymy algo
        while(counter<15 && root.getLowerBound()<min)
        {
            size=root.getSize();
            while(size>2)  // 1 iteracja algo do moemntu gdy redukowana macierz ma rozmiar 2
            {
                ++ileKrokow;
                root.setLowerBoundAndReduce(root.getLowerBound()); // redukcja i obliczenie lb
                root.setEdgeToBranch(); // wyznaczenie luku wzgledem ktorego dzielimy
                Edge branchEdge=root.getEdgeToBranch();
                // podział i reduckaj dzieci
                leftChild = new Costs(root.getSize()-1,root,branchEdge,true);
                leftChild.setLowerBoundAndReduce(root.getLowerBound());
                leftChild.lewy=1;
                rightChild=new Costs(root.getSize(),root,branchEdge,false);
                rightChild.setLowerBoundAndReduce(root.getLowerBound());
                rightChild.lewy=0;
                boolean ok;
                // dodanie dzieci do drzewa
                ok=tree.addChild(edgeCounter,root, leftChild);
                edgeCounter++;
                ok=tree.addChild(edgeCounter, root,rightChild);
                edgeCounter++;
                // jako nowego roota wybieramy lewe dziecko
                root=leftChild;
                --size;
            }// o przejsciu 1 iteracji rootem jest skrajnie lewe dziecko
        
            if(counter==0) // pierwsza iteracja
                answer=root;
            if(root.getLowerBound()<answer.getLowerBound()) // kolejne iteracje gdy daja lepsze  rozw nadpisujemy
                answer=root;
                comPath();
            min=root.getLowerBound();
            newMinLeaf=false;
            chooseNext((Costs)tree.getRoot(),root.getLowerBound()); // jezeli znajdzie sie jakies prawe dziecko
            // praweczyli nie ma calej sciezki ale ma lb mnijesze czyli daje nadzieje w chooseNext ustawia new Min leaf na true
            if(newMinLeaf==true)
                root=minLeaf; // robmy nowe roota i od niego znowu algo
            else
                break;
            ++counter;
        }
      comPath();
        return ileKrokow;
    }
       
    /**Druga wersja na potrzebę 3 trybu wypisywania wyniku
     * */
    public void branchAndBound2 (int ileIteracji,int max)
       {
           g=null;
           tree=null;
           g = new DirectedSparseGraph();
           tree=new DelegateTree(g);
           root=rootNiezmienialny;
           boolean dziala=tree.addVertex(root);
           int edgeCounter=0;
           root =(Costs)tree.getRoot();
           int size=root.getSize();
           root.setLowerBoundAndReduce(root.getLowerBound()); //redukujemy routa jako lb jego parenta 0
           int min=INF;
           int counter=0;
           int iteracje=0;
            // root zmienny w trakcie algorytmu po 1 przejsciu algorytmu znajduje najlepsze dolne
            // ograniczenie i pelnasciezke ale moze sie tak zdazyc ze jakas inna niepelna sciezka
            // bedzie miała mniejsze ograniczenie od naszej - pojawia sie nadzieja wtedy ten
            // element staje się nowym rootem i dla niego zaczynamy wyliczenia
            // w pechowej sytuacji mozemy tak do  niesk dlatego konczymy po 5 iteracjach
            // 1 iteracja algorytmu ro redukcja roota kolejne podziały az dochodzimy do momentu
            // gdy lewa podmacierz marozmiar 2 i konczymy algo
            while(counter<15 && root.getLowerBound()<min)
            {
                size=root.getSize();

                while(size>2 && iteracje<ileIteracji)  // 1 iteracja algo do moemntu gdy redukowana macierz ma rozmiar 2
                {
                    iteracje++;
                    ++ileKrokow;
                   root.setLowerBoundAndReduce(root.getLowerBound()); // redukcja i obliczenie lb

                    root.setEdgeToBranch(); // wyznaczenie luku wzgledem ktorego dzielimy
                    Edge branchEdge=root.getEdgeToBranch();
                    // podział i reduckaj dzieci
                    leftChild = new Costs(root.getSize()-1,root,branchEdge,true);
                    leftChild.setLowerBoundAndReduce(root.getLowerBound());
                    leftChild.lewy=1;
                    rightChild=new Costs(root.getSize(),root,branchEdge,false);
                    rightChild.setLowerBoundAndReduce(root.getLowerBound());
                    rightChild.lewy=0;
                    boolean ok;
                    // dodanie dzieci do drzewa
                    ok=tree.addChild(edgeCounter,root, leftChild);
                    edgeCounter++;
                    ok=tree.addChild(edgeCounter, root,rightChild);
                    edgeCounter++;
                    // jako nowego roota wybieramy lewe dziecko
                    root=leftChild;

                    --size;
                    
               } // o przejsciu 1 iteracji rootem jest skrajnie lewe dziecko
             if(counter==0) // pierwsza iteracja
                 answer=root;
             if(root.getLowerBound()<answer.getLowerBound()) // kolejne iteracje gdy daja lepsze  rozw nadpisujemy
                 answer=root;
             if(answer.getSize()<=2 && iteracje<ileIteracji)
                {
                        comPath();
                }
             min=root.getLowerBound();
             newMinLeaf=false;
             chooseNext((Costs)tree.getRoot(),root.getLowerBound()); // jezeli znajdzie sie jakies prawe dziecko
             // praweczyli nie ma calej sciezki ale ma lb mnijesze czyli daje nadzieje w chooseNext ustawia new Min leaf na true
             if(newMinLeaf==true)
             {
                 root=minLeaf; // robmy nowe roota i od niego znowu algo

             }
             else
                  break;
             ++counter;
             }
        }

    /**
     * wybiera nowego roota na kolejne iteracje algo zapisuje go w minLeaf b
     * newMinLeaf na true
     * @param root
     * @param oldLB
     */
    public void chooseNext(Costs root,int oldLB)
    {
          Collection<Costs>children=new ArrayList<Costs>();
          children=tree.getChildren(root);
          int min=oldLB;
                for(Iterator<Costs> it=children.iterator(); it.hasNext();)
                {
                    Costs child=it.next();
                    if(child.getLowerBound()<min)
                    {
                    if(tree.isLeaf(child)==true)
                    {
                            min=child.getLowerBound();
                            minLeaf=child;
                            newMinLeaf=true;
                    }
                    else
                        chooseNext(child,oldLB);
                    }
                }
    }
    /**
     * wypisuje drzewo
     * @param root
     * @param parent
     */
    public void showTree(Costs root, Vertex parent)
    {
        Collection<Costs>children=new ArrayList<Costs>();
        children=tree.getChildren(root);
        for(Iterator<Costs> it=children.iterator(); it.hasNext();)
        {
            Costs child=it.next();

            Vertex a=new Vertex(child.getDistances(),child.getArraySize(),child.getLowerBound(),child.lewy);
            child.setDescription();
            child.setDescription2();
            a.setDescription(child.getDescription());
            a.setDescription2(child.getDescription2());
            treeVisualization.gv.addVertex(a);
            if(parent!=null)
            {
                treeVisualization.gv.addEdge(id, parent, a);
                ++id;
            }
            showTree(child,a);
        }
         
    }

    public JPanel createTreeVisualization()
    {
        treeVisualization=new Graf();
        treeVisualization.init();
        Costs r=(Costs)tree.getRoot();
        Vertex a=new Vertex(r.getDistances(),r.getArraySize(),r.getLowerBound(),r.lewy);
        r.setDescription();
        r.setDescription2();
        String desc=r.getDescription();
        String desc2=r.getDescription2();
        a.setDescription(desc);
        a.setDescription2(desc2);
        treeVisualization.gv.addVertex(a);
        showTree(r,a);
        /*******Moje zmiany*******/
        JPanel tmpPanel = new javax.swing.JPanel();
        tmpPanel.setLayout(new BoxLayout(tmpPanel, BoxLayout.PAGE_AXIS));//zmienić layout
        tmpPanel.add(treeVisualization.drawGraf());
        tmpPanel.add(Box.createRigidArea(new Dimension(0,5)));
        return tmpPanel;
        /*************************/

    }
    public void comPath()
    {
        System.out.println("ble");
       /* for(int i=0; i<answer.getArraySize();++i)
        {
            for(int j=0;j<answer.getArraySize(); ++j)
            {
                System.out.print(answer.getDistances()[i][j]+" ");
            }
            System.out.println();
        }*/
      //  System.out.println(answer.getArraySize());
        Edge edges[]=new Edge[4];
        int index=0;


        for(int i=0; i<answer.getArraySize();++i)
            for(int j=0; j<answer.getArraySize();++j)
            {
                if( answer.getDistances()[i][j]!=-1 && answer.getDistances()[i][j]!=INF)
                {
                    Edge e=new Edge(i,j);
                    edges[index]=e;
                    ++index;
                }
            }


        int index1,index2;


    //    answer.printPath();
        
        for(int i=0; i<index; ++i)
            for(int j=0; j<index; ++j)
            {
                if(i!=j)
                {
                Edge toTake1=edges[i];
                Edge toTake2=edges[j];

                index1=answer.findIndexInPath(toTake1);
              //  if(index1!=-1)
                  answer.getPath().add(index1, toTake1);
                index2=answer.findIndexInPath(toTake2);
              // if(index2!=-1)
                    answer.getPath().add(index2, toTake2);
                if(answer.pathOk()==true)
                {
                    return;
                }
                else
                {
                    answer.getPath().remove(index2);
                    answer.getPath().remove(index1);
                }
                }
            }
    }


   public String printAnswer3()
    {
        answer.setDescription2();
        return answer.getDescription2();

    }

    public String printAnswer2()
    {

        answer.setDescription();
        return answer.getDescription();

    }

    public int getIleKrokow()
    {

        return ileKrokow;
    }

    /**Zwraca tablice kosztow roota*/
    public Integer[][] getDistances () {
        return answer.getDistances();
    }

    /**Zwraca rozmiar roota*/
    public int getSize () {
        return answer.getArraySize();
    }

}

