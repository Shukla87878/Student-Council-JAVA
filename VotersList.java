
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

public class VotersList extends JFrame implements ActionListener{
     JButton b7;
    VotersList(){
    setBounds(0,0,1600,800);
    setLayout(null);
    getContentPane().setBackground(Color.WHITE);
    setTitle("Voters List");
    
    JLabel l1=new JLabel("Name                                                                                 | Roll no");
    l1.setBounds(0,0,1600,30);
        l1.setFont(new Font("Raleway", Font.PLAIN, 30));
        add(l1);
    
    JTable t1=new JTable();
        t1.setBounds(0,30,1600,600);
        t1.setFont(new Font("Raleway", Font.PLAIN, 30));
        t1.setRowHeight(t1.getRowHeight()+20);
        add(t1);
        
        
        b7=new JButton("Back");
        b7.setBounds(20,650,200,40);
        b7.setBackground(new Color(34,34,34));
        b7.setBorder(null);
        b7.setFont(new Font("Tahoma",Font.PLAIN,18));
        b7.setForeground(new Color(255,165,0));
        b7.addActionListener(this);
        add(b7);
        
    try{
            Conn c=new Conn();
            ResultSet rs=c.s.executeQuery("SELECT * FROM Details");
            t1.setModel(DbUtils.resultSetToTableModel(rs));
        }catch(Exception e){
            e.printStackTrace();
        }
}
    
    public static void main(String []args){
        new VotersList().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==b7){
            this.setVisible(false);
            new DashBoard().setVisible(true);
        }
    }
}
