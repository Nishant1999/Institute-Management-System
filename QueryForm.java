import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class QueryForm
{
	QueryForm(JFrame frame)
	{
		try
		{
		System.out.println("hello query");
		JDialog query=new JDialog(frame,"Query",true);
		query.setSize(640,450);
		query.setLocation(170,150);
		query.setLocation(170,150);
		query.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.insets=new Insets(2,5,2,5);
		
		JLabel date1,studentname,contact,course,batch,comments;
		JTextField studentnametextfield,contacttextfield,date1textfield;
		JComboBox coursecombobox;
		JTextArea commentstextarea;
		JButton savebutton,cancelbutton;
		
		date1=new JLabel("Date");
		gbc.anchor=GridBagConstraints.EAST;
		gbc.gridx=1;
		gbc.gridy=0;
		query.add(date1,gbc);
		
		
		date1textfield=new JTextField("10.9.2019");
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=2;
		gbc.gridy=0;
		query.add(date1textfield,gbc);
		
		course=new JLabel("Course");
		gbc.gridx=0;
		gbc.gridy=1;
		query.add(course,gbc);
		
		coursecombobox=new JComboBox();
		ResultSet rsqf=Database.statement.executeQuery("select * from Courses");
		while(rsqf.next())
		{
			System.out.println(rsqf.getString("Course"));
			coursecombobox.addItem(rsqf.getString("Course"));
		}
		gbc.fill=GridBagConstraints.BOTH;
		gbc.gridx=1;
		gbc.gridy=1;
		gbc.gridwidth=1;
		query.add(coursecombobox,gbc);
		
		studentname=new JLabel("Student Name");
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=0;
		gbc.gridy=2;
		query.add(studentname,gbc);
		
		studentnametextfield=new JTextField(10);
		gbc.gridx=1;
		gbc.gridy=2;
		query.add(studentnametextfield,gbc);
		
		contact=new JLabel("Contact");
		gbc.gridx=0;
		gbc.gridy=3;
		query.add(contact,gbc);
		
		contacttextfield=new JTextField(10);
		gbc.gridx=1;
		gbc.gridy=3;
		query.add(contacttextfield,gbc);
		
		comments=new JLabel("Comments");
		//gbc.anchor=GridBagConstraints.NORTHWEST;
		gbc.gridx=0;
		gbc.gridy=4;
		query.add(comments,gbc);
		
		commentstextarea=new JTextArea();
		gbc.anchor=GridBagConstraints.WEST;
		commentstextarea.setRows(5);
		gbc.gridx=1;
		gbc.gridy=4;
		JScrollPane scrollpane=new JScrollPane(commentstextarea);
		query.add(scrollpane,gbc);
		
		savebutton=new JButton("Save");
		gbc.anchor=GridBagConstraints.WEST;
		gbc.fill=GridBagConstraints.NONE;
		gbc.gridx=1;
		gbc.gridy=5;
		query.add(savebutton,gbc);
		
		savebutton.addActionListener((E)->{
										try
										{
										System.out.println("hello queryform");
										int tempqno=0;
										ResultSet rs=Database.statement.executeQuery("Select * from Queries");
										while(rs.next())
										{
											tempqno=rs.getInt("QNo");
										}
										PreparedStatement pstmt=Database.connection.prepareStatement("insert into Queries values(?,?,?,?,?,?)");
										pstmt.setInt(1,tempqno+1);
										pstmt.setString(2,studentnametextfield.getText());
										pstmt.setString(3,contacttextfield.getText());
										pstmt.setString(4,String.valueOf(coursecombobox.getSelectedItem()));
										pstmt.setString(5,commentstextarea.getText());
										pstmt.setString(6,LoginWindow.usernametextfield.getText());
										pstmt.execute();
										pstmt.close();
										studentnametextfield.setText("");
										contacttextfield.setText("");
										commentstextarea.setText("");
										System.out.println("Query Saved");
										int qno=tempqno+1;
										JOptionPane.showMessageDialog(query,"Query Number is "+qno,"Query",JOptionPane.INFORMATION_MESSAGE);
										}
										catch(SQLException H)
										{
											System.out.println(H);
										}
									});
		
		cancelbutton=new JButton("Cancel");
		gbc.gridx=2;
		gbc.gridy=5;
		query.add(cancelbutton,gbc);
		
		cancelbutton.addActionListener((e)->query.setVisible(false));
		
		query.setVisible(true);
		}
		catch(SQLException I)
		{
			System.out.println(I);
		}
	}
}