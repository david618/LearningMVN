package learning.websockets;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketClient;
import org.eclipse.jetty.websocket.WebSocketClientFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.URI;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

/**
 * Created by david on 2/18/2016.
 */
public class ConsumeWS {
    private void trustAll() {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {

                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }


                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }


                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        } catch (GeneralSecurityException e) {
            System.out.println("Oops");
        }
    }


    public void connectWebsocket() {


        try {
            //SslContextFactory sslContextFactory = new SslContextFactory();
            //sslContextFactory.setTrustAll(true);
            //sslContextFactory.start();

            //WebSocketClient wsc = new WebSocketClient(sslContextFactory);


            final WebSocketClientFactory factory = new WebSocketClientFactory();

            factory.start();



            WebSocketClient client = factory.newWebSocketClient();



            //String url = "wss://W12AGS104.JENNINGS.HOME:6143/arcgis/ws/services/FAA/StreamServer/subscribe";
            String url = "ws://W12AGS104.JENNINGS.HOME:6180/arcgis/ws/services/FAA-Stream/StreamServer/subscribe";

            URI uri = new URI(url);

            WebSocketMessage msg = new WebSocketMessage(10000);

            WebSocket.Connection websocketConnection = client.open(uri, new WebSocketMessage(10000)).get(5, TimeUnit.SECONDS);

            System.out.println(System.currentTimeMillis());
            websocketConnection.setMaxIdleTime(10000);




        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        ConsumeWS a = new ConsumeWS();
        a.connectWebsocket();
    }
}
