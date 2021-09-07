import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

/**
 * InvisibleIcon class that implements Icon
 */
public class InvisibleIcon implements Icon {
   
   /**
   * InvisibleIcon constructor
   */
   public InvisibleIcon() {
      int width = 0; 
      int height = 0;
   }
   
   /**
   * @return int which is height
   * Gets icon height
   */
   public int getIconHeight() {
      return 0;
   }
    
   /**
   * @return int which is width
   * Gets icon width
   */
   public int getIconWidth() {
      return 0;
   }
    
    
   /**
   * @param Component c which is the component to draw on 
   * @param Graphics g which is Java graphics
   * @param int x which is x pos
   * @param int y which is y pos
   * Doesn't draw anything because it is supposed to be blank icon (for if piece captured/deleted/moved) 
   */
   public void paintIcon(Component c, Graphics g, int x, int y) {
   }

}