import javax.swing.JFrame; //�~�ӵ��� ��5��
import java.awt.Container; //�~�Ӯe�� ��12��

//�D�������O
public class MainFrame extends JFrame {
	GamePanel p; //�ŧiGamePanel���O �a�ip�ܼ�(�}�l�N���O��m����)
	
	//�غc�l��k
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//��������
		p = new GamePanel(); //�Ы�p����
		Container c = getContentPane(); 
		//�ŧiContainer���O �Ы�c����(�HgetContentPane��k������骺�D�e��)
		
		c.add(p); //��C�����O(p)�K�[��D�e����
		addKeyListener( p );; //�K�[��L�ƥ��ť
	}
	
	//�D�{��
	public static void main(String[] args) {
		MainFrame frame = new MainFrame(); //�ŧiMainFrame���O �Ы�frame���骫��
		frame.setBounds(340, 150, 820, 500); //�]�w����j�p
		frame.setVisible(true); 
		
	}
	
	//���s�}�l
	public void restart() {
		Container c = getContentPane(); 
		c.removeAll(); //�R���Ҧ��ե�
		//�ХD�e�� >> �йC�����O >> �C�����O���D�e�� >>�K�[��L��ť
		GamePanel np = new GamePanel();
		c.add(np);
		addKeyListener(np);
		
		c.validate(); //���s���Үe���դ�
	}
}
