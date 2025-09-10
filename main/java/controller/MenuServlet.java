package controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ECsiteDAO;
import model.Shohin;


@WebServlet("/MenuServlet")
public class MenuServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
	
		ECsiteDAO dao = new ECsiteDAO(); // DAOクラスのインスタンス
		List<Shohin> shohinList = dao.getAllShohin(); // 商品情報を取得
	
		boolean connected = dao.isConnected();
		System.out.println("DB接続状態: " + connected);
	
	
		request.setAttribute("shohinList", shohinList);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/menu.jsp");
		dispatcher.forward(request, response);
		
}

protected void doPost(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
		
	}

}