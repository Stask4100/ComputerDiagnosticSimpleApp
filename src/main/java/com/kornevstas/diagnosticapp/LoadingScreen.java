package com.kornevstas.diagnosticapp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class LoadingScreen extends JFrame{

    
    JLabel welcome, start;
    JPanel top, center;
    
    public LoadingScreen() {
        setLayout(null);
        
        top = new JPanel();
        top.setLayout(null);
        top.setBounds(0, 0, 800, 150);
        top.setBackground(new Color(73, 77, 139));
        add(top);
        
        welcome = new JLabel("Welcome to Diagnostic Program");
        welcome.setBounds(70, 10, 750, 50);
        welcome.setFont(new Font("Raleway", Font.BOLD, 42));
        welcome.setForeground(Color.WHITE);
        top.add(welcome);
        
        center = new JPanel();
        center.setLayout(null);
        center.setBounds(0, 150, 800, 300);
        center.setBackground(new Color(128, 191, 255));
        add(center);
        
        start = new JLabel("Do you want start program?");
        start.setBounds(100, 10, 750, 50);
        start.setFont(new Font("Raleway", Font.BOLD, 42));
        start.setForeground(Color.WHITE);
        center.add(start);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/close.png"));
        Image i2 = i1.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel close = new JLabel(i3);
        close.setBounds(400, 110, 50, 50);
        center.add(close);
        
        close.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
    });
        
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/yesarrow.png"));
        Image i5 = i4.getImage().getScaledInstance(75, 75, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel yescheck = new JLabel(i6);
        yescheck.setBounds(300, 95, 75, 75);
        center.add(yescheck);
        
        yescheck.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setString("Loading...");
        progressBar.setStringPainted(true);
        progressBar.setBounds(280, 200, 200, 30);
        center.add(progressBar);

        center.revalidate();
        center.repaint();

        Timer timer = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new DiagnosticApp().setVisible(true);
            }
        });
        timer.setRepeats(false);
        timer.start();
            }
    });
        
        setSize(800, 450);
        setLocation(350, 200);
        setTitle("Computer Diagnostic App");
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new LoadingScreen();
    }
    
}
