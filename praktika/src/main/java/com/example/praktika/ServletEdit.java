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

@WebServlet(urlPatterns = "/edit")
public class ServletEdit extends HttpServlet {

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

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
            String name = jobj.get("name").getAsString();
            String surname = jobj.get("surname").getAsString();
            double salary = jobj.get("salary").getAsDouble();

            User user = new User(name, surname, salary);
            user.setName(name);
            user.setSurname(surname);
            user.setSalary(salary);
            model.edit(user,id);

            pw.print("Пользователь с ID="+id+" изменен на "+"Имя: " + gson.toJson(model.getFromList().get(id).getName()) +" "+
                    "Фамилия: " + gson.toJson(model.getFromList().get(id).getSurname())+" "+
                    "Зарплата: " + gson.toJson(model.getFromList().get(id).getSalary()));
            pw.print(gson.toJson(model.getFromList()));
        }
    }
}
