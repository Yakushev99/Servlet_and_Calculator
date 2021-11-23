package com.example.praktika;

import com.example.praktika.logic.Model;
import com.example.praktika.logic.User;
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

@WebServlet(urlPatterns = "/delete")
public class ServletDelete extends HttpServlet {

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Model model = Model.getInstance();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        StringBuffer jb = new StringBuffer();
        String line;
        try{
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
        JsonObject jobj = gson.fromJson(String.valueOf(jb), JsonObject.class);

        request.setCharacterEncoding("UTF-8");

        int id = jobj.get("id").getAsInt();

        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();

        if ((id < 1) || (id > model.getFromList().size())){
            pw.print("Пользователя с таким ID не существует!");
        } else {

            model.delete(id);

            pw.print("Пользователь с ID="+id+" удален");
            pw.print(gson.toJson(model.getFromList()));
        }
    }
}
