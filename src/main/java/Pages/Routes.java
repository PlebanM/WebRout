package Pages;

import Annotations.WebRoute;

public class Routes {

    @WebRoute(value = "/test")
    public String onTest(){
        return "Its a working Annotation";
    }

    @WebRoute("/inna")
    public String anotherPage(){
        return "Another Page";
    }


}
