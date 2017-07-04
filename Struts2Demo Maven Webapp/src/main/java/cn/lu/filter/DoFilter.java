package cn.lu.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.lu.action.Action;

public class DoFilter implements Filter {
	// 全局的变量
	Map<String, String> map = new HashMap<>();

	@Override
	public void destroy() {

	}

	/**
	 * 真正的处理
	 */
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// 向下转型
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		// 看一下各个路径的区别
		// 项目名
		System.out.println("getContextPath()==>" + request.getContextPath());
		// 访问的路径
		System.out.println("getServletPath()==>" + request.getServletPath());
		// 项目下面的路径
		System.out.println("getRequestURI()==>" + request.getRequestURI());
		// 带协议的完整路径
		System.out.println("getRequestURL()==>" + request.getRequestURL());
		// 使用getServletPath
		String path = request.getServletPath();
		try {
			if (path.equals("/index.jsp")) {
				arg2.doFilter(arg0, arg1);// 放行
			} else {
				Action action = (Action) Class.forName(map.get(path))
						.newInstance();
				action.execute();
				// 跳转到成功页面
				request.getRequestDispatcher("/success.jsp")
						.forward(arg0, arg1);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化操作
	 */
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// key是用户请求的路径，value是对应的类名
		map.put("/login", "cn.lu.action.LoginAction");
		map.put("/list", "cn.lu.action.ListAction");
	}

}
