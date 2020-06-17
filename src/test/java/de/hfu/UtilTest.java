package de.hfu;

import org.junit.Test;

import static org.junit.Assert.*;
import static de.hfu.Util.*;

public class UtilTest {


    @Test(expected = IllegalArgumentException.class)
    public void wrongDate() {
        assertFalse(istErstesHalbjahr(0));
        assertFalse(istErstesHalbjahr(18));
    }

    @Test
    public void vaildDate() {
        for (int i = 1; i < 13; ++i){
            if (i <= 6){
                assertTrue(istErstesHalbjahr(i));
            } else {
                assertFalse(istErstesHalbjahr(i));
            }
        }
    }
}