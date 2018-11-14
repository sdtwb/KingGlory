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
      BufferedImage images[];
      
      Bullet(){
    	  this.stillAlive= 0;
    	  this.toX= 0;
    	  this.toY= 0;
    	  this.x= 0;
    	  this.y= 0;
    	  this.timeToBoom= 0;
    	  this.images= new BufferedImage[imageSum];
    	  
    	  try {
			this.images[0]= ImageIO.read(Bullet.class.getResource("bullet-Up.png"));
			this.images[1]= ImageIO.read(Bullet.class.getResource("bullet-Down.png"));
			this.images[2]= ImageIO.read(Bullet.class.getResource("bullet-Left.png"));
			this.images[3]= ImageIO.read(Bullet.class.getResource("bullet-Right.png"));
			
			this.images[4]= ImageIO.read(Bullet.class.getResource("bullet-Boom.png"));
		  } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		  }
      }
      
      
      public BufferedImage getImage() {
           if(this.toX!= 0)
    		   return this.toX== -1? this.images[2]:this.images[3];
    	   else
    		   return this.toY== -1? this.images[0]: this.images[1];
    		   
    		  
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
