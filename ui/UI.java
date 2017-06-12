package ui;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
class KeyBoardListener implements KeyListener
{
	public void keyTyped(KeyEvent e)
	{
	}
	public void keyReleased(KeyEvent e)
	{	
	}
	public void keyPressed(KeyEvent e)
	{
		//int movecode=e.getKeyCode();
		//call keyGetPressed()
		//call inputMoves(movecode)
	}
}
public class UI
{
	private static UI uniqueinstance; 
	private JFrame frame;
	private Canvas canvas;
	private final int framewidth=500,frameheight=500;
	private UI()
	{
		frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(framewidth,frameheight);
		frame.setVisible(true);
	}
	public static synchronized UI getinstance()
	{
		if(uniqueinstance==null)
		{
			uniqueinstance=new UI();
		}
		return uniqueinstance;
	}
	public void startMenu()
	{
		frame.remove(frame.getContentPane());
		frame.add(new JPanel());
		frame.getContentPane().setLayout(null);
		JButton button=new JButton("登入遊戲");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				waitingScreen();
			}
		}
		);
		button.setBounds(150,100, 150,50);
		frame.getContentPane().add(button);
		frame.setVisible(true);
		button=new JButton("退出遊戲");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
			}
		}
		);
		button.setBounds(150, 250,150,50);
		frame.getContentPane().add(button);
		frame.setVisible(true);
	}
	public void startGame()
	{
		Canvas canvas=new Canvas();
		frame.getContentPane().removeAll();
		frame.add(new JPanel());
		canvas=new Canvas();
		frame.add(canvas);
		frame.addKeyListener(new KeyBoardListener());
	}
	public int getCanvasWidth()
	{
		return canvas.getWidth();
	}
	public int getCanvasHeight()
	{
		return canvas.getHeight();
	}
	public void waitingScreen()
	{
		frame.getContentPane().removeAll();
		frame.getContentPane().add(new JPanel());
		frame.getContentPane().repaint();
		JLabel lbl=new JLabel();
		lbl.setIcon(new ImageIcon(this.getClass().getResource("/waitingscreen.jpg")));
		lbl.setBounds(0, 0,500,500);
		frame.getContentPane().add(lbl);
		frame.getContentPane().setLayout(null);
	}
	/*public static void main(String[] args)
	{		
	}*/
}

