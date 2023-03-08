package mvc2.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc2.model.MVCBoradDAO;

public class PassController extends HttpServlet {
    MVCBoradDAO dao;
    @Override
    public void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException,ServletException{
        ServletContext application = this.getServletContext();
        dao =new MVCBoradDAO(application);

        String idx = req.getPathInfo();
        String pass = req.getPathInfo();

        boolean result = dao.passController(idx,pass);
        if(result){
            req.getRequestDispatcher("/view/View.jsp").forward(req, resp);
        }else{
            req.getRequestDispatcher("/view/Pass.jsp").forward(req,resp);
        }

    }
}
