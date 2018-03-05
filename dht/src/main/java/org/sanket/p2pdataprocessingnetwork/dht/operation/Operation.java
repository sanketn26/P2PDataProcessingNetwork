package org.sanket.p2pdataprocessingnetwork.dht.operation;

import java.io.IOException;
import org.sanket.p2pdataprocessingnetwork.dht.exceptions.RoutingException;

/**
 * An operation in the Kademlia routing protocol
 *
 * @author Joshua Kissoon
 * @created 20140218
 */
public interface Operation
{

    /**
     * Starts an operation and returns when the operation is finished
     *
     * @throws org.sanket.p2pdataprocessingnetwork.dht.exceptions.RoutingException
     */
    public void execute() throws IOException, RoutingException;
}