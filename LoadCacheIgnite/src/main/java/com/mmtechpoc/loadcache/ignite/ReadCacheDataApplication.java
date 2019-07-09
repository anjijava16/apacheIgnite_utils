package com.mmtechpoc.loadcache.ignite;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CachePeekMode;

import com.mmtechpoc.loadcache.ignite.model.Person;

public class ReadCacheDataApplication {

	public static void main(String[] args) {

		try {

			Ignite ignite = Ignition.start(ConstantsUtils.LOAD_IGNITE);

			System.out.println();
			System.out.println(">>> CacheLoadOnlyStoreExample started.");

			IgniteCache<Long, Person> cache = ignite.getOrCreateCache(ConstantsUtils.CACHE_NAME);

			// load data.
			cache.loadCache(null);

			System.out.println(">>> Loaded number of items: " + cache.size(CachePeekMode.PRIMARY));

			for (int i = 0; i <= 20; i++) {
				
				System.out.println(">>> Data for the person by id1: " + cache.get(Long.valueOf(i)));
				
			}

		} catch (Exception e) {

		}

	}

}
