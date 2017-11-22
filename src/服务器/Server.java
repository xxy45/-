package 服务器;

import java.io.*;
import java.net.*;
import java.util.*;

import javax.swing.*;

public class Server {
	//public static ArrayList<chatRoomThread> list1 =new ArrayList<chatRoomThread>();
	public static ArrayList<chatThread> list2 =new ArrayList<chatThread>();
	ServerSocket server2 = null;
	DataInputStream in;
	
	Server(){
		
		
		while (true) {
			try {
				//server1 = new ServerSocket(2010);
				server2 = new ServerSocket(2011);
				System.out.println("多人聊天服务创建成功");
				
				while(true){
					//Socket user1 =server1.accept();
					Socket user2 =server2.accept();
					System.out.println("客户端已连接");
					//当有客户端连接进来以后，开启一个线程，用来处理该客户端的逻辑,
					//chatRoomThread st1 = new chatRoomThread(user1);
					chatThread st2 = new chatThread(user2);
					//st1.start();
					st2.start();
					//添加该客户端到容器中
					//list1.add(st1);
					list2.add(st2);
					
				}
			} 
			catch (IOException e) {
				System.out.println("正在监听");
			}
		
		}
	}	
}
