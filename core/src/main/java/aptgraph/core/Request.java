package aptgraph.core;

import java.io.Serializable;
import net.jcip.annotations.Immutable;

/**
 * Represents a single Request.
 * The class is immutable (once it is created, it cannot be modified anymore),
 * which is very handy for multi-threaded programming.
 * @author Thibault Debatty
 */
@Immutable
public class Request implements Serializable {
    private final double time;
    private final int elapsed;
    private final String client;
    private final String code;
    private final int status;
    private final int bytes;
    private final String method;
    private final String url;
    private final String domain;
    private final String peerstatus;
    private final String peerhost;
    private final String type;

    /**
     * Create a new immutable request.
     *
     * @param time
     * @param elapsed
     * @param client
     * @param code
     * @param status
     * @param bytes
     * @param method
     * @param url
     * @param domain
     * @param peerstatus
     * @param peerhost
     * @param type
     */
    public Request(
            final double time,
            final int elapsed,
            final String client,
            final String code,
            final int status,
            final int bytes,
            final String method,
            final String url,
            final String domain,
            final String peerstatus,
            final String peerhost,
            final String type) {

        this.time = time;
        this.elapsed = elapsed;
        this.client = client;
        this.code = code;
        this.status = status;
        this.bytes = bytes;
        this.method = method;
        this.url = url;
        this.domain = domain;
        this.peerstatus = peerstatus;
        this.peerhost = peerhost;
        this.type = type;
    }



    /**
     * Unix Timestamp as UTC seconds with a millisecond resolution.
     * @return
     */
    public final double getTime() {
        return time;
    }

    /**
     * In millisecond.
     * @return
     */
    public final int getElapsed() {
        return elapsed;
    }

    /**
     *
     * @return
     */
    public final String getClient() {
        return client;
    }

    /**
     *
     * @return
     */
    public final String getCode() {
        return code;
    }

    /**
     *
     * @return
     */
    public final int getStatus() {
        return status;
    }

    /**
     *
     * @return
     */
    public final int getBytes() {
        return bytes;
    }

    /**
     *
     * @return
     */
    public final String getMethod() {
        return method;
    }

    /**
     *
     * @return
     */
    public final String getUrl() {
        return url;
    }

    /**
     *
     * @return
     */
    public final String getDomain() {
        return domain;
    }

    /**
     *
     * @return
     */
    public final String getPeerstatus() {
        return peerstatus;
    }

    /**
     * This is the origin IP of the server if peerstatus is DIRECT.
     * @return
     */
    public final String getPeerhost() {
        return peerhost;
    }

    /**
     *
     * @return
     */
    public final String getType() {
        return type;
    }

    @Override
    public final String toString() {
        return time + " " + url + " " + client;
    }

}
