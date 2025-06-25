
import java.awt.*;
import javax.swing.*;

public class ResultDashboard extends JFrame {

    public ResultDashboard() {
        setTitle("Election Results");
        setLayout(null);
        setBounds(0, 30, 1600, 800);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Student Council Election Results");
        heading.setFont(new Font("Raleway", Font.BOLD, 35));
        heading.setBounds(450, 30, 700, 40);
        add(heading);

        String[] posts = {
            "Head Boy", "Head Girl", "Deputy Head Boy", "Deputy Head Girl",
            "Sports Captain", "Science Club Secretary", "Cultural Secretary",
            "Heritage Secretary", "Literacy Secretary",
            "Spartans House Captain", "Vikings House Captain", "Samurais House Captain", "Knights House Captain",
            "Spartans House Vice Captain", "Vikings House Vice Captain", "Samurais House Vice Captain", "Knights House Vice Captain"
        };

        int x = 300, y = 100;
        for (int i = 0; i < posts.length; i++) {
            String post = posts[i];
            JButton btn = new JButton(post);
            btn.setBounds(x, y, 300, 40);
            btn.setFont(new Font("Raleway", Font.PLAIN, 18));
            btn.setBackground(new Color(34, 139, 34));
            btn.setForeground(Color.WHITE);
            btn.addActionListener(e -> {
                new PostResultPage(post).setVisible(true);
            });
            add(btn);

            y += 60;
            if ((i + 1) % 6 == 0) {
                x += 350;
                y = 100;
            }
        }
    }

    public static void main(String[] args) {
        new ResultDashboard().setVisible(true);
    }
}
