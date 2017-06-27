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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.Border;
import dom.DOM;

import tcp.codes;
import tcp.TCPClient;
import logger.Logger;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;


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
        if(!codetable.containsKey(code)) return;
		TCPClient.getClient().keyRelease(codetable.get(code));
	}
	public void keyPressed(KeyEvent e)
	{
		int code=e.getKeyCode();
        if(!codetable.containsKey(code)) return;
		TCPClient.getClient().keyDown(codetable.get(code));
	}
	public KeyBoardListener()
	{
		codetable=new HashMap<Integer, Integer>();
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
	private final int framewidth=800,frameheight=800;
	private final int canvaswidth=500,canvasheight=500;
	private BufferStrategy bs;
    private String srvaddr;
    private int Maxplayerno;
    private JProgressBar lifebar;
    private JLabel[] lbl;
    private JButton startbutton,exitbutton;
    private JLabel waitingscreenimage;
	private UI()
	{
		frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(framewidth,frameheight);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setResizable(false);		
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
        startbutton=new JButton("Start Game");
		startbutton.addActionListener(new ActionListener()
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
		startbutton.setBounds(300,100, 150,50);
		frame.add(startbutton);
		frame.repaint();
		frame.setVisible(true);
		exitbutton=new JButton("Exit");
		exitbutton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
			}
		}
		);
		exitbutton.setBounds(300, 500,150,50);
		frame.add(startbutton);
		frame.add(exitbutton);
		frame.repaint();
		frame.setVisible(true);
	}
	public void showScore()
	{
		Maxplayerno=dom.DOM.getInstance().getPlayerNumber();
		if(lbl==null)
		{
			lbl=new JLabel[Maxplayerno];
			for(int i=0;i<Maxplayerno;i+=1)
			{
				String tmp="";
				lbl[i]=new JLabel(tmp);
				lbl[i].setBounds(50,i*100+10,250,50);
				lbl[i].setFont(new Font("Serif", Font.PLAIN, 30));   
			}
		}
		for(int i=0;i<Maxplayerno;i+=1)
		{
			int score=DOM.getInstance().getPlayerScore(i);
			String tmp="Player "+String.valueOf(i)+" : ";
			tmp+=String.valueOf(score);
			lbl[i].setText(tmp);
			frame.add(lbl[i]);
			frame.repaint();
		}
		if(lifebar==null)
			lifebar=new JProgressBar();
		int health,maxhealth;
		health=DOM.getInstance().getPlayerHealth();
		maxhealth=DOM.getInstance().getPlayerMaxHealth();
		lifebar.setValue(health*100/maxhealth); 
		Border border = BorderFactory.createMatteBorder(6,6,6,6,Color.BLUE);  
		lifebar.setStringPainted(true);
		lifebar.setBorder(border);
		lifebar.setBounds(200,50,300,100);
		frame.add(lifebar);
		frame.repaint();
	}
	public void startGame()
	{
		canvas=new Canvas();
		frame.remove(waitingscreenimage);
		frame.repaint();
		canvas=new Canvas();
		canvas.setBounds(300,300,canvaswidth,canvasheight);		
		canvas.addKeyListener(new KeyBoardListener());
		frame.add(canvas);
		frame.repaint();
		frame.addKeyListener(new KeyBoardListener());
		showScore();
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
		waitingscreenimage=new JLabel();
		waitingscreenimage.setIcon(new ImageIcon(this.getClass().getResource("../resource/waitingscreen.jpg")));
		waitingscreenimage.setBounds(0, 0,500,500);
		frame.remove(startbutton);
		frame.remove(exitbutton);
		frame.add(waitingscreenimage);
		frame.repaint();
		frame.setVisible(true);
	}
	public void endGameScreen()
	{
		int[] number=new int[Maxplayerno];
		int[] finalscore=new int[Maxplayerno];
		JLabel[] finalscorelabel=new JLabel[Maxplayerno];
		for(int i=0;i<Maxplayerno;i+=1)
			number[i]=i;
		for(int i=0;i<Maxplayerno;i+=1)
			finalscore[i]=DOM.getInstance().getPlayerScore(i);
		for(int i=Maxplayerno;i>=0;i-=1)
		{
			for(int j=0;j<i;j+=1)
			{
				if(finalscore[j]>finalscore[j+1])
				{
					int tmpscore=finalscore[j],tmpnum=num[j];
					finalscore[j]=finalscore[j+1];
					finalscore[j+1]=tmpscore;
					num[j]=num[j+1];
					num[j+1]=tmpnum;
				}
			}
		}
		for(int i=0;i<Maxplayerno;i+=1)
		{
			finalscorelabel[i]="Player "+String.valueOf(i)+" : ";
			finalscorelabel[i]=String.valueOf(finalscore[i]);
			finalscorelabel[i].setBounds(100,100+i*100,100,200);
			frame.add(finalscorelabel[i]);
		}
		frame.setVisible(true);
	}
	/*public static void main(String[] args)
	{
		UI demo=new UI();
		demo.startGame();
	}*/
}
