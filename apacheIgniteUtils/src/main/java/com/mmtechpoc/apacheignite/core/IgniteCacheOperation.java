package com.mmtechpoc.apacheignite.core;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.CacheConfiguration;

public class IgniteCacheOperation {

	
	public static void main(String[] args) {

		try {

			Ignite ignite = Ignition
					.start("D:/USA_Works/ApacheIgnite/apache-ignite-2.7.0-bin/examples/config/example-ignite.xml");
	
			CacheConfiguration<Integer, String> cf1=new CacheConfiguration<>("webniar");
			cf1.setBackups(2);
			
			IgniteCache<Integer, String> iCache=ignite.getOrCreateCache(cf1);
			
			int cnt=10;
			
			for(int i=0;i<cnt;i++) {
				iCache.put(i, Integer.toString(i));
			}
			for(int i=0;i<cnt;i++) {
				System.out.println("Got ==>"+iCache.get(i));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
