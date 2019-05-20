import Annotations.WebRoute;
import Pages.CreatePages;
import Pages.Routes;
import Requests.RequestReader;
import com.sun.net.httpserver.HttpServer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<String, String> siteMap;

    public static void main(String[] args) throws Exception {

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new RequestReader());
        server.setExecutor(null); // creates a default executor
        server.start();
    }



}