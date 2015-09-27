package com.training.topaz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {


	private static final String JDBC_DRIVER="com.mysql.jdbc.Driver";  
	private static final String DB_URL="jdbc:mysql://localhost:3306/bookshop";
	private static final String USER = "root";
	private static final String PASS = "root";
	Connection conn;

	public DatabaseConnection (){

		try{
			// Register JDBC driver
			Class.forName(JDBC_DRIVER);

			// Open a connection
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		}catch(SQLException se){

			se.printStackTrace();

		}catch(Exception e){

			e.printStackTrace();

		}
	}


	/*Admin related*/
	public boolean validateAdmin(String username,String password) throws SQLException{
		PreparedStatement psCheck = conn.prepareStatement("select username,password from admin where username=? and password=?");
		psCheck.setString(1, username);
		psCheck.setString(2,password);
		ResultSet rs = psCheck.executeQuery();

		if(rs.next()){

			return true;
		}
		else {
			return false;
		}
	}

	//getting unapproved list of customers
	public List<Customer> getApprovalRequests() throws SQLException{
		PreparedStatement psCheck = conn.prepareStatement("select * from customer where approval_flag=0");
		ResultSet rs = psCheck.executeQuery();
		List<Customer> list=new ArrayList<Customer>();
		while (rs.next()) 
		{
			Customer c=new Customer();
			c.setUsername(rs.getString("username"));
			c.setFname(rs.getString("fname"));
			c.setLname(rs.getString("lname"));
			c.setEmail(rs.getString("email"));
			c.setMobile(rs.getString("mobile"));
			c.setAddress(rs.getString("address"));
			c.setFlag(rs.getInt("approval_flag"));

			list.add(c);
		}
		return list;
	}

	//giving approval
	public void setApprovalRequest(String username) throws SQLException {
		// TODO Auto-generated method stub
		Statement st=conn.createStatement();
		st.executeUpdate("update customer set approval_flag = 1 where username='"+username+"'");
		System.out.println(username+"approved");
	}

	//rejecting
	public void setRejectRequest(String username) throws SQLException {
		// TODO Auto-generated method stub
		Statement st=conn.createStatement();
		st.executeUpdate("delete from customer where username='"+username+"'");
		System.out.println(username+"rejected");
	}




	/*Customr Related*/
	public Customer loginValidate(String username,String password) 
	{
		String qs = "select * from customer where username=? and password=? and approval_flag=1";

		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(qs);
			ps.setString(1,username);
			ps.setString(2,password);

			ResultSet rs = ps.executeQuery();

			Customer customer = new Customer();

			if(rs.next()){
				customer.setUsername(rs.getString("username"));
				customer.setPassword(rs.getString("password"));
				customer.setFname(rs.getString("fname"));
				customer.setLname(rs.getString("lname"));
				customer.setEmail(rs.getString("email"));
				customer.setMobile(rs.getString("mobile"));
				customer.setAddress(rs.getString("address"));
				customer.setFlag(rs.getInt("approval_flag"));
				return customer;
			}

			else {
				return null;	         
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void register(Customer customer) throws SQLException {

		PreparedStatement preparedStatementInsert = null;
		try{

			String qry="insert into customer (username,password,fname,lname,email,mobile,address,approval_flag) values(?,?,?,?,?,?,?,0)";
			preparedStatementInsert = conn.prepareStatement(qry);
			preparedStatementInsert.setString(1, customer.getUsername());
			preparedStatementInsert.setString(2, customer.getPassword());
			preparedStatementInsert.setString(3, customer.getFname());
			preparedStatementInsert.setString(4, customer.getLname());
			preparedStatementInsert.setString(5, customer.getEmail());
			preparedStatementInsert.setString(6, customer.getMobile());
			preparedStatementInsert.setString(7, customer.getAddress());
			preparedStatementInsert.executeUpdate();
			System.out.println("Done!");

		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if (preparedStatementInsert != null) {
				preparedStatementInsert.close();
			}
			if (conn != null) {
				conn.close();
			}
		}

	}

	public boolean signupValidation(String username) throws SQLException {


		PreparedStatement psCheck= conn.prepareStatement("select username from customer where username=?");
		psCheck.setString(1, username);
		ResultSet rs = psCheck.executeQuery();
		if(rs.next()){
			System.out.println("username exists");
			return false;
		}
		else{

			System.out.println("username doesn't exists");
			return true;
		}

	}

	public boolean validateEmail(String email) throws SQLException{
		System.out.println("Enter validationEmail");
		PreparedStatement psCheck = conn.prepareStatement("select email from customer where email=?");
		psCheck.setString(1, email);
		ResultSet rs = psCheck.executeQuery();
		if(rs.next()){
			System.out.println("email exists");
			return false;
		}
		else {
			System.out.println("email doesnt exist");
			return true;
		}
	}


	//getting item from database to item.jsp
	public Item getItem(String title) {

		// TODO Auto-generated method stub

		try {
			PreparedStatement pst=conn.prepareStatement("select * from items where title=?");
			pst.setString(1, title);
			ResultSet rs=pst.executeQuery();
			Item item=new Item();
			while (rs.next()) 
			{
				item.setItemNo(rs.getInt("item_no"));
				item.setTitle(rs.getString("title"));
				item.setAuthor(rs.getString("author"));
				item.setPublication(rs.getString("publication"));
				item.setCategory(rs.getString("category"));
				item.setDescription(rs.getString("description"));
				item.setCost(rs.getDouble("cost"));
				item.setStock(rs.getInt("stock"));
			}
			rs.close();
			pst.close();
			conn.close();
			return item;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	//getting item by ID
	public Item getItemByID(int id) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pst=conn.prepareStatement("select * from items where item_no=?");
			pst.setInt(1, id);
			ResultSet rs=pst.executeQuery();
			Item item=new Item();
			while (rs.next()) 
			{
				item.setItemNo(rs.getInt("item_no"));
				item.setTitle(rs.getString("title"));
				item.setAuthor(rs.getString("author"));
				item.setPublication(rs.getString("publication"));
				item.setCategory(rs.getString("category"));
				item.setDescription(rs.getString("description"));
				item.setCost(rs.getDouble("cost"));
				item.setStock(rs.getInt("stock"));
			}
			rs.close();
			pst.close();

			return item;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	//getting items by category
	public List<Item> getResultsBycategory(String category,int offset) {
		// TODO Auto-generated method stub

		try {
			PreparedStatement pst=conn.prepareStatement("select * from items where category=? limit "+ offset +",30");
			pst.setString(1, category);
			ResultSet rs=pst.executeQuery();
			List<Item> listitems=new ArrayList<Item>();
			while (rs.next()) 
			{
				Item item=new Item();
				item.setItemNo(rs.getInt("item_no"));
				item.setTitle(rs.getString("title"));
				item.setAuthor(rs.getString("author"));
				item.setPublication(rs.getString("publication"));
				item.setCategory(rs.getString("category"));
				item.setDescription(rs.getString("description"));
				item.setCost(rs.getDouble("cost"));
				item.setStock(rs.getInt("stock"));
				listitems.add(item);
			}
			rs.close();
			pst.close();

			return listitems;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	//adding to cart
	public void addToCart(String username, int itemid, int qty,double cost) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pst=conn.prepareStatement("insert into cart(username,item_no,quantity,price) values(?,?,?,?)");
			pst.setString(1, username);
			pst.setInt(2, itemid);
			pst.setInt(3, qty);
			pst.setDouble(4, cost);
			pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//checking if already in cart
	public int checkSameItem(int itemID,String username){
		try {
			PreparedStatement pst=conn.prepareStatement("select * from cart where username=? and item_no=?");
			pst.setString(1, username);
			pst.setInt(2, itemID);
			ResultSet rst=pst.executeQuery();
			if(rst.next())
			{
				int qty=rst.getInt("quantity");
				return qty;
			}
			else
			{
				return -1;
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return -1;

	}

	//updating in cart
	public void updateCart(int itemno,String username,int qty) throws SQLException
	{
		Statement st=conn.createStatement();
		st.executeUpdate("update cart set quantity="+qty+" where username='"+username+"' and item_no="+itemno+"");

		/*	
			PreparedStatement pst=conn.prepareStatement("update cart set quantity=? where username=? and cart_id=?");
			pst.setInt(1, qty);
			pst.setString(2, username);
			pst.setInt(3, itemno);
			pst.executeUpdate();*/

	}

	//removing from cart
	public void removeFromCart(int cartid) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pst=conn.prepareStatement("delete from cart where cart_id=?");
			pst.setInt(1, cartid);
			pst.executeUpdate();
			System.out.println("cart id "+cartid+" deleted");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//getting items from cart to cart page
	public List<Cart> getCartItems(String username) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pst=conn.prepareStatement("select * from cart where username=?");
			pst.setString(1, username);
			ResultSet rs=pst.executeQuery();
			List<Cart> list=new ArrayList<Cart>();
			while (rs.next()) 
			{
				Cart cart=new Cart();
				cart.setCartId(rs.getInt("cart_id"));
				cart.setUsername(rs.getString("username"));
				cart.setItemNo(rs.getInt("item_no"));
				cart.setQuantity(rs.getInt("quantity"));
				cart.setPrice(rs.getDouble("price"));
				System.out.println(cart.getItemNo());
				list.add(cart);
			}
			rs.close();
			pst.close();

			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	//whole order transaction
	public boolean orderTransaction(String username, double total, String date, List<Cart> listCartItems){

	
		try {
			conn.setAutoCommit(false);
			if(!updatestock(listCartItems))
			{
				conn.rollback();
				conn.setAutoCommit(true);
				return false;
			}
			else
			{
				emptyCart(username);
				addOrder(username, total, date);
				int orderid=getOrderId(username);
				addOrderLine(orderid,listCartItems);
				conn.commit();
				conn.setAutoCommit(true);
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	//getting stock
	private boolean updatestock(List<Cart> listCartItems) {
		// TODO Auto-generated method stub
		PreparedStatement pst,pst2;
		int oldStock,newStock;
		try {
			pst = conn.prepareStatement("select * from items where item_no=? FOR UPDATE");
			pst2 = conn.prepareStatement("update items set stock=? where item_no=?");
			ResultSet rst=null;
			for (Cart cart : listCartItems) 
			{
				pst.setInt(1, cart.getItemNo());
				rst=pst.executeQuery();
				rst.next();
				oldStock=rst.getInt("stock");
				
				System.out.println("oldstock "+oldStock);
				newStock=oldStock-cart.getQuantity();
				if(newStock < 0)
					return false;
				//calculating newstock
				
				pst2.setInt(1, newStock);
				pst2.setInt(2, cart.getItemNo());
				pst2.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}


	//empty cart first
	public void emptyCart(String username) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pst=conn.prepareStatement("delete from cart where username=?");
			pst.setString(1, username);
			pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//1) adding new orderId
	public void addOrder(String username, double total, String date) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pst=conn.prepareStatement("insert into bookshop.order(username,total,orderdate)values(?,?,?)");
			pst.setString(1, username);
			pst.setDouble(2, total);
			pst.setString(3, date);
			pst.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//2)getting the orderid
	public int getOrderId(String username){
		try {
			PreparedStatement pst=conn.prepareStatement("select max(order_id) from bookshop.order where username=?");
			pst.setString(1, username);
			ResultSet rs=pst.executeQuery();
			int oid=-1;
			rs.next();
			oid=rs.getInt(1);
			System.out.println("oid is "+oid);
			return oid;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	//adding to orderline
	private void addOrderLine(int orderid, List<Cart> listCartItems) {
		// TODO Auto-generated method stub

		try {
			PreparedStatement pst=conn.prepareStatement("insert into bookshop.orderline(order_id,item_no,quantity,price)values(?,?,?,?)");

			for (Cart cart : listCartItems) {
				pst.setInt(1, orderid);
				pst.setInt(2, cart.getItemNo());
				pst.setInt(3, cart.getQuantity());
				pst.setDouble(4, cart.getPrice());
				pst.executeUpdate();

			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//getting orderid from order
	public List<Order> getOrderHistory(String username) {
		// TODO Auto-generated method stub
		System.out.println("nside getorderhistory");
		try {
			List<Order> listOrder=new ArrayList<Order>();
			List<Item> listItems=new ArrayList<Item>();
			double total=0;
			String date;
			int orderid;
			PreparedStatement pst=conn.prepareStatement("Select * from bookshop.order where username=?");
			pst.setString(1, username);
			ResultSet rs=pst.executeQuery();
			while (rs.next()) 
			{
				Order order=new Order();
				orderid=rs.getInt("order_id");
				date=rs.getString("orderdate");
				total=rs.getDouble("total");
				listItems=getOrderItems(orderid);
				order.setOrderid(orderid);
				order.setDate(date);
				order.setTotal(total);
				order.setListItems(listItems);
				System.out.println(order.getOrderid());
				listOrder.add(order);
			}
			return listOrder;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//getting order items from orderline
	private List<Item> getOrderItems(int orderid)
	{
		PreparedStatement pst2=null,pst3=null;
		List<Item> listItems=new ArrayList<Item>();
		try {
			pst2 = conn.prepareStatement("select * from bookshop.orderline where order_id=?");
			pst3 = conn.prepareStatement("select * from items where item_no=?");
			pst2.setInt(1, orderid);
			ResultSet rs2=pst2.executeQuery();
			while (rs2.next()) 
			{
				
				pst3.setInt(1, rs2.getInt("item_no"));
				ResultSet rs3=pst3.executeQuery();
				while (rs3.next()) 
				{
					Item item=new Item();
					item.setItemNo(rs3.getInt("item_no"));
					item.setTitle(rs3.getString("title"));
					item.setAuthor(rs3.getString("author"));
					item.setPublication(rs3.getString("publication"));
					item.setCategory(rs3.getString("category"));
					item.setDescription(rs3.getString("description"));
					item.setCost(rs3.getDouble("cost"));
					item.setStock(rs3.getInt("stock"));
					listItems.add(item);
				}
				
			}
			return listItems;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return null;
	}

	//searching book in all categories
	public List<Item> getBooksAll(String select1, String text, int offset) {
		// TODO Auto-generated method stub
		PreparedStatement pst;
		try {
			List<Item> listItems=new ArrayList<Item>();
			pst = conn.prepareStatement("select * from items where "+ select1 +" like ? limit "+ offset +",30");
			pst.setString(1, text+"%");
			ResultSet rs=pst.executeQuery();
			while (rs.next()) 
			{
				Item item=new Item();
				item.setItemNo(rs.getInt("item_no"));
				item.setTitle(rs.getString("title"));
				item.setAuthor(rs.getString("author"));
				item.setPublication(rs.getString("publication"));
				item.setCategory(rs.getString("category"));
				item.setDescription(rs.getString("description"));
				item.setCost(rs.getDouble("cost"));
				item.setStock(rs.getInt("stock"));
				System.out.println("item "+item.getTitle());
				listItems.add(item);
			}
			return listItems;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	//admin methods
	
	public ArrayList<String> searchPublication(String category) throws SQLException{
		ArrayList< String>list = new ArrayList<String>();
		String query = "SELECT DISTINCT publication from items" +
				" where category='"+category+"'";
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			list.add(rs.getString(1));	
		}
		return list;
		
	}
	
	public ArrayList<String> searchAuthor(String category,String publication) throws SQLException{
		ArrayList< String>list = new ArrayList<String>();
		System.out.println(":"+publication);
		String query = "SELECT DISTINCT author from items" +
				" where publication='"+publication+"'and category='"+category+"';";
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			list.add(rs.getString(1));	
		}
		return list;
		
	}
	
	public List<Item> search (String category,String publication,String author) throws SQLException{
		List< Item>list = new ArrayList<Item>();
		String query = "SELECT * from items" +
				" where publication='"+publication+"'and category='"+category+"'and author='"+author+"'";
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			
			Item item = new Item();
			item.setTitle(rs.getString("title"));
			item.setAuthor(rs.getString("author"));
			item.setPublication(rs.getString("publication"));
			item.setCategory(rs.getString("category"));
			item.setDescription(rs.getString("description"));
			item.setCost(rs.getDouble("cost"));
			item.setStock(rs.getInt("stock"));
			list.add(item);
		}

		return list;
	}
	
	public boolean updateStock(String category,String pub,String author,int stock,String title) throws SQLException{
		System.out.println(title);
		System.out.println(author);
		System.out.println(stock);
		try{

		String query = "UPDATE items set stock=stock+"+stock+" where title='"+title+"' and author='"+author+"';";
		PreparedStatement ps= conn.prepareStatement(query);
		int update=ps.executeUpdate();
		if(update==0)
			return false;
		else
			return true;
	}catch (SQLException e){
		e.printStackTrace();
	}
		return false;
	}

	//update `bookshop`.`items` set stock=stock+2 where title='Book on Title295' and author='author15';

	public List<Item> searchByStock(int stock) throws SQLException {
		String qry = "select * from items where stock="+ stock+";";
		PreparedStatement ps = conn.prepareStatement(qry);
		List<Item> list = new ArrayList<Item>();
		ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			
			Item item = new Item();
			item.setTitle(rs.getString("title"));
			item.setAuthor(rs.getString("author"));
			item.setPublication(rs.getString("publication"));
			item.setCategory(rs.getString("category"));
			item.setDescription(rs.getString("description"));
			item.setCost(rs.getDouble("cost"));
			item.setStock(rs.getInt("stock"));
			list.add(item);
		}

		return list;
	}
	public boolean updateSearchByStock(int stock,String title) throws SQLException{
		System.out.println(title);

		System.out.println("line413:"+stock);
		try{

		String query = "UPDATE items set stock=stock+"+stock+" where title='"+title+"';";
		PreparedStatement ps= conn.prepareStatement(query);
		int update=ps.executeUpdate();
		if(update==0)
			return false;
		else
			return true;
	}catch (SQLException e){
		e.printStackTrace();
	}
		return false;
	}
	
	public List<Customer> getCustomerList() throws SQLException{
		String query = "SELECT * FROM customer WHERE approval_flag=1;";
		List<Customer> list = new ArrayList<Customer>();
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs = ps.executeQuery();

		while(rs.next())
		{
				Customer customer=new Customer();
				customer.setUsername(rs.getString("username"));
				customer.setPassword(rs.getString("password"));
				customer.setFname(rs.getString("fname"));
				customer.setLname(rs.getString("lname"));
				customer.setEmail(rs.getString("email"));
				customer.setMobile(rs.getString("mobile"));
				customer.setAddress(rs.getString("address"));
				customer.setFlag(rs.getInt("approval_flag"));
				list.add(customer);
			
		}
		return list;
	}

	//adding item to db by admin
	public boolean addItems(Item item) throws SQLException {
		
		String qry ="insert into items(title,author,publication,category,description,cost,stock) values(?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(qry);
		ps.setString(1, item.getTitle());
		ps.setString(2, item.getAuthor());
		ps.setString(3, item.getPublication());
		ps.setString(4, item.getCategory());
		ps.setString(5, item.getDescription());
		ps.setDouble(6, item.getCost());
		ps.setInt(7, item.getStock());
		int a = ps.executeUpdate();
		if(a!=0) {
			return true;
		}
		else {
		return false;
		}
	}











}




