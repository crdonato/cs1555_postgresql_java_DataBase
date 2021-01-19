// Project phase 3
// BoutiqueCoffee
// Craig Donato - crd69
// Sam Skupien - sss78

import java.sql.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat; 
import java.util.*;

public class BoutiqueCoffee {
	

    private Connection connection;
    private Statement statement;

	
	public BoutiqueCoffee(Connection link) throws SQLException{
		this.connection = link;
	}

    public BoutiqueCoffee(){
        System.out.println("Boutique Coffee Database Running...\n");
        runOps();
    }

    public void runOps() {

        Scanner scan = new Scanner(System.in);
        int userOp = 0;

        while(true) {
            System.out.println("Choose an operation:\n" +
                    "1: Add Store\n" +
                    "2: Add Coffee\n" +
                    "3: Store 'x' offers coffee 'y'\n" +
                    "4: Add store promotion \n" +
                    "5: Promote for coffee \n" +
                    "6: Store 'x' has promotion 'y' \n" +
                    "7: Add member level\n" +
                    "8: Add customer\n" +
                    "9: Add purchase\n" +
                    "10: Get all coffees\n" +
                    "11: Get coffee's by keywords\n" +
                    "12: Get points by customer ID\n" +
                    "13: Get top stores with the highest revenue in last x months\n" +
                    "14: Get top K customers\n" +
					"15: Exit\n");

            userOp = scan.nextInt();

            switch (userOp) {

                case 1: // Add Store

                    String StoreName;
                    String address;
                    String storeType;
                    double gpsLat;
                    double gpsLong;
                    int addStoreRes;

                    System.out.println("Enter Store Name: [string]\n");
                    scan.skip("\n");
                    StoreName = scan.nextLine();
                    System.out.println("Enter Store Address: [string]\n");
                    address = scan.nextLine();
                    System.out.println("Enter Store Type: [string]\n");
                    storeType = scan.nextLine();
                    System.out.println("Enter Store GPS Latitude: [double]\n");
                    gpsLat = scan.nextDouble();
                    System.out.println("Enter Store GPS Longitude: [double]\n");
                    gpsLong = scan.nextDouble();

                    addStoreRes = addStore (StoreName, address, storeType, gpsLong, gpsLat);

                    System.out.println("Addstore operation result: " + addStoreRes + "\n\n");
                    break;

                case 2: // Add Coffee

                    String coffeeName;
                    String description;
                    int intensity;
                    double price;
                    double rewardPoints;
                    double redeemPoints;
                    int addCoffeeRes;

                    System.out.println("Enter Coffee Name: [string]\n");
                    scan.skip("\n");
                    coffeeName = scan.nextLine();
                    System.out.println("Enter Coffee Description: [string]\n");
                    description = scan.nextLine();
                    System.out.println("Enter Intensity: [int and >= 0]\n");
                    intensity = scan.nextInt();
                    System.out.println("Enter Price: [double and >= 1.50]\n");
                    price = scan.nextDouble();
                    System.out.println("Enter Reward Points: [double <= Redeem_Points * 0.10 ]\n");
                    rewardPoints = scan.nextDouble();
                    System.out.println("Enter redeemPoints: [double]\n");
                    redeemPoints = scan.nextDouble();




                    addCoffeeRes = addCoffee (coffeeName, description, intensity, price, rewardPoints, redeemPoints);

                    System.out.println("AddCoffee operation result: " + addCoffeeRes + "\n\n");
                    break;

                case 3: // Offer Coffee

                    int offerCoffeeStoreId;
                    int offerCoffeeCoffeeId;
                    int offerCoffeeRes;

                    System.out.println("Enter Store ID: \n");
                    scan.skip("\n");
                    offerCoffeeStoreId = scan.nextInt();
                    System.out.println("Enter Coffee ID: \n");
                    offerCoffeeCoffeeId = scan.nextInt();

                    offerCoffeeRes = offerCoffee(offerCoffeeStoreId, offerCoffeeCoffeeId);

                    System.out.println("offerCoffee operation result: " + offerCoffeeRes + "\n\n");
                    break;

                case 4: // Add Promotion

                    String addPromotionName;
                    String strStartDate;
                    String strEndDate;
                    Date startDate;
                    Date endDate;
                    int addPromotionResult;

                    System.out.println("Enter Promotion Name: \n");
                    scan.skip("\n");
                    addPromotionName = scan.nextLine();
                    System.out.println("Enter Start Date: \n");
                    strStartDate = scan.nextLine();
                    startDate = Date.valueOf(strStartDate);
                    System.out.println("Enter End Date: \n");
                    strEndDate = scan.nextLine();
                    endDate = Date.valueOf(strEndDate);

                    addPromotionResult = addPromotion(addPromotionName, startDate,  endDate);

                    System.out.println("addPromotion operation result: " + addPromotionResult + "\n\n");
                    break;

                case 5: // Promote For

                    int promoteForPromotionId;
                    int promoteForCoffeeId;
                    int promoteForResult;

                    System.out.println("Enter Promotion ID: \n");
                    scan.skip("\n");
                    promoteForPromotionId = scan.nextInt();
                    System.out.println("Enter Coffee ID: \n");
                    promoteForCoffeeId = scan.nextInt();

                    promoteForResult = promoteFor (promoteForPromotionId, promoteForCoffeeId);

                    System.out.println("promoteFor operation result: " + promoteForResult + "\n\n");
                    break;

                case 6: // Has Promotion

                    int hasPromotionStoreId;
                    int hasPromotionPromotionId;
                    int hasPromotionResult;

                    System.out.println("Enter Store ID: \n");
                    scan.skip("\n");
                    hasPromotionStoreId = scan.nextInt();
                    System.out.println("Enter Promotion ID: \n");
                    hasPromotionPromotionId = scan.nextInt();

                    hasPromotionResult = hasPromotion (hasPromotionStoreId, hasPromotionPromotionId);

                    System.out.println("hasPromotion operation result: " + hasPromotionResult + "\n\n");
                    break;

                case 7: // Add Member Level

                    String memberLevelName;
                    double memberBoosterFactor;
                    int addMemberLevelResult;

                    System.out.println("Enter Name: \n");
                    scan.skip("\n");
                    memberLevelName = scan.nextLine();
                    System.out.println("Enter Booster Factor: \n");
                    memberBoosterFactor = scan.nextDouble();

                    addMemberLevelResult = addMemberLevel (memberLevelName, memberBoosterFactor);

                    System.out.println("addMemberLevel operation result: " + addMemberLevelResult + "\n\n");
                    break;

                case 8: // Add customer

                    String addCustomerFirstName;
                    String addCustomerLastName;
                    String addCustomerEmail;
                    int addCustomerMemberLevelId;
                    double addCustomerTotalPoints;
                    int addCustomerResult;

                    System.out.println("Enter First Name: \n");
                    scan.skip("\n");
                    addCustomerFirstName = scan.nextLine();
                    System.out.println("Enter Last Name: \n");
                    addCustomerLastName = scan.nextLine();
                    System.out.println("Enter email: \n");
                    addCustomerEmail = scan.nextLine();
                    System.out.println("Enter Member Level ID: \n");
                    addCustomerMemberLevelId = scan.nextInt();
                    System.out.println("Enter Total Points: \n");
                    addCustomerTotalPoints = scan.nextDouble();

                    addCustomerResult = addCustomer (addCustomerFirstName, addCustomerLastName, addCustomerEmail, addCustomerMemberLevelId, addCustomerTotalPoints);

                    System.out.println("addCustomer operation result: " + addCustomerResult + "\n\n");
                    break;

                case 9: // Add Purchase

                    int numPurchases;
                    int numRedeems;
                    int addPurchaseCustomerId;
                    int addPurchaseStoreId;
                    String strAddPurchasePurchaseTime;
                    Date addPurchasePurchaseTime;
                    List <Integer> coffeeIds = new ArrayList<>();
                    List <Integer> purchaseQuantities = new ArrayList<>();
                    List <Integer> redeemQuantities = new ArrayList<>();
                    int addPurchaseResult;

                    System.out.println("Enter the amount of coffee's purchased: \n");
                    scan.skip("\n");
                    numPurchases = scan.nextInt();
                    System.out.println("Enter the Redeems: \n");
                    numRedeems = scan.nextInt();
                    System.out.println("Enter Customer ID: \n");
                    addPurchaseCustomerId = scan.nextInt();
                    System.out.println("Enter Store ID: ");
                    addPurchaseStoreId = scan.nextInt();
                    System.out.println("Enter Purchase time: \n");
                    scan.skip("\n");
                    strAddPurchasePurchaseTime = scan.nextLine();
                    addPurchasePurchaseTime = Date.valueOf(strAddPurchasePurchaseTime);

                    System.out.println("Enter the " + numPurchases + " Coffee IDs: \n");
                    for(int i = 0; i < numPurchases; i++){

                        int temp = scan.nextInt();
                        coffeeIds.add(temp);
                    }

                    System.out.println("Enter the Amount of Each Coffee Being Purchased: \n");
                    for(int i = 0; i < numPurchases; i++){

                        System.out.println("Amount for " + coffeeIds.get(i) + " :\n");
                        int temp = scan.nextInt();
                        purchaseQuantities.add(temp);
                    }

                    System.out.println("Enter the " + numRedeems + " Redeem Points for each coffee: ");
                    for(int i = 0; i < numPurchases; i++){

                        System.out.println("Amount for " + coffeeIds.get(i) + " (redeem points are ints) :\n");
                        int temp = scan.nextInt();
                        redeemQuantities.add(temp);
                    }

                    addPurchaseResult = addPurchase(addPurchaseCustomerId, addPurchaseStoreId, addPurchasePurchaseTime, coffeeIds, purchaseQuantities, redeemQuantities);
                    System.out.println("addPurchase operation result: " + addPurchaseResult + "\n\n");
                    break;

                case 10: // get all coffee's

                    List <Integer> allCoffees = getCoffees();


                    for(int i = 0; i < allCoffees.size(); i++){
                        System.out.println("Coffee ID's: " + allCoffees.get(i) + "\n");
                    }
                    break;

                case 11: // get coffee by keywords

                    String coffeeKeyword1;
                    String coffeeKeyword2;

                    System.out.println("Enter the first keyword: \n");
                    scan.skip("\n");
                    coffeeKeyword1 = scan.nextLine();
                    System.out.println("Enter the Second keyword: \n");
                    coffeeKeyword2 = scan.nextLine();

                    List <Integer> coffeeKeywords = getCoffeesByKeywords (coffeeKeyword1, coffeeKeyword2);

                    for(int i = 0; i < coffeeKeywords.size(); i++){
                        System.out.println("Coffee's are: " + coffeeKeywords.get(i) + "\n");
                    }
                    break;

                case 12: // get points by customer ID

                    int getPointsCustomerId;
                    double returnedPoints;

                    System.out.println("Enter Customer ID: \n");
                    scan.skip("\n");
                    getPointsCustomerId = scan.nextInt();

                    returnedPoints = getPointsByCustomerId (getPointsCustomerId);

                    System.out.println("Customer ID: " + getPointsCustomerId + " total points are: " + returnedPoints + "\n");
                    break;

                case 13: // get top k stores in past x months

                    int topStores;
                    int storeMonths;

                    System.out.println("Enter number of stores to be retrieved: ");
                    scan.skip("\n");
                    topStores = scan.nextInt();
                    System.out.println("Enter months: ");
                    storeMonths = scan.nextInt();

                    List<Integer> stores = getTopKStoresInPastXMonth(topStores, storeMonths);

                    for(int i = 0; i < stores.size(); i++){
                        System.out.println("Top Stores: " + stores.get(i) + "\n");
                    }
                    break;

                case 14: // get to k customers in past x months

                    int topCust;
                    int custMonths;

                    System.out.println("Enter number of customers to be retrieved: ");
                    scan.skip("\n");
                    topCust = scan.nextInt();
                    System.out.println("Enter months: ");
                    custMonths = scan.nextInt();

                    List<Integer> customers = getTopKCustomersInPastXMonth(topCust, custMonths);

                    for(int i = 0; i < customers.size(); i++){
                        System.out.println("Top customers: " + customers.get(i) + "\n");
                    }
                    break;

                case 15:
                    System.exit(0);
                    break;

                default:
                    throw new IllegalStateException("Unexpected value: " + userOp);
            }
        }
    }


    // case 1
    public int addStore (String name, String address, String storeType, double gpsLong, double gpsLat){

        int result = -1;
        ResultSet res;

        try{

            Statement s = connection.createStatement();

            String sqlString = "insert into store (name, address, store_type, gps_long, gps_lat)" +
            "values ('" + name + "','" + address + "', '" + storeType + "','" + gpsLong + "' ,'" + gpsLat + "');";

            result = s.executeUpdate(sqlString);

            if(result != -1) {
                // assumption: name and address paired together are unique enough to identify a store
                String sqlString2 = "select store_id from store where name = '" + name + "' and address = '" + address + "';";
                res = s.executeQuery(sqlString2);

                while(res.next()) {
                    result = res.getInt("store_id");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return result;

    }


    // case 2
    public int addCoffee (String name, String description,int intensity, double price, double rewardPoints, double redeemPoints){

        int result = -1;
        ResultSet res;

        try{

            Statement s = connection.createStatement();

            String sqlString = "insert into coffee (name, description, intensity, price, reward_points, redeem_points)" +
                    "values ('" + name + "','" + description + "', '" + intensity + "','" + price + "' ,'" + rewardPoints + "','" + redeemPoints + "');";

            result = s.executeUpdate(sqlString);

            if(result != -1) {
                // assumption: name is unique enough to identify a coffee
                String sqlString2 = "select coffee_id from coffee where name = '" + name + "';";
                res = s.executeQuery(sqlString2);

                while(res.next()) {
                    result = res.getInt("coffee_id");
                }
            }



        } catch (SQLException e) {
            e.printStackTrace();

        }
        return result;
    }



    // case 3
    public int offerCoffee (int storeId, int coffeeId){

        int res = -1;


        try{

            Statement s = connection.createStatement();

            String sqlString = "insert into offerCoffee (store_id, coffee_id)" +
                    "values ('" + storeId + "','" + coffeeId + "');";

            res = s.executeUpdate(sqlString);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return res;

    }

    //case 4
    public int addPromotion (String name, Date startDate, Date endDate){

        int result = -1;
        ResultSet res;

        try{

            Statement s = connection.createStatement();

            String sqlString = "insert into promotion (name, start_date, end_date)" +
                    "values ('" + name + "','" + startDate + "', '" + endDate + "');";

            result = s.executeUpdate(sqlString);

            if(result != -1) {
                // assumption: name, start date and end date is unique enough to identify promotion
                String sqlString2 = "select promotion_id from promotion where name = '" + name + "' and "
                                    + "start_date = '" + startDate + "'"
                                    + "and end_date = '" + endDate + "';";
                res = s.executeQuery(sqlString2);

                while(res.next()) {
                    result = res.getInt("promotion_id");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return result;
    }


    // case 5
    public int promoteFor (int promotionId, int coffeeId){

        int res = -1;

        try{

            Statement s = connection.createStatement();

            String sqlString = "insert into promoteFor (promotion_id, coffee_id)" +
                    "values ('" + promotionId + "','" + coffeeId + "');";

            res = s.executeUpdate(sqlString);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return res;
    }


    // case 6
    public int hasPromotion (int storeId, int promotionId){

        int res = -1;

        try{

            Statement s = connection.createStatement();

            String sqlString = "insert into hasPromotion (store_id, promotion_id)" +
                    "values ('" + storeId + "','" + promotionId + "');";

            res = s.executeUpdate(sqlString);

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return res;
    }


    // case 7
    public int addMemberLevel (String name,double boosterFactor){

        int result = -1;
        ResultSet res;

        try{

            Statement s = connection.createStatement();

            String sqlString = "insert into memberlevel (name, booster_factor)" +
                    "values ('" + name + "','" + boosterFactor + "');";

            result = s.executeUpdate(sqlString);

            if(result != -1) {
                // assumption: name, start date and end date is unique enough to identify promotion
                String sqlString2 = "select memberlevel_id from memberlevel where name = '" + name + "' and booster_factor = '"
                                    + boosterFactor + "';";
                res = s.executeQuery(sqlString2);

                while(res.next()) {
                    result = res.getInt("memberlevel_id");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return result;
    }


    // case 8
    public int addCustomer (String firstName, String lastName, String email, int memberLevelId, double totalPoints){

        int result = -1;
        ResultSet res;

        try{

            Statement s = connection.createStatement();

            String sqlString = "insert into customer(first_name, last_name, email, memberlevel_id, total_points)" +
                    "values ('" + firstName + "','" + lastName + "','" + email + "','" + memberLevelId + "','" + totalPoints + "');";

            result = s.executeUpdate(sqlString);

            if(result != -1) {
                // assumption: name, start date and end date is unique enough to identify promotion
                String sqlString2 = "select customer_id from customer where first_name = '" + firstName + "' and last_name = '"
                        + lastName + "' and email = '" + email + "';";
                res = s.executeQuery(sqlString2);

                while(res.next()) {
                    result = res.getInt("customer_id");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return result;
    }


    // case 9
    public int addPurchase (int customerId, int storeId, Date purchaseTime, List <Integer> coffeeIds, List <Integer> purchaseQuantities, List <Integer> redeemQuantities)
    {

        int result = -1;
        ResultSet res;

        try{

            Statement s = connection.createStatement();

            for(int i = 0; i < coffeeIds.size(); i++) {

                String sqlString = "insert into CustomerPurchases(customer_id, store_id, coffee_id, purchase_quantity, redeem_quantity, purchase_time)" +
                        "values ('" + customerId + "','"
                                    + storeId + "','"
                                    + coffeeIds.get(i) + "','"
                                    + purchaseQuantities.get(i) + "','"
                                    + redeemQuantities.get(i) + "','"
                                    + purchaseTime + "');";

                result = s.executeUpdate(sqlString);

                if (result != -1) {

                    // assumption: name,
                    String sqlString2 = "select purchase_id from CustomerPurchases where customer_id = '" + customerId + "' and store_id = '"
                            + storeId + "' and coffee_id = '" + coffeeIds.get(i) + "' and purchase_quantity = '" + purchaseQuantities.get(i) + "'" +
                            " and redeem_quantity = '" + redeemQuantities.get(i) + "';";
                    res = s.executeQuery(sqlString2);

                    while (res.next()) {
                        result = res.getInt("purchase_id");
                    }
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return result;

    }

    // case 10
    public List<Integer> getCoffees() {


        List<Integer> coffees = new ArrayList<>();
        ResultSet res;

        try {

            Statement s = connection.createStatement();
            String sql = "select coffee_id from coffee;";
            res = s.executeQuery(sql);

            while(res.next()) {
                int temp = res.getInt("coffee_id");
                coffees.add(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coffees;

     }


    // case 11
    public List<Integer> getCoffeesByKeywords (String keyword1, String keyword2){

        List<Integer> coffees = new ArrayList<>();
        ResultSet res;

        try {

            Statement s = connection.createStatement();
            String sql = "select coffee_id from coffee where name ilike '%" + keyword1 + " " + keyword2 + "%';";
            res = s.executeQuery(sql);

            while(res.next()) {
                int temp = res.getInt("coffee_id");
                coffees.add(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coffees;
    }


    // case 12
    public double getPointsByCustomerId(int customerId){

        double returnVal = -1;
        ResultSet res;

        try {

            Statement s = connection.createStatement();
            String sql = "select total_points from customer where customer_id = '" + customerId + "';";
            res = s.executeQuery(sql);

            while(res.next()) {
                returnVal = res.getDouble("total_points");

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnVal;
    }


    // case 13
    public List<Integer> getTopKStoresInPastXMonth(int k, int x){

        List<Integer> stores = new ArrayList<>();
        ResultSet res;

        try {

            Statement s = connection.createStatement();

            String sql = "select s.store_id, sum(b.purchase_quantity) as purchase_quantity"
            + " from store s, BuyCoffee b, purchase p"
            + " where s.store_id = p.store_id"
            + " and p.Purchase_ID = b.Purchase_ID"
            + " and p.purchase_time > now() - interval '" + x + " months'"
			+ " group by s.store_id"
            + " order by Purchase_Quantity desc"
            + " fetch first " + k + " rows only;";

            res = s.executeQuery(sql);

            while(res.next()) {
                int temp = res.getInt("store_id");
                stores.add(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stores;
    }


    // case 14
    public List<Integer> getTopKCustomersInPastXMonth(int k, int x){

        List<Integer> customers = new ArrayList<>();
        ResultSet res;

        try {

            Statement s = connection.createStatement();

            String sql = "select Distinct c.customer_id, sum(b.purchase_quantity) as purchase_quantity"
                        + " from customer c, BuyCoffee b, purchase p"
                        + " where c.Customer_ID = p.Customer_ID"
                        + " and p.Purchase_ID = b.Purchase_ID"
                        + " and p.purchase_time > now() - interval '" + x + "months'"
						+ " group by c.customer_id"
                        + " order by Purchase_Quantity desc"
                        + " fetch first " + k + " rows only;";

            res = s.executeQuery(sql);

            while(res.next()) {
                int temp = res.getInt("customer_id");
                customers.add(temp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customers;
    }
}
