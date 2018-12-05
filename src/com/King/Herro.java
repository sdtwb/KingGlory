package com.King;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Herro {
      int x;
      int y;
      int life;
      int isAlive;
      int moveToX;
      int moveToY;
      int stillMove;
      int beAttack;
      int times;
      int isblock;
      BufferedImage image;
      final int Long= 1000;
      final int Width= 1200;
 	  StringBuffer  [] block= new StringBuffer[Long];
      Herro(int xx, int yy){
    	  x= xx;
    	  y= yy;
    	  isAlive= 1;
    	  stillMove= 0;
    	  life= 200;
    	  beAttack= 0;
    	  times= 0;
    	  this.isblock= 0;
    	  this.getData(".\\src\\com\\King\\block.jpg");
//    	  for(int i= 0; i< this.Long; i++)
//    		  System.out.println(this.block[i]);
      }
      public void getData(String path) {
          try {
          for(int i= 0; i< this.Long; i++)
        	   block[i]= new StringBuffer("");
          //获取图像资源，转成BufferedImage对象
          BufferedImage bimg = ImageIO.read(new File(path));
          //创建一个二维数组，用来存放图像每一个像素位置的颜色值
          int[][] data = new int[bimg.getHeight()][bimg.getWidth()];
          //以高度为范围，逐列扫描图像，存进数组对应位置
          for (int i = 0; i < bimg.getWidth(); i++) {
          for (int j = 0; j < bimg.getHeight(); j++) {
                data[j][i] = bimg.getRGB(i, j);//得到的是sRGB值，4字节
               // System.out.println(data[i][j]);
          }
          }
          //将数组旋转90°输出，实现逐行输出图像
          //System.out.println(bimg.getHeight()+" "+ bimg.getWidth());
          for (int i = 0; i < bimg.getHeight(); i++) {
              for (int j = 0; j < bimg.getWidth(); j++) {
                  if (data[i][j] != -1) {//有颜色输出
                        block[i].append("1");
	  		        } else { //无颜色输出
	  		                block[i].append("0");
	  		        }
               }
          }
          } catch (IOException e) {
              e.printStackTrace();
          }
       }

     public void addTimes() {
    	 if(this.isAlive== 0)
    		  this.times++;
    	 if(this.times== 500) {
    		 this.times= 0;
    		 this.isAlive= 1;
    		 this.beAttack= 0;
    	 }
    		  
     }
     public void beAttack() {
    	 this.beAttack++;
    	 if(this.beAttack>= 1)
    		  this.isAlive= 0;
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
           
	      	 if(this.x< moveToX) 
	    		 this.x++;
	    	 else if(this.x> moveToX)
	    		 this.x--;
	    	 
	    	 if(this.y< moveToY)
	    		 this.y++;
	    	 else if(this.y> moveToY)
	    		 this.y--;
	    	 
	    	 if(this.block[this.y+ 40].charAt(this.x+ 40)== '1')
	    		 this.isblock= 1;
	    	 else 
	    		 this.isblock= 0;
//	    	 System.out.print(this.x+ 40);
//	    	 System.out.print(" ");
//	    	 System.out.println(this.y+ 40);
     }
     public int getX() {
    	 return this.x;
     }
     public int getY() {
    	 return this.y;
     }
     public int getIsAlive() {
    	 return this.isAlive;
     }
     public void setStillMove() {
    	 this.stillMove= this.stillMove== 0?1: 0;
     }
     public int getStillMove() {
    	 if(this.stillMove== 1&&this.isblock== 0)
    		 return 1;
    	 else
    		 return 0;
     }
     public void autoMove() {
    	  if((int)(10*Math.random())%10== 3){
    		  this.x+= 3;
    		  System.out.println(Math.random());
    		  if((int)(10*Math.random())% 2== 3)
    			  this.x-= 2;
    		  if(this.x>1000)
    			   this.x= 1000;
    		  if(this.x< 0)
    			   this.x= 0;
    	  }
    	  if((int)(10*Math.random())%10== 4){
    		  this.y++;
    		  if((int)(10*Math.random())%10== 4)
    			  this.y-= 2;
    		  if(this.y> 1000)
    			   this.y= 1000;
    		  if(this.y< 0)
    			   this.y= 0;
    	  }
    	  
    	  
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
    	 if(this.block[this.y+ 40].charAt(this.x+ 40)== '1')
    		 this.isblock= 1;
    	 else 
    		 this.isblock= 0;

    	 if((this.x== moveToX&&this.y== moveToY)) {
    		 this.stillMove= 0;
    		// System.out.println(this.block[this.x].charAt(this.y));
    	 }
    		 
    	 
     }
     
}
