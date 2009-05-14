/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrafVisualization;
import javax.swing.*;
import java.awt.*;
import Algorithm.*;
import java.util.*;

/**
 *
 * @author Dorota
 */
public class Canva extends Canvas
{
    Solver a;
    public Canva(int n,int tab[][])
    {
        a=new Solver(n,tab);
        a.branchAndBound();
        Costs root=(Costs)a.tree.getRoot();
        a.createTreeVisualization();
        a.completePath();
        a.printAnswer();
        setSize(1000, 1000);
    }
    @Override
    /**
     * metoda ktora musi bycw kazdej canvie przedefiniowna
     * uruchamia sie sama niewiadomo keidy jak gasrbage collector i musi przyjmowac
     * taki parametr
     * w niej rysujemy wszelkie rzeczy na canvie - okienko z tabelkami
     */
    public void paint(Graphics g)
    {

        Graphics2D g2;
        int height;
        g2=(Graphics2D)g;
        height=getHeight();

       Stack <Vertex>stos=new Stack<Vertex>();
       Collection<Vertex> children=new ArrayList<Vertex>();
        Vertex root=a.treeVisualization.findRoot();
        stos.push(root);
        int tableIndex=1; // nr aktualnie wypisywanej tabelki
        while(stos.isEmpty()==false)
        {
        root=stos.pop();
       // System.out.println(tableIndex);
        //Wypisywanie 1 tabelki
        String napis=Integer.toString(root.getId());
        //wyliczamy połozenie tabelki
        int startx,starty,rownr,colnr;
        rownr=tableIndex/5; // zakladamy ze w 1 rzedzie bedzie 5 tabelek -> nrrzedu=nrtabelki/5
        colnr=tableIndex%5; // 5 tabelek w rzedzie == 5 kolumn
        // na podstawie nr wierrsza i kolumny wyliczamy  wsp punktu gdzie zaczynamy
        // wypisywac tabelke jak cos bedziemy dopisywac trzeba zadbaco odpowiednie odtespy
        starty=20+rownr*200;
        startx=20+colnr*200;
        g2.drawString(napis, startx,starty); // wypisanie nr tableki
        // wypisanie tabelki
        for(int i=0; i<root.getDistancesSize(); ++i)
        {
            //ponizsze w porzadku wypisuje nr kolumn tabelki dorobic rzedy
           /* napis=" ";
            for(int j=1; j<=root.getDistancesSize(); ++j)
                napis+=j+"   ";*/

            // g2.drawString(napis,startx,starty+30);
            napis=" ";
            for(int j=0; j<root.getDistancesSize(); ++j)
            {
               // napis+=i+1+"  ";
                if(root.getDistances()[i][j]!=100000000)
                napis+=root.getDistances()[i][j]+"  ";
                else
                   napis+="inf ";
            }
            g2.drawString(napis,startx,starty+20*(i+1)+30);
        }
        tableIndex++;
        //koniec wypisywania tabelki
        children=a.treeVisualization.gv.getSuccessors(root);
        for(Iterator<Vertex>it=children.iterator();it.hasNext();)
        {
            Vertex naStos=it.next();
            stos.push(naStos);
        }
        
        }
    }

}
