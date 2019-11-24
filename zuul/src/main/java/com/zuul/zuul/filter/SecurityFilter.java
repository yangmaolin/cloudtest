package com.zuul.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * zuul网关过滤器，通过继承ZuulFilter类来实现
 */
@Component
public class SecurityFilter extends ZuulFilter {
    @Override
    public String filterType() {
        //pre路由之前
        //routing：路由之时
        // post： 路由之后
        // error：发送错误调用
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //true表示开启拦截
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //获取Request与Response接口对象
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String securityToken = request.getParameter("token");
        if (securityToken == null) {
            ctx.setSendZuulResponse(false);
            //状态码
            ctx.setResponseStatusCode(401);
            try {
                //返回响应
                ctx.getResponse().getWriter().write("request failure , you do not have security token ");
            } catch (Exception e) {
            }
        }
        //return null 表示直接越过此Filter
        return null;
    }
}
