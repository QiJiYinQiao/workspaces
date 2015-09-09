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
	
	//定义添加矩形的方法
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
		background = new ImageIcon("src/123.jpg");// 背景图片
		/*System.out.println(background.getIconWidth());
		im =Toolkit.getDefaultToolkit().getImage("src/123.jpg");*/
		
		
		
		// System.out.println(background.getImage());
		  JLabel label = new JLabel(background);// 把背景图片显示在一个标签里面
		  // 把标签的大小位置设置为图片刚好填充整个面板
		  label.setBounds(0, 0, background.getIconWidth(),
		    background.getIconHeight());
		  // 把背景图片添加到分层窗格的最底层作为背景
		  frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		//获取frame的最上层面板为了设置其背景颜色（JPanel有设置透明的方法）
		  JPanel jps=(JPanel)frame.getContentPane(); 
		  jps.setOpaque(false);//设置透明
		  
		  
		  jp.setOpaque(false);
		//Window window = new Window(frame);
		
		frame.add(jp);
		
		//在panel中添加矩形
		jp.addRect(0, 0, 200, 200, Color.BLUE);
		jp.addRect(100, 100, 200, 200, Color.GREEN);
		
		
		
		frame.setSize(800,600);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		ImageIO.write(bi, "JPG", new File("src/234.jpg"));
		
		// 加载背景图片  
	    /* JFrame frame = new JFrame();
		 ImageIcon bg = new ImageIcon("123.jpg"); // 把背景图片显示在一个标签里
		 JLabel label = new JLabel(bg); //把标签的大小位置设置为图片刚好填充整个面
		 label.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight()); //添加图片到frame的第二层  
		 frame.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE)); //获取frame的最上层面板为了设置其背景颜色（JPanel有设置透明的方法） 
		 JPanel jp=(JPanel)frame.getContentPane(); jp.setOpaque(false);//设置透明    
		 //测试用的JPanel    
		 JPanel panel=new JPanel();     
		 panel.setOpaque(false);//也要让他透明   
		 panel.setLayout(null);//为了使用按钮的定位   
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
