package cn.clearskycpy.myrobot.filter;

import cn.clearskycpy.myrobot.common.Constants;
import cn.clearskycpy.myrobot.common.Result;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:  登录过滤器
 * @author：clearSky
 * @date: 2023/9/19
 * @Copyright： clearskycpy.cn
 */
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    /**
     * Spring提供的工具类,专门用于路径匹配
     */
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //强转
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.获取本次请求的URI
        String requestURI = request.getRequestURI();
        // 将拦截到的URI输出到日志，{}是占位符将自动填充request.getRequestURI()的内容
        log.info("拦截到请求：{}",requestURI);

        //定义不需要处理的请求
        String[] urls = new String[]{
                "/chat/user/login",
                "/chat/user/create",
                "/chat/user/sendCodeMessage",
        };

        //2.判断本次请求是否需要处理
        boolean check = check(urls, requestURI);

        //3.如果不需要处理，则直接放行
        if (check) {
            log.info("本次请求：{}，不需要处理",requestURI);
            filterChain.doFilter(request,response);
            return;
        }

        //4.判断登录状态，如果已登录，则直接放行
        if (request.getSession().getAttribute(Constants.LOGIN) != null) {
            log.info("用户已登录，id为{}",request.getSession().getAttribute("employee"));
            filterChain.doFilter(request,response);
            return;
        }

        //5.如果未登录则返回未登录结果,通过输出流方式向客户端页面响应数据,由前端负责跳转到登录页面
        log.info("用户未登录");
        // 需要导一下fastjson的坐标,将Result对象转化为Json格式的字符串
        response.getWriter().write(JSON.toJSONString(Result.error("NOT_LOGIN")));

    }

    public boolean check(String[] urls, String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                //匹配
                return true;
            }
        }
        //不匹配
        return false;
    }
}
