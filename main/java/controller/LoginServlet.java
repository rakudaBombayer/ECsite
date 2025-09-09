package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.ECsiteDAO;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
	 // DAOインスタンスを作成
    ECsiteDAO dao = new ECsiteDAO();

    // 接続確認
    boolean connected = dao.isConnected();
    System.out.println("DB接続状態: " + connected);
	
	
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
		dispatcher.forward(request, response);
		
}

protected void doPost(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("pass");
		
		ECsiteDAO dao = new ECsiteDAO();
	    boolean isValid = dao.isValidUser(userId, password);
	    
	    
	    //ログアウトの処理はいったん終了
	    if (isValid) {
	        // 認証成功 → メニュー画面へ
	        HttpSession session = request.getSession();
	        session.setAttribute("userId", userId); // ログイン状態を保持

	        System.out.println("ログイン成功: " + userId);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/menu.jsp");
	        dispatcher.forward(request, response);
	    } else {
	        // 認証失敗 → ログイン画面に戻す
	        System.out.println("ログイン失敗: " + userId);
	        request.setAttribute("errorMessage", "ユーザーIDまたはパスワードが違います");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
	        dispatcher.forward(request, response);
	    }
		
	}

}