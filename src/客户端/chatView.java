package 客户端;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import 服务器.Server;
import 服务器.chatThread;

public class chatView extends JFrame implements ActionListener{
	static JTextArea a1;
	JTextArea a2;
	JButton b1,b2;
	JPanel p;
	Socket mysocket = null;
	DataInputStream in = null;
	DataOutputStream out = null;
	chatRead read1 = null;
	Thread readData1;
	String username = loginView.name;
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
	
	chatView(){
		init(); 
	    setVisible(true); 
	    setSize(505,450); 
	    setLocationRelativeTo(null);
		setTitle("与"+friend+"聊天中");
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		try{
			mysocket = new Socket("127.0.0.1",2011);
			read1 = new chatRead();
			readData1 = new Thread(read1);
			in = new DataInputStream(mysocket.getInputStream());
			out = new DataOutputStream(mysocket.getOutputStream());
			read1.setDataInputStream1(in);
			readData1.start();
			System.out.println("连接双人聊天服务器成功！");
		}
		catch(Exception e){
			System.out.println("与双人聊天服务器已断开"+e);
		
		/*	try{
				chatThread.in.close();
				chatThread.out.close();
				Server.list2.remove(this);
				System.out.println("用户断开！");
			}
			catch(Exception ee){};	*/
		}

	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource() == b1){
			if(a2.getText().equals(""))
				JOptionPane.showMessageDialog(null, "消息不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
			else{
				try{
					out.writeUTF(username+"++"+friend+"++"+a2.getText());
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
