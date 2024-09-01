package com.mysite.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.json.JSONArray;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

@Component(
        service = { Servlet.class },
        property = {
                "sling.servlet.paths=/bin/saveImageMappings",
                "sling.servlet.methods=POST"
        }
)
public class SaveImageMappingsServlet extends SlingAllMethodsServlet {

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        // Read the incoming JSON data
        String json = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
        try {
            JSONObject imageMappings = new JSONObject(json);
            response.getWriter().write(json);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        // Process and store imageMappings in the repository or other storage
        // For example, you can save this data to a specific node in the JCR

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.print("{\"status\": \"success\"}");
        }
    }
}

