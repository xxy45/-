package 客户端;

import java.io.DataInputStream;
import java.io.IOException;

public class chatRead implements Runnable{
	DataInputStream in;
	
	void setDataInputStream1(DataInputStream in){
		this.in = in;
	}
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		String s;
		while(true){
			try{
				s = in.readUTF();
				chatView.a1.append(s);
			}
			catch(IOException e){
				System.out.println("与服务器已断开"+e);
				
				break;
			}
		}
	}
}
