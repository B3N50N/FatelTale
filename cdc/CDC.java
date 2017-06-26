package cdc;
import java.awt.Point;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import adm.ADM;
import entity.Item;
import entity.ItemInfo;
import entity.Player;
import entity.PlayerInfo;
import entity.Monster;
import entity.Projector;
import sdm.SDM;
import tcp.codes;
import logger.Logger;
public class CDC
{
	final static int MaxPlayerno=4;
	private static Point playerinitlocation[];
	private Map<Integer,Player> player;
	private Map<Integer,Item> item;
	private Map<Integer,Monster> monster;
	private Map<Integer,Projector> projector;
	private int itemid=0;
	private int monsterid=0;
	private int projectorid=0;
	public static CDC uniqueinstance;
	private CDC()
	{
		player=new ConcurrentHashMap<>();
		item=new ConcurrentHashMap<>();
		monster=new ConcurrentHashMap<>();
		projector=new ConcurrentHashMap<>();
		playerinitlocation=new Point[MaxPlayerno];
		playerinitlocation[0]=new Point(100,100);
		/*
		playerinitlocation[1]=new Point(SDM.getInstance().getWidth() * ADM.getInstance().getMapWidth() - 100, 100);
		playerinitlocation[2]=new Point(0,SDM.getInstance().getHeight() * ADM.getInstance().getMapHeight() - 100);
		playerinitlocation[3]=new Point(SDM.getInstance().getWidth() * ADM.getInstance().getMapWidth() - 100, 
				                        SDM.getInstance().getHeight() * ADM.getInstance().getMapHeight() );
		*/
		
		addPlayer(0, 0);
	}
	public static synchronized CDC getInstance()
	{
		if(uniqueinstance==null)
		{
			uniqueinstance=new CDC();
		}
		return uniqueinstance;
	}
	public Map<Integer,Player> getPlayer(){return player;}
	public Map<Integer,Item> getItem(){return item;}
	public Map<Integer,Monster> getMonster(){return monster;}
	public Map<Integer,Projector> getProjector(){return projector;}
	public void keyDown(int clientno,int action)
	{
		assert player.get(clientno)!=null:"The clientno is invalid";
		if(action==codes.ATTACK)
			player.get(clientno).playerAttack();
		else
			player.get(clientno).playerMove(action);
	}
	public void keyRelease(int clientno,int action)
	{
		assert player.get(clientno)!=null:"The clientno is invalid";
		if(action==codes.ATTACK)
			player.get(clientno).attackingEnd();
		else
			player.get(clientno).movingEnd();
	}
	public int getMonsterNewId()
	{
		int tmp=monsterid;
		monsterid+=1;
		return tmp;
	}
	public int getItemNewId()
	{
		int tmp=itemid;
		itemid+=1;
		return tmp;
	}
	public int getProjectorId()
	{
		int tmp=projectorid;
		projectorid+=1;
		return tmp;
	}
	public void addPlayer(int clientno,int type)
	{
		assert clientno>=0&&clientno<4:"The clientno is invalid";
		Player p = new Player(clientno, type,playerinitlocation[clientno],PlayerInfo.getInstance().getTypeInfo(type),
				   PlayerInfo.getInstance().getEmitter(type), PlayerInfo.getInstance().getCollider(type) );
		player.put(clientno, p);
		
		p.setPosition(new Point(playerinitlocation[clientno].x, playerinitlocation[clientno].y) );
		Logger.log(p.toString());
	}
	public void addItem(Point point,int type)
	{
		Item tmp=new Item(point,type,ItemInfo.getInstance().getTypeInfo(type));
		item.putIfAbsent(itemid,tmp);
		itemid+=1;
	}
	public Vector<String> getUpdateInfo()
	{
		Vector<String> v=new Vector<String>();
		for(Map.Entry<Integer,Player> entry:player.entrySet())
		{
			String str="";
			str=entry.getValue().toString();
			v.add(str);
		}
		/*
		for(Map.Entry<Integer,Monster> entry:monster.entrySet())
		{
			String str = "Monster ";
			str += String.valueOf( entry.getKey() );
			str += " ";
			str += entry.getValue().toString();
			v.add(str);
		}
		*/
		/*
		for (Map.Entry<Integer, Projector> entry : projector.entrySet() ) {
			String str = "Projector ";
			str += String.valueOf( entry.getKey() );
			str += " ";
			str += entry.getValue().toString();
			v.add(str);
		}
		for(Map.Entry<Integer,Item> entry:item.entrySet())
		{
			String str;
			str=entry.toString();
			v.add(str);
		}
		*/
		return v;
	}
	public static void main(String[] args)
	{
		CDC cdc;
		cdc=CDC.getInstance();
		CDC.getInstance().addPlayer(1,0);
		CDC.getInstance().addPlayer(2,1);
		Logger.log(CDC.getInstance().getPlayer().get(1).toString());
		Logger.log(CDC.getInstance().getPlayer().get(2).toString());
	}
}
