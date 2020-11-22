package ex1.src;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import ex1.src.WGraph_DS.NodeInfo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
public class WGraph_Algo implements weighted_graph_algorithms,Serializable {

	private weighted_graph myG;

	/**
	 * constructor
	 */
	public WGraph_Algo() {
		myG=new WGraph_DS();
	}

	/**
	 * The function Initialize the graph
	 */
	public void init(weighted_graph g) {
		myG=g;

	}

	/**
	 * The function return this graph
	 */
	public weighted_graph getGraph() {
		return myG;
	}

	/**
	 * The function return copy of the graph
	 */
	public weighted_graph copy() {
		weighted_graph Copy=new WGraph_DS(myG);
		return Copy;
	}

	/**
	 * Algorithm to check if all nodes in graph are connected using my implement of BFS algorithm.
	 */
	public boolean isConnected() {
		if(myG.nodeSize()<2)
			return true;

		Queue<Integer> q=new LinkedList<Integer>();
		boolean[] visit=new boolean[1000000];

		q.add(myG.getV().iterator().next().getKey());
		while(!q.isEmpty()) {
			int t=q.poll();
			if(!visit[t]) {
				visit[t]=true;
				Iterator<node_info> it=myG.getV(t).iterator();
				while(it.hasNext()) {
					int temp=it.next().getKey();
					if(!visit[temp])
						q.add(temp);
				}
			}
		}
		Iterator<node_info> iter=myG.getV().iterator();
		while(iter.hasNext()) {
			if(!visit[iter.next().getKey()])
				return false;
		}
		return true;
	}

	/**
	 * Algorithm to check the shortest path between 2 nodes in graph, src and dest.
	 *  Using dijkstra algorithm.
	 *  my implementation requires PriorityQueue to access in distance order.
	 *  This function returns The function returns how much we went in the path
	 */
	 
	public double shortestPathDist(int src, int dest) {
		if(this.myG.getNode(src) == null||this.myG.getNode(dest)== null) return -1;
		toZeroPoint();

		PriorityQueue<node_info> minHeap = new PriorityQueue<node_info>(new Comparator<node_info>() {
			@Override
			public int compare(node_info o1, node_info o2) {
				return - Double.compare(o2.getTag(),o1.getTag());
			}
		});

		boolean[] visit=new boolean[1000000];
		node_info src_n=myG.getNode(src);
		src_n.setTag(0);
		minHeap.add(src_n);

		while(!minHeap.isEmpty()) {
			node_info u=minHeap.poll();
			double lastDist=u.getTag();
			for(node_info i : ((NodeInfo)u).getN().values()) {
				node_info v=i;
				if(visit[v.getKey()]==false) {
					double test= ((NodeInfo)v).getW().get(u.getKey());
					double dist=u.getTag() + ((NodeInfo)v).getW().get(u.getKey());
					if(dist<v.getTag()) {
						v.setTag(dist);
						minHeap.remove(v);
						minHeap.add(v);
					}
				}
			}
			visit[u.getKey()]=true;
		}
		return this.myG.getNode(dest).getTag();
	}
	
	/**
	 * This function set the tag to infinity 
	 */
	private void toZeroPoint() {
		Iterator<node_info> it=myG.getV().iterator();
		while(it.hasNext()) {
			it.next().setTag(Double.POSITIVE_INFINITY);
		}
	}
	
	/**
	 * This function returns list of nodes we went
	 * returns the path
	 */
	public List<node_info> shortestPath(int src, int dest) {
		toZeroPoint();
		LinkedHashMap<node_info,node_info> p=new LinkedHashMap<>();
		PriorityQueue<node_info> minHeap = new PriorityQueue<node_info>(new Comparator<node_info>() {
			@Override
			public int compare(node_info o1, node_info o2) {
				return - Double.compare(o2.getTag(),o1.getTag());
			}
		});

		boolean[] visit=new boolean[1000000];
		node_info src_n=myG.getNode(src);
		src_n.setTag(0);
		minHeap.add(src_n);

		while(!minHeap.isEmpty()) {
			node_info u=minHeap.poll();
			double lastDist=u.getTag();
			for(node_info i : ((NodeInfo)u).getN().values()) {
				node_info v=i;
				if(visit[v.getKey()]==false) {
					double test= ((NodeInfo)v).getW().get(u.getKey());
					double dist=u.getTag() + ((NodeInfo)v).getW().get(u.getKey());
					if(dist<v.getTag()) {
						v.setTag(dist);
						p.put(v, u);
						minHeap.remove(v);
						minHeap.add(v);
					}
				}
			}
			visit[u.getKey()]=true;
		}
		return getpath(p,dest);
	}

	
	private List<node_info> getpath(LinkedHashMap<node_info,node_info> p,int dest){
		List<node_info> list =new LinkedList<>();
		
		node_info d=this.myG.getNode(dest);
		while(d!=null){
			list.add(d);
			d = p.get(d);
		}
		Collections.reverse(list);
		return list;
	}

	/**
	 * This function save graph to file
	 */
	public boolean save(String file) {
		try
        {    
            FileOutputStream file_ = new FileOutputStream(file); 
            ObjectOutputStream out = new ObjectOutputStream(file_); 
            out.writeObject(this.myG); 
              
            out.close(); 
            file_.close(); 
              
            return true;
        }   
        catch(IOException ex) 
        { 
        	return false; 
        } 
	}

	/**
	 * This function load graph to file
	 */
	public boolean load(String file) {
		try {    
			FileInputStream file_ = new FileInputStream(file); 
			ObjectInputStream in = new ObjectInputStream(file_);  
			this.myG = (weighted_graph) in.readObject();
			in.close(); 
			file_.close();   

			return true;
		} 

		catch(IOException ex) 
		{ 
			return false;
		} 

		catch(ClassNotFoundException ex) 
		{ 
			return false;
		} 
		
	}

}
