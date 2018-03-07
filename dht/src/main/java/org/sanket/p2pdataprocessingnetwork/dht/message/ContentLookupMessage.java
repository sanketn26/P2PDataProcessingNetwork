package org.sanket.p2pdataprocessingnetwork.dht.message;

import org.sanket.p2pdataprocessingnetwork.dht.hashtable.GetParameter;
import org.sanket.p2pdataprocessingnetwork.dht.node.Node;
import org.sanket.p2pdataprocessingnetwork.dht.util.serializer.JsonSerializer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ContentLookupMessage implements Message
{

    public static final byte CODE = 0x03;

    private Node origin;
    private GetParameter params;

    /**
     * @param origin The node where this lookup came from
     * @param params The parameters used to find the content
     */
    public ContentLookupMessage(Node origin, GetParameter params)
    {
        this.origin = origin;
        this.params = params;
    }

    public ContentLookupMessage(DataInputStream in) throws IOException
    {
        this.fromStream(in);
    }

    public GetParameter getParameters()
    {
        return this.params;
    }

    public Node getOrigin()
    {
        return this.origin;
    }

    @Override
    public void toStream(DataOutputStream out) throws IOException
    {
        this.origin.toStream(out);

        /* Write the params to the stream */
        new JsonSerializer<GetParameter>().write(this.params, out);
    }

    @Override
    public final void fromStream(DataInputStream in) throws IOException
    {
        this.origin = new Node(in);

        /* Read the params from the stream */
        try
        {
            this.params = new JsonSerializer<GetParameter>().read(in);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public byte code()
    {
        return CODE;
    }

}
