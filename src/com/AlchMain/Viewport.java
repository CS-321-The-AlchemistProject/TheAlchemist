package com.AlchMain;

import javax.swing.JPanel;
import java.awt.Graphics;

public class Viewport extends JPanel {
   
    public Viewport(){
        this.setBounds(0,0, 200, 200);
    }
    

    protected void painComponent(Graphics g){
        g.fillRect(0, 0, 100, 100);
    }
}
