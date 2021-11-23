package com.example.calculator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet(urlPatterns = "/math")
public class ServletCalculator extends HttpServlet {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        StringBuffer jb = new StringBuffer();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
        JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);

        request.setCharacterEncoding("UTF-8");

        double a = jobj.get("a").getAsDouble();
        double b = jobj.get("b").getAsDouble();
        String math = jobj.get("math").getAsString();

        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();

        if (Objects.equals(math, "*")){
            pw.print("\"result\": "+gson.toJson(a*b));
        } else if (Objects.equals(math, "/")){
            pw.print("\"result\": "+gson.toJson(a/b));
        } else if (Objects.equals(math, "-")){
            pw.print("\"result\": "+gson.toJson(a-b));
        } else if (Objects.equals(math, "+")){
            pw.print("\"result\": "+gson.toJson(a+b));
        } else if (Objects.equals(math, "^")){
            pw.print("\"result\": "+gson.toJson(Math.pow(a,b)));
        }
    }
}
