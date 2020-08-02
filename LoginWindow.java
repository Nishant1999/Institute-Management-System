import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class LoginWindow
{
	JFrame frame;
	JDialog logindialog;
	public static JTextField usernametextfield;
	JPasswordField passwordfield;
	JLabel usernamelabel,passwordlabel;
	JButton loginbutton,cancelbutton;
	JRadioButton adminbutton,userbutton;
	ButtonGroup bg;
	ResultSet rs;
	String permissions="User";
	LoginWindow()
	{

		frame=new JFrame();

		logindialog=new JDialog(frame,"Login",true);
		logindialog.setLayout(new GridBagLayout());
		logindialog.setSize(500,200);
		logindialog.setLocation(300,300);

		usernamelabel=new JLabel("Username");
		passwordlabel=new JLabel("Password");



		adminbutton=new JRadioButton("Admin");
		userbutton=new JRadioButton("User");
		userbutton.setSelected(true);

		bg=new ButtonGroup();
		bg.add(adminbutton);
		bg.add(userbutton);

		usernametextfield=new JTextField(15);
		passwordfield=new JPasswordField(15);
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.insets=new Insets(5,5,5,5);

		gbc.gridx=0;
		gbc.gridy=0;
		logindialog.add(usernamelabel,gbc);

		gbc.fill=GridBagConstraints.BOTH;
		gbc.gridx=1;
		gbc.gridy=0;
		logindialog.add(usernametextfield,gbc);

		gbc.insets=new Insets(5,5,5,5);

		gbc.gridx=0;
		gbc.gridy=1;
		logindialog.add(passwordlabel,gbc);

		gbc.gridx=1;
		gbc.gridy=1;
		logindialog.add(passwordfield,gbc);

		gbc.insets=new Insets(5,5,5,5);
		
		gbc.gridx=0;
		gbc.gridy=2;
		logindialog.add(adminbutton,gbc);

		gbc.gridx=1;
		gbc.gridy=2;
		logindialog.add(userbutton,gbc);


		loginbutton=new JButton("Login");
		cancelbutton=new JButton("Cancel");

		gbc.gridx =  0;
		gbc.gridy =  3;
		logindialog.add(loginbutton,gbc);

		gbc.gridx =  1;
		gbc.gridy =  3;
		logindialog.add(cancelbutton,gbc);



		adminbutton.addActionListener((e)->permissions="Admin");
		userbutton.addActionListener((e)->permissions="User");
		try
		{
			rs=Database.statement.executeQuery("select * from Login");
		}
		catch(SQLException e)
		{
			System.out.println("Database Encountered!!");
		}
		loginbutton.addActionListener(new ActionListener()
							{
								public void actionPerformed(ActionEvent e)
								{
									String username=usernametextfield.getText();
									char c[];
									c=passwordfield.getPassword();
									String password=String.valueOf(c);
									try
									{
									while(rs.next())
									{
										if(username.equals(rs.getString("Username")) && password.equals(rs.getString("Password")) && permissions.equals(rs.getString("Permissions")))
										{
											JOptionPane.showMessageDialog(logindialog,"Login Successfull","Confirmation",JOptionPane.INFORMATION_MESSAGE);
											Management mg=new Management();
											logindialog.setVisible(false);
											break;
										}
										else
										{

											JOptionPane.showMessageDialog(logindialog,"Invalid username or password","Invalid Credintials",JOptionPane.ERROR_MESSAGE);
											break;
										}
									}
									}
									catch(SQLException E)
									{
										System.out.println("Exception Occur");
									}

								}
							});
	cancelbutton.addActionListener((e)->{logindialog.setVisible(false);
										System.exit(0);});
	logindialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	logindialog.setVisible(true);

	}
	public static void main(String args[])
	{
		LoginWindow lw=new LoginWindow();
	}
}
