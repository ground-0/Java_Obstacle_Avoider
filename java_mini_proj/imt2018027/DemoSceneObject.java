package imt2018027;
import java.util.*;

import animation.BBox;
import animation.Point;
import animation.SceneObject;
import animation.Scene;

public class DemoSceneObject extends SceneObject
{

	public DemoSceneObject()
	{
		cPos = new Point(0, 0);
		destP = new Point(0, 0);
		obj_count += 1;
		id = obj_count;
	}
	@Override
	public Point getPosition()
	{
		return cPos;
	}
	@Override
	public void setPosition(int x, int y)
	{
		cPos.setPos(x,y);
	}
	public void setDestPosition(int x, int y)
	{
		destP.setPos(x,y);
	}
	@Override
	protected ArrayList<Point> getOutline() 
	{
		//Rectangle outline
		ArrayList<Point> boxOut = new ArrayList<Point>();
		int cx = cPos.getX();
		int cy = cPos.getY();

		boxOut.add(new Point(cx-5, cy+5));
		boxOut.add(new Point(cx+5, cy+5));
		boxOut.add(new Point(cx+5, cy-5));
		boxOut.add(new Point(cx-5, cy-5));
		return boxOut;
	}

	@Override
	public BBox getBBox()
	{
		BBox bbox = new CBox(new Point(cPos.getX()-5, cPos.getY()-5), new Point(cPos.getX()+5, cPos.getY()+5));
		return bbox;
	}
	@Override
	public String getObjName()
	{
		return "Obj " + id;
	}
	private boolean actorAvoid(ArrayList<SceneObject> actors, Point direction)
	{
		setPosition(direction.getX(), direction.getY());
		for(SceneObject a: actors)
		{
			if(a == this)
			{
				continue;
			}
			if(a.getBBox().intersects(this.getBBox()))
			{
				return false;
			}
		}
		return true;
	}
	private void avoidActors(Point in, Point s, Point l, Point U, Point r)
	{
		ArrayList<SceneObject> actors = Scene.getScene().getActors();
		if(actorAvoid(actors, s))
		{
			return;
		}
		Random rand = new Random();
		ArrayList<Point> dirs = new ArrayList<Point>();
		dirs.add(r);
		dirs.add(U);
		dirs.add(l);
		while(dirs.size() != 0)
		{
			int i = rand.nextInt(dirs.size());
			if(actorAvoid(actors, dirs.get(i)))
			{
				setPosition(dirs.get(i).getX(), dirs.get(i).getY());
				return;
			}
			else
			{
				dirs.remove(i);
			}
		}
		if(dirs.size() == 0)
		{
			setPosition(in.getX(), in.getY());
		}
	}
	private boolean obstacleAvoid(ArrayList<SceneObject> obstacles, Point direction)
	{
		setPosition(direction.getX(), direction.getY());
		boolean flag = true;
		for(SceneObject a: obstacles)
		{
			if(a.getBBox().intersects(this.getBBox()))
			{
				flag = false;
				return flag;
			}
		}
		return flag;
	}

	private void avoidObstacles(Point in, Point s, Point l, Point U, Point r)
	{
		ArrayList<SceneObject> obstacles = Scene.getScene().getObstacles();
		if(obstacleAvoid(obstacles, s))
		{
			return;
		}
		Random rand = new Random();
		ArrayList<Point> dirs = new ArrayList<Point>();
		dirs.add(l);
		dirs.add(U);
		dirs.add(r);
		while(dirs.size() != 0)
		{
			int i = rand.nextInt(dirs.size());
			if(obstacleAvoid(obstacles, dirs.get(i)))
			{
				setPosition(dirs.get(i).getX(), dirs.get(i).getY());
				return;
			}
			else
			{
				dirs.remove(i);
			}
		}
		if(dirs.size() == 0)
		{
			setPosition(in.getX(), in.getY());
		}
	}

	@Override
	protected void updatePos(int deltaT)
	{
		Scene s = Scene.getScene();
		int dirX = 1;
		int dirY = 1;
		int disX =  destP.getX()-cPos.getX();
		int disY =  destP.getY()-cPos.getY();
		double distance = Math.sqrt(disX*disX + disY*disY);
		double cos = (disX/distance);
		double sin = (disY/distance);
		int xvel = (int)(vel * deltaT/1000.0 * cos);
		int yvel = (int)(vel * deltaT/1000.0 * sin);
		Point cpos = new Point(cPos.getX() + dirX * xvel, cPos.getY() + dirY * yvel);
		Point lt =  new Point(cPos.getX() + (-dirY) * yvel, cPos.getY() + (dirX) * xvel);
		Point ut =  new Point(cPos.getX() + (-dirX) * xvel, cPos.getY() + (-dirY) * yvel);
		Point rt =  new Point(cPos.getX() + dirY * yvel, cPos.getY() + (-dirX) * xvel);
		Point inital = new Point(cPos.getX(), cPos.getY());
		avoidActors(cPos, cpos, lt, ut, rt);
		avoidObstacles(cPos, cpos, lt, ut, rt);
	}

	private Point cPos;
	private Point destP;
	private static int obj_count = 0;
	private int id;
	private final int vel = 15;
}
