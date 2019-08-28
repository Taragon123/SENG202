/**
 * Name: UUID_Entity.java
 * Authors: Connor Macdonald
 * Date: 19/08/2019
 */

package seng202.teamsix.data;

import java.util.UUID;

public class UUID_Entity {
    // Members
    private UUID uuid;

    UUID_Entity() {
        generateUUID();
    }

    // Methods

    /**
     * Sets internal uuid
     * @param msb most significant 8 bytes in uuid
     * @param lsb least significant 8 bytes in uuid
     */
    public void setUUID(long msb, long lsb) {
        uuid = new UUID(msb, lsb);
    }

    /**
     * Returns internal uuid
     * @return uuid
     */
    public UUID getUUID() {
        return uuid;
    }

    /**
     * Randomly generates an internal uuid
     */
    public void generateUUID() {
        uuid = UUID.randomUUID();
    }

    /**
     * Tests if two uuid entities are equal.
     * This function should not be overwritten by subclasses as the uuid should
     * always be the condition of equality.
     * @param entity compare equality with
     * @return true if equals entity
     */
    public boolean equals(UUID_Entity entity) {
        return uuid.equals(entity.getUUID());
    }
}
