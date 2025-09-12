package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CartItem;  // ← モデルにクラスが必要
import model.ECsiteDAO;

@WebServlet("/CartListServlet")
public class CartListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer kaiinId = (Integer) session.getAttribute("kaiinId");

        if (kaiinId == null) {
            response.sendRedirect("LoginServlet");
            return;
        }

        ECsiteDAO dao = new ECsiteDAO();
        List<CartItem> cartList = dao.getCartList(kaiinId);

        request.setAttribute("cartList", cartList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/cart.jsp");
        dispatcher.forward(request, response);
    }
}
