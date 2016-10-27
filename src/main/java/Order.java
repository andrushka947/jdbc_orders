import java.sql.*;
import java.util.Scanner;

public class Order {
    private String client_name;
    private String good_name;
    public void addOrder(Connection conn, Scanner sc) throws SQLException {
        Client client = new Client();
        Good good = new Good();
        client.addClient(conn, sc);
        good.addGood(conn, sc);
        PreparedStatement ps = conn.prepareStatement("INSERT INTO ORDERS (client_name, good_name) VALUES (?, ?)");
        try {
            ps.setString(1, client.getName());
            ps.setString(2, good.getName());
            ps.executeUpdate();
        } finally {
            ps.close();
        }
    }

    public static void viewOrders(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM ORDERS");
        try {
            ResultSet rs = ps.executeQuery();
            try {
                ResultSetMetaData md = rs.getMetaData();
                if (rs != null) {
                    for (int i = 1; i < md.getColumnCount() + 1; i++) {
                        System.out.print(md.getColumnName(i) + "\t\t");
                    }
                    System.out.println();
                    System.out.println("_______________________________________________________________________");

                    while (rs.next()) {
                        for (int i = 1; i < md.getColumnCount() + 1; i++) {
                            System.out.print(rs.getString(i) + "\t\t");
                        }
                        System.out.println();
                    }
                } else {
                    System.out.println("No results found(");
                }
            } finally {
                rs.close();
            }
        } finally {
            ps.close();
        }
    }

}
