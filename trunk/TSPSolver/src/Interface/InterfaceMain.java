/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;

/**
 *
 * @author zupa
 */
public class InterfaceMain {

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InterfacePanel1 Panel1 = new InterfacePanel1();
                /*InterfacePanel2 Panel2 = new InterfacePanel2();
                InterfacePanel3 Panel3 = new InterfacePanel3();
                InterfacePanel4 Panel4 = new InterfacePanel4();*/
                Panel1.setVisible(true);
            }
        });
    }
/*public void PanelVisible(javax.swing.JFrame PanelS, javax.swing.JFrame PanelN)
{
    PanelN.setVisible(true);
    PanelS.setVisible(false);
}*/
}
