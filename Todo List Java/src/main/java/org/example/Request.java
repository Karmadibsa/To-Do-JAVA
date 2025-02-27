package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Request {

    private String method;
    private String path;
    private String protocol;
    private Map<String, String> headers = new HashMap<String, String>();
    private Map<String, String> params;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        var realPath = path;
        if (path.contains("?")) {
            var split = path.split("\\?");
            realPath = split[0];
            params = Arrays
                    .stream(split[1].split("&"))
                    .map(s -> s.split("="))
                    .collect(Collectors.toMap(
                            arr -> arr[0],
                            arr -> arr[1]));
        }
        this.path = realPath;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
