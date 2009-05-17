package Algorithm;

import edu.uci.ics.jung.graph.*;
import java.util.*;
import GrafVisualization.*;
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
    int id=1;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.3042A9A7-FDFE-5E68-5152-3AFC6E761C5E]
    // </editor-fold> 
    private Costs rightChild;
    private DirectedGraph<Costs,Integer>g; // dane zebrane w graf
    public DelegateTree tree; // do grafu podpiete drzewo  zeby graf sie stał drzewem były dzieci i rodzice itd
    private Costs answer;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E00E93BC-F2F5-204E-DAF9-148AB083C5B1]
    // </editor-fold>
    /**
     * dana macierz jest rootem drzewa. Drzewo skałda sie z Costsów
     * tworzymy roota i  drzewo wstawiamy roota do drzewa
     * @param n
     * @param tab
     */
    public Solver(int n, int tab[][])
    {
        root=new Costs(n);
        for(int i = 0; i<n; i++)
            for(int j = 0; j<n; j++)
                if(i!=j)
                    root.setDistances(i, j, tab[i][j]);
        g = new DirectedSparseGraph();
        tree=new DelegateTree(g);
        boolean ok=tree.addVertex(root);

    }
    /**
     * podobnedziałanie jak powyższego konstruktowa chyba niewykorzystywany
     * @param root
     */
    public Solver (Costs root/*int n*/) {
    //    root= new Costs(n);
    //    root.setDistances();
        g = new DirectedSparseGraph();
        tree=new DelegateTree(g);        
        boolean ok=tree.addVertex(root);
        }
    /* rkurencyjnie przekazywac rooota jako argument branch and bound sprawdzac czy jakis inny lisc
     * nie bedize mniejszy i jego jako argument i tak pare razy 
            */
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5EFAA0A4-EDC5-C78A-A7BD-D36B86FF52CA]
    // </editor-fold>
    /**
     * główny algorytm
     */
    public void branchAndBound () {
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
        while(counter<5 && root.getLowerBound()<min)
        {
         size=root.getSize();
        while(size>2)  // 1 iteracja algo do moemntu gdy redukowana macierz ma rozmiar 2
        {
        root.setLowerBoundAndReduce(root.getLowerBound()); // redukcja i obliczenie lb
       root.setEdgeToBranch(); // wyznaczenie luku wzgledem ktorego dzielimy
        Edge branchEdge=root.getEdgeToBranch();
        // podział i reduckaj dzieci
        leftChild = new Costs(root.getSize()-1,root,branchEdge,true);
        leftChild.setLowerBoundAndReduce(root.getLowerBound());
        rightChild=new Costs(root.getSize(),root,branchEdge,false);
        rightChild.setLowerBoundAndReduce(root.getLowerBound());
        boolean ok;
        // dodanie dzieci do drzewa
        ok=tree.addChild(edgeCounter,root, leftChild);
        edgeCounter++;
        ok=tree.addChild(edgeCounter, root,rightChild);
        edgeCounter++;
        // jako nowego roota wybieramy lewe dziecko
        root=leftChild;
        --size;
        createTreeVisualization();
       } // o przejsciu 1 iteracji rootem jest skrajnie lewe dziecko
         if(counter==0) // pierwsza iteracja
             answer=root;
         if(root.getLowerBound()<answer.getLowerBound()) // kolejne iteracje gdy daja lepsze  rozw nadpisujemy
            answer=root;
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
        }

    /**
     * wybiera nowego roota na kolejne iteracje algo zapisuje go w minLeaf a
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
                    Vertex a=new Vertex(child.getDistances(),child.getArraySize(),child.getLowerBound());
                    child.setDescription();
                    a.setDescription(child.getDescription());
                    treeVisualization.gv.addVertex(a);
                    if(parent!=null)
                    {
                        treeVisualization.gv.addEdge(id, parent, a);
                        ++id;
                    }
                   /* System.out.println(child.getLowerBound());
                    child.showDistances();
                    System.out.println("path:");
                    child.showPath();
                    System.out.println();*/
                    showTree(child,a);
                }
              /*  for(Iterator<Costs> it=children.iterator(); it.hasNext();)
                {
                    Costs child=it.next();
                    showTree(child);
                }*/
    }
    public void createTreeVisualization()
    {
        treeVisualization=new Graf();
        treeVisualization.init();
        Costs r=(Costs)tree.getRoot();
        Vertex a=new Vertex(r.getDistances(),r.getArraySize(),r.getLowerBound());
        r.setDescription();
        String desc=r.getDescription();
        a.setDescription(desc);
        treeVisualization.gv.addVertex(a);
        showTree(r,a);
        treeVisualization.drawGraf();

    }
    public void completePath()
    {
        Edge edges[]=new Edge[3];
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
       for(int i=0; i<3; ++i)
            for(int j=0; j<3; ++j)
            {
                if(i!=j)
                {
                Edge toTake1=edges[i];
                Edge toTake2=edges[j];
                index1=answer.findIndexInPath(toTake1);
                answer.getPath().add(index1, toTake1);
                index2=answer.findIndexInPath(toTake2);
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

    public void printAnswer()
    {
        //answer.showDistances();
        //System.out.println(answer.getLowerBound());
        //answer.showPath();
        answer.setDescription();
        System.out.println(answer.getDescription());

    }
}

