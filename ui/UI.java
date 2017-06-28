package ui;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.*;
import dom.DOM;
import tcp.*;
import logger.Logger;
import render.RenderThread;

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
	private final int framewidth=800,frameheight=700;
	private final int canvaswidth=800,canvasheight=600;
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
		startbutton.setBounds((framewidth - 150)/ 2, frameheight / 3, 150, 50);
		frame.add(startbutton);
		frame.repaint();
		frame.setVisible(true);
		exitbutton = new JButton("Exit");
		exitbutton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
			}
		}
		);
		exitbutton.setBounds((framewidth - 150)/ 2, frameheight * 2 / 3, 150, 50);
		frame.add(startbutton);
		frame.add(exitbutton);
		frame.repaint();
		frame.setVisible(true);
	}
	public void showScore()
	{
		Maxplayerno=DOM.getInstance().getPlayerNumber();
		if(lbl==null)
		{
			lbl=new JLabel[Maxplayerno];
			for(int i=0;i<Maxplayerno;i+=1)
			{
				String tmp="";
				lbl[i]=new JLabel(tmp);
				lbl[i].setBounds(50 + i * ((framewidth - 100)/ 4), 10, 250, 50);
				lbl[i].setFont(new Font("Serif", Font.PLAIN, 18));   
			}
		}
		int[] playerscore=new int[Maxplayerno];
		int[] playerid=new int[Maxplayerno];
		for(int i=0;i<Maxplayerno;i+=1)
		{
			playerid[i]=i;
			playerscore[i]=DOM.getInstance().getPlayerScore(i);
		}
		for(int i=Maxplayerno;i>=0;i-=1)
		{
			for(int j=0;j<i - 1;j+=1)
			{
				if(playerscore[j]<playerscore[j+1])
				{
					int tmpscore=playerscore[j],tmpid=playerid[j];
					playerscore[j]=playerscore[j+1];
					playerid[j]=playerid[j+1];
					playerscore[j+1]=tmpscore;
					playerid[j+1]=tmpid;
				}
			}
		}
		for(int i=0;i<Maxplayerno;i+=1)
		{
			String tmp = "Player " + playerid[i] +" score " + playerscore[i];
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
		Border border = null;
        if(health == 0) {
            BorderFactory.createMatteBorder(6, 6, 6, 6, Color.RED);  
        } else {
            BorderFactory.createMatteBorder(6, 6, 6, 6, Color.BLUE);  
        }
		lifebar.setStringPainted(true);
		lifebar.setBorder(border);
		lifebar.setBounds(10 ,frameheight - canvasheight - 40 - 10, 300, 40);
		frame.add(lifebar);
		frame.repaint();
	}
	public void startGame()
	{
		canvas=new Canvas();
		frame.remove(waitingscreenimage);
		frame.repaint();
		canvas=new Canvas();
		canvas.setBounds(0, frameheight - canvasheight ,canvaswidth, canvasheight);		
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
		waitingscreenimage.setBounds(framewidth - canvaswidth, frameheight - canvasheight, frameheight, canvasheight);
		frame.remove(startbutton);
		frame.remove(exitbutton);
		frame.add(waitingscreenimage);
		frame.repaint();
		frame.setVisible(true);
	}
	public void endGameScreen()
	{
        RenderThread.stop();
		Maxplayerno=DOM.getInstance().getPlayerNumber();
        int[] number=new int[Maxplayerno];
		int[] finalscore=new int[Maxplayerno];
		for(int i=0;i<Maxplayerno;i+=1)
			number[i]=i;
		for(int i=0;i<Maxplayerno;i+=1)
			finalscore[i]=DOM.getInstance().getPlayerScore(i);
		for(int i=Maxplayerno;i>=0;i-=1)
		{
			for(int j=0;j<i - 1;j+=1)
			{
				if(finalscore[j]>finalscore[j+1])
				{
					int tmpscore=finalscore[j],tmpnum=number[j];
					finalscore[j]=finalscore[j+1];
					finalscore[j+1]=tmpscore;
					number[j]=number[j+1];
					number[j+1]=tmpnum;
				}
			}
		}
		frame.remove(canvas);
        frame.remove(lifebar);
        for(int i=0;i<Maxplayerno;i+=1)
        {
           frame.remove(lbl[i]);
        }
        frame.repaint();
		JLabel[] finalscorelabel=new JLabel[Maxplayerno];
		for(int i=0;i<Maxplayerno;i+=1)
		{
			String tmpstr = "Player " + i + " score :  " + finalscore[i];
            finalscorelabel[i] = new JLabel();
			finalscorelabel[i].setText(tmpstr);
			finalscorelabel[i].setBounds(100, 100 + i * 150, 150, 300);
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
