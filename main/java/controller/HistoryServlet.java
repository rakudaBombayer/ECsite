package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ECsiteDAO;
import model.OrderHistory;

@WebServlet("/HistoryServlet") 
public class HistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer kaiinId = (Integer) session.getAttribute("kaiinId");

        if (kaiinId == null) {
            response.sendRedirect("/WEB-INF/view/login.jsp");
            return;
        }

        ECsiteDAO dao = new ECsiteDAO();
        List<OrderHistory> orderHistory = dao.getOrderHistoryByKaiinId(kaiinId);
        request.setAttribute("orderHistory", orderHistory);
        request.getRequestDispatcher("/WEB-INF/view/history.jsp").forward(request, response);
    }
}
