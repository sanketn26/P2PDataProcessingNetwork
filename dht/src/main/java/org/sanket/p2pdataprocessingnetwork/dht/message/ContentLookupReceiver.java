package org.sanket.p2pdataprocessingnetwork.dht.message;

import org.sanket.p2pdataprocessingnetwork.dht.KadConfiguration;
import org.sanket.p2pdataprocessingnetwork.dht.KadServer;
import org.sanket.p2pdataprocessingnetwork.dht.KademliaNode;
import org.sanket.p2pdataprocessingnetwork.dht.hashtable.KademliaDHT;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * Responds to a ContentLookupMessage by sending a ContentMessage containing the requested content;
 * if the requested content is not found, a NodeReplyMessage containing the K closest nodes to the request key is sent.
 *
 * @author Joshua Kissoon
 * @since 20140226
 */
public class ContentLookupReceiver implements Receiver
{

    private final KadServer server;
    private final KademliaNode localNode;
    private final KademliaDHT dht;
    private final KadConfiguration config;

    public ContentLookupReceiver(KadServer server, KademliaNode localNode, KademliaDHT dht, KadConfiguration config)
    {
        this.server = server;
        this.localNode = localNode;
        this.dht = dht;
        this.config = config;
    }

    @Override
    public void receive(Message incoming, int comm) throws IOException
    {
        ContentLookupMessage msg = (ContentLookupMessage) incoming;
        this.localNode.getRoutingTable().insert(msg.getOrigin());

        /* Check if we can have this data */
        if (this.dht.contains(msg.getParameters()))
        {
            try
            {
                /* Return a ContentMessage with the required data */
                ContentMessage cMsg = new ContentMessage(localNode.getNode(), this.dht.get(msg.getParameters()));
                server.reply(msg.getOrigin(), cMsg, comm);
            }
            catch (NoSuchElementException ex)
            {
                /* @todo Not sure why this exception is thrown here, checkup the system when tests are writtem*/
            }
        }
        else
        {
            /**
             * Return a the K closest nodes to this content identifier
             * We create a NodeLookupReceiver and let this receiver handle this operation
             */
            NodeLookupMessage lkpMsg = new NodeLookupMessage(msg.getOrigin(), msg.getParameters().getKey());
            new NodeLookupReceiver(server, localNode, this.config).receive(lkpMsg, comm);
        }
    }

    @Override
    public void timeout(int comm)
    {

    }
}
