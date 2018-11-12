package com.King;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
//import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class KingGloryBattle extends JPanel {

	public static final int WIDTH = 1000; // 面板宽
	public static final int HEIGHT = 764; // 面板高
	public static final int Maxx= 15;
	public static final int herroSize= 80;
	
	public static BufferedImage background;
	public static BufferedImage people;
	public static BufferedImage bulletImage;
	public static BufferedImage boom;
	public static Timer timer;
	public static int index;
	public static int position[][];
	public static Bullet herroBullet[];
	public static String status;
	public static int herroSum;
	//英雄
	Herro hero= new Herro();
	Assassin assassinA= new Assassin(0, 0);
	Assassin assassinB= new Assassin(0, 0);

	static {
		try {
			background= ImageIO.read(KingGloryBattle.class.getResource("map.jpg"));
			people= ImageIO.read(KingGloryBattle.class.getResource("people1.png"));
			boom= ImageIO.read(KingGloryBattle.class.getResource("bullet-Boom.png"));
			//bulletImage= ImageIO.read(KingGloryBattle.class.getResource("bullet.png"));
			position= new int[Maxx][Maxx];
			herroBullet= new Bullet[Maxx];
			status= "";
			herroSum= 2;
            
			for(int i= 0; i< Maxx; i++)
				herroBullet[i]= new Bullet();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null); // 画背景图
		paintHerro(g);
		paintBullet(g);
	}
	
	public void paintBullet(Graphics g) {
		for(int i= 0; i< Maxx; i++) {
			if(herroBullet[i].getStillAlive()== 1)
				g.drawImage(herroBullet[i].getImage(), herroBullet[i].getX(), herroBullet[i].getY(), null);
			if(herroBullet[i].timeToBoom== 1) {
				g.drawImage(boom, herroBullet[i].getX(), herroBullet[i].getY(), null);
				herroBullet[i].timeToBoom= 0;
			}
		}
		
	}
	public void paintHerro(Graphics g) {
		if(assassinA.getIsAlive()== 1)
			g.drawImage(people, assassinA.getX(), assassinA.getY(), null);
		if(assassinB.getIsAlive()== 1)
			g.drawImage(people, assassinB.getX(), assassinB.getY(), null);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		     KeyAdapter key= new KeyAdapter() {
			   public  void keyPressed(KeyEvent e) {
	               
				   if(e.getKeyCode()== KeyEvent.VK_UP)
				        index= 0;
				   else if(e.getKeyCode()== KeyEvent.VK_DOWN) {
					   index= 1;
					  
				   }
				   else if(e.getKeyCode()== KeyEvent.VK_UP) {
					   
				   }
			   }
			   

				public void keyReleased(KeyEvent e) {}
				public void keyTyped(KeyEvent e) {}
			   
			};
			
		    index= 0;
			JFrame frame = new JFrame("王者荣耀");
			KingGloryBattle game = new KingGloryBattle(); // 面板对象
			frame.addKeyListener(key);
			frame.add(game); // 将面板添加到JFrame中
			frame.setSize(WIDTH, HEIGHT); // 设置大小
			frame.setAlwaysOnTop(true); // 设置其总在最上
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 默认关闭操作
			frame.setIconImage(new ImageIcon("images/icon.jpg").getImage()); // 设置窗体的图标
			frame.setLocationRelativeTo(null); // 设置窗体初始位置
			//frame.setLocationRelativeTo(frame);
			frame.setVisible(true); // 尽快调用paint

			game.action(); // 启动执行
		
	}
	
	
	public void action() {
		MouseAdapter mouse= new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				int x= e.getX();
				int y= e.getY();
				//hero.setPosition(x, y);
			}
			public void mouseClicked(MouseEvent e) {
				int x= e.getX();
				int y= e.getY();
				status= "START";
                herroMoveTo(x, y);
			}
			
		};
		this.addMouseListener(mouse); // 处理鼠标点击操作
		this.addMouseMotionListener(mouse); // 处理鼠标滑动操作
		
		timer= new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				if(status== "START") {
				  getHerroPosition();
				  checkBullet();
				  canAttack();
				  HerroStep();
				  bulletStep();					
				}

				repaint();
			}
			
		},10, 10);
		
		
	}
	
	public void checkBullet() {
		 for(int i= 0; i< Maxx; i++) {
			 if(i!= index&&((herroBullet[index].getX()==position[i][0]+ 15)&&(herroBullet[index].getY()== position[i][1]+ 15))) {
				  herroBullet[index].stillAlive= 0;
				  herroBullet[index].timeToBoom= 1;
			 }
		 }

	}
	public void bulletStep() {
		for(int i= 0; i< Maxx; i++) {
			if(herroBullet[i].getStillAlive()== 1) {
				herroBullet[i].step();
				
			}
				
		}
	}
	public void canAttack() {
		for(int i= 0 ; i< Maxx ; i++) {
			if(i!= index&&(position[index][0]== position[i][0]||position[index][1]== position[i][1])) {
				if(herroBullet[i].getStillAlive()== 0) {
				  herroBullet[index].setStillAlive();
				  herroBullet[index].setBullet(position[index][0], position[index][1]);
				
				  if(position[index][1]== position[i][1])
				  herroBullet[index].setToX(position[index][0]>  position[i][0]? -1: 1);
			      if(position[index][0]== position[i][0])
				  herroBullet[index].setToY(position[index][1]>  position[i][1]? -1: 1);	
			      
			     
				}

			}
		}
	}
	public void herroMoveTo(int x, int y) {
		if(index== 0) {
    		assassinA.moveTo(x, y); 	
			assassinA.setStillMove();
		}
		else if(index== 1) {
			assassinB.moveTo(x, y);		
			assassinB.setStillMove();
		}
	
	}   
	
	public void HerroStep() {
		if(assassinA.getStillMove()== 0) 
			assassinA.step();
		if(assassinB.getStillMove()== 0)
			assassinB.step();
	}
	
	//获取英雄的X坐标，Y坐标
	public void getHerroPosition() {
		  position[0][0]= assassinA.getX(); position[0][1]= assassinA.getY();
		  position[1][0]= assassinB.getX(); position[1][1]= assassinB.getY();
	}
}
