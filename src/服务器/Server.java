package ������;

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
				System.out.println("����������񴴽��ɹ�");
				
				while(true){
					//Socket user1 =server1.accept();
					Socket user2 =server2.accept();
					System.out.println("�ͻ���������");
					//���пͻ������ӽ����Ժ󣬿���һ���̣߳���������ÿͻ��˵��߼�,
					//chatRoomThread st1 = new chatRoomThread(user1);
					chatThread st2 = new chatThread(user2);
					//st1.start();
					st2.start();
					//��Ӹÿͻ��˵�������
					//list1.add(st1);
					list2.add(st2);
					
				}
			} 
			catch (IOException e) {
				System.out.println("���ڼ���");
			}
		
		}
	}	
}
