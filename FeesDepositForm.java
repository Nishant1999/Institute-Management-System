import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class FeesDepositForm
{
	JDialog feesdeposit;
	JLabel receiptnolabel,datelabel,coursenamelabel,batchlabel,studentnamelabel,contactlabel,feeamountlabel,feesnolabel,depositedlabel,depositednolabel,discountlabel,duelabel,duenolabel,amounttodepositlabel;
	JComboBox coursecombobox,batchcombobox,studentnamecombobox;
	JTextField datetextfield,contacttextfield,amountdeposittextfield,discounttextfield;
	JButton savefeesbutton,cancelbutton;
	FeesDepositForm(JFrame frame)
	{
		feesdeposit=new JDialog(frame,"Fees Deposit",true);
		feesdeposit.setSize(640,450);
		feesdeposit.setLocation(170,150);
		feesdeposit.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.insets=new Insets(5,5,5,5);
		
		receiptnolabel=new JLabel("Receipt No.");
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=0;
		gbc.gridy=0;
		feesdeposit.add(receiptnolabel,gbc);
		
		datelabel=new JLabel("Date");
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=1;
		gbc.gridy=0;
		feesdeposit.add(datelabel,gbc);
		
		datetextfield=new JTextField("8-10-2019");
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=2;
		gbc.gridy=0;
		feesdeposit.add(datetextfield,gbc);
		
		coursenamelabel=new JLabel("Course");
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=0;
		gbc.gridy=1;
		feesdeposit.add(coursenamelabel,gbc);
		
		coursecombobox=new JComboBox();
		gbc.fill=GridBagConstraints.BOTH;
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=1;
		gbc.gridy=1;
		feesdeposit.add(coursecombobox,gbc);
		
		batchlabel=new JLabel("Batch");
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=4;
		gbc.gridy=1;
		feesdeposit.add(batchlabel,gbc);
		
		batchcombobox=new JComboBox();
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=5;
		gbc.gridy=1;
		feesdeposit.add(batchcombobox,gbc);
		
		studentnamelabel=new JLabel("Student Name");
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=0;
		gbc.gridy=2;
		feesdeposit.add(studentnamelabel,gbc);
		
		studentnamecombobox=new JComboBox();
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=1;
		gbc.gridy=2;
		feesdeposit.add(studentnamecombobox,gbc);
		
		contactlabel=new JLabel("Contact");
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=4;
		gbc.gridy=2;
		feesdeposit.add(contactlabel,gbc);
		
		contacttextfield=new JTextField(10);
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=5;
		gbc.gridy=2;
		feesdeposit.add(contacttextfield,gbc);
		
		feeamountlabel=new JLabel("Fee Amount");
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=0;
		gbc.gridy=3;
		feesdeposit.add(feeamountlabel,gbc);
		
		feesnolabel=new JLabel();
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=1;
		gbc.gridy=3;
		feesdeposit.add(feesnolabel,gbc);
		
		depositedlabel=new JLabel("Deposited");
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=3;
		gbc.gridy=3;
		feesdeposit.add(depositedlabel,gbc);
		
		depositednolabel=new JLabel();
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=4;
		gbc.gridy=3;
		feesdeposit.add(depositednolabel,gbc);
		
		discountlabel=new JLabel("Discount");
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=5;
		gbc.gridy=3;
		feesdeposit.add(discountlabel,gbc);
		
		discounttextfield=new JTextField(5);
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=6;
		gbc.gridy=3;
		feesdeposit.add(discounttextfield,gbc);
		
		duelabel=new JLabel("Due");
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=0;
		gbc.gridy=4;
		feesdeposit.add(duelabel,gbc);
		
		duenolabel=new JLabel();
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=1;
		gbc.gridy=4;
		feesdeposit.add(duenolabel,gbc);
		
		amounttodepositlabel=new JLabel("Amount to deposit");
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=3;
		gbc.gridy=4;
		feesdeposit.add(amounttodepositlabel,gbc);
		
		amountdeposittextfield=new JTextField(5);
		gbc.anchor=GridBagConstraints.WEST;
		gbc.gridx=4;
		gbc.gridy=4;
		feesdeposit.add(amountdeposittextfield,gbc);
		
		savefeesbutton=new JButton("Save");
		gbc.fill=GridBagConstraints.NONE;
		gbc.gridx=1;
		gbc.gridy=5;
		feesdeposit.add(savefeesbutton,gbc);
		
		cancelbutton=new JButton("Cancel");
		gbc.gridx=3;
		gbc.gridy=5;
		feesdeposit.add(cancelbutton,gbc);
		
		feesdeposit.setVisible(true);
	}
}