
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class PostResultPage extends JFrame {

    JTable table;

    public PostResultPage(String post) {
        setTitle("Results for " + post);
        setSize(800, 600);
        setLocation(400, 100);
        getContentPane().setBackground(Color.WHITE);
        setLayout(new BorderLayout());

        JLabel heading = new JLabel("Results: " + post, SwingConstants.CENTER);
        heading.setFont(new Font("Raleway", Font.BOLD, 30));
        heading.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(heading, BorderLayout.NORTH);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        displayResults(post);
    }

    private void displayResults(String post) {
        try {
            Conn c = new Conn();
            String query = "SELECT Name, Rollno, Votes FROM candidatedetails WHERE Post = ? ORDER BY Votes DESC";
            PreparedStatement ps = c.c.prepareStatement(query);
            ps.setString(1, post);
            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Name");
            model.addColumn("Roll No.");
            model.addColumn("Votes");

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("Name"),
                    rs.getString("Rollno"),
                    rs.getInt("Votes")
                });
            }

            table.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

