import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Connection conn;
   // static Client client;
  //  static Good good;
   // static Order order;
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            conn = DataBase.initDB();


            Client client = new Client();
            Good good = new Good();
            Order order = new Order();

            while (true) {
                System.out.println("-> 1. Add Client");
                System.out.println("-> 2. Add Good");
                System.out.println("-> 3. Add Order");
                System.out.println("-> 4. View Clients");
                System.out.println("-> 5. View Goods");
                System.out.println("-> 6. View Order");
                System.out.println("-> ");
                String ans = sc.nextLine();

                switch (ans) {
                    case "1": {
                        client.addClient(conn, sc);
                        break;
                    }
                    case "2": {
                        good.addGood(conn, sc);
                        break;
                    }
                    case "3": {
                        order.addOrder(conn, sc);
                        break;
                    }
                    case "4": {
                        client.viewClients(conn);
                        break;
                    }
                    case "5": {
                        good.viewGoods(conn);
                        break;
                    }
                    case "6": {
                        order.viewOrders(conn);
                        break;
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
