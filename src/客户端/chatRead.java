package �ͻ���;

import java.io.DataInputStream;
import java.io.IOException;

public class chatRead implements Runnable{
	DataInputStream in;
	
	void setDataInputStream1(DataInputStream in){
		this.in = in;
	}
	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		String s;
		while(true){
			try{
				s = in.readUTF();
				chatView.a1.append(s);
			}
			catch(IOException e){
				System.out.println("��������ѶϿ�"+e);
				
				break;
			}
		}
	}
}
