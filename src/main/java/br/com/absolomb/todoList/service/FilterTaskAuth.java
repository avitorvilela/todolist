package br.com.absolomb.todoList.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.absolomb.todoList.repository.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var servletPath = request.getServletPath();

        if(servletPath.startsWith("/tasks/")) {
            // user and password
            var authorization = request.getHeader("Authorization");

            var authEncoded = authorization.substring("Basic".length()).trim();

            byte[] authDecoded = Base64.getDecoder().decode(authEncoded);

            var authDecodedToString = new String(authDecoded);

            String[] credentials = authDecodedToString.split(":");

            String username = credentials[0];
            String password = credentials[1];

            System.out.println("\n- Authorization ");
            System.out.println("- Username: " + username);
            System.out.println("- Password: " + password);

            // validate user
            var user = this.iUserRepository.findByUsername(username);
            if (user == null) {
                response.sendError(401);
            } else {
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passwordVerify.verified) {
                    request.setAttribute("idUser", user.getId());
                } else {
                    response.sendError(401);
                }
            }

            filterChain.doFilter(request, response);

        }else{
            filterChain.doFilter(request, response);
        }
    }
}
