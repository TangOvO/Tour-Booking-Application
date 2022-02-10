package AiLvYou.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(urlPatterns = "/*", initParams = {
        @WebInitParam(name = "charSet", value = "utf-8")
})
public class CharacterCodeFilter implements Filter {
    private String charSet = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String initParams = filterConfig.getInitParameter("charSet");
        charSet = initParams;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (charSet != null) {
            servletRequest.setCharacterEncoding(charSet);
            servletResponse.setCharacterEncoding(charSet);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
