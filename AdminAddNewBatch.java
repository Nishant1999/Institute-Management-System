import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class AdminAddNewBatch
{
	JDialog addnewbatch;
	JLabel coursenamelabel,batchtimelabel,batchdatelabel;
	JComboBox coursecombobox,batchtimecombobox,batchtimeampmcombobox,batchdateyearcombobox,batchdatemonthcombobox,batchdatedaycombobox;
	JButton savebatchbutton,cancelbutton;
	Object time[]={"01:00","02:00","03:00","04:00","05:00","06:00","07:00","08:00","09:00","10:00","11:00","12:00"};
	Object midday[]={"AM","PM"};
	Object year[]={"2019","2020","2021","2022"};
	Object month[]={"1","2","3","4","5","6","7","8","9","10","11","12"};
	Object day[]={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"};
	AdminAddNewBatch(JFrame frame)
	{
		addnewbatch=new JDialog(frame,"Add New Batch",true);
		addnewbatch.setSize(640,450);
		addnewbatch.setLocation(170,150);
		addnewbatch.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.insets=new Insets(5,5,5,5);
		
		coursenamelabel=new JLabel("Course");
		gbc.gridx=0;
		gbc.gridy=0;
		addnewbatch.add(coursenamelabel,gbc);
		
		coursecombobox=new JComboBox();
		try
		{
		ResultSet rs=Database.statement.executeQuery("select * from Courses");
		while(rs.next())
		{
			//System.out.println(rs.getString("Course"));
			coursecombobox.addItem(rs.getString("Course"));
		}
		}
		catch(SQLException S)
		{
			System.out.println("Error in new batches"+S);
		}
		gbc.fill=GridBagConstraints.BOTH;
		gbc.gridx=1;
		gbc.gridy=0;
		addnewbatch.add(coursecombobox,gbc);
		
		batchtimelabel=new JLabel("Batch Time");
		gbc.gridx=0;
		gbc.gridy=1;
		addnewbatch.add(batchtimelabel,gbc);
		
		batchtimecombobox=new JComboBox(time);
		gbc.gridx=1;
		gbc.gridy=1;
		addnewbatch.add(batchtimecombobox,gbc);
		
		batchtimeampmcombobox=new JComboBox(midday);
		gbc.gridx=2;
		gbc.gridy=1;
		addnewbatch.add(batchtimeampmcombobox,gbc);
		
		batchdatelabel=new JLabel("Batch Date");
		gbc.gridx=0;
		gbc.gridy=2;
		addnewbatch.add(batchdatelabel,gbc);
		
		batchdateyearcombobox=new JComboBox(year);
		gbc.gridx=1;
		gbc.gridy=2;
		addnewbatch.add(batchdateyearcombobox,gbc);
		
		batchdatemonthcombobox=new JComboBox(month);
		gbc.gridx=2;
		gbc.gridy=2;
		addnewbatch.add(batchdatemonthcombobox,gbc);
		
		batchdatedaycombobox=new JComboBox(day);
		gbc.gridx=3;
		gbc.gridy=2;
		addnewbatch.add(batchdatedaycombobox,gbc);
		
		savebatchbutton=new JButton("Save");
		gbc.gridx=0;
		gbc.gridy=3;
		addnewbatch.add(savebatchbutton,gbc);
		
		cancelbutton=new JButton("Cancel");
		gbc.gridx=2;
		gbc.gridy=3;
		addnewbatch.add(cancelbutton,gbc);
		
		savebatchbutton.addActionListener((M)->{
											String tempcoursename=String.valueOf(coursecombobox.getSelectedItem());
											String tempbatchtime=String.valueOf(batchtimecombobox.getSelectedItem());
											String tempbatchmidday=String.valueOf(batchtimeampmcombobox.getSelectedItem());
											String tempbatchyear=String.valueOf(batchdateyearcombobox.getSelectedItem());
											String tempbatchmonth=String.valueOf(batchdatemonthcombobox.getSelectedItem());
											String tempbatchday=String.valueOf(batchdatedaycombobox.getSelectedItem());
											int tempbatchno=1;
											int tempcourseid=0;
											Time ti=Time.valueOf(tempbatchtime+":00");
											Date dt=Date.valueOf(tempbatchyear+"-"+tempbatchmonth+"-"+tempbatchday);
											try
											{
											ResultSet rs;
											rs=Database.statement.executeQuery("Select * from Batches where Course_Id=(Select Course_Id from Courses where Course='"+tempcoursename+"')");
											while(rs.next())
											{
												tempbatchno=rs.getInt("Batch_No");
											}
											rs=Database.statement.executeQuery("Select Course_Id from Courses where Course='"+tempcoursename+"'");
											while(rs.next())
											{
												tempcourseid=rs.getInt("Course_Id");
											}
											PreparedStatement pstmt=Database.connection.prepareStatement("insert into Batches(Batch_No,Course_Id,BatchTime,BatchDate) values(?,?,?,?)");
											pstmt.setInt(1,tempbatchno);
											pstmt.setInt(2,tempcourseid);
											pstmt.setTime(3,ti);
											pstmt.setDate(4,dt);
											pstmt.execute();
											pstmt.close();
											JOptionPane.showMessageDialog(addnewbatch,"Batch added Successfully","Batch Information",JOptionPane.INFORMATION_MESSAGE);
											}
											catch(SQLException A)
											{
												System.out.println("Error in adding new batch"+A);
											}
										});
		
		cancelbutton.addActionListener((E)->addnewbatch.setVisible(false));
		
		addnewbatch.setVisible(true);
	}
}