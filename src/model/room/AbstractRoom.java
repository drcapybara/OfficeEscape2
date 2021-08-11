/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Dustin Ray, Raz Consta, Reuben Keller
 */

package model.room;

import model.map.GameMap;
import model.map.Terrain;

/**
 * Implements behavior common to all Rooms.
 *
 * @author Reuben Keller
 */
public abstract class AbstractRoom {

    /** Door object containing a given trivia question and link to another room. */
    private Door DoorA;
    /** Door object containing a given trivia question and link to another room. */
    private Door DoorB;
    /** Door object containing a given trivia question and link to another room. */
    private Door DoorC;
    /** Door object containing a given trivia question and link to another room. */
    private Door DoorD;

    /** Room object possibly linked to this room by a given door. */
    private Room RoomA;
    /** Room object possibly linked to this room by a given door. */
    private Room RoomB;
    /** Room object possibly linked to this room by a given door. */
    private Room RoomC;
    /** Room object possibly linked to this room by a given door. */
    private Room RoomD;

    /** The int ID for this room. */
    private final int myID;
    /** The terrain grid for this room. */
    private final Terrain[][] myTerrain;

    private final GameMap gm;


    /**
     * Constructs a Room with the given integer ID.
     * @param theID The integer ID of this Room.
     */
    public AbstractRoom(final int theID) {
        myID = theID;
        GameMap tG = new GameMap(theID);
        myTerrain = tG.getTerrainGrid();
        gm = new GameMap(myID);
    }

    public GameMap getMap() {
        return gm;
    }

    /**
     * Gets the terrain grid for this room.
     * @return 2D character array representing terrain grid for this room.
     */
    public Terrain[][] getTerrain() {
        return myTerrain;
    }

    /**
     * Returns the integer ID of for this Room.
     * @return The integer ID for this Room.
     */
    public int getRoomID() {
        return myID;
    }

    /**
     * Gets the door specified if it exists.
     * @param theDoorName A letter representing the door to return.
     * @return the door specified by theDoorName.
     */
    public Door getDoor(final String theDoorName) {
        return switch (theDoorName) {
            case "A" -> DoorA;
            case "B" -> DoorB;
            case "C" -> DoorC;
            case "D" -> DoorD;
            default -> null;
        };
    }

    /**
     * Gets the room specified if it exists.
     * @param theRoomName A letter representing the room to return.
     * @return the room specified by theRoomName.
     */
    public Room getRoom(final String theRoomName) {
        return switch (theRoomName) {
            case "A" -> RoomA;
            case "B" -> RoomB;
            case "C" -> RoomC;
            case "D" -> RoomD;
            default -> null;
        };
    }

    /**
     * Checks to see if the specified room exists.
     *
     * @param theRoomName the room to check.
     * @return true if room exists, false otherwise.
     */
    public boolean hasRoom(final String theRoomName) {
        return switch (theRoomName) {
            case "A" -> RoomA != null;
            case "B" -> RoomB != null;
            case "C" -> RoomC != null;
            case "D" -> RoomD != null;
            default -> false;
        };
    }


    /**
     * Sets the A Room and Door to the given Room and Door.
     *
     * @param room The A Room of this Room.
     * @param door The A Door separating this Room and the A Room.
     */
    public void setA(final Room room, final Door door) {
        RoomA = room;
        DoorA = door;
    }


    /**
     * Sets the B Room and Door to the given Room and Door.
     *
     * @param room The B Room of this Room.
     * @param door The B Door separating this Room and the B Room.
     */
    public void setB(final Room room, final Door door) {
        RoomB = room;
        DoorB = door;
    }


    /**
     * Sets the C Room and Door to the given Room and Door.
     *
     * @param room The C Room of this Room.
     * @param door The C Door separating this Room and the C Room.
     */
    public void setC(final Room room, final Door door) {
        RoomC = room;
        DoorC = door;
    }


    /**
     * Sets the D Room and Door to the given Room and Door.
     *
     * @param room The D Room of this Room.
     * @param door The D Door separating this Room and the D Room.
     */
    public void setD(final Room room, final Door door) {
        RoomD = room;
        DoorD = door;
    }


    /**
     * Returns the door between this Room and the north Room.
     *
     * @return The Door between this Room and the north Room.
     * @throws NullPointerException if this Room does not have a north Room.
     */
    public Door getDoorA() {
        if (!hasRoomA()) {
            throw new NullPointerException("Room is null (i.e., not connected)");
        }
        return DoorA;
    }


    /**
     * Returns the door between this Room and the south Room.
     *
     * @return The Door between this Room and the south Room.
     * @throws NullPointerException if this Room does not have a south Room.
     */
    public Door getDoorB() {
        if (!hasRoomB()) {
            throw new NullPointerException("Room B is null (i.e., not connected)");
        }
        return DoorB;
    }


    /**
     * Returns the door between this Room and the east Room.
     * @return The Door between this Room and the east Room.
     * @throws NullPointerException if this Room does not have an east Room.
     */
    public Door getDoorC() {
        if (!hasRoomC()) {
            throw new NullPointerException("Room C is null (i.e., not connected)");
        }
        return DoorC;
    }


    /**
     * Returns the door between this Room and the west Room.
     * @return The Door between this Room and the west Room.
     * @throws NullPointerException if this Room does not have a west Room.
     */
    public Door getDoorD() {
        if (!hasRoomD()) {
            throw new NullPointerException("Room D is null (i.e., not connected)");
        }
        return DoorD;
    }


    /**
     * Returns true if this Room is connected to a north Room and false
     * otherwise.
     * @return true if this Room is connected to a north Room and false
     *     otherwise.
     */
    public boolean hasRoomA() {
        return RoomA != null;
    }




    /**
     * Returns true if this Room is connected to a south Room and false
     * otherwise.
     * @return true if this Room is connected to a south Room and false
     *     otherwise.
     */
    public boolean hasRoomB() {
        return RoomB != null;
    }

    /**
     * Returns true if this Room is connected to a east Room and false
     * otherwise.
     * @return true if this Room is connected to a east Room and false
     *     otherwise.
     */
    public boolean hasRoomC() {
        return RoomC != null;
    }

    /**
     * Returns true if this Room is connected to a west Room and false
     * otherwise.
     * @return true if this Room is connected to a west Room and false
     *     otherwise.
     */
    public boolean hasRoomD() {
        return RoomD != null;
    }

}
