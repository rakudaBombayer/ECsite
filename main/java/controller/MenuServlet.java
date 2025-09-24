package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.ECsiteDAO;
import model.Shohin;


@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int kaiinId = (int) session.getAttribute("kaiinId");
		
		String offsetParam = request.getParameter("offset");
		int offset = 0; // デフォルト値

		if (offsetParam != null && !offsetParam.isEmpty()) {
		    offset = Integer.parseInt(offsetParam);
		}

		String limitParam = request.getParameter("limit");
		int limit = 10; // デフォルト値

		if (limitParam != null && !limitParam.isEmpty()) {
		    limit = Integer.parseInt(limitParam);
		}

		ECsiteDAO dao = new ECsiteDAO(); // DAOクラスのインスタンス
		//無限スクロールから始める。
		
		List<Shohin> shohinList = new ArrayList<>();
		
		try {
			shohinList = dao.getShohinList(offset, limit);
		} catch (SQLException e) {
		    e.printStackTrace();
		    // エラー時の処理（必要なら）
		}
		
		
		int CartItem = dao.getCartTotalQuantity(kaiinId);
		Account account = dao.getAccountById(kaiinId);
	
		request.setAttribute("shohinList", shohinList);
		request.setAttribute("cartNum", CartItem);
		session.setAttribute("loginUser", account); 
		
	    

		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/menu.jsp");
		dispatcher.forward(request, response);
		
}

protected void doPost(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
		
	}

}