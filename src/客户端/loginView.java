 package �ͻ���;



import java.awt.Font;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.*;

public class loginView extends JFrame implements ActionListener{
	JButton b1,b2,b3;
	JLabel user,password,l;
	JTextField t1,t2;
	static String name = null;
	boolean judge = false;
		
	void init(){
		b1 = new JButton("��¼");
		b2 = new JButton("ȡ��");
		b3 = new JButton("ע��");
		user = new JLabel("�û�����");
		password = new JLabel("���룺");
		l = new JLabel("α��QQ");
		t1 = new JTextField();
		t2 = new JTextField();
		
		add(l);
		add(user);
		add(t1);
		add(password);
		add(t2);
		add(b1);
		add(b2);
		add(b3);
		setLayout(null);
		user.setBounds(30, 120, 60, 20);    //��ʼX ��ʼY ��  ��
		password.setBounds(30, 150, 60, 20);
		t1.setBounds(90, 120, 100, 20);
		t2.setBounds(90, 150, 100, 20);
		b3.setBounds(210, 120, 60, 20);
		b1.setBounds(60, 190, 80, 20);
		b2.setBounds(160, 190, 80, 20);
		l.setBounds(100, 40, 100, 40);
		l.setFont(new Font("����",Font.BOLD, 30));
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
	}
	
	
	loginView(){
	     init(); 
	     setVisible(true);  
		 setSize(300,280);
		 setTitle("α��QQ��¼");
		 setResizable(false);
		 setLocationRelativeTo(null);
		 setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		 setVisible(true);
	 }
	void loginIn(){
		String str,user,password;
		try{
			Reader in = new FileReader("�û���Ϣ.txt");
			BufferedReader reader = new BufferedReader(in);
			while((str = reader.readLine()) != null){
				StringTokenizer s = new StringTokenizer(str);
				user = s.nextToken();
				password = s.nextToken();
				if(t1.getText().equals(user)&&t2.getText().equals(password)){
					name = user;
					judge = true;
					break;
				}
			}
			reader.close();
			in.close();
		}
		catch(IOException e){
			System.out.println(e);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		if(e.getSource() == b1){             // ��¼
			loginIn();
			if(judge){
				new listView();
				setVisible(false);
			}
			else {
				JOptionPane.showMessageDialog(null, "�û��������벻��ȷ�����������룡", "����", JOptionPane.ERROR_MESSAGE);
				t2.setText("");
				t2.requestFocus(true);
				}
			
		}
		
		if(e.getSource() == b2){           //ȡ�����رճ���
			System.exit(0);
		}
		
		if(e.getSource() == b3){            //ע��
			new registerView();
		}
	}

}
