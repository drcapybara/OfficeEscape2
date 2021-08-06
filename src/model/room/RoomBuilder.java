/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Dustin Ray, Raz Consta, Reuben Keller
 */

package model.room;

import model.graph.AdjacencyListGraph;
import model.graph.KruskalMSTFinder;
import model.trivia.TriviaManager;

import java.util.*;

/**
 * Uses a graph representation to generate a mapping of each Room to connected
 * Rooms.
 *
 * @author Reuben Keller
 */
public class RoomBuilder {

    /** The number of rows in the graph representation. */
    public static final int ROWS = 4;

    /** The number of room columns. */
    public static final int COLS = 4;

    private static final int NUM_ROOMS = ROWS * COLS;

    /** The Graph representation of rooms to manage. */
    private final AdjacencyListGraph<Integer> myGraph;

    /** The MST finder that finds the MST of the Graph representation. */
    private KruskalMSTFinder<Integer> mstFinder;

    /** A Random object for generating pseudo-random edge weights. */
    private final Random myRand;

    /** A mapping of each vertex ID to its connected vertex IDs. */
    private Map<Integer, List<Integer>> connectedVertices;

    /** A mapping of each Room to its connected Rooms. */
    private Map<Room, Set<Room>> rooms;

    /** A helper class to get random trivia. */
    private final TriviaManager myTriviaManager;

    /**
     * Constructs a RoomManager for a given graph representation of Rooms.
     */
    public RoomBuilder() {
        myGraph = new AdjacencyListGraph<>();
        myTriviaManager = new TriviaManager();
        myRand = new Random();
        generateGraph();
        generateMST();
        extractRoomsMap();
    }

    /**
     * Generates the Graph for this GraphManager. Each vertex is numbered from
     * left to right, starting with 0, and moving down row by row.
     *
     * e.g., if myRows = 3 and myCols = 3, then the visual representation of
     * the generated graph is as follows:
     *      0---1---2
     *      |   |   |
     *      3---4---5
     *      |   |   |
     *      6---7---8
     */
    private void generateGraph() {
        for (int j = 0; j < NUM_ROOMS; j++) {
            // horizontal edges
            if ((j + 1) % ROWS != 0) {
                double weight = myRand.nextDouble();
                myGraph.addEdge(j, j + 1, weight);
            }
            // vertical edges
            if (j + COLS < NUM_ROOMS) {
                double weight = myRand.nextDouble();
                myGraph.addEdge(j, j + COLS, weight);
            }
        }
    }


    /**
     * Generates the MST of the Graph generated by generateGraph() and returns
     * a mapping of each room to its connected rooms.
     */
    private void generateMST() {
        mstFinder = new KruskalMSTFinder<>(myGraph);
        connectedVertices = mstFinder.getVertexMap();
    }


    /**
     * Builds and returns a mapping of each Room to connected Rooms.
     * Sets valid doors between rooms in the process.
     */
    public void extractRoomsMap() {
        rooms = new HashMap<>();
        for (Integer currID : connectedVertices.keySet()) {
            Room currRoom = new Room(currID);
            Set<Room> s = new HashSet<>();
            if (rooms.containsKey(currRoom)) {
                s = rooms.get(currRoom);
            }
            for (Integer neighborID : connectedVertices.get(currID)) {
                Room neighborRoom = new Room(neighborID);
                if (!s.contains(neighborRoom)) {
                    Door door = new Door(true, false, myTriviaManager.getTrivia());
                    if (currID - ROWS == neighborID) {
                        // neighbor is north of curr (north = A)
                        currRoom.setDoorA(neighborRoom, door);
                        neighborRoom.setDoorB(currRoom, door);
                    } else if (currID + ROWS == neighborID) {
                        // neighbor is south of curr (south = B)
                        currRoom.setDoorB(neighborRoom, door);
                        neighborRoom.setDoorA(currRoom, door);
                    } else if (currID + 1 == neighborID) {
                        // neighbor is east of curr  (east = C)
                        currRoom.setDoorC(neighborRoom, door);
                        neighborRoom.setDoorD(currRoom, door);
                    } else if (currID - 1 == neighborID) {
                        // neighbor is west of curr  (west = D)
                        currRoom.setDoorD(neighborRoom, door);
                        neighborRoom.setDoorC(currRoom, door);
                    }
                    s.add(neighborRoom);
                }
            }
            rooms.put(currRoom, s);
        }
    }

    /**
     * Returns a list of Room objects in increasing order of Room ID.
     *
     * @return A list of Room objects in  increasing order of Room ID.
     */
    public List<Room> roomsList() {
        List<Room> list = new ArrayList<>(rooms.keySet());
        list.sort(Comparator.comparingInt(Room::getRoomID));
        return list;
    }

    /**
     * Returns a mapping of each Room to its connected Rooms.
     *
     * e.g., if the Room 0 with room ID 0 is mapped to Rooms 1 and 3, with room
     * IDs 1 and 3, respectively, then Room 0 is connected to both Rooms 1 and
     * 3.
     *
     * @return A mapping of each Room to its connected Rooms.
     */
    public Map<Room, Set<Room>> roomsMap() {
        return rooms;
    }
}
