package com.King;

public class Herro {
      int x;
      int y;
      int isAlive;
      int moveToX;
      int moveToY;
      int stillMove;
      Herro(){
//    	  x= 500;
//    	  y= 200;
    	  isAlive= 1;
    	  stillMove= 0;
      }
     void moveTo(int xx, int yy) {
    	   this.stillMove= 1;
           moveToX= xx;
           moveToY= yy;
     }
     int getX() {
    	 return this.x;
     }
     int getY() {
    	 return this.y;
     }
     int getIsAlive() {
    	 return isAlive;
     }
     void setStillMove() {
    	 this.stillMove= this.stillMove== 0?1: 0;
     }
     int getStillMove() {
    	 return stillMove;
     }
     void step() {
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
