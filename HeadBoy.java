

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.sql.*;
import javax.swing.*;


public class HeadBoy extends JFrame implements ActionListener{
    JTextField t1,t2;
    JComboBox c1;
    JButton b1,b2;
    JLabel l7,l6;
    HeadBoy(){
         getContentPane().setBackground(Color.WHITE);
        setTitle("Add Candidate");
        setLayout(null);
        
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("Icons/StudentCouncil14.jpg"));
        Image i2=i1.getImage().getScaledInstance(700,400,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l3=new JLabel(i3);
        l3.setBounds(20,150,700,400);
       add(l3);
       
        
       JPanel p2=new JPanel();
        p2.setBounds(750,20,700,700);
        p2.setLayout(null);
        add(p2);
        
        JLabel l1=new JLabel("Candidate Details");
        l1.setFont(new Font("Raleway",Font.BOLD,50));
        l1.setBounds(150,20,500,50);
        p2.add(l1);
        
        JLabel l2=new JLabel("Name");
        l2.setFont(new Font("Raleway",Font.BOLD,35));
        l2.setBounds(50,50,200,200);
        p2.add(l2);
        
        l6=new JLabel();
        l6.setFont(new Font("Raleway",Font.PLAIN,25));
        l6.setBounds(50,500,400,30);
        p2.add(l6);
        
         JLabel l4=new JLabel("Roll No.");
        l4.setFont(new Font("Raleway",Font.BOLD,35));
        l4.setBounds(50,200,200,200);
        p2.add(l4);
        
        l7=new JLabel();
        l7.setFont(new Font("Raleway",Font.PLAIN,25));
        l7.setBounds(50,350,400,30);
        p2.add(l7);
        
          JLabel l5=new JLabel("Post Applied");
        l5.setFont(new Font("Raleway",Font.BOLD,35));
        l5.setBounds(50,350,300,200);
        p2.add(l5);
        
           
                
        c1=new JComboBox();
        c1.setFont(new Font("Raleway",Font.PLAIN,25));
        c1.setBounds(50,200,400,30);
        p2.add(c1);
        
        try{
            Conn c=new Conn();
            String query="select * from candidatedetails WHERE Post = 'Head Boy'";
            ResultSet rs=c.s.executeQuery(query);
           while(rs.next()){
               c1.addItem(rs.getString("Name"));
           }
            
        }catch(Exception ae){
            ae.printStackTrace();
        }
        
        b1=new JButton("Vote");
        b1.setBounds(50,600,200,50);
        b1.setBackground(new Color(54, 69, 79));
        b1.setForeground(Color.WHITE);
        b1.setBorder(null);
        b1.setFont(new Font("Raleway",Font.PLAIN,15));
        b1.addActionListener(this);
        p2.add(b1);
        
        b2=new JButton("Back");
        b2.setBounds(300,600,200,50);
        b2.setBackground(new Color(54, 69, 79));
        b2.setForeground(Color.WHITE);
        b2.setBorder(null);
        b2.setFont(new Font("Raleway",Font.PLAIN,15));
        b2.addActionListener(this);
        p2.add(b2);
        
        try{
            Conn c=new Conn();
            String query="select * from candidatedetails WHERE Name = '"+c1.getSelectedItem()+"'";
            ResultSet rs=c.s.executeQuery(query);
           while(rs.next()){
               l7.setText(rs.getString("Rollno"));
               l6.setText(rs.getString("Post"));
           }
            
        }catch(Exception ae){
            ae.printStackTrace();
        }
       
        setBounds(0,30,1600,800);
    }
    
    public static void main(String[]args){
        new HeadBoy().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
          


try 
     {
Conn c=new Conn();
               String a=l7.getText();
               String b=l6.getText();
                PreparedStatement updatePstmt = c.c.prepareStatement(
                "UPDATE candidatedetails SET Votes = Votes + 1 WHERE Rollno = '"+a+"' AND Post = '"+b+"'");
                
        try{
           
            int rowsAffected = updatePstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Your vote has been recorded!");
            } else {
                JOptionPane.showMessageDialog(null, "Error recording your vote.");
            }
        }catch(Exception x){
            x.printStackTrace();
        }
   
} catch (Exception fe) {
    JOptionPane.showMessageDialog(null, "Error: " + fe.getMessage());
    fe.printStackTrace();
}
    }
    }
}
