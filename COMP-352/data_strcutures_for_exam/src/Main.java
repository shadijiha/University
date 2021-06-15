import my.*;

/**
 *
 */

public class Main {

	public static void main(String[] args) {
		WUGraph graph = new WUGraph();

		var a = new WUGraph.Vertex("A");
		var b = new WUGraph.Vertex("B");
		var c = new WUGraph.Vertex("C");
		var d = new WUGraph.Vertex("D");
		var e = new WUGraph.Vertex("E");
		var f = new WUGraph.Vertex("F");
		var g = new WUGraph.Vertex("G");
		var h = new WUGraph.Vertex("H");
		var i = new WUGraph.Vertex("I");
		var j = new WUGraph.Vertex("J");

		a.addUndirectedEdge(f, 4);
		a.addUndirectedEdge(h, 2);
		a.addUndirectedEdge(b, 6);
		b.addUndirectedEdge(d, 7);
		b.addUndirectedEdge(c, 4);
		c.addUndirectedEdge(d, 5);
		d.addUndirectedEdge(j, 3);
		d.addUndirectedEdge(e, 9);
		e.addUndirectedEdge(f, 3);
		f.addUndirectedEdge(g, 1);
		g.addUndirectedEdge(h, 2);
		h.addUndirectedEdge(i, 1);
		i.addUndirectedEdge(j, 2);
	}

}
