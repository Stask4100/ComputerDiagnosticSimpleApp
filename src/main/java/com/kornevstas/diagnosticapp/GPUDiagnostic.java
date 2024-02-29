package com.kornevstas.diagnosticapp;


import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Gpu;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import oshi.SystemInfo;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HardwareAbstractionLayer;

public class GPUDiagnostic extends JFrame implements ActionListener{
    
    
    JButton allGpuInf, gpuTemp;
    JLabel welcome, GN, GM, GP, GI, GN2, GM2, GP2, GI2, line, gpuTemperature;
    JPanel top, leftSide, center, temp;
    
    GPUDiagnostic(){
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
        
        welcome = new JLabel("GPU Diagnostic Menu");
        welcome.setBounds(150, 10, 400, 30);
        welcome.setFont(new Font("Raleway", Font.BOLD, 25));
        welcome.setForeground(Color.WHITE);
        top.add(welcome);
        
        allGpuInf = new JButton("All GPU Info");
        allGpuInf.setBounds(10, 90, 125, 30);
        allGpuInf.setFont(new Font("System", Font.BOLD, 13));
        allGpuInf.setBackground(new Color(255, 222, 52));
        allGpuInf.addActionListener(this);
        leftSide.add(allGpuInf);

        GN = new JLabel("Video Card Name");
        GN.setBounds(10, 20, 650, 30);
        GN.setFont(new Font("Raleway", Font.BOLD, 18));
        GN.setForeground(Color.WHITE);
        center.add(GN);
        
        GP = new JLabel("Video Card Producer");
        GP.setBounds(10, 60, 650, 30);
        GP.setFont(new Font("Raleway", Font.BOLD, 18));
        GP.setForeground(Color.WHITE);
        center.add(GP);
        
        GM = new JLabel("Video Card Memory");
        GM.setBounds(10, 100, 650, 30);
        GM.setFont(new Font("Raleway", Font.BOLD, 18));
        GM.setForeground(Color.WHITE);
        center.add(GM);
        
        GI = new JLabel("Video Card ID");
        GI.setBounds(10, 140, 650, 30);
        GI.setFont(new Font("Raleway", Font.BOLD, 18));
        GI.setForeground(Color.WHITE);
        center.add(GI);
        
        GN2 = new JLabel("");
        GN2.setBounds(10, 180, 650, 30);
        GN2.setFont(new Font("Raleway", Font.BOLD, 18));
        GN2.setForeground(Color.WHITE);
        center.add(GN2);
        
        GP2 = new JLabel("");
        GP2.setBounds(10, 220, 650, 30);
        GP2.setFont(new Font("Raleway", Font.BOLD, 18));
        GP2.setForeground(Color.WHITE);
        center.add(GP2);
        
        GM2 = new JLabel("");
        GM2.setBounds(10, 260, 650, 30);
        GM2.setFont(new Font("Raleway", Font.BOLD, 18));
        GM2.setForeground(Color.WHITE);
        center.add(GM2);
        
        GI2 = new JLabel("");
        GI2.setBounds(10, 300, 650, 30);
        GI2.setFont(new Font("Raleway", Font.BOLD, 18));
        GI2.setForeground(Color.WHITE);
        center.add(GI2);
        
        line = new JLabel("");
        line.setBounds(10, 160, 800, 30);
        line.setFont(new Font("Raleway", Font.BOLD, 18));
        line.setForeground(Color.WHITE);
        center.add(line);
        
        gpuTemp = new JButton("GPU TEMP");
        gpuTemp.setBounds(10, 140, 125, 30);
        gpuTemp.setFont(new Font("System", Font.BOLD, 13));
        gpuTemp.setBackground(new Color(255, 222, 52));
        gpuTemp.addActionListener(this);
        leftSide.add(gpuTemp);
        
        temp = new JPanel();
        temp.setLayout(null);
        temp.setBounds(50, 190, 50, 50);
        temp.setBackground(Color.WHITE);
        leftSide.add(temp);
        
        gpuTemperature = new JLabel("");
        gpuTemperature.setLayout(null);
        gpuTemperature.setBounds(50, 190, 50, 50);
        gpuTemperature.setFont(new Font("Raleway", Font.BOLD, 14));
        gpuTemperature.setForeground(Color.BLACK);
        leftSide.add(gpuTemperature);
        
        leftSide.setComponentZOrder(gpuTemperature, 0);
        
        
        
        setSize(800, 450);
        setLocation(350, 200);
        setTitle("Computer Diagnostic App");
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == allGpuInf){
         SystemInfo systemInfo = new SystemInfo();

        HardwareAbstractionLayer hardware = systemInfo.getHardware();

            java.util.List<GraphicsCard> graphicsCards = hardware.getGraphicsCards();
            
            if(graphicsCards.size() == 1){
            GN.setText("Video Card Name: " + graphicsCards.getFirst().getName());
            GP.setText("Video Card Producer: " + graphicsCards.getFirst().getVendor());
            double memoryInGB = (double) graphicsCards.getFirst().getVRam() / (1024 * 1024 * 1024);
            String formattedMemory = String.format("%.3f", memoryInGB);
            GM.setText("Video Card Memory: " + formattedMemory + " GB");
            GI.setText("Video Card ID: " + graphicsCards.getFirst().getDeviceId());
        } else if(graphicsCards.size() == 2) {
            GN.setText("Video Card Name: " + graphicsCards.getFirst().getName());
            GP.setText("Video Card Producer: " + graphicsCards.getFirst().getVendor());
            double memoryInGB = (double) graphicsCards.getFirst().getVRam() / (1024 * 1024 * 1024);
            String formattedMemory = String.format("%.3f", memoryInGB);
            GM.setText("Video Card Memory: " + formattedMemory + " GB");
            GI.setText("Video Card ID: " + graphicsCards.getFirst().getDeviceId());
            
            line.setText("-------------------------------------------------------------------------------------------------");
            
            GN2.setText("Video Card Name: " + graphicsCards.getLast().getName());
            GP2.setText("Video Card Producer: " + graphicsCards.getLast().getVendor());
            double memoryInGB2 = (double) graphicsCards.getLast().getVRam() / (1024 * 1024 * 1024);
            String formattedMemory2 = String.format("%.2f", memoryInGB2);
            GM2.setText("Video Card Memory: " + formattedMemory2 + " GB");
            GI2.setText("Video Card ID: " + graphicsCards.getLast().getDeviceId());
            }
        } else if(ae.getSource() == gpuTemp){
            Gpu gpu = JSensors.get.components().gpus.getLast();
            Temperature t = gpu.sensors.temperatures.getLast();
            gpuTemperature.setText(""+t.value + " °C");
        }
    }
    
    
    public static void main(String[] args){
        new GPUDiagnostic();
    }
}
