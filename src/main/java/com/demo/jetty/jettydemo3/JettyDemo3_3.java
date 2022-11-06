package com.demo.jetty.jettydemo3;


import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 上一篇我们通过一个handler来完成了所有的内容，但是在实际应用中这样是不现实的，我们可能需要多个handler来共同参与处理，
 * 那么我们这个时候就可以借助于HandlerList或者HandlerCollection来添加多个Handler了。
 *
 * 用法：
 * HandlerList  list = new HandlerList();
 * list.addHandler(xxx);
 * list.addHandler(xxx);
 * list.addHandler(xxx);
 * server.setHandler(list);
 *
 * 或者
 * HandlerCollection  list = new HandlerCollection();
 * list.addHandler(xxx);
 * list.addHandler(xxx);
 * list.addHandler(xxx);
 * server.setHandler(list);
 *
 *
 *
 * 它们的区别:
 * HandlerList只要有一个Handler将请求标记为已处理，或抛出异常，Handler的调用就到此结束。
 * HandlerCollection则是不管是否抛出异常或者把请求标记为结束，都会执行到最后一个Handler。(但是还是至少需要一个handler标记为已处理)
 *
 */
public class JettyDemo3_3 {
    public static void main(String[] args) throws Exception {
        HandlerCollection collection = new HandlerCollection();
        collection.addHandler(new First1());
        collection.addHandler(new Second2());
        collection.addHandler(new Third3());
        Server server = new Server(8888);
        server.setHandler(collection);
        server.start();
        server.join();
    }
}


/**
 * Without the baseRequest.setHandled(true); The request processing will just continue onto the next handler
 * 这个handler处理完请求后由于没有setHandled(true)， 所以这个请求会被传递到下一个handler处理
 */
class First1 extends AbstractHandler {
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // 设置字符集
        response.setContentType("text/html;charset=utf-8");

        // 输出一段文本，这里为了简单，我们直接使用最原始的方式打印信息
        response.getWriter().println("调用First的handle方法.."+"<br/>");

        // 这里标记为已处理
        baseRequest.setHandled(true);
    }
}


/**
 * baseRequest.setHandled(true); is used to tell Jetty to not process any more handlers after this current one.
 * setHandled(true)告诉jetty当前handler已处理完请求，不必传到下个handler进行处理
 */
class Second2 extends AbstractHandler{
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // 输出文本信息
        response.getWriter().println("调用Second的handle方法.."+"<br/>");


    }
}


/**
 * 由于Second 处理器已经标记请求处理完成，所以请求不糊传到这个handler
 */
class Third3 extends AbstractHandler{

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.getWriter().println("调用Third的handle方法.."+"<br/>");
    }
}