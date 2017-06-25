package ui;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import tcp.codes;
import tcp.TCPClient;

class KeyBoardListener implements KeyListener
{
	final static int upcode=38,downcode=40,rightcode=39,leftcode=37;
	final static int attackcode=87;
	final HashMap<Integer,Integer> codetable;
	public void keyTyped(KeyEvent e)
	{
		
	}
	public void keyReleased(KeyEvent e)
	{
		int code=e.getKeyCode();
		//TCPClient.getClient().keyRelease(code);
	}
	public void keyPressed(KeyEvent e)
	{
		int code=e.getKeyCode();
		//TCPClient.getClient().keyDown(code);
		//TCPClient.getClient().keyDown(codetable.get(code));
	}
	public KeyBoardListener()
	{
		codetable=new HashMap();
		codetable.put(upcode,codes.MOVEUP);
		codetable.put(leftcode,codes.MOVELEFT);
		codetable.put(rightcode,codes.MOVERIGHT);
		codetable.put(downcode,codes.MOVEDOWN);
		codetable.put(attackcode, codes.ATTACK);
	}
}
public class UI
{
	private static UI uniqueinstance; 
	private JFrame frame;
	private Canvas canvas;
	private final int framewidth=500,frameheight=500;
	private BufferStrategy bs;
	private JPanel startmenupanel,waitingpanel;
	private JPanel endgamepanel,gamepanel;
    private String srvaddr;
	private UI()
	{
		frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(framewidth,frameheight);
		frame.setVisible(true);
		frame.setResizable(false);
		buildStartMenuPanel();
		
	}
	private void buildStartMenuPanel()
	{
		startmenupanel=new JPanel();
		assert startmenupanel!=null:"startmenupanel is null";
		startmenupanel.setLayout(null);
		JButton button=new JButton("Start Game");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				waitingScreen();
                new Thread() {
                    public void run() {
                        TCPClient.getClient().connectServer(srvaddr);
                    }
                }.start();
			}
		}
		);
		button.setBounds(150,100, 150,50);
		startmenupanel.add(button);
		frame.setVisible(true);
		button=new JButton("Exit");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
			}
		}
		);
		button.setBounds(150, 250,150,50);
		startmenupanel.add(button);
		frame.setVisible(true);
	}
	public static synchronized UI getInstance()
	{
		if(uniqueinstance==null)
		{
			uniqueinstance=new UI();
		}
		return uniqueinstance;
	}
	public Graphics getGraphics()
	{
		bs=canvas.getBufferStrategy();
		if(bs==null)
		{
			canvas.createBufferStrategy(2);
			return null;
		}
		Graphics g=bs.getDrawGraphics();
		return g;
	}
	public BufferStrategy getBufferStrategy()
	{
		bs=canvas.getBufferStrategy();
		if(bs==null)
		{
			canvas.createBufferStrategy(2);
			return null;
		}
		else
			return bs;
	}
	public void startMenu(String _srvaddr)
	{
        srvaddr = _srvaddr;
		frame.remove(frame.getContentPane());
		assert startmenupanel!=null:"startmenupanel is null";
		frame.add(startmenupanel);
	}
	public void startGame()
	{
		canvas=new Canvas();
		frame.getContentPane().removeAll();
		frame.add(new JPanel());
		canvas=new Canvas();
		canvas.setBounds(0,0,framewidth,frameheight);		
		frame.add(canvas);
		frame.addKeyListener(new KeyBoardListener());
		frame.setVisible(true);
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
		frame.add(new JPanel());
		frame.getContentPane().repaint();
		JLabel lbl=new JLabel();
		lbl.setIcon(new ImageIcon(this.getClass().getResource("../resource/waitingscreen.jpg")));
		lbl.setBounds(0, 0,500,500);
		frame.getContentPane().add(lbl);
		frame.getContentPane().setLayout(null);
	}
	/*public static void main(String[] args)
	{
		UI demo=new UI();
		demo.startGame();
	}*/
}