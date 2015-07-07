package edu.wayne.csc3200.hw4.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import edu.wayne.csc3200.hw4.core.Game;
import edu.wayne.csc3200.hw4.core.Player;
import edu.wayne.csc3200.hw4.core.Square;

public class ScrabbleGui extends JFrame implements ActionListener {

	Game game;

	private int gameBoardSize = 15;

	private static final long serialVersionUID = 1L;

	JPanel scorePanel = new JPanel();
	JButton pNameButton;
	JTextField pScoreLabel;
	JLabel titleLabel1;

	JPanel playerOptionPanel = new JPanel();
	JButton placeButton;
	JButton buyButton;
	JButton addPlayer;
	JTextArea playerInfo;
	JButton endTurn;
	String specialTile = "";

	JPanel rackPanel = new JPanel();
	JLabel currentPlayerNameLabel;
	JLabel p1Rack;
	JLabel titleLabel;
	JLabel specialSymbol;

	JPanel addPanel = new JPanel(new FlowLayout());

	JPanel gridPanel = new JPanel();
	JLabel[][] grid = new JLabel[gameBoardSize][gameBoardSize];
	JPanel[][] squares = new JPanel[gameBoardSize][gameBoardSize];
	boolean firstMove = true;

	/**
	 * creates the Gui
	 * 
	 * @param game
	 */
	public ScrabbleGui(Game game) {
		setLayout(new BorderLayout());
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		setBounds(screenSize.width / 5, screenSize.height / 12, 700, 600);

		setTitle("Scrabble With Stuff");
		setResizable(false);

		this.game = game;		

		initGUI();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JOptionPane.showMessageDialog(this, "Welcome To Scrabble With Stuff!"
				+ "\nTo start the game click 'Add Player' above.");
		pack();

	}

	/**
	 * initializes all the panels for the GUi L&R
	 */
	private void initGUI() {
		
		initGrid();
		initScorePanel();
		initRackPanel();
		initPlayerOptionPanel();
		initMenu();
		JButton addPlayer = new JButton("Add Player");
		addPlayer.setBackground(Color.CYAN);
		addPlayer.setToolTipText("Add a player to the game, limit 4.");
		addPlayer.addActionListener(this);
		addPanel.add(addPlayer);
		add(addPanel, BorderLayout.NORTH);
		add(gridPanel);
		add(scorePanel, BorderLayout.WEST);
		add(rackPanel, BorderLayout.SOUTH);
		add(playerOptionPanel, BorderLayout.EAST);

	}

	/**
	 * initializes menubar options
	 */
	private void initMenu() {
		JMenuBar menu = new JMenuBar();
		JMenu fileMenu = new JMenu("Game");
		JMenu screenOptions = new JMenu("Screen Options");
		menu.add(fileMenu);
		menu.add(screenOptions);

		JMenuItem newGame = new JMenuItem("New Game");
		JMenuItem exitGame = new JMenuItem("Exit");
		JMenuItem fullScreen = new JMenuItem("Go Fullscreen");
		JMenuItem normalScreen = new JMenuItem("Exit Fullscreen");

		fileMenu.add(newGame);
		fileMenu.add(exitGame);
		screenOptions.add(fullScreen);
		screenOptions.add(normalScreen);

		setJMenuBar(menu);
		newGame.addActionListener(this);
		exitGame.addActionListener(this);
		fullScreen.addActionListener(this);
		normalScreen.addActionListener(this);
	}

	/**
	 * initializes players name and score panel
	 */
	private void initScorePanel() {
		scorePanel.setLayout(new GridBagLayout());
		scorePanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(20, 10, 20, 10);
		gbc.gridwidth = 4;
		gbc.gridy = 0;
		gbc.gridx = 0;
		JLabel titleLabel = new JLabel("Players");
		scorePanel.add(titleLabel, gbc);

	}

	/**
	 * initializes the current players rack information fields
	 */
	private void initRackPanel() {
		rackPanel.setLayout(new GridBagLayout());
		rackPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridwidth = 4;
		gbc.gridy = 0;
		gbc.gridx = 0;
		JLabel titleLabel = new JLabel("Player's Rack");
		rackPanel.add(titleLabel, gbc);

		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 1;

		currentPlayerNameLabel = new JLabel();
		p1Rack = new JLabel();
		specialSymbol = new JLabel();
		specialSymbol.setText("-");
		JLabel specialText = new JLabel("SpecialTile Slot:");

		currentPlayerNameLabel.setFont(currentPlayerNameLabel.getFont()
				.deriveFont(18));
		currentPlayerNameLabel.setText("Player Name");
		rackPanel.add(currentPlayerNameLabel, gbc);

		gbc.gridx = 4;
		p1Rack.setText("");
		rackPanel.add(p1Rack, gbc);

		gbc.gridy = 2;
		gbc.gridx = 1;
		rackPanel.add(specialText, gbc);

		gbc.gridy = 2;
		gbc.gridx = 6;
		rackPanel.add(specialSymbol, gbc);

	}

	/**
	 * initializes the options a player may take
	 */
	private void initPlayerOptionPanel() {
		playerOptionPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.gridwidth = 4;
		gbc.gridy = 0;
		gbc.gridx = 0;
		JLabel titleLabel = new JLabel("Player Options");
		playerOptionPanel.add(titleLabel, gbc);

		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = 2;
		gbc.gridx = 1;
		gbc.gridy++;
		placeButton = new JButton("Place Tile(s)");
		placeButton.setEnabled(false);
		placeButton.setBackground(Color.GREEN);
		placeButton.setToolTipText("Place tiles from your rack to the board.");
		playerOptionPanel.add(placeButton, gbc);

		gbc.gridy++;
		buyButton = new JButton("Buy Special Tile");
		buyButton.setEnabled(false);
		buyButton.setBackground(Color.GREEN);
		buyButton.setToolTipText("Buy a special tile.");
		playerOptionPanel.add(buyButton, gbc);

		gbc.gridheight = 2;
		gbc.gridwidth = 2;

		gbc.gridy++;
		playerInfo = new JTextArea();

		playerInfo.setText("Special Tiles Available:\n"
				+ "NAME        SYMBOL   COST\n"
				+ "Boom             'B'           50\n"
				+ "Lose Score   'L'           30\n"
				+ " -50 Points    'N'           25\n"
				+ "Reverse         'R'           20\n"// );
				+ "Skip                'S'           12\n");

		playerInfo.setColumns(15);
		playerInfo.setRows(10);
		playerInfo.setEditable(false);
		playerInfo.setLineWrap(true);
		playerInfo.setWrapStyleWord(true);
		playerInfo.setBorder(BorderFactory.createBevelBorder(1));
		playerOptionPanel.add(playerInfo, gbc);

		gbc.gridwidth = 4;
		gbc.gridy = 7;
		endTurn = new JButton("End Turn");
		endTurn.setEnabled(false);
		endTurn.setBackground(Color.YELLOW);
		endTurn.setToolTipText("End your turn.");
		playerOptionPanel.add(endTurn, gbc);

		playerOptionPanel
				.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		endTurn.addActionListener(this);
		buyButton.addActionListener(this);
		placeButton.addActionListener(this);

	}

	/**
	 * initializes the grid with squares and labels with square content
	 * information
	 */
	private void initGrid() {
		gridPanel.setLayout(new GridLayout(gameBoardSize, gameBoardSize));
		gridPanel.setMinimumSize(new Dimension(400, 400));

		for (int row = 0; row < gameBoardSize; row++) {
			for (int column = 0; column < gameBoardSize; column++) {
				Square square = game.getGameBoard().getSquare(row, column);
				JPanel panel = new JPanel();
				JLabel label = new JLabel(getContent(square));
				label.setBackground(getSquareColor(square));
				panel.setBackground(getSquareColor(square));
				if (row == 7 && column == 7)
					panel.setBackground(Color.PINK);
				panel.setSize(50, 50);
				panel.setBorder(BorderFactory.createEtchedBorder());
				panel.add(label);
				squares[row][column] = panel;
				grid[row][column] = label;
				gridPanel.add(panel);
			}
		}

	}

	/**
	 * Colors each of the squares by what bonus type each cell is
	 * 
	 * @param square
	 * @return
	 */
	private Color getSquareColor(Square square) {
		char content = square.getSquareType();
		switch (content) {
		case Square.THREE_WORD_BONUS:
			return Color.RED;
		case Square.TWO_WORD_BONUS:
			return new Color(255, 228, 150);
		case Square.THREE_LETTER_BONUS:
			return Color.CYAN;
		case Square.TWO_LETTER_BONUS:
			return new Color(100, 149, 237);

		}
		return new Color(210, 210, 210);
	}

	/**
	 * displays each squares bonus type in conjunction with corresponding color
	 * 
	 * @param square
	 * @return
	 */
	private String getContent(Square square) {
		char content = square.getSquareType();
		if (square.containsLetter()) {
			return "" + content;
		} else {
			switch (content) {
			case Square.THREE_WORD_BONUS:
				return "3W";
			case Square.TWO_WORD_BONUS:
				return "2W";
			case Square.THREE_LETTER_BONUS:
				return "3L";
			case Square.TWO_LETTER_BONUS:
				return "2L";
			}
		}
		return "";
	}

	/**
	 * updates the board to display words and special tile location
	 */
	private void updateBoard() {

		for (int row = 0; row < gameBoardSize; row++) {
			for (int column = 0; column < gameBoardSize; column++) {
				JLabel label = grid[row][column];
				Square square = game.getGameBoard().getSquare(row, column);
				if (square.containsLetter()) {
					squares[row][column].setBackground(Color.WHITE);
					label.setText("" + square.getContainedchar());
				}
				if (square.isContainsSpecial()
						&& square.getSpecialLetter().getOwnerID() == game
								.getCurrentPlayer().getID())
					squares[row][column].setBackground(Color.ORANGE);
				else if (square.isContainsSpecial()
						&& square.getSpecialLetter().getOwnerID() != game
								.getCurrentPlayer().getID())
					squares[row][column].setBackground(getSquareColor(square));

			}
		}
	}

	/**
	 * updates the players with the current games player information in score
	 * panel
	 */
	private void updatePlayers() {
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.insets = new Insets(20, 5, 20, 5);
		gbc.gridy = 0;
		gbc.gridx = 0;
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = 2;

		for (int i = 0; i < game.getTurnOrder().size(); i++) {
			pNameButton = new JButton();
			pScoreLabel = new JTextField();
			pScoreLabel.setEditable(false);
			pScoreLabel.setColumns(5);

			gbc.gridy += 1;
			gbc.gridx = 0;

			pNameButton.setText(game.getTurnOrder().get(i).getName());
			pNameButton.setBackground(Color.GREEN);
			if (game.getTurnOrder().get(i).equals(game.getCurrentPlayer())
					&& !game.getTurnOrder().isEmpty())
				pNameButton.setBackground(Color.GREEN);
			else
				pNameButton.setBackground(Color.GRAY);

			scorePanel.add(pNameButton, gbc);

			gbc.gridx = 2;
			pScoreLabel.setText("");
			pScoreLabel
					.setText(game.getTurnOrder().get(i).displayPlayerScore());
			pScoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
			scorePanel.add(pScoreLabel, gbc);
		}

	}

	/**
	 * sets buttons in the player option panel to enabled for turn by turn use
	 */
	private void enableButtons() {
		placeButton.setEnabled(true);
		buyButton.setEnabled(true);
		endTurn.setEnabled(true);
	}

	/**
	 * updates the current players visible rack with letter contents
	 */
	private void updateRack() {

		for (Player p : game.getTurnOrder()) {
			p.fillRack(game.getGameBag());
			p.rerack(game.getGameBag());
		}
		specialSymbol.setText(game.displaySpecialTileSlot());
		currentPlayerNameLabel.setText(game.getCurrentPlayer().getName());
		p1Rack.setText(game.getCurrentPlayer().displayPlayerRack());
	}

	/**
	 * updates current players special tile slot symbol
	 */
	private void updateText() {
		specialSymbol.setText(game.displaySpecialTileSlot());

	}

	private void endTurnHelper() {
		game.getCurrentPlayer().addScore(100);
		if (!game.gameEnded()) {
			String title = "Congratulations";
			String message = game.declareWinner().getName()
					+ " is this game's winner with a score of "
					+ game.declareWinner().getPlayerScore();
			int reply = JOptionPane.showConfirmDialog(this, message, title,
					JOptionPane.OK_OPTION);

			if (reply == 0)
				System.exit(0);
			else if (reply == 1)
				System.exit(0);
		} else {
			game.nextTurn();
			enableButtons();
			update();

			revalidate();
		}
	}

	private void placeHelper(JButton callButton) {

		String[] specialTileChoices = { "Word", "Special Tile" };
		String placeTitle = "Choose action";
		String placeMessage = "What kind of tile(s) would you like to place?:";
		if (game.getCurrentPlayer().getSpecialTileSlot().get(0).getSymbol() != '-') {
			String optionChoice = (String) JOptionPane.showInputDialog(null,
					placeTitle, placeMessage, JOptionPane.QUESTION_MESSAGE,
					null, specialTileChoices, specialTileChoices[0]);
			if (optionChoice == null
					|| (optionChoice != null && ("".equals(optionChoice))))
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			else if (optionChoice == "Special Tile") {

				String[] intChoice = { "1", "2", "3", "4", "5", "6", "7", "8",
						"9", "10", "11", "12", "13" };
				JComboBox<String> xField = new JComboBox<>(intChoice);
				JComboBox<String> yField = new JComboBox<>(intChoice);

				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Row Number:"));
				myPanel.add(xField);
				myPanel.add(Box.createHorizontalStrut(15)); // a
															// spacer
				myPanel.add(new JLabel("Column Number"));
				myPanel.add(yField);

				int result = JOptionPane.showConfirmDialog(null, myPanel,
						"Please Enter Row and Column Values",
						JOptionPane.OK_CANCEL_OPTION);
				int x = Integer.parseInt((String) xField.getSelectedItem());
				int y = Integer.parseInt((String) yField.getSelectedItem());

				if (result == JOptionPane.OK_OPTION) {
					if (!game.getGameBoard().getSquare(x, y)
							.isPlayerHasPlaced()) {
						game.placeSpecialTile(x, y);
						callButton.setEnabled(false);
					} else
						JOptionPane.showMessageDialog(this,
								"Sorry, that location is occupied",
								"Try again Later.", JOptionPane.ERROR_MESSAGE);
				} else
					setDefaultCloseOperation(DISPOSE_ON_CLOSE);

			} else if (optionChoice == "Word") {
				String[] intChoice = { "1", "2", "3", "4", "5", "6", "7", "8",
						"9", "10", "11", "12", "13", "14", "15" };
				String[] dirChoice = { "0", "1" };
				// String[] dirChoice = { "\u2192", "\u2193" };
				JTextField word = new JTextField(10);
				JComboBox<String> xField = new JComboBox<>(intChoice);
				JComboBox<String> yField = new JComboBox<>(intChoice);
				JComboBox<String> dirField = new JComboBox<>(dirChoice);

				JPanel myPanel = new JPanel();
				myPanel.add(new JLabel("Type Word"));
				myPanel.add(word);
				myPanel.add(Box.createHorizontalStrut(15));
				myPanel.add(new JLabel("Row Number:"));
				myPanel.add(xField);
				myPanel.add(Box.createHorizontalStrut(15)); // a
															// spacer
				myPanel.add(new JLabel("Column Number"));
				myPanel.add(yField);
				myPanel.add(Box.createHorizontalStrut(15));
				myPanel.add(new JLabel("Direction"));
				myPanel.add(dirField);

				word.getText().trim();

				int result = JOptionPane
						.showConfirmDialog(
								null,
								myPanel,
								"Please Enter Row,Column, and Direction(0 -right,1-down) Values.",
								JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					int x = Integer.parseInt((String) xField.getSelectedItem());
					int y = Integer.parseInt((String) yField.getSelectedItem());
					if (game.getGameDict().lookUpWord(word.getText().trim())
							|| !game.getGameBoard().isOccupied(x - 1, y - 1)) {

						int dir = Integer.parseInt((String) dirField
								.getSelectedItem());
						if (!firstMove) {
							game.getGameBoard().placeOnBoard(
									word.getText().trim(), x - 1, y - 1, dir,
									game.getCurrentPlayer(),
									game.getGameDict(), game);
						} else {
							game.getGameBoard().placeOnBoard(
									word.getText().trim(), 7, 7, dir,
									game.getCurrentPlayer(),
									game.getGameDict(), game);
							firstMove = false;
						}
						if (game.isSpecialAct() == true) {
							JOptionPane
									.showMessageDialog(
											this,
											"A special tile has been triggered!",
											"Too bad "
													+ game.getCurrentPlayer()
															.getName(),
											JOptionPane.WARNING_MESSAGE);
						}

						callButton.setEnabled(false);
					} else
						JOptionPane.showMessageDialog(this,
								"Sorry, that word is not accepted",
								"Try again Later.", JOptionPane.ERROR_MESSAGE);
				} else
					setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}

		} else
			JOptionPane.showMessageDialog(this,
					"Sorry, you don't have a Special Tile", "Try again Later.",
					JOptionPane.ERROR_MESSAGE);
	}

	private void update() {
		updateRack();
		updateBoard();
		updatePlayers();
		updateText();
	}

	private void buyHelper() {
		String[] specialTileChoices = { "B", "L", "N", "R", "S" };
		String buyTitle = "Buy a Special Tile";
		String buyMessage = "Select a Special tile:";
		String selectedTile = (String) JOptionPane.showInputDialog(null,
				buyTitle, buyMessage, JOptionPane.QUESTION_MESSAGE, null,
				specialTileChoices, specialTileChoices[0]);
		if (selectedTile == null
				|| (selectedTile != null && ("".equals(selectedTile))))
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		else if (!game.checkPoints(selectedTile))
			JOptionPane.showMessageDialog(this,
					"Sorry, you don't have enough points", "Try again Later.",
					JOptionPane.ERROR_MESSAGE);
		else {
			game.addSpecialTile(selectedTile);
			buyButton.setEnabled(false);
		}
	}

	private void addPlayerHelper(String[] choices, JButton callButton) {
		String playerChoice = (String) JOptionPane.showInputDialog(null,
				"How many players will play?", "Number of Players",
				JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
		while (playerChoice == null
				|| (playerChoice != null && ("".equals(playerChoice))))
			try {
				playerChoice = (String) JOptionPane
						.showInputDialog(null, "How many players will play?",
								"Number of Players",
								JOptionPane.QUESTION_MESSAGE, null, choices,
								choices[0]);
			} catch (Exception e) {

				e.printStackTrace();
			}
		int nbrPlayers = Integer.parseInt(playerChoice);

		for (int i = 0; i < nbrPlayers; i++) {
			String name = JOptionPane
					.showInputDialog("Enter name of new Player");
			game.addPlayer(name);

		}
		game.setCurrentPlayer(game.getTurnOrder().get(0));
		updateRack();
		updatePlayers();
		callButton.setVisible(false);

		//game.getCurrentPlayer().addScore(100);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String[] choices = { "1", "2", "3", "4" };
		Object eventSource = e.getSource();
		if (eventSource instanceof JButton) {
			JButton callButton = (JButton) eventSource;
			String cmd = callButton.getText();
			if (cmd != null) {

				if (cmd.equals("Add Player")) {
					addPlayerHelper(choices, callButton);
					enableButtons();
					revalidate();

				}
				if (cmd.equals("End Turn")) {
					endTurnHelper();

				}

				if (cmd.equals("Buy Special Tile")) {
					buyHelper();
					update();
					revalidate();
					return;
				}
				if (cmd.equals("Place Tile(s)")) {
					placeHelper(callButton);
					update();
					revalidate();
				}

			}

		}
		if (eventSource instanceof JMenuItem) {
			JMenuItem callButton = (JMenuItem) eventSource;
			String cmd = callButton.getText();
			if (cmd.equals("New Game")) {
				int x = JOptionPane.showConfirmDialog(this,
						"Are you sure you want to start a new game?",
						"New Game?", JOptionPane.YES_NO_OPTION);
				if (x == 0) {
					this.dispose();
					game.getTurnOrder().clear();
					game.startGame();
				} else
					setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}// new game

			if (cmd.equals("Go Fullscreen")) {
				setExtendedState(JFrame.MAXIMIZED_BOTH);
			}
			if (cmd.equals("Exit Fullscreen")) {
				setExtendedState(JFrame.NORMAL);
			}
			if (cmd.equals("Exit")) {
				this.dispose();
			}
		}
	}

}
