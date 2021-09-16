import java.awt.image.BufferedImage; //第8行
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics; //第31行
import java.awt.Graphics2D; //第9行
import java.awt.List;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;//第15行
import java.awt.event.KeyListener; //第15行
import javax.imageio.ImageIO; //第27行
import java.io.File; //第27行
import java.io.IOException; //第30行
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel; //繼承面板 第7行
//import java.awt.Color;




//主面板類別
public class GamePanel extends JPanel implements KeyListener{
	BufferedImage Image; //宣告主圖片(指的是panel原有黑色底) 帶進Image變數
	Graphics2D G2; //宣告繪圖 帶進G2變數
	Human player; //宣告人類類別 帶進player變數
	BackgroundImage background;//宣告背景類別 帶進background變數
	
	boolean finish = false; //遊戲結束
	static final int FRESH = 20;//常量 刷新時間 static變靜態以便直接調用
	
	//Obstacle o = new Obstacle(); //宣告障礙類別 以障礙障礙建構子方法 創建O物件
	//泛型 在泛型中寫了指定類別 list中只能放那個指定類別
	java.util.List<Obstacle> list = new ArrayList<Obstacle>(); //障礙物同夥
	int addObstacleTimer = 0;
	
	int score = 0; //宣告得分
	int addScoreTimer = 0; //得分計時器
	
	//建構子方法
	public GamePanel() {
		Image = new BufferedImage(800,300,BufferedImage.TYPE_INT_BGR); //創建Image物件設置主圖片(長寬)
		G2 = Image.createGraphics(); //創建G2物件
		try {
			player = new Human();
		} catch (Exception e) {
			e.printStackTrace();
		} //創建player物件 
		
		background = new BackgroundImage();
		
		list.add(new Obstacle());
		
		FreshThread t = new FreshThread(this); //宣告FreshThread類別 創建t物件 刷新多執行緒
		t.start();
	}
	
	//方法
	//圖片
	private void paintImage() {
		player.move(); //讓玩家踏步 (每隔十分之一秒換一張圖)
		background.roll(); //讓背景圖滾動
		//o.move(); ////讓障礙物移動
		
		
		G2.drawImage(background.image, 0, 0, this); //繪製背景 background物件的座標
		
		
		Rectangle rec = player.getFrontBounds(); 
		G2.setColor(Color.black);
		G2.fillRect(rec.x, rec.y, rec.width, rec.height);
		G2.drawImage(player.image, player.x, player.y, this); //繪製障礙物 o物件的座標
		
		if(addObstacleTimer >= 800) {
			Random r = new Random();
			int a = r.nextInt(100); //100裡隨機數字
			if(a > 50) {
				list.add(new Obstacle());
			}
			addObstacleTimer = 0;
		}
		for(int i = 0; i<list.size(); i++) {
			Obstacle o = list.get(i);
			o.move();
			
			rec = o.getBounds(); 
			G2.setColor(Color.black);
			G2.fillRect(rec.x, rec.y, rec.width, rec.height);
			G2.drawImage(o.image, o.x, o.y, this); //繪製障礙物 o物件的座標
			
			//發生碰撞
			if(o.getBounds().intersects(player.getFrontBounds())) {
				gameOver();
			}/*else {
				if (o.x < player.x) {
					score ++;
					
				}
			}*/
		}
		
		addObstacleTimer += FRESH; //讓添加障礙物計時器計時 
		addScoreTimer += FRESH; //讓添加得分計時器計時 
		
		//計算得分
		if (addScoreTimer >= 500) {
			score +=1;
			addScoreTimer = 0;
		}
		G2.setColor(Color.black);
		G2.setFont(new Font("黑體", Font.BOLD, 24));
		G2.drawString(String.format("%05d",score), 700 ,30);
		
	}
	
	//方法
	//@Override>>同一方法不同實作 
	public void paint(Graphics g/*第2行*/) { //複寫swing.Conponent類別裡的paint方法
		paintImage(); 
		g.drawImage(Image,0, 0,this); //主圖片貼到面板上
	}
	
	//方法
	@Override
	public void repaint() {
		// TODO Auto-generated method stub
		super.repaint();
	}
	
	//方法
	public boolean isFinish() {
		return finish;
	}
	//方法
	@Override
	public void keyTyped(KeyEvent e) {//按鍵的類型
		// TODO Auto-generated method stub
		
	}
	//方法
	@Override
	public void keyPressed(KeyEvent e) {//按鍵按下
		//System.out.println(e.getKeyChar()); 獲取鍵盤輸入
		int code = e.getKeyChar(); //獲取按下的鍵盤編碼
		
		if (code == KeyEvent.VK_UP); {
			player.jump();
		}
		/*
		if (code == (KeyEvent.VK_RIGHT)); {
			player.doublejump();
		}
		*/
		
	}
	//方法
	@Override
	public void keyReleased(KeyEvent e) {//按鍵抬起
		// TODO Auto-generated method stub
		
	}
	
	//方法
	public void gameOver() {
		finish = true;
	}
	
	
	

	
	
	
	
	
	
	
	
}
