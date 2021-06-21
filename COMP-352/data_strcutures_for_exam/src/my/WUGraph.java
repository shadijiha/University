/**
 * Weighted Undirected graph
 */

package my;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class WUGraph {
	private Set<Vertex> vertices; //collection of all verices

	public WUGraph() {
		vertices = new HashSet<>();
	}

	public List<Vertex> getVertices() {
		return new ArrayList<>(vertices);
	}

	public boolean addVertex(Vertex vertex) {
		return vertices.add(vertex);
	}

	public void DFS(Vertex v, Consumer<String> func) {
		Map<Vertex, Boolean> visited = new HashMap<>();
		Stack<Vertex> S = new Stack<>();
		S.push(v);

		while (!S.isEmpty()) {
			v = S.pop();
			if (!visited.getOrDefault(v, false)) {
				visited.put(v, true);
				System.out.println(S);
				func.accept(v.getLabel());
				for (Edge edge : v.getEdges()
						.stream()
						.sorted()
						.collect(Collectors.toList())) {
					S.push(edge.getTo());
				}
			}
		}

	}

	public void BFS(Vertex root, Consumer<String> func) {
		Queue<Vertex> Q = new ArrayDeque<>();
		Map<Vertex, Boolean> visited = new HashMap<>();
		visited.put(root, true);
		Q.offer(root);

		while (!Q.isEmpty()) {
			System.out.println(Q);
			Vertex v = Q.poll();
			//func.accept(v.getLabel());

			for (Edge e : v.getEdges().stream().sorted().collect(Collectors.toList())) {
				if (!visited.getOrDefault(e.to, false)) {
					visited.put(e.to, true);
					Q.offer(e.to);
				}
			}
		}
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

		public boolean addUndirectedEdge(Vertex v) {
			return addUndirectedEdge(v, 1);
		}

		public List<Edge> getEdges() {
			return new ArrayList<>(edges);
		}

		public boolean equals(Object o) {
			if (o == this)
				return true;
			else if (o == null || o.getClass() != getClass())
				return false;
			else {
				return getLabel().equals(((Vertex) o).getLabel());
			}
		}

		public String toString() {
			return label;
		}

		public List<Vertex> getAdjVertecies(Collection<Vertex> q) {
			List<Vertex> result = new ArrayList<>();
			for (var e : edges)
				if (q.contains(e.getTo()))
					result.add(e.getTo());
			return result;
		}

		public int getWeightTo(Vertex to) {
			return edges.stream().filter(e -> e.getTo().equals(to)).findFirst().get().weight;
		}

		//todo override hashCode()
	}

	public static class Edge implements Comparable<Edge> {

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

		@Override
		public int compareTo(Edge o) {
			return to.getLabel().compareTo(o.to.getLabel());
		}

		//todo override hashCode()
	}


}
