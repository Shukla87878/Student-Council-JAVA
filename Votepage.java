// package Student.Council;

// import java.awt.*;
// import java.awt.event.*;
// import javax.swing.*;
// import java.sql.*;
// import java.util.*;

// public class Votepage extends JFrame {

//     private final String studentName;
//     private final String studentRoll;

//     // All 17 voting positions
//     private static final String[] ALL_POSTS = {
//         "Head Boy", "Head Girl", "Deputy Head Boy", "Deputy Head Girl",
//         "Sports Captain", "Science Club Secretary", "Cultural Secretary",
//         "Heritage Secretary", "Literacy Secretary",
//         "Spartans House Captain", "Vikings House Captain", "Knights House Captain", "Samurais House Captain",
//         "Spartans House Vice Captain", "Vikings House Vice Captain", "Knights House Vice Captain", "Samurais House Vice Captain"
//     };

//     // Official list of dignitaries who can vote for all positions
//     private static final Set<String> DIGNITARIES = new HashSet<>(Arrays.asList(
//         "BNG234918", "BNG222585", "BNG285909", "BNG859669",
//         "BNG211583", "BNG211369", "BNG470176", "BNG942202",
//         "BNG298683", "BNG237799", "AGM001"
//     ));

//     public Votepage(String name, String roll) {
//         this.studentName = name;
//         this.studentRoll = roll;
//         initializeUI();
//     }

//     private void initializeUI() {
//         setBounds(0, 30, 1600, 800);
//         setTitle("Vote for Student Council");
//         getContentPane().setBackground(Color.WHITE);
//         setLayout(null);

//         // Welcome message
//         JLabel title = new JLabel("Welcome, " + studentName, JLabel.CENTER);
//         title.setFont(new Font("Raleway", Font.BOLD, 40));
//         title.setBounds(400, 100, 800, 50);
//         add(title);

//         // Start Voting button
//         JButton startVoting = new JButton("START VOTING");
//         startVoting.setBounds(550, 300, 400, 60);
//         startVoting.setFont(new Font("Raleway", Font.BOLD, 24));
//         startVoting.setBackground(new Color(30, 144, 255));
//         startVoting.setForeground(Color.WHITE);
//         startVoting.addActionListener(e -> handleVotingStart());
//         add(startVoting);

//         // Back button
//         JButton back = new JButton("← Back");
//         back.setBounds(50, 700, 120, 40);
//         back.setFont(new Font("Raleway", Font.PLAIN, 18));
//         back.addActionListener(e -> {
//             new StartPage().setVisible(true);
//             dispose();
//         });
//         add(back);
//     }

//     private void handleVotingStart() {
//         ArrayList<String> allowedPosts = checkVotingEligibility();
        
//         if (allowedPosts.isEmpty()) {
//             JOptionPane.showMessageDialog(this, 
//                 "No voting positions available.\nContact administrator.", 
//                 "Voting Error", 
//                 JOptionPane.ERROR_MESSAGE);
//         } else {
//             new GenericVotePage(allowedPosts.get(0), studentName, studentRoll, allowedPosts).setVisible(true);
//             dispose();
//         }
//     }

//     private ArrayList<String> checkVotingEligibility() {
//         ArrayList<String> allowedPosts = new ArrayList<>();
        
//         try (Connection conn = new Conn().getConnection()) {
//             // First check if user is a dignitary
//             if (isDignitary(conn)) {
//                 Collections.addAll(allowedPosts, ALL_POSTS);
//                 return allowedPosts;
//             }
            
//             // Regular user - apply house restrictions
//             String house = getHouse(conn);
//             if (house != null && !house.trim().isEmpty()) {
//                 for (String post : ALL_POSTS) {
//                     if (!post.contains("House") || post.startsWith(house)) {
//                         allowedPosts.add(post);
//                     }
//                 }
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//             JOptionPane.showMessageDialog(this, 
//                 "Database error: " + e.getMessage(), 
//                 "System Error", 
//                 JOptionPane.ERROR_MESSAGE);
//         }
        
//         return allowedPosts;
//     }

//     private boolean isDignitary(Connection conn) throws SQLException {
//         try (PreparedStatement ps = conn.prepareStatement(
//                 "SELECT rollno FROM Details WHERE rollno = ?")) {
//             ps.setString(1, studentRoll);
//             ResultSet rs = ps.executeQuery();
//             return rs.next() && DIGNITARIES.contains(rs.getString("rollno"));
//         }
//     }

//     private String getHouse(Connection conn) throws SQLException {
//         try (PreparedStatement ps = conn.prepareStatement(
//                 "SELECT house FROM Details WHERE rollno = ?")) {
//             ps.setString(1, studentRoll);
//             ResultSet rs = ps.executeQuery();
//             return rs.next() ? rs.getString("house") : null;
//         }
//     }

//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(() -> {
//             Votepage frame = new Votepage("Test User", "BNG234918"); // Test with dignitary roll
//             frame.setVisible(true);
//             frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         });
//     }
// }

import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class Votepage extends JFrame {

    private final String studentName;
    private final String studentRoll;

    // All 17 voting positions
    private static final String[] ALL_POSTS = {
            "Head Boy", "Head Girl", "Deputy Head Boy", "Deputy Head Girl",
            "Sports Captain", "Science Club Secretary", "Cultural Secretary",
            "Heritage Secretary", "Literacy Secretary",
            "Spartans House Captain", "Vikings House Captain", "Knights House Captain", "Samurais House Captain",
            "Spartans House Vice Captain", "Vikings House Vice Captain", "Knights House Vice Captain",
            "Samurais House Vice Captain"
    };

    // Official list of dignitaries who can vote for all positions
    private static final Set<String> DIGNITARIES = new HashSet<>(Arrays.asList(
            "BNG234918", "BNG222585", "BNG285909", "BNG859669",
            "BNG211583", "BNG211369", "BNG470176", "BNG942202",
            "BNG298683", "BNG237799", "AGM001"));

    public Votepage(String name, String roll) {
        this.studentName = name;
        this.studentRoll = roll;

        System.out.println("Reached Votepage for " + name + " (" + roll + ")");
        JOptionPane.showMessageDialog(this, "Welcome to the Voting Page, " + name);

        markAsVoted(); // Optional: Lock user here itself
        initializeUI();
    }

    private void initializeUI() {
        setBounds(0, 30, 1600, 800);
        setTitle("Vote for Student Council");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Welcome message
        JLabel title = new JLabel("Welcome, " + studentName, JLabel.CENTER);
        title.setFont(new Font("Raleway", Font.BOLD, 40));
        title.setBounds(400, 100, 800, 50);
        add(title);

        // Start Voting button
        JButton startVoting = new JButton("START VOTING");
        startVoting.setBounds(550, 300, 400, 60);
        startVoting.setFont(new Font("Raleway", Font.BOLD, 24));
        startVoting.setBackground(new Color(30, 144, 255));
        startVoting.setForeground(Color.WHITE);
        startVoting.addActionListener(e -> handleVotingStart());
        add(startVoting);

        // Back button
        JButton back = new JButton("← Back");
        back.setBounds(50, 700, 120, 40);
        back.setFont(new Font("Raleway", Font.PLAIN, 18));
        back.addActionListener(e -> {
            new StartPage().setVisible(true);
            dispose();
        });
        add(back);
    }

    private void handleVotingStart() {
        ArrayList<String> allowedPosts = checkVotingEligibility();

        if (allowedPosts.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No voting positions available.\nContact administrator.",
                    "Voting Error",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            new GenericVotePage(allowedPosts.get(0), studentName, studentRoll, allowedPosts).setVisible(true);
            dispose();
        }
    }

    private ArrayList<String> checkVotingEligibility() {
        ArrayList<String> allowedPosts = new ArrayList<>();

        try (Connection conn = new Conn().getConnection()) {
            if (isDignitary(conn)) {
                Collections.addAll(allowedPosts, ALL_POSTS);
                return allowedPosts;
            }

            String house = getHouse(conn);
            if (house != null && !house.trim().isEmpty()) {
                for (String post : ALL_POSTS) {
                    if (!post.contains("House") || post.startsWith(house)) {
                        allowedPosts.add(post);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Database error: " + e.getMessage(),
                    "System Error",
                    JOptionPane.ERROR_MESSAGE);
        }

        return allowedPosts;
    }

    private boolean isDignitary(Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT rollno FROM Details WHERE rollno = ?")) {
            ps.setString(1, studentRoll);
            ResultSet rs = ps.executeQuery();
            return rs.next() && DIGNITARIES.contains(rs.getString("rollno"));
        }
    }

    private String getHouse(Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT house FROM Details WHERE rollno = ?")) {
            ps.setString(1, studentRoll);
            ResultSet rs = ps.executeQuery();
            return rs.next() ? rs.getString("house") : null;
        }
    }

    private void markAsVoted() {
        try (Connection conn = new Conn().getConnection()) {
            PreparedStatement update = conn.prepareStatement(
                    "UPDATE Details SET has_voted = true WHERE rollno = ?");
            update.setString(1, studentRoll);
            update.executeUpdate();
            System.out.println("Marked as voted: " + studentRoll);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to mark as voted.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Votepage frame = new Votepage("Test User", "BNG234918"); // Test dignitary
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }
}
