package Pages;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class CreatePages {

    public String siteDoesntExist(){
        return "Site doesn't exist";
    }

    public void createSite(HttpExchange httpExchange, String resource) throws IOException {
        httpExchange.sendResponseHeaders(200,resource.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(resource.getBytes());
        os.close();
    }
}
