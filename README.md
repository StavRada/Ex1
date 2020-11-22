Ex1:
In this project we dealt with the development of data structures of weighted graph, after that we implemented a number of algorithms on the graph such as: connected, shortest path distance and more…
About Graph:
 Graph is a collection of points and lines that connect pairs of points.
 The points are called Vertices and the lines called edges.
The first class is wGraph_Ds:
In this class there is edges size, mode counter (mc) and graph, I used linked hash map
 and the functions are:
Get node – get the key of this node from linked hash map.
Has edge – check If there is edge between two nodes.
Add node – check if there is this node, and adds the node with this key we get to the graph.
Connect – The function checks first if there is edge between the two nodes and if not 
	 we connects between them and adds the weight we get.
Remove node – remove the node that receive and update the neighbors.'
Equals- The function compares the size of the graph to another size in the graph 
	 and also the size of the ribs to the size of the ribs of another graph.
And more function that receive node size, edge size.
In this class we have sub class node_info:
 and the functions are: key, tag ,info, edges, weight and I used linked hash map.
getInfo- The function returns the information of the node.
setITag- we set the tag of the node for example: return tag infinity for algorithms Dijkstra.
getN- we gets the neighbors with edges.
getW- we gets the weight of the edge or node.

The last class is wGraph Algo (algorithm) :
In this class there are algorithms that we used to graph:
Is connected – I used algorithm to check if all nodes in graph are connected for that I used Bfs (breadth first search) algorithm. 
Bfs is algorithm that used to pass graph nodes, usually while searching for a node that maintains a particular feature.
Any node in the graph is set to be the initial node v, 
and the algorithm passes through all the nodes at one edge distance from v, 
then on all nodes at a distance of 2 edges from v and so on.
Shortest path – I used algorithm to check the shortest path between 2 nodes in graph -dijkstra is algorithm that we maintain two sets, 
one set contains vertices included in shortest path tree, other set includes vertices not yet included in shortest path tree. 
At every step of the algorithm, we find a vertex which is in the other set (set of not yet included) and has a minimum distance from the source.
We have more functions like:
Init- The function Initialize the graph.
Save- The functions save the graph to file.
Load – The function load the graph to file.



 
 

