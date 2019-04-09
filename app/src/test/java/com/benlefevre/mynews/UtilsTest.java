package com.benlefevre.mynews;

import com.benlefevre.mynews.utils.Utils;
import com.google.android.material.checkbox.MaterialCheckBox;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UtilsTest {

    @Mock
    MaterialCheckBox cb1,cb2,cb3,cb4,cb5,cb6;

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

        initialDate = "1/1/2018";

        assertEquals("01/01/2018",Utils.convertDateForDisplay(initialDate));

        initialDate = "15/1/2018";

        assertEquals("15/01/2018",Utils.convertDateForDisplay(initialDate));

        initialDate = "1/12/2018";

        assertEquals("01/12/2018",Utils.convertDateForDisplay(initialDate));

        initialDate = "2019-04-03T06:24:44+0000";

        assertEquals("03/04/2019",Utils.convertDateForDisplay(initialDate));

        initialDate = "2012-04-30T00:00:00Z";

        assertEquals("30/04/2012",Utils.convertDateForDisplay(initialDate));
    }

    @Test
    public void convertDateForQuery(){
        String initialDate = "01/12/2018";

        assertEquals("20181201",Utils.convertDateForQuery(initialDate));

        initialDate = "";

        assertEquals("",Utils.convertDateForQuery(initialDate));

        initialDate = "2018-12-01";

        assertEquals("2018-12-01",Utils.convertDateForQuery(initialDate));
    }


    @Test
    public void convertTitleForDisplayTest(){
        String initialTitle = "A Deadly Blaze in the Alps Made a Biker a Hero and Tunnels Safer for All";

        assertEquals("A Deadly Blaze in the Alps Made a Biker a Hero and Tunnels S..." , Utils.convertTitleForDisplay(initialTitle));

        initialTitle = "A Deadly Blaze in the Alps Made a Biker a Hero...";

        assertEquals(initialTitle,Utils.convertTitleForDisplay(initialTitle));

        initialTitle = "A Deadly Blaze in the Alps Made a Biker a Hero and Tunnels                               ";

        assertEquals("A Deadly Blaze in the Alps Made a Biker a Hero and Tunnels...",Utils.convertTitleForDisplay(initialTitle));
    }

    @Test
    public void configureQueries(){
        MockitoAnnotations.initMocks(this);

        assertEquals("news_desk:()",Utils.configureFilterQueries(cb1,cb2,cb3,cb4,cb5,cb6));
        when(cb1.isChecked()).thenReturn(true);
        assertEquals("news_desk:(\"Arts\" )",Utils.configureFilterQueries(cb1,cb2,cb3,cb4,cb5,cb6));

        when(cb2.isChecked()).thenReturn(true);
        assertEquals("news_desk:(\"Arts\" \"Business\" )",Utils.configureFilterQueries(cb1,cb2,cb3,cb4,cb5,cb6));

        when(cb3.isChecked()).thenReturn(true);
        when(cb4.isChecked()).thenReturn(true);
        assertEquals("news_desk:(\"Arts\" \"Business\" \"Politics\" \"Sports\" )",Utils.configureFilterQueries(cb1,cb2,cb3,cb4,cb5,cb6));

        when(cb5.isChecked()).thenReturn(true);
        when(cb6.isChecked()).thenReturn(true);
        assertEquals("news_desk:(\"Arts\" \"Business\" \"Politics\" \"Sports\" \"Entrepreneurs\" \"Travel\" )",Utils.configureFilterQueries(cb1,cb2,cb3,cb4,cb5,cb6));
    }

    @Test
    public void convertTitleToIdTest(){
        String initialtitle = "Ethiopian Crash Report Indicates Pilots Followed Boeing’s Emergency Procedures";
        assertEquals("Ethiopian Crash Repo",Utils.convertTitleToId(initialtitle));
        initialtitle = "Ethiopian Crash";
        assertEquals("Ethiopian Crash",Utils.convertTitleToId(initialtitle));
    }
}