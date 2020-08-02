import java.sql.*;
public class Database
{
	public static Connection connection;
	public static Statement statement;
	static
	{
		try
		{
			connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Institute_Management_System","root","admin");
			statement=connection.createStatement();
		}
		catch(SQLException e)
		{
			try
			{
			connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306","root","admin");
			statement=connection.createStatement();
			statement.executeUpdate("create database Institute_Management_System");
			connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Institute_Management_System","root","admin");
			statement=connection.createStatement();
			statement.executeUpdate("create table Login(Username varchar(30),Password varchar(30),Permissions varchar(10),LastLoginDate tinyint,LastLoginTime tinyint)");
				statement.executeUpdate("insert into Login values('Nishant','nishant','Admin',null,null)");
			statement.executeUpdate("insert into Login values('Deepanshu','deepanshu','User',null,null)");
			statement.executeUpdate("insert into Login values('Abhishek','abhishek','User',null,null)");

			statement.executeUpdate("create table Courses(Course_Id tinyint,Course varchar(40),Fee smallint)");
			statement.executeUpdate("insert into Courses values(1,'C',3700)");
			statement.executeUpdate("insert into Courses values(2,'C++',4500)");
			statement.executeUpdate("insert into Courses values(3,'Core Java',5500)");
			statement.executeUpdate("insert into Courses values(4,'Adv. Java',8000)");
			statement.executeUpdate("insert into Courses values(5,'DSA',4000)");
			statement.executeUpdate("create table Batches(Batch_No tinyint,Course_Id tinyint,BatchTime Time,BatchDate Date)");
			statement.executeUpdate("insert into Batches values(1,1,'10:20:00','2019-12-12')");
			statement.executeUpdate("insert into Batches values(1,4,'11:00:00','2019-10-04')");
			statement.executeUpdate("create table Students(AddBy varchar(70),Dated Date,Timed Time,Reg_No smallint(6),Name varchar(20),Contact varchar(20),Course_Id tinyint,Batch_No tinyint,Comments varchar(100))");
			statement.executeUpdate("insert into Students values('Deepanshu','2019-10-01','12:12:05',1,'Gaurav','9636707431',1,1,'hello')");
			statement.executeUpdate("create table Queries(QNo tinyint,StudentName varchar(40),Contact varchar(20),Course varchar(30),Commments varchar(100),AddBy varchar(50))");
			statement.executeUpdate("insert into Queries values(1,'amit','912442','Adv. Java','hello amit','Deepanshujain@gmail.com')");
			}
			catch(SQLException E)
			{
				System.out.println("Error Occur");
			}
		}
	}
}
