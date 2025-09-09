package controller;

import java.io.IOException;
import java.sql.Date;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ECsiteDAO;


@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;

protected void doGet(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/register.jsp");
		dispatcher.forward(request, response);
		
}

protected void doPost(HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException, IOException {
	
	 System.out.println("doPostが呼ばれました");
	 request.setCharacterEncoding("UTF-8");

     // フォームから値を取得
     String shimei = request.getParameter("shimei");
     String password = request.getParameter("password");
     String yuubin = request.getParameter("yuubin_bangou");
     String address = request.getParameter("address");
     String denwa = request.getParameter("denwa_bangou");
//     String birth = request.getParameter("seinengappi");
     Date birth = Date.valueOf(request.getParameter("seinengappi"));
     String mail = request.getParameter("mail_address");
     String shiharai = request.getParameter("shiharai_houhou");
     
  // DAOを使って登録処理
     ECsiteDAO dao = new ECsiteDAO();
     boolean result = dao.insertAccount(shimei, password, yuubin, address, denwa, birth, mail, shiharai);
	
     if (result) {
         // 登録成功 → 完了画面へ
    	 request.setAttribute("successMessage", "登録に成功しました。");
         RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
         dispatcher.forward(request, response);
     } else {
         // 登録失敗 → エラー画面へ
         request.setAttribute("errorMessage", "登録に失敗しました");
         RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/login.jsp");
         dispatcher.forward(request, response);
     }
}

}