package Requests;

import Annotations.WebRoute;
import Pages.CreatePages;
import Pages.Routes;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RequestReader implements HttpHandler {


    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String path = httpExchange.getRequestURI().toString();
        try {
            createSite(httpExchange,findWebRoute(path));
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    public String findWebRoute(String path) throws InvocationTargetException, IllegalAccessException {
        Routes routesObj = new Routes();
        Class routesClass = Routes.class;
        for (Method method : routesClass.getDeclaredMethods()) {
            Annotation annotation = method.getAnnotation(WebRoute.class);
            WebRoute webRoute = (WebRoute) annotation;
            if (webRoute.value().equals(path)) {
                return (String) method.invoke(routesObj);
            }

        }
        return siteDoesntExist();
    }
    public String siteDoesntExist(){
        return "Site doesn't exist";
    }

    private void createSite(HttpExchange httpExchange, String resource) throws IOException {
        httpExchange.sendResponseHeaders(200,resource.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(resource.getBytes());
        os.close();
    }
}
