package com.King;

import java.awt.image.BufferedImage;

public class Herro {
      int x;
      int y;
      int life;
      int isAlive;
      int moveToX;
      int moveToY;
      int stillMove;
      BufferedImage image;
      Herro(int xx, int yy){
    	  x= xx;
    	  y= yy;
    	  isAlive= 1;
    	  stillMove= 0;
    	  life= 200;
      }
     public void setImage(BufferedImage im) {
    	 image= im;
     }
     public BufferedImage getImage() {
    	 return image;
     }
     public void moveTo(int xx, int yy) {
    	   this.stillMove= 1;
           moveToX= xx;
           moveToY= yy;
     }
     public int getX() {
    	 return this.x;
     }
     public int getY() {
    	 return this.y;
     }
     public int getIsAlive() {
    	 return isAlive;
     }
     public void setStillMove() {
    	 this.stillMove= this.stillMove== 0?1: 0;
     }
     public int getStillMove() {
    	 return stillMove;
     }
     public void step() {
    	 if(this.x< moveToX) 
    		 this.x++;
    	 else if(this.x> moveToX)
    		 this.x--;
    	 
    	 if(this.y< moveToY)
    		 this.y++;
    	 else if(this.y> moveToY)
    		 this.y--;
    	 
    	 //如果英雄到了那个点则改变控制运动的变量stillMove
    	 if(this.x== moveToX&&this.y== moveToY)
    		 this.stillMove= 0;
    	 
     }
     
}
