
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GenericVotePage extends JFrame implements ActionListener {

    String post, studentName, studentRoll;
    JComboBox<String> c1;
    JButton b1;
    ArrayList<String> allowedPosts;

    // List of dignitary roll numbers
    private static final Set<String> DIGNITARIES = new HashSet<>(Arrays.asList(
        "BNG234918", "BNG222585", "BNG285909", "BNG859669", 
        "BNG211583", "BNG211369", "BNG470176", "BNG942202",
        "BNG298683", "BNG237799", "AGM001"
    ));

    public GenericVotePage(String post, String name, String roll, ArrayList<String> allowedPosts) {
        this.post = post;
        this.studentName = name;
        this.studentRoll = roll;
        this.allowedPosts = allowedPosts;

        setTitle("Vote for " + post);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        // Image on left
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Icons/StudentCouncil14.jpg"));
        Image i2 = i1.getImage().getScaledInstance(700, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(100, 100, 700, 400);
        add(l3);

        // Title and dropdown
        JLabel l1 = new JLabel("Candidates for " + post);
        l1.setFont(new Font("Raleway", Font.BOLD, 30));
        l1.setBounds(850, 150, 600, 40);
        add(l1);

        c1 = new JComboBox<>();
        c1.setFont(new Font("Raleway", Font.PLAIN, 25));
        c1.setBounds(850, 200, 400, 30);
        add(c1);

        try {
            Conn c = new Conn();
            Connection conn = c.getConnection();

            // For dignitaries, show all candidates regardless of house
            if (DIGNITARIES.contains(studentRoll)) {
                PreparedStatement pst = conn.prepareStatement(
                    "SELECT Name FROM candidatedetails WHERE Post = ?");
                pst.setString(1, post);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    c1.addItem(rs.getString("Name"));
                }
            } 
            // For regular students
            else if (post.contains("House")) {
                String house = null;
                PreparedStatement hs = conn.prepareStatement("SELECT house FROM Details WHERE rollno = ?");
                hs.setString(1, studentRoll);
                ResultSet hr = hs.executeQuery();
                if (hr.next()) {
                    house = hr.getString("house");
                }

                String filteredPost = house + " " + post.split(" ", 2)[1]; // e.g. Spartans House Captain
                PreparedStatement pst = conn.prepareStatement("SELECT Name FROM candidatedetails WHERE Post = ?");
                pst.setString(1, filteredPost);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    c1.addItem(rs.getString("Name"));
                }

                if (c1.getItemCount() == 0) {
                    JOptionPane.showMessageDialog(null, "No candidates for your house in " + post);
                    this.setVisible(false);
                    String next = nextPost(post);
                    if (next != null) {
                        new GenericVotePage(next, studentName, studentRoll, allowedPosts).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "You've completed voting. Thank you!");
                    }
                    return;
                }
            } 
            // For common posts
            else {
                PreparedStatement pst = conn.prepareStatement("SELECT Name FROM candidatedetails WHERE Post = ?");
                pst.setString(1, post);
                ResultSet rs = pst.executeQuery();
                while (rs.next()) {
                    c1.addItem(rs.getString("Name"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Vote button
        b1 = new JButton("Vote");
        b1.setBounds(850, 270, 200, 40);
        b1.setBackground(new Color(54, 69, 79));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("Raleway", Font.PLAIN, 18));
        b1.addActionListener(this);
        add(b1);

        setBounds(0, 30, 1600, 900);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1) {
            try {
                Conn c = new Conn();
                Connection conn = c.getConnection();

                String selectedCandidate = (String) c1.getSelectedItem();

                PreparedStatement updateVote = conn.prepareStatement(
                    "UPDATE candidatedetails SET Votes = Votes + 1 WHERE Name = ? AND Post = ?");
                updateVote.setString(1, selectedCandidate);
                updateVote.setString(2, post);
                updateVote.executeUpdate();

                JOptionPane.showMessageDialog(null, "Vote recorded for: " + selectedCandidate);
                this.setVisible(false);

                String next = nextPost(post);
                if (next != null) {
                    new GenericVotePage(next, studentName, studentRoll, allowedPosts).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "You have completed voting. Thank you!");
                    this.setVisible(false);
                    new StartPage().setVisible(true);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public String nextPost(String currentPost) {
        int index = allowedPosts.indexOf(currentPost);
        if (index != -1 && index < allowedPosts.size() - 1) {
            return allowedPosts.get(index + 1);
        }
        return null;
    }

    public static void main(String[] args) {
        ArrayList<String> demo = new ArrayList<>();
        demo.add("Head Boy");
        demo.add("Spartans House Captain");
        new GenericVotePage("Head Boy", "Test", "001", demo).setVisible(true);
    }
}