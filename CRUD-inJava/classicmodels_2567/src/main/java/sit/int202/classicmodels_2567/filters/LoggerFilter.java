package sit.int202.classicmodels_2567.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class LoggerFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

//        if(request.getRequestURI().startsWith("/model-")
//        || request.getRequestURI().startsWith("/images")) {
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else {
            long startTime = System.currentTimeMillis();
            filterChain.doFilter(servletRequest, servletResponse);
            long duration = System.currentTimeMillis() - startTime;
            System.out.println(request.getRequestURI()
                    + " executed in " + duration + " ms");
//        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
