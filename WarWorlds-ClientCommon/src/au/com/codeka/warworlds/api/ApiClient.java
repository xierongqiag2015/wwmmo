package au.com.codeka.warworlds.api;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ByteArrayEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.protobuf.Message;

/**
 * This is the main "client" that accesses the War Worlds API.
 */
public class ApiClient {
    final static Logger log = LoggerFactory.getLogger(ApiClient.class);
    private static ArrayList<String> sCookies;

    /**
     * Configures the client to talk to the given "baseUri". All URLs will then be relative
     * to this URL. Usually, this will be something like https://warworldsmmo.appspot.com/api/v1
     * 
     * @param baseUri The base URI that all APIs calls are made against.
     */
    public static void configure(URI baseUri) {
        log.info("Resetting cookies... configuring baseUri: "+baseUri);
        sCookies = new ArrayList<String>();
        RequestManager.configure(baseUri);
    }

    public static URI getBaseUri() {
        return RequestManager.getBaseUri();
    }

    /**
     * Gets the collection of cookies we'll add to all requests (useful for authentication, 
     * or whatever)
     */
    public static List<String> getCookies() {
        return sCookies;
    }

    /**
     * Fetches a raw protocol buffer from the given URL via a HTTP GET.
     * 
     * \param url The URL of the object to fetch, relative to the server root (so for
     *        example, it might be "/motd" and depending on the other properties set up
     *        in the \c ApiClient, this could resolve to something like
     *        "https://warworldsmmo.appspot.com/api/v1/motd"
     * \param protoBuffFactory the class that we want to fetch, this will also determine
     *        the return value of this method.
     */
    public static <T> T getProtoBuf(String url, Class<T> protoBuffFactory) throws ApiException {
        Map<String, List<String>> headers = getHeaders();

        RequestManager.ResultWrapper res = RequestManager.request("GET", url, headers);
        try {
            HttpResponse resp = res.getResponse();
            int statusCode = resp.getStatusLine().getStatusCode();
            if (statusCode < 200 || statusCode > 299) {
                log.warn("API \"GET {}\" returned {}", url, resp.getStatusLine());
                return null;
            }

            return parseResponseBody(resp, protoBuffFactory);
        } finally {
            res.close();
        }
    }

    /**
     * Uses the "PUT" HTTP method to put a protocol buffer at the given URL. This is useful when
     * you don't expect a response (other than "201", success)
     */
    public static boolean putProtoBuf(String url, Message pb) throws ApiException{
        Map<String, List<String>> headers = getHeaders();

        ByteArrayEntity body = new ByteArrayEntity(pb.toByteArray());
        body.setContentType("application/x-protobuf");

        RequestManager.ResultWrapper res = RequestManager.request("PUT", url, headers, body);
        try {
            HttpResponse resp = res.getResponse();
            int statusCode = resp.getStatusLine().getStatusCode();
            if (statusCode < 200 || statusCode > 299) {
                log.warn("API \"PUT {}\" returned {}", url, resp.getStatusLine());
                return false;
            }

            return true;
        } finally {
            res.close();
        }
    }

    /**
     * Uses the "PUT" HTTP method to put a protocol buffer at the given URL.
     */
    public static <T> T putProtoBuf(String url, Message pb, Class<T> protoBuffFactory)
            throws ApiException {
        return putOrPostProtoBuff("PUT", url, pb, protoBuffFactory);

    }

    /**
     * Uses the "POST" HTTP method to post a protocol buffer at the given URL.
     */
    public static <T> T postProtoBuf(String url, Message pb, Class<T> protoBuffFactory)
            throws ApiException {
        return putOrPostProtoBuff("POST", url, pb, protoBuffFactory);
    }

    private static <T> T putOrPostProtoBuff(String method, String url, Message pb, 
            Class<T> protoBuffFactory) throws ApiException {
        Map<String, List<String>> headers = getHeaders();

        ByteArrayEntity body = new ByteArrayEntity(pb.toByteArray());
        body.setContentType("application/x-protobuf");

        RequestManager.ResultWrapper res = RequestManager.request(method, url, headers, body);
        try {
            HttpResponse resp = res.getResponse();
            int statusCode = resp.getStatusLine().getStatusCode();
            if (statusCode < 200 || statusCode > 299) {
                log.warn("API \"{} {}\" returned {}", new Object[] {
                        method, url, resp.getStatusLine()});
            }

            return parseResponseBody(resp, protoBuffFactory);
        } finally {
            res.close();
        }
    }

    /**
     * Sends a HTTP 'DELETE' to the given URL.
     */
    public static void delete(String url) throws ApiException {
        Map<String, List<String>> headers = getHeaders();

        RequestManager.ResultWrapper res = RequestManager.request("DELETE", url, headers);
        try {
            HttpResponse resp = res.getResponse();
            int statusCode = resp.getStatusLine().getStatusCode();
            if (statusCode < 200 || statusCode > 299) {
                log.warn("API \"DELETE {}\" returned {}", url, resp.getStatusLine());
            }
        } finally {
            res.close();
        }
    }

    /**
     * Gets the headers that we'll add to all of our requests.
     */
    private static Map<String, List<String>> getHeaders() {
        TreeMap<String, List<String>> headers = new TreeMap<String, List<String>>();
        if (!sCookies.isEmpty()) {
            headers.put("Cookie", sCookies);
        } else {
            log.warn("Cookies collection is empty, possible error!");
        }
        ArrayList<String> accept = new ArrayList<String>();
        accept.add("application/x-protobuf");
        headers.put("Accept", accept);

        return headers;
    }

    /**
     * Parses the response from a request and returns the protocol buffer returned therein 
     * (if any).
     * 
     * @param url
     * @param resp
     * @param protoBuffFactory
     * @return
     */
    @SuppressWarnings({"unchecked", "deprecation"}) /* not deprecated on Android */
    private static <T> T parseResponseBody(HttpResponse resp, Class<T> protoBuffFactory) {
        HttpEntity entity = resp.getEntity();
        if (entity != null) {
            T result = null;

            try {
                Method m = protoBuffFactory.getDeclaredMethod("parseFrom", InputStream.class);
                result = (T) m.invoke(null, entity.getContent());

                entity.consumeContent();
            } catch (Exception e) {
                // any errors can just be ignored, reallu (return null instead)
                log.error("Error getting protocol buffer!", e);
            }

            return result;
        }

        return null;
    }
}