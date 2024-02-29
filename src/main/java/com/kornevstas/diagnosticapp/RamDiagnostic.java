package com.kornevstas.diagnosticapp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import oshi.SystemInfo;
import java.util.List;
import oshi.hardware.GlobalMemory;
import oshi.hardware.PhysicalMemory;

public class RamDiagnostic extends JFrame implements ActionListener {

    JButton allRamInf, ramUsage;
    JLabel welcome, NR, TR, SR, NR2, TR2, SR2, NR3, TR3, SR3, NR4, TR4, SR4, line;
    JPanel top, leftSide, center;
    
    public RamDiagnostic() {
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
        
        welcome = new JLabel("Ram Diagnostic Menu");
        welcome.setBounds(150, 10, 400, 30);
        welcome.setFont(new Font("Raleway", Font.BOLD, 25));
        welcome.setForeground(Color.WHITE);
        top.add(welcome);
        
        allRamInf = new JButton("All RAM INFO");
        allRamInf.setBounds(10, 90, 125, 30);
        allRamInf.setFont(new Font("System", Font.BOLD, 13));
        allRamInf.setBackground(new Color(255, 222, 52));
        allRamInf.addActionListener(this);
        leftSide.add(allRamInf);
        
        NR = new JLabel("RAM Num");
        NR.setBounds(10, 5, 650, 30);
        NR.setFont(new Font("Raleway", Font.BOLD, 18));
        NR.setForeground(Color.WHITE);
        center.add(NR);
        
        TR = new JLabel("Type");
        TR.setBounds(10, 35, 650, 30);
        TR.setFont(new Font("Raleway", Font.BOLD, 18));
        TR.setForeground(Color.WHITE);
        center.add(TR);
        
        SR = new JLabel("Size");
        SR.setBounds(10, 65, 650, 30);
        SR.setFont(new Font("Raleway", Font.BOLD, 18));
        SR.setForeground(Color.WHITE);
        center.add(SR);

        NR2 = new JLabel("");
        NR2.setBounds(250, 5, 650, 30);
        NR2.setFont(new Font("Raleway", Font.BOLD, 18));
        NR2.setForeground(Color.WHITE);
        center.add(NR2);
        
        TR2 = new JLabel("");
        TR2.setBounds(250, 35, 650, 30);
        TR2.setFont(new Font("Raleway", Font.BOLD, 18));
        TR2.setForeground(Color.WHITE);
        center.add(TR2);
        
        SR2 = new JLabel("");
        SR2.setBounds(250, 65, 650, 30);
        SR2.setFont(new Font("Raleway", Font.BOLD, 18));
        SR2.setForeground(Color.WHITE);
        center.add(SR2);
        
        line = new JLabel("");
        line.setBounds(10, 95, 800, 30);
        line.setFont(new Font("Raleway", Font.BOLD, 18));
        line.setForeground(Color.WHITE);
        center.add(line);
        
        NR3 = new JLabel("");
        NR3.setBounds(10, 125, 650, 30);
        NR3.setFont(new Font("Raleway", Font.BOLD, 18));
        NR3.setForeground(Color.WHITE);
        center.add(NR3);
        
        TR3 = new JLabel("");
        TR3.setBounds(10, 155, 650, 30);
        TR3.setFont(new Font("Raleway", Font.BOLD, 18));
        TR3.setForeground(Color.WHITE);
        center.add(TR3);
        
        SR3 = new JLabel("");
        SR3.setBounds(10, 185, 650, 30);
        SR3.setFont(new Font("Raleway", Font.BOLD, 18));
        SR3.setForeground(Color.WHITE);
        center.add(SR3);
        
        NR4 = new JLabel("");
        NR4.setBounds(250, 125, 650, 30);
        NR4.setFont(new Font("Raleway", Font.BOLD, 18));
        NR4.setForeground(Color.WHITE);
        center.add(NR4);
        
        TR4 = new JLabel("");
        TR4.setBounds(250, 155, 650, 30);
        TR4.setFont(new Font("Raleway", Font.BOLD, 18));
        TR4.setForeground(Color.WHITE);
        center.add(TR4);
        
        SR4 = new JLabel("");
        SR4.setBounds(250, 185, 650, 30);
        SR4.setFont(new Font("Raleway", Font.BOLD, 18));
        SR4.setForeground(Color.WHITE);
        center.add(SR4);
        
        ramUsage = new JButton("RAM USAGE");
        ramUsage.setBounds(10, 140, 125, 30);
        ramUsage.setFont(new Font("System", Font.BOLD, 13));
        ramUsage.setBackground(new Color(255, 222, 52));
        ramUsage.addActionListener(this);
        leftSide.add(ramUsage);
        
        
        setSize(800, 450);
        setLocation(350, 200);
        setTitle("Computer Diagnostic App");
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == allRamInf){
        
        SystemInfo systemInfo = new SystemInfo();
        
        List<PhysicalMemory> physicalMemoryArray = systemInfo.getHardware().getMemory().getPhysicalMemory();

        if(physicalMemoryArray.size() == 1){
            NR.setText("RAM Num: " + 1);
            TR.setText("Type: " + physicalMemoryArray.get(0).getMemoryType());
            SR.setText("Size: " + (physicalMemoryArray.get(0).getCapacity() / (1024 * 1024 * 1024)) + " GB");
        } else if(physicalMemoryArray.size() == 2){
            NR.setText("RAM Num: " + 1);
            TR.setText("Type: " + physicalMemoryArray.get(0).getMemoryType());
            SR.setText("Size: " + (physicalMemoryArray.get(0).getCapacity() / (1024 * 1024 * 1024)) + " GB");
            
            NR2.setText("RAM Num: " + 2);
            TR2.setText("Type: " + physicalMemoryArray.get(1).getMemoryType());
            SR2.setText("Size: " + (physicalMemoryArray.get(1).getCapacity() / (1024 * 1024 * 1024)) + " GB");
        } else if(physicalMemoryArray.size() == 3){
            line.setText("-------------------------------------------------------------------------------------------");
            
            NR.setText("RAM Num: " + 1);
            TR.setText("Type: " + physicalMemoryArray.get(0).getMemoryType());
            SR.setText("Size: " + (physicalMemoryArray.get(0).getCapacity() / (1024 * 1024 * 1024)) + " GB");
            
            NR2.setText("RAM Num: " + 2);
            TR2.setText("Type: " + physicalMemoryArray.get(1).getMemoryType());
            SR2.setText("Size: " + (physicalMemoryArray.get(1).getCapacity() / (1024 * 1024 * 1024)) + " GB");
            
            NR3.setText("RAM Num: " + 3);
            TR3.setText("Type: " + physicalMemoryArray.get(2).getMemoryType());
            SR3.setText("Size: " + (physicalMemoryArray.get(2).getCapacity() / (1024 * 1024 * 1024)) + " GB");
        } else if(physicalMemoryArray.size() == 4){
            line.setText("-------------------------------------------------------------------------------------------");
            
            NR.setText("RAM Num: " + 1);
            TR.setText("Type: " + physicalMemoryArray.get(0).getMemoryType());
            SR.setText("Size: " + (physicalMemoryArray.get(0).getCapacity()/ (1024 * 1024 * 1024)) + " GB");
            
            NR2.setText("RAM Num: " + 2);
            TR2.setText("Type: " + physicalMemoryArray.get(1).getMemoryType());
            SR2.setText("Size: " + (physicalMemoryArray.get(1).getCapacity() / (1024 * 1024 * 1024)) + " GB");
            
            NR3.setText("RAM Num: " + 3);
            TR3.setText("Type: " + physicalMemoryArray.get(2).getMemoryType());
            SR3.setText("Size: " + (physicalMemoryArray.get(2).getCapacity() / (1024 * 1024 * 1024)) + " GB");
            
            NR4.setText("RAM Num: " + 4);
            TR4.setText("Type: " + physicalMemoryArray.get(3).getMemoryType());
            SR4.setText("Size: " + (physicalMemoryArray.get(3).getCapacity() / (1024 * 1024 * 1024)) + " GB");
        }
        } else if(ae.getSource() == ramUsage){
        SystemInfo systemInfo = new SystemInfo();

        GlobalMemory memory = systemInfo.getHardware().getMemory();

        long totalMemoryGB = memory.getTotal() / (1024 * 1024 * 1024);
        long availableMemoryGB = memory.getAvailable() / (1024 * 1024 * 1024);
        long usedMemoryGB = (memory.getTotal() - memory.getAvailable()) / (1024 * 1024 * 1024);

        String message = "Загальний обсяг пам'яті: " + totalMemoryGB  + " ГБ\n" +
                         "Вільний обсяг пам'яті: " + availableMemoryGB + " ГБ\n" +
                         "Використана пам'ять: " + usedMemoryGB + " ГБ\n" +
                         String.format("Вільна пам'ять в процентах: %.2f%%", ((double) availableMemoryGB / totalMemoryGB) * 100);

        JOptionPane.showMessageDialog(null, message, "Інформація про оперативну пам'ять", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        new RamDiagnostic();
    }
}
