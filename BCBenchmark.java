// Project phase 3
// Benchmark
// Craig Donato - crd69
// Sam Skupien - sss78
//------------------------------------------------------------------------------------

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


public class BCBenchmark{
	
	private Connection connection;
    private Statement statement;
	BoutiqueCoffee db = null;
	
	public BCBenchmark(Connection link) throws SQLException{
		this.connection = link;
		db = new BoutiqueCoffee(connection);
	}
	
	public void TestAddStore(){
		String storeName = "TestStore: ";
		String address = "Test Street ";
		String storeType = "Test Kiosk: ";
		double gpsLat = 0.00;
		double gpsLong = 0.00;
		
		int temp_num = 0;
		int addStoreRes;
		
		try{
			Statement s = connection.createStatement();
			String sql = "select max(store_id) as store_id from store;";
			ResultSet res = s.executeQuery(sql);
			if(res.next()){
				temp_num = res.getInt("store_id");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		int i = temp_num;

		long startTime = System.currentTimeMillis();
		
		for( ; i < temp_num + 5000; i++){
			addStoreRes = db.addStore(storeName + i, address + i, storeType + i, gpsLong + i, gpsLat + i);
		}
		
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		long averageTime = totalTime / Math.abs(temp_num - i);
	
		
		System.out.println("------------------------------------------------------------");
		System.out.println("Added: " + Math.abs(temp_num - i) + " test Stores");
		System.out.println("Total before number of Rows: " + temp_num);
		System.out.println("Total after  number of Rows: " + i);
		System.out.println("Total run time: " + totalTime + " ms");
		System.out.println("Average time per insert: " + averageTime + " ms");
		System.out.println();
	}
	
	public void TestAddCoffee(){
		String coffeeName = "Test Coffee: ";
		String description;
		double price = 1.50;
		double rewardPoints = 10;
		double redeemPoints = 100;
		
		String des1 = "Weak flavor";
		String des2 = "Full flavor";
		String des3 = "Bold flavor";
		
		int addCoffeeRes;
		
		int temp_num = 0;
		
		try{
			Statement s = connection.createStatement();
			String sql = "select max(coffee_id) as coffee_id from coffee;";
			ResultSet res = s.executeQuery(sql);
			if(res.next()){
				temp_num = res.getInt("coffee_id");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		int i = temp_num;
		
		Random rand = new Random();
		
		long startTime = System.currentTimeMillis();
		
		for ( ; i < temp_num + 5000; i++){
			int r = rand.nextInt(2);
			if (r == 0){
				description = des1;
			}else if(r == 1){
				description = des2;
			}else{
				description = des3;
			}
			
			addCoffeeRes = db.addCoffee(coffeeName + i, description, i, price, rewardPoints, redeemPoints);
		}
		
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		long averageTime = totalTime / Math.abs(temp_num - i);
		
		System.out.println("------------------------------------------------------------");
		System.out.println("Added: " + Math.abs(temp_num - i) + " test Coffees");
		System.out.println("Total before number of Rows: " + temp_num);
		System.out.println("Total after  number of Rows: " + i);
		System.out.println("Total run time: " + totalTime + " ms");
		System.out.println("Average time per insert: " + averageTime + " ms");
		System.out.println();
	}
	
	public void TestOfferCoffee(){
		int offerStoreID;
		int offerCoffeeID;
		int offerCoffeeRes;
		
		int temp_num = 0;

		try{
			Statement s = connection.createStatement();
			String sql = "select max(store_id) as store_id from offercoffee;";
			ResultSet res = s.executeQuery(sql);
			if(res.next()){
				temp_num = res.getInt("store_id");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		int i = temp_num + 1;
		int j = 1;
		
		long startTime = System.currentTimeMillis();
		
		for( ; i < temp_num + 2500; i++, j++){
			offerCoffeeRes = db.offerCoffee(i, j);
		}
		
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		long averageTime = totalTime / Math.abs(temp_num - i);
		
		System.out.println("------------------------------------------------------------");
		System.out.println("Added: " + Math.abs(temp_num - i) + " test Coffee and Store combinations");
		System.out.println("Total before number of Rows: " + temp_num);
		System.out.println("Total after  number of Rows: " + i);
		System.out.println("Total run time: " + totalTime + " ms");
		System.out.println("Average time per insert: " + averageTime + " ms");
		System.out.println();
	}
	
	public void TestAddPromotion(){
		String addPromotionName = "Test Promo: ";
		Date startDate = Date.valueOf("2019-07-01");
        Date endDate = Date.valueOf("2019-09-30");
		int addPromotionResult;
		
		int temp_num = 0;

		try{
			Statement s = connection.createStatement();
			String sql = "select max(promotion_id) as promotion_id from promotion;";
			ResultSet res = s.executeQuery(sql);
			if(res.next()){
				temp_num = res.getInt("promotion_id");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}

		int i = 0;
		
		long startTime = System.currentTimeMillis();
		
		for( ; i < 5000; i++){
			addPromotionResult = db.addPromotion(addPromotionName + i, startDate, endDate);
		}
		
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		long averageTime = totalTime / i;
		
		System.out.println("------------------------------------------------------------");
		System.out.println("Added: " + i + " Test Promotions.");
		System.out.println("Total before number of Rows: " + temp_num);
		System.out.println("Total after  number of Rows: " + i);
		System.out.println("Total run time: " + totalTime + " ms");
		System.out.println("Average time per insert: " + averageTime + " ms");
		System.out.println();
	}
	
	public void TestPromoteFor(){
		int promoteForPromotionId;
		int promoteForCoffeeId;
		int promoteForResult;

		int temp_num = 0;

		try{
			Statement s = connection.createStatement();
			String sql = "select max(promotion_id) as promotion_id from promotefor;";
			ResultSet res = s.executeQuery(sql);
			if(res.next()){
				temp_num = res.getInt("promotion_id");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		int i = temp_num + 1;
		
		long startTime = System.currentTimeMillis();
		
		for( ; i < temp_num + 2500; i++){
			promoteForResult = db.promoteFor(i, i);
		}
		
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		long averageTime = totalTime / Math.abs(temp_num - i);
		
		System.out.println("------------------------------------------------------------");
		System.out.println("Added: " + Math.abs(temp_num - i) + " Test Promotions For.");
		System.out.println("Total before number of Rows: " + temp_num);
		System.out.println("Total after  number of Rows: " + i);
		System.out.println("Total run time: " + totalTime + " ms");
		System.out.println("Average time per insert: " + averageTime + " ms");
		System.out.println();
	}
	
	public void TestHasPromotion(){
		int hasPromotionStoreId;
		int hasPromotionPromotionId;
		int hasPromotionResult;

		int temp_num = 0;

		try{
			Statement s = connection.createStatement();
			String sql = "select max(store_id) as store_id from haspromotion;";
			ResultSet res = s.executeQuery(sql);
			if(res.next()){
				temp_num = res.getInt("store_id");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		int i = temp_num + 1;
		
		long startTime = System.currentTimeMillis();
		
		for( ; i < temp_num + 2500; i++){
			hasPromotionResult = db.hasPromotion (i + 1, i + 1);
		}
		
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		long averageTime = totalTime / Math.abs(temp_num - i);
		
		System.out.println("------------------------------------------------------------");
		System.out.println("Added: " + Math.abs(temp_num - i) + " Test Has Promotions.");
		System.out.println("Total before number of Rows: " + temp_num);
		System.out.println("Total after  number of Rows: " + i);
		System.out.println("Total run time: " + totalTime + " ms");
		System.out.println("Average time per insert: " + averageTime + " ms");
		System.out.println();
	}
	
	public void TestAddMemberLevel(){
		String memberLevelName = "Test lvl: ";
		int addMemberLevelResult;
		
		int temp_num = 0;

		try{
			Statement s = connection.createStatement();
			String sql = "select max(memberlevel_id) as memberlevel_id from memberlevel;";
			ResultSet res = s.executeQuery(sql);
			if(res.next()){
				temp_num = res.getInt("memberlevel_id");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		int i = temp_num;
		
		long startTime = System.currentTimeMillis();
		
		for( ; i < temp_num + 5000; i++){
			addMemberLevelResult = db.addMemberLevel (memberLevelName + i, i);
		}
		
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		long averageTime = totalTime / Math.abs(temp_num - i);
		
		System.out.println("------------------------------------------------------------");
		System.out.println("Added: " + Math.abs(temp_num - i) + " Test member levels.");
		System.out.println("Total before number of Rows: " + temp_num);
		System.out.println("Total after  number of Rows: " + i);
		System.out.println("Total run time: " + totalTime + " ms");
		System.out.println("Average time per insert: " + averageTime + " ms");
		System.out.println();
	}
	
	public void TestAddCustomer(){
		String addCustomerFirstName = "Test F_Name: ";
		String addCustomerLastName = "Test L_Name: ";
		int addCustomerMemberLevelId = 1;
		double addCustomerTotalPoints = 0.00;
		int addCustomerResult;
		
		int temp_num = 0;

		try{
			Statement s = connection.createStatement();
			String sql = "select max(customer_id) as customer_id from customer;";
			ResultSet res = s.executeQuery(sql);
			if(res.next()){
				temp_num = res.getInt("customer_id");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		int i = temp_num;
		
		long startTime = System.currentTimeMillis();
		
		for(; i < temp_num + 5000; i++){
			addCustomerResult = db.addCustomer (addCustomerFirstName + i, addCustomerLastName + i, "email" + i + "@test.com", addCustomerMemberLevelId, addCustomerTotalPoints);
		}
		
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		long averageTime = totalTime / Math.abs(temp_num - i);
		
		System.out.println("------------------------------------------------------------");
		System.out.println("Added: " + Math.abs(temp_num - i) + " Test customers.");
		System.out.println("Total before number of Rows: " + temp_num);
		System.out.println("Total after  number of Rows: " + i);
		System.out.println("Total run time: " + totalTime + " ms");
		System.out.println("Average time per insert: " + averageTime + " ms");
		System.out.println();
	}
	
	public void TestAddPurchase(){
		int numPurchases;
		int addPurchaseCustomerId;
		int addPurchaseStoreId;
		Date addPurchasePurchaseTime = Date.valueOf("2019-07-23");
		List <Integer> coffeeIds = new ArrayList<>();
		List <Integer> purchaseQuantities = new ArrayList<>();
		List <Integer> redeemQuantities = new ArrayList<>();
		int addPurchaseResult;
		
		int temp_num = 0;

		try{
			Statement s = connection.createStatement();
			String sql = "select max(purchase_id) as purchase_id from purchase;";
			ResultSet res = s.executeQuery(sql);
			if(res.next()){
				temp_num = res.getInt("purchase_id");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		int i = temp_num;
		
		numPurchases = 1;
		coffeeIds.add(1);
		purchaseQuantities.add(1);
		redeemQuantities.add(0);
		
		addPurchaseCustomerId = 21;
		addPurchaseStoreId = 3;
		
		long startTime = System.currentTimeMillis();
		
		for(; i < temp_num + 500; i++){	
			addPurchaseResult = db.addPurchase(addPurchaseCustomerId, addPurchaseStoreId, addPurchasePurchaseTime, coffeeIds, purchaseQuantities, redeemQuantities);
		}
		
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		long averageTime = totalTime / Math.abs(temp_num - i);
		
		System.out.println("------------------------------------------------------------");
		System.out.println("Added: " + Math.abs(temp_num - i) + " Test purchases.");
		System.out.println("Total before number of Rows: " + temp_num);
		System.out.println("Total after  number of Rows: " + i);
		System.out.println("Total run time: " + totalTime + " ms");
		System.out.println("Average time per insert: " + averageTime + " ms");
	}
	
	public void TestGetCoffee(){
		long startTime = System.currentTimeMillis();
		
		int i;
		
		for(i = 0; i < 500; i++){
			List <Integer> allCoffees = db.getCoffees();
		}
			
		
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		long averageTime = totalTime / i;
		
		System.out.println("------------------------------------------------------------");
		System.out.println("Ran: " + i + " Test get coffee queries.");
		System.out.println("Total run time: " + totalTime + " ms");
		System.out.println("Average time per insert: " + averageTime + " ms");
	}
	
	public void TestCoffeeKeyWord(){
		long startTime = System.currentTimeMillis();
		String coffeeKeyword1 = "test";
        String coffeeKeyword2 = "coffee";
		
		int i;
		
		for(i = 0; i < 500; i++){
			List <Integer> coffeeKeywords = db.getCoffeesByKeywords (coffeeKeyword1, coffeeKeyword2);
		}
			
		
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		long averageTime = totalTime / i;
		
		System.out.println("------------------------------------------------------------");
		System.out.println("Ran: " + i + " Test get coffee by keyword queries.");
		System.out.println("Total run time: " + totalTime + " ms");
		System.out.println("Average time per insert: " + averageTime + " ms");
	}
	
	public void TestPointsByCustID(){
		int getPointsCustomerId;
		double returnedPoints;
		
		Random rand = new Random();
		
		int temp_num = 0;

		try{
			Statement s = connection.createStatement();
			String sql = "select max(customer_id) as customer_id from customer;";
			ResultSet res = s.executeQuery(sql);
			if(res.next()){
				temp_num = res.getInt("customer_id");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		int k = temp_num - 1;
		int r = 1;
					
		long startTime = System.currentTimeMillis();
		
		int i;
		
		for(i = 0; i < 500; i++){
			r = rand.nextInt(k) + 1;
			returnedPoints = db.getPointsByCustomerId (r);
		}
			
		
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		long averageTime = totalTime / i;
		
		System.out.println("------------------------------------------------------------");
		System.out.println("Ran: " + i + " Test get points by customer id queries.");
		System.out.println("Total run time: " + totalTime + " ms");
		System.out.println("Average time per insert: " + averageTime + " ms");
	}
	
	public void TestGetTopStores(){
		int topStores;
		int storeMonths = 2;
		
		Random rand = new Random();
		int rStore = 1;
		
		int temp_num = 0;

		try{
			Statement s = connection.createStatement();
			String sql = "select max(store_id) as store_id from store;";
			ResultSet res = s.executeQuery(sql);
			if(res.next()){
				temp_num = res.getInt("store_id");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		int k = temp_num / 2;
		
		long startTime = System.currentTimeMillis();
		
		int i;
		
		for(i = 0; i < 500; i++){
			rStore = rand.nextInt(k) + 1;
			List<Integer> stores = db.getTopKStoresInPastXMonth(rStore, storeMonths);
		}
			
		
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		long averageTime = totalTime / i;
		
		System.out.println("------------------------------------------------------------");
		System.out.println("Ran: " + i + " Test get top stores queries.");
		System.out.println("Total run time: " + totalTime + " ms");
		System.out.println("Average time per insert: " + averageTime + " ms");
	}
	
	public void TestGetTopCustomers(){
		int topCust;
		int custMonths = 2;
		
		Random rand = new Random();
		int rCust = 1;
		
		int temp_num = 0;

		try{
			Statement s = connection.createStatement();
			String sql = "select max(customer_id) as customer_id from customer;";
			ResultSet res = s.executeQuery(sql);
			if(res.next()){
				temp_num = res.getInt("customer_id");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		int k = temp_num / 2;
		
		long startTime = System.currentTimeMillis();
		
		int i;
		
		for(i = 0; i < 500; i++){
			rCust = rand.nextInt(k) + 1;
			List<Integer> customers = db.getTopKStoresInPastXMonth(rCust, custMonths);
		}
			
		
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		long averageTime = totalTime / i;
		
		System.out.println("------------------------------------------------------------");
		System.out.println("Ran: " + i + " Test get top customers queries.");
		System.out.println("Total run time: " + totalTime + " ms");
		System.out.println("Average time per insert: " + averageTime + " ms");
	}
}