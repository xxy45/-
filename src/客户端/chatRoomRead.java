package 客户端;
import java.io.*;
public class chatRoomRead implements Runnable{
	DataInputStream in;
	
	void setDataInputStream(DataInputStream in){
		this.in = in;
	}
	@Override
	public void run() {
		// TODO 自动生成的方法存根
		String s;
		while(true){
			try{
				s = in.readUTF();
				chatRoomView.a1.append(s);
			}
			catch(IOException e){
				System.out.println("与服务器已断开"+e);
				break;
			}
		}
	}

}
