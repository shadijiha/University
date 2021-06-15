/**
 * Weighted Undirected graph
 */

package my;

import java.util.ArrayList;
import java.util.*;

public class WUGraph {
	private Set<Vertex> vertices; //collection of all verices

	public WUGraph() {
		vertices = new HashSet<>();
	}

	List<Vertex> getVertices() {
		return new ArrayList<>(vertices);
	}

	boolean addVertex(Vertex vertex) {
		return vertices.add(vertex);
	}

	public static class Vertex {

		private String label;
		private Set<Edge> edges; //collection of edges to neighbors

		public Vertex(String pageObject) {
			this.label = pageObject;
			edges = new HashSet<>();
		}

		public String getLabel() {
			return label;
		}

		public boolean addEdge(Edge edge) {
			return edges.add(edge);
		}

		public boolean addUndirectedEdge(Vertex v, int weight) {
			boolean v1 = edges.add(new Edge(v, weight));
			boolean v2 = v.edges.add(new Edge(this, weight));
			return v1 && v2;
		}

		public List<Edge> getEdges() {
			return new ArrayList<>(edges);
		}

		//todo override hashCode()
	}

	public static class Edge {

		private Vertex to;
		private int weight;

		public Edge(Vertex to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		public Vertex getTo() {
			return to;
		}

		public int getWeight() {
			return weight;
		}

		//todo override hashCode()
	}
}
