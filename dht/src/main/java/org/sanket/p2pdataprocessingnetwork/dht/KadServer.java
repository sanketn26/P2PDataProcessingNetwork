package org.sanket.p2pdataprocessingnetwork.dht;

import org.sanket.p2pdataprocessingnetwork.dht.exceptions.KadServerDownException;
import org.sanket.p2pdataprocessingnetwork.dht.message.Message;
import org.sanket.p2pdataprocessingnetwork.dht.message.Receiver;
import org.sanket.p2pdataprocessingnetwork.dht.node.Node;

import java.io.IOException;

public interface KadServer {

    int sendMessage(Node to, Message msg, Receiver recv) throws IOException, KadServerDownException;

    void reply(Node to, Message msg, int comm) throws IOException;

    void startSeerver();

    void shutdownServer();
}
