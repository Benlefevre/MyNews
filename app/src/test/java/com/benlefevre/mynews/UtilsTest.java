package com.benlefevre.mynews;

import com.benlefevre.mynews.models.Article;
import com.benlefevre.mynews.utils.Utils;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;

import okhttp3.internal.Util;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UtilsTest {

    @Test
    public void convertDateForDisplayTest(){
        String initialDate = "2019-03-28T06:00:06-04:00";

        assertEquals("This is not a correct date", Utils.convertDateForDisplay(""));
        assertEquals("This is not a correct date", Utils.convertDateForDisplay("AAAA"));
        assertEquals("This is not a correct date", Utils.convertDateForDisplay("19-03-28"));
        assertEquals("28/03/2019",Utils.convertDateForDisplay(initialDate));

        initialDate = "28/03/2019";

        assertEquals(initialDate,Utils.convertDateForDisplay(initialDate));

        initialDate = "2019-03-28";

        assertEquals("28/03/2019",Utils.convertDateForDisplay(initialDate));
    }


    @Test
    public void ConvertTitleForDisplayTest(){
        String initialTitle = "A Deadly Blaze in the Alps Made a Biker a Hero and Tunnels Safer for All";

        assertEquals("A Deadly Blaze in the Alps Made a Biker a Hero and Tunnels S..." , Utils.convertTitleForDisplay(initialTitle));

        initialTitle = "A Deadly Blaze in the Alps Made a Biker a Hero...";

        assertEquals(initialTitle,Utils.convertTitleForDisplay(initialTitle));

        initialTitle = "A Deadly Blaze in the Alps Made a Biker a Hero and Tunnels                               ";

        assertEquals("A Deadly Blaze in the Alps Made a Biker a Hero and Tunnels...",Utils.convertTitleForDisplay(initialTitle));
    }
}