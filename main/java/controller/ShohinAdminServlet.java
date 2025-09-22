package controller;

import java.io.IOException;
import java.nio.file.Paths;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.ECsiteDAO;
import model.Shohin;

//ここからはじめる。商品カテゴリの処理から9/17

@WebServlet("/ShohinAdminServlet")
@MultipartConfig
public class ShohinAdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        ECsiteDAO dao = new ECsiteDAO();
        boolean result = false;

        switch (action) {
            case "add":
                String name = request.getParameter("shouhinMei");
                String category = request.getParameter("shouhinCategory");
                String desc = request.getParameter("shouhinSetsumei");
                int price = Integer.parseInt(request.getParameter("kakaku"));
                int stock = Integer.parseInt(request.getParameter("zaikoSuuryou"));
                
                Part filePart = request.getPart("shouhinGazou");
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                
                result = dao.insertShohin(name, category, desc, price, stock, fileName);
                break;

            case "edit":
            	String oldMei = request.getParameter("oldShouhinMei");           	
            	String newMei = request.getParameter("shouhinMei");
            	String newcategory = request.getParameter("shouhinCategory");
            	String newDesc = request.getParameter("shouhinSetsumei");
            	String newPriceStr = request.getParameter("kakaku");
            	String newStockStr = request.getParameter("zaikoSuuryou");
            	String newImage = request.getParameter("shouhinGazou");

            	Shohin shohin = new Shohin();
            	shohin.setShouhinMei(newMei);
            	shohin.setShouhinMei(newcategory);
            	shohin.setShouhinSetsumei(newDesc);
            	shohin.setKakaku(Integer.parseInt(newPriceStr));
            	shohin.setZaikoSuuryou(Integer.parseInt(newStockStr));
            	shohin.setShouhinGazou(newImage);

            	result = dao.updateShohin(shohin, oldMei);

                // 空チェックして必要に応じて更新
//                Shohin updated = dao.getProductById(editId);
//                if (updated != null) {
//                    if (newName != null && !newName.isEmpty()) updated.setShouhinMei(newName);
//                    if (newDesc != null && !newDesc.isEmpty()) updated.setShouhinSetsumei(newDesc);
//                    if (newPriceStr != null && !newPriceStr.isEmpty()) updated.setKakaku(Integer.parseInt(newPriceStr));
//                    if (newStockStr != null && !newStockStr.isEmpty()) updated.setZaikoSuuryou(Integer.parseInt(newStockStr));
//                    if (newImage != null && !newImage.isEmpty()) updated.setShouhinGazou(newImage);
//
//                    result = dao.updateShohin(updated, oldMei);
//                }
                break;

            case "delete":
            	String oldMeiDelete = request.getParameter("oldShouhinMei");
                
                result = dao.deleteShohin(oldMeiDelete);
                
                if (result) {
                    request.setAttribute("success", "商品を削除しました！");
                    
                    request.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(request, response);
                } else {
                    request.setAttribute("error", "商品削除に失敗しました。");
                    
                    request.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(request, response);
                }
                
                break;

            default:
                break;
        }

        // 成功したら admin.jsp にリダイレクト
//        if (result) {
//            response.sendRedirect("AdminServlet");
//        } else {
//            request.setAttribute("error", "操作に失敗しました。");
//            request.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(request, response);
//        }
        if (result && ("add".equals(action) || "edit".equals(action))) {
            Shohin shohin = new Shohin();
            shohin.setShouhinMei(request.getParameter("shouhinMei"));
            shohin.setCategoryName(request.getParameter("shouhinCategory"));
            shohin.setShouhinSetsumei(request.getParameter("shouhinSetsumei"));

            try {
                shohin.setKakaku(Integer.parseInt(request.getParameter("kakaku")));
                shohin.setZaikoSuuryou(Integer.parseInt(request.getParameter("zaikoSuuryou")));
            } catch (NumberFormatException e) {
                request.setAttribute("error", "失敗しました。");
                request.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(request, response);
                return;
            }

            Part part = request.getPart("shouhinGazou");
            String fileName = (part != null && part.getSubmittedFileName() != null)
                    ? Paths.get(part.getSubmittedFileName()).getFileName().toString()
                    : "";
            shohin.setShouhinGazou(fileName);

            request.setAttribute("registeredShohin", shohin);
            request.setAttribute("success", "商品を登録・変更しました！");
            request.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(request, response);
        } else if (!result) {
        	
            request.setAttribute("error", "商品登録・変更に失敗しました。");
            request.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(request, response);
        }

    }
}
