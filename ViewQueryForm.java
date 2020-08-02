import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
class ViewQueryForm
{
	JDialog viewquery;
	JScrollPane scrollpane;
	String coulmname[]={"Q No","Student Name","Contact","Course","Comments","Add By"};
	Object o[][]={};
	DefaultTableModel dtm;
	JTable jt;
	JButton exportquery;
	ViewQueryForm(JFrame frame)
	{
		viewquery=new JDialog(frame,"View Query Records",true);
		viewquery.setSize(640,450);
		viewquery.setLocation(170,150);
		viewquery.setLocation(170,150);
		viewquery.setLayout(new BorderLayout());
	    
		
		dtm=new DefaultTableModel(o,coulmname);
		jt=new JTable(dtm);
		scrollpane=new JScrollPane(jt);
		scrollpane.setFont(new Font("Arial",Font.BOLD,12));
		jt.setFont(new Font("Arial",Font.BOLD,12));
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		
		try
		{
		System.out.println("opertion1");
		ResultSet rsvqf=Database.statement.executeQuery("Select * from Queries");
		System.out.println("opertion2");
		while(rsvqf.next())
		{
			System.out.println(rsvqf.getString("QNo"));
			String row[]={rsvqf.getString("QNo"),rsvqf.getString("StudentName"),rsvqf.getString("Contact"),rsvqf.getString("Course"),rsvqf.getString("Commments"),rsvqf.getString("AddBy")};
			dtm.addRow(row);
		}
		}
		catch(SQLException e)		
		{
			System.out.println(e);
		}
		viewquery.add(scrollpane,BorderLayout.NORTH);
		
		exportquery=new JButton("Export Query");
		
		exportquery.addActionListener(new ActionListener()
									{
										public void actionPerformed(ActionEvent e)
										{
											try
											{
											ResultSet rseq;
											int tempeqno=0;
											int tempecid=0;
											int tempdeleteqno=0;
											int tempbatchno=0;
											String temp="";
											Date dt=new Date(System.currentTimeMillis());
											Time ti=new Time(System.currentTimeMillis());
											rseq=Database.statement.executeQuery("Select * from Students");
											while(rseq.next())
											{
												tempeqno=rseq.getInt("Reg_No");
											}
											rseq=Database.statement.executeQuery("Select Course from Queries");
											while(rseq.next())
											{
												temp=rseq.getString("Course");
											}
											System.out.println("Select Course_Id from Courses where Course='"+temp+"'");
											rseq=Database.statement.executeQuery("Select Course_Id from Courses where Course='"+temp+"'");
											while(rseq.next())
											{
												tempecid=rseq.getInt("Course_Id");
												
											}
											System.out.println("Select Batch_No from Batches where Course_Id=(Select Course_Id from Courses where Course='"+temp+"')");
											rseq=Database.statement.executeQuery("Select Batch_No from Batches where Course_Id=(Select Course_Id from Courses where Course='"+temp+"')");
											while(rseq.next())
											{
												tempbatchno=rseq.getInt("Batch_No");
											}
											rseq=Database.statement.executeQuery("Select * from Queries");
											while(rseq.next())
											{
												PreparedStatement pstmt=Database.connection.prepareStatement("insert into Students(AddBy,Dated,Timed,Reg_No,Name,Contact,Course_Id,Batch_No,Comments) values(?,?,?,?,?,?,?,?,?)");
												pstmt.setString(1,rseq.getString("AddBy"));
												pstmt.setDate(2,dt);
												pstmt.setTime(3,ti);
												pstmt.setInt(4,tempeqno+1);
												pstmt.setString(5,rseq.getString("StudentName"));
												pstmt.setString(6,rseq.getString("Contact"));
												pstmt.setInt(7,tempecid);
												pstmt.setInt(8,tempbatchno);
												pstmt.setString(9,rseq.getString("Commments"));
												pstmt.execute();
												pstmt.close();
												tempdeleteqno=rseq.getInt("QNo");
											}
											//dtm.removeRow(tempdeleteqno);
											}
											catch(SQLException E)
											{
												System.out.println(E);
											}
										}
									});
		
		viewquery.add(exportquery,BorderLayout.SOUTH);
		
		viewquery.setVisible(true);
	}
}