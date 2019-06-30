package com.mmtechpoc.apacheignite.core;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;

public class ApacheIgniteBroadCastAllCluster {

	public static void main(String[] args) {

		try {

			Ignite ignite = Ignition
					.start("D:/USA_Works/ApacheIgnite/apache-ignite-2.7.0-bin/examples/config/example-ignite.xml");
			// Broadcast closure to all cluster

			ignite.compute().broadcast(() -> System.out.println("Hello World"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
