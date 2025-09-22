package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.CartItem;
import model.ECsiteDAO;
import shopMail.ShopMail;

@WebServlet("/OrderCompServlet")
public class OrderCompServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int kaiinId = (int) request.getSession().getAttribute("kaiinId");
        String shimei = (String) request.getSession().getAttribute("order_shimei");
        String yuubin = (String) request.getSession().getAttribute("order_yuubin");
        String address = (String) request.getSession().getAttribute("order_address");
        String denwa = (String) request.getSession().getAttribute("order_denwa");
        String mail = (String) request.getSession().getAttribute("order_mail");
        String shiharai = (String) request.getSession().getAttribute("order_shiharai");

        ECsiteDAO dao = new ECsiteDAO();

        Timestamp now = new Timestamp(System.currentTimeMillis());
        boolean orderInserted = dao.insertOrderCustomer(kaiinId, shimei, yuubin, address, denwa, mail, shiharai, now);

        if (!orderInserted) {
            request.setAttribute("errorMessage", "注文情報の登録に失敗しました。");
            request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
            return;
        }

        List<CartItem> cartList = dao.getCartList(kaiinId);
        boolean historySuccess = true;
        for (CartItem item : cartList) {
            if (!dao.insertOrderHistory(kaiinId, item.getShohinId(), item.getQuantity(), now)) {
                historySuccess = false;
                break;
            }
        }

        if (!historySuccess) {
            request.setAttribute("errorMessage", "注文履歴の登録に失敗しました。");
            request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
            return;
        }

        if (!dao.clearCart(kaiinId)) {
            request.setAttribute("errorMessage", "カートのクリアに失敗しました。");
            request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
            return;
        }

        // ✅ メール本文作成と送信
        StringBuilder mailBody = new StringBuilder();
        mailBody.append(shimei).append(" 様\n\n")
                .append("このたびはご注文ありがとうございます。\n")
                .append("以下の内容でご注文を承りました。\n\n");

        int total = 0;
        for (CartItem item : cartList) {
            int subtotal = item.getKakaku() * item.getQuantity();
            total += subtotal;
            mailBody.append(String.format("%s - %d円 × %d = %d円\n",
                    item.getShohinMei(), item.getKakaku(), item.getQuantity(), subtotal));
        }
        mailBody.append("\n合計金額: ").append(total).append(" 円\n")
                .append("またのご利用をお待ちしております。");

        try {
            ShopMail.send(1, mail, "ECサイト", mailBody.toString(), "ご注文ありがとうございます", 0);
            System.out.println("注文確認メールを送信しました。");
        } catch (Exception e) {
            System.out.println("メール送信失敗: " + e.getMessage());
            e.printStackTrace();
        }

        // ✅ 完了画面へフォワード
        request.setAttribute("cartList", cartList); // 購入商品一覧
        request.setAttribute("totalPrice", total);  // 合計金額
        request.setAttribute("shimei", shimei);     // 氏名など
        request.setAttribute("orderTime", now);     // 注文日時
        request.getRequestDispatcher("/WEB-INF/view/order_comp.jsp").forward(request, response);
    }
}
