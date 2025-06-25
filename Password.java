

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Password extends JFrame implements ActionListener{
    
    Password(){
        setTitle("Enter your Rollno");
        setBounds(400,150,700,400);
        getContentPane().setBackground(Color.WHITE);
    }
    
    public static void main(String []args){
        new Password().setVisible(true);
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
