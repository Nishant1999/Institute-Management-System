import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class AdminAddCourse
{
	JDialog addcourse;
	JTextField coursenametextfield,coursefeestextfield;
	JLabel coursenamelabel,coursefeeslabel;
	JButton addcoursebutton,cancelbutton;
	AdminAddCourse(JFrame frame)
	{
		addcourse=new JDialog(frame,"Add Course",true);
		addcourse.setSize(250,250);
		addcourse.setLocation(170,150);
		addcourse.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.insets=new Insets(5,5,5,5);
		
		coursenamelabel=new JLabel("Course Name");
		gbc.gridx=0;
		gbc.gridy=0;
		addcourse.add(coursenamelabel,gbc);
		
		coursenametextfield=new JTextField(10);
		gbc.gridx=1;
		gbc.gridy=0;
		addcourse.add(coursenametextfield,gbc);
		
		coursefeeslabel=new JLabel("Course Fees");
		gbc.gridx=0;
		gbc.gridy=1;
		addcourse.add(coursefeeslabel,gbc);
		
		coursefeestextfield=new JTextField(10);
		gbc.gridx=1;
		gbc.gridy=1;
		addcourse.add(coursefeestextfield,gbc);
		
		addcoursebutton=new JButton("Add Course");
		gbc.gridx=0;
		gbc.gridy=2;
		addcourse.add(addcoursebutton,gbc);
		
		cancelbutton=new JButton("Cancel");
		gbc.gridx=1;
		gbc.gridy=2;
		addcourse.add(cancelbutton,gbc);
		
		addcoursebutton.addActionListener((E)->{
												String tempcoursename;
												String tempcoursefees;
												int tempcourseid=0;
												tempcoursename=coursenametextfield.getText();
												tempcoursefees=coursefeestextfield.getText();
												try
												{
												ResultSet rsac=Database.statement.executeQuery("Select Course_Id from Courses");
												while(rsac.next())
												{
													tempcourseid=rsac.getInt("Course_Id");
												}
												PreparedStatement pstmtac=Database.connection.prepareStatement("insert into Courses(Course_Id,Course,Fee) values(?,?,?)");
												pstmtac.setInt(1,tempcourseid+1);
												pstmtac.setString(2,tempcoursename);
												pstmtac.setInt(3,Integer.parseInt(tempcoursefees));
												pstmtac.execute();
												pstmtac.close();
												int tempcidno=tempcourseid+1;
												JOptionPane.showMessageDialog(addcourse,"Course Id of "+tempcoursename+" is "+tempcidno,"ID Number",JOptionPane.INFORMATION_MESSAGE);
												addcourse.setVisible(false);
												}
												catch(SQLException A)
												{
													System.out.println("Error in addcourse"+A);
												}
											});
		cancelbutton.addActionListener((E)->addcourse.setVisible(false));
		
		addcourse.setVisible(true);
	}
}