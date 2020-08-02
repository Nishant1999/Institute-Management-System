import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.*;
class ViewStudentForm
{
	JDialog viewentry;
	JScrollPane scrollpane;
	String coulmname[]={"Reg No","Name","Contact","Course","Batch Start","Batch Timing","Batch No","Add By"};
	Object o[][]={};
	DefaultTableModel dtm;
	JTable jt;
	ViewStudentForm(JFrame frame)
	{
		System.out.println("hello student form");
		viewentry=new JDialog(frame,"View Entry Records",true);
		viewentry.setSize(640,450);
		viewentry.setLocation(170,150);
		viewentry.setLocation(170,150);
	    
		
		dtm=new DefaultTableModel(o,coulmname);
		jt=new JTable(dtm);
		scrollpane=new JScrollPane(jt);
		scrollpane.setFont(new Font("Arial",Font.BOLD,12));
		jt.setFont(new Font("Arial",Font.BOLD,12));
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		
		try
		{
		System.out.println("opertion1");
		ResultSet rsvsf;
		/*rsvsf=Database.statement.executeQuery("Select Course from Courses where Course_Id=(select Course_Id from Students)");
		int i=0,j=3;
		while(rsvsf.next())
		{
			dtm.setValueAt(rsvsf.getString("Course"),i,j);
			i++;
		}*/
		
		rsvsf=Database.statement.executeQuery("Select * from Students");
		System.out.println("opertion2");
		while(rsvsf.next())
		{
			System.out.println(rsvsf.getString("Reg_No"));
			String row[]={rsvsf.getString("Reg_No"),rsvsf.getString("Name"),rsvsf.getString("Contact"),rsvsf.getString("Course_Id"),rsvsf.getString("Dated"),rsvsf.getString("Timed"),rsvsf.getString("Batch_No"),rsvsf.getString("AddBy")};
			dtm.addRow(row);
		}
		}
		catch(SQLException e)		
		{
			System.out.println(e);
		}
		viewentry.add(scrollpane);
		viewentry.setVisible(true);
	}
}