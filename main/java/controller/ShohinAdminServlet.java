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
                String desc = request.getParameter("shouhinSetsumei");
                int price = Integer.parseInt(request.getParameter("kakaku"));
                int stock = Integer.parseInt(request.getParameter("zaikoSuuryou"));

                Part filePart = request.getPart("shouhinGazou");
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                
                result = dao.insertShohin(name, desc, price, stock, fileName);
                break;

            case "edit":
                int editId = Integer.parseInt(request.getParameter("shohinId"));
                String newName = request.getParameter("shouhinMei");
                String newDesc = request.getParameter("shouhinSetsumei");
                String newPriceStr = request.getParameter("kakaku");
                String newStockStr = request.getParameter("zaikoSuuryou");
                String newImage = request.getParameter("shouhinGazou");

                // 空チェックして必要に応じて更新
                Shohin updated = dao.getProductById(editId);
                if (updated != null) {
                    if (newName != null && !newName.isEmpty()) updated.setShouhinMei(newName);
                    if (newDesc != null && !newDesc.isEmpty()) updated.setShouhinSetsumei(newDesc);
                    if (newPriceStr != null && !newPriceStr.isEmpty()) updated.setKakaku(Integer.parseInt(newPriceStr));
                    if (newStockStr != null && !newStockStr.isEmpty()) updated.setZaikoSuuryou(Integer.parseInt(newStockStr));
                    if (newImage != null && !newImage.isEmpty()) updated.setShouhinGazou(newImage);

                    result = dao.updateShohin(updated);
                }
                break;

            case "delete":
                int deleteId = Integer.parseInt(request.getParameter("shohinId"));
                result = dao.deleteShohin(deleteId);
                break;

            default:
                break;
        }

        // 成功したら admin.jsp にリダイレクト
        if (result) {
            response.sendRedirect("AdminServlet");
        } else {
            request.setAttribute("error", "操作に失敗しました。");
            request.getRequestDispatcher("/WEB-INF/view/admin.jsp").forward(request, response);
        }
    }
}
