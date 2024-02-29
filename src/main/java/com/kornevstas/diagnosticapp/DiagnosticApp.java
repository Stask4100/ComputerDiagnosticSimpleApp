package com.kornevstas.diagnosticapp;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class DiagnosticApp extends JFrame implements ActionListener{

    JLabel welcome, info1, info2, info3, info4; 
    JPanel leftSide, center;
    JButton infocpu, infogpu, infohard, inforam;
    
    
    public DiagnosticApp() {
        
        setLayout(null);
        
        leftSide = new JPanel();
        leftSide.setLayout(null);
        leftSide.setBounds(0, 0, 150, 450);
        leftSide.setBackground(new Color(73, 77, 139));
        add(leftSide);
        
        infocpu = new JButton("Info CPU");
        infocpu.setBounds(10, 90, 125, 30);
        infocpu.setFont(new Font("System", Font.BOLD, 13));
        infocpu.setBackground(new Color(255, 222, 52));
        infocpu.addActionListener(this);
        leftSide.add(infocpu);
        
        infogpu = new JButton("Info GPU");
        infogpu.setBounds(10, 170, 125, 30);
        infogpu.setFont(new Font("System", Font.BOLD, 13));
        infogpu.setBackground(new Color(255, 222, 52));
        infogpu.addActionListener(this);
        leftSide.add(infogpu);
        
        infohard = new JButton("Info Hard Drive");
        infohard.setBounds(10, 250, 125, 30);
        infohard.setFont(new Font("System", Font.BOLD, 13));
        infohard.setBackground(new Color(255, 222, 52));
        infohard.addActionListener(this);
        leftSide.add(infohard);
        
        inforam = new JButton("Info RAM");
        inforam.setBounds(10, 330, 125, 30);
        inforam.setFont(new Font("System", Font.BOLD, 13));
        inforam.setBackground(new Color(255, 222, 52));
        inforam.addActionListener(this);
        leftSide.add(inforam);
        
        center = new JPanel();
        center.setBounds(150, 0, 650, 450);
        center.setBackground(new Color(112, 128, 150));
        center.setLayout(null);
        add(center);
        
        welcome = new JLabel("Welcome to Computer Diagnostic!");
        welcome.setBounds(125, 15, 500, 30);
        welcome.setFont(new Font("Raleway", Font.BOLD, 25));
        welcome.setForeground(Color.WHITE);
        center.add(welcome);
        
        info1 = new JLabel("<--- Use this button for info about CPU");
        info1.setBounds(10, 90, 450, 30);
        info1.setFont(new Font("System", Font.BOLD, 20));
        info1.setForeground(Color.WHITE);
        center.add(info1);
        
        info2 = new JLabel("<--- Use this button for info about GPU");
        info2.setBounds(10, 170, 450, 30);
        info2.setFont(new Font("System", Font.BOLD, 20));
        info2.setForeground(Color.WHITE);
        center.add(info2);
        
        info3 = new JLabel("<--- Use this button for info about Hard Drive");
        info3.setBounds(10, 250, 450, 30);
        info3.setFont(new Font("System", Font.BOLD, 20));
        info3.setForeground(Color.WHITE);
        center.add(info3);
        
        info4 = new JLabel("<--- Use this button for info about RAM");
        info4.setBounds(10, 330, 450, 30);
        info4.setFont(new Font("System", Font.BOLD, 20));
        info4.setForeground(Color.WHITE);
        center.add(info4);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/close.png"));
        Image i2 = i1.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel close = new JLabel(i3);
        close.setBounds(570, 350, 50, 50);
        center.add(close);
        
        close.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
    });
        
        
        setSize(800, 450);
        setLocation(350, 200);
        setTitle("Computer Diagnostic App");
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == infocpu){
            setVisible(false);
            new CPUDiagnostic().setVisible(true);
        } else if(ae.getSource() == infogpu){
            setVisible(false);
            new GPUDiagnostic().setVisible(true);
        } else if(ae.getSource() == infohard){
            setVisible(false);
            new HardDriveDiagnostic().setVisible(true);
        } else if(ae.getSource() == inforam){
            setVisible(false);
            new RamDiagnostic().setVisible(true);
        }
    }
    
    
    public static void main(String[] args) {
        new DiagnosticApp();
    }
}
