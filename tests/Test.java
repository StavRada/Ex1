package ex1.tests;

import ex1.src.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

class Test {

	weighted_graph g1=new WGraph_DS();

	@BeforeEach
	void setUp() throws Exception {
		g1=new WGraph_DS();
	
	}



	@org.junit.jupiter.api.Test
	void test1() {
		g1.addNode(0);
		if(g1.nodeSize()!=1) {
			fail("size graph need to be 1");
		}
	}


	@org.junit.jupiter.api.Test
	void test2() {
		for(int i=0; i<100 ; i++) {
			g1.addNode(i);
		}
		if(g1.nodeSize()!=100) {
			fail("size graph need to be 1");
		}
	}

	@org.junit.jupiter.api.Test
	void test3() {
		for(int i=0; i<100 ; i++) {
			g1.addNode(i);
		}
		for(int i=0; i<100 ; i+=2) {
			g1.connect(i, i+1, 50);
		}
		if(g1.edgeSize()!=50) {
			fail("graph need to be 100 nodes and 50 edges");
		}
	}

	@org.junit.jupiter.api.Test
	void test4() {
		weighted_graph_algorithms algo=new WGraph_Algo();
		g1.addNode(0);
		g1.addNode(1);
		g1.addNode(2);

		g1.connect(0, 1, 3);
		g1.connect(1, 2, 2);
		g1.connect(0, 2, 3);
		algo.init(g1);

		if(algo.shortestPathDist(0, 2)!=3) {
			fail("Not yet implemented");
		}
	}
		@org.junit.jupiter.api.Test
		void test5() {
			weighted_graph_algorithms algo=new WGraph_Algo();
			g1.addNode(0);
			g1.addNode(1);
			g1.addNode(2);

			g1.connect(0, 1, 3);
			g1.connect(1, 2, 2);
			g1.connect(0, 2, 7);
			algo.init(g1);
			
			if(algo.shortestPathDist(0, 2)!=5) {
				fail("Not yet implemented");
			}
		}
		@org.junit.jupiter.api.Test
		void test6() {
			weighted_graph_algorithms algo=new WGraph_Algo();
			g1.addNode(0);
			g1.addNode(1);
			g1.addNode(2);

			g1.connect(0, 1, 3);
			algo.init(g1);
			
			if(algo.isConnected()!=false) {
			fail("graph is not connected");
			}
		}
		@org.junit.jupiter.api.Test
		void test7() {
			weighted_graph_algorithms algo=new WGraph_Algo();
			g1.addNode(0);
			g1.addNode(1);
			g1.addNode(2);

			g1.connect(0, 1, 3);
			algo.init(g1);
			if(algo.save("bdika.txt")!=true) {
			fail("Not save file");
			}
		}
		@org.junit.jupiter.api.Test
		void test8() {
			weighted_graph_algorithms algo=new WGraph_Algo();
			g1.addNode(0);
			g1.addNode(1);
			g1.addNode(2);
			g1.addNode(3);

			g1.connect(0, 1, 3);
			g1.connect(1, 2, 2);
			g1.connect(0, 3, 3);
			g1.connect(3, 2, 2);
		
			algo.init(g1);
			if(algo.isConnected()!=true) {
				fail("graph is not connected");
			}
		}
}
