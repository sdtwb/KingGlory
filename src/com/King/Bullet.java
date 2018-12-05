package com.King;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
public class Bullet {
	  public static final int imageSum= 6;
	  
      int stillAlive;
      int x;
      int y;
      int toX;
      int toY;
      int change= 0;
      int timeToBoom;
      int index;
      BufferedImage bullet[];
      BufferedImage water[];
      BufferedImage fire[];
      
      Bullet(){
    	  this.stillAlive= 1;
    	  this.toX= 0;
    	  this.toY= 0;
    	  this.x= 1000;
    	  this.y= 753;
    	  this.timeToBoom= 0;
    	  this.bullet= new BufferedImage[imageSum];
    	  this.water= new BufferedImage[imageSum];
    	  this.fire= new BufferedImage[imageSum];
    	  
    	  try {
			this.bullet[0]= ImageIO.read(Bullet.class.getResource("bullet-Up.png"));
			this.bullet[1]= ImageIO.read(Bullet.class.getResource("bullet-Down.png"));
			this.bullet[2]= ImageIO.read(Bullet.class.getResource("bullet-Left.png"));
			this.bullet[3]= ImageIO.read(Bullet.class.getResource("bullet-Right.png"));
			this.water[0]= ImageIO.read(Bullet.class.getResource("water-Up.png"));
			this.water[1]= ImageIO.read(Bullet.class.getResource("water-Down.png"));
			this.water[2]= ImageIO.read(Bullet.class.getResource("water-Left.png"));
			this.water[3]= ImageIO.read(Bullet.class.getResource("water-Right.png"));
			this.fire[0]= ImageIO.read(Bullet.class.getResource("fire-Up.png"));
			this.fire[1]= ImageIO.read(Bullet.class.getResource("fire-Down.png"));
			this.fire[2]= ImageIO.read(Bullet.class.getResource("fire-Left.png"));
			this.fire[3]= ImageIO.read(Bullet.class.getResource("fire-Right.png"));
			
			this.bullet[4]= ImageIO.read(Bullet.class.getResource("bullet-Boom.png"));
			this.water[4]= ImageIO.read(Bullet.class.getResource("water-Boom.png"));
		  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
      }
      public BufferedImage getBoom() {
    	  if(this.index== 4||this.index== 5)
    		   return this.water[4];
    	  else
    	       return bullet[4];
      }
      public void setIndex(int in) {
    	  this.index= in;
      }
      public BufferedImage getImage() {
    	  if(this.index== 0||this.index== 1) {
             if(this.toX!= 0)
    		   return this.toX== -1? this.bullet[2]:this.bullet[3];
    	     else
    		   return this.toY== -1? this.bullet[0]: this.bullet[1];   		  
    	  }
    	  else if(this.index== 2|| this.index== 3) {
              if(this.toX!= 0)
       		   return this.toX== -1? this.fire[2]:this.fire[3];
       	     else
       		   return this.toY== -1? this.fire[0]: this.fire[1];     		  
    	  }
    	  else {
              if(this.toX!= 0)
       		   return this.toX== -1? this.water[2]:this.water[3];
       	     else
       		   return this.toY== -1? this.water[0]: this.water[1];     		  
    	  }

    		   
    		  
      }
      public void setStillAlive() {
    	  this.stillAlive= 1;
    	  this.toX= 0;
    	  this.toY= 0;
      }
      public int getStillAlive() {
    	  return this.stillAlive;
      }
      public int getX() {
    	  return x;
      }
      public int getY() {
    	  return y;
      }
      public void setToX(int a) {
    	 
    	  toX= a;
      }
      public void setToY(int a) {
    	
    	  toY= a;
      }
      public void setBullet(int a, int b) {
    	  this.x= a+ 15;
    	  this.y= b+ 15;
      }
      public void step() {
    	  this.x+= this.toX;
    	 
    	  this.y+= this.toY;

    	  if(this.x<= 0||this.x> 999) {
    		  this.toX= 0;
    		  this.toY= 0;
    		  this.stillAlive= 0;
    	  }
    	 if(this.y> 750||this.y<= 0) {
   		      this.toX= 0;
   		      this.toY= 0;
   		      this.stillAlive= 0;    		 
    	 }
      }
}
