package Algorithm;
import java.util.*;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.B1E29A5E-B0C9-DE82-5C7F-2D9407852545]
// </editor-fold> 
public class Costs {

    // ATRIBUTES:
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C98302E3-1152-F130-A844-F3457B1473A9]
    // </editor-fold>
    /** tabela kosztów*/
    private Integer[][] distances;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F91F661B-AEC4-C8C7-027A-C4ED2B889438]
    // </editor-fold>
    /**  rozmiar kosztów czasem rózny od rozmiaru tabeli
     * w lewym poddrzewie w ykreslamy 1  wiersz i 1 kolumną size się zmniejsza
     * w tabeli zaznaczamy te wiersze i kolumny -1 arraySize - rozmiar tabeli pozostaje bez
     * zmian*/
    private int size;
    private int arraySize;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C3C6D90E-B87F-7E39-7894-2F867EA58A96]
    // </editor-fold> 
    private int lowerBound;
    public int lewy;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9794A3B4-71FF-E157-7A13-C7773A3FFE63]
    // </editor-fold>
    /**
     * dla każdej macierzy kosztów wyznaczamy ścieżkę krawędzi które ona zawiera
     */
    private ArrayList<Edge> path;

    /*Spis krawędzi, których nie zawiera*/
    private ArrayList<Edge> noPath;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5275A5C1-0E3C-ED28-030E-DC9E857A5296]
    // </editor-fold> 
    private String description;//krawędzie, z którymi jest ścieżka

    private String description2;//krawędzie, których nie ma w ścieżce

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.6C270CB1-6125-0FDB-19FA-3EE73D07CEAA]
    // </editor-fold> 
    private Edge edgeToBranch;
    /**
     *  symboliczna nieskonczoność wspolna dla wszystich instancji
     */
    private static final int INF=100000000;
/////////////////////////////////////////////////
// METHODS:
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.E6C135C1-B775-4307-DEDF-6D4DDD33738D]
    // </editor-fold>
    /** najprostszy konstruktor wypełnia ksozty inf
     * @param n
     */
    public Costs (int n) {
        path=new ArrayList<Edge>();
        noPath=new ArrayList<Edge>();
        size=n;
        arraySize=n;
        distances=new Integer[arraySize][];
        for(int i=0; i<arraySize; ++i)
        {
            distances[i]=new Integer[arraySize];
        }
        for(int i=0; i<arraySize; ++i)
            for(int j=0; j<n; ++j)
            distances[i][j]=INF;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.B06BF4CD-4CF5-1A0B-E39E-9EF2A0EBD370]
    // </editor-fold>

    /**
     *
     * @param n
     * @param parent
     * @param e
     * @param take
     * konstruktor do tworzesnia dziecka dziecko ma rozmiar n, Edge e - krawędź
     * rodzica wg której dzieliliśmy
     * jeżeli lewe dziecko to bierzemy tą krawędź take =true jeżeli prawe to nie
     * take =false
     */
    public Costs (int n, Costs parent, Edge e, boolean take) {
        // tworzymy ścieżkę wziętych krawędzi dla dziecka = ścieżka rodzica
        path=new ArrayList<Edge>();
        noPath=new ArrayList<Edge>();
        ArrayList<Edge> parentPath=parent.getPath();
        ArrayList<Edge> parentNoPath=parent.getNoPath();
        for(Iterator<Edge>it=parentPath.iterator(); it.hasNext();)
        {
            Edge val=it.next();
            path.add(val);

        } // w tym momencie ścieżka dziecka == ścieżka rodzica
        for(Iterator<Edge>it=parentNoPath.iterator(); it.hasNext();)
        {
            Edge val=it.next();
            noPath.add(val);

        }

        size=n; // już właściwy rozmiar dziecka jeżeli take==true size= rozmiar rodzica -1
                // jezeli take==false size=rozmiar rodzica
        arraySize=parent.getArraySize();
        distances=new Integer[arraySize][];
        for(int i=0; i<arraySize; ++i)
        {
            distances[i]=new Integer[arraySize];
        }
        for(int i=0; i<arraySize; ++i)
            for(int j=0; j<arraySize; ++j)
                distances[i][j]=parent.getDistances()[i][j];
        // tablica kosztów dziecka == tablicy kosztów rodzica
        int f=e.getFrom();
        int t=e.getTo();
        //jezeli krawedz ktora mamy wziac ma sens czyli miejsci sie w tabeli
        if(f<arraySize && t<arraySize)
        {
            if(take==true) // lewe poddrzewo bierzemy krawedz
            {
                if(path.isEmpty()) // path pusty dodajemy e - peirwsza i ostatnia krawedz
                {
                    path.add(e);
            
                }
            //jeżeli już cośbyło w path to wstawimy od razu na właściwe miejsce
            // tzn tak aby tworzyło możliwie nieprzerwany ciąg (a , b)(b,c)(c,d)
            //jeżeli się tak nie da to na koniec
            // f(rom) - początek krawedzi do wstawienia t(o)- koniec
            else
            {
                int index=0;
                boolean done=false;
                for(Iterator<Edge> it= path.iterator(); it.hasNext();) // iteruejmy po liscie krawedzi
                {
                   
                    Edge a=it.next(); //bierzemy kolejny element z listy
                    
                    if(a.getTo()==f) // wstawiamy za elementem
                    {
                        path.add(index+1,e);
                        done=true; // wstawione we wsłaściwie miejsce przerywamy działanie
                        break;
                    }
                    if(a.getFrom()==t) // wstawiamyp rzed elementem
                    {
                        path.add(index,e);
                        done=true;
                        break;
                    }
                    ++index;
               }
               if(done==false) // jezeli nie znalezlismy właściwego meijsca wstawiamyn na koniec
                   path.add(e);
           }
         
           //wzieliśmy frawędź (f,t) więc nie możemy już wyjść z f ani wejść do t innym sposobem
           // usuwamy odpowiedni rząd i kolumnę z tabeli kosztów
            for(int i=0; i<arraySize; ++i)
            {
                distances[f][i]=-1;
                distances[i][t]=-1;
            }
           blokujPetle(); //blokuje ścieżkę przed pojawieniem się pętli
        }        
        else
        {
            if(noPath.isEmpty()) // path pusty dodajemy e - peirwsza i ostatnia krawedz
                {
                    noPath.add(e);

                }
            //jeżeli już cośbyło w path to wstawimy od razu na właściwe miejsce
            // tzn tak aby tworzyło możliwie nieprzerwany ciąg (a , b)(b,c)(c,d)
            //jeżeli się tak nie da to na koniec
            // f(rom) - początek krawedzi do wstawienia t(o)- koniec
            else
            {
                int index=0;
                boolean done=false;
                for(Iterator<Edge> it= noPath.iterator(); it.hasNext();) // iteruejmy po liscie krawedzi
                {

                    Edge a=it.next(); //bierzemy kolejny element z listy

                    if(a.getTo()==f) // wstawiamy za elementem
                    {
                        noPath.add(index+1,e);
                        done=true; // wstawione we wsłaściwie miejsce przerywamy działanie
                        break;
                    }
                    if(a.getFrom()==t) // wstawiamyp rzed elementem
                    {
                        noPath.add(index,e);
                        done=true;
                        break;
                    }
                    ++index;
               }
               if(done==false) // jezeli nie znalezlismy właściwego meijsca wstawiamyn na koniec
                   noPath.add(e);
           }
           distances[f][t]=INF;
        }
     }
       
 }

    //nie ma problemu pustego path bo blogujPetle wyowlujemy tlyko po dodaniu patha
    private void blokujPetle()
    {

        Iterator<Edge> it=path.iterator();
        Edge start=it.next();
        Edge stop=start;
        int prev=start.getTo();
        while(it.hasNext())
        {
            Edge e=it.next();
            if(prev!=e.getFrom())
            {
                distances[stop.getTo()][start.getFrom()]=INF; //blokuje wszystkie spójne częsci poza ostatnią
                start=e;
                stop=e;
                prev=start.getTo();
            }
            else
            {
                stop=e;
                prev=e.getTo();
            }
            
        }
            distances[stop.getTo()][start.getFrom()]=INF;
    // zawsze ostatnia pętla zostaje do zablokowania jeżeli path spójny to całość jeżeli nie
    // to ostatnia część
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.3F4B4058-332A-95F5-044F-4891723043EF]
    // </editor-fold> 
    public String getDescription () {
        return description;
    }

    public String getDescription2 () {
        return description2;
    }
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.E86A8C45-4692-08F7-C201-67F546E3BCDE]
    // </editor-fold>
    /**
     * na podstawie path tworzy opis do gui ścieżki elementu String description
     */
    public void setDescription ()
    {
        description="";
        if (!path.isEmpty())
        {
            description+="z: ";
            for(int i=0; i<path.size(); ++i)
            {
                Edge e=path.get(i);
                description+="(";
                description+=e.getFrom()+1;
                description+="-";
                description+=e.getTo()+1;
                description+=") ";
            }
        }
    }
    public void setDescription2 ()
    {
        description2="";
        if (!noPath.isEmpty())
        {
            description2+="bez: ";
            for(int i=0; i<noPath.size(); ++i)
            {
                Edge e=noPath.get(i);
                description2+="(";
                description2+=e.getFrom()+1;
                description2+="-";
                description2+=e.getTo()+1;
                description2+=") ";
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.A86B808A-99D3-C47E-09E5-AA6130EFD29A]
    // </editor-fold> 
    public Integer[][] getDistances () {
        return distances;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.ACCA8629-200C-821C-7C1F-C9C09A44971A]
    // </editor-fold> 
    public void setDistances (int i, int j, int k)
    {
        if (i< size)
            if(j<size)
                distances[i][j] = k;
    }
  
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.B41B1FF7-A0B4-FF0D-EC1F-FF846CD47B13]
    // </editor-fold> 
    public int getLowerBound () {
        return lowerBound;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.D74975C4-6175-82C6-AEC5-8021D778CB01]
    // </editor-fold>
    /**
     * redukuje macierz i oblicza dolne ograniczenia
     * @param oldLowerBound
     */
    public void setLowerBoundAndReduce (int oldLowerBound) {
        int lb=oldLowerBound;
        //redukcja po rzędach
        for(int i=0; i<arraySize; ++i)
        {
            int minPoziom=INF;
            // w kazdym rzedzie wybieramy wartosć najmniejszą
            for(int j=0; j<arraySize; ++j)
            {
                if(distances[i][j]!=-1 && distances[i][j]<minPoziom)
                    minPoziom=distances[i][j];
            }
            // od wszystkich elelemtnów tego rzędu odejmujemy tą wartosć najmnijeszą
            for(int j=0; j<arraySize; ++j)
            {
                if(distances[i][j]!=INF && distances[i][j]!=-1)
                    distances[i][j]-=minPoziom;
            }
            if(minPoziom!=INF)
            lb+=minPoziom; // do lb dodajemny najmnijesze elementy z rzedów
        }
        //redukcja kolumn
        for(int i=0; i<arraySize; ++i)
        {
            int minPion=INF;
            //w każdej kolumnie wybieramy najmniejszą wartość
            for(int j=0; j<arraySize; ++j)
            {
                if(distances[j][i]!=-1 && distances[j][i]<minPion)
                    minPion=distances[j][i];
            }
            // od wszystkich elementów kolumny odejmujemy najmniejszą wartość
            for(int j=0; j<arraySize; ++j)
            {
                if(distances[j][i]!=INF &&distances[j][i]!=-1)
                distances[j][i]-=minPion;
            }
            if(minPion!=INF)
            lb+=minPion; // dodajemny najmniejze elementy z kazdej kolumny
        }
        lowerBound=lb;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.1265C963-939B-377D-3732-05384D0E7A5E]
    // </editor-fold> 
    public ArrayList<Edge> getPath () {
        return path;
    }

    public ArrayList<Edge> getNoPath () {
        return noPath;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.51DD69AC-26BB-EB64-8462-AD56CCC6E583]
    // </editor-fold> 
    public int getSize () {
        return size;
    }

    
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.45480FBC-5F06-C21A-2CF3-9E0EB3BDD036]
    // </editor-fold> 
    public Edge getEdgeToBranch () {
        return edgeToBranch;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.46E659CB-98C7-9557-1B64-452BB8831131]
    // </editor-fold>
    /**
     * wyiera krawedz wg ktorej dokonujemy podziału rodzica na galaz lewa i prawa
     */
    public void setEdgeToBranch () {
       int maxGrowth=0;
       for(int i=0; i<arraySize; ++i)
       {
           for(int j=0; j<arraySize; ++j)
           {
               // krawedz wybieramy tylko sposrod pol bedacych zerami w zredukowanej !! macierzy
               //dla kazdego pola liczymy wzrost ograniczenia dolnego dla rozw nie zawierajcych tego luku
               //czyli po polsku dla kazdego pola z jego rzedu i jego kolumny wybieramy najmniejszy element poza nim
               //ich suma stanowi wzrost lb
               //ze wszystkich wzrostow lb wybieramy max i wg tego luku ktory ma max dzielimy
               if(distances[i][j]==0) 
               {
                   int growth=0, min=INF;
                   //wybranie min dla wiersza
                    for(int k=0; k<arraySize; ++k)
                    {
                        if(k!=j && distances[i][k]<min && distances[i][k]!=-1)
                            min=distances[i][k];
                    }
                   growth+=min;
              
                   min=INF;
                   //wybranie mina dla kolumny
                   for(int k=0; k<arraySize; ++k)
                   {
                       if(k!=i && distances[k][j]<min && distances[k][j]!=-1)
                           min=distances[k][j];
                   }
                   growth+=min; // przyrost dla tego luku
                   if(growth>maxGrowth) //wybor max przyrostu
                   {
                       maxGrowth=growth;
                       Edge a=new Edge(i,j);
                       edgeToBranch=a;
                   }
               }
           }
       }
    }
    
public int getArraySize()
{
   return arraySize;
}

public int findIndexInPath(Edge toPut)
{
    int index=1;
    int indexToReturn=path.size();
    boolean found=false;

    for(Iterator<Edge>it=path.iterator();it.hasNext();)
    {
        Edge e=it.next();
       // System.out.println(toPut.getFrom());
        //  System.out.println(toPut.getTo());
        if(e.getTo()==toPut.getFrom())
        {
            indexToReturn = index;
            found=true;
        }
        index++;
    }
    if(found==false)
    {
    index=0;
        for(Iterator<Edge>it=path.iterator();it.hasNext();)
        {
        Edge e=it.next();
        if(e.getFrom()==toPut.getTo())
        {
            indexToReturn = index;
        }
        index++;
        }
    }
    return indexToReturn;
}

/**
 * sprawdza spojnosc sciezki czy stanowi ciag spojnychp rzejsc pomiedzy miastami
 * @return
 */
public boolean pathOk()
{
    if(path.get(0).getFrom()!=path.get(path.size()-1).getTo())
    {
        return false;
    }
    for(int i=0; i<path.size()-1; ++i)
    {
        if(path.get(i).getTo()!=path.get(i+1).getFrom())
            return false;
    }
    return true;

}
}
