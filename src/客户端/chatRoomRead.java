package �ͻ���;
import java.io.*;
public class chatRoomRead implements Runnable{
	DataInputStream in;
	
	void setDataInputStream(DataInputStream in){
		this.in = in;
	}
	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		String s;
		while(true){
			try{
				s = in.readUTF();
				chatRoomView.a1.append(s);
			}
			catch(IOException e){
				System.out.println("��������ѶϿ�"+e);
				break;
			}
		}
	}

}
