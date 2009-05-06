/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Interface;
import java.awt.Graphics;
import javax.swing.JComponent;
import java.awt.Color;
/**
 *
 * @author zupa
 */
public class myCanvas extends JComponent{
public void addLabelX(Graphics g, int x)
{
    g.drawString (String.valueOf(x), x, 0);
}
public void addLabelY(Graphics g, int y)
{
    g.drawString (String.valueOf(y), 0, y);
}
public void addCost(Graphics g, int x, int y, int k)
{
    g.setColor(Color.RED);
    g.drawString(String.valueOf(k), x, y);
}
}
