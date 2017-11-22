package 客户端;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.*;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class registerView extends JFrame implements ActionListener,FocusListener{
	JButton b1,b2;
	JLabel u,p1,p2;
	JTextField t,t1,t2;
	
	void init(){
		b1 = new JButton("注册");
		b2 = new JButton("取消");
		u = new JLabel("输入用户名：");
		p1 = new JLabel("输入密码：");
		p2 = new JLabel("确认密码：");
		t1 = new JTextField();
		t2 = new JTextField();
		t = new JTextField();
		add(b1);
		add(b2);
		add(u);
		add(p1);
		add(p2);
		add(t1);
		add(t2);
		add(t);
		setLayout(null);
		u.setBounds(40, 30, 100, 20);
		p1.setBounds(40, 60, 100, 20);
		p2.setBounds(40, 90, 100, 20);
		t.setBounds(140, 30, 100, 20);
		t1.setBounds(140, 60, 100, 20);
		t2.setBounds(140, 90, 100, 20);
		b1.setBounds(50, 130, 80, 20);
		b2.setBounds(160, 130, 80, 20);
		b1.addActionListener(this);
		b2.addActionListener(this);
		t.addFocusListener(this);
	}
	
	registerView(){
		init();
		setVisible(true);  
		setSize(300,210);
		setTitle("用户注册");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	
	}
	
	void registerUser(){
		try{
			//File f = new File("用户信息.txt");
			Writer out = new FileWriter("用户信息.txt",true);
			BufferedWriter writer = new BufferedWriter(out);
			String regex = "[a-zA-Z0-9]+";
		
			if( t.getText().matches(regex)&&t1.getText().matches(regex)&&t2.getText().matches(regex)){
				if(t1.getText().equals(t2.getText())){
					writer.write(t.getText()+" "+t1.getText()+"\n");
					writer.close();
					out.close();
					JOptionPane.showMessageDialog(null, "注册成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					JOptionPane.showMessageDialog(null, "两次密码不一致，请重新输入", "警告", JOptionPane.WARNING_MESSAGE);
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "含有非法字符，内容不能为空且只允许数字字母组合", "错误", JOptionPane.ERROR_MESSAGE);
			}
				
		}
		catch(IOException e){System.out.println(e);}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource() == b1){
			registerUser();
			t.setText("");
			t1.setText("");
			t2.setText("");
		}
		
		if(e.getSource() == b2){
			dispose();
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO 自动生成的方法存根
	
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource() == t){
			String str,user;
			try{
				Reader in = new FileReader("用户信息.txt");
				BufferedReader reader = new BufferedReader(in);
				while((str = reader.readLine()) != null){
					StringTokenizer s = new StringTokenizer(str);
					user = s.nextToken();
					if(t.getText().equals(user)){
						JOptionPane.showMessageDialog(null, "用户名已存在，请重新输入！", "错误", JOptionPane.ERROR_MESSAGE);
						//t.requestFocus(true);
						break;
					}
				}
				reader.close();
				in.close();
			}
			catch(IOException ee){
				System.out.println(ee);
			}
		}
	}
	
}
