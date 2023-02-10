
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.AccountService;
import models.User;
/**
 *
 * @author vitor
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        String username = (String) session.getAttribute("username");
        String logout = request.getParameter("logout");
        
        if (username != null && logout == null) {
            response.sendRedirect("home");
            return;
        }
                
        if (logout != null) {
            session.invalidate();
            request.setAttribute("message", "You have successfully logged out.");
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String user_name = request.getParameter("username");
        String password = request.getParameter("password");

        request.setAttribute("username", user_name);
        request.setAttribute("password", password);
        
        if (user_name.equals("") || password.equals("")) {
            request.setAttribute("message", "Blank username or password");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response);
            return;
        }
        
        User user = new AccountService().login(user_name, password);
        
        if (user == null) {
            request.setAttribute("message", "Invalid login");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response);
        }
        
        request.setAttribute("username", null);
        request.setAttribute("password", null);
        
        HttpSession session = request.getSession();
        
        session.setAttribute("username", user.getUserName());
        session.setAttribute("password", user.getPassword());
        
        response.sendRedirect("home");
        
    }   
}
