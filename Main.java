package Game2;

import java.awt.Desktop.Action;
import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.*;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application{

	// Temporary Variable
	private int Temporary = -8;
	private int choice;
	private Random randint = new Random();
	
	// Default speed
	private static double speed = 4; 
	
	// Score of the Player
	private int Score = 0;
	
	//speeds
	private Scene game;
	private Scene menu;
	private Scene board;
	
	//stages
	private Stage theStage; 
	
	//Temporary StackPane & Pane
	private Pane temp;
	private Pane Below;
	

	//Buttons
	private Button startGame ;
	private Button resumeGame;
	private Button leaderboard;
	private Button exit;	
	private Button BackButton;
	private Button StartNewGame;
	private Button Instructions;
	
	// Combo Box ( Drop Down Option and score )
	private ChoiceBox<String> list = new ChoiceBox<>();
	private Label score = new Label();
	private HBox TopShow = new HBox();
	
	// Blocks 
	private ArrayList<Blocks> Obstruction = new ArrayList<Blocks>();
	private int placeforboxinpane ;
	private int pointerforblocks = 0;

	private ArrayList<StackPane> PaneForBlocks = new ArrayList<StackPane>();
	private HBox Row1 = new HBox();
	private HBox Row2 = new HBox();
	private HBox Row3 = new HBox();
	private HBox Row4 = new HBox();

	// Walls
	private Walls wall;
	private Pane pforwall = new Pane();
	private Thread forWall;
	
	// Balls
	private Balls balls;
	
	// Magnets
	private Magnets magnet;
	private Pane tokenspane = new Pane();
	private int placeforTokeninpane;
	private Thread forToken;
	
	// Shields
	private Shields shields;

	
	// Backgrounds
	private BackgroundSize backgroundSize;
	private BackgroundImage backgroundImage; 
	private Background background;
	
	// LeaderBoard
	private LeaderBoard b = new LeaderBoard();
	private ObservableList<Player> Board = b.getPlayer();
	
	// Snake Object and Corresponding Images
	private Snake snake = new Snake();
	
	//Dimensions Of the stage
	private final int Length = 800;
	private final int Width = 500;
	private int Initialpos = 250;		//Default value of the position when new game is started
	
	//Functions
	


	//Creating Menu
	private Parent createMenu() throws IOException { // creating the starting
		// menu
		
		Pane root = new Pane();
		
		backgroundSize = new BackgroundSize(Width, Length, false, false, false, false);
		backgroundImage = new BackgroundImage(new Image(new FileInputStream("D:\\eclipse-workspace\\SnakvsBlock\\src\\Game2\\FP\\MainPage.png")), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
		background = new Background(backgroundImage);
		
		root.setBackground(background);
		root.getChildren().add(startGame);
		root.getChildren().add(resumeGame);
		root.getChildren().add(leaderboard);
		root.getChildren().add(Instructions);
		root.getChildren().add(exit);
		
		
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
	
	//Play the game
	public void startGame() throws Exception{		
		play();
	}
	
	private void play() throws Exception{

		// Setting up the background
		backgroundSize = new BackgroundSize(Width, Length, false, false, false, false);
		backgroundImage = new BackgroundImage(new Image(new FileInputStream("D:\\eclipse-workspace\\SnakvsBlock\\src\\Game2\\FP\\bg3.jpeg")), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
		background = new Background(backgroundImage);
		
		
		// Top drop down List
		StartNewGame = new Button("Start New Game");
		BackButton = new Button("Return");
		
		list.setLayoutY(0);
		list.setMinWidth(Width-100);
		list.getItems().add("Start New Game");
		list.getItems().add("Back Button");
		list.setAccessibleHelp("Pause");
		
		score.setText(Integer.toString(Score));
		score.setTextFill(Color.web("#00FFFF"));
		score.setFont(new Font(30));
		score.setTranslateX(Width-60);
		TopShow.getChildren().add(list);
		TopShow.getChildren().add(score);
		
		// Make Snake
		snake.makeSnake(Initialpos,Length);
		
		
		temp = new Pane();
		
		game = new Scene(temp,Width,Length);

		temp.setBackground(background);
		
		temp.getChildren().add(TopShow);
		temp.getChildren().add(snake.getSnakeBox());	
		
		
		for (int i = 0; i < 32; i++) {
			Obstruction.add(new Blocks());
			PaneForBlocks.add(new StackPane());
			PaneForBlocks.set(pointerforblocks,new StackPane(Obstruction.get(pointerforblocks).getShape(),new Text(Integer.toString(Obstruction.get(pointerforblocks).getValue()))));
			
			if (Obstruction.get(pointerforblocks).getValue() == 0) {
				PaneForBlocks.get(pointerforblocks).setVisible(false);
			}
			else {
				PaneForBlocks.get(pointerforblocks).setVisible(true);
			}
			
			if (i < 8) {
				Row1.getChildren().add(PaneForBlocks.get(pointerforblocks));
			}
			else if (i < 16) {
				Row2.getChildren().add(PaneForBlocks.get(pointerforblocks));
			}
			else if (i < 24) {
				Row3.getChildren().add(PaneForBlocks.get(pointerforblocks));
			}
			else  {
				Row4.getChildren().add(PaneForBlocks.get(pointerforblocks));
			}
			pointerforblocks++;
		}	
		
		Temporary = 0;
		
		
		temp.getChildren().add(Row1);
		temp.getChildren().get(temp.getChildren().size()-1).setLayoutY(-300);
		temp.getChildren().add(Row2);
		temp.getChildren().get(temp.getChildren().size()-1).setLayoutY(-600);
		temp.getChildren().add(Row3);
		temp.getChildren().get(temp.getChildren().size()-1).setLayoutY(-900);
		temp.getChildren().add(Row4);
		temp.getChildren().get(temp.getChildren().size()-1).setLayoutY(-1200);
		
		mover move = new mover();
		Timeline timeline3 = new Timeline(new KeyFrame(Duration.millis(20), move));
		timeline3.setCycleCount(Timeline.INDEFINITE);
		timeline3.play();
		
		Timeline timeline2 = new Timeline(new KeyFrame(Duration.millis(6000),  new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Row1.getChildren().clear();
				
				for (int i = 0; i < 8; i++) {
					Obstruction.add(new Blocks());
					PaneForBlocks.add(new StackPane());
					PaneForBlocks.set(pointerforblocks,new StackPane(Obstruction.get(pointerforblocks).getShape(),new Text(Integer.toString(Obstruction.get(pointerforblocks).getValue()))));
					
					if (Obstruction.get(pointerforblocks).getValue() == 0) {
						PaneForBlocks.get(pointerforblocks).setVisible(false);
					}
					else {
						PaneForBlocks.get(pointerforblocks).setVisible(true);
					}
					
					Row1.getChildren().add(PaneForBlocks.get(pointerforblocks));

					pointerforblocks++;
				}		

			}
			
		}),
				new KeyFrame(Duration.millis(6000), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Row2.getChildren().clear();
				
				for (int i = 0; i < 8; i++) {
					Obstruction.add(new Blocks());
					PaneForBlocks.add(new StackPane());
					PaneForBlocks.set(pointerforblocks,new StackPane(Obstruction.get(pointerforblocks).getShape(),new Text(Integer.toString(Obstruction.get(pointerforblocks).getValue()))));
					
					if (Obstruction.get(pointerforblocks).getValue() == 0) {
						PaneForBlocks.get(pointerforblocks).setVisible(false);
					}
					else {
						PaneForBlocks.get(pointerforblocks).setVisible(true);
					}
					
					Row2.getChildren().add(PaneForBlocks.get(pointerforblocks));

					pointerforblocks++;
				}		

			}
			
		}),
				new KeyFrame(Duration.millis(6000), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Row3.getChildren().clear();
				
				for (int i = 0; i < 8; i++) {
					Obstruction.add(new Blocks());
					PaneForBlocks.add(new StackPane());
					PaneForBlocks.set(pointerforblocks,new StackPane(Obstruction.get(pointerforblocks).getShape(),new Text(Integer.toString(Obstruction.get(pointerforblocks).getValue()))));
					
					if (Obstruction.get(pointerforblocks).getValue() == 0) {
						PaneForBlocks.get(pointerforblocks).setVisible(false);
					}
					else {
						PaneForBlocks.get(pointerforblocks).setVisible(true);
					}
					
					Row3.getChildren().add(PaneForBlocks.get(pointerforblocks));

					pointerforblocks++;
				}		

			}
			
		}),
				new KeyFrame(Duration.millis(6000), new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						Row4.getChildren().clear();
						
						for (int i = 0; i < 8; i++) {
							Obstruction.add(new Blocks());
							PaneForBlocks.add(new StackPane());
							PaneForBlocks.set(pointerforblocks,new StackPane(Obstruction.get(pointerforblocks).getShape(),new Text(Integer.toString(Obstruction.get(pointerforblocks).getValue()))));
							
							if (Obstruction.get(pointerforblocks).getValue() == 0) {
								PaneForBlocks.get(pointerforblocks).setVisible(false);
							}
							else {
								PaneForBlocks.get(pointerforblocks).setVisible(true);
							}
							
							Row4.getChildren().add(PaneForBlocks.get(pointerforblocks));

							pointerforblocks++;
						}		
					}
		}));
		timeline2.setCycleCount(Timeline.INDEFINITE);
		timeline2.play();
			
		timerlistener checkCollision = new timerlistener();
	    Timeline timeline1 = new Timeline(new KeyFrame(Duration.millis(20),checkCollision ));
	    timeline1.setCycleCount(Timeline.INDEFINITE);
	    timeline1.play();	
		
		temp.getChildren().add(list);
		
		BackButton.setOnAction(e -> {
			try {
				ButtonClicked(e);
			} catch (Exception e1) {

				e1.printStackTrace();
			}
		});
		
		StartNewGame.setOnAction(e -> {
			try {
				ButtonClicked(e);
			} catch (Exception e1) {

				e1.printStackTrace();
			}
		});
		
		System.out.println(temp.getBoundsInLocal().getMinX());
		game.setOnKeyPressed(e-> Keypressed(e));
		theStage.setScene(game);
		theStage.show();
				
	}
    
    
    
	// LeaderBoard
	
	@SuppressWarnings("unchecked")
	public Parent Board() throws Exception {
		
		Pane root = new Pane();
		
		root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		
		backgroundSize = new BackgroundSize(Width, Length, false, false, false, false);
		Image image = new Image(new FileInputStream("D:\\eclipse-workspace\\SnakvsBlock\\src\\Game2\\FP\\l4.jpg"));
		backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
		background = new Background(backgroundImage);
		
		ImageView imageView = new ImageView(image);
		imageView.setLayoutX(50);
		imageView.setLayoutY(Length/2);
		
		
		root.getChildren().add(imageView);
		BackButton.setTranslateX(220);
		BackButton.setTranslateY(Length-50);
		
		root.getChildren().add(BackButton);
		
		BackButton.setOnAction(e -> {
			try {
				ButtonClicked(e);
			} catch (Exception e1) {
			
				e1.printStackTrace();
			}
		});

		
		TableView<Player> table = new TableView<Player>();
	
		table.setMaxHeight(Length/2);
		table.setMaxWidth(Width); 	 	
		
		TableColumn<Player, String> Date = new TableColumn<>("Date");
		
		Date.setCellValueFactory(new PropertyValueFactory<>("Name"));
		
		Date.setMinWidth(Width/3);
		
		TableColumn<Player, String> name = new TableColumn<>("Name");
		
		name.setMinWidth(Width/3);
		
		name.setCellValueFactory(new PropertyValueFactory<>("Name"));
		
		TableColumn<Player, Integer> score = new TableColumn<>("Score");

		score.setMinWidth(Width/3);
	
		score.setCellValueFactory(new PropertyValueFactory<>("score"));
		
		Collections.sort((List<Player>) Board, new Comparator<Player>() {
			public int compare(Player c1, Player c2) {
				if (c1.getScore() > c2.getScore())
					return -1;
				if (c1.getScore() < c2.getScore())
					return 1;
				return 0;
			}
		});
		
		table.setItems(Board); // setting the items in the table
		table.getColumns().addAll(name,Date,score);
		root.getChildren().add(table);
		
		Below = new Pane();
//		Below.setBackground(background);
//		Below.setLayoutX(Length/2);
//		Below.setLayoutY(Length/2);
		root.getChildren().add(Below);
		//root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		return root;
		
	}
	
	// Listeners for button
	private void ButtonClicked(ActionEvent e)throws Exception {
	
		if (e.getSource() == startGame) {
			startGame();
		}
		
		if (e.getSource() == resumeGame) {
			
		}
		
		if (e.getSource() == leaderboard) {
			board = new Scene(Board(),Width,Length,Color.BLACK);
			theStage.setScene(board);
		}
		
		if (e.getSource() == exit) {
			Platform.exit();
		}
		
		if (e.getSource() == BackButton) {
			theStage.setScene(menu);
		}
	}
	
	// Listener for key press
	private void Keypressed(KeyEvent e)
	{
		//System.out.println(e.getCode());
		switch(e.getCode())
		{
		
			case RIGHT :
				
						if(Initialpos+25 <= Width-25)
						{
							Initialpos += 20;
						}
						else {
							Initialpos = Width - 25;
						}
						
						snake.getSnakeBox().setTranslateX(Initialpos);
						
						break;
						
			case LEFT :
						Initialpos -= 20;
						
						if(Initialpos <= 0)
						{
							Initialpos = 0;
						}
						
						snake.getSnakeBox().setTranslateX(Initialpos);
						
						break;
				default	:
						break;
		}
	}

	//Start
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		backgroundSize = new BackgroundSize(Width, Length, false, false, false, false);
		backgroundImage = new BackgroundImage(new Image(new FileInputStream("D:\\eclipse-workspace\\SnakvsBlock\\src\\Game2\\FP\\upmouth.png")), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
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

	public static void main(String[] args) {
		
		Application.launch(args);
	}

	private class generate implements EventHandler<ActionEvent>{
		
		@Override
		public void handle(ActionEvent t) {
			
			if (Temporary == 0) {
				Row1.getChildren().clear();
			}
			if (Temporary == 1) {
				Row2.getChildren().clear();
			}
			if (Temporary == 2) {
				Row3.getChildren().clear();
			}
			if (Temporary == 3) {
				Row4.getChildren().clear();
			}
			for (int i = 0; i < 8; i++) {
				Obstruction.add(new Blocks());
				PaneForBlocks.add(new StackPane());
				PaneForBlocks.set(pointerforblocks,new StackPane(Obstruction.get(pointerforblocks).getShape(),new Text(Integer.toString(Obstruction.get(pointerforblocks).getValue()))));
				
				if (Obstruction.get(pointerforblocks).getValue() == 0) {
					PaneForBlocks.get(pointerforblocks).setVisible(false);
				}
				else {
					PaneForBlocks.get(pointerforblocks).setVisible(true);
				}
				
				if (Temporary == 0) {
					Row1.getChildren().add(PaneForBlocks.get(pointerforblocks));
				}
				if (Temporary == 1) {
					Row2.getChildren().add(PaneForBlocks.get(pointerforblocks));
				}
				if (Temporary == 2) {
					Row3.getChildren().add(PaneForBlocks.get(pointerforblocks));
				}
				if (Temporary == 3) {
					Row4.getChildren().add(PaneForBlocks.get(pointerforblocks));
				}
				pointerforblocks++;
			}		
			
			Temporary++;
			
			if (Temporary > 3) {
				Temporary = 0;
			}
		}
	}
	
	private class timerlistener implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			
			Bounds bounds = temp.getBoundsInLocal();
	        VBox sqbounds= snake.getSnakeBox();
	        
	        
	        // Row 1
	        if((Row1.getLayoutY() >= Length )){

	        	System.out.println(Row1.getChildren().size());
	        	
            	Row1.setLayoutY(bounds.getMinY() - 250);
            }
			
	        if (Row1.getLayoutY() == Length - sqbounds.getHeight()) {
				
	        	if (sqbounds.getTranslateX() >= 0 && sqbounds.getTranslateX() < 100) {
					Row1.getChildren().get(0).setVisible(false);
				}
	        	
	        	else if (sqbounds.getTranslateX() >= 100 && sqbounds.getTranslateX() < 200) {
					Row1.getChildren().get(1).setVisible(false);	
				}
				
	        	else if (sqbounds.getTranslateX() >= 200 && sqbounds.getTranslateX() < 300) {
					Row1.getChildren().get(2).setVisible(false);
				}
				
	        	else if (sqbounds.getTranslateX() >= 300 && sqbounds.getTranslateX() < 400) {
					Row1.getChildren().get(3).setVisible(false);
				}
				
	        	else if (sqbounds.getTranslateX() >= 400 && sqbounds.getTranslateX() < 500) {
					Row1.getChildren().get(4).setVisible(false);
				}
				
	        	else if (sqbounds.getTranslateX() >= 500 && sqbounds.getTranslateX() < 600) {
					Row1.getChildren().get(5).setVisible(false);
				}
				
	        	else if (sqbounds.getTranslateX() >= 600 && sqbounds.getTranslateX() < 700) {
					Row1.getChildren().get(6).setVisible(false);
				}
				
				else {
					Row1.getChildren().get(7).setVisible(false);
				}
			}
	        
	        
	        // Row 2
	        if((Row2.getLayoutY() >= Length )){

            	Row2.setLayoutY(bounds.getMinY() - 250);
            }
			
	        if (Row2.getLayoutY() == Length - sqbounds.getHeight()) {
				
	        	if (sqbounds.getTranslateX() >= 0 && sqbounds.getTranslateX() < 100) {
					Row2.getChildren().get(0).setVisible(false);
				}
	        	
	        	else if (sqbounds.getTranslateX() >= 100 && sqbounds.getTranslateX() < 200) {
					Row2.getChildren().get(1).setVisible(false);	
				}
				
	        	else if (sqbounds.getTranslateX() >= 200 && sqbounds.getTranslateX() < 300) {
					Row2.getChildren().get(2).setVisible(false);
				}
				
	        	else if (sqbounds.getTranslateX() >= 300 && sqbounds.getTranslateX() < 400) {
					Row2.getChildren().get(3).setVisible(false);
				}
				
	        	else if (sqbounds.getTranslateX() >= 400 && sqbounds.getTranslateX() < 500) {
					Row2.getChildren().get(4).setVisible(false);
				}
				
	        	else if (sqbounds.getTranslateX() >= 500 && sqbounds.getTranslateX() < 600) {
					Row2.getChildren().get(5).setVisible(false);
				}
				
	        	else if (sqbounds.getTranslateX() >= 600 && sqbounds.getTranslateX() < 700) {
					Row2.getChildren().get(6).setVisible(false);
				}
				
				else {
					Row2.getChildren().get(7).setVisible(false);
				}
			}
	        
	        // Row 3
	        if((Row3.getLayoutY() >= Length )){

            	Row3.setLayoutY(bounds.getMinY() - 250);
            }
			
	        if (Row3.getLayoutY() == Length - sqbounds.getHeight()) {
				
	        	if (sqbounds.getTranslateX() >= 0 && sqbounds.getTranslateX() < 100) {
					Row3.getChildren().get(0).setVisible(false);
				}
	        	
	        	else if (sqbounds.getTranslateX() >= 100 && sqbounds.getTranslateX() < 200) {
					Row3.getChildren().get(1).setVisible(false);	
				}
				
	        	else if (sqbounds.getTranslateX() >= 200 && sqbounds.getTranslateX() < 300) {
					Row3.getChildren().get(2).setVisible(false);
				}
				
	        	else if (sqbounds.getTranslateX() >= 300 && sqbounds.getTranslateX() < 400) {
					Row3.getChildren().get(3).setVisible(false);
				}
				
	        	else if (sqbounds.getTranslateX() >= 400 && sqbounds.getTranslateX() < 500) {
					Row3.getChildren().get(4).setVisible(false);
				}
				
	        	else if (sqbounds.getTranslateX() >= 500 && sqbounds.getTranslateX() < 600) {
					Row3.getChildren().get(5).setVisible(false);
				}
				
	        	else if (sqbounds.getTranslateX() >= 600 && sqbounds.getTranslateX() < 700) {
					Row3.getChildren().get(6).setVisible(false);
				}
				
				else {
					Row3.getChildren().get(7).setVisible(false);
				}
			}
	        
	        // Row 4
	        if((Row4.getLayoutY() >= Length)){

            	Row4.setLayoutY(bounds.getMinY() - 250);
            }
			
	        if (Row4.getLayoutY() == Length - sqbounds.getHeight()) {
				
	        	if (sqbounds.getTranslateX() >= 0 && sqbounds.getTranslateX() < 100) {
					Row4.getChildren().get(0).setVisible(false);
				}
	        	
	        	else if (sqbounds.getTranslateX() >= 100 && sqbounds.getTranslateX() < 200) {
					Row4.getChildren().get(1).setVisible(false);	
				}
				
	        	else if (sqbounds.getTranslateX() >= 200 && sqbounds.getTranslateX() < 300) {
					Row4.getChildren().get(2).setVisible(false);
				}
				
	        	else if (sqbounds.getTranslateX() >= 300 && sqbounds.getTranslateX() < 400) {
					Row4.getChildren().get(3).setVisible(false);
				}
				
	        	else if (sqbounds.getTranslateX() >= 400 && sqbounds.getTranslateX() < 500) {
					Row4.getChildren().get(4).setVisible(false);
				}
				
	        	else if (sqbounds.getTranslateX() >= 500 && sqbounds.getTranslateX() < 600) {
					Row4.getChildren().get(5).setVisible(false);
				}
				
	        	else if (sqbounds.getTranslateX() >= 600 && sqbounds.getTranslateX() < 700) {
					Row4.getChildren().get(6).setVisible(false);
				}
				
				else {
					Row4.getChildren().get(7).setVisible(false);
				}
			}
	        
	        
		}
		
	}
	private class mover implements EventHandler<ActionEvent> {

		double dy = speed; //Step on y or speed
		
	    @Override
	    public void handle(ActionEvent t) {    		
    		
    		//Checking bounds and making things disappear

	        //System.out.println(Temporary);
	        	 Row1.setLayoutY(Row1.getLayoutY() + dy);
	        	 Row2.setLayoutY(Row2.getLayoutY() + dy);
	        	 Row3.setLayoutY(Row3.getLayoutY() + dy);
	        	 Row4.setLayoutY(Row4.getLayoutY() + dy);
	        	        
	    }
	}
}

