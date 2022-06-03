package com.example.strategyandroidapp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import android.location.Location;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.google.android.gms.tasks.Task;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


@RunWith(AndroidJUnit4.class)
public class StrategyFinderTest {
    private int globalId = 0;
    private StrategyFinder sf;
    private SixG_Strategy.Strategy makeStrat(double lat, double lon, double radius,
                                             long startTime, long endTime,
                                             int connection, int priority){
        BigInteger bigLat = toBigInteger(lat*100000);
        BigInteger bigLon = toBigInteger(lon*100000);
        BigInteger bigRadius = toBigInteger(radius*100000);
        SixG_Strategy.Location location = new SixG_Strategy.Location(bigLon, bigLat, bigRadius);
        BigInteger bigStartTime = toBigInteger(startTime);
        BigInteger bigEndTime = toBigInteger(endTime);
        BigInteger bigConn = toBigInteger(connection);
        BigInteger bigPrio = toBigInteger(priority);
        BigInteger bigId = toBigInteger(globalId++);
        return new SixG_Strategy.Strategy( bigId, location, bigStartTime, bigEndTime, bigConn, bigPrio, BigInteger.ZERO, BigInteger.ZERO);
    }

    private BigInteger toBigInteger(Number number) {
        return BigInteger.valueOf(number.longValue());
    }

    @Before
    public void setUp() throws Exception {
        Log.i("web3j", "setup");
        sf = new StrategyFinder(InstrumentationRegistry.getInstrumentation().getTargetContext());
        Location fakeLocation = new Location((String) null);
        fakeLocation.setLatitude(55.7828672);
        fakeLocation.setLongitude(12.5111416);
        Task task = sf.fusedLocationClient.setMockMode(true);
        sf.fusedLocationClient.setMockLocation(fakeLocation);
    }

    @Test
    public void emptyList() {
        sf.strategies = new ArrayList<SixG_Strategy.Strategy>();
        assertTrue(sf.chooseActiveStrategy() == null);
    }

    @Test
    public void fromNullToWorkingStrategy() {
        Long now = System.currentTimeMillis() / 1000L;
        SixG_Strategy.Strategy newStrat = makeStrat(55.785749, 12.521464,10000, now - 1000L,
                now + 1000000L, 0, 0);
        sf.strategies.add(newStrat);
        assertEquals(newStrat,sf.chooseActiveStrategy());
    }

    @Test
    public void chooseStratWithHigherPriority() {
        Long now = System.currentTimeMillis() / 1000L;
        SixG_Strategy.Strategy lowPriStrat = makeStrat(55.785749, 12.521464,10000, now - 1000L,
                now + 1000000L, 0, 0);
        SixG_Strategy.Strategy highPriStrat = makeStrat(55.785749, 12.521464,10000, now - 1000L,
                now + 1000000L, 0, 2);
        sf.strategies.add(lowPriStrat);
        sf.chooseActiveStrategy();
        sf.strategies.add(highPriStrat);
        assertEquals(highPriStrat,sf.chooseActiveStrategy());
    }

    @Test
    public void stratTimeExpired() {
        Long now = System.currentTimeMillis() / 1000L;
        SixG_Strategy.Strategy expiredStrat = makeStrat(55.785749, 12.521464,10000, now - 1000L,
                now - 10L, 0, 0);
        sf.strategies.add(expiredStrat);
        assertTrue(sf.chooseActiveStrategy() == null);
    }

    @Test
    public void stratToFarAway() {
        Long now = System.currentTimeMillis() / 1000L;
        SixG_Strategy.Strategy tooFarAwayStrat = makeStrat(55.681446, 12.575230,10000, now - 1000L,
                now + 10000000L, 0, 0);
        sf.strategies.add(tooFarAwayStrat);
        assertTrue(sf.chooseActiveStrategy() == null);
    }

    @Test
    public void stratExpiresChooseStratWithLowerPri() {
        Long now = System.currentTimeMillis() / 1000L;
        SixG_Strategy.Strategy toBeExpired = makeStrat(55.785749, 12.521464,10000, now - 1000L,
                now + 5L, 0, 2);

        SixG_Strategy.Strategy lowerPri = makeStrat(55.785749, 12.521464,10000, now - 1000L,
                now + 10000L, 1, 0);

        sf.strategies.add(toBeExpired);
        sf.strategies.add(lowerPri);
        assertEquals(toBeExpired, sf.chooseActiveStrategy());
        try {
            TimeUnit.SECONDS.sleep(6);
            assertEquals(lowerPri, sf.chooseActiveStrategy());
        } catch (InterruptedException e) {
            assertTrue(false);
        }
    }

}