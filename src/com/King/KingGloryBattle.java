package com.King;


import java.awt.Font;
import java.awt.BorderLayout;
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Wenbiao Tan
 */
public class KingGloryBattle extends JPanel implements ActionListener{


	public static final int WIDTH = 1000; // ����
	public static final int HEIGHT = 764; // ����
	public static final int Maxx= 15;
	public static final int herroSize= 80;
	
	public static BufferedImage background;
	public static BufferedImage people1;
	public static BufferedImage people2;
	public static BufferedImage people3;
	public static BufferedImage people4;
	public static BufferedImage people5;
	public static BufferedImage people6;
	public static BufferedImage bulletImage;
	public static Timer timer;
	public static int index;
	public static int position[][];
	public static Bullet herroBullet[];
	public static String status;
	public static int herroSum;
	//Ӣ��
	public static Assassin assassinA;
	public static Assassin assassinB;
	public static  Fighter fighterA;
	public static  Fighter fighterB;	
	public static  Robbi robbiA;
	public static  Robbi robbiB;
	static {
		try {
			background= ImageIO.read(KingGloryBattle.class.getResource("map.jpg"));
			people1= ImageIO.read(KingGloryBattle.class.getResource("people-LuBan.png"));
			people2= ImageIO.read(KingGloryBattle.class.getResource("people-HouYi.png"));
			people3= ImageIO.read(KingGloryBattle.class.getResource("people-HouZi.png"));
			people4= ImageIO.read(KingGloryBattle.class.getResource("people-YanJian.png"));
			people5= ImageIO.read(KingGloryBattle.class.getResource("people-BianQue.png"));
			people6= ImageIO.read(KingGloryBattle.class.getResource("people-ZhuGe.png"));
			
			assassinA= new Assassin(400, 400);
			assassinB= new Assassin(330, 330);
			fighterA= new Fighter(0, 0);
			fighterB= new Fighter(0, 0);	
			robbiA= new Robbi(0, 0);
			robbiB= new Robbi(0, 0);
			
			
			assassinA.setImage(people1);
			assassinB.setImage(people2);
			fighterA.setImage(people3);
			fighterB.setImage(people4);
			robbiA.setImage(people5);
			robbiB.setImage(people6);
			
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
	
	/**
	 * ��ͼƬ�滭�ϴ���
	 */
	public void paint(Graphics g) {
		
		try {
			Thread.sleep(50);
		}catch(Exception e) {
			
		}
		g.drawImage(background, 0, 0, null); // ������ͼ
		paintHerro(g);
		paintBullet(g);
	}
	
	/**
	 * �滭ը��
	 * @param g ͬһ��ͼƬ
	 */
	public void paintBullet(Graphics g) {
		for(int i= 0; i< Maxx; i++) {
			if(herroBullet[i].getStillAlive()== 1)
				g.drawImage(herroBullet[i].getImage(), herroBullet[i].getX(), herroBullet[i].getY(), null);
			if(herroBullet[i].timeToBoom== 1) {
				g.drawImage(herroBullet[i].getBoom(), herroBullet[i].getX(), herroBullet[i].getY(), null);
				herroBullet[i].timeToBoom= 0;
			}
		}
		
	}

	/**
	 * �滭Ӣ��
	 * @param g ͬһ��ͼƬ
	 */
	public void paintHerro(Graphics g) {
		if(assassinA.getIsAlive()== 1)
			g.drawImage(assassinA.getImage(), assassinA.getX(), assassinA.getY(), null);
		if(assassinB.getIsAlive()== 1)
			g.drawImage(assassinB.getImage(), assassinB.getX(), assassinB.getY(), null);
		if(fighterA.getIsAlive()== 1)
			g.drawImage(fighterA.getImage(), fighterA.getX(), fighterA.getY(), null);
		if(fighterB.getIsAlive()== 1)
			g.drawImage(fighterB.getImage(), fighterB.getX(), fighterB.getY(), null);
		if(robbiA.getIsAlive()== 1)
			g.drawImage(robbiA.getImage(), robbiA.getX(), robbiA.getY(), null);
		if(robbiB.getIsAlive()== 1)
			g.drawImage(robbiB.getImage(), robbiB.getX(), robbiB.getY(), null);
	}
	
	
	
//-----------------------------------------------------------------------	
	JFrame frame = new JFrame("������ҫ");
	JButton button0= new JButton("Herro0");
	JButton button1= new JButton("Herro1");
	JButton button2= new JButton("Herro2");
	JButton button3= new JButton("Herro3");
	JButton button4= new JButton("Herro4");
	JButton button5= new JButton("Herro5");
	JButton button6= new JButton("Herro6");
	JButton button7= new JButton("Herro7");
	JButton button8= new JButton("Herro8");
	JButton button9= new JButton("Herro9");
	JButton button10= new JButton("START");
	JPanel panel= new JPanel();

	static KeyAdapter key= new KeyAdapter() {
		    /**
		     * ���̰���
		     */
			   public  void keyPressed(KeyEvent e) {
	               
				   if(e.getKeyCode()== KeyEvent.VK_0)
				        index= 0;
				   else if(e.getKeyCode()== KeyEvent.VK_1) {
					   index= 1;
				   }
				   else if(e.getKeyCode()== KeyEvent.VK_2) {
					   index= 2;
				   }
				   else if(e.getKeyCode()== KeyEvent.VK_3) {
					   index= 3;
				   }
				   else if(e.getKeyCode()== KeyEvent.VK_4) {
					   index= 4;
				   }
				   else if(e.getKeyCode()== KeyEvent.VK_5) {
					   index= 5;
				   }
				   else if(e.getKeyCode()== KeyEvent.VK_6) {
					   index= 6;
				   }
				   else if(e.getKeyCode()== KeyEvent.VK_7) {
					   index= 7;
				   }
				   else if(e.getKeyCode()== KeyEvent.VK_8) {
					   index= 8;
				   }
				   else if(e.getKeyCode()== KeyEvent.VK_9) {
					   index= 9;
				   }
				   else if(e.getKeyCode()== KeyEvent.VK_S) {
					    status= "START";
				   }
			   }
			   

				public void keyReleased(KeyEvent e) {}
				public void keyTyped(KeyEvent e) {}
			   
			};
	public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(button0== e.getSource()) {
					index= 0;
				}
				else if(button1== e.getSource()) {
					index= 1;
				}
				else if(button2== e.getSource()) {
					index= 2;
				}
				else if(button3== e.getSource()) {
					index= 3;
				}
				else if(button4== e.getSource()) {
					index= 4;
				}
				else if(button5== e.getSource()) {
					index= 5;
				}
				else if(button6== e.getSource()) {
					index= 6;
				}
				else if(button7== e.getSource()) {
					index= 7;
				}
				else if(button8== e.getSource()) {
					index= 8;
				}
				else if(button9== e.getSource()) {
					index= 9;
				}
				else if(button10== e.getSource()) {
					status= "START";
				}

				
	}
	
	/**
	 * ������ҫ������������
	 * @param args û����
	 */
    public void Button() {
    	button0.addActionListener(this);button0.setMnemonic(KeyEvent.VK_0);
    	button1.addActionListener(this);button1.setMnemonic(KeyEvent.VK_1);
    	button2.addActionListener(this);button2.setMnemonic(KeyEvent.VK_2);
    	button3.addActionListener(this);button3.setMnemonic(KeyEvent.VK_3);
    	button4.addActionListener(this);button4.setMnemonic(KeyEvent.VK_4);
    	button5.addActionListener(this);button5.setMnemonic(KeyEvent.VK_5);
    	button6.addActionListener(this);button6.setMnemonic(KeyEvent.VK_6);
    	button7.addActionListener(this);button7.setMnemonic(KeyEvent.VK_7);
    	button8.addActionListener(this);button8.setMnemonic(KeyEvent.VK_8);
    	button9.addActionListener(this);button9.setMnemonic(KeyEvent.VK_9);
    	button10.addActionListener(this);button10.setMnemonic(KeyEvent.VK_Q);

    	panel.add(button10);
		panel.add(button0);
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		panel.add(button5);
		panel.add(button6);
		panel.add(button7);
		panel.add(button8);
		panel.add(button9);
		
		
    }
    
    static KingGloryBattle game = new KingGloryBattle(); 
    public void Frame() {
			frame.addKeyListener(key);
			panel.add(game);
			frame.setLayout(new BorderLayout());
			frame.add(panel,BorderLayout.NORTH);
			frame.add(game, BorderLayout.CENTER);
			//frame.add(game); // �������ӵ�JFrame��
			frame.setSize(WIDTH, HEIGHT); // ���ô�С
			frame.setAlwaysOnTop(true); // ��������������
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ĭ�Ϲرղ���
			frame.setIconImage(new ImageIcon("images/icon.jpg").getImage()); // ���ô����ͼ��
			frame.setLocationRelativeTo(null); // ���ô����ʼλ��
			//frame.setLocationRelativeTo(frame);
			frame.setVisible(true); // �������paint

			game.action(); // ����ִ��    	
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		   // ������
         index= 0; 

         game.Button();
         game.Frame();
	}
	
	/**
	 * ��Ҫ�̺߳���
	 */
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
				//status= "START";
                herroMoveTo(x, y);
			}
			
		};
		this.addMouseListener(mouse); // �������������
		this.addMouseMotionListener(mouse); // ������껬������
		
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
	
	/**
	 * �ж�ը��ʱ����Ա�ը
	 */
	public void checkBullet() {
		 for(int i= 0; i< Maxx; i++) {
			 if(i!= index&&((herroBullet[index].getX()==position[i][0]+ 15)&&(herroBullet[index].getY()== position[i][1]+ 15))) {
				  herroBullet[index].stillAlive= 0;
				  herroBullet[index].timeToBoom= 1;
			 }
		 }

	}
	/**
	 * ִ��ը������һ��
	 */
	public void bulletStep() {
		for(int i= 0; i< Maxx; i++) {
			if(herroBullet[i].getStillAlive()== 1) {
				herroBullet[i].step();
				
			}
				
		}
	}
	
	/**
	 * �ж�ը���Ƿ���Ϸ�������
	 */
	public void canAttack() {
		for(int i= 0 ; i< Maxx ; i++) {
			if(i!= index&&(position[index][0]== position[i][0]||position[index][1]== position[i][1])) {
				if(herroBullet[i].getStillAlive()== 0) {
				  herroBullet[index].setStillAlive();
				  herroBullet[index].setIndex(index);
				  herroBullet[index].setBullet(position[index][0], position[index][1]);
				
				  if(position[index][1]== position[i][1])
				  herroBullet[index].setToX(position[index][0]>  position[i][0]? -1: 1);
			      if(position[index][0]== position[i][0])
				  herroBullet[index].setToY(position[index][1]>  position[i][1]? -1: 1);	
			      
			     
				}

			}
		}
	}
	
	/**
	 * Ӣ���Ƶ�
	 * @param x Ӣ���ƶ�����X����
	 * @param y Ӣ���ƶ�����Y����
	 */
	public void herroMoveTo(int x, int y) {
		if(index== 0) {
    		assassinA.moveTo(x, y); 	
			assassinA.setStillMove();
		}
		else if(index== 1) {
			assassinB.moveTo(x, y);		
			assassinB.setStillMove();
		}
		else if(index== 2) {
			fighterA.moveTo(x, y);		
			fighterA.setStillMove();
		}
		else if(index== 3) {
			fighterB.moveTo(x, y);		
			fighterB.setStillMove();
		}
		else if(index== 4) {
			robbiA.moveTo(x, y);		
			robbiA.setStillMove();
		}
		else if(index== 5) {
			robbiB.moveTo(x, y);		
			robbiB.setStillMove();
		}
	
	}   
	
	/**
	 * Ӣ�۵���һ��
	 */
	public void HerroStep() {
		if(assassinA.getStillMove()== 0) 
			assassinA.step();
		if(assassinB.getStillMove()== 0)
			assassinB.step();
		if(fighterA.getStillMove()== 0)
			fighterA.step();
		if(fighterB.getStillMove()== 0)
			fighterB.step();
		if(robbiA.getStillMove()== 0)
			robbiA.step();
		if(robbiB.getStillMove()== 0)
			robbiB.step();
	}
	
	/**
	 * ��ȡӢ�۵�X���꣬Y����
	 */
	public void getHerroPosition() {
		  position[0][0]= assassinA.getX(); position[0][1]= assassinA.getY();
		  position[1][0]= assassinB.getX(); position[1][1]= assassinB.getY();
		  position[2][0]= fighterA.getX(); position[2][1]= fighterA.getY();
		  position[3][0]= fighterB.getX(); position[3][1]= fighterB.getY();
		  position[4][0]= robbiA.getX(); position[4][1]= robbiA.getY();
		  position[5][0]= robbiB.getX(); position[5][1]= robbiB.getY();

	}
}