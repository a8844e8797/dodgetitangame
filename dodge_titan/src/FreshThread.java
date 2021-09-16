import java.awt.Container;

import javax.swing.JOptionPane;

public class FreshThread extends Thread{
	GamePanel p;
	
	public FreshThread(GamePanel p) {
		this.p = p; //���������ݩʽ�� �N�Ѽ�p�a��GamePanel p�o����
	}
	 
	
	@Override
	public void run() {
		while(!p.isFinish()/*��C���٨S����*/) {
			p.repaint(); //���sø�s�Ϥ�
			try {
				Thread.sleep(p.FRESH);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} //��v
		}
		
		
		
		Container c = p.getParent();
		while (!(c instanceof MainFrame)) { //��c�����e�����OMainFrame�A�~��䪽���쬰��
			c = c.getParent(); //����e��
		}
		MainFrame f =(MainFrame) c; //�Nc�j���ഫ��(MainFrame)���O
		
		
		
		
		//�u�X�q������
		JOptionPane.showMessageDialog(f,"Game Over!");
		f.restart();
	}
}
