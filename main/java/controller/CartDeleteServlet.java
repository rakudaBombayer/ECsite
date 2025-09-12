package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ECsiteDAO;

@WebServlet("/CartDeleteServlet")
public class CartDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Integer kaiinId = (Integer) session.getAttribute("kaiinId");
        if (kaiinId == null) {
            response.sendRedirect("LoginServlet");
            return;
        }

        int shohinId = Integer.parseInt(request.getParameter("shohin_id"));

        ECsiteDAO dao = new ECsiteDAO();
        dao.deleteCartItem(kaiinId, shohinId);

        response.sendRedirect("CartListServlet");
    }
}
