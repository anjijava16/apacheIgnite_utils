package com.mmtechpoc.apacheignite;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;


public class IgniteMetricsExample {
    public static void main(String args[]) {
        final Ignite ignite = Ignition.start("config/ignite-config.xml");

        final IgniteCache cache = ignite.cache("test-cache");

        new Timer().schedule(new TimerTask() {
            @Override public void run() {
                System.out.println("average put time = " + cache.metrics().getAveragePutTime());
                System.out.println("average get time = " + cache.metrics().getAverageGetTime());
            }
        }, 1000, 2000);
    }
}