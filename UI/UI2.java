package UIpackage;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UI2 
{
	private JFrame frame;
	public void createFrame(int width,int height)
	{
		frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width,height);
		frame.setVisible(true);
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
	}
	public int getCanvasWidth()
	{
		Component[] component=frame.getContentPane().getComponents();
		int index=-1;
		for(int i=0;i<component.length;i+=1)
		{
			if(component.equals(Component.class));
			{
				index=i;
				break;
			}
		}
		assert index>=0:"There is no canvas in frame.";
		return component[index].getWidth();
	}
	public int getCanvasHeight()
	{
		Component[] component=frame.getContentPane().getComponents();
		int index=-1;
		for(int i=0;i<component.length;i+=1)
		{
			if(component.equals(Component.class));
			{
				index=i;
				break;
			}
		}
		assert index>=0:"There is no canvas in frame.";
		return component[index].getHeight();
	}
	public void waitingScreen()
	{
		frame.getContentPane().removeAll();
		frame.getContentPane().add(new JPanel());
		frame.getContentPane().repaint();
		JLabel lbl=new JLabel();
		lbl.setIcon(new ImageIcon("C:\\Users\\Asus-X550J\\Desktop\\等待畫面.jpg"));
		lbl.setBounds(0, 0,500,500);
		frame.getContentPane().add(lbl);
		frame.getContentPane().setLayout(null);
	}
}
