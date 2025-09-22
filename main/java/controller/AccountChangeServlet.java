package controller;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;
import model.ECsiteDAO;


@WebServlet("/AccountChangeServlet")
public class AccountChangeServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    int kaiinId = (int) request.getSession().getAttribute("kaiinId");

    ECsiteDAO dao = new ECsiteDAO();
    Account account = dao.getAccountById(kaiinId);

    if (account != null) {
        request.setAttribute("account", account);
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/account_change.jsp");
    dispatcher.forward(request, response);
}

protected void doPost(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {

    request.setCharacterEncoding("UTF-8");

    // フォームの入力値を取得
    String shimei = request.getParameter("shimei");
    String password = request.getParameter("password");
    String yuubin = request.getParameter("yuubin_bangou");
    String address = request.getParameter("address");
    String denwa = request.getParameter("denwa_bangou");
    Date birth = Date.valueOf(request.getParameter("seinengappi"));
    String mail = request.getParameter("mail_address");
    String shiharai = request.getParameter("shiharaiHouhou");

    // ここでは仮にログイン済み会員IDをセッションから取得（ログイン処理と連携）
    int kaiinId = (int) request.getSession().getAttribute("kaiinId");

    ECsiteDAO dao = new ECsiteDAO();
    boolean result = dao.updateAccount(kaiinId, shimei, password, yuubin, address, denwa, birth, mail, shiharai);

    if (result) {
    	Account updatedAccount = dao.getAccountById(kaiinId);
    	request.setAttribute("account", updatedAccount); 
        request.setAttribute("successMessage", "アカウント情報を変更しました。");
    } else {
        request.setAttribute("errorMessage", "アカウント情報の変更に失敗しました。");
    }

    // 再度、変更フォームに戻る
    RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/account_change.jsp");
    dispatcher.forward(request, response);
}


}