import org.newdawn.slick.*;
import java.util.Random;

public class Main extends BasicGame{

	private int PX = 50, PY = 660;
	private int score = 0;
	private float VX, VY, gravity = 0.5f;
	private boolean jumping = true;
	private boolean plat1 = false;
	private boolean plat2 = false;
	private boolean plat3 = false;
	private boolean plat4 = false;
	private Image BG;
	private Image DS;
	private Image Block;
	Animation player;
	Animation left, right, jump;
	Random rand = new Random();
	
	//random X quad 1
	int x1 = rand.nextInt(480);
	
	//random Y quad 1
	int y1 = 500;
	
	//random X quad 2
	int x2 = rand.nextInt(300);
	
	//random Y quad 2
	int y2 = 310;
	
	//random X quad 3
	int x3 = rand.nextInt(150);
	
	//random Y quad 3
	int y3 = 100;
	
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
		Block.draw(0,660);
		
		
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
		}
		
	}

	public void init(GameContainer arg0) throws SlickException 
	{
		BG = new Image("res/cloudBackground.png");
		DS = new Image("res/DeathScreen.png");
		Block = new Image("res/Platform.png");
		Image [] pyro = {new Image("res/sprite1.png"), new Image("res/sprite2.png"), new Image("res/sprite3.png")};
		left = new Animation(pyro, 150, false);
		Image [] pyro2 = {new Image("res/sprite4.png"), new Image("res/sprite5.png"), new Image("res/sprite6.png")};
		right = new Animation(pyro2, 150, false);
		player = right;
		Image [] pyro3 = {new Image("res/sprite7.png")};
		jump = new Animation(pyro3, 150, false);
		
	}

	public void update(GameContainer arg0, int arg1) throws SlickException 
	{
		Input input = arg0.getInput();
		
		PX += VX;
		PY += VY;
		VY += gravity;
		
		//PlatForm1
		if(PX > x1 && PX < x1+220 && PY > y1 && PY < y1+20)
		{
			plat1=true;
		}
		else
		{
			plat1=false;
		}
		
		//platform2
		if(PX > x2 && PX < x2+220 && PY > y2 && PY < y2+20)
		{
			plat2=true;
		}
		else
		{
			plat2=false;
		}
		
		//platform3
		if(PX > x3 && PX < x3+220 && PY > y3 && PY < y3+20)
		{
			plat3=true;
		}
		else
		{
			plat3=false;
		}
		//platform4
		if(PX > 0 && PX < 0+220 && PY > 660 && PY < 660+20)
		{
			plat4=true;
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
			VY=-15.0f;
		}
		
		else if(plat2 && PY > y2)
		{
			jumping = false;
			plat1=false;
			plat3=false;
			plat4=false;
			gravity = 0;
			PY = y2;
			VY=-15.0f;
		}
		
		else if(plat3 && PY > y3)
		{
			jumping = false;
			plat1=false;
			plat2=false;
			plat4=false;
			gravity = 0;
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
			
			PY=640;
			PX=50;
			VY = -1.0f;
			score++;
		}
		
		else if(plat4 && PY > 660)
		{
			jumping = false;
			plat1=false;
			plat2=false;
			plat3=false;
			gravity = 0;

			PY = 660;
			VY=-15.0f;
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
			
			PY=640;
			PX=50;
			VY = -1.0f;
			score=0;
		}
		
		
		
		
	}

}
