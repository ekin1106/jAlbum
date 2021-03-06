package com.service.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractFilter implements Filter
{
    private static final Logger logger = LoggerFactory.getLogger(AbstractFilter.class);

    @Override
    public void destroy()
    {

    }

    protected abstract boolean doFilterInner(HttpServletRequest req, HttpServletResponse res)
            throws IOException;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chains)
            throws IOException, ServletException
    {
        if (!(req instanceof HttpServletRequest) || !(res instanceof HttpServletResponse))
        {
            logger.error("got an not request not http.");
            return;
        }
        HttpServletRequest httpreq = (HttpServletRequest) req;
        HttpServletResponse httpres = (HttpServletResponse) res;

        if (doFilterInner(httpreq, httpres))
        {
            chains.doFilter(req, res);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException
    {

    }


}
