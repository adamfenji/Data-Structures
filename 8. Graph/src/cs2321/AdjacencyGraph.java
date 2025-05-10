package cs2321;

import net.datastructures.*;

/*
 * Implement Graph interface. A graph can be declared as either directed or undirected.
 * In the case of an undirected graph, methods outgoingEdges and incomingEdges return the same collection,
 * and outDegree and inDegree return the same value.
 * 
 * @author Adam Fenjiro
 * CS2321
 * 4/11/2023
 */

public class AdjacencyGraph<V, E> implements Graph<V, E> {
	
	private boolean isDirected;
	private DoublyLinkedList<Vertex<V>> vertices = new DoublyLinkedList<>();
	private DoublyLinkedList<Edge<E>> edges = new DoublyLinkedList<>();

	private static class myVertex<V,E> implements Vertex<V> {

		private V element;
	    private Position<Vertex<V>> position;
	    public Map<Vertex<V> ,Edge<E>> outgoing, incoming;
	    
	    //Constructor to store the given elements 
	    public myVertex(V elem, boolean graphIsDirected) {
	    	element = elem;
	    	outgoing = new HashMap<>();
	    	if(graphIsDirected)
	    		incoming = new HashMap<>();
	    	else
	    		incoming = outgoing;
	    }
	    
	    //Getters and Setters
	    @Override
		public V getElement() {
			return element;
		}
		public void setPosition(Position<Vertex<V>> p) {
			position = p;
		}
		public Position<Vertex<V>> getPosition(){
			return position;
		}
		public Map<Vertex<V>, Edge<E>> getOutgoing(){
			return outgoing;
		}
		public Map<Vertex<V>, Edge<E>> getIncoming(){
			return incoming;
		}
	}
	//--end of vertex class--
	
	private static class myEdge<V,E> implements Edge<E> {

		private E element;
		private Position<Edge<E>> position;
		private Vertex<V>[] endpoints;
		
		//Constructor:
		public myEdge(Vertex<V> u, Vertex<V> v, E elem) {
			element = elem;
			endpoints = (Vertex<V>[]) new Vertex[] {u,v};
		}
		
		//Getters and Setters
		@Override
		public E getElement() {
			return element;
		}
		public Vertex<V>[] getEndpoints(){
			return endpoints;
		}
		public void setPosition(Position<Edge<E>> p) {
			position = p;
		}
		public Position<Edge<E>> getPosition(){
			return position;
		}
	}
	//--end of edge class--
	
	//This constructs an empty graph either directed or indirected 
	public AdjacencyGraph(boolean directed) {
		isDirected = directed; 
	}

	//For indirected graph
	public AdjacencyGraph() {
		isDirected = false;
	}


	/*Returns an iteration of all the edges of the graph*/
	/*TCJ 
	 * it will go through every single edge in the DLL, and it will run constant time */
	@TimeComplexity("O(m)")
	public Iterable<Edge<E>> edges() {
		return edges;
	}

	/* Returns an array containing the two end point vertices of edge e. If the graph 
	 * is directed, the first vertex is the origin and the second the destination */
	/*TCJ
	 * This will create a variable Edge and return the end points, which is a O(1) operation*/
	@TimeComplexity("O(n)")
	public Vertex[] endVertices(Edge<E> e) throws IllegalArgumentException {
		myEdge<V,E> edge = (myEdge<V,E>)e;
		return edge.getEndpoints();
	}


	/* TCJ  
	* This will check if the connection between u and v exists, it will then create a new edge object, and it will add the edge at the end of the DLL
	* then we will store the vertex and add it to the hashmap to make the connection
	* It is expected to run O(1) because we are only adding values at the end of the DLL and hashmap*/

	/* Creates and returns a new edge from vertex u to vertex v*/
	@TimeComplexityExpected("O(1)")
	public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E o) throws IllegalArgumentException {
		if(getEdge(u,v) == null) { 			
			myEdge<V,E> e = new myEdge<>(u,v,o);
			e.setPosition(edges.addLast(e));
			myVertex<V,E> origin = (myVertex<V,E>)u;
			myVertex<V,E> destination = (myVertex<V,E>)v;
			origin.getOutgoing().put(v, e);
			destination.getIncoming().put(u, e);
			return e;
		} else
			throw new IllegalArgumentException("Edge from u to v exists");
	}

	/*This creates and returns a new Vertex storing element*/
	/*TCJ 
	 * It will create a new vertex that is a O(1) operation, will then set the position at the end of the DLL that is O(1)*/
	@TimeComplexity("O(1)")
	public Vertex<V> insertVertex(V o) {
		myVertex<V,E> v = new myVertex<>(o, isDirected); //create a new Object
	    v.setPosition(vertices.addLast(v)); //add it to the list of the vertex and give the position 
		return v;
	}

	/* Return the number of edges of the graph */
	/*TCJ 
	 * This runs at O(1) because it only returns the size*/
	@TimeComplexity("O(1)")
	public int numEdges() {
		return edges.size();
	}

	/* This returns the number of vertices of the graph*/
	public int numVertices() {
		return vertices.size();
	}

	//For edge e incident to vertex v, it returns the other vertex of the edge; 
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e)throws IllegalArgumentException {
		myEdge<V,E> edge = (myEdge<V,E>)e;
		Vertex<V>[] endpoints = edge.getEndpoints();
		if(endpoints[0] == v)
			return endpoints[1];
		else if (endpoints[1] == v)
			return endpoints[0];
		else
			throw new IllegalArgumentException("v is not incident to this edge");
	}

	/* This removes edge e from the graph*/
	/* TCJ 
	 * it will first store the edge that want to be removed in a variable object , then will get the connections of the e edge, then will get the vertex connections 
	 * Then remove the incoming and outgoing connection between the object vertex and edge ex.remove(V --> {u,edge}) -- V {u,edge}, and finally remove the edge, it is expected to run O(1)*/
	@TimeComplexityExpected("O(1)")
	public void removeEdge(Edge<E> e) throws IllegalArgumentException {
		myEdge<V,E> edge = (myEdge<V,E>)e;
		Vertex<V>[]  temp = edge.getEndpoints();
		myVertex<V,E> from = (myVertex<V,E>)temp[0]; 
		myVertex<V,E> to = (myVertex<V,E>)temp[1];	
		from.outgoing.remove(to);
		to.incoming.remove(from);
		edges.remove(edge.getPosition());		
		
	}

	/*This Removes vertex v and all its incidents edges from the graph*/
	 /*TCJ
	 * It will store the vertex in a variable, then will remove all edges that connected to that vertex,to finally remove the vertex, this is expected to run O(dv) dependeing on the degree of the vertex */
	//for d: the degree of the vertex 
	@TimeComplexityExpected("O(d)")
	public void removeVertex(Vertex<V> v) throws IllegalArgumentException {
			myVertex<V,E> vert = (myVertex<V,E>) v;
			for(Edge<E> e : vert.getOutgoing().values()) 
				removeEdge(e);
			for(Edge<E> e : vert.getIncoming().values()) {
				removeEdge(e);
		}
			vertices.remove(vert.getPosition());
	}

	/* 
     * This method replaces the element in edge object, and returns the old element
     */
	public E replace(Edge<E> e, E o) throws IllegalArgumentException {
		myEdge<V,E> temp =  (myEdge<V, E>) e;
		E returnval = temp.getElement();
		temp.element = o;
		return returnval;
	}

	/* This method replaces the element in vertex object, and return the old element*/
	public V replace(Vertex<V> v, V o) throws IllegalArgumentException {
		myVertex<V,E> temp = (myVertex<V, E>) v;
		V returnval = temp.getElement();
		temp.element = o;
		return returnval;
	}

	/* This returns an iteration of all the vertices of the graph*/
	/*TCJ 
	 * will go through every single vertex in the DLL, runs as an O(n) operation*/
	@TimeComplexity("O(n)")
	public Iterable<Vertex<V>> vertices() {
		return vertices;
	}


	@Override
	//This method returns the number of pointing edges coming from vertex
	public int outDegree(Vertex<V> v) throws IllegalArgumentException {
		myVertex<V,E> vert = (myVertex<V, E>) v;
		return vert.getOutgoing().size();
	}

	@Override
	//This method returns the number of incoming edges.
	public int inDegree(Vertex<V> v) throws IllegalArgumentException {
		myVertex<E,V> vert = (myVertex<E,V>)v; 
		return vert.getIncoming().size();
	}

	@Override
	//This will return an iteration of all outgoing edges from vertex v
	/*TCJ 
	 * It is going to run O(d) since it will depend on the degree of the vertex, the more edges connected to it is has, the higher the time it uses*/
	@TimeComplexity("O(d)")
	public Iterable<Edge<E>> outgoingEdges(Vertex<V> v)throws IllegalArgumentException {
		myVertex<V,E> vert = (myVertex<V,E>)v;
		return vert.getOutgoing().values();
	}

	@Override
	//Will returns an iteration of incoming edges to vertex v
	public Iterable<Edge<E>> incomingEdges(Vertex<V> v)throws IllegalArgumentException {
		myVertex<V,E> vert = (myVertex<V,E>)v;
		return vert.getIncoming().values();
	}

	@Override
	//This method returns the edge from vertex u to vertex v if one exists, if else it returns null
	//Undirected graph, there is no difference between (u,v) and (v,u)
	/* TCJ 
	 * It begins by creating a variable with u values, then it checks for the connections in the hashMap
	 * from the vertex u to v and that is O(1) operation, this is expected to run O(1)*/
	
	@TimeComplexityExpected("O(1)")
	public Edge<E> getEdge(Vertex<V> u, Vertex<V> v)throws IllegalArgumentException {
		myVertex<V,E> origin = (myVertex<V,E>)u;
		return origin.getOutgoing().get(v);
	}
	
	//toString() method:
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    for (Vertex<V> v : vertices) {
	      sb.append("Vertex " + v.getElement() + "\n");
	      if (isDirected)
	        sb.append(" [outgoing]");
	      sb.append(" " + outDegree(v) + " adjacencies:");
	      for (Edge<E> e: outgoingEdges(v))
	        sb.append(String.format(" (%s, %s)", opposite(v,e).getElement(), e.getElement()));
	      sb.append("\n");
	      if (isDirected) {
	        sb.append(" [incoming]");
	        sb.append(" " + inDegree(v) + " adjacencies:");
	        for (Edge<E> e: incomingEdges(v))
	          sb.append(String.format(" (%s, %s)", opposite(v,e).getElement(), e.getElement()));
	        sb.append("\n");
	      }
	    }
	    return sb.toString();
	}
	
}
