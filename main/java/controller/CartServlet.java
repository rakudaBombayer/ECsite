package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.CartItem;
import model.ECsiteDAO;
import model.Shohin;

@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

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
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        ECsiteDAO dao = new ECsiteDAO();

        // 在庫数を取得してチェック
        Shohin shohin = dao.getProductById(shohinId);
        if (shohin == null) {
            request.setAttribute("errorMessage", "商品が見つかりませんでした。");
            request.getRequestDispatcher("/WEB-INF/view/shohin_detail.jsp").forward(request, response);
            return;
        }

        // すでにカートに入っている数量を取得（必要なら拡張）
        List<CartItem> cartList = dao.getCartList(kaiinId);
        int currentInCart = 0;
        for (CartItem item : cartList) {
            if (item.getShohinId() == shohinId) {
                currentInCart = item.getQuantity();
                break;
            }
        }

        if (quantity + currentInCart > shohin.getZaikoSuuryou()) {
            request.setAttribute("errorMessage", "在庫数を超える数量は追加できません。");
            request.setAttribute("shohin", shohin); // 詳細表示用
            request.getRequestDispatcher("/WEB-INF/view/shohin_detail.jsp").forward(request, response);
            return;
        }

        boolean success = dao.insertOrUpdateCart(kaiinId, shohinId, quantity);

        if (success) {
            response.sendRedirect("CartListServlet");
        } else {
            request.setAttribute("errorMessage", "カートに追加できませんでした。");
            request.setAttribute("shohin", shohin);
            request.getRequestDispatcher("/WEB-INF/view/shohin_detail.jsp").forward(request, response);
        }
    }

}
