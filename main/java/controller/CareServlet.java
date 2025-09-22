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
import model.CartItem;
import model.ECsiteDAO;

@WebServlet("/CareServlet")
public class CareServlet extends HttpServlet {
    
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

        // 合計金額を計算
        int totalPrice = 0;
        for (CartItem item : cartList) {
            totalPrice += item.getKakaku() * item.getQuantity();
        }
        request.setAttribute("totalPrice", totalPrice);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/care.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        // POST時の処理があればここに追加
    }
}
