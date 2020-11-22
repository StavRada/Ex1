package ex1.src;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class WGraph_DS implements weighted_graph ,Serializable{

	private int mc;
	private int edgesize;
	private LinkedHashMap<Integer,node_info> graph;

	public WGraph_DS() {
		this.edgesize=0;
		this.mc=0;
		this.graph = new LinkedHashMap<Integer,node_info>();
	}

	/*
	Deep Copy using Iterator
	 */
	public WGraph_DS(weighted_graph g) {
		this.mc=g.getMC();
		this.edgesize=g.edgeSize();
		Iterator<node_info> it= g.getV().iterator();
		while(it.hasNext()) {
			node_info temp = it.next();
			this.addNode(temp.getKey());
		}
	}

	public class NodeInfo implements node_info,Serializable{

		private int key;
		private String info;
		private double Tag;
		private LinkedHashMap<Integer,node_info> edge;
		private LinkedHashMap<Integer,Double> w ;


		public NodeInfo(int key) {
			this.key=key;
			this.info="";
			this.Tag=0;
			this.w= new LinkedHashMap<Integer,Double>();
			edge= new LinkedHashMap<Integer,node_info>();
		}

		/**
		 * The function returns the key of this node
		 */
		public int getKey() {

			return this.key;
		}

		/**
		 * The function return the information of this node
		 */
		public String getInfo() {

			return this.info;
		}

		/**
		 * The function set the information to s
		 */
		public void setInfo(String s) {
			this.info= s;

		}

		/**
		 * The function returns the tag of this node
		 */
		public double getTag() {

			return Tag;
		}
		/**
		 * this function sets the tag of node to t
		 * for example return tag infinity for algorithms Dijkstra
		 */
		@Override
		public void setTag(double t) {
			this.Tag=t;

		}
		
		/**
		 * The function returns the edge (key and value) of the neighbor
		 */
		public  LinkedHashMap<Integer,node_info> getN(){
			return this.edge;
		}
		
		/**
		 * The function returns the weight (key and value) of this node
		 */
		public LinkedHashMap<Integer,Double> getW(){
			return this.w;
		}
	}


	/**
	 * The function returns from graph the node with this key
	 */
	public node_info getNode(int key) {

		return this.graph.get(key);
	}

	
	/**
	 * The function checks if there edge between the two nodes 
	 */
	public boolean hasEdge(int node1, int node2) {
		return ((NodeInfo) this.getNode(node1)).getN().containsKey(node2);
	}

	/**
	 * The function checks first if there edge between the two nodes 
	 * and returns the edge
	 */
	public double getEdge(int node1, int node2) {
		if(this.hasEdge(node1, node2)) {
		return ((NodeInfo) this.getNode(node1)).getW().get(node2);
		}
		return -1;
	}

	/**
	 * The function adds new node with this key to the graph
	 */
	public void addNode(int key) {
		this.mc++;
		this.graph.put(key, new NodeInfo(key));
	}

	/**
	 * The function checks first if there is edge between the two nodes and if not 
	 * we connects between them and adds the weight we get
	 */
	public void connect(int node1, int node2, double w) {
		if(node1!=node2&&!(this.hasEdge(node1, node2))) {
			this.edgesize++;
			this.mc++;

			((NodeInfo) this.getNode(node1)).getN().put(node2, getNode(node2));
			((NodeInfo) this.getNode(node1)).getW().put(node2,w);

			((NodeInfo) this.getNode(node2)).getN().put(node1,  getNode(node1));
			((NodeInfo) this.getNode(node2)).getW().put(node1, w);

		}

	}

	/**
	 * The function returns collection of vertices (the values) 
	 */
	public Collection<node_info> getV() {
		return this.graph.values();
	}

	/**
	 * The function gets node_id and returns the values
	 */
	public Collection<node_info> getV(int node_id) {
		return ((NodeInfo) this.getNode(node_id)).getN().values();
	}

	/**
	 * The function checks first if there is node with this key we get 
	 * and move on with iterator all of the nodes in the graph until we found the node 
	 * and remove him
	 */
	public node_info removeNode(int key) {

		if(!graph.containsKey(key)) {
			return null;
		}else {
			mc++;
			Iterator<node_info> it=((NodeInfo) graph.get(key)).getN().values().iterator();
			while(it.hasNext()) {

				node_info temp=it.next();
				((NodeInfo) temp).getN().remove(key);
				((NodeInfo) temp).getW().remove(key);

				edgesize--;
				mc++;
			}

			return graph.remove(key);
		}
	}

	/**
	 * The function checks if there is edge between the 2 nodes we get and removes the edge
	 */
	public void removeEdge(int node1, int node2) {
		if(this.hasEdge(node1, node2)) {
			this.edgesize--;
			this.mc++;

			((NodeInfo) this.getNode(node1)).getN().remove(node2);
			((NodeInfo) this.getNode(node1)).getW().remove(node2);

			((NodeInfo) this.getNode(node2)).getN().remove(node1);
			((NodeInfo) this.getNode(node2)).getW().remove(node1);

		}

	}

	/**
	 * The function return the number of nodes in the graph
	 */
	public int nodeSize() {
		return graph.size();
	}

	/**
	 *  The function return the number of edges 
	 */
	public int edgeSize() {

		return this.edgesize;
	}

	/**
	 * The function return the mode count 
	 */
	public int getMC() {

		return this.mc;
	}

	/**
	 * The function compares the size of the graph to another size in the graph 
	 * and also the size of the ribs to the size of the ribs of another graph
	 */
	public boolean equals(Object o) {
		return this.nodeSize()==((weighted_graph)o).nodeSize()&&
				this.edgesize==((weighted_graph)o).edgeSize();
	}

}
