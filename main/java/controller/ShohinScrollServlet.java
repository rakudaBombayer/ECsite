package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ECsiteDAO;
import model.Shohin;

@WebServlet("/ShohinScrollServlet")
public class ShohinScrollServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                         throws ServletException, IOException {

        String offsetParam = request.getParameter("offset");
        String limitParam = request.getParameter("limit");

        int offset = 0;
        int limit = 29;

        if (offsetParam != null && !offsetParam.isEmpty()) {
            offset = Integer.parseInt(offsetParam);
        }
        if (limitParam != null && !limitParam.isEmpty()) {
            limit = Integer.parseInt(limitParam);
        }

        ECsiteDAO dao = new ECsiteDAO();
        List<Shohin> shohinList = null;

        try {
            shohinList = dao.getShohinList(offset, limit);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("shohinList", shohinList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/shohin_fragment.jsp");
        dispatcher.forward(request, response);
    }
}
