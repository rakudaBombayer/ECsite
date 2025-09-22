package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import shopMail.ShopMail; 

@WebServlet("/SendMailServlet")
public class SendMailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // JSP からの入力を受け取る
        String to = request.getParameter("to");
        String shopName = request.getParameter("shopName");
        String message = request.getParameter("message");
        String subject = request.getParameter("subject");

        try {
            // ShopMail のクラスメソッド呼び出し
            // 引数: int, String, String, String, String, int
            ShopMail.send(
                1,            // 固定値
                to,           // 宛先アドレス
                shopName,     // ショップ名
                message,      // 本文
                subject,      // 件名
                0             // 固定値
            );

            request.setAttribute("result", "メール送信成功しました！");
        } catch (Exception e) {
            request.setAttribute("result", "送信失敗: " + e.getMessage());
            e.printStackTrace();
        }

        // JSP に処理を渡す
        request.getRequestDispatcher("/WEB-INF/view/result.jsp").forward(request, response);
    }
}