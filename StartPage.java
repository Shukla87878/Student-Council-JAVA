

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class StartPage extends JFrame implements ActionListener {

    JTextField t1, t2;
    JComboBox<String> houseBox;
    JButton b1, b2;

    StartPage() {
        setBounds(0, 30, 1600, 800);
        setTitle("Student Council");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JPanel p1 = new JPanel();
        p1.setBounds(700, 0, 900, 800);
        p1.setBackground(Color.WHITE);
        p1.setLayout(null);
        add(p1);

        JPanel p2 = new JPanel();
        p2.setBounds(50, 75, 600, 600);
        p2.setLayout(null);
        add(p2);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/StudentCouncil1.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        JLabel l3 = new JLabel(new ImageIcon(i2));
        l3.setBounds(100, 50, 600, 600);
        p1.add(l3);

        JLabel l1 = new JLabel("Details");
        l1.setFont(new Font("Raleway", Font.BOLD, 50));
        l1.setBounds(200, 20, 200, 50);
        p2.add(l1);

        JLabel l2 = new JLabel("Name");
        l2.setFont(new Font("Raleway", Font.BOLD, 35));
        l2.setBounds(50, 50, 200, 200);
        p2.add(l2);

        t1 = new JTextField();
        t1.setFont(new Font("Raleway", Font.PLAIN, 25));
        t1.setBounds(50, 200, 400, 30);
        p2.add(t1);

        JLabel l4 = new JLabel("SCS No."); // 🔁 Changed from Roll No.
        l4.setFont(new Font("Raleway", Font.BOLD, 35));
        l4.setBounds(50, 200, 200, 200);
        p2.add(l4);

        t2 = new JTextField();
        t2.setFont(new Font("Raleway", Font.PLAIN, 25));
        t2.setBounds(50, 350, 400, 30);
        p2.add(t2);

        JLabel l5 = new JLabel("House");
        l5.setFont(new Font("Raleway", Font.BOLD, 35));
        l5.setBounds(50, 350, 200, 200);
        p2.add(l5);

        // 🔁 Updated houses
        String[] houses = { "Spartans", "Vikings", "Samurais", "Knights", "Dignitaries" };
        houseBox = new JComboBox<>(houses);
        houseBox.setFont(new Font("Raleway", Font.PLAIN, 25));
        houseBox.setBounds(50, 500, 400, 30);
        p2.add(houseBox);

        b1 = new JButton("Submit and Proceed");
        b1.setBounds(50, 550, 200, 50);
        b1.setBackground(new Color(54, 69, 79));
        b1.setForeground(Color.WHITE);
        b1.setBorder(null);
        b1.setFont(new Font("Raleway", Font.PLAIN, 15));
        b1.addActionListener(this);
        p2.add(b1);

        b2 = new JButton("President Sign in");
        b2.setBounds(300, 550, 200, 50);
        b2.setBackground(new Color(54, 69, 79));
        b2.setForeground(Color.WHITE);
        b2.setBorder(null);
        b2.setFont(new Font("Raleway", Font.PLAIN, 15));
        b2.addActionListener(this);
        p2.add(b2);
    }

    public static void main(String[] args) {
        new StartPage().setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            String name = t1.getText();
            String rollno = t2.getText();
            String house = (String) houseBox.getSelectedItem();

            if (name.equals("") || rollno.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter all details.");
                return;
            }

            try {
                Conn c = new Conn();
                Connection conn = c.getConnection();

                String checkQuery = "SELECT has_voted FROM Details WHERE rollno = ?";
                PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
                checkStmt.setString(1, rollno);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    boolean hasVoted = rs.getBoolean("has_voted");

                    if (hasVoted) {
                        JOptionPane.showMessageDialog(null, "You have already voted. Access denied.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Welcome back! You may proceed to vote.");
                        this.setVisible(false);
                        new Votepage(name, rollno).setVisible(true);
                    }

                } else {
                    // new user: insert and allow vote
                    String insertQuery = "INSERT INTO Details(name, rollno, house) VALUES (?, ?, ?)";
                    PreparedStatement insertStmt = conn.prepareStatement(insertQuery);
                    insertStmt.setString(1, name);
                    insertStmt.setString(2, rollno);
                    insertStmt.setString(3, house);
                    insertStmt.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Welcome! You may now vote.");
                    this.setVisible(false);
                    new Votepage(name, rollno).setVisible(true);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == b2) {
            this.setVisible(false);
            new PresidentSignup().setVisible(true);
        }
    }
    
}