package com.mmtechpoc.apacheignite.core;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;
import org.apache.ignite.cluster.ClusterGroup;
//http://ggfabric.blogspot.com/2015/12/apache-ignite-hello-world-example.html
public class BroacastRemoteClusterOnly {
	public static void main(String[] args) {

		try {

			Ignite ignite = Ignition
					.start("D:/USA_Works/ApacheIgnite/apache-ignite-2.7.0-bin/examples/config/example-ignite.xml");
			// Broadcast closure to all cluster
			ClusterGroup cGroup = ignite.cluster().forRemotes();
			ignite.compute(cGroup).broadcast(() -> System.out.println("Hello Worlds Remote cluster"));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
