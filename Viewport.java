import javax.swing.JPanel;
import java.awt.Graphics;

public class Viewport {

    public Viewport(){
        this.setBounds(0,0, 100, 100);
    }
    @Override
    protected void painComponent(Graphics g){
        g.fillRect(0, 0, 50, 50);
    }
}
