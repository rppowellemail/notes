# KryoNet Example 01 - Basic KryoNet Server/Client

This is an example Java project that uses KryoNet as a Server/Client.

Based on `xSaucecode`'s _Java KryoNet :: A Simple Server/Client Program_:

* https://www.youtube.com/watch?v=KDTLRxppejE


## Adding the KryoNet Dependency

### Manual download/using all-in-one jar 

* https://github.com/EsotericSoftware/kryonet/releases/tag/kryonet-2.21

Extract and copy the `kryonet-2.21-all.jar` file

Add the 'all-in-one' jar to the classpath/add as library module to project:

`jars/production/onejar/kryonet-2.21-all.jar`

### Gradle Dependency

Add the following to `build.gradle`:

```gradle
dependencies {
    // https://mvnrepository.com/artifact/com.esotericsoftware/kryonet
    implementation group: 'com.esotericsoftware', name: 'kryonet', version: '2.22.0-RC1'
    ...
}
```

Or:

```gradle
dependencies {
    // https://mvnrepository.com/artifact/kryonet/kryonet
    implementation group: 'kryonet', name: 'kryonet', version: '2.21'
    // or
    // implementation group: 'kryonet', name: 'kryonet-all', version: '2.21'
    ...
}
```


## The Shared Classes

`KryoNetMessage.java`

```java
public class KryoNetMessage {
    public String message;
}
```


`KryoNetShared.java`

```java
import com.esotericsoftware.kryo.Kryo;

public class KryoNetShared {
    public static int tcpPort = 5150, udpPort=5151;
    public static String serverHostname = "127.0.0.1";

    public static void register(Kryo k) {
        k.register(KryoNetMessage.class);
    }
}
```

## The Server

`KryoNetServer.java`

```java
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;
import java.util.Date;

public class KryoNetServer extends Listener {
    private static Server server;

    public static void main(String[] args) throws IOException {
        System.out.println("Starting Server...");
        server = new Server();
        KryoNetShared.register(server.getKryo());
        server.bind(KryoNetShared.tcpPort, KryoNetShared.udpPort);

        server.start();

        server.addListener(new KryoNetServer());
        System.out.println("Server is running!");
    }

    @Override
    public void connected(Connection c) {
        System.out.println("Received a connection from " + c.getID() + " : "+ c.getRemoteAddressTCP().getHostString());
        KryoNetMessage message = new KryoNetMessage();
        message.message = "Hello!  The time is: " + (new Date()).toString();
        c.sendTCP(message);
    }

    @Override
    public void received(Connection connection, Object o) {
    }

    @Override
    public void disconnected(Connection c) {
        System.out.println("Client " + c.getID() + " disconnected.");
    }
}
```

## The Client

`KryoNetClient.java`

```java
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

public class KryoNetClient extends Listener {
    static Client client;
    static boolean messageReceived = false;
    public static void main(String[] args) throws IOException, InterruptedException {
        client = new Client();
        KryoNetShared.register(client.getKryo());
        client.start();
        client.connect(5000, KryoNetShared.serverHostname, KryoNetShared.tcpPort, KryoNetShared.udpPort);
        client.addListener(new KryoNetClient());
        System.out.println("Client waiting for message...");
        while(!messageReceived) {
            Thread.sleep(100);
        }
        System.out.println("Client exiting.");
        System.exit(0);
    }

    @Override
    public void received(Connection connection, Object o) {
        if (o instanceof KryoNetMessage) {
            KryoNetMessage m = (KryoNetMessage) o;
            System.out.println("Client received: " + m.message);
            messageReceived = true;
        }
    }
}
```
