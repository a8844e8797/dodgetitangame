import javax.swing.JFrame; //繼承窗體 第5行
import java.awt.Container; //繼承容器 第12行

//主窗體類別
public class MainFrame extends JFrame {
	GamePanel p; //宣告GamePanel類別 帶進p變數(開始將面板放置窗體)
	
	//建構子方法
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//窗體關閉
		p = new GamePanel(); //創建p物件
		Container c = getContentPane(); 
		//宣告Container類別 創建c物件(以getContentPane方法獲取窗體的主容器)
		
		c.add(p); //把遊戲面板(p)添加到主容器中
		addKeyListener( p );; //添加鍵盤事件監聽
	}
	
	//主程式
	public static void main(String[] args) {
		MainFrame frame = new MainFrame(); //宣告MainFrame類別 創建frame窗體物件
		frame.setBounds(340, 150, 820, 500); //設定窗體大小
		frame.setVisible(true); 
		
	}
	
	//重新開始
	public void restart() {
		Container c = getContentPane(); 
		c.removeAll(); //刪除所有組件
		//創主容器 >> 創遊戲面板 >> 遊戲面板放到主容器 >>添加鍵盤監聽
		GamePanel np = new GamePanel();
		c.add(np);
		addKeyListener(np);
		
		c.validate(); //重新驗證容器組片
	}
}
