package com.benlefevre.mynews;

import com.benlefevre.mynews.utils.Constants;

import org.junit.Test;

import static org.junit.Assert.*;

public class ConstantsTest {

    @Test
    public void constantReturnsTest(){
        assertEquals("url", Constants.URL);
        assertEquals("id",Constants.ID);

        assertEquals("position",Constants.POSITION);
        assertEquals(1,Constants.TOPSTORIES);
        assertEquals(2,Constants.MOSTPOPULAR);

        assertEquals("channel", Constants.CHANNEL_ID);
        assertEquals("preferences",Constants.PREFERENCES);
        assertEquals("query",Constants.QUERY);
        assertEquals("filterQuery",Constants.FILTERQUERY);
        assertEquals("beginDate",Constants.BEGINDATE);
        assertEquals("endDate",Constants.ENDDATE);

        assertEquals("switch",Constants.SWITCH);
        assertEquals("arts",Constants.ARTS);
        assertEquals("sport",Constants.SPORT);
        assertEquals("travel",Constants.TRAVEL);
        assertEquals("entrepreneurs",Constants.ENTREPRENEURS);
        assertEquals("business",Constants.BUSINESS);
        assertEquals("politics",Constants.POLITICS);
    }

}