package com.kornevstas.diagnosticapp;

import java.util.List;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.DecimalFormat;
import javax.swing.*;
import oshi.SystemInfo;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HardwareAbstractionLayer;

public class HardDriveDiagnostic extends JFrame implements ActionListener{

    JButton allHardInf, cleanInfo;
    JLabel welcome, MH, SH, SiH, RH, WH, line, MH2, SH2, SiH2, RH2, WH2;
    JPanel top, leftSide, center;
    
    public HardDriveDiagnostic() {
        
        setLayout(null);
        
        leftSide = new JPanel();
        leftSide.setLayout(null);
        leftSide.setBounds(0, 0, 150, 450);
        leftSide.setBackground(new Color(73, 77, 139));
        add(leftSide);
        
        top = new JPanel();
        top.setLayout(null);
        top.setBounds(150, 0, 650, 50);
        top.setBackground(new Color(73, 77, 139));
        add(top);
        
        center = new JPanel();
        center.setLayout(null);
        center.setBounds(150, 50, 650, 400);
        center.setBackground(new Color(112, 128, 150));
        add(center);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/backarrow.png"));
        Image i2 = i1.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(570, 300, 50, 50);
        center.add(back);
        
        back.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                setVisible(false);
                new DiagnosticApp().setVisible(true);
            }
        });
        
        welcome = new JLabel("Hard Drive Diagnostic Menu");
        welcome.setBounds(150, 10, 400, 30);
        welcome.setFont(new Font("Raleway", Font.BOLD, 25));
        welcome.setForeground(Color.WHITE);
        top.add(welcome);
        
        allHardInf = new JButton("All HARD INFO");
        allHardInf.setBounds(10, 90, 125, 30);
        allHardInf.setFont(new Font("System", Font.BOLD, 13));
        allHardInf.setBackground(new Color(255, 222, 52));
        allHardInf.addActionListener(this);
        leftSide.add(allHardInf);
        
        MH = new JLabel("Model");
        MH.setBounds(10, 5, 650, 30);
        MH.setFont(new Font("Raleway", Font.BOLD, 18));
        MH.setForeground(Color.WHITE);
        center.add(MH);
        
        SH = new JLabel("Serial");
        SH.setBounds(10, 35, 650, 30);
        SH.setFont(new Font("Raleway", Font.BOLD, 18));
        SH.setForeground(Color.WHITE);
        center.add(SH);
        
        SiH = new JLabel("Size");
        SiH.setBounds(10, 65, 650, 30);
        SiH.setFont(new Font("Raleway", Font.BOLD, 18));
        SiH.setForeground(Color.WHITE);
        center.add(SiH);
        
        RH = new JLabel("Reads");
        RH.setBounds(10, 95, 650, 30);
        RH.setFont(new Font("Raleway", Font.BOLD, 18));
        RH.setForeground(Color.WHITE);
        center.add(RH);
        
        WH = new JLabel("Writes");
        WH.setBounds(10, 125, 650, 30);
        WH.setFont(new Font("Raleway", Font.BOLD, 18));
        WH.setForeground(Color.WHITE);
        center.add(WH);
        
        line = new JLabel("");
        line.setBounds(10, 150, 800, 30);
        line.setFont(new Font("Raleway", Font.BOLD, 18));
        line.setForeground(Color.WHITE);
        center.add(line);
        
        MH2 = new JLabel("");
        MH2.setBounds(10, 175, 650, 30);
        MH2.setFont(new Font("Raleway", Font.BOLD, 18));
        MH2.setForeground(Color.WHITE);
        center.add(MH2);
        
        SH2 = new JLabel("");
        SH2.setBounds(10, 205, 650, 30);
        SH2.setFont(new Font("Raleway", Font.BOLD, 18));
        SH2.setForeground(Color.WHITE);
        center.add(SH2);
        
        SiH2 = new JLabel("");
        SiH2.setBounds(10, 235, 650, 30);
        SiH2.setFont(new Font("Raleway", Font.BOLD, 18));
        SiH2.setForeground(Color.WHITE);
        center.add(SiH2);
        
        RH2 = new JLabel("");
        RH2.setBounds(10, 265, 650, 30);
        RH2.setFont(new Font("Raleway", Font.BOLD, 18));
        RH2.setForeground(Color.WHITE);
        center.add(RH2);
        
        WH2 = new JLabel("");
        WH2.setBounds(10, 295, 650, 30);
        WH2.setFont(new Font("Raleway", Font.BOLD, 18));
        WH2.setForeground(Color.WHITE);
        center.add(WH2);
        
        cleanInfo = new JButton("NEED CLEAN");
        cleanInfo.setBounds(10, 140, 125, 30);
        cleanInfo.setFont(new Font("System", Font.BOLD, 13));
        cleanInfo.setBackground(new Color(255, 222, 52));
        cleanInfo.addActionListener(this);
        leftSide.add(cleanInfo);
        
        setSize(800, 450);
        setLocation(350, 200);
        setTitle("Computer Diagnostic App");
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == allHardInf){
            
        SystemInfo systemInfo = new SystemInfo();
        HardwareAbstractionLayer hardware = systemInfo.getHardware();
        List<HWDiskStore> diskStores = hardware.getDiskStores();
        
        if(diskStores.size() == 1){
            MH.setText("Model: " + diskStores.getFirst().getModel());
            SH.setText("Serial: " + diskStores.getFirst().getSerial());
            SiH.setText("Size: " + String.format("%.2f", (double)diskStores.getFirst().getSize() / (1024 * 1024 * 1024)));
            RH.setText("Reads: " + diskStores.getFirst().getReads());
            WH.setText("Writes: " + diskStores.getFirst().getWrites());   
        } else if(diskStores.size() == 2){
            MH.setText("Model: " + diskStores.getFirst().getModel());
            SH.setText("Serial: " + diskStores.getFirst().getSerial());
            SiH.setText("Size: " + String.format("%.2f", (double)diskStores.getFirst().getSize() / (1024 * 1024 * 1024)));
            RH.setText("Reads: " + diskStores.getFirst().getReads());
            WH.setText("Writes: " + diskStores.getFirst().getWrites()); 
            
            line.setText("-------------------------------------------------------------------------------------------------");
            
            MH2.setText("Model: " + diskStores.get(1).getModel());
            SH2.setText("Serial: " + diskStores.get(1).getSerial());
            SiH2.setText("Size: " + String.format("%.2f", (double)diskStores.get(1).getSize() / (1024 * 1024 * 1024)));
            RH2.setText("Reads: " + diskStores.get(1).getReads());
            WH2.setText("Writes: " + diskStores.get(1).getWrites()); 
        } else if(diskStores.size() > 2){
            StringBuilder message = new StringBuilder();
        for (int i = 0; i < diskStores.size(); i++) {
        HWDiskStore disk = diskStores.get(i);
        message.append("Disk ").append(i + 1).append(":\n");
        message.append("Model: ").append(disk.getModel()).append("\n");
        message.append("Serial: ").append(disk.getSerial()).append("\n");
        message.append("Size: ").append(String.format("%.2f", (double)disk.getSize() / (1024 * 1024 * 1024))).append(" GB\n");
        message.append("Reads: ").append(disk.getReads()).append("\n");
        message.append("Writes: ").append(disk.getWrites()).append("\n\n");
    }  
            MH.setText("");
            SH.setText("");
            SiH.setText("");
            RH.setText("");
            WH.setText("");   
        JOptionPane.showMessageDialog(null, message.toString(), "Disk Information", JOptionPane.INFORMATION_MESSAGE);
        }
            
        } else if (ae.getSource() == cleanInfo) {
    StringBuilder message = new StringBuilder();
    DecimalFormat df = new DecimalFormat("#.##");
    File[] roots = File.listRoots();

    for (File root : roots) {
        long totalSpace = root.getTotalSpace();
        long freeSpace = root.getFreeSpace();

        double usedPercentage = ((double) (totalSpace - freeSpace) / totalSpace) * 100;

        String formattedPercentage = df.format(usedPercentage);

        message.append("Disk ").append(root.getAbsolutePath()).append(" usage: ").append(formattedPercentage).append("%\n");

        if (usedPercentage > 90) {
            message.append("Потрібна чистка на диску ").append(root.getAbsolutePath()).append("\n");
        }
    }

    JOptionPane.showMessageDialog(null, message.toString(), "Disk Space Usage", JOptionPane.INFORMATION_MESSAGE);
}


    }
    
    public static void main(String[] args) {
        new HardDriveDiagnostic();
    }
}
