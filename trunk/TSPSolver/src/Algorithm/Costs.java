package Algorithm;
import java.util.*;
import java.lang.*;


// <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
// #[regen=yes,id=DCE.B1E29A5E-B0C9-DE82-5C7F-2D9407852545]
// </editor-fold> 
public class Costs {

    // ATRIBUTES:
    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C98302E3-1152-F130-A844-F3457B1473A9]
    // </editor-fold> 
    private Integer[][] distances;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F91F661B-AEC4-C8C7-027A-C4ED2B889438]
    // </editor-fold> 
    private int size;
    private int arraySize;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.C3C6D90E-B87F-7E39-7894-2F867EA58A96]
    // </editor-fold> 
    private int lowerBound;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.9794A3B4-71FF-E157-7A13-C7773A3FFE63]
    // </editor-fold>
    /**
     * dla każdej macierzy kosztów wyznaczamy ścieżkę krawędzi które ona zawiera
     */
    private ArrayList<Edge> path;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.5275A5C1-0E3C-ED28-030E-DC9E857A5296]
    // </editor-fold> 
    private String description;

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
    public Costs (int n) {
        path=new ArrayList<Edge>();
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
             * 2 przypadki
         * piewszy przypadek bierzemy łuk (f,t)
         * dodajemy łuk do ścieżki wziętych łuków dla tej macierzy kosztów w miejsce takie aby
         * możliwie tworzyły spójną ścieżkę np (a,b)(b,c)
         * z f już nie możemy więcej nigdzie
         * wyjść ani wejść do t więc te rzędy 'usuwamy' wpisując wartości -1
         * i zapobiegamy pętli ustawiając (t,f) = INF
         */
    public Costs (int n, Costs parent, Edge e, boolean take) {
        path=new ArrayList<Edge>();
        ArrayList<Edge> parentPath=parent.getPath();
        for(Iterator<Edge>it=parentPath.iterator(); it.hasNext();)
        {
            Edge val=it.next();
            path.add(val);

        }
        size=n;
        arraySize=parent.getArraySize();
        distances=new Integer[arraySize][];
        for(int i=0; i<arraySize; ++i)
        {
            distances[i]=new Integer[arraySize];
        }
        for(int i=0; i<arraySize; ++i)
            for(int j=0; j<arraySize; ++j)
                distances[i][j]=parent.getDistances()[i][j];
        int f=e.getFrom();
        int t=e.getTo();
          if(f<arraySize && t<arraySize)
          {
        if(take==true)
        {
           if(path.isEmpty())
           {
               path.add(e);
            
           }
           else
           {
               int index=0;
               boolean done=false;
               for(Iterator<Edge> it= path.iterator(); it.hasNext();)
               {
                   
                    Edge a=it.next();
                    if(a.getTo()==f || a.getFrom()==t)
                    {
                        path.add(index,e);
                        done=true;
                        break;
                    }
                    ++index;
               }
               if(done==false)
                   path.add(e);
           }
         
           
            for(int i=0; i<arraySize; ++i)
            {
                distances[f][i]=-1;
                distances[i][t]=-1;
            }
           //BLOKOWNIE PETLI - ZLE
           // todo SPRAWDZIC CO Z BLOKOWNAIEM ZROBIC Z PETLA
           //POWYPISYWAC PATHY DLA KAZDEJ MACIERZY
            if(distances[t][f]!=-1)
            distances[t][f]=INF;
           }
            
        
        else
        {
            distances[f][t]=INF;
        }
          }
        //setLowerBoundAndReduce(parent.getLowerBound());
        //lowerBound+=getLowerBound();
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.3F4B4058-332A-95F5-044F-4891723043EF]
    // </editor-fold> 
    public String getDescription () {
        return description;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.E86A8C45-4692-08F7-C201-67F546E3BCDE]
    // </editor-fold> 
    public void setDescription () {
        description="";
        for(int i=0; i<path.size(); ++i)
        {
            Edge e=path.get(i);
            description+="(";
            description+=e.getFrom()+1;
            description+="-";
            description+=e.getTo()+1;
            description+=")";
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
    public void setDistances (int i, int j, int k) {
        //TODO metoda ma pobierać dane wpisane przez uzytkownika
        // z gui przechwycic wartości i wstawić
        // tymczasowo wartosci wpisywane na sztywno
        /*distances[0][1]=3; distances[0][2]=93; distances[0][3]=13; distances[0][4]=33;distances[0][5]=9;
        distances[1][0]=4; distances[1][2]=77; distances[1][3]=42; distances[1][4]=21;distances[1][5]=16;
        distances[2][0]=45; distances[2][1]=17; distances[2][3]=36; distances[2][4]=16;distances[2][5]=28;
        distances[3][0]=39; distances[3][1]=90; distances[3][2]=80; distances[3][4]=56;distances[3][5]=7;
        distances[4][0]=28; distances[4][1]=46; distances[4][2]=88; distances[4][3]=33;distances[4][5]=25;
        distances[5][0]=3; distances[5][1]=88; distances[5][2]=18; distances[5][3]=46;distances[5][4]=92;
    */

    //wersja Gochy:
        if (i< size)
            if(j<size)
                distances[i][j] = k;
    }
   /* public void setDistancesTemp()
    {
        distances[0][1]=3; distances[0][2]=93; distances[0][3]=13; distances[0][4]=33;distances[0][5]=9;
        distances[1][0]=4; distances[1][2]=77; distances[1][3]=42; distances[1][4]=21;distances[1][5]=16;
        distances[2][0]=45; distances[2][1]=17; distances[2][3]=36; distances[2][4]=16;distances[2][5]=28;
        distances[3][0]=39; distances[3][1]=90; distances[3][2]=80; distances[3][4]=56;distances[3][5]=7;
        distances[4][0]=28; distances[4][1]=46; distances[4][2]=88; distances[4][3]=33;distances[4][5]=25;
        distances[5][0]=3; distances[5][1]=88; distances[5][2]=18; distances[5][3]=46;distances[5][4]=92;
    }*/

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.B41B1FF7-A0B4-FF0D-EC1F-FF846CD47B13]
    // </editor-fold> 
    public int getLowerBound () {
        return lowerBound;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.D74975C4-6175-82C6-AEC5-8021D778CB01]
    // </editor-fold> 
    public void setLowerBoundAndReduce (int oldLowerBound) {
        int lb=oldLowerBound;
        for(int i=0; i<arraySize; ++i)
        {
            int minPoziom=INF;
            for(int j=0; j<arraySize; ++j)
            {
                if(distances[i][j]!=-1 && distances[i][j]<minPoziom)
                    minPoziom=distances[i][j];
            }
            for(int j=0; j<arraySize; ++j)
            {
                if(distances[i][j]!=INF && distances[i][j]!=-1)
                    distances[i][j]-=minPoziom;
            }
            if(minPoziom!=INF)
            lb+=minPoziom;
        }
        for(int i=0; i<arraySize; ++i)
        {
            int minPion=INF;
            for(int j=0; j<arraySize; ++j)
            {
                if(distances[j][i]!=-1 && distances[j][i]<minPion)
                    minPion=distances[j][i];
            }
            for(int j=0; j<arraySize; ++j)
            {
                if(distances[j][i]!=INF &&distances[j][i]!=-1)
                distances[j][i]-=minPion;
            }
            if(minPion!=INF)
            lb+=minPion;
        }
        lowerBound=lb;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.1265C963-939B-377D-3732-05384D0E7A5E]
    // </editor-fold> 
    public ArrayList<Edge> getPath () {
        return path;
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
    public void setEdgeToBranch () {
       int maxGrowth=0;
       for(int i=0; i<arraySize; ++i)
       {
           for(int j=0; j<arraySize; ++j)
           {
               if(distances[i][j]==0)
               {

                   int growth=0, min=INF;
                    for(int k=0; k<arraySize; ++k)
                    {
                        if(k!=j && distances[i][k]<min && distances[i][k]!=-1)
                            min=distances[i][k];
                    }
                   growth+=min;
              
                   min=INF;
                   for(int k=0; k<arraySize; ++k)
                   {
                       if(k!=i && distances[k][j]<min && distances[k][j]!=-1)
                           min=distances[k][j];
                   }
                   growth+=min;
                   if(growth>maxGrowth)
                   {
                       maxGrowth=growth;
                       Edge a=new Edge(i,j);
                       edgeToBranch=a;
                   }
                   //System.out.println("hohoh"+growth);
               }
           }
       }
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7E829106-F424-1058-A7FC-80968FCE53BF]
    // </editor-fold> 
    public void addToTree () {
    }
    public void showDistances()
    {
        for(int i=0; i<arraySize; ++i)
        {
            for(int j=0; j<arraySize; ++j)
                System.out.print(distances[i][j]+" ");
            System.out.println();
        }
        System.out.println();
    }
    public void showPath()
    {
        for(Iterator<Edge>it=path.iterator(); it.hasNext();)
        {
            Edge val=it.next();
            System.out.println(val.getFrom());
            System.out.println(val.getTo());
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
