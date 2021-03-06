/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Raz Consta, Reuben Keller, Dustin Ray
 */

package model.map;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;


/**
 * Represents map objects that can collide with each other.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public abstract class AbstractMapEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 4925595764489656918L;

    /** The pixel width of this map entity. */
    protected int myWidth;

    /** The pixel height of this map entity. */
    protected int myHeight;

    /** The x position of this map entity. */
    protected int myX;

    /** The y position of this map entity. */
    protected int myY;

    /** The x velocity of this map entity. */
    protected int myVelX;

    /** The y velocity of this map entity. */
    protected int myVelY;


    /**
     * Constructs a AbstractMapEntity with default tile width (48 pixels) and default
     * tile height (48 pixels).
     *
     * @param theX The x position for this entity in the map.
     * @param theY The y position for this entity in the map.
     */
    public AbstractMapEntity(final int theX, final int theY) {
        this(theX, theY, GameMap.TILE_WIDTH, GameMap.TILE_HEIGHT);
    }


    /**
     * Constructs a AbstractMapEntity with the given x and y positions, and the given
     * pixel width and height.
     *
     * @param theX The x position for this entity.
     * @param theY The y position for this entity.
     */
    public AbstractMapEntity(final int theX, final int theY,
                             final int theWidth, final int theHeight) {
        myX = theX;
        myY = theY;
        myWidth = theWidth;
        myHeight = theHeight;
        myVelX = 0;
        myVelY = 0;
    }


    /**
     * Returns the x position of this entity.
     *
     * @return The x position of this entity.
     */
    public int getX() {
        return myX;
    }


    /**
     * Returns the y position of this entity.
     *
     * @return The y position of this entity.
     */
    public int getY() {
        return myY;
    }


    /**
     * Sets the x position of this entity.
     */
    public void setX(final int theX) {
        myX = theX;
    }


    /**
     * Sets the y position of this entity.
     */
    public void setY(final int theY) {
        myY = theY;
    }


    /**
     * Sets the x-velocity of this entity.
     *
     * @param theVelX The x-velocity of this entity.
     */
    public void setVelX(final int theVelX) {
        myVelX = theVelX;
    }


    /**
     * Sets the y-velocity of this entity.
     *
     * @param theVelY The y-velocity of this entity.
     */
    public void setVelY(final int theVelY) {
        myVelY = theVelY;
    }


    /**
     * Updates the entities x and y positions according to its x- and y-velocities.
     */
    public void update() {
        myX += myVelX;
        myY += myVelY;
    }


    /**
     * Detects whether this AbstractMapEntity collides with another AbstractMapEntity.
     *
     * @param other The AbstractMapEntity to check for collision with.
     * @return true if this AbstractMapEntity collides with the given AbstractMapEntity and
     *     false otherwise.
     */
    public boolean collidesWith(AbstractMapEntity other) {
        return myX < other.myX + other.myWidth
                && myX + myWidth > other.myX
                && myY < other.myY + other.myHeight
                && myY + myHeight > other.myY;
    }


    /**
     * Detects whether this AbstractMapEntity is out of the Map bounds.
     *
     * @return true if this AbstractMapEntity is out of Map bounds and false otherwise.
     */
    public boolean outOfBounds() {
        return myX <= 0 || myX >= GameMap.PIXEL_WIDTH - myWidth
                || myY <= 0 || myY >= GameMap.PIXEL_HEIGHT - myHeight;
    }


    @Override
    public boolean equals(final Object other) {
        boolean result = false;
        if ((other != null) && (other.getClass().equals(this.getClass()))) {
            AbstractMapEntity o = (AbstractMapEntity) other;
            result = (myX == o.myX
                    && myY == o.myY
                    && myVelX == o.myVelX
                    && myVelY == o.myVelY);
        }
        return result;
    }


    @Override
    public int hashCode() {
        return Objects.hash(myX, myY, myVelX, myVelY);
    }


    @Override
    public String toString() {
        return "(x=" + myX + ", y=" + myY + ")";
    }
}
