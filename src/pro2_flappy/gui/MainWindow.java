package pro2_flappy.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import pro2_flappy.game.GameBoard;
import service.CsvGameBoardLoader;

public class MainWindow extends JFrame {

	BoardPanel pnl = new BoardPanel();
	GameBoard gameBoard;
	static long x = 0;

	Timer t;

	class BoardPanel extends JPanel {
		@Override
		public void paint(Graphics g) {
			super.paint(g);

			gameBoard.drawAndTestCollisions(g);
		}
	}

	public MainWindow() {
	/*	try (InputStream is = new FileInputStream("level.csv")) {
			CsvGameBoardLoader loader = new CsvGameBoardLoader(is);
			gameBoard = loader.loadLevel();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
*/
		LevelPicker picker = new LevelPicker();
		gameBoard = picker.pickAndLoadLevel();
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// gameBoard = new GameBoard();

		this.add(pnl, BorderLayout.CENTER);
		pnl.setPreferredSize(new Dimension(200, gameBoard.getHeightPix())); // TODO

		pack();

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// zavolame metodu kickTheBird
				gameBoard.kickTheBird();
			}
		});

		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_P) {
					if (t.isRunning())
						t.stop();
					else {
						t.start();
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					gameBoard.chean = !gameBoard.chean;
				}

			}
	
		});

		// z balicku Java.Swing - z dùvodu kompatibility se swing oknem
		t = new Timer(12, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				gameBoard.tick(x++);
				gameBoard.setX(x);
				pnl.repaint();
			}
		});
	
		t.start();

	}
	
	public static void setX(long y){
		x = y;
		
	}
	public void endGame(){
		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			MainWindow mainWindow;
			mainWindow = new MainWindow();
			mainWindow.setSize(860, 600);
			mainWindow.setVisible(true);
		});
	}

}
