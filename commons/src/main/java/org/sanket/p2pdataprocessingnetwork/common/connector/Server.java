package org.sanket.p2pdataprocessingnetwork.common.connector;

/**
 * A Generic interface for any Server component
 */
public interface Server {

    /**
     * Starts the Server
     */
    void startServer();

    /**
     * Shutsdown the Server
     */
    void shutdownServer();

    /**
     * Indicates if the Server is running or not
     * @return the status flag indicating the server status
     */
    boolean isRunning();
}
