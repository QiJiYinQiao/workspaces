package com.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanelTest extends JPanel{
	private ArrayList<ColorRect> colorRects = new ArrayList<ColorRect>();
	private static ImageIcon background;
	private static Image im;
	private static BufferedImage  bi = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);
	
	public JPanelTest(){
		super();
	}
	
	//������Ӿ��εķ���
	public void addRect(int x,int y,int width,int height,Color color){
		ColorRect colorRect = new ColorRect();
		colorRect.x = x;
		colorRect.y = y;
		colorRect.width = width;
		colorRect.height = height;
		colorRect.color = color;
		colorRects.add(colorRect);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);
		//g = bi.getGraphics();
		for(ColorRect colorRect : colorRects){
			/*g.setColor(colorRect.color);
			g.fillRect(colorRect.x, colorRect.y, colorRect.width, colorRect.height);*/
			g.setColor(Color.red);
			g.drawRect(colorRect.x, colorRect.y, colorRect.width, colorRect.height);
			g.drawImage(im, 0, 0, null);
		}
	}

	private class ColorRect{
		public int x;
		public int y;
		public int width;
		public int height;
		public Color color;
	}
	
	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame();
		JPanelTest jp = new JPanelTest();
		File file = new File("src/123.jpg");
		System.out.println(file.exists());
		background = new ImageIcon("src/123.jpg");// ����ͼƬ
		/*System.out.println(background.getIconWidth());
		im =Toolkit.getDefaultToolkit().getImage("src/123.jpg");*/
		
		
		
		// System.out.println(background.getImage());
		  JLabel label = new JLabel(background);// �ѱ���ͼƬ��ʾ��һ����ǩ����
		  // �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������
		  label.setBounds(0, 0, background.getIconWidth(),
		    background.getIconHeight());
		  // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����
		  frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		//��ȡframe�����ϲ����Ϊ�������䱳����ɫ��JPanel������͸���ķ�����
		  JPanel jps=(JPanel)frame.getContentPane(); 
		  jps.setOpaque(false);//����͸��
		  
		  
		  jp.setOpaque(false);
		//Window window = new Window(frame);
		
		frame.add(jp);
		
		//��panel����Ӿ���
		jp.addRect(0, 0, 200, 200, Color.BLUE);
		jp.addRect(100, 100, 200, 200, Color.GREEN);
		
		
		
		frame.setSize(800,600);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		ImageIO.write(bi, "JPG", new File("src/234.jpg"));
		
		// ���ر���ͼƬ  
	    /* JFrame frame = new JFrame();
		 ImageIcon bg = new ImageIcon("123.jpg"); // �ѱ���ͼƬ��ʾ��һ����ǩ��
		 JLabel label = new JLabel(bg); //�ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ����������
		 label.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight()); //���ͼƬ��frame�ĵڶ���  
		 frame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE)); //��ȡframe�����ϲ����Ϊ�������䱳����ɫ��JPanel������͸���ķ����� 
		 JPanel jp=(JPanel)frame.getContentPane(); jp.setOpaque(false);//����͸��    
		 //�����õ�JPanel    
		 JPanel panel=new JPanel();     
		 panel.setOpaque(false);//ҲҪ����͸��   
		 panel.setLayout(null);//Ϊ��ʹ�ð�ť�Ķ�λ   
		 JButton button=new JButton("OK");   
		 button.setSize(100, 20);   
		 button.setLocation(100, 50);   
		 panel.add(button);     
		 frame.add(panel);
		 frame.setSize(800,600);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
	}
}
