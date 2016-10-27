import java.sql.*;
import java.util.Scanner;

public class Good {
    private String name;
    private double price;

    public Good() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    ///////////////////////////////////////////////////////////////////

    public void addGood(Connection conn, Scanner sc) throws SQLException {
        System.out.println("Enter good's name:");
        this.name = sc.nextLine();
        System.out.println("Enter good's price:");
        this.price = Double.parseDouble(sc.nextLine());

        PreparedStatement ps = conn.prepareStatement("INSERT INTO GOODS (name, price) VALUES (?, ?)");
        try {
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.executeUpdate();
        } finally {
            ps.close();
        }
    }

    public static void viewGoods(Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM GOODS");
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
