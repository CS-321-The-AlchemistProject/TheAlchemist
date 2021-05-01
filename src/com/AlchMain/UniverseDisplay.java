package com.AlchMain;

import AlchMain.AlchGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UniverseDisplay extends Canvas implements MouseListener, ActionListener {
    public String brush_chem = "H2O";
    public double brush_temp = 273.15;

    public static JLabel label1 = new JLabel("Enter Chemical and Temperature.");
    public static JButton button1 = new JButton("Submit Chem");
    public static JButton button2 = new JButton("Submit Temp");
    public static JTextField text1 = new JTextField(10);
    public static JButton button3 = new JButton("Chem View");
    public static JButton button4 = new JButton("Temp View");
    public static JButton button5 = new JButton("Brush Size");
    public static JTextField text2 = new JTextField(10);
    public static JTextField text3 = new JTextField(3);

    /**
    * The UniverseDisplay will set up the GUI to draw the universe.
    * @param width, the desired width of the grid
    * @param height, the desired height of the grid
    * @param title, the title of the frame
    * @param alchGui, the object of alchGUI that will have the items set
    */
    public UniverseDisplay(int width, int height, String title, AlchGUI alchGui) {
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        GridBagLayout grid_bag = new GridBagLayout();
        frame.setLayout(grid_bag);
        GridBagConstraints c = new GridBagConstraints();

        frame.addMouseListener(this);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);

        JPanel panel1 = new JPanel();
        panel1.add(text1);
        panel1.add(button1);
        panel1.add(text2);
        panel1.add(button2);
        panel1.add(label1);
        c.fill = GridBagConstraints.PAGE_START;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        grid_bag.setConstraints(panel1, c);
        frame.add(panel1, c);

        JPanel panel2 = new JPanel();
        panel2.add(button3);
        panel2.add(button4);
        c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        grid_bag.setConstraints(panel2, c);
        frame.add(panel2, c);

        JPanel panel3 = new JPanel();
        panel3.add(text3);
        panel3.add(button5);
        c.fill = GridBagConstraints.PAGE_START;
        c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        grid_bag.setConstraints(panel3, c);
        frame.add(panel3, c);

        c.fill = GridBagConstraints.CENTER;
        c.ipadx = AlchGUI.UNIVERSE_WIDTH;
        c.ipady = AlchGUI.UNIVERSE_HEIGHT;
        c.weightx = 0.0;
        c.weighty = 0.5;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 1;
        grid_bag.setConstraints(alchGui, c);
        frame.add(alchGui);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        alchGui.start();
    }

    /**
    * The mouseClicked method will check if the mouse has been clicked.
    */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
    * The mousePressed method will check if the mouse has been pressed.
    */
    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    /**
    * The mouseReleased method will check if the mouse has been released.
    */
    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    /**
    * The mouseEntered method will check if the mouse event has been entered.
    */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
    * The mouseExited method will check if the mouse event has been exited.
    */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
    * The actionPerformed method will take action depending on which text box the information is placed in. One will gather the chemical while the other will change the temperature.
    * @param e, is the action event
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        System.out.println(s);
        if (s.equals("Submit Chem")) {
            AlchGUI.brush_chem = text1.getText();
        }
        if (s.equals("Submit Temp")) {
            if (!(text2.getText().equals(""))) {
                AlchGUI.brush_temp = Double.parseDouble(text2.getText());
            }
            else {
                AlchGUI.brush_temp = 0.01;
            }
        }
        if (s.equals("Chem View")) {
            AlchGUI.view_state = AlchGUI.View.CHEM_VIEW;
        }
        if (s.equals("Temp View")) {
            AlchGUI.view_state = AlchGUI.View.TEMP_VIEW;
        }
        if (s.equals("Brush Size")) {
            AlchGUI.brush_size = Integer.parseInt(text3.getText());
            if (AlchGUI.brush_size <= 0) {
                AlchGUI.brush_size = 1;
            }
            else if (AlchGUI.brush_size > 300) {
                AlchGUI.brush_size = 300;
            }
        }
    }
}
