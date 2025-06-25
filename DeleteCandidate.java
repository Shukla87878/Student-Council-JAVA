
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DeleteCandidate extends JFrame implements ActionListener{
    JTextField t1,t2,t3;
    String a,b,d;
    JButton b1,b2,searchButton;
    DeleteCandidate(){
        getContentPane().setBackground(Color.WHITE);
        setTitle("Remove Candidate");
        setLayout(null);
        
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("Icons/StudentCouncil5.jpeg"));
        Image i2=i1.getImage().getScaledInstance(600,600,Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel l3=new JLabel(i3);
        l3.setBounds(100,50,600,600);
       add(l3);
       
       
       
        
       JPanel p2=new JPanel();
        p2.setBounds(750,20,700,700);
        p2.setLayout(null);
        add(p2);
        
        searchButton=new JButton("Search");
        searchButton.setBounds(150,230,200,50);
        searchButton.setBackground(new Color(54, 69, 79));
        searchButton.setForeground(Color.WHITE);
        searchButton.setBorder(null);
        searchButton.setFont(new Font("Raleway",Font.PLAIN,15));
        searchButton.addActionListener(this);
        p2.add(searchButton);
        
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
        
         JLabel l4=new JLabel("Roll No.");
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
        
        
        
                
        t3=new JTextField();
        t3.setFont(new Font("Raleway",Font.PLAIN,25));
        t3.setBounds(50,500,400,30);
        p2.add(t3);
        
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
        new DeleteCandidate().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b2) {
        this.setVisible(false);
        new DashBoard().setVisible(true);
    } else if (e.getSource() == b1) {
        a = t1.getText();
        b = t2.getText();
        d = t3.getText();

        
        String query = "DELETE FROM candidatedetails WHERE Name = '"+a+"' AND Rollno = '"+b+"' AND Post = '"+d+"'";

        try {
            Conn c = new Conn();
            try (PreparedStatement preparedStatement = c.c.prepareStatement(query)) {

               
                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Candidate Removed Successfully");
                    this.setVisible(false);
                    new DeleteCandidate().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Candidate not found!");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    } else if (e.getSource() == searchButton) {
        
        String Name = t1.getText(); 
        String query = "SELECT * FROM candidatedetails WHERE name = ?";
        ResultSet rs = null;
        PreparedStatement preparedStatement = null;

        try {
            Conn c = new Conn(); 
            preparedStatement = c.c.prepareStatement(query);
            preparedStatement.setString(1, Name);
            rs = preparedStatement.executeQuery();

            if (rs.next()) {
               
                t1.setText(rs.getString("Name"));
                t2.setText(rs.getString("Rollno"));
                t3.setText(rs.getString("Post"));
            } else {
                JOptionPane.showMessageDialog(null, "Candidate not found!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
    }

    

