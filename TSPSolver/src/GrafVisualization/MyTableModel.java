/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package GrafVisualization;

import javax.swing.table.*;
/**
 *
 * @author Dorota
 */
public class MyTableModel extends AbstractTableModel {

    Integer distances[][];
    Integer data[][];
   private String [] columnNames;
    MyTableModel(Integer tab[][],int tabSize)
    {
    distances=new Integer[tabSize][];
        for(int i=0; i<tabSize; ++i)
            distances[i]=new Integer[tabSize];

        for(int i=0; i<tabSize; ++i)
            for(int j=0; j<tabSize; ++j)
                distances[i][j]=tab[i][j];
    columnNames=new String[tabSize];
    data=new Integer [tabSize][];
    for(int i=0; i<tabSize; ++i)
        data[i]=new Integer[tabSize];
    for(Integer i=0; i<tabSize; ++i)
    {
            columnNames[i]=i.toString();
            data[i][0]=i;
    }
    for(Integer i=0; i<tabSize; ++i)
        for(int j=1; j<tabSize; ++j)
            data[i][j]=distances[i][j];
    }

    public int getColumnCount()
    {
       return columnNames.length;
    }
    public int getRowCount()
    {
        return data.length;
    }
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return (Integer)data[row][col];
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }


}
