import java.sql.*;
import java.util.Scanner;

public class Client {
    private String name;
    private String surname;

    public Client() {}
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    ///////////////////////////////////////////////

    public void addClient(Connection conn, Scanner sc) throws SQLException {
        System.out.println("Enter client's name");
        this.name = sc.nextLine();
        System.out.println("Enter client's surname");
        this.surname = sc.nextLine();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO CLIENTS (name, surname) VALUES (?, ?)");
        try {
            ps.setString(1, name);
            ps.setString(2, surname);
            ps.executeUpdate();
        } finally {

        }
    }

    public static void viewClients (Connection conn) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM CLIENTS");
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
