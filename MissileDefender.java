//Programmer: Cameron Dufault
//Purpose: A missile defence game where the user must click on the missiles descending
//         from the top of the screen before they reach the city they are defending.
//Program Name: MissileDefender.java
//Date of Completion: june 16 2016

//seperate method for city health colour and showing increases in score

//importing required libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import javazoom.jl.player.Player;

public class MissileDefender
{
    //timers
    static Timer missile1Timer;
    static Timer missile2Timer;
    static Timer missile3Timer;
    static Timer missile4Timer;
    static Timer missile5Timer;

    //ImageIcons
    static ImageIcon cityPic = new ImageIcon ("city.jpg");
    static ImageIcon explosionGif = new ImageIcon ("explosion.gif");
    static ImageIcon loseCityPic = new ImageIcon ("deadcity.jpg");
    static ImageIcon losePic = new ImageIcon ("lose.jpg");
    static ImageIcon missilePic = new ImageIcon ("missile.jpg");
    static ImageIcon titlePic = new ImageIcon ("title.gif");
    static ImageIcon winPic = new ImageIcon ("winimage.jpg");

    //JLabel images
    static JLabel city = new JLabel (cityPic);
    static JLabel explosion = new JLabel (explosionGif);
    static JLabel lose = new JLabel (losePic);
    static JLabel loseCity = new JLabel (loseCityPic);
    static JLabel title = new JLabel (titlePic);
    static JLabel win = new JLabel (winPic);


    //Strings
    static String endNoise = "endscreen.mp3";
    static String explosionSound = "explosionsound.mp3";
    static String filename = "mario.mp3";
    static String gameSound = "gametheme.mp3";
    static String levelEnd = "levelclear.mp3";

    //mp3's
    static MP3 endScreen = new MP3 (endNoise);
    static MP3 explosionNoise = new MP3 (explosionSound);
    static MP3 gameTheme = new MP3 (gameSound);
    static MP3 levelClear = new MP3 (levelEnd);
    static MP3 marioTheme = new MP3 (filename);


    //Jlabels
    static JLabel cityHealth = new JLabel ("City Health: ");
    static JLabel finalMessage = new JLabel ();
    static JLabel instructions = new JLabel ("<HTML>The city is under attack! It is your job to defend your city and<BR> save the citizens by destroying the missiles sent to destroy<BR> you. Click on the missiles to destroy them. Dont let your city health drop to 0%, or you lose!");
    static JLabel level = new JLabel ("Level: ");
    static JLabel score = new JLabel ("Score: ");
    static JLabel myName = new JLabel ("CREATED BY CAMERON DUFAULT");

    //Missile Buttons
    static JButton missile1 = new JButton (missilePic);
    static JButton missile2 = new JButton (missilePic);
    static JButton missile3 = new JButton (missilePic);
    static JButton missile4 = new JButton (missilePic);
    static JButton missile5 = new JButton (missilePic);

    //Level change Buttons
    static JButton level2 = new JButton ("Continue to Level 2");
    static JButton level3 = new JButton ("Continue to Level 3");
    static JButton level4 = new JButton ("Continue to Level 4");
    static JButton level5 = new JButton ("Continue to Level 5");

    //Miscellaneous buttons
    static JButton exitGame = new JButton ("Exit");
    static JButton playGame = new JButton ("Begin Playing");
    static JButton playAgain = new JButton ("Play Again");
    static JButton start = new JButton ("Start");

    //frane
    static JFrame frame = new JFrame ("Missile Defender");

    //panels
    static JPanel bottomPanel = new JPanel ();
    static JPanel topPanel = new JPanel ();

    //missile 1 values
    static int missile1X = 0;
    static int missile1Y = 0;
    static int missile1XShift = 0; //how much the missile shifts along the x-axis per timer cycle
    static int missile1YShift = 0;  //how much the missile shifts along the y-axis per timer cycle

    //missile 2 values
    static int missile2X = 0;
    static int missile2Y = 0;
    static int missile2XShift = 0; //how much the missile shifts along the x-axis per timer cycle
    static int missile2YShift = 0; //how much the missile shifts along the y-axis per timer cycle

    //missile 3 values
    static int missile3X = 0;
    static int missile3Y = 0;
    static int missile3XShift = 0; //how much the missile shifts along the x-axis per timer cycle
    static int missile3YShift = 0; //how much the missile shifts along the y-axis per timer cycle

    //missile 4 values
    static int missile4X = 0;
    static int missile4Y = 0;
    static int missile4XShift = 0; //how much the missile shifts along the x-axis per timer cycle
    static int missile4YShift = 0; //how much the missile shifts along the y-axis per timer cycle

    //missile 5 values
    static int missile5X = 0;
    static int missile5Y = 0;
    static int missile5XShift = 0; //how much the missile shifts along the x-axis per timer cycle
    static int missile5YShift = 0; //how much the missile shifts along the y-axis per timer cycle

    //number of times timer has run
    static int cycleCount = 0;

    //GUI APP//////////////////////////////////////////////////////////////////////////////////////////////////

    private static void guiApp ()
    {
	ButtonHandler onClick = new ButtonHandler ();

	//formatting frame
	frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE); //setting frame to exit on close
	frame.setResizable (false); //frame is not resizable
	frame.setBounds (600, 100, 800, 800);

	//content pane
	Container contentPane = frame.getContentPane ();
	contentPane.add (topPanel, BorderLayout.CENTER);
	contentPane.add (bottomPanel, BorderLayout.SOUTH);

	//panel 1 null layour
	topPanel.setLayout (null);

	//creating fonts
	Font bottomPanelFont = new Font ("Impact", Font.PLAIN, 20);
	Font buttonsFont = new Font ("Impact", Font.BOLD, 20);
	Font instructionsFont = new Font ("Aharoni", Font.BOLD, 20);


	//setting fonts
	//buttonsFont
	exitGame.setFont (buttonsFont);
	finalMessage.setFont (buttonsFont);
	instructions.setFont (buttonsFont);
	playAgain.setFont (buttonsFont);
	playGame.setFont (buttonsFont);
	start.setFont (buttonsFont);
	myName.setFont (instructionsFont);

	//INstructionsFont
	instructions.setFont (instructionsFont);

	//bottomPanelFont
	score.setFont (bottomPanelFont);
	level.setFont (bottomPanelFont);
	cityHealth.setFont (bottomPanelFont);

	//adding action listeners
	start.addActionListener (onClick);
	playGame.addActionListener (onClick);
	playAgain.addActionListener (onClick);
	exitGame.addActionListener (onClick);

	//adding listeners to missiles
	missile1.addActionListener (onClick);
	missile2.addActionListener (onClick);
	missile3.addActionListener (onClick);
	missile4.addActionListener (onClick);
	missile5.addActionListener (onClick);

	//adding listeners to level buttons
	level2.addActionListener (onClick);
	level3.addActionListener (onClick);
	level4.addActionListener (onClick);
	level5.addActionListener (onClick);

	//adding components to top panel
	topPanel.add (city);
	topPanel.add (exitGame);
	topPanel.add (explosion);
	topPanel.add (finalMessage);
	topPanel.add (instructions);
	topPanel.add (myName);
	topPanel.add (level2);
	topPanel.add (level3);
	topPanel.add (level4);
	topPanel.add (level5);
	topPanel.add (lose);
	topPanel.add (loseCity);
	topPanel.add (missile1);
	topPanel.add (missile2);
	topPanel.add (missile3);
	topPanel.add (missile4);
	topPanel.add (missile5);
	topPanel.add (playAgain);
	topPanel.add (title);
	topPanel.add (win);


	//adding components to bottom panel
	bottomPanel.add (cityHealth);
	bottomPanel.add (level);
	bottomPanel.add (playGame);
	bottomPanel.add (score);
	bottomPanel.add (start);

	//setting component backgrounds
	topPanel.setBackground (Color.BLACK);
	bottomPanel.setBackground (Color.GRAY);
	start.setBackground (Color.RED);
	instructions.setBackground (Color.BLACK);
	finalMessage.setBackground (Color.BLACK);

	//setting component foregrounds
	instructions.setForeground (Color.WHITE);
	finalMessage.setForeground (Color.WHITE);
	myName.setForeground (Color.WHITE);

	//hiding components
	city.setVisible (false);
	cityHealth.setVisible (false);
	exitGame.setVisible (false);
	finalMessage.setVisible (false);
	instructions.setVisible (false);
	level.setVisible (false);
	level2.setVisible (false);
	level3.setVisible (false);
	level4.setVisible (false);
	level5.setVisible (false);
	lose.setVisible (false);
	loseCity.setVisible (false);
	missile1.setVisible (false);
	missile2.setVisible (false);
	missile3.setVisible (false);
	missile4.setVisible (false);
	missile5.setVisible (false);
	playAgain.setVisible (false);
	playGame.setVisible (false);
	score.setVisible (false);
	win.setVisible (false);


	//setting positions of components
	city.setBounds (0, 600, 800, 170);
	exitGame.setBounds (480, 130, 100, 100);
	finalMessage.setBounds (240, 520, 300, 100);
	instructions.setBounds (140, 120, 600, 600);
	level2.setBounds (300, 130, 200, 100);
	level3.setBounds (300, 130, 200, 100);
	level4.setBounds (300, 130, 200, 100);
	level5.setBounds (300, 130, 200, 100);
	lose.setBounds (210, 300, 274, 184);
	loseCity.setBounds (0, 600, 800, 170);
	playAgain.setBounds (220, 130, 150, 100);
	title.setBounds (240, 130, 356, 256);
	win.setBounds (210, 300, 274, 184);
	myName.setBounds (240, 500, 400, 184);


	//making components visible
	bottomPanel.setVisible (true);
	frame.setVisible (true);


    } //guiapp method


    ////////BUTTON HANDLER////////////////////////////////////////////////////////////////


    private static class ButtonHandler implements ActionListener
    {

	//initizialing varibles
	int currentScore = 0; //score
	int currentCityHealth = 100; //city Health
	int currentLevel = 1; // level
	int missileNum = 0; //number of missiles blown up so far in level



	public void actionPerformed (ActionEvent e)  //when buttons are clicked
	{

	    if (e.getSource () == start) //if the user clicks the start button
	    {
		//showing and hidding components
		myName.setVisible (false);
		start.setVisible (false);
		title.setVisible (false);
		playGame.setVisible (true);
		instructions.setVisible (true);

		//game theme
		gameTheme.play ();

	    } //start button

	    if (e.getSource () == missile1) //if the user clicks the first missile
	    {
		explosionNoise.play ();
		missile1Timer.stop (); //stop timer
		currentScore = currentScore + 5; //add 5 points to score
		score.setText ("Score: " + currentScore + "     ");

		explosion.setBounds (missile1X, missile1Y, 200, 200); //show explosions image at location of missile
		explosion.setVisible (true);

		//resetting missile values
		missile1X = (int) (Math.random () * 400 + 1);
		missile1Y = 0;
		missile1XShift = (int) (Math.random () * 7 + 5);
		missile1YShift = (int) (Math.random () * 20 + 10);

		missileNum = missileNum + 1; //increase missile counter

		missile1Timer.restart (); //begin timer again

	    } //missile1 button

	    if (e.getSource () == missile2) //if the user clicks the 2nd missile
	    {
		explosionNoise.play ();

		missile2Timer.stop (); //stop timer and add 5 to score
		currentScore = currentScore + 5;
		score.setText ("Score: " + currentScore + "     ");

		explosion.setBounds (missile2X, missile2Y, 200, 200); //show explosion image a location
		explosion.setVisible (true);


		//resetting missile values
		missile2X = (int) (Math.random () * 400 + 400);
		missile2Y = 0;
		missile2XShift = (int) (Math.random () * -7 - 5);
		missile2YShift = (int) (Math.random () * 20 + 10);

		missileNum = missileNum + 1; //increase missile counter

		missile2Timer.restart (); //begin timer again

	    } //missile2 button

	    if (e.getSource () == missile3) //if the user clicks the 3rd missile
	    {
		explosionNoise.play ();

		missile3Timer.stop (); //stop timer and add 5 to score
		currentScore = currentScore + 5;
		score.setText ("Score: " + currentScore + "     ");

		explosion.setBounds (missile3X, missile3Y, 200, 200);
		explosion.setVisible (true);

		//resetting missile values
		missile3X = (int) (Math.random () * 500 + 1);
		missile3Y = 0;
		missile3XShift = (int) (Math.random () * 7 + 5);
		missile3YShift = (int) (Math.random () * 20 + 10);

		missileNum = missileNum + 1; //increase missile counter

		missile3Timer.restart (); //begin timer again

	    } //missile3 button

	    if (e.getSource () == missile4)  //if the user clicks the 4th missile
	    {
		explosionNoise.play ();

		missile4Timer.stop (); //stop timer and add 5 to score
		currentScore = currentScore + 5;
		score.setText ("Score: " + currentScore + "     ");

		explosion.setBounds (missile4X, missile4Y, 200, 200);
		explosion.setVisible (true);

		//resetting missile values
		missile4X = (int) (Math.random () * 500 + 1);
		missile4Y = 0;
		missile4XShift = (int) (Math.random () * 7 + 5);
		missile4YShift = (int) (Math.random () * 20 + 10);

		missileNum = missileNum + 1; //increase missile counter

		missile4Timer.restart (); //begin timer again

	    } //missile4 button

	    if (e.getSource () == missile5) //if the user clicks the 5th missile
	    {
		explosionNoise.play ();

		cycleCount = 0; //setting the amount of times the timer has run back to 0

		missile5Timer.stop (); //stop timer and add 5 to score
		currentScore = currentScore + 5;
		score.setText ("Score: " + currentScore + "     ");

		explosion.setBounds (missile5X, missile5Y, 200, 200);
		explosion.setVisible (true);

		//resetting missile values
		missile5X = (int) (Math.random () * 400 + 400);
		missile5Y = 0;
		missile5XShift = (int) (Math.random () * -7 - 1);
		missile5YShift = (int) (Math.random () * 20 + 10);

		missileNum = missileNum + 1; //increase missile counter

		missile5Timer.restart (); //begin timer again

	    } //missile5 button

	    if (e.getSource () == exitGame) //if the user clicks the exit button
	    {
		System.exit (0); //close program
	    } //exit button

	    if ((e.getSource () == playGame) || (e.getSource () == playAgain)) // if the user clicks the play game button or play again button
	    {
		//restart theme
		gameTheme.close ();
		gameTheme.play ();

		//resetting values
		currentScore = 0;
		currentCityHealth = 100;
		currentLevel = 1;
		missileNum = 0;

		//hiding components
		exitGame.setVisible (false);
		finalMessage.setVisible (false);
		instructions.setVisible (false);
		lose.setVisible (false);
		missile2.setVisible (false);
		missile3.setVisible (false);
		missile4.setVisible (false);
		missile5.setVisible (false);
		playAgain.setVisible (false);
		playGame.setVisible (false);
		win.setVisible (false);


		//resetting the colour of the topPanel and city health
		topPanel.setBackground (Color.BLACK);
		cityHealth.setForeground (Color.BLACK);


		//showing components
		bottomPanel.setVisible (true);
		score.setVisible (true);
		cityHealth.setVisible (true);
		level.setVisible (true);
		city.setVisible (true);
		missile1.setVisible (true);

		//settting text of bottom panel components
		cityHealth.setText ("City Health = " + currentCityHealth + "%     ");
		score.setText ("Score: " + currentScore + "     ");
		level.setText ("Level:" + " " + currentLevel);

		//setting initial missile1 values
		missile1X = (int) (Math.random () * 400 + 1);
		missile1Y = 0;
		missile1XShift = (int) (Math.random () * 7 + 5);
		missile1YShift = (int) (Math.random () * 20 + 10);

		//update Missile1 timer
		Action updateMissile1 = new AbstractAction ()
		{
		    public void actionPerformed (ActionEvent e)  //action performed method
		    {

			if ((missileNum > 12)) //if the number of missile explosions so far is above 12
			{
			    missile1.setVisible (false); // hide missile and stop timer
			    missile1Timer.stop ();

			    explosion.setVisible (false); //hide explosion

			    gameTheme.close (); //stop theme music and play level end music
			    levelClear.play ();

			    if (currentLevel == 1) // if it's level 1
			    {
				//stopping the program for 2 seconds
				try
				{
				    Thread.sleep (2000);

				}
				catch (InterruptedException ie)
				{
				}

				level2.setVisible (true); //show level 2 button

				currentScore = (currentScore + currentCityHealth); //update score with city health
				score.setText ("Score: " + currentScore + "     ");

				currentLevel = 2;
				level.setText ("Level:" + " " + currentLevel);

			    } //end if
			} //end if

			missile1X = missile1X + missile1XShift; //increase the x value of the missile with the shift value each time the timer runs
			missile1Y = missile1Y + missile1YShift; //increase the y value of the missile with the shift value each time the timer runs
			missile1.setBounds (missile1X, missile1Y, 30, 113); //setting new placement

			//if the missile reaches the city at y-400 or leaves the window
			if ((missile1X > 800) || (missile1Y > 400))
			{
			    //play explosion noise and move missile to new position slightly above the city skyline, at missiles x position
			    explosionNoise.play ();
			    explosion.setVisible (false);
			    explosion.setBounds (missile1X, missile1Y + 50, 200, 200);
			    explosion.setVisible (true);

			    missile1Timer.stop ();

			    //resetting missile values
			    missile1X = (int) (Math.random () * 400 + 1);
			    missile1Y = 0;
			    missile1XShift = (int) (Math.random () * 7 + 5);
			    missile1YShift = (int) (Math.random () * 20 + 10);

			    missileNum = missileNum + 1; //increase missile counter

			    currentCityHealth = currentCityHealth - 5; //changing and updating city health
			    cityHealth.setText ("City Health: " + currentCityHealth + "% ");

			    healthColour (currentCityHealth); //changes colour of cityHealth label and city pic if currentCityHealth is less than 0

			    missile1Timer.restart (); //restart timer

			} //end if



		    } //actionPerformed method
		} // updatemissile action
		;
		//creating missile1Timer variable
		missile1Timer = new Timer (100, updateMissile1); //runs ever 0.1 seconds
		missile1Timer.start ();


	    } //end level 1

	    //if the user clicks the level 2 button
	    if (e.getSource () == level2)
	    {
		//play game music from beginning
		gameTheme.play ();

		//hide level 2 button and show both missiles
		level2.setVisible (false);
		missile1.setVisible (true);
		missile2.setVisible (true);

		missile1Timer.start (); //start missile1Timer back up

		missileNum = 0; //reset explosion count

		//setting initial missile4 values
		missile2X = (int) (Math.random () * 400 + 400);
		missile2Y = 0;
		missile2XShift = (int) (Math.random () * -7 - 5);
		missile2YShift = (int) (Math.random () * 25 + 10);

		//updateMissile2 timer
		Action updateMissile2 = new AbstractAction ()
		{
		    public void actionPerformed (ActionEvent e)
		    {
			if ((missileNum > 12))  //if the number of missile explosions so far is above 12
			{
			    missile2.setVisible (false); //hide missile and stop timer
			    missile2Timer.stop ();

			    explosion.setVisible (false); //hide explosion

			    if (currentLevel == 2) //if it is level 2
			    {
				//stop program for 2 seconds
				try
				{
				    Thread.sleep (2000);

				}
				catch (InterruptedException ie)
				{
				}

				level3.setVisible (true); //show level 3 button

				currentScore = (currentScore + currentCityHealth); //add city health to score and update
				score.setText ("Score: " + currentScore + "     ");

				currentLevel = 3; //set level to 3 and update
				level.setText ("Level:" + " " + currentLevel);
			    } //end if

			} //end if


			missile2X = missile2X + missile2XShift; //increase the x value of the missile with the shift value each time the timer runs
			missile2Y = missile2Y + missile2YShift; //increase the y value of the missile with the shift value each time the timer runs
			missile2.setBounds (missile2X, missile2Y, 30, 113); //setting new placement

			//if the missile reaches the city at y-400 or leaves the window
			if ((missile2X > 800) || (missile2Y > 400))
			{
			    //play explosion noise and move missile to new position slightly above the city skyline, at missiles x position
			    explosionNoise.play ();
			    explosion.setVisible (false);
			    explosion.setBounds (missile2X, missile2Y + 50, 200, 200);
			    explosion.setVisible (true);

			    missile2Timer.stop ();

			    //resetting missile2 values
			    missile2X = (int) (Math.random () * 400 + 400);
			    missile2Y = 0;
			    missile2XShift = (int) (Math.random () * -7 - 5);
			    missile2YShift = (int) (Math.random () * 25 + 10);

			    missileNum = missileNum + 1; //increase missile count

			    currentCityHealth = currentCityHealth - 5; //changing and updating city health
			    cityHealth.setText ("City Health: " + currentCityHealth + "% ");

			    healthColour (currentCityHealth); //changes colour of cityHealth label and city pic if currentCityHealth is less than 0

			    missile2Timer.restart ();
			} //end if
		    } //actionPerformed method
		} //updateMissile2 action
		;

		missile2Timer = new Timer (100, updateMissile2); //creating missile2Timer variable, update timer every 0.1 seconds
		missile2Timer.start ();

	    } //end if

	    //if the user clicks the level 3 button
	    if (e.getSource () == level3)
	    {
		gameTheme.play (); //play game music from beginning

		//hide level 3 button and show missiles for level
		level3.setVisible (false);
		missile1.setVisible (true);
		missile2.setVisible (true);
		missile3.setVisible (true);

		//start previous timers and set their missiles y value to 0
		missile1Timer.start ();
		missile2Timer.start ();
		missile1Y = 0;
		missile2Y = 0;

		missileNum = 0;  //reset missile number count

		//setting initial missile2  values
		missile3X = (int) (Math.random () * 400 + 1);
		missile3Y = 0;
		missile3XShift = (int) (Math.random () * 7 + 5);
		missile3YShift = (int) (Math.random () * 25 + 10);

		// updateMissile3 timer
		Action updateMissile3 = new AbstractAction ()
		{
		    public void actionPerformed (ActionEvent e)
		    {
			if ((missileNum > 12)) //if the number of missile explosions so far is above 12
			{
			    missile3.setVisible (false); //hide missile and stop timer
			    missile3Timer.stop ();

			    explosion.setVisible (false);

			    if (currentLevel == 3) //if it is level 3
			    {
				//stop program for 2 seconds
				try
				{
				    Thread.sleep (2000);

				}
				catch (InterruptedException ie)
				{
				}

				level4.setVisible (true); //show level 4 button

				currentScore = (currentScore + currentCityHealth); //add city health value to score
				score.setText ("Score: " + currentScore + "     ");

				currentLevel = 4; //set level to 4 and update level label
				level.setText ("Level:" + " " + currentLevel);
			    } //end if
			} //end if


			missile3X = missile3X + missile3XShift; //increase the x value of the missile with the shift value each time the timer runs
			missile3Y = missile3Y + missile3YShift;  //increase the y value of the missile with the shift value each time the timer runs
			missile3.setBounds (missile3X, missile3Y, 30, 113);  //setting new placement

			//if the missile reaches the city at y-400 or leaves the window
			if ((missile3X > 800) || (missile3Y > 400))
			{
			    //play explosion noise and move missile to new position slightly above the city skyline, at missiles x position
			    explosionNoise.play ();
			    explosion.setVisible (false);
			    explosion.setBounds (missile3X, missile3Y + 50, 200, 200);
			    explosion.setVisible (true);

			    missile3Timer.stop ();

			    //resetting missile3 values
			    missile3X = (int) (Math.random () * 400 + 1);
			    missile3Y = 0;
			    missile3XShift = (int) (Math.random () * 7 + 5);
			    missile3YShift = (int) (Math.random () * 25 + 10);

			    missileNum = missileNum + 1; //increase missile counter

			    currentCityHealth = currentCityHealth - 5; //changing and updating city health
			    cityHealth.setText ("City Health: " + currentCityHealth + "% ");

			    healthColour (currentCityHealth); //changes colour of cityHealth label and city pic if currentCityHealth is less than 0

			    missile3Timer.restart ();

			} //end if
		    } //actionPerformed method
		} //updateMissile3 action
		;

		missile3Timer = new Timer (100, updateMissile3); //creating missile3Timer variable, update timer every 0.1 seconds
		missile3Timer.start ();

	    } //end if

	    //if the user clicks the level 4 button
	    if (e.getSource () == level4)
	    {
		gameTheme.play (); //play game music from beginning

		//hide level 4 button and show missiles for level
		level4.setVisible (false);
		missile1.setVisible (true);
		missile2.setVisible (true);
		missile3.setVisible (true);
		missile4.setVisible (true);

		//start previous timers and set their missiles y value to 0
		missile1Timer.start ();
		missile2Timer.start ();
		missile3Timer.start ();
		missile1Y = 0;
		missile2Y = 0;
		missile3Y = 0;

		missileNum = 0; //increase missile count

		//resetting missile4 values
		missile4X = (int) (Math.random () * 400 + 1);
		missile4Y = 0;
		missile4XShift = (int) (Math.random () * 10 + 5);
		missile4YShift = (int) (Math.random () * 20 + 10);

		// updateMissile4 action
		Action updateMissile4 = new AbstractAction ()  // updateMissile4 timer
		{
		    public void actionPerformed (ActionEvent e)
		    {
			if ((missileNum > 12)) //if the number of missile explosions so far is above 12
			{
			    missile4.setVisible (false); //hide missile and stop timer
			    missile4Timer.stop ();

			    explosion.setVisible (false);

			    if (currentLevel == 4) //if it is level 4
			    {
				//stop program for 2 seconds
				try
				{
				    Thread.sleep (2000);

				}
				catch (InterruptedException ie)
				{
				}

				level5.setVisible (true); //show level 4 button

				currentScore = (currentScore + currentCityHealth); //add city health value to score
				score.setText ("Score: " + currentScore + "     ");

				currentLevel = 5; //set level to 5 and update level label
				level.setText ("Level:" + " " + currentLevel);

			    } //end if
			} //end if


			missile4X = missile4X + missile4XShift; //increase the x value of the missile with the shift value each time the timer runs
			missile4Y = missile4Y + missile4YShift; //increase the y value of the missile with the shift value each time the timer runs
			missile4.setBounds (missile4X, missile4Y, 30, 113); //setting new placement

			//if the missile reaches the city at y-400 or leaves the window
			if ((missile4X > 800) || (missile4Y > 400))
			{
			    //play explosion noise and move missile to new position slightly above the city skyline, at missiles x position
			    explosionNoise.play ();
			    explosion.setVisible (false);
			    explosion.setBounds (missile4X, missile4Y + 50, 200, 200);
			    explosion.setVisible (true);

			    missile4Timer.stop ();

			    //resetting missile4 values
			    missile4X = (int) (Math.random () * 400 + 1);
			    missile4Y = 0;
			    missile4XShift = (int) (Math.random () * 7 + 5);
			    missile4YShift = (int) (Math.random () * 20 + 10);

			    missileNum = missileNum + 1; //increase missile count

			    currentCityHealth = currentCityHealth - 5; //changing and updating city health
			    cityHealth.setText ("City Health: " + currentCityHealth + "% ");

			    healthColour (currentCityHealth); //changes colour of cityHealth label and city pic if currentCityHealth is less than 0

			    missile4Timer.restart ();

			} //end if
		    } //actionPerformed method
		} //updateMissile4 action
		;

		missile4Timer = new Timer (100, updateMissile4); //creating missile4Timer variable, update timer every 0.1 seconds
		missile4Timer.start ();
	    } //end if

	    //if the level 5 button is clicked
	    if (e.getSource () == level5)
	    {


		gameTheme.play (); //play game music from beginning

		//hide level 5 button and show missiles for level
		level5.setVisible (false);
		missile1.setVisible (true);
		missile2.setVisible (true);
		missile3.setVisible (true);
		missile4.setVisible (true);
		missile5.setVisible (true);

		//start previous timers and set their missiles y value to 0
		missile1Timer.start ();
		missile2Timer.start ();
		missile3Timer.start ();
		missile4Timer.start ();
		missile1Y = 0;
		missile2Y = 0;
		missile3Y = 0;
		missile4Y = 0;

		missileNum = 0; //increase missile count

		//resetting missile4 values
		missile5X = (int) (Math.random () * 400 + 400);
		missile5Y = 0;
		missile5XShift = (int) (Math.random () * -7 - 5);
		missile5YShift = (int) (Math.random () * 25 + 10);

		// updateMissile5 action
		Action updateMissile5 = new AbstractAction ()
		{
		    public void actionPerformed (ActionEvent e)
		    {

			if ((missileNum > 12)) //if the number of missile explosions so far is above 12
			{
			    missile5Timer.stop (); //hide missile and stop timer
			    missile5.setVisible (false);

			    explosion.setVisible (false);

			    currentScore = (currentScore + currentCityHealth); //changing and updating city health
			    cityHealth.setText ("City Health: " + currentCityHealth + "% ");

			    //stop program for 2 seconds
			    try
			    {
				Thread.sleep (2000);

			    }
			    catch (InterruptedException ie)
			    {
			    }

			    endScreen (currentCityHealth, currentScore); //chooses what to display on end screen

			    //hiding components
			    city.setVisible (false);
			    loseCity.setVisible (false);
			    bottomPanel.setVisible (false);
			    missile1.setVisible (false);
			    missile2.setVisible (false);
			    missile3.setVisible (false);
			    missile4.setVisible (false);
			    missile5.setVisible (false);

			    //showing components
			    playAgain.setVisible (true);
			    exitGame.setVisible (true);
			    finalMessage.setVisible (true);

			} //end if

			cycleCount = cycleCount + 1;

			//structure used to determine if missile is moving left or right
			//if cycleCount is a certain number
			if (((cycleCount < 3) && (0 < cycleCount)) || ((cycleCount < 7) && (4 < cycleCount)) || ((cycleCount < 11) && (8 < cycleCount) || ((cycleCount < 15) && (12 < cycleCount)) || ((cycleCount < 19) && (16 < cycleCount))))
			{
			    missile5X = missile5X - missile5XShift; //descrase the x value of the missile
			    missile5Y = missile5Y + missile5YShift; //increase the y value of the missile with the shift value
			    missile5.setBounds (missile5X, missile5Y, 30, 113); //setting new placement
			}
			else //if cycleCount is not a specified numeber
			{
			    missile5X = missile5X + missile5XShift; //increase the x value of the missile with the shift value
			    missile5Y = missile5Y + missile5YShift; //increase the y value of the missile with the shift value
			    missile5.setBounds (missile5X, missile5Y, 30, 113); //setting new placement
			} //end if


			//if the missile reaches the city at y-400 or leaves the window
			if ((missile5X > 800) || (missile5Y > 400))
			{
			    cycleCount = 0;

			    //play explosion noise and move missile to new position slightly above the city skyline, at missiles x position
			    explosionNoise.play ();
			    explosion.setVisible (false);
			    explosion.setBounds (missile5X, missile4Y + 50, 200, 200);
			    explosion.setVisible (true);

			    missile5Timer.stop ();

			    //resetting missile4 values
			    missile5X = (int) (Math.random () * 400 + 400);
			    missile5Y = 0;
			    missile5XShift = (int) (Math.random () - 15 - 5);
			    missile5YShift = (int) (Math.random () * 25 + 10);

			    missileNum = missileNum + 1; //increase missile count

			    currentCityHealth = currentCityHealth - 5; //changing and updating city health
			    cityHealth.setText ("City Health: " + currentCityHealth + "% ");

			    healthColour (currentCityHealth); //changes colour of cityHealth label and city pic if currentCityHealth is less than 0

			    missile5Timer.restart ();

			} //end if
		    } //actionPerformed method
		} //updateMissile5 action
		;
		missile5Timer = new Timer (100, updateMissile5); //creating missile4Timer variable, update timer every 0.1 seconds
		missile5Timer.start ();
	    } //end if

	} //end game
    } //buttonHander class


    public static void main (String[] args)
    {
	//schedule a job for the event-dispatching thread:
	//creating and showing this applications GUI.
	javax.swing.SwingUtilities.invokeLater (new Runnable ()
	{
	    public void run ()
	    {
		guiApp ();
	    }
	}


	);
    } //main method


    public static void endScreen (int currentCityHealth, int currentScore)
    {
	if (currentCityHealth > 0) //if the city health is 0 or below
	{
	    finalMessage.setText ("<HTML> CONGRATULATIONS ON SUCCESSFULLY DEFENDING YOUR CITY! <BR>   YOUR FINAL SCORE IS: " + currentScore);
	    win.setVisible (true); //victory picture
	    topPanel.setBackground (Color.lightGray);
	}


	else //city health is above 0
	{
	    finalMessage.setText ("<HTML> YOU FAILED AT PROTECTING YOUR CITY! <BR> YOUR FINAL SCORE IS: " + currentScore);
	    lose.setVisible (true); //losePic picture
	    topPanel.setBackground (Color.darkGray);
	} //end if
    } // endScreen method


    public static void healthColour (int currentCityHealth)
    {
	if (currentCityHealth < 1) //if city health is 0 or below
	{
	    //change city picture
	    city.setVisible (false);
	    loseCity.setVisible (true);

	    cityHealth.setForeground (Color.RED);

	} //end if
    } //healthcolour method
} //the missileDefender class






