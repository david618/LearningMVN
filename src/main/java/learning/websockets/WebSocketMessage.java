package learning.websockets;

import org.eclipse.jetty.websocket.WebSocket;

/**
 * Created by david on 2/18/2016.
 */
public class WebSocketMessage implements WebSocket.OnTextMessage  {

    Integer numFeatures = 0;


    public Integer getCnt() {
        return cnt;
    }

    private Integer cnt = 0;
    private Connection con;


    public WebSocketMessage(Integer numFeatures) {
        this.numFeatures = numFeatures;
    }


    public void onMessage(String s) {
        cnt++;
        //System.out.println(cnt);
        if (cnt % 1000 == 0) System.out.println(cnt);
        //System.out.println(data);
        //if (cnt >= numFeatures) this.con.close();

    }

    public void onOpen(Connection connection) {
        this.con = connection;
        System.out.println("Websocket connection succeeded");
    }

    public void onClose(int i, String s) {
        System.out.println(System.currentTimeMillis());
        System.out.println("Websocket connection lost");
        System.out.println("Number Received: " + this.cnt);

    }
}
