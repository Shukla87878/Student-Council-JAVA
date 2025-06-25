

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddCandidate extends JFrame implements ActionListener{
    JTextField t1,t2;
    JComboBox c1;
    JButton b1,b2;
    AddCandidate(){
        getContentPane().setBackground(Color.WHITE);
        setTitle("Add Candidate");
        setLayout(null);
        
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("Icons/StudentCouncil4.jpeg"));
        Image i2=i1.getImage().getScaledInstance(600,600,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l3=new JLabel(i3);
        l3.setBounds(100,50,600,600);
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
        
        t1=new JTextField();
        t1.setFont(new Font("Raleway",Font.PLAIN,25));
        t1.setBounds(50,200,400,30);
        p2.add(t1);
        
         JLabel l4=new JLabel("SCS No.");
        l4.setFont(new Font("Raleway",Font.BOLD,35));
        l4.setBounds(50,200,200,200);
        p2.add(l4);
        
        t2=new JTextField();
        t2.setFont(new Font("Raleway",Font.PLAIN,25));
        t2.setBounds(50,350,400,30);
        p2.add(t2);
        
          JLabel l5=new JLabel("Post Applied");
        l5.setFont(new Font("Raleway",Font.BOLD,35));
        l5.setBounds(50,350,300,200);
        p2.add(l5);
        
        
        String type[]={"Head Boy","Head Girl","Deputy Head Boy","Deputy Head Girl","Sports Captain","Science Club Secretary","Cultural Secretary","Heritage Secretary","Literacy Secretary","Spartans House Captain","Vikings House Captain","Knights House Captain","Samurais House Captain","Spartans House Vice Captain","Vikings House Vice Captain","Knights House Vice Captain","Samurais House Vice Captain"};
                
        c1=new JComboBox(type);
        c1.setFont(new Font("Raleway",Font.PLAIN,25));
        c1.setBounds(50,500,400,30);
        p2.add(c1);
        
        b1=new JButton("Submit and Proceed");
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
       
        setBounds(0,30,1600,800);
    }
    
    public static void main(String []args){
        new AddCandidate().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b1){
            String a=t1.getText();
            String b=t2.getText();
            String d=(String)c1.getSelectedItem();
            
            String query="insert into candidatedetails values('"+a+"','"+b+"','"+d+"','"+0+"')";
            
            
            try{
               Conn c=new Conn();
               c.s.executeUpdate(query);
               
               JOptionPane.showMessageDialog(null,"Candidate Registered Successfully");
               
               
            }catch(Exception ae){
                ae.printStackTrace();
            }
        }else{
            this.setVisible(false);
            new DashBoard().setVisible(true);
        }
    }
    
}
