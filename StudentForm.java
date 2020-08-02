import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class StudentForm
{
	StudentForm(JFrame frame)
	{
		try
		{
		JDialog form=new JDialog(frame,"New Entry",true);
		form.setSize(640,450);
		form.setLocation(170,150);
		form.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.insets=new Insets(2,5,2,5);
		
		JLabel date1,studentname,contact,course,batch,remarks,date2,time;
		JTextField studentnametextfield,contacttextfield,date1textfield,date2textfield,timetextfield;
		JComboBox coursecombobox,batchcombobox;
		JTextArea remarkstextarea;
		JButton savebutton,cancelbutton;
		
		date1=new JLabel("Date");
		gbc.anchor=GridBagConstraints.EAST;
		gbc.gridx=1;
		gbc.gridy=0;
		form.add(date1,gbc);
		
		date1textfield=new JTextField("10.9.2019");
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=2;
		gbc.gridy=0;
		form.add(date1textfield,gbc);
		
		studentname=new JLabel("Student Name");
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=0;
		gbc.gridy=1;
		form.add(studentname,gbc);
		
		studentnametextfield=new JTextField(10);
		gbc.gridx=1;
		gbc.gridy=1;
		form.add(studentnametextfield,gbc);
		
		contact=new JLabel("Contact");
		gbc.gridx=0;
		gbc.gridy=2;
		form.add(contact,gbc);
		
		contacttextfield=new JTextField(10);
		gbc.gridx=1;
		gbc.gridy=2;
		form.add(contacttextfield,gbc);
		
		course=new JLabel("Course");
		gbc.gridx=0;
		gbc.gridy=3;
		form.add(course,gbc);
		
		coursecombobox=new JComboBox();
		ResultSet rs=Database.statement.executeQuery("select * from Courses");
		while(rs.next())
		{
			//System.out.println(rs.getString("Course"));
			coursecombobox.addItem(rs.getString("Course"));
		}
		gbc.fill=GridBagConstraints.BOTH;
		gbc.gridx=1;
		gbc.gridy=3;
		gbc.gridwidth=1;
		form.add(coursecombobox,gbc);
		
		date2=new JLabel("Date");
		gbc.gridx=5;
		gbc.gridy=3;
		form.add(date2,gbc);
		
		time=new JLabel("Time");
		gbc.gridx=10;
		gbc.gridy=3;
		form.add(time,gbc);
		
		batch=new JLabel("Batch");
		gbc.gridx=0;
		gbc.gridy=4;
		form.add(batch,gbc);

		ResultSet rsb=Database.statement.executeQuery("Select * from Batches where Course_Id=(Select Course_Id from Courses where course='"+String.valueOf(coursecombobox.getSelectedItem())+"')");
		batchcombobox=new JComboBox();
		while(rsb.next())
		{
			System.out.println(rsb.getString("Batch_No")+rsb.getString("BatchTime")+String.valueOf(coursecombobox.getSelectedItem())+rsb.getString("BatchDate"));
			batchcombobox.addItem(rsb.getString("Batch_No")+rsb.getString("BatchTime")+String.valueOf(coursecombobox.getSelectedItem())+rsb.getString("BatchDate"));
		}
		gbc.gridx=1;
		gbc.gridy=4;
		gbc.gridwidth=4;
		form.add(batchcombobox,gbc);
		
		date2textfield=new JTextField(5);
		gbc.gridx=5;
		gbc.gridy=4;
		form.add(date2textfield,gbc);
		
		timetextfield=new JTextField(5);
		gbc.gridx=10;
		gbc.gridy=4;
		form.add(timetextfield,gbc);
		
		remarks=new JLabel("Remarks");
		//gbc.anchor=GridBagConstraints.NORTHWEST;
		gbc.gridx=0;
		gbc.gridy=5;
		form.add(remarks,gbc);
		
		remarkstextarea=new JTextArea();
		gbc.anchor=GridBagConstraints.WEST;
		remarkstextarea.setRows(5);
		gbc.gridx=1;
		gbc.gridy=5;
		JScrollPane scrollpane=new JScrollPane(remarkstextarea);
		form.add(scrollpane,gbc);
		
		savebutton=new JButton("Save");
		gbc.anchor=GridBagConstraints.WEST;
		gbc.fill=GridBagConstraints.NONE;
		gbc.gridx=0;
		gbc.gridy=6;
		form.add(savebutton,gbc);
		
		savebutton.addActionListener((e)->{System.out.println("hello student operation");
											int registertemp=0;
											int coursetemp=0;
											int batchnotemp=0;
											try
											{
												ResultSet rss;
												rss=Database.statement.executeQuery("Select * from Students");
												while(rss.next())
												{
													registertemp=rss.getInt("Reg_No");
												}
												rss=Database.statement.executeQuery("Select Course_Id from Courses where course='"+coursecombobox.getSelectedItem()+"'");
			
												while(rss.next())
												{
													coursetemp=rss.getInt("Course_Id");
												}
												rss=Database.statement.executeQuery("Select Batch_No from Batches where Course_Id=(Select Course_Id from Courses where course='"+coursecombobox.getSelectedItem()+"')");
												while(rss.next())
												{
												
													batchnotemp=rss.getInt("Batch_No");
												}
												System.out.println("hello abhishek");
												String studenttemp=studentnametextfield.getText();
												String contacttemp=contacttextfield.getText();
												Date dt=new Date(System.currentTimeMillis());
												Time ti=new Time(System.currentTimeMillis());
												System.out.println(studenttemp!=null || studenttemp.length()!=0);
												//if(!(studenttemp!=null ||  studenttemp.length()!=0))
												if((studenttemp==null ||  studenttemp.length()==0))
												{
													JOptionPane.showMessageDialog(form,"Student details are not proper","Incomplete",JOptionPane.ERROR_MESSAGE);
												}
												else
												{
												/*if((studenttemp>="A" || studenttemp>="a") && (studenttemp<="Z" || studenttemp<="z"))
												{
													if(contacttemp>="0" && contacttemp<="9")
													{*/
														PreparedStatement pstmt=Database.connection.prepareStatement("insert into Students(AddBy,Dated,Timed,Reg_No,Name,Contact,Course_Id,Batch_No,Comments) values(?,?,?,?,?,?,?,?,?)");
														pstmt.setString(1,LoginWindow.usernametextfield.getText());
														pstmt.setDate(2,dt);
														pstmt.setTime(3,ti);
														pstmt.setInt(4,registertemp+1);
														pstmt.setString(5,studenttemp);
														pstmt.setString(6,contacttemp);
														pstmt.setInt(7,coursetemp);
														pstmt.setInt(8,batchnotemp);
														pstmt.setString(9,remarkstextarea.getText());
														pstmt.execute();
														pstmt.close();
														studentnametextfield.setText("");
														contacttextfield.setText("");
														remarkstextarea.setText("");
														System.out.println("Added successfully");
														int regno=registertemp+1;
													//}
												//}
												//else
												//{
													//JOptionPane.showMessageDialog(form,JOptionPane.ERROR_MESSAGE);
												//}
												JOptionPane.showMessageDialog(form,"Registration Number is "+regno,"Registration",JOptionPane.INFORMATION_MESSAGE);
												}	
											}
											catch(SQLException Y)
											{
												System.out.println("Exception in student form");
											}});
		
		
		cancelbutton=new JButton("Cancel");
		gbc.gridx=1;
		gbc.gridy=6;
		form.add(cancelbutton,gbc);
		
		cancelbutton.addActionListener((e)->form.setVisible(false));
		
		form.setVisible(true);
		}
		catch(SQLException E)
		{
			System.out.println("Error in courses table");
		}
	}
}