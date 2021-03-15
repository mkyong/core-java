package com.mkyong.java16.jep392;

import javax.swing.*;
import java.awt.*;

public class JEP392 {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Hello World Java Swing");
        // display frame site
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // center the JLabel
        JLabel lblText = new JLabel("Hello World!", SwingConstants.CENTER);
        // add JLabel to JFrame
        frame.getContentPane().add(lblText);

        // display it
        frame.pack();
        frame.setVisible(true);

    }
}
