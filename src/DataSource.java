import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

enum SORT{
	BYNAME,BYROLL,BYTOTAL
}
public class DataSource {
	private  Connection conn=null;
	private  Statement statement=null;
	private  ResultSet results=null;
	private  PreparedStatement preStatement=null;
	
	private  String tableName="student";
	private  String column1="RollNo";
	private  String column2="Name";
	private  String column3="Report";
	private  String column4="Letter";
	private  String column5="Synopsis";
	private  String column6="Progress1";
	private  String column7="Progress2";
	private  String column8="Progress3";
	public  void connect() {
		
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:"+Data.getFileName());
			statement = conn.createStatement();
			String sql="CREATE TABLE IF NOT EXISTS "+tableName +" ("+
					column1+" integer PRIMARY KEY, "+column2+" text, "+column3+" integer, "+column4+" integer, "+column5+" integer, "+column6+" integer, "+column7+" integer, "+column8+" integer )";
					statement.execute(sql);

		} catch (SQLException e2) {
			System.out.println(e2.getMessage());
			e2.printStackTrace();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	public boolean  createNewDBFile(String fileName) {
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:"+fileName);
			statement = conn.createStatement();
			String sql="CREATE TABLE IF NOT EXISTS "+tableName +" ("+
					column1+" integer PRIMARY KEY, "+column2+" text, "+column3+" integer, "+column4+" integer, "+column5+" integer, "+column6+" integer, "+column7+" integer, "+column8+" integer )";
					statement.execute(sql);

		} catch (SQLException e2) {
			System.out.println(e2.getMessage());
			e2.printStackTrace();
			return false;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public  void close() {
		try {
			if(statement!=null) {
				statement.close();
			}
			if(conn!=null) {
				conn.close();
			}
		} catch (SQLException e2) {
			System.out.println(e2.getMessage());
			e2.printStackTrace();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

		
	public ArrayList<Student> getData() {
		connect();
	ArrayList<Student> list=new ArrayList<>();
		try {
	statement=conn.createStatement();
	results=statement.executeQuery("SELECT * FROM "+tableName);
	while(results.next()) {
		Student obj=new Student();
		obj.setRoll(results.getInt(1));
		obj.setName(results.getString(2));
		obj.setExt(results.getInt(3));
		obj.setLetter(results.getInt(4));
		obj.setSnop(results.getInt(5));
		obj.setProg1(results.getInt(6));
		obj.setProg2(results.getInt(7));
		obj.setProg3(results.getInt(8));
		list.add(obj);
	}
	return list;
	}
		catch(SQLException ex) {
			return null;
		}
		finally {
			close();
		}
}
	
	
	public ArrayList<Student> searchData(String search) {
		connect();
		
	ArrayList<Student> list=new ArrayList<>();
	
		try {
		
	try{	
		Integer.parseInt(search);
	
		preStatement=conn.prepareStatement("SELECT * FROM student WHERE RollNo LIKE CONCAT('%',?,'%')");
		preStatement.setInt(1,Integer.parseInt(search));
	
		}
	catch(NumberFormatException ex) {
		
		preStatement=conn.prepareStatement("SELECT * FROM student WHERE Name LIKE CONCAT(''%',?,'%'')");
		preStatement.setString(1,search);
	}
	results=preStatement.executeQuery();

	while(results.next()) {
		System.out.print("Hell");
		Student obj=new Student();
		obj.setRoll(results.getInt(1));
		obj.setName(results.getString(2));
		obj.setExt(results.getInt(3));
		obj.setLetter(results.getInt(4));
		obj.setSnop(results.getInt(5));
		obj.setProg1(results.getInt(6));
		obj.setProg2(results.getInt(7));
		obj.setProg3(results.getInt(8));
		list.add(obj);
		}
		}
		catch(SQLException ex) {
			ex.getMessage();
			return null;
			
		}
		catch(Exception e) {System.out.print("ye");
			e.printStackTrace();
			return null;
		}
		finally {
		
			close();
			return list;
		}
}
	public ArrayList<Student> sortData(SORT sort,ORDER order) {
		connect();
	ArrayList<Student> list=new ArrayList<>();
		try {
	statement=conn.createStatement();
	StringBuilder sql=new StringBuilder();
	sql.append("SELECT * FROM "+tableName+ " ORDER BY ");
	if(sort==SORT.BYNAME)
		sql.append(column2);
	else if(sort==SORT.BYROLL)
		sql.append(column1);
	else
		sql.append(column2);
	sql.append(" COLLATE NOCASE ");
	if(order==ORDER.ASC)
	sql.append("ASC");
	else
	sql.append("DESC");
	results=statement.executeQuery(sql.toString());
	while(results.next()) {
		Student obj=new Student();
		obj.setRoll(results.getInt(1));
		obj.setName(results.getString(2));
		obj.setExt(results.getInt(3));
		obj.setLetter(results.getInt(4));
		obj.setSnop(results.getInt(5));
		obj.setProg1(results.getInt(6));
		obj.setProg2(results.getInt(7));
		obj.setProg3(results.getInt(8));
		list.add(obj);
	}
	return list;
	}
		catch(SQLException ex) {
			return null;
		}
		finally {
			close();
		}
}
	public boolean addRow(String roll,String name) {

	connect();
		try (Statement statement =conn.createStatement();) {
			statement.execute("INSERT INTO "+tableName+" VALUES(" + roll + ", '"
					+ name + "' , 0" + ", 0" + ", 0" + ", 0" + ", 0" + ", 0)");
			Data.updateList(getData());
		} catch (SQLException e1) {
			return false;
		}
		finally {
			close();
		}
	
		return true;
	}
	public boolean editMarks(Student s) {
		connect();
		try (Statement statement =conn.createStatement();) {
			String sql="UPDATE "+tableName+
					" SET "+column3+"="+s.getExt()+", "+column4+"="+s.getLetter()+", "+column5+"="+s.getSynop()+", "+column6+"="+s.getProg1()+", "+column7+"="+s.getProg2()+", "+column8+"="+s.getProg3()+" WHERE "+column1+"="+s.getRoll();
			System.out.print(sql);
			statement.execute(sql);
			
			//Data.updateList(getData());
		} catch (SQLException e1) {
			return false;
		}
		finally {
			close();
		}
	
		return true;
	}
}


