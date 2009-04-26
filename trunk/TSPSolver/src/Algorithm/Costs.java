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
    private int[][] distances;

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.F91F661B-AEC4-C8C7-027A-C4ED2B889438]
    // </editor-fold> 
    private int size;

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
    private List<Edge> path;

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
        size=n;
        for(int i=0; i<n; ++i)
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
        distances=parent.getDistances();
        int f=e.getFrom();
        int t=e.getTo();
        if(take==true)
        {
           if(path.isEmpty())
               path.add(e);
           else
           {
               Iterator<Edge> it=path.iterator();
               while(it.hasNext() && it.next().getTo()!=f)
               {
               }
           }
            for(int i=0; i<size; ++i)
            {
                distances[f][i]=-1;
                distances[i][t]=-1;
            }
            if(distances[t][f]!=-1)
            distances[t][f]=INF;
            
        }
        else
        {
            distances[f][t]=INF;
        }
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
    public void setDescription (String val) {
        this.description = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.A86B808A-99D3-C47E-09E5-AA6130EFD29A]
    // </editor-fold> 
    public int[][] getDistances () {
        return distances;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.ACCA8629-200C-821C-7C1F-C9C09A44971A]
    // </editor-fold> 
    public void setDistances (int[][] val) {
        this.distances = val;
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
    public void setLowerBound (int val) {
        this.lowerBound = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.1265C963-939B-377D-3732-05384D0E7A5E]
    // </editor-fold> 
    public List<Edge> getPath () {
        return path;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.1FE038AE-000B-EAEB-05BC-61770D1357CF]
    // </editor-fold> 
    public void setPath (List<Edge> val) {
        this.path = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.51DD69AC-26BB-EB64-8462-AD56CCC6E583]
    // </editor-fold> 
    public int getSize () {
        return size;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,regenBody=yes,id=DCE.47C338F2-4500-2BD7-1094-AE05BB94ADEF]
    // </editor-fold> 
    public void setSize (int val) {
        this.size = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.377FC451-928A-C9F3-9772-9D578A1A3670]
    // </editor-fold> 
    public void reduce () {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.610A3A58-17BA-5C41-68F1-D094EDAE60C2]
    // </editor-fold> 
    public void Unnamed () {
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
    public void setEdgeToBranch (Edge val) {
        this.edgeToBranch = val;
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.558C938C-B404-1557-1E2B-4DA2E4DC1DEE]
    // </editor-fold> 
    public void setCosts () {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.7E829106-F424-1058-A7FC-80968FCE53BF]
    // </editor-fold> 
    public void addToTree () {
    }

    // <editor-fold defaultstate="collapsed" desc=" UML Marker "> 
    // #[regen=yes,id=DCE.ADA5F4C2-426B-5696-E7FB-4AF32EFB1345]
    // </editor-fold> 
    public void addTownToPath () {
    }

}

