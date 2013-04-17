package com.iwgame.message.simulator;

import java.text.SimpleDateFormat;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{

    public void testApp() throws Exception
    {
    	long time1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2013-03-01 12:00:00").getTime()/1000;
    	long time2 = new SimpleDateFormat("yyyy-MM-dd :mm:ss").parse("2013-03-01 12:10:00").getTime()/1000;
    	
    	System.out.println(time2);
    	System.out.println(time1);
    	System.out.println(time2-time1);
    }
}
