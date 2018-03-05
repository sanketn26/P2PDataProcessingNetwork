package org.sanket.p2pdataprocessingnetwork.dht.operation;

import java.io.IOException;
import org.sanket.p2pdataprocessingnetwork.dht.KadConfiguration;
import org.sanket.p2pdataprocessingnetwork.dht.KadServer;
import org.sanket.p2pdataprocessingnetwork.dht.KademliaNode;
import org.sanket.p2pdataprocessingnetwork.dht.hashtable.KademliaDHT;

/**
 * An operation that handles refreshing the entire Kademlia Systems including buckets and content
 *
 * @author Joshua Kissoon
 * @since 20140306
 */
public class KadRefreshOperation implements Operation
{

    private final KadServer server;
    private final KademliaNode localNode;
    private final KademliaDHT dht;
    private final KadConfiguration config;

    public KadRefreshOperation(KadServer server, KademliaNode localNode, KademliaDHT dht, KadConfiguration config)
    {
        this.server = server;
        this.localNode = localNode;
        this.dht = dht;
        this.config = config;
    }

    @Override
    public void execute() throws IOException
    {
        /* Run our BucketRefreshOperation to refresh buckets */
        new BucketRefreshOperation(this.server, this.localNode, this.config).execute();

        /* After buckets have been refreshed, we refresh content */
        new ContentRefreshOperation(this.server, this.localNode, this.dht, this.config).execute();
    }
}
