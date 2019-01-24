package Game2;

import java.io.File;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage; 

/**
 * <h1> Snake vs Block Game </h1>
 * <h2> AP Final Project </h2>
 * <b>
 * About Project
 * </b>
 * <p>
 * As our final project for Advanced Programming (AP) project, we have made
 * a replica of the famous game Snake vs Block. In this game, there is
 * a snake which the user can control to obtain tokens which increases the 
 * size of the snake. The player then collides with the snake against walls 
 * of varying lengths. The the bigger the block broken, the higher the score.
 * There are also various features such as Walls, Magnets, Shields and Bombs
 * in the game!
 * </p>
 * 
 * Date: 02/12/2018
 * @author Dashmesh,Chinmay
 * @since 03/10/2018
 * @version 1.0
 * @serial Serialize and Deserialize methods. Serialize Snake and Leadebord.
 */
public class Main extends Application{


	/** Displacement X */
	private int dispright = 10;
	private int displeft = 10;

	/** Audio files for various interactions */
	Media m1 = new Media(new File("C:\\Users\\Chinmay\\Desktop\\FP\\blop.mp3").toURI().toString());
	MediaPlayer pop = new MediaPlayer(m1);
	Media m2 = new Media(new File("C:\\Users\\Chinmay\\Desktop\\FP\\gun.mp3").toURI().toString());
	MediaPlayer bombsound = new MediaPlayer(m2);
	Media m3 = new Media(new File("C:\\Users\\Chinmay\\Desktop\\FP\\coin1.wav").toURI().toString());
	MediaPlayer coinsound = new MediaPlayer(m3);
	Media m4 = new Media(new File("C:\\Users\\Chinmay\\Desktop\\FP\\shield.wav").toURI().toString());
	MediaPlayer shieldsound = new MediaPlayer(m4);
	Media m5 = new Media(new File("C:\\Users\\Chinmay\\Desktop\\FP\\magnet.wav").toURI().toString());
	MediaPlayer magnetsound = new MediaPlayer(m4);
	
	/**
	 *  Random Generator Variable
	 */
	private Random r = new Random();
	private int random;
	
	/**
	 * time
	 */
	private AnimationTimer gameTime;
	
	/**
	 * Static Variable that giverns game speed
	 */ 
	static double speed = 1.5; 
	
	/**
	 * Score of the Player
	 * All temporary variables show the data retrieved from the files
	 */
	private int Score = 0;
	private int tempscore = 0;
	private int tempsize = 0;
	private int temppos = 250;
	
	/**
	 * variables for different scenes
	 */
	private Scene game;
	private Scene menu;
	private Scene board;
	
	/**
	 * the main stage for the game
	 */
	private Stage theStage; 
	
	/**
	 * Temporary StackPane & Pane
	 */
	private Pane temp = new Pane();
	private Pane Below;
	

	/**
	 * Button Variables 
	 */
	private Button menuu;
	private Button replay;
	private Button startGame ;
	private Button resumeGame;
	private Button leaderboard;
	private Button exit;	
	private Button BackButton;
	private Button Instructions;
	
	/**
	 * Combo Box ( Drop Down Option and score ) 
	 */
	private MenuBar dropdown = new MenuBar();
	private Menu menu1 = new Menu("PAUSE");
	private MenuItem item1;
	private MenuItem item2;
	private Label score = new Label();
	private Label ShieldTimer = new Label();
	private Label MagnetTimer = new Label();
	private HBox TopShow = new HBox();
	
	/**
	 * ArrayList for Blocks 
	 */
	private ArrayList<Blocks> blocks=  new ArrayList<Blocks>();
	
	/**
	 * ArrayList for Walls 
	 */
	private ArrayList<Walls> walls = new ArrayList<>();

	/**
	 * ArrayList for Balls 
	 */
	private ArrayList<Balls> balls = new ArrayList<>();
	private Balls tempb;

	
	/**
	 * ArrayList for Magnets 
	 */
	private ArrayList<Magnets> magnet = new ArrayList<>();
	private Magnets tempm;

	/**
	 *  for Shields 
	 */
	private ArrayList<Shields> shield = new ArrayList<>();
	private Shields temps;
	private Circle C1 = new Circle(75);
	
	/**
	 * ArrayList for Bombs (DestroyBlocks).
	 */
	private ArrayList<DestryBlocks> bombs = new ArrayList<>();
	private DestryBlocks tempbomb;
	
	/**
	 * Variables for Timer objects for Magnet and Shields
	 */
	private double starttimemagnet;
	private double currenttimemagnet;
	private boolean magnetflag;
	private double starttimeshield;
	private double currenttimeshield;
	private boolean shieldflag;
	
	/**
	 * Background setting variables.
	 */
	private BackgroundSize backgroundSize;
	private BackgroundImage backgroundImage; 
	private Background background;
	
	/**
	 * Database and record variables for the Leaderboard.
	 */
	private LeaderBoard database = new LeaderBoard();
	private ArrayList<Player> records;
	
	/**
	 * Snake Object and Corresponding Images
	 */
	private Snake snake = new Snake();
	
	/**
	 * Dimensions Of the stage
	 */
	private final int Length = 800;
	private final int Width = 500;
	private int Initialpos = 250;		//Default value of the position when new game is started
	
	/**
	 * Input and Output Streams to store the data
	 */
	private ObjectOutputStream outgame;
	private ObjectInputStream ingame;

	
	//Functions
	


	/**
	 * Method for serialization
	 */
	public void Serialize() {
		
		try {
			outgame = new ObjectOutputStream(new FileOutputStream("D:\\eclipse-workspace\\SnakvsBlock\\src\\Game2\\Resume.dat"));
			outgame.reset();
			outgame.writeInt(Score);
			outgame.writeInt(snake.getSize());
			outgame.writeInt(Initialpos);
			outgame.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			
		}
	}
	
	/**
	 * Method for deserialization
	 */
	public void DeSerialize(){
		
		try {
			ingame = new ObjectInputStream(new FileInputStream("D:\\eclipse-workspace\\SnakvsBlock\\src\\Game2\\Resume.dat"));
		    
			tempscore = ingame.readInt();
			tempsize = ingame.readInt();
			temppos = ingame.readInt();
			ingame.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Displays the LeaderBoard of the game.
	 * @return A Parent type root object which is used to set the new scene.
	 * @throws Exception : Generated if FileNotFound.
	 */
	public Parent Board() throws Exception {
		
		Pane root = new Pane();
		
		root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		
		backgroundSize = new BackgroundSize(Width, Length, false, false, false, false);
		Image image = new Image(new FileInputStream("C:\\Users\\Chinmay\\Desktop\\FP\\l4.jpg"));
		backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
		background = new Background(backgroundImage);
		
		ImageView imageView = new ImageView(image);
		imageView.setLayoutX(50);
		imageView.setLayoutY(Length/2);
		
		
		root.getChildren().add(imageView);
		
		BackButton.setTranslateX(220);
		BackButton.setTranslateY(Length-50);
		BackButton.setOnAction(e -> {
			try {
				ButtonClicked(e);
			}
			catch(Exception ee) { ee.printStackTrace();}
		});
		
		root.getChildren().add(BackButton);
		
		BackButton.setOnAction(e -> {
			try {
				ButtonClicked(e);
			} catch (Exception e1) {
			
				e1.printStackTrace();
			}
		});

		//TABLE
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setPadding(new Insets(10, 10, 10, 10)); // Insets are the place at which the Pane shows.
		grid.setVgap(5);
		grid.setHgap(5);
		grid.setAlignment(Pos.CENTER);
		
		Label Name= new Label("Name");
		Name.setStyle("-fx-font-weight: bold");
		Name.setFont(new Font(24));
		Name.setMinWidth(Width/3);
		Name.setTextFill(Color.WHITE);
		
		Label Date= new Label("Date");
		Date.setStyle("-fx-font-weight: bold");
		Date.setFont(new Font(24));
		Date.setMinWidth(Width/3);
		Date.setTextFill(Color.WHITE);
		
		Label Score=new Label("Score");
		Score.setStyle("-fx-font-weight: bold");
		Score.setFont(new Font(24));
		Score.setMinWidth(Width/3);
		Score.setTextFill(Color.WHITE);
		
		GridPane.setConstraints(Name, 0, 0);
		GridPane.setConstraints(Date, 1, 0);
		GridPane.setConstraints(Score, 2, 0);
		
		grid.getChildren().addAll(Name,Date,Score);
		
		
		records = database.getPlayer();
		for(int i=0;i<records.size();i++)
		{
			if(i<10)
			{
				Label n=new Label(records.get(i).getName());
				n.setFont(new Font(20));
				n.setMinWidth(Width/3);
				n.setTextFill(Color.WHITE);
				
				Label d=new Label(records.get(i).getDate());
				d.setFont(new Font(20));
				d.setMinWidth(Width/3);
				d.setTextFill(Color.WHITE);
				
				Label s=new Label(records.get(i).getScore());
				s.setFont(new Font(20));
				s.setMinWidth(Width/3);
				s.setTextFill(Color.WHITE);
				
				GridPane.setConstraints(n, 0, i+1);
				GridPane.setConstraints(d, 1, i+1);
				GridPane.setConstraints(s, 2, i+1);
				
				grid.getChildren().addAll(n,d,s);
			}
			else
			{
				break;
			}
		}
	
		
		root.getChildren().add(grid);
			
		Below = new Pane();
//		Below.setBackground(background);
//		Below.setLayoutX(Length/2);
//		Below.setLayoutY(Length/2);
		root.getChildren().add(Below);
		//root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		return root;
		
	}

	/**
	 * Creates the menu from which user can choose different options.
	 * @return Parent type root object used to set a new scene.
	 * @throws IOException : Generated when FileNotFound.
	 */
	private Parent createMenu() throws IOException { // creating the starting
		// menu

		
		Pane root = new Pane();
		
		backgroundSize = new BackgroundSize(Width, Length, false, false, false, false);
		backgroundImage = new BackgroundImage(new Image(new FileInputStream("C:\\Users\\Chinmay\\Desktop\\FP\\MainPage.png")), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
		background = new Background(backgroundImage);
		
		Label Lastgame = new Label();
		DeSerialize();
		Lastgame.setText("Last Game Score : " + Integer.toString(tempscore));
		Lastgame.setTextFill(Color.DARKTURQUOISE);
		Lastgame.setFont(new Font(20));
		
		root.setBackground(background);
		root.getChildren().add(startGame);
		root.getChildren().add(resumeGame);
		root.getChildren().add(leaderboard);
		root.getChildren().add(Instructions);
		root.getChildren().add(exit);
		root.getChildren().add(Lastgame);
		
		
		startGame.setLayoutX(190);
		startGame.setLayoutY(470);
		
		resumeGame.setLayoutX(190);
		resumeGame.setLayoutY(510);
		
		leaderboard.setLayoutX(190);
		leaderboard.setLayoutY(550);
		
		Instructions.setLayoutX(190);
		Instructions.setLayoutY(590);
		
		exit.setLayoutX(190);
		exit.setLayoutY(630);
		
		Lastgame.setLayoutX(190);
		Lastgame.setLayoutY(670);
		
		// Button Listeners
			startGame.setOnAction(e -> {
				try {
					ButtonClicked(e);
				} catch (Exception e1) {
				
					e1.printStackTrace();
				}
			});
			resumeGame.setOnAction(e -> {
				try {
					ButtonClicked(e);
				} catch (Exception e1) {
		
					e1.printStackTrace();
				}
			});
			leaderboard.setOnAction(e -> {
				try {
					ButtonClicked(e);
				} catch (Exception e1) {
	
					e1.printStackTrace();
				}
			});
			exit.setOnAction(e -> {
				try {
					ButtonClicked(e);
				} catch (Exception e1) {
				
					e1.printStackTrace();
				}
			});
			
		
		return root;
	}
	
	/**
	 * This function provides a setup for playing game loop.
	 * Initializes variables and starts first, basic blocks and the snake.
	 * Also contains the actimer started which starts the gameloop.
	 * @throws Exception generated by FileNotFound.
	 */
	public void startGame() throws Exception{	
		
		// Re-Initializing the parameters

		
		C1.setFill(Color.rgb(100,250,50, 0.5));
		C1.setVisible(false);
		
		balls = new ArrayList<>();
		walls = new ArrayList<>(); 
		blocks = new ArrayList<>();
		magnet = new ArrayList<>();
		shield = new ArrayList<>();
		bombs = new ArrayList<>();
		
		// Setting up the background
		
		backgroundSize = new BackgroundSize(Width, Length, false, false, false, false);
		backgroundImage = new BackgroundImage(new Image(new FileInputStream("C:\\Users\\Chinmay\\Desktop\\FP\\bg3.jpeg")), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
		background = new Background(backgroundImage);
		
		// Setting Drop-down Menu
		
		dropdown = new MenuBar();
		dropdown.setBackground(new Background(new BackgroundImage(new Image(new FileInputStream("C:\\Users\\Chinmay\\Desktop\\FP\\pause.png")), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize)));
		dropdown.setLayoutY(0);
		item1 = new MenuItem("RETURN");
		item2 = new MenuItem("START NEW GAME");
		
		item1.setGraphic(new ImageView(new Image(new FileInputStream("C:\\Users\\Chinmay\\Desktop\\FP\\backbutton.png"))));
		item2.setGraphic(new ImageView(new Image(new FileInputStream("C:\\Users\\Chinmay\\Desktop\\FP\\replay.png"))));
		
		menu1 = new Menu("PAUSE");
		menu1.getItems().add(item1);
		menu1.getItems().add(item2);
		
		menu1.setOnShowing(e-> {
			
			gameTime.stop();
			
		});
		
		menu1.setOnHiding(e-> {
			
			gameTime.start();

		});
		dropdown.getMenus().add(menu1);
		
		score = new Label();
		TopShow = new HBox();
		ShieldTimer = new Label();
		MagnetTimer = new Label();
		
		ShieldTimer.setVisible(false);
		MagnetTimer.setVisible(false);
		
		score.setTextFill(Color.web("#00FFFF"));
		score.setFont(new Font(30));
		score.setTranslateX(Width-60);
		
		ShieldTimer.setTextFill(Color.web("#00FFFF"));
		ShieldTimer.setFont(new Font(30));
		ShieldTimer.setTranslateX(100);
		
		MagnetTimer.setTextFill(Color.web("#00FFFF"));
		MagnetTimer.setFont(new Font(30));
		MagnetTimer.setTranslateX(200);
		TopShow.getChildren().add(dropdown);
		TopShow.getChildren().add(score);
		
	
		// Make Snake
		snake.makeSnake(Initialpos,Length);
		
		temp = new Pane(); //game + bar menu
		
		game = new Scene(temp,Width,Length);
		
		
		temp.getChildren().add(snake.getSnakeBody());	
		
		//initial blocks
		double blockx=0;
		for(int i=0;i<5;i++)
		{
			Blocks b= new Blocks(speed,blockx);
			blockx=blockx+100;
			blocks.add(b);
			temp.getChildren().add(b.getBlock());
		}
		
		//initial Walls
		double wallx=100;
		for(int i=0;i<4;i++)
		{
			Walls w= new Walls(speed,wallx,-252);
			wallx=wallx+100;
			walls.add(w);
			temp.getChildren().add(w.getWall());
		}
		
		wallx=100;
		for(int i=0;i<4;i++)
		{
			Walls w= new Walls(speed,wallx,-512);
			wallx=wallx+100;
			walls.add(w);
			temp.getChildren().add(w.getWall());
		}
		
		// Initial Tokens  
		random = r.nextInt(1000);
		
			// Balls
		if (random<910) {

			Balls b = new Balls(speed,-252);
			balls.add(b);
			temp.getChildren().add(b.getBall());
		}
		
			// Shield
		else if (random < 940) {
			Shields s = new Shields(speed,-252);
			shield.add(s);
			temp.getChildren().add(s.getShield());
		}
		
			// Magnet
		else if (random < 970) {
			Magnets m = new Magnets(speed,-252);
			magnet.add(m);
			temp.getChildren().add(m.getMagnet());
		}
		
			// Destroy Blocks
		else {
			DestryBlocks s = new DestryBlocks(speed,-252);
			bombs.add(s);
			temp.getChildren().add(s.getBomb());
		}
		
		// 2nd Round
		random = r.nextInt(100);
		
			// Balls
		if (random<52) {
			Balls b = new Balls(speed,-500);
			balls.add(b);
			temp.getChildren().add(b.getBall());
		}
		
			// Shield
		else if (random < 68) {
			Shields s = new Shields(speed,-500);
			shield.add(s);
			temp.getChildren().add(s.getShield());
		}
		
			// Magnet
		else if (random < 84) {
			Magnets m = new Magnets(speed,-500);
			magnet.add(m);
			temp.getChildren().add(m.getMagnet());
		}
		
			// Destroy Blocks
		else {
			DestryBlocks s = new DestryBlocks(speed,-500);
			bombs.add(s);
			temp.getChildren().add(s.getBomb());
		}
		
		gameTime = new AnimationTimer() {

			@Override
			public void handle(long arg0) {
				try {
					play();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		};	
		
		gameTime.start();
		
		
		temp.getChildren().add(TopShow); 
		temp.getChildren().add(dropdown);
	 
		ShieldTimer.setGraphic(new ImageView(new Image(new FileInputStream("C:\\Users\\Chinmay\\Desktop\\FP\\shield2.png"))));
		MagnetTimer.setGraphic(new ImageView(new Image(new FileInputStream("C:\\Users\\Chinmay\\Desktop\\FP\\magnet2.png"))));
		temp.getChildren().add(ShieldTimer);
		temp.getChildren().add(MagnetTimer);
		
		ShieldTimer.setTranslateX(100);
		MagnetTimer.setTranslateX(250);
		
		item1.setOnAction(e -> {
			try {
				gameTime.stop();
				Serialize();
				theStage.setScene(new Scene(createMenu(),Width,Length));
			} catch (Exception e1) {

				e1.printStackTrace();
			}
		});
		
		item2.setOnAction(e -> {
			try {
				gameTime.stop();
				startGame();
			} catch (Exception e1) {

				e1.printStackTrace();
			}
		});
		
		temp.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		//System.out.println(temp.getBoundsInLocal().getMinX());
		game.setOnKeyPressed(e-> Keypressed(e));
		theStage.setScene(game);
		theStage.show();
		
	}
	
	/**
	 * The gameloop of the game.
	 * @throws Exception: FileNotFoundException generated in the generate and intersection functions.
	 */
	private void play() throws Exception{
		
		
//		C1.setTranslateX(snake.getSnakeBody().getTranslateX());
//		C1.setTranslateY(Length - 350 );
		
		Serialize();
		// Display Shield Timer
		ShieldTimer.setText(Double.toString(5-(int)(currenttimeshield-starttimeshield)/1000));
		
		// Display magnet timer
		MagnetTimer.setText(Double.toString(5-(int)(currenttimemagnet-starttimemagnet)/1000));
		//display score
		score.setText(Integer.toString(Score));
		
		// Move all
		move();
		
		// Generate all
		generate();		
		
		//intersections and updation of snake with block
		intersection();
		
		// Remove old
		remove();
		
	}
    
	
	/**
	 * This function moves all the tokens in the game loop.
	 */	
	private void move() {
		
		//blocks move
		for(int i=0;i<blocks.size();i++)
		{
			blocks.get(i).move();
		}
		 
		//walls move
		for (int i = 0; i < walls.size(); i++) {
			walls.get(i).move();

			//System.out.println(1);
			if (walls.get(i).getWall().isVisible()) {
				
				//System.out.println(walls.get(i).getWall().getBoundsInParent().getMaxY());
				if(walls.get(i).getWall().getBoundsInParent().getMaxY() >= 450-154 && walls.get(i).getWall().getBoundsInParent().getMinY() <= 450) {
						
					//System.out.println( snake.getSnakeBody().getTranslateX() + " " + walls.get(i).getPosx());
					//System.out.println(Initialpos);	
					if (Initialpos < walls.get(i).getPosx() && Initialpos >= walls.get(i).getPosx() - 14) {
							System.out.println(111);
							dispright = 0;
							displeft = 10;
						}
						else if (Initialpos > walls.get(i).getPosx() && Initialpos <= walls.get(i).getPosx() + 14) {
							displeft = 0;
							dispright = 10;
						}
						else if (Initialpos == walls.get(i).getPosx()){
							snake.getSnakeBody().setTranslateX(Initialpos - 4);
							dispright = 10;
							displeft = 10;
						}
						else {
							dispright = 10;
							displeft = 10;
						}
				}
				
			}
		}
	
		// Balls Move
		for(int i=0;i<balls.size();i++)
		{
			//System.out.println(1);
			balls.get(i).move();
		}
				
		// Shields Move
		for(int i=0;i<shield.size();i++)
		{
			//System.out.println(1);
			shield.get(i).move();
		}
		
		// Magnets Move
		for(int i=0;i<magnet.size();i++)
		{
			//System.out.println(1);
			magnet.get(i).move();
		}
		
		// Bombs Move
		for(int i=0;i<bombs.size();i++)
		{
			//System.out.println(1);
			bombs.get(i).move();
		}
		
	}
	
	/**
	 * This function generates all the tokens in the game loop.
	 */
	private void generate() throws FileNotFoundException {
		
		// Blocks
		//middle value find
		if(blocks.get(2).getBlock().getTranslateY()==404)
		{
			int blockx=0;
			for(int i=0;i<5;i++)
			{
				Blocks b= new Blocks(speed,blockx);
				blockx=blockx+100;
				blocks.add(b);
				temp.getChildren().add(b.getBlock());
			}
			int wallx=100;
			for(int i=0;i<4;i++)
			{
				Walls w= new Walls(speed,wallx,-252);
				wallx=wallx+100;
				walls.add(w);
				temp.getChildren().add(w.getWall());
			}
			
			wallx=100;
			for(int i=0;i<4;i++)
			{
				Walls w= new Walls(speed,wallx,-512);
				wallx=wallx+100;
				walls.add(w);
				temp.getChildren().add(w.getWall());
			}
			
			// Initial Tokens  
			random = r.nextInt(1000);
			
				// Balls
			if (random<910) {

				Balls b = new Balls(speed,-252);
				balls.add(b);
				temp.getChildren().add(b.getBall());
			}
			
				// Sheild
			else if (random < 940) {
				Shields s = new Shields(speed,-252);
				shield.add(s);
				temp.getChildren().add(s.getShield());
			}
			
				// Magnet
			else if (random < 970) {
				Magnets m = new Magnets(speed,-252);
				magnet.add(m);
				temp.getChildren().add(m.getMagnet());
			}
			
				// Destroy Blocks
			else {
				DestryBlocks s = new DestryBlocks(speed,-252);
				bombs.add(s);
				temp.getChildren().add(s.getBomb());
			}
			
			// 2nd Round
			random = r.nextInt(100);
			
				// Balls
			if (random<52) {
				Balls b = new Balls(speed,-500);
				balls.add(b);
				temp.getChildren().add(b.getBall());
			}
			
				// Shield
			else if (random < 68) {
				Shields s = new Shields(speed,-500);
				shield.add(s);
				temp.getChildren().add(s.getShield());
			}
			
				// Magnet
			else if (random < 84) {
				Magnets m = new Magnets(speed,-500);
				magnet.add(m);
				temp.getChildren().add(m.getMagnet());
			}
			
				// Destroy Blocks
			else {
				DestryBlocks s = new DestryBlocks(speed,-500);
				bombs.add(s);
				temp.getChildren().add(s.getBomb());
			}
		}
		
	}
	
	
	/**
	 * This function terminates the tokens in the game loop when required.
	 */
	private void remove() {
		
		//removing Blocks
		if(blocks.get(2).getBlock().getTranslateY()==800)
		{
			for(int i=0;i<5;i++)
			{
				//System.out.println(blocks.get(i).getValue());
				blocks.remove(0);	
			}
		}
			
		// Removing wall
		if(walls.get(2).getWall().getTranslateY()==800)
		{
			for(int i=0;i<4;i++)
			{
				//System.out.println(blocks.get(i).getValue());
				walls.remove(0);	
			}
		}
				
		// Removing balls
			
		if (balls.size()>0) {
			if (balls.get(0).getBall().getTranslateY()>=1200) {
				balls.remove(0);
				//System.out.println(balls.size());
			}
		}
		

		// Removing Shields
			
		if (shield.size()>0) {
			if (shield.get(0).getShield().getTranslateY()>=1200) {
				shield.remove(0);
				//System.out.println(balls.size());
			}
		}
		
		// Removing Magnets

		if (magnet.size()>0) {
			if (magnet.get(0).getMagnet().getTranslateY()>=1200) {
				magnet.remove(0);
				//System.out.println(balls.size());
			}
		}
			
		
		// Removing Bombs
		
		if (bombs.size()>0) {
			if (bombs.get(0).getBomb().getTranslateY()>=1200) {
				bombs.remove(0);
				//System.out.println(balls.size());
			}
		}
	}
		
	/**
	 * This function handles the intersection of all the tokens
	 * @throws FileNotFoundException
	 */
	private void intersection() throws FileNotFoundException {
		
		//intersection of the blocks only.
		
		
		
		for(int i=0;i<5;i++)
		{
			Blocks b= blocks.get(i);
			
			//System.out.println(snake.getSnakeBody().getTranslateX());
			if(b.getBlock().getTranslateX()<=snake.getSnakeBody().getTranslateX() && b.getBlock().getTranslateX() + 90>=snake.getSnakeBody().getTranslateX())
			{
				if(b.getBlock().getBoundsInParent().getMaxY()==snake.getSnakeBody().getBoundsInParent().getMinY())
				{
					
					//decreasing snake length
					if (shieldflag == false) {
						if(snake.getSize()-b.getValue()<0)
						{
							snake.setSize(0);
							Serialize();
							gameTime.stop();
							showgameover();
						}
						else
						{
							if (b.getBlock().isVisible()) {
								pop = new MediaPlayer(m1);
								pop.play();
							}
						
							
							if (b.getValue() > 5) {
								speed = 0;
								long t = 0;
								pop = new MediaPlayer(m1);
								pop.play();
								while(t < 600000000) {
									if (t == 300000000) {
										pop = new MediaPlayer(m1);
										pop.play();
									}
									t++;
								}
								speed = 1.5;
							}
							Score+=b.getValue();
							snake.setSize(snake.getSize()-b.getValue());
							//System.out.println(snake.getSize());
							snake.makeSnake(Initialpos,Length);
							
						}
					}
					
					b.getBlock().setVisible(false);
					
				}
			}
		
		}
		
		
		// Intersection of Balls
		
		for (int i = 0; i < balls.size();) {
			
			tempb = balls.get(i);
			
			if(tempb.getBall().getTranslateX()>=snake.getSnakeBody().getTranslateX()-10 && tempb.getBall().getTranslateX() <=snake.getSnakeBody().getTranslateX() + 10)
			{
				
				//System.out.println(444 + " " + balls.size())
					if(tempb.getBall().getBoundsInParent().getMaxY()>=snake.getSnakeBody().getBoundsInParent().getMinY()-1 && tempb.getBall().getBoundsInParent().getMaxY()<=snake.getSnakeBody().getBoundsInParent().getMinY()+1)
					{			
						//System.out.println(555);
						//i snake length
							snake.setSize(snake.getSize()+tempb.getValue()+1);
							//System.out.println(snake.getSize());
							snake.makeSnake(Initialpos,Length);
						
							coinsound = new MediaPlayer(m3);
						coinsound.play();
						tempb.getBall().setVisible(false);
					}
					//System.out.println(tempb.getBall().getBoundsInParent().getMaxY())
				
			}
			
			if (magnetflag) {
				if (tempb.getBall().getBoundsInParent().getMaxY()>=snake.getSnakeBody().getBoundsInParent().getMinY()-150 && tempb.getBall().getBoundsInParent().getMaxY()<=snake.getSnakeBody().getBoundsInParent().getMinY()) {
					System.out.println(snake.getSize() + " " + tempb.getValue());
					snake.setSize(snake.getSize()+tempb.getValue()+1);
					snake.makeSnake(Initialpos,Length);
					balls.remove(i);
					i--;
					coinsound = new MediaPlayer(m3);
					coinsound.play();
					tempb.getBall().setVisible(false);
				}
			}
			
			i++;
		
		}
		
		// Intersection of Shield
		for (int i = 0; i < shield.size(); i++) {
			
			temps = shield.get(i);

			if(temps.getShield().getTranslateX()>=snake.getSnakeBody().getTranslateX()-10 && temps.getShield().getTranslateX() <=snake.getSnakeBody().getTranslateX() + 10)
			{
				
				//System.out.println(444 + " " + balls.size());
				if(temps.getShield().getBoundsInParent().getMaxY()>=snake.getSnakeBody().getBoundsInParent().getMinY()-1 && temps.getShield().getBoundsInParent().getMaxY()<=snake.getSnakeBody().getBoundsInParent().getMinY()+1)
				{			
					ShieldTimer.setVisible(true);
					starttimeshield = System.currentTimeMillis();
					shieldflag = true;
					shieldsound = new MediaPlayer(m4);
					shieldsound.play();
					temps.getShield().setVisible(false);
				}
			}
		}

			currenttimeshield = System.currentTimeMillis();
			if (currenttimeshield - starttimeshield >= 5000) {
				shieldflag = false;
				ShieldTimer.setVisible(false);
			}
		
		// Intersection with magnet
		
		for (int i = 0; i < magnet.size(); i++) {
			
			tempm = magnet.get(i);

			if(tempm.getMagnet().getTranslateX()>=snake.getSnakeBody().getTranslateX()-10 && tempm.getMagnet().getTranslateX() <=snake.getSnakeBody().getTranslateX() + 10)
			{
				
				///System.out.println(444 + " " + magnet.size() + " " + tempm.getMagnet().getBoundsInParent().getMaxY());
				if(tempm.getMagnet().getBoundsInParent().getMaxY()>=snake.getSnakeBody().getBoundsInParent().getMinY()-1 && tempm.getMagnet().getBoundsInParent().getMaxY()<=snake.getSnakeBody().getBoundsInParent().getMinY()+1)
				{			
								
					starttimemagnet = System.currentTimeMillis();
					MagnetTimer.setVisible(true);
					magnetflag = true;
					magnetsound = new MediaPlayer(m5);
					magnetsound.play();
					tempm.getMagnet().setVisible(false);
				}
			}
		}
		
		currenttimemagnet = System.currentTimeMillis();
		if (currenttimemagnet - starttimemagnet >= 5000) {
			MagnetTimer.setVisible(false);
			magnetflag = false;
		}
		
		// Intersection of Bombs
		for (int i = 0; i < bombs.size(); i++) {
					
			tempbomb = bombs.get(i);

			if(tempbomb.getBomb().getTranslateX()>=snake.getSnakeBody().getTranslateX()-10 && tempbomb.getBomb().getTranslateX() <=snake.getSnakeBody().getTranslateX() + 10)
			{
				
				//System.out.println(444 + " " + balls.size());
				if(tempbomb.getBomb().getBoundsInParent().getMaxY()>=snake.getSnakeBody().getBoundsInParent().getMinY()-1 && tempbomb.getBomb().getBoundsInParent().getMaxY()<=snake.getSnakeBody().getBoundsInParent().getMinY()+1)
				{			
						
					for (int j = 0; j < Math.min(10,blocks.size()); j++) {
						blocks.get(j).setValue(0);
						blocks.get(j).getBlock().setVisible(false);
					}
							
					bombsound = new MediaPlayer(m2);
					bombsound.play();
					tempbomb.getBomb().setVisible(false);
				}
			}
		}
	}
    
	/**
	 * This function displays the pop up window when the game gets over.
	 * Also writes to the database.
	 * @throws FileNotFoundException
	 */
	private void showgameover() throws FileNotFoundException
	{
		Pane OverBox= new Pane();
		OverBox.setTranslateX(100);
		OverBox.setTranslateY(250);
		OverBox.setMinSize(300, 300);
		String bgColorName = "D3D3D3";
		OverBox.setStyle("-fx-background-color: #" + bgColorName);
		VBox v= new VBox();
		
		//Creating a GridPane container
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10, 10, 10, 10)); // Insets are the place at which the Pane shows.
		grid.setVgap(5);
		grid.setHgap(5);
		grid.setAlignment(Pos.CENTER);
		grid.setTranslateX(25);
		
		//Game over Label
		Image GO= new Image(new FileInputStream("C:\\Users\\Chinmay\\Desktop\\FP\\gameover.png"));
		ImageView gameOver= new ImageView(GO);;
        gameOver.maxHeight(80);   
        gameOver.setFitHeight(80);
        gameOver.setPreserveRatio(true);
        gameOver.setTranslateX(25);
        gameOver.setTranslateY(15);
        
		//Score
		Label scc= new Label("Score : " + Score); //replace score var instead of 20
		scc.setTranslateX(25);
		scc.setTranslateY(15);
		scc.setFont(new Font(30));
		
		//Defining the Name text field
		final TextField name = new TextField();
		name.setPromptText("Enter your first name.");
		name.setPrefColumnCount(10);
		name.getText();
		GridPane.setConstraints(name, 0, 2);
		grid.getChildren().add(name);

		
		//Defining the Submit button
		Button submit = new Button("Submit");
		GridPane.setConstraints(submit, 1, 2);
		grid.getChildren().add(submit);
		
		replay= new Button();	
		Image re= new Image(new FileInputStream("C:\\Users\\Chinmay\\Desktop\\FP\\replay.png"));
		Image men= new Image(new FileInputStream("C:\\Users\\Chinmay\\Desktop\\FP\\backbutton.png"));
		replay.setGraphic(new ImageView(re));
		replay.setOnAction(e -> {
			try {
				ButtonClicked(e);
			}
			catch(Exception ee) { ee.printStackTrace();}
		});
		
		
		menuu= new Button();
		menuu.setGraphic(new ImageView(men));
		menuu.setOnAction(e -> {
			try {
				ButtonClicked(e);
			}
			catch(Exception ee) { ee.printStackTrace();}
		});
		
		
		HBox btns= new HBox();
		btns.setSpacing(10);
		btns.setAlignment(Pos.CENTER);
		btns.getChildren().addAll(replay,menuu);
		btns.setTranslateX(25);
		btns.setTranslateX(20);
		OverBox.getChildren().add(btns);

		//Setting an action for the Submit button
		submit.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		    public void handle(ActionEvent e) {
				Player p=new Player();
		     	p.setName(name.getText());
		     	p.setDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
		     	p.setScore(Integer.toString(Score));
		     	
		     	//add player to database.
		     	database.addPlayer(p);
		     	
		     	database.Serialize();
		     	name.clear(); //to clear the feild.
		     	name.setDisable(true);
//		     	grid.getChildren().remove(name);
//		     	grid.getChildren().remove(submit);
		     }
		 });
		
		v.getChildren().addAll(gameOver,scc,grid,btns);
		v.setAlignment(Pos.CENTER);
		OverBox.getChildren().add(v);
		
		temp.getChildren().add(OverBox);
	}
	
	
	/**
	 * Detects if there is previous game or not
	 * @throws FileNotFoundException 
	 */
	private void Noprevgame() throws FileNotFoundException
	{
		Pane prevbox= new Pane();
		prevbox.setMinSize(300, 300);
		String bgColorName = "D4D4D4D4";
		prevbox.setStyle("-fx-background-color: #" + bgColorName);
				
		
		Label l2 = new Label("NO PREVIOUS GAME FOUND");
		prevbox.getChildren().add(l2);
		l2.setLayoutX(20);
		l2.setLayoutY(80);
		l2.setFont(new Font(20));
		l2.setTextFill(Color.BLACK);
	
		
		
		Stage s2 = new Stage();
		s2.setScene(new Scene(prevbox,Color.BLACK));
		s2.show();
		
		replay= new Button();	
		Image re= new Image(new FileInputStream("C:\\Users\\Chinmay\\Desktop\\FP\\replay.png"));
		Image men= new Image(new FileInputStream("C:\\Users\\Chinmay\\Desktop\\FP\\backbutton.png"));
		replay.setGraphic(new ImageView(re));
		replay.setOnAction(e -> {
			try {
				s2.close();
				ButtonClicked(e);
			}
			catch(Exception ee) { ee.printStackTrace();}
		});
		
		
		menuu= new Button();
		menuu.setGraphic(new ImageView(men));
		menuu.setOnAction(e -> {
			try {
				s2.close();
				ButtonClicked(e);
			}
			catch(Exception ee) { ee.printStackTrace();}
		});
		
		HBox btns= new HBox();
		btns.setSpacing(10);
		btns.setAlignment(Pos.CENTER);
		btns.getChildren().addAll(replay,menuu);
		btns.setTranslateX(25);
		btns.setTranslateX(20);
		
		prevbox.getChildren().add(btns);
		btns.setLayoutY(150);
		btns.setLayoutX(75);
	}

	/**
	 * Listeners for the various buttons.
	 * @param e : e contains the ActionEvent object which is the info. of the action.
	 * @throws Exception: Given by the Board() function.
	 */
	private void ButtonClicked(ActionEvent e)throws Exception {
	
		if (e.getSource() == startGame || e.getSource() == replay) {
			Initialpos = 250;
			snake = new Snake();
			Score = 0;
			startGame();
		}
		
		if (e.getSource() == menuu || e.getSource()==BackButton)
		{
			theStage.setScene(new Scene(createMenu(),Width,Length));
		}
		
		if (e.getSource() == resumeGame) {
			DeSerialize();
			Score = tempscore;
			snake.setSize(tempsize);
			Initialpos = temppos;
			
			if (tempsize == 0) {
				System.out.println("No old game");
				Noprevgame();
			}
			else {
				startGame();
			}
			
		}
		
		if (e.getSource() == leaderboard) {
			board = new Scene(Board(),Width,Length,Color.BLACK);
			theStage.setScene(board);
		}
		
		if (e.getSource() == exit) {
			Platform.exit();
		}
		
	}
	
	/**
	 * Listener for key press for snake
	 * @param e : Contrains the KeyEvent information.
	 */
	private void Keypressed(KeyEvent e)
	{
		//System.out.println(e.getCode());
		switch(e.getCode())
		{
		
			case RIGHT :
				
						if(Initialpos+dispright <= Width-25)
						{
							Initialpos += dispright;
						}
						else {
							Initialpos = Width - 25;
						}
						
						snake.getSnakeBody().setTranslateX(Initialpos);
						
						break;
						
			case LEFT :
						Initialpos -= displeft;
						
						if(Initialpos <= 0)
						{
							Initialpos = 0;
						}
						
						snake.getSnakeBody().setTranslateX(Initialpos);
						
						break;
				default	:
						break;
		}
	}

	/* (non-Javadoc)
	 * Starts the application.
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		backgroundSize = new BackgroundSize(Width, Length, false, false, false, false);
		backgroundImage = new BackgroundImage(new Image(new FileInputStream("C:\\Users\\Chinmay\\Desktop\\FP\\upmouth.png")), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
		background = new Background(backgroundImage);
		
		startGame = new Button("Start Game");
		startGame.setBackground(background);
		
		resumeGame = new Button("Resume Game");
		resumeGame.setBackground(background);
		
		leaderboard = new Button("Leader board");
		leaderboard.setBackground(background);
		
		Instructions = new Button("Instructions");
		Instructions.setBackground(background);

		exit = new Button("Exit");
		exit.setBackground(background);
		
		BackButton = new Button("Return");
		BackButton.setBackground(background);
		
		theStage = primaryStage;
		
		menu = new Scene(createMenu(),Width,Length,Color.BLACK);
		

		primaryStage.setTitle("Snake");
		primaryStage.setScene(menu);
		primaryStage.setResizable(false);
		primaryStage.show();		
		
	}

	/**
	 * Starts the application. Only necessary for the IDE.
	 * @param args : unused.
	 */
	public static void main(String[] args) {
		
		Application.launch(args);
	}

}
