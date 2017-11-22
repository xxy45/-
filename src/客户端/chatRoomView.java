package 客户端;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.*;

public class chatRoomView extends JFrame implements ActionListener{
	static JTextArea a1;
	JTextArea a2;
	JButton b1,b2;
	JPanel p;
	Socket mysocket = null;
	DataInputStream in = null;
	DataOutputStream out = null;
	chatRoomRead read = null;
	Thread readData;
	String friend = listView.friendname;
	
	void init(){
		a1 = new JTextArea();
		a1.setEditable(false);
		a2 = new JTextArea();
		a1.setFont(new Font("楷体",Font.BOLD, 15));
		a2.setFont(new Font("楷体",Font.BOLD, 15));
		b1 = new JButton("发送");
		b2 = new JButton("取消");
		
		p = new JPanel();
		p.setPreferredSize(new Dimension(505, 450));
		p.setBackground(Color.GRAY);
		
		a1.setBounds(10, 10, 480, 310);
		a2.setBounds(10, 330, 480, 50);
		
		b1.setBounds(410, 390, 80, 20);
		b2.setBounds(310, 390, 80, 20);
		a1.setBackground(Color.white);
		a2.setBackground(Color.white);
		p.setLayout(null);
		p.add(a1);
		p.add(a2);
		p.add(b1);
		p.add(b2);
		
		add(p);
		
		b1.addActionListener(this);
		b2.addActionListener(this);		
	}
	
	chatRoomView(){
		init(); 
	    setVisible(true); 
	    setSize(505,450); 
	    setLocationRelativeTo(null);
		setTitle("多人聊天室");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		try{
			mysocket = new Socket("127.0.0.1",2011);
			read = new chatRoomRead();
			readData = new Thread(read);
			in = new DataInputStream(mysocket.getInputStream());
			out = new DataOutputStream(mysocket.getOutputStream());
			read.setDataInputStream(in);
			readData.start();
			System.out.println("连接服务器成功！");
		}
		catch(Exception e){
			System.out.println("与服务器已断开"+e);
		}
	/*	try{
			out.writeUTF(str);			
		}
		catch(Exception e1){}*/
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource() == b1){
			if(a2.getText().equals(""))
				JOptionPane.showMessageDialog(null, "消息不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
			else{
				try{
					out.writeUTF(loginView.name+"++"+a2.getText());
					a2.setText("");
				}
				catch(Exception e2){
					JOptionPane.showMessageDialog(null, "服务器连接失败！", "错误", JOptionPane.ERROR_MESSAGE);
					System.out.println("服务器连接失败"+e);
				}
			}
		}
		if(e.getSource() == b2){
			a2.setText("");
		}
	}
}
