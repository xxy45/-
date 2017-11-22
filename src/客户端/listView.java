package 客户端;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.*;

public class listView extends JFrame implements ActionListener,MouseListener{
	JLabel j1,j11,j2,j3,j4;
	JButton b;
	JTextField t;
	JPanel p1,p2,p3;
	static String name,friendname ;
	GridLayout g;
	
	ArrayList<String> list = new ArrayList<String>();
	
	void setName(){
		name = loginView.name ;
	}
	
	void getFriend(){
		try{
			File f = new File(name+"的好友信息.txt");
			if(f.exists() == false){
				f.createNewFile();
			}
			Reader in = new FileReader(f);
			BufferedReader reader = new BufferedReader(in);
			String str;
			while((str = reader.readLine()) != null){
				list.add(str);
			}
			reader.close();
			in.close();
		}
		catch(IOException e){
			System.out.println(e);
		}
	}
	
	void init(){
		
		setName();
		j1 = new JLabel(name);
		j1.setFont(new Font("黑体",Font.BOLD, 25));
		j2 = new JLabel("好友列表");
		j2.setFont(new Font("楷体",Font.BOLD, 18));
		j3 = new JLabel("用户名");
		j3.setFont(new Font("黑体",Font.BOLD, 15));
		j4 = new JLabel("多人聊天室");
		t = new JTextField();
		t.setFont(new Font("黑体",Font.BOLD, 15));
		
		b = new JButton("添加好友");
		b.addActionListener(this);
		j4.setFont(new Font("楷体",Font.BOLD, 18));
		j4.addMouseListener(this);
		p1 = new JPanel();
		j11 = new JLabel();
		p2 = new JPanel();
		p3 = new JPanel();
	//p1面板
		p1.setPreferredSize(new Dimension(300, 120));
		p2.setPreferredSize(new Dimension(300, 520));
		p3.setPreferredSize(new Dimension(300, 60));
		p3.setBackground(Color.CYAN);
		p2.setBackground(Color.GRAY);
		
		p1.setLayout(null);
		j11.setBounds(10, 10, 60, 60);
		ImageIcon image= new ImageIcon("image//center.jpg");
		j11.setIcon(image);
		j1.setBounds(80, 20, 200, 40);
		j2.setBounds(20, 90, 300, 30);
		j4.setBounds(170, 90, 300, 30);
		p1.setBackground(Color.CYAN);
		p1.add(j11);
		p1.add(j1);
		p1.add(j2);
		p1.add(j4);
	//p2面板
		g = new GridLayout(10,1);
		p2.setLayout(g);
		
		getFriend();
		JLabel label[] = new JLabel[list.size()];
		for (int i = 0; i<list.size();i++){
			label[i] = new JLabel(list.get(i));
			label[i].addMouseListener(this);
			label[i].setFont(new Font("黑体",Font.BOLD, 18));
			if(i%2 == 1)
				label[i].setBackground(Color.white);
			p2.add(label[i]);
		}
	//p3面板
		p3.setLayout(null);
		j3.setBounds(10, 20, 60, 25);
		t.setBounds(70, 20, 100, 25);
		b.setBounds(190, 20, 90, 25);
	 
		//去边框  
		b.setBorderPainted(false); 
		p3.add(j3);
		p3.add(t);
		p3.add(b);
		
	//总面板	
		add(p1,BorderLayout.NORTH);
		add(p2,BorderLayout.CENTER);
		add(p3,BorderLayout.SOUTH);
		
		
	}
	listView(){
		init(); 
	    setVisible(true);  
		setSize(300,700);
		setTitle("伪·QQ");
		setResizable(false);
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = (int)screensize.getWidth();
		//int screenHeight = (int)screensize.getHeight();
		setLocation(screenWidth-370,0);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	void addFriend(){
		try{
			File fuser = new File("用户信息.txt");
			File ffriend = new File(name+"的好友信息.txt");
			Reader in1 = new FileReader(fuser);
			Reader in2 = new FileReader(ffriend);
			BufferedReader reader1 = new BufferedReader(in1);
			BufferedReader reader2 = new BufferedReader(in2);
			String s1,s2;
			while ((s1 = reader1.readLine()) != null) {
				StringTokenizer ss1 = new StringTokenizer(s1);
				String username = ss1.nextToken();
				if(username.equals(t.getText())){
					File fnewfriend = new File(username+"的好友信息.txt");
					try{
						while ((s2 = reader2.readLine()) != null) {
							if(s2.trim().equals(t.getText())){
								JOptionPane.showMessageDialog(null, "用户已存在！", "错误", JOptionPane.ERROR_MESSAGE);
								break;
							}
						}
					}
					catch (Exception e) {
						Writer out1 = new FileWriter(ffriend);
						BufferedWriter writer1 = new BufferedWriter(out1);
						Writer out2 = new FileWriter(fnewfriend);
						BufferedWriter writer2 = new BufferedWriter(out2);
						writer1.write(t.getText()+"\n");
						writer2.write(name+"\n");
						JOptionPane.showMessageDialog(null, "好友添加成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
						writer1.close();
						writer2.close();
						out1.close();
						out2.close();
					}
				}
			}
			reader1.close();
			reader2.close();
			in1.close();
			in2.close();
		}
		catch (Exception e) {
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, "用户不存在！", "错误", JOptionPane.ERROR_MESSAGE);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if (e.getSource() == b){
			addFriend();
			repaint();
		}
	}          

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		
		if(e.getSource() == j4){
			int clickTimes = e.getClickCount();			//双击事件   聊天室
			if (clickTimes == 2) {
				chatRoomView c = new chatRoomView();
			}
		}
		
		else{
			JLabel item =(JLabel)e.getSource();
			if(e.getSource() == item){
				int clickTimes = e.getClickCount();			//双击事件  聊天
				if (clickTimes == 2) {
					friendname = item.getText();
					if(friendname != null){
						chatView cc =  new chatView();
					}
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

      
}
