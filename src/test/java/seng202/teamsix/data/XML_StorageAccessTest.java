package seng202.teamsix.data;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class XML_StorageAccessTest {
    @Test
    public void testConstructor() {
        XML_StorageAccess invalid;
        XML_StorageAccess valid;

        // Test pass case
        try {
            valid = new XML_StorageAccess("test_data");
        }catch (IOException e) {
            fail("XML_StorageAccess unable to create valid directory");
        }

        // Test fail case
        boolean caught_exception = false;
        try {
            invalid = new XML_StorageAccess("impossibledirectory/shouldnotexist");
        }catch (IOException e) {
           caught_exception = true;
        }
        assertTrue(caught_exception);
    }
}