//--------------------------------------------------------------------------------------------
public class Player extends AnimatedSprite{
  
  boolean inPlace, onPlatform;
  PImage[] walkLeft, walkRight, neutral;
//----------------------------
  public Player(PImage img, float scale){
    super(img, scale);
    
    direction = NEUTRAL_FACING;
    onPlatform = true;
    inPlace = true;
    
    neutral = new PImage[1];
    neutral[0] = loadImage("sprite.png");
    
    walkRight = new PImage[8];
    walkRight[0] = loadImage("character_robot_walk0.png");
    walkRight[1] = loadImage("character_robot_walk1.png");
    walkRight[2] = loadImage("character_robot_walk2.png");
    walkRight[3] = loadImage("character_robot_walk3.png");
    walkRight[4] = loadImage("character_robot_walk4.png");
    walkRight[5] = loadImage("character_robot_walk5.png");
    walkRight[6] = loadImage("character_robot_walk6.png");
    walkRight[7] = loadImage("character_robot_walk7.png");
    
    walkLeft = new PImage[8];
    walkLeft[0] = loadImage("character_robot_walk0_L.png");
    walkLeft[1] = loadImage("character_robot_walk1_L.png");
    walkLeft[2] = loadImage("character_robot_walk2_L.png");
    walkLeft[3] = loadImage("character_robot_walk3_L.png");
    walkLeft[4] = loadImage("character_robot_walk4_L.png");
    walkLeft[5] = loadImage("character_robot_walk5_L.png");
    walkLeft[6] = loadImage("character_robot_walk6_L.png");
    walkLeft[7] = loadImage("character_robot_walk7_L.png");
    
    currentImages = neutral;
  }
 //---------------------------- 
  @Override
  public void updateAnimation(){
      onPlatform = isOnPlatforms(this, platforms);
      inPlace = change_x == 0 && change_y == 0;
      
      super.updateAnimation();
  }
//----------------------------
  @Override
  public void selectDirection(){
    if(change_x > 0){
      direction = RIGHT_FACING;
    }
    else if(change_x < 0){
      direction = LEFT_FACING;
    }
      //else{
      // direction = NEUTRAL_FACING;
    //}
  }
//----------------------------
  @Override
  public void selectCurrentImages(){
    if(direction == RIGHT_FACING){
      if(inPlace){
        currentImages = neutral;
      }
      else{
        currentImages = walkRight;
      }
    }
    
    else if(direction == LEFT_FACING){
      if(inPlace){
        currentImages = neutral;
      }
      else{
        currentImages = walkLeft;
      }
    }
  }
//----------------------------
}
//--------------------------------------------------------------------------------------------
