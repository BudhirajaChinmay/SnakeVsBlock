package Game2;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javafx.animation.TranslateTransition;
import javafx.application.*;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class Main extends Application{

	// Random Generator
	private Random randin = new Random();
	// Default speed
	private static double speed = 0.09; 
	
	// Score of the Player
	private int Score = 0;
	
	//speeds
	private Scene game;
	private Scene menu;
	private Scene level;
	private Scene board;
	
	//stages
	private Stage theStage; 
	private Stage stage;
	
	// Pane to move all
	private Pane Moveall = new Pane();
	private Pane Moveall2 = new Pane();
	
	//Temporary StackPane & Pane
	private StackPane[] PaneForBlocks;
	private StackPane[] PaneForBlocks2;
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
	private ArrayList<Blocks> Obstruction = new ArrayList<Blocks>(8);
	private ArrayList<Blocks> Obstruction2 = new ArrayList<Blocks>(8);
	
	HBox ObstructionList = new HBox();
	HBox ObstructionList2 = new HBox();
	
	// Walls
	private Walls wall;
	private Walls wall2;

	private Pane MoveWall = new Pane();
	private Pane MoveWall2 = new Pane();
	private TranslateTransition transitionwall;
	
	// Balls
	private Balls balls;
	private Balls balls2;
	
	private Pane MoveBalls = new Pane();
	private Pane MoveBalls2 = new Pane();
	private TranslateTransition transitionballs;
	private TranslateTransition transitionballs2;
	
	// Translate Blocks
	HBox Movable = new HBox();
	HBox Movable2 = new HBox();
	
	private TranslateTransition transition;
	private TranslateTransition transition2;
	
	// Backgrounds
	private BackgroundSize backgroundSize;
	private BackgroundImage backgroundImage; 
	private Background background;
	
	// LeaderBoard
	private LeaderBoard b = new LeaderBoard();
	private ObservableList<Player> Board = b.getPlayer();
	
	// Snake Object and Corresponding Images
	private Snake snake = new Snake();
	private Image snakeHeadImg;
	private Image snakeBodyImg;
	
	//Dimensions Of the stage
	private final int Length = 800;
	private final int Width = 500;
	private int Initialpos = 250;		//Default value of the position when new game is started
	
	//Functions
	
	//Directions
	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}


	//Creating Menu
	private Parent createMenu() throws IOException { // creating the starting
		// menu
		
		Pane root = new Pane();
		
		backgroundSize = new BackgroundSize(Width, Length, false, false, false, false);
		backgroundImage = new BackgroundImage(new Image(new FileInputStream("D:\\eclipse-workspace\\SnakvsBlock\\src\\Game2\\MainPage.png")), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
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
			
		//root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		
		return root;
	}
	
	//Play the game
	public void startGame() throws Exception{
	
//		//BODY of the Snake
//		snakeHeadImg= new Image(new FileInputStream("C:\\Users\\Chinmay\\Desktop\\upmouth.png"));
//		snakeBodyImg= new Image(new FileInputStream("C:\\Users\\Chinmay\\Desktop\\snakeimage.png"));
//						
//		snake.snakeBox.setTranslateX(Initialpos);
//		snake.snakeBox.setTranslateY(Length-150);
//		snake.snakeBox.getChildren().add(new ImageView(snakeHeadImg));
//		
//		temp = new Pane();
//		temp.getChildren().add(snake.snakeBox);
//		game = new Scene(temp,Width,Length);
//		theStage.setScene(game);
//		theStage.show();
		
		play();
	}
	
	private void play() throws Exception{
		//BODY of the Snake
		
		backgroundSize = new BackgroundSize(Width, Length, false, false, false, false);
		backgroundImage = new BackgroundImage(new Image(new FileInputStream("D:\\eclipse-workspace\\SnakvsBlock\\src\\Game2\\bg3.jpeg")), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
		background = new Background(backgroundImage);
		
		StartNewGame = new Button("Start New Game");
		BackButton = new Button("Return");
		
		list.setLayoutY(0);
		list.setMinWidth(Width-100);
		list.getItems().add("Start New Game");
		list.getItems().add("Back Button");
		list.setAccessibleHelp("Pause");
		//list.getSelectionModel().selectedIndexProperty().addListener();
		score.setText(Integer.toString(Score));
		score.setTextFill(Color.web("#00FFFF"));
		score.setFont(new Font(30));
		score.setTranslateX(Width-60);
//		score.setText(Integer.toString(Score));
//		score.setLayoutX(Width-100);
		TopShow.getChildren().add(list);
		TopShow.getChildren().add(score);
		
		snakeHeadImg = new Image(new FileInputStream("D:\\eclipse-workspace\\SnakvsBlock\\src\\Game2\\upmouth.png"));
		snakeBodyImg = new Image(new FileInputStream("D:\\eclipse-workspace\\SnakvsBlock\\src\\Game2\\snakeimage.png"));
								
		snake.getSnakeBox().setTranslateX(Initialpos);
		snake.getSnakeBox().setTranslateY(Length-150);
		snake.getSnakeBox().getChildren().add(new ImageView(snakeHeadImg));
				
		//snake.getSnakeBox().setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
	
		temp = new Pane();
	
		game = new Scene(temp,Width,Length);

		temp.setBackground(background);
		PaneForBlocks = new StackPane[8];
		temp.getChildren().add(TopShow);
		temp.getChildren().add(snake.getSnakeBox());		
		
		for(int i=0;i<snake.getSize();i++)
		{
			snake.getSnakeBox().getChildren().add(new ImageView(snakeBodyImg));
		}
		
		
	//		for (int i = 0; i < 5; i++) {
	//		PaneForBlocks[i] = new StackPane();
	//		PaneForBlocks[i].setMaxSize(100, 100);
	//		Obstruction.add(new Blocks());
	//		PaneForBlocks[i].getChildren().addAll(Obstruction.get(i).shape,new Text(Integer.toString(Obstruction.get(i).getValue())));
	//		//ObstructionList.getChildren().add(Obstruction.get(i).shape/);
	//		Movable.getChildren().add(PaneForBlocks[i]);
	//	}
		//temp.getChildren().add(ObstructionList);
		
		
		//First
		
		Thread forBlock = new Thread(generateBlock); 	
		forBlock.setDaemon(true);
		forBlock.start();		
		
		wall = new Walls();
		Thread forWall = new Thread(generateWall);
		forWall.setDaemon(true);
		forWall.start();
		
		balls = new Balls();
		Thread forBall = new Thread(generateBall);
		forBall.setDaemon(true);
		forBall.start();
		
		
		//Second 
		
//		Thread forBlock2 = new Thread(generateBlock2); 	
//		forBlock2.setDaemon(true);
//		forBlock2.start();		
//		
//		wall2 = new Walls();
//		Thread forWall2 = new Thread(generateWall2);
//		forWall2.setDaemon(true);
//		forWall2.start();
//		
//		balls2 = new Balls();
//		Thread forBall2 = new Thread(generateBall2);
//		forBall2.setDaemon(true);
//		forBall2.start();
//		
		
		
		MoveBalls.getChildren().add(balls.getShape());
		MoveWall.getChildren().add(wall.getShape());
		
		Moveall.getChildren().add(MoveBalls);
		Moveall.getChildren().add(MoveWall);
		Moveall.getChildren().add(Movable);
		
//		temp.getChildren().add(Movable);
//		temp.getChildren().add(MoveBalls);
//		temp.getChildren().add(MoveWall);
		
		//Moveall.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		temp.getChildren().add(Moveall);
		
		//temp.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		
		Thread generateall = new Thread(generateThings);
		generateall.setDaemon(true);
		generateall.start();
		
//		Thread generateall2 = new Thread(generateThings);
//		generateall2.setDaemon(true);
//		generateall2.join(2223);
//		
//		transitionballs = new TranslateTransition();
//		transitionballs.setFromY(-250);
//		transitionballs.setRate(speed);
//		transitionballs.setCycleCount(transition.INDEFINITE);
//		transitionballs.setNode(Moveall);
//		transitionballs.setByY(Length+250);
//		transitionballs.setAutoReverse(false);
//		transitionballs.play();
		
//		transition = new TranslateTransition();
//		transition.setRate(speed);
//		transition.setCycleCount(transition.INDEFINITE);
//		transition.setNode(Movable);
//		transition.setByY(Length);
//		transition.setAutoReverse(false);
//		transition.play();
//		
//		transitionwall = new TranslateTransition();
//		transitionwall.setRate(speed);
//		transitionwall.setCycleCount(transition.INDEFINITE);
//		transitionwall.setNode(MoveWall);
//		transitionwall.setByY(Length);
//		transitionwall.setAutoReverse(false);
//		transitionwall.play();
		
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
		
		game.setOnKeyPressed(e-> Keypressed(e));
		theStage.setScene(game);
		theStage.show();
				
	}

	
	Task<Void> generateBall = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            while (true) {
                Platform.runLater(new Runnable() {
                    @Override
                    public synchronized void run() {
                            balls = new Balls();
                            MoveBalls.getChildren().remove(0);
                            MoveBalls.getChildren().add(balls.getShape());
                    }
                });

                Thread.sleep(4445);
            }
        }
    };
    
    Task<Void> generateBall2 = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            while (true) {
                Platform.runLater(new Runnable() {
                    @Override
                    public synchronized void run() {
                            balls2 = new Balls();
                            MoveBalls2.getChildren().remove(0);
                            MoveBalls2.getChildren().add(balls.getShape());
                    }
                });

                Thread.sleep(4445);
            }
        }
    };
    
    Task<Void> generateThings = new Task<Void>() {
    	protected Void call() throws Exception {
            while (true) {
                Platform.runLater(new Runnable() {
                    @Override
                    public synchronized void run() {
                    	transitionballs = new TranslateTransition();
                		transitionballs.setFromY(-250);
                		transitionballs.setRate(speed);
                		transitionballs.setNode(Moveall);
                		transitionballs.setByY(Length+250);
                		transitionballs.setAutoReverse(false);
                		transitionballs.play();
                    }
                });

                Thread.sleep(4445);
            }
        }
    };
    
    Task<Void> generateThings2 = new Task<Void>() {
    	protected Void call() throws Exception {
            while (true) {
                Platform.runLater(new Runnable() {
                    @Override
                    public synchronized void run() {
                    	transitionballs2 = new TranslateTransition();
                		transitionballs2.setFromY(-250);
                		transitionballs2.setRate(speed);
						transitionballs2.setNode(Moveall2);
                		transitionballs2.setByY(Length+250);
                		transitionballs2.setAutoReverse(false);
                		transitionballs2.play();
                    }
                });

                Thread.sleep(4445);
            }
        }
    };
    
    Task<Void> generateWall = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            while (true) {
                Platform.runLater(new Runnable() {
                    @Override
                    public synchronized void run() {
                            wall = new Walls();
                            MoveWall.getChildren().remove(0);
                            MoveWall.getChildren().add(wall.getShape());
                    }
                });

                Thread.sleep(4445);
            }
        }
    };
    
    Task<Void> generateBlock = new Task<Void>() {
    	@Override
        protected Void call() throws Exception {
            while (true) {
                Platform.runLater(new Runnable() {
                    @Override
                    public synchronized void run() {
                    		
                    	Obstruction.clear();
                    	System.out.println(Obstruction.size());
                    	Movable.getChildren().clear();
                		for (int i = 0; i < 5; i++) {
	            			PaneForBlocks[i] = new StackPane();
	            			PaneForBlocks[i].setMaxSize(100, 100);
	            			Obstruction.add(new Blocks());
	            	
	            			PaneForBlocks[i].getChildren().addAll(Obstruction.get(i).getShape(),new Text(Integer.toString(Obstruction.get(i).getValue())));
	            			//ObstructionList.getChildren().add(Obstruction.get(i).shape/);
	            			if (Obstruction.get(i).getValue() == 0) {
	            				PaneForBlocks[i].setVisible(false);
	            			}
	            			Movable.getChildren().add(PaneForBlocks[i]);
	            		}
                    }
                   });

                Thread.sleep(4445);
            }
        }
    };
//
//	private void recursKey(Scene game2) {
//	
//		
//	}


//	private Parent create() {
//	
//		
//		Pane root = new Pane();
//		
//		return root;
//	}


	// LeaderBoard
	
	@SuppressWarnings("unchecked")
	public Parent Board() throws Exception {
		
		Pane root = new Pane();
		
		root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		
		backgroundSize = new BackgroundSize(Width, Length, false, false, false, false);
		Image image = new Image(new FileInputStream("D:\\eclipse-workspace\\SnakvsBlock\\src\\Game2\\l4.jpg"));
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
		backgroundImage = new BackgroundImage(new Image(new FileInputStream("D:\\eclipse-workspace\\SnakvsBlock\\src\\Game2\\upmouth.png")), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize);
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
	

}
