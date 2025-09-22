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

		ECsiteDAO dao = new ECsiteDAO(); // DAOクラスのインスタンス
		List<Shohin> shohinList = dao.getAllShohin(); // 商品情報を取得
	
		int CartItem = dao.getCartTotalQuantity(kaiinId);
	
	
		request.setAttribute("shohinList", shohinList);
		request.setAttribute("cartNum", CartItem);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/menu.jsp");
		dispatcher.forward(request, response);
		
}

protected void doPost(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
		
	}

}