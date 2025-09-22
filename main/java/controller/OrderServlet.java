package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ECsiteDAO;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int kaiinId = (int) request.getSession().getAttribute("kaiinId");

        ECsiteDAO dao = new ECsiteDAO();
        model.Account account = dao.getAccountById(kaiinId);

        if (account != null) {
            request.setAttribute("account", account);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/order.jsp");
        dispatcher.forward(request, response);
    }

 // OrderServlet.java (修正後)

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // 入力値を取得
        String shimei = request.getParameter("shimei");
        String yuubin = request.getParameter("yuubin_bangou");
        String address = request.getParameter("address");
        String denwa = request.getParameter("denwa_bangou");
        String mail = request.getParameter("mail_address");
        String shiharai = request.getParameter("shiharaiHouhou");

        // 注文者情報をセッションに一時保存（OrderDataなどでまとめると良いが今回はそのまま）
        request.getSession().setAttribute("order_shimei", shimei);
        request.getSession().setAttribute("order_yuubin", yuubin);
        request.getSession().setAttribute("order_address", address);
        request.getSession().setAttribute("order_denwa", denwa);
        request.getSession().setAttribute("order_mail", mail);
        request.getSession().setAttribute("order_shiharai", shiharai);

        // care.jsp にリダイレクト
        response.sendRedirect("CareServlet");
    }

}
