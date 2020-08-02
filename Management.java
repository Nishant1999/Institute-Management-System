import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Management
{
	JFrame frame;
	JPanel panel1,panel2,panel3,panel4;
	JButton administrator,newentry,viewrecords,feesdeposit,editrecord,logout;
	JLabel name;
	Font fnt;
	ResultSet rs1,rs2;
	String temp,coursesdetails;

	Management()
	{
		frame=new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setSize(660,550);
		frame.setLocation(50,50);

		panel1=new JPanel(new BorderLayout());
		panel2=new JPanel(new GridBagLayout());
		panel3=new JPanel(new BorderLayout());
		panel4=new JPanel(new BorderLayout());

		name=new JLabel("Welcome To Institute",SwingConstants.CENTER);
		fnt=new Font("Arial",Font.BOLD,24);
		name.setFont(fnt);
		name.setEnabled(false);
		panel3.setBackground(Color.RED);
		panel3.add(name,BorderLayout.CENTER);

		administrator=new JButton("Administrator");
		newentry=new JButton("New Entry");
		viewrecords=new JButton("View Records");
		feesdeposit=new JButton("Fees Deposit");
		editrecord=new JButton("Edit Record");
		logout=new JButton("Logout");

		GridBagConstraints gbc=new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;
		gbc.insets=new Insets(5,5,5,5);
/*
		try{
		rs1=Database.statement.executeQuery("select * from Login where Username='"+ LoginWindow.usernametextfield.getText()+"'");
		while(rs1.next())
		{
			temp=rs1.getString("Permissions");
			System.out.println(temp);
		}
		}
		catch(SQLException e)
		{
			System.out.println("No Permissions");
		}
		if(temp.equals("Admin"))
		{
			gbc.gridx=0;
			gbc.gridy=0;
			panel2.add(administrator,gbc);
		}
*/

		gbc.gridx=0;
		gbc.gridy=1;
		panel2.add(newentry,gbc);

		gbc.gridx=0;
		gbc.gridy=2;
		panel2.add(viewrecords,gbc);

		gbc.gridx=0;
		gbc.gridy=3;
		panel2.add(feesdeposit,gbc);

		gbc.gridx=0;
		gbc.gridy=4;
		panel2.add(editrecord,gbc);

		gbc.gridx=0;
		gbc.gridy=5;
		panel2.add(logout,gbc);

		newentry.addActionListener(new ActionListener()
									{
									public void actionPerformed(ActionEvent e)
									{
										JDialog New=new JDialog(frame,"New",true);
										New.setSize(200,110);
										New.setLocation(170,260);
										New.setLayout(new GridBagLayout());

										JButton entry,query;
										entry=new JButton("Entry");
										query=new JButton("Query");

										GridBagConstraints gbc=new GridBagConstraints();
										gbc.insets=new Insets(5,5,5,5);

										gbc.gridx=0;
										gbc.gridy=0;
										New.add(entry,gbc);

										gbc.gridx=0;
										gbc.gridy=1;
										New.add(query,gbc);

										entry.addActionListener((E)->{New.setVisible(false);
																StudentForm st=new StudentForm(frame);
																});
										query.addActionListener((T)->{New.setVisible(false);
																		QueryForm qf=new QueryForm(frame);});

										New.setVisible(true);
									}
									});

		viewrecords.addActionListener(new ActionListener()
									{
										public void actionPerformed(ActionEvent e)
										{
										JDialog View=new JDialog(frame,"View",true);
										View.setSize(200,110);
										View.setLocation(170,260);
										View.setLayout(new GridBagLayout());

										JButton viewentry,viewquery;
										viewentry=new JButton("View Entry");
										viewquery=new JButton("View Query");

										GridBagConstraints gbc=new GridBagConstraints();
										gbc.insets=new Insets(5,5,5,5);

										gbc.gridx=0;
										gbc.gridy=0;
										View.add(viewentry,gbc);

										gbc.gridx=0;
										gbc.gridy=1;
										View.add(viewquery,gbc);

										viewentry.addActionListener((E)->{View.setVisible(false);
																ViewStudentForm vst=new ViewStudentForm(frame);
																});
										viewquery.addActionListener((T)->{View.setVisible(false);
																		ViewQueryForm vqf=new ViewQueryForm(frame);});

										View.setVisible(true);
										}
									});
/*
		if(temp.equals("Admin"))
		{
			administrator.addActionListener(new ActionListener()
											{
												public void actionPerformed(ActionEvent e)
												{
													JDialog admin=new JDialog(frame,"Administrator",true);
													admin.setSize(200,210);
													admin.setLocation(170,260);
													admin.setLayout(new GridBagLayout());

													JButton addcourse,addnewbatch,feesrecords,addnewuser;
													addcourse=new JButton("Add Course");
													addnewbatch=new JButton("Add New Batch");
													feesrecords=new JButton("Fees Records");
													addnewuser=new JButton("Add New User");

													GridBagConstraints gbc=new GridBagConstraints();
													gbc.insets=new Insets(5,5,5,5);

													gbc.fill=GridBagConstraints.BOTH;
													gbc.gridx=0;
													gbc.gridy=0;
													admin.add(addcourse,gbc);

													gbc.gridx=0;
													gbc.gridy=1;
													admin.add(addnewbatch,gbc);

													gbc.gridx=0;
													gbc.gridy=2;
													admin.add(feesrecords,gbc);

													gbc.gridx=0;
													gbc.gridy=3;
													admin.add(addnewuser,gbc);

													addcourse.addActionListener((E)->{
																		admin.setVisible(false);
																		AdminAddCourse aac=new AdminAddCourse(frame);
																				});

													addnewbatch.addActionListener((P)->{
																				admin.setVisible(false);
																				AdminAddNewBatch aanb=new AdminAddNewBatch(frame);
																					});

													admin.setVisible(true);
												}

											});
		}
*/
		feesdeposit.addActionListener((E)->{FeesDepositForm fdf=new FeesDepositForm(frame);});

		panel1.add(panel2,BorderLayout.WEST);
		panel1.add(panel3,BorderLayout.NORTH);
		panel1.add(panel4,BorderLayout.CENTER);

		frame.add(panel1);
		frame.setVisible(true);
	}
	public static void main(String arg[])
	{
		Management m=new Management();
	}
}
