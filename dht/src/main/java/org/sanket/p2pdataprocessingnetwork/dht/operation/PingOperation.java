package org.sanket.p2pdataprocessingnetwork.dht.operation;

import java.io.IOException;
import org.sanket.p2pdataprocessingnetwork.dht.KadServer;
import org.sanket.p2pdataprocessingnetwork.dht.exceptions.RoutingException;
import org.sanket.p2pdataprocessingnetwork.dht.node.Node;

public class PingOperation implements Operation
{

    private final KadServer server;
    private final Node localNode;
    private final Node toPing;

    /**
     * @param server The Kademlia server used to send & receive messages
     * @param local  The local node
     * @param toPing The node to send the ping message to
     */
    public PingOperation(KadServer server, Node local, Node toPing)
    {
        this.server = server;
        this.localNode = local;
        this.toPing = toPing;
    }

    @Override
    public void execute() throws IOException, RoutingException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}