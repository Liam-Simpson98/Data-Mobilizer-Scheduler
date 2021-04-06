package servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mailUtility.JavaMailUtility;

/**
 *
 * @author Liam
 */
public class RunPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/WEB-INF/RunPage.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        
        String email = request.getParameter("email");
        
        try {

            JavaMailUtility.sendMail(email);

        } catch (MessagingException ex) {
            
            request.setAttribute("buttonPress", "Mail Failed to Send!");

            Logger.getLogger(RunPageServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("buttonPress", "Mail Sent!");

        getServletContext().getRequestDispatcher("/WEB-INF/RunPage.jsp").forward(request, response);
    }
}
