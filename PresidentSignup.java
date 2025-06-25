

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class PresidentSignup extends JFrame implements ActionListener{
     JTextField t1;
     JPasswordField t2;
     
    JButton b1,b2;
    
    PresidentSignup(){
        setBounds(0,30,1600,800);
        setTitle("President Sign in");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        JPanel p1=new JPanel();
        p1.setBounds(700,0,900,800);
        p1.setBackground(Color.WHITE);
        p1.setLayout(null);
        add(p1);
        
        JPanel p2=new JPanel();
        p2.setBounds(50,75,600,600);
        p2.setLayout(null);
        add(p2);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("Icons/StudentCouncil2.png"));
        Image i2=i1.getImage().getScaledInstance(600,600,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l3=new JLabel(i3);
        l3.setBounds(100,50,600,600);
        p1.add(l3);
        
        JLabel l1=new JLabel("Credentials");
        l1.setFont(new Font("Raleway",Font.BOLD,50));
        l1.setBounds(150,20,300,50);
        p2.add(l1);
        
        JLabel l2=new JLabel("Username");
        l2.setFont(new Font("Raleway",Font.BOLD,35));
        l2.setBounds(50,50,200,200);
        p2.add(l2);
        
        t1=new JTextField();
        t1.setFont(new Font("Raleway",Font.PLAIN,25));
        t1.setBounds(50,200,400,30);
        p2.add(t1);
        
         JLabel l4=new JLabel("Password");
        l4.setFont(new Font("Raleway",Font.BOLD,35));
        l4.setBounds(50,200,200,200);
        p2.add(l4);
        
        t2=new JPasswordField();
        t2.setFont(new Font("Raleway",Font.PLAIN,25));
        t2.setBounds(50,350,400,30);
        p2.add(t2);
        
        b1=new JButton("Submit and Proceed");
        b1.setBounds(50,450,200,50);
        b1.setBackground(new Color(54, 69, 79));
        b1.setForeground(Color.WHITE);
        b1.setBorder(null);
        b1.setFont(new Font("Raleway",Font.PLAIN,15));
        b1.addActionListener(this);
        p2.add(b1);
        
        b2=new JButton("Back");
        b2.setBounds(300,450,200,50);
        b2.setBackground(new Color(54, 69, 79));
        b2.setForeground(Color.WHITE);
        b2.setBorder(null);
        b2.setFont(new Font("Raleway",Font.PLAIN,15));
        b2.addActionListener(this);
        p2.add(b2);
                
        
        
    }

    public static void main(String[] args) {
        new PresidentSignup().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getSource()==b1){
            if("School".equals(t1.getText()) && "007".equals(t2.getText())){
               this.setVisible(false);
               new DashBoard().setVisible(true);
           }else{
               JOptionPane.showMessageDialog(null,"You have entered either wrong username or password . ");
           }
           
       }if(e.getSource()==b2){
           this.setVisible(false);
           new StartPage().setVisible(true);
       }
    }

}
