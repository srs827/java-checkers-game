import java.awt.*;
import javax.swing.*;
public class P1PieceIconK implements Icon{

 @Override
 /**
  * @param Component c which is component to be drawn on
  * @param Graphics g which is Java graphics
  * @param int x which is x pos of icon
  * @param int y which is y pos of icon
  * Paints the circular icon in intended x and y pos 
  */ 
  public void paintIcon(Component c, Graphics g, int x, int y) {
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setColor(new Color(172, 0, 181));
    g2.fillOval(x, y, getIconWidth() - 1, getIconHeight() - 1);
    g2.dispose();
  }

  @Override
  /**
  * @return int which is width
  * gets icon width
  */
  public int getIconWidth() {
    return 60;
  }

  @Override
  /**
  * @return int which is height
  * gets icon height
  */
  public int getIconHeight() {
    return 60;
  }
} 