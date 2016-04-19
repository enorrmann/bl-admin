package ar.gov.santafe.meduc.bladmin.configuration;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class CORSFilter implements javax.servlet.Filter {

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        if (sr1 instanceof HttpServletResponse) {
            HttpServletResponse hsr = (HttpServletResponse) sr1;
            hsr.addHeader("Access-Control-Allow-Origin", "*");
            hsr.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
            hsr.addHeader("Access-Control-Allow-Credentials", "true");
            hsr.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
            hsr.addHeader("Access-Control-Max-Age", "1209600");

        }
        fc.doFilter(sr, sr1);
    }

    @Override
    public void destroy() {
    }

}
