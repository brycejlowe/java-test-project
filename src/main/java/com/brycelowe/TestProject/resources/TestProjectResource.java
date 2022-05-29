package com.brycelowe.TestProject.resources;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

@Path("/{subResources:.*}")
@Produces(MediaType.APPLICATION_JSON)
public class TestProjectResource {
    private String[] groupInfo;

    public TestProjectResource(String[] groupInfo) {
        this.groupInfo = groupInfo;
    }

    @GET
    public String get(@Context HttpServletRequest request) throws Exception {
        return new ObjectMapper().writeValueAsString(writeSummary(request));
    }

    @POST
    public String post(@Context HttpServletRequest request) throws Exception {
        return new ObjectMapper().writeValueAsString(writeSummary(request));
    }

    @PUT
    public String put(@Context HttpServletRequest request) throws Exception {
        return new ObjectMapper().writeValueAsString(writeSummary(request));
    }

    @DELETE
    public String delete(@Context HttpServletRequest request) throws Exception {
        return new ObjectMapper().writeValueAsString(writeSummary(request));
    }

    private HashMap<String, Object> writeSummary(HttpServletRequest request) throws Exception {
        var response = new HashMap<String, Object>();

        // store the method
        response.put("method", request.getMethod());

        // capture the url
        response.put("url", request.getRequestURL());

        // capture the uri
        response.put("uri", request.getRequestURI());

        // output the group
        response.put("group", groupInfo.length > 0 ? groupInfo[0] : null);

        // capture the body
        BufferedInputStream inputStream = new BufferedInputStream(request.getInputStream());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = inputStream.read(); i != -1; i = inputStream.read()) {
            byteArrayOutputStream.write((byte)i);
        }
        response.put("body", byteArrayOutputStream.toString(StandardCharsets.UTF_8));

        // store the headers
        var capturedHeaders = new HashMap<String, String>();
        response.put("headers", capturedHeaders);
        var headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            capturedHeaders.put(headerName, request.getHeader(headerName));
        }

        // capture the query string
        var queryString = request.getParameterMap();
        var capturedQueryString = new HashMap<String, String[]>();
        response.put("queryString", capturedQueryString);
        queryString.keySet().forEach(key -> capturedQueryString.put(key, queryString.get(key)));

        return response;
    }
}
