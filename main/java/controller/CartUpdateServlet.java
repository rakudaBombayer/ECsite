package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ECsiteDAO;
import model.Shohin;

@WebServlet("/CartUpdateServlet")
public class CartUpdateServlet extends HttpServlet {
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
        Shohin shohin = dao.getProductById(shohinId); // 商品情報取得

        if (shohin == null) {
            request.setAttribute("errorMessage", "商品が見つかりませんでした。");
            request.getRequestDispatcher("CartListServlet").forward(request, response);
            return;
        }

        int zaiko = shohin.getZaikoSuuryou();

        if (quantity > zaiko) {
            // エラーメッセージとカート画面に戻る
            request.setAttribute("errorMessage", "お届けできる数量を超えています");
            
            // カート再取得して表示に渡す
            request.setAttribute("cartList", dao.getCartList(kaiinId));
            request.getRequestDispatcher("/WEB-INF/view/cart.jsp").forward(request, response);
            return;
        }

        // 在庫数以内であれば更新
        dao.updateCartQuantity(kaiinId, shohinId, quantity);

        response.sendRedirect("CartListServlet");
    }
}
