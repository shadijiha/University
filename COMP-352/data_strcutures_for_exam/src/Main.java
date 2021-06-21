import my.*;
import my.WUGraph.*;

/**
 *
 */

public class Main {

	public static void main(String[] args) {

		WUGraph graph = new WUGraph();
		Vertex[] v = new Vertex[10];
		for (int i = 0; i < v.length; i++) {
			v[i] = new Vertex(i + "");
			graph.addVertex(v[i]);
		}

		v[0].addUndirectedEdge(v[6]);
		v[0].addUndirectedEdge(v[9]);
		v[1].addUndirectedEdge(v[2]);
		v[1].addUndirectedEdge(v[7]);
		v[2].addUndirectedEdge(v[7]);
		v[2].addUndirectedEdge(v[8]);
		v[2].addUndirectedEdge(v[4]);
		v[2].addUndirectedEdge(v[3]);
		v[2].addUndirectedEdge(v[5]);
		v[3].addUndirectedEdge(v[5]);
		v[4].addUndirectedEdge(v[5]);
		v[4].addUndirectedEdge(v[6]);
		v[5].addUndirectedEdge(v[6]);
		v[6].addUndirectedEdge(v[8]);
		v[6].addUndirectedEdge(v[9]);
		v[7].addUndirectedEdge(v[8]);
		v[8].addUndirectedEdge(v[9]);

		graph.BFS(v[0], (e) -> {
			System.out.print(e + " ");
		});

	}
}
