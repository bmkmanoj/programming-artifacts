# Introduction #

```
package snack;

import java.util.List;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * The service specification for this puzzle, (simonsays.thrift), can be compiled via the Thrift compiler into client
 * and server skeleton code, classes, and/or stubs depending on the target language of choice. Your client begins the
 * game by connecting to a Simon Says Thrift server and invoking the registerClient(1:string email) API first. Once that
 * has been done successfully, your client will iterate through a series of turns until the game is won. The client must
 * call the startTurn() API to receive a list of colors from the server. The client must then play back these colors in
 * the correct order to the server using the chooseColor(1:color colorChosen) API. Once done, the client must then call
 * endTurn().
 * 
 * If endTurn() returns false, the game has not yet been won. The client should repeat the process of calling
 * startTurn(), chooseColor(1:color colorChosen) several times, and then endTurn(). Once endTurn() returns true, the
 * client is in a win-ready state, and may win the game by calling the winGame() API. Make sure you save the string
 * returned by the winGame() call, as you will be required to send an email to the puzzle robot using this string as the
 * subject line. Also make sure you send the email from the same address used with your registerClient(1:string email)
 * call. Once the client has received the string from winGame(), the client may then safely terminate the connection and
 * shut down. You do not have to send an email to the puzzle robot before shutting down your client.
 * 
 * If the client chooses the wrong color, calls an extra color when it should have called endTurn(), or calls endTurn()
 * prematurely, the server will restart its state. The server resets the color sequence back to the starting length of
 * one and will return this new color sequence at the next startTurn() call. If your client detects this error, it
 * should stop and immediately start over by calling startTurn().
 * 
 * @author Marcello de Sales (marcello.desales@gmail.com)
 * 
 *         Generated the code using thrift "ant thrift-gen" = "thrift --strict --gen java:bean -o src thrift-dir" Note
 *         that the parameter "java:bean" generates the setters/getters.
 * 
 */
public class simonsays {

    /**
     * 
     * For future reference, we might have different thrift servers, so creating an enum to hold them here.
     * 
     * @author Marcello de Sales (marcello.desales@gmail.com)
     * 
     */
    enum ThriftServers {

        FACEBOOK("localhost", 1234);

        /**
         * The hostname of the server.
         */
        private String host;
        /**
         * The port of the server.
         */
        private int port;

        private ThriftServers(String hostname, int port) {
            this.host = hostname;
            this.port = port;
        }

        public String getHostname() {
            return this.host;
        }

        public int getPortNumber() {
            return this.port;
        }

        @Override
        public String toString() {
            return this.host;
        }
    }

    public static void main(String[] args) {
        // Connect with the thrift server: transport and protocol.
        TTransport transp = new TSocket(ThriftServers.FACEBOOK.getHostname(), ThriftServers.FACEBOOK.getPortNumber());
        TProtocol prot = new TBinaryProtocol(transp);

        try {
            // open the connection
            transp.open();

            // Create the client from the generated.
            SimonSays.Client simonClient = new SimonSays.Client(prot);

            // Your client begins the game by connecting to a Simon Says Thrift server and invoking the
            // registerClient(1:string email) API first. Once that has been done successfully, your client will iterate
            // through a series of turns until the game is won.
            simonClient.registerClient("marcellodesales@gmail.com");

            // If endTurn() returns false, the game has not yet been won. The client should repeat the process of
            // calling startTurn(), chooseColor(1:color colorChosen) several times, and then endTurn().
            do {
                // The client must call the startTurn() API to receive a list of colors from the server.
                List<Color> simonListOfColors = simonClient.startTurn();
                if (simonListOfColors != null) {
                    System.out.println("Colors: " + simonListOfColors);
                    // The client must then play back these colors in the correct order to the server using the
                    // chooseColor(1:color colorChosen) API.
                    for (Color color : simonListOfColors) {
                        simonClient.chooseColor(color);
                    }
                }

                // Once done, the client must then call endTurn().
            } while (simonClient.endTurn());

            //Once endTurn() returns true, the client is in a
            // win-ready state, and may win the game by calling the winGame() API.
            String receivedStringEmailHeaderForBot = simonClient.winGame();
            System.out.println(receivedStringEmailHeaderForBot);

        } catch (TTransportException transportError) {
            System.err.println("Error while trying to connect to the server: " + transportError.getMessage());

        } catch (TException generalError) {
            System.err.println("General Thrift Error: " + generalError.getMessage());

        }
    }

}

```


# Details #

Add your content here.  Format your content with:
  * Text in **bold** or _italic_
  * Headings, paragraphs, and lists
  * Automatic links to other wiki pages