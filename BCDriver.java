// Project phase 3
// Driver
// Craig Donato - crd69
// Sam Skupien - sss78
//------------------------------------------------------------------------------------

import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat; 
import java.util.*;
import java.io.*;

public class BCDriver{
	
	private static Connection connection;
    private Statement statement;
	


    public static void main(String[] args) throws SQLException {
		BoutiqueCoffee db = null;
		BCBenchmark bm = null;
		Scanner scan = new Scanner(System.in);
		int userOp = 0;

        try  {

            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
            System.out.println("Connected to Boutiqe Coffee Database.\n");
            //db = new BoutiqueCoffee();
			db = new BoutiqueCoffee(connection);
			bm = new BCBenchmark(connection);
			
			System.out.println("Select 1 for Boutique Coffee options, 2 for Benchmark tests");
			userOp = scan.nextInt();
			
			if (userOp == 1){
				db.runOps();
			}
			
			if(userOp == 2){
				bm.TestAddStore();
				bm.TestAddCoffee();
				bm.TestOfferCoffee();
				bm.TestAddPromotion();
				bm.TestPromoteFor();
				bm.TestHasPromotion();
				bm.TestAddMemberLevel();
				bm.TestAddCustomer();
				bm.TestAddPurchase();
				bm.TestGetCoffee();
				bm.TestCoffeeKeyWord();
				bm.TestGetTopStores();
				bm.TestGetTopCustomers();
			}
			

        } catch (SQLException e) {
            System.out.println("Failure to Connect.\n");
            e.printStackTrace();
            System.exit(0);
        }
    }
}