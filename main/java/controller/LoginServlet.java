package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.ECsiteDAO;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
	 	
	
	
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
		dispatcher.forward(request, response);
		
}

protected void doPost(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("pass");
		
		ECsiteDAO dao = new ECsiteDAO();

	    if (dao.isValidUser(userId, password)) {
	        // ✅ ログイン成功 → アカウント情報取得
	        Account account = dao.getAccountByNameAndPassword(userId, password);

	        // ✅ セッションに kaiinId を保存
	        HttpSession session = request.getSession();
	        session.setAttribute("kaiinId", account.getKaiinId());

	        // 他にも必要があればアカウント情報をセッションに保存
	        session.setAttribute("account", account);

	        // ✅ メニュー画面へリダイレクト
	        response.sendRedirect("MenuServlet");
	    } else {
	        // ログイン失敗時の処理
	        request.setAttribute("errorMessage", "ユーザーIDまたはパスワードが違います。");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
	        dispatcher.forward(request, response);
	    }
		
	}

}