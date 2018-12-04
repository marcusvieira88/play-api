package datasources;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;

import javax.inject.Singleton;

/**
 * The `Couchbase` class acts a simple connection manager for the `CouchbaseClient`
 * and makes sure that only one connection is alive throughout the application.
 *
 * You may want to extend and harden this implementation in a production environment.
 */
@Singleton
public final class Couchbase {

    /**
     * Holds the actual `CouchbaseClient`.
     */
    private static Cluster cluster = null;
    private static Bucket bucket;

    /**
     * Connects to Couchbase based on the configuration settings.
     *
     * If the database is not reachable, an error message is written and the
     * application exits.
     */
    public static boolean connect() {

        cluster = CouchbaseCluster.create("localhost");
        System.out.println("-----------------cluster-----------------------");
        System.out.println(cluster);
        System.out.println("-----------------cluster-----------------------");
        bucket = cluster.openBucket("test", "123456");
        System.out.println("-----------------bucket-----------------------");
        System.out.println(bucket);
        System.out.println("-----------------bucket-----------------------");

        return true;
    }

    /**
     * Disconnect from Couchbase.
     */
    public static boolean disconnect() {
//        if(client == null) {
//            return false;
//        }
//
//        return client.shutdown(3, TimeUnit.SECONDS);

//        Boolean disconnected = cluster.disconnect();
//        Boolean closed = bucket.close();
        return true;
    }

    public static Cluster getCluster() {
        return cluster;
    }

    public static Bucket getBucket() {
        return bucket;
    }
}