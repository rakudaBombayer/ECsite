package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ECsiteDAO;
import model.Shohin;


@WebServlet("/ShohinDetailServlet")
public class ShohinDetailServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
		// パラメータから商品IDを取得（文字列として）
    	String idParam = request.getParameter("shohin_id");
	
    	int shohinId = Integer.parseInt(idParam);
    	
		ECsiteDAO sho = new ECsiteDAO(); 
		Shohin shohin = sho.getProductById(shohinId);
		
		request.setAttribute("shohin", shohin);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/shohin_detail.jsp");
		dispatcher.forward(request, response);
		
}

protected void doPost(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
		
		
	}

}