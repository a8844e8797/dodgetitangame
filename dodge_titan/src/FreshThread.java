import java.awt.Container;

import javax.swing.JOptionPane;

public class FreshThread extends Thread{
	GamePanel p;
	
	public FreshThread(GamePanel p) {
		this.p = p; //給類成員屬性賦值 將參數p帶給GamePanel p這物件
	}
	 
	
	@Override
	public void run() {
		while(!p.isFinish()/*當遊戲還沒結束*/) {
			p.repaint(); //重新繪製圖片
			try {
				Thread.sleep(p.FRESH);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} //休眠
		}
		
		
		
		Container c = p.getParent();
		while (!(c instanceof MainFrame)) { //當c的父容器不是MainFrame，繼續找直到找到為止
			c = c.getParent(); //找父容器
		}
		MainFrame f =(MainFrame) c; //將c強制轉換成(MainFrame)類別
		
		
		
		
		//彈出通知視窗
		JOptionPane.showMessageDialog(f,"Game Over!");
		f.restart();
	}
}
