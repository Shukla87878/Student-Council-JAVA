// package Student.Council;

// import java.sql.*;

// public class Conn {
//     public Connection c;
//     public Statement s;

//     public Conn() {
//         try {
//             Class.forName("com.mysql.cj.jdbc.Driver");
//             c = DriverManager.getConnection(
//                 "jdbc:mysql://127.0.0.1:3306/studentcouncil?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
//                 "root",
//                 "shukla123"  // ✅ Use your actual password (NO SPACES!)
//             );
//             s = c.createStatement();
//             System.out.println("✅ Database connection successful");
//         } catch (Exception e) {
//             System.out.println("❌ Database connection failed:");
//             e.printStackTrace();
//         }
// }



import java.sql.*;

public class Conn {
    public Connection c;
    public Statement s;

    public Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/studentcouncil?useSSL=false",
                    "root",
                    "shukla123" // 
            );
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return c;
    }
}
