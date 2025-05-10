package cs2321;

import cs2321.AdjacencyGraph.myVertex;
import net.datastructures.*;

/**
 * @author Adam Fenjiro
 * CS2321
 * 04/16/2023
 * 
 * Reference textbook R14.16 P14.81 
 * In this file, you will traverse the city map using DFS, BFS and Dijkstra's algorithm. 
 *
 */



public class Travel {
	AdjacencyGraph<String,Integer> graph = new AdjacencyGraph<>(); 
	HashMap<String,Vertex<String>> map = new HashMap<>();
	
	/**
	 * @param routes: Array of routes between cities. 
	 *                routes[i][0] and routes[i][1] represent the city names on both ends of the route. 
	 *                routes[i][2] represents the cost in string type. 
	 *            
	 *                  routes = {  {"A","B","8"},
	 *							    {"A","D","1"},
	 *							    {"B","C","11"},
	 * 							    {"C","D","1"}
	 *                           };
	 *                Hint: In Java, use Integer.valueOf to convert string to integer. 
	 *                
	 *                
	 */
	public Travel(String [][] routes) {
		 
		for(int i = 0; i < routes.length; i++) { 
		Vertex<String> v1 = map.get(routes[i][0]); 
		Vertex<String> v2 = map.get(routes[i][1]);
		if(v1 == null) {
			v1 = graph.insertVertex(routes[i][0]);
			map.put(routes[i][0], v1);
			}
		if(v2 == null) {
			v2 = graph.insertVertex(routes[i][1]);		
			map.put(routes[i][1], v2);
		} 
		graph.insertEdge(v1, v2, Integer.valueOf(routes[i][2]));
		}
	}
	
	/**
	 * @param departure: the departure city name 
	 * @param destination: the destination city name
	 * @return Return an array of cities in the visiting order from the Depth First Search algorithm.    
	 * 
	 * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the city names stored in
	 *                 the opposite vertices. 
	 */

	public String[] DFS(String departure) {
	    Vertex<String> start = map.get(departure);
	    ArrayList<String> visitedOrder = new ArrayList<>();
	    HashMap<Vertex<String>, Boolean> discovered = new HashMap<>();
	    DFSRoute(start, discovered, visitedOrder);
	    String[] visitedOrderArr = new String[visitedOrder.size()];
	    for (int i = 0; i < visitedOrder.size(); i++) {
	        visitedOrderArr[i] = visitedOrder.get(i);
	    }
	    return visitedOrderArr;
	}

	private void DFSRoute(Vertex<String> v, HashMap<Vertex<String>, Boolean> discovered, ArrayList<String> visitedOrder) {
	    discovered.put(v, true);
	    visitedOrder.addLast(v.getElement());
	    ArrayList<Vertex<String>> adjacentVertices = new ArrayList<>();
	    for (Edge<Integer> e : graph.outgoingEdges(v)) {
	        Vertex<String> opposite = graph.opposite(v, e);
	        adjacentVertices.addLast(opposite);
	    }
	    sortVertices(adjacentVertices);
	    for (Vertex<String> adjacentVertex : adjacentVertices) {
	        if (discovered.get(adjacentVertex) == null) {
	            DFSRoute(adjacentVertex, discovered, visitedOrder);
	        }
	    }
	}

	private void sortVertices(ArrayList<Vertex<String>> vertices) {
	    int n = vertices.size();
	    for (int i = 0; i < n-1; i++) {
	        int minIndex = i;
	        for (int j = i+1; j < n; j++) {
	            Vertex<String> v1 = vertices.get(j);
	            Vertex<String> v2 = vertices.get(minIndex);
	            if (v1.getElement().compareTo(v2.getElement()) < 0) {
	                minIndex = j;
	            }
	        }
	        Vertex<String> temp = vertices.get(i);
	        vertices.set(i, vertices.get(minIndex));
	        vertices.set(minIndex, temp);
	    }
	}
	

	
	/**
	 * @param departure: the departure city name 
	 * @return Return an array of cities in the visiting order from the Depth First Search algorithm. 
	 * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the city names stored in
	 *                 the opposite vertices. 
	 */
	
	public String[] BFS(String departure) {
	    Vertex<String> start = map.get(departure);
	    DoublyLinkedList<Vertex<String>> queue = new DoublyLinkedList<>();
	    HashMap<Vertex<String>, Boolean> discovered = new HashMap<>();
	    ArrayList<String> visited = new ArrayList<>();
	    
	    discovered.put(start, true);
	    queue.addLast(start);
	    
	    while(!queue.isEmpty()) {
	        Vertex<String> dequeue = queue.removeFirst();
	        visited.addLast(dequeue.getElement());
	        
	        for(Edge<Integer> e : sortedOutgoingEdges(dequeue)) {
	            Vertex<String> opposite = graph.opposite(dequeue, e);
	            if(discovered.get(opposite) == null) {
	                discovered.put(opposite, true);
	                queue.addLast(opposite);
	            }
	        }
	    }
	    
	    int size = visited.size();
	    String[] result = new String[size];
	    int index = 0;
	    for (String city : visited) {
	        result[index] = city;
	        index++;
	    }
	    return result;
	}
	
	/**
	 * @param departure: the departure city name 
	 *  
	 * @return return a double array of String[numberOfCities][2] following the order 
	 *        when shortest distance is found to a city 
	 *          [i][0] is the city name
	 *          [i][1] is the shortest distance from departure city 
	 *        
	 *        When multiple cities have the same distance from the departure, 
	 *        you may list them in any order. They will all be accepted by the autograder. 
	 *        See the test case testDijkstra2() for how it will be tested. 
	 *        	
	 */

	public String[][] Dijkstra(String departure) {
	    Vertex<String> start = map.get(departure);
	    HashMap<Vertex<String>, Double> distanceMap = new HashMap<>();
	    HeapAPQ<Double, Vertex<String>> PQ = new HeapAPQ<>();
	    HashMap<Vertex<String>, Entry<Double, Vertex<String>>> PQEntryMap = new HashMap<>();

	    for (Vertex<String> vertex : graph.vertices()) {
	        double distance = Double.POSITIVE_INFINITY;
	        if (vertex == start) {
	            distance = 0;
	        }
	        distanceMap.put(vertex, distance);
	        Entry<Double, Vertex<String>> e = PQ.insert(distance, vertex);
	        PQEntryMap.put(vertex, e);
	    }

	    while (!PQ.isEmpty()) {
	        Entry<Double, Vertex<String>> e = PQ.removeMin();
	        double d = e.getKey();
	        Vertex<String> w = e.getValue();

	        for (Edge<Integer> edge : sortedOutgoingEdges(w)) {
	            Vertex<String> opposite = graph.opposite(w, edge);
	            double oppositeDistance = d + edge.getElement();

	            if (oppositeDistance < distanceMap.get(opposite)) {
	                distanceMap.put(opposite, oppositeDistance);
	                PQ.replaceKey(PQEntryMap.get(opposite), oppositeDistance);
	            }
	        }
	    }

	    String[][] result = new String[graph.numVertices()][2];
	    int i = 0;

	    for (Vertex<String> vertex : graph.vertices()) {
	        result[i][0] = vertex.getElement();
	        double dist = distanceMap.get(vertex);
	        result[i][1] = dist == Double.POSITIVE_INFINITY ? "INF" : String.valueOf((int)dist);
	        i++;
	    }
	    for (int j = 0; j < result.length; j++) {
	        int minIndex = j;
	        for (int k = j + 1; k < result.length; k++) {
	            Vertex<String> vertexK = null;
	            Vertex<String> vertexMinIndex = null;

	            for (Vertex<String> v : distanceMap.keySet()) {
	                if (v.getElement().equals(result[k][0])) {
	                    vertexK = v;
	                }
	                if (v.getElement().equals(result[minIndex][0])) {
	                    vertexMinIndex = v;
	                }
	            }

	            int a = distanceMap.get(vertexK) != null ? (int) Math.round(distanceMap.get(vertexK)) : Integer.MAX_VALUE;
	            int b = distanceMap.get(vertexMinIndex) != null ? (int) Math.round(distanceMap.get(vertexMinIndex)) : Integer.MAX_VALUE;

	            if (a < b) {
	                minIndex = k;
	            }
	        }
	        String[] temp = result[j];
	        result[j] = result[minIndex];
	        result[minIndex] = temp;
	    }

	    return result;
	}
	


	
	/*
	 * @IMPORTANT_NOTE: The outgoing edges should be traversed by the order of the city names stored in
	 *                 the opposite vertices. For example, if V has 3 outgoing edges as in the picture below,
	 *                           V
	 *                        /  |  \
	 *                       /   |    \
	 *                      B    A     F  
	 *              your algorithm below should visit the outgoing edges of V in the order of A,B,F.
	 *              This means you will need to create a helper function to sort the outgoing edges by 
	 *              the opposite city names.
	 *              	             
	 *              See the method sortedOutgoingEdges below. 
	 *
	 * I strongly recommend you to implement this method to return sorted outgoing edges for vertex V
	 * 
	 */
	
	
	/**
	* @param v: vertex v
	* @return outgoing edges of V sorted by V's opposite city's names. 
	* 
	* You may use any sorting algorithms, such as insert sort, selection sort, etc.
	* Or you may simply uncomment the following code and change the graph variable name g to match your definition
	**/
	
	public Iterable<Edge<Integer>> sortedOutgoingEdges(Vertex<String> v)  {
	
		ArrayList<Edge<Integer>>  A = new ArrayList<Edge<Integer>>();
		HeapAPQ<String, Edge<Integer>> pq = new HeapAPQ<String, Edge<Integer>> ();
		
		for (Edge<Integer> e: graph.outgoingEdges(v)) {
			String city = graph.opposite(v, e).getElement();
			pq.insert(city,e);
		}
		
		while (pq.size()!=0) {
			A.addLast(pq.removeMin().getValue());
		}
		
		return A;
		
	}
	
	
		

}
