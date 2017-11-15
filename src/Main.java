import java.io.IOException;
import java.util.Random;
import java.lang.Object;
import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Music;

public class Main extends BasicGame{

	private int PX = 50, PY = 640;
	private int score = 0;
	private float VX, VY, gravity = 0.5f;
	private boolean jumping = true;
	private boolean plat1 = false;
	private boolean plat2 = false;
	private boolean plat3 = false;
	private boolean plat4 = false;
	private boolean left1 = false;
	private boolean left2 = false;
	private boolean left3 = false;
	private boolean left4 = false;
	private Image DS;
	private Image Block;
	Animation player;
	Animation left, right, jump, BG;
	Random rand = new Random();
	private int plat3move=rand.nextInt(2);
	private int plat2move=rand.nextInt(2);
	private int plat1move=rand.nextInt(2);
	private int plat4move=rand.nextInt(2);


	//random X quad 1
	int x1 = rand.nextInt(400);
	
	//random Y quad 1
	int y1 = 500;
	
	//random X quad 2
	int x2 = rand.nextInt(400);
	
	//random Y quad 2
	int y2 = 310;
	
	//random X quad 3
	int x3 = rand.nextInt(400);
	
	//random Y quad 3
	int y3 = 100;
	
	int x4 = 0;
	int y4 = 660;
	
	public Main() 
	{
		super("FirstPlatformer");
		
		
	}
	
	public static void main(String[] args) 
	{
		try {
			AppGameContainer app = new AppGameContainer(new Main());
			app.setDisplayMode(650,720,false);
			app.setTargetFrameRate(60);
			app.setShowFPS(false);
			app.start();
			
			
			}
		catch(Exception e)
		{
			
		}
	}

	public void render(GameContainer arg0, Graphics arg1) throws SlickException
	{
		
		BG.draw(0,0);
		//block1
		Block.draw(x1,y1);
		//block2
		Block.draw(x2,y2);
		//block3
		Block.draw(x3,y3);
		//block4	
		Block.draw(x4,y4);
		
		
		arg1.setColor(Color.white);
		arg1.drawString("Score: "+score, 10, 10);
//		arg1.drawString(PY+"", 10, 10);
//		arg1.setColor(Color.white);
//		arg1.drawString(PX+"", 50, 10);
		//player
		player.draw(PX,PY-30);
		
		
		if(PY > 690)
		{
			DS.draw(0,0);
			jumping=true;
			arg1.setColor(Color.white);
			arg1.drawString("Final Score: "+score,260, 400);
		}
		
	}

	public void init(GameContainer arg0) throws SlickException 
	{
		Music music = new Music("res/song.ogg");
		music.play();
		DS = new Image("res/DeathScreen.png");
		Block = new Image("res/Platform.png");
		Image [] pyro = {new Image("res/sprite1.png"), new Image("res/sprite2.png"), new Image("res/sprite3.png")};
		left = new Animation(pyro, 150, false);
		Image [] pyro2 = {new Image("res/sprite4.png"), new Image("res/sprite5.png"), new Image("res/sprite6.png")};
		right = new Animation(pyro2, 150, false);
		player = right;
		Image [] pyro3 = {new Image("res/sprite7.png")};
		jump = new Animation(pyro3, 150, false);
		Image [] scroll = {new Image("res/cloudBackground44.png"),new Image("res/cloudBackground43.png"),new Image("res/cloudBackground41.png")
				,new Image("res/cloudBackground40.png"),new Image("res/cloudBackground39.png"),new Image("res/cloudBackground38.png")
				,new Image("res/cloudBackground37.png"),new Image("res/cloudBackground36.png"),new Image("res/cloudBackground35.png")
				,new Image("res/cloudBackground34.png"),new Image("res/cloudBackground33.png"),new Image("res/cloudBackground32.png")
				,new Image("res/cloudBackground31.png"),new Image("res/cloudBackground30.png"),new Image("res/cloudBackground29.png")
				,new Image("res/cloudBackground28.png"),new Image("res/cloudBackground27.png"),new Image("res/cloudBackground26.png")
				,new Image("res/cloudBackground25.png"),new Image("res/cloudBackground24.png"),new Image("res/cloudBackground23.png")
				,new Image("res/cloudBackground22.png"),new Image("res/cloudBackground21.png"),new Image("res/cloudBackground20.png")
				,new Image("res/cloudBackground19.png"),new Image("res/cloudBackground18.png"),new Image("res/cloudBackground17.png")
				,new Image("res/cloudBackground16.png"),new Image("res/cloudBackground15.png"),new Image("res/cloudBackground14.png")
				,new Image("res/cloudBackground13.png"),new Image("res/cloudBackground12.png"),new Image("res/cloudBackground11.png")
				,new Image("res/cloudBackground10.png"),new Image("res/cloudBackground9.png"),new Image("res/cloudBackground8.png")
				,new Image("res/cloudBackground7.png"),new Image("res/cloudBackground6.png"),new Image("res/cloudBackground5.png")
				,new Image("res/cloudBackground4.png"),new Image("res/cloudBackground3.png"),new Image("res/cloudBackground2.png")
				,new Image("res/cloudBackground.png")};	
		BG = new Animation(scroll, 50, true);	
		if(PY>690)
		{
			music.stop();
		}
}
		
	
	//ShutDown Method
	public static void shutdown() throws RuntimeException, IOException {
	    String shutdownCommand;
	    String operatingSystem = System.getProperty("os.name");

	    if ("Linux".equals(operatingSystem) || "Mac OS X".equals(operatingSystem)) {
	        shutdownCommand = "sudo shutdown -h now";
	    }
	    else if ("Windows".equals(operatingSystem)) {
	        shutdownCommand = "shutdown.exe -s -t 0";
	    }
	    else {
	        throw new RuntimeException("Unsupported operating system.");
	    }
   
	    Runtime.getRuntime().exec(shutdownCommand);
	    System.exit(0);
	}
	
	public void update(GameContainer arg0, int arg1) throws SlickException 
	{
		
		if(plat3move==1) {
		if(left3)
		{
		x3-=3;	
			if(x3<-40)	
			{
			x3=-40;
			left3=false;
			}
		}
		if(!left3)
		{
		x3+=3;
			if(x3>450)
			{
				x3=430;
				left3=true;
			}
		}
		}
		if(plat2move==1) {
			if(left2)
			{
			x2-=3;	
				if(x2<-40)	
				{
				x2=-40;
				left2=false;
				}
			}
			if(!left2)
			{
			x2+=3;
				if(x2>450)
				{
					x2=430;
					left2=true;
				}
			}
			}
		if(plat1move==1) {
			if(left1)
			{
			x1-=3;	
				if(x1<-40)	
				{
				x1=-40;
				left1=false;
				}
			}
			if(!left1)
			{
			x1+=3;
				if(x1>450)
				{
					x1=430;
					left1=true;
				}
			}
			}if(plat4move==1) {
				if(left4)
				{
				x4-=3;	
					if(x4<-40)	
					{
					x4=-40;
					left4=false;
					}
				}
				if(!left4)
				{
				x4+=3;
					if(x4>450)
					{
						x4=430;
						left4=true;
					}
				}
				}
		
		//If score is 100 then call shutdown method
		if(score==100)
		{
		try {
			shutdown();
		} catch (RuntimeException | IOException e) {
			e.printStackTrace();
		}
		}
		
		Input input = arg0.getInput();
		
		PX += VX;
		PY += VY;
		VY += gravity;
		
		//PlatForm1
		if(PX > x1 && PX < x1+220 && PY > y1 && PY < y1+20)
		{
			plat1=true;
			jumping=false;
			gravity=0.00f;
			if(left1&&plat1move==1)
			{
				PX-=3;
			}
			else if(!left1&&plat1move==1){
			PX+=3;}
		}
		else
		{
			plat1=false;
			jumping=true;
			gravity=15.0f;
		}
		
		//platform2
		if(PX > x2 && PX < x2+220 && PY > y2 && PY < y2+20)
		{
			plat2=true;
			jumping=false;
			gravity=0.00f;
			if(left2&&plat2move==1)
			{
				PX-=3;
			}
			else if(!left2&&plat2move==1){
			PX+=3;}
		}
		else
		{
			plat2=false;
			jumping=true;
			gravity=15.0f;
		}
		
		//platform3
		if(PX > x3 && PX < x3+220 && PY > y3 && PY < y3+20)
		{
			plat3=true;
			jumping=false;
			gravity=0.00f;
			if(left3&&plat3move==1)
			{
				PX-=3;
			}
			else if(!left3&&plat3move==1){
			PX+=3;}
		}
		else
		{
			plat3=false;
			jumping=true;
			gravity=15.0f;
		}
		//platform4
		if(PX > x4 && PX < x4+220 && PY > y4 && PY < y4+20)
		{
			plat4=true;
			if(left4&&plat4move==1)
			{
				PX-=3;
			}
			else if(!left4&&plat4move==1){
			PX+=3;}
		}
		else
		{
			plat4=false;
		}
		
		
		
		//ground
		if(plat1 && PY > y1)
		{
			jumping = false;
			plat2= false;
			plat3=false;
			plat4=false;
			gravity = 0;
			PY = y1;
//			VY=-15.0f;
		}
		
		else if(plat2 && PY > y2)
		{
			jumping = false;
			plat1=false;
			plat3=false;
			plat4=false;
			gravity = 0;
			PY = y2;
//			VY=-15.0f;
		}
		
		else if(plat3 && PY > y3)
		{
			jumping = false;
			plat1=false;
			plat2=false;
			plat4=false;
			gravity = 0;
			plat3move=rand.nextInt(2);
			plat1move=rand.nextInt(2);
			plat2move=rand.nextInt(2);
			plat4move=rand.nextInt(2);
			x4=x3;
			
			//random X quad 1
			x1 = rand.nextInt(480);
			
			//random Y quad 1
			y1 = 500;
			
			//random X quad 2
			x2 = rand.nextInt(300);
			
			//random Y quad 2
			y2 = 310;
			
			//random X quad 3
			x3 = rand.nextInt(150);
			
			//random Y quad 3
			y3 = 100;
			
			
			
			PY=y4-20;
			PX=x4+80;
			VY = -1.0f;
			score++;
		}
		
		else if(plat4 && PY > y4)
		{
			jumping = false;
			plat1=false;
			plat2=false;
			plat3=false;
			gravity = 0;

			VY=-0.00001f;
		}
		else
		{
			gravity = 0.5f;
		}
		
		
		//main ground
//		if(PY > 690)
//		{
//			PY = 690;
//			jumping = false;
//		}
		
		if(input.isKeyDown(Input.KEY_LEFT))
		{
			PX -=7;
			player = left;
			player.update(arg1);
		}
		if(input.isKeyDown(Input.KEY_RIGHT))
		{
			PX +=7;
			player = right;
			player.update(arg1);

		}
		if(input.isKeyDown(Input.KEY_SPACE)&& !jumping) 
		{
			VY = -15.0f;
			jumping = true;
			player = jump;
			player.update(arg1);

		}
		if(PX<0)
		{
			PX=0;
		}
		if(PX>620)
		{
			PX=620;
		}
		
		if(PY>690&&input.isKeyDown(Input.KEY_ENTER))
		{
			//random X quad 1
			x1 = rand.nextInt(400);
			
			//random Y quad 1
			y1 = 500;
			
			//random X quad 2
			x2 = rand.nextInt(400);
			
			//random Y quad 2
			y2 = 310;
			
			//random X quad 3
			x3 = rand.nextInt(400);
			
			//random Y quad 3
			y3 = 100;
			plat3move=rand.nextInt(2);
			plat1move=rand.nextInt(2);
			plat2move=rand.nextInt(2);
			plat4move=rand.nextInt(2);
			
			x4=0;
			y4=660;
			
			PX=50;
			PY=640;
			VY = -1.0f;
			score=0;
			
			
		}
		
	}

}
