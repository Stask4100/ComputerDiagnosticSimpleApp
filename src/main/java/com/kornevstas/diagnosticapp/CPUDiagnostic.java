package com.kornevstas.diagnosticapp;

import com.profesorfalken.jsensors.JSensors;
import com.profesorfalken.jsensors.model.components.Cpu;
import com.profesorfalken.jsensors.model.sensors.Temperature;
import java.lang.management.ManagementFactory;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;


public class CPUDiagnostic extends JFrame implements ActionListener{
    
    
    JButton allCpuInf, mostCpuUsageThreads, mostCpuUsageProcess, cpuTemp;
    JLabel welcome, LCores, PN, PA, PL, PR, cpuTemperature;
    JPanel top, leftSide, center, temp;
    
    CPUDiagnostic(){
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
        
        welcome = new JLabel("CPU Diagnostic Menu");
        welcome.setBounds(150, 10, 400, 30);
        welcome.setFont(new Font("Raleway", Font.BOLD, 25));
        welcome.setForeground(Color.WHITE);
        top.add(welcome);
        
        allCpuInf = new JButton("All CPU Info");
        allCpuInf.setBounds(10, 90, 125, 30);
        allCpuInf.setFont(new Font("System", Font.BOLD, 13));
        allCpuInf.setBackground(new Color(255, 222, 52));
        allCpuInf.addActionListener(this);
        leftSide.add(allCpuInf);
        
        
        
        PN = new JLabel("Processor Name");
        PN.setBounds(10, 20, 650, 30);
        PN.setFont(new Font("Raleway", Font.BOLD, 18));
        PN.setForeground(Color.WHITE);
        center.add(PN);
        
        PA = new JLabel("Processor Architecture");
        PA.setBounds(10, 60, 600, 30);
        PA.setFont(new Font("Raleway", Font.BOLD, 18));
        PA.setForeground(Color.WHITE);
        center.add(PA);
        
        PL = new JLabel("Processor Cores");
        PL.setBounds(10, 100, 600, 30);
        PL.setFont(new Font("Raleway", Font.BOLD, 18));
        PL.setForeground(Color.WHITE);
        center.add(PL);
        
        LCores = new JLabel("Processor Logic Cores");
        LCores.setBounds(10, 140, 600, 30);
        LCores.setFont(new Font("Raleway", Font.BOLD, 18));
        LCores.setForeground(Color.WHITE);
        center.add(LCores);
        
        PR = new JLabel("Processor Revision");
        PR.setBounds(10, 180, 600, 30);
        PR.setFont(new Font("Raleway", Font.BOLD, 18));
        PR.setForeground(Color.WHITE);
        center.add(PR);
        
        mostCpuUsageThreads = new JButton("CPU TREAD");
        mostCpuUsageThreads.setBounds(10, 140, 125, 30);
        mostCpuUsageThreads.setFont(new Font("System", Font.BOLD, 13));
        mostCpuUsageThreads.setBackground(new Color(255, 222, 52));
        mostCpuUsageThreads.addActionListener(this);
        leftSide.add(mostCpuUsageThreads);
        
        mostCpuUsageProcess = new JButton("CPU USE INF");
        mostCpuUsageProcess.setBounds(10, 190, 125, 30);
        mostCpuUsageProcess.setFont(new Font("System", Font.BOLD, 13));
        mostCpuUsageProcess.setBackground(new Color(255, 222, 52));
        mostCpuUsageProcess.addActionListener(this);
        leftSide.add(mostCpuUsageProcess);

        cpuTemp = new JButton("CPU TEMP");
        cpuTemp.setBounds(10, 240, 125, 30);
        cpuTemp.setFont(new Font("System", Font.BOLD, 13));
        cpuTemp.setBackground(new Color(255, 222, 52));
        cpuTemp.addActionListener(this);
        leftSide.add(cpuTemp);
        
        temp = new JPanel();
        temp.setLayout(null);
        temp.setBounds(50, 280, 50, 50);
        temp.setBackground(Color.WHITE);
        leftSide.add(temp);
        
        cpuTemperature = new JLabel("");
        cpuTemperature.setLayout(null);
        cpuTemperature.setBounds(50, 280, 50, 50);
        cpuTemperature.setFont(new Font("Raleway", Font.BOLD, 14));
        cpuTemperature.setForeground(Color.BLACK);
        leftSide.add(cpuTemperature);
        
        leftSide.setComponentZOrder(cpuTemperature, 0);
        
        setSize(800, 450);
        setLocation(350, 200);
        setTitle("Computer Diagnostic App");
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == allCpuInf){
            LCores.setText("Processor Logic Cores: " + Runtime.getRuntime().availableProcessors());
            
            SystemInfo si = new SystemInfo();
            HardwareAbstractionLayer hal = si.getHardware();
            CentralProcessor cpu = hal.getProcessor();
            String name = cpu.getProcessorIdentifier().getName();
            
            String processorArchitecture = System.getenv("PROCESSOR_ARCHITECTURE");
            String processorLevel = System.getenv("PROCESSOR_LEVEL");
            String processorRevision = System.getenv("PROCESSOR_REVISION");

            PN.setText("Processor Name: " + name);
            PA.setText("Processor Architecture: " + processorArchitecture);
            PL.setText("Processor Cores: " + processorLevel);
            PR.setText("Processor Revision: " + processorRevision);
            
            

    } else if(ae.getSource() == mostCpuUsageThreads){
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

if (!threadMXBean.isThreadCpuTimeSupported()) {
    System.out.println("Підтримка відстеження часу процесора в потоках не підтримується.");
    return;
}

long[] threadIds = threadMXBean.getAllThreadIds();

ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(threadIds);

Comparator<ThreadInfo> cpuUsageComparator = new Comparator<ThreadInfo>() {
    @Override
    public int compare(ThreadInfo t1, ThreadInfo t2) {
        long cpuTime1 = threadMXBean.getThreadCpuTime(t1.getThreadId());
        long cpuTime2 = threadMXBean.getThreadCpuTime(t2.getThreadId());
        return Long.compare(cpuTime1, cpuTime2);
    }
};

Arrays.sort(threadInfos, cpuUsageComparator.reversed());

int numOfThreadsToDisplay = 5;
for (int i = 0; i < Math.min(numOfThreadsToDisplay, threadInfos.length); i++) {
    ThreadInfo threadInfo = threadInfos[i];
    System.out.println("Потік з найбільшим навантаженням CPU:");
    System.out.println("Номер: " + threadInfo.getThreadId());
    System.out.println("Назва: " + threadInfo.getThreadName());
    System.out.println();
}

StringBuilder message = new StringBuilder();
if (threadInfos.length > 0) {
    message.append("Найбільше навантаження CPU мають наступні потоки:\n\n");
    for (int i = 0; i < Math.min(numOfThreadsToDisplay, threadInfos.length); i++) {
        ThreadInfo threadInfo = threadInfos[i];
        message.append("Номер: ").append(threadInfo.getThreadId()).append("\n");
        message.append("Назва: ").append(threadInfo.getThreadName()).append("\n\n");
    }
} else {
    message.append("Не вдалося знайти потоки з найбільшим навантаженням CPU.");
}

JOptionPane.showMessageDialog(null, message.toString(), "Інформація про навантаження CPU", JOptionPane.INFORMATION_MESSAGE);

    } else if(ae.getSource() == mostCpuUsageProcess){
        SystemInfo systemInfo = new SystemInfo();
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();

        List<OSProcess> processes = operatingSystem.getProcesses(10, OperatingSystem.ProcessSort.CPU);

        StringBuilder message = new StringBuilder();
        message.append("Процеси, якиі використовують найбільше CPU:\n");
        message.append("----------------------------------------------\n");
        for (OSProcess process : processes) {
            message.append(String.format("ID: %5d, Name: %-20s, CPU: %.1f%%, Memory: %s%n",
                    process.getProcessID(), process.getName(),
                    100d * (process.getKernelTime() + process.getUserTime()) / process.getUpTime(),
                    FormatUtil.formatBytes(process.getResidentSetSize())));
            message.append("\n");
        }
        JOptionPane.showMessageDialog(null, message.toString(), "Інформація про процеси", JOptionPane.INFORMATION_MESSAGE);
    }else if(ae.getSource() == cpuTemp){
            Cpu cpu = JSensors.get.components().cpus.getLast();
            Temperature t = cpu.sensors.temperatures.getLast();
            cpuTemperature.setText(""+t.value + " °C");
        }
    }
    

    public static void main(String[] args){
        new CPUDiagnostic();
    }
}
