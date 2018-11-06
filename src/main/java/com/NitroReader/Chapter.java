package com.NitroReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.ResponseLogin;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

@WebServlet("/Chapter")
public class Chapter extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String option = request.getParameter("option");
        ResponseLogin res = new ResponseLogin();
        ObjectMapper objM = new ObjectMapper();
        objM.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objM.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        String r;
        PrintWriter out = response.getWriter();
        switch (option) {
            case "delete":
                try{
                    FileUtils.deleteDirectory(new File("D:\\Users\\Brandon\\Documentos\\URU\\WEB 2\\WorkSpace\\NitroReader\\NitroReader\\src\\main\\webapp\\probarCAPITULOS\\hola"));
                    res.setMessage("el capitulo se ha borrado correctamente");
                    r = objM.writeValueAsString(res);
                    System.out.println(r);
                    out.print(r);
                    }
                    catch (Error e){
                    e.printStackTrace();
                    }

                break;
            case "getchapter":
                String Dir = "D:\\Users\\Brandon\\Documentos\\URU\\WEB 2\\WorkSpace\\NitroReader\\NitroReader\\src\\main\\webapp\\Test";
                String filedir =  "http://localhost:8080/NitroReader/Test";
                try{
                    int c = new File(Dir).listFiles().length;
                    JSONObject json = new JSONObject();
                    json.put("max", c );
                    json.put("dir", filedir);
                    System.out.println(json );
                    out.print(json);
                }catch (Error e){
                    e.printStackTrace();
                }
                break;
            case "createchapter":
                String baseDir = "D:\\Users\\Brandon\\Documentos\\URU\\WEB 2\\WorkSpace\\NitroReader\\NitroReader\\src\\main\\webapp\\probarCAPITULOS";
                try{
                    int c = (new File(baseDir).listFiles().length)+1;
                    FileUtils.forceMkdir(new File(baseDir+ "/"+"hola"));
                    res.setMessage("el capitulo se ha creado correctamente");
                    r = objM.writeValueAsString(res);
                    System.out.println(r);
                    out.print(r);
                }
                catch (Error e){
                    e.printStackTrace();
                }
                break;
        }

    }
}