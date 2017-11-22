package 服务器;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class chatThread extends Thread{
	Socket socket;
	DataInputStream in = null;
	DataOutputStream out = null;
	
	chatThread(Socket t){
		socket = t;
		
		try{
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
		}
		catch(IOException e){}
	}
	
	public void run(){
		while(true){
			try{
				String come = in.readUTF();
				StringTokenizer s = new StringTokenizer(come,"++");
				String username = s.nextToken();
				String message = s.nextToken();
				Date date=new Date();
				DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time=format.format(date);
				out.writeUTF(time+"\t"+username+"\n"+message+"\n");
				for (int i = 0; i <Server.list2.size(); i++) {
					chatThread st =Server.list2.get(i);
					if(st !=this){
						st.out.writeUTF(time+"\t"+username+"\n"+message+"\n");
					}
				}
						
			}
			catch(IOException e){
				System.out.println("用户断开");
				return;
			}

		}
	}
}
