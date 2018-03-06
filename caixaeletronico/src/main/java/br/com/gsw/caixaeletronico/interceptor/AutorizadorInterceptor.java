package br.com.gsw.caixaeletronico.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizadorInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller)
	throws Exception {
		
		String uri = request.getRequestURI();
		if(uri.endsWith("login") || uri.endsWith("efetuaLogin")) {
			return true;
		}
		
		if(request.getSession().getAttribute("clienteLogado")!=null) {
			return true;
		} else {
			response.sendRedirect("login");
			return false;
		}
		
	}
}
