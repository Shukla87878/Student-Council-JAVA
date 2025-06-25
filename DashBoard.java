

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DashBoard extends JFrame implements ActionListener{
    JButton b1,b2,b3,b7,b4,b5;
    
    DashBoard(){
        setBounds(20,20,1500,800);
        setTitle("DashBoard");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("Icons/StudentCouncil3.jpg"));
        Image i2=i1.getImage().getScaledInstance(1200,800,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l1=new JLabel(i3);
        l1.setBounds(300,0,1200,800);
        add(l1);
        
        JPanel p1=new JPanel();
        p1.setBounds(0,0,300,800);
        p1.setBackground((new Color(128,128,128)));
        p1.setLayout(null);
        add(p1);
        
         b1=new JButton("Add Candidate");
        b1.setBounds(20,50,250,50);
        b1.setBackground(new Color(34,34,34));
        b1.setBorder(null);
        b1.setFont(new Font("Tahoma",Font.PLAIN,20));
        b1.addActionListener(this);
        b1.setForeground(new Color(255,165,0));
        p1.add(b1);
        
        b2=new JButton("Remove Candidate");
        b2.setBounds(20,150,250,50);
        b2.setBackground(new Color(34,34,34));
        b2.setBorder(null);
        b2.setFont(new Font("Tahoma",Font.PLAIN,20));
        b2.addActionListener(this);
        b2.setForeground(new Color(255,165,0));
        p1.add(b2);
        
        b3=new JButton("Voters List");
        b3.setBounds(20,250,250,50);
        b3.setBackground(new Color(34,34,34));
        b3.setBorder(null);
        b3.setFont(new Font("Tahoma",Font.PLAIN,20));
        b3.setForeground(new Color(255,165,0));
        b3.addActionListener(this);
        p1.add(b3);
        
        b4=new JButton("Candidate List");
        b4.setBounds(20,350,250,50);
        b4.setBackground(new Color(34,34,34));
        b4.setBorder(null);
        b4.setFont(new Font("Tahoma",Font.PLAIN,20));
        b4.setForeground(new Color(255,165,0));
        b4.addActionListener(this);
        p1.add(b4);
        
        b5=new JButton("View Results");
        b5.setBounds(20,450,250,50);
        b5.setBackground(new Color(34,34,34));
        b5.setBorder(null);
        b5.setFont(new Font("Tahoma",Font.PLAIN,20));
        b5.setForeground(new Color(255,165,0));
        b5.addActionListener(this);
        p1.add(b5);
        
        JButton b6=new JButton("About");
        b6.setBounds(20,550,250,50);
        b6.setBackground(new Color(34,34,34));
        b6.setBorder(null);
        b6.setFont(new Font("Tahoma",Font.PLAIN,20));
        b6.setForeground(new Color(255,165,0));
        p1.add(b6);
        
        b7=new JButton("Quit");
        b7.setBounds(20,650,250,50);
        b7.setBackground(new Color(34,34,34));
        b7.setBorder(null);
        b7.setFont(new Font("Tahoma",Font.PLAIN,20));
        b7.setForeground(new Color(255,165,0));
        b7.addActionListener(this);
        p1.add(b7);
        
        
        
        
        
    }
    
    public static void main(String []args){
        new DashBoard().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b3){
            this.setVisible(false);
            new VotersList().setVisible(true);
        }if(e.getSource()==b7){
            this.setVisible(false);
        }if(e.getSource()==b1){
            this.setVisible(false);
            new AddCandidate().setVisible(true);
        }if(e.getSource()==b4){
            this.setVisible(false);
            new CandidateList().setVisible(true);
        }if(e.getSource()==b2){
            this.setVisible(false);
            new DeleteCandidate().setVisible(true);
        }if(e.getSource()==b5){
            this.setVisible(false);
            new ResultDashboard().setVisible(true);
        }
    }
}
