package com.demo.IO.net_io.jetty.websocket.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;


/**    WebSocketServletFactory来自于<artifactId>websocket-servlet</artifactId，
 *     Basic WebSocketServletFactory for working with Jetty-based WebSocketServlets
 *        <dependency>
 *             <groupId>org.eclipse.jetty.websocket</groupId>
 *             <artifactId>websocket-servlet</artifactId>
 *             <version>9.4.19.v20190610</version>
 *         </dependency>
 *
 *
 *
 */
public class StartServer {
    public static void main(String args[])
    {
        WebSocketHandler handler = new WebSocketHandler() {
            @Override
            public void configure(WebSocketServletFactory webSocketServletFactory) {
                webSocketServletFactory.getPolicy().setIdleTimeout(10L * 60L * 1000L);
                webSocketServletFactory.getPolicy().setAsyncWriteTimeout(10L * 1000L);
                //When you want a custom WebSocketCreator, use WebSocketServletFactory.setCreator(WebSocketCreator creator)
                // and the WebSocketServletFactory will use your creator for all incoming Upgrade requests on this servlet.
                webSocketServletFactory.setCreator(new MyAdvancedEchoCreator());
            }
        };


        ContextHandler context = new ContextHandler();
        context.setContextPath("/test/");   //ws://127.0.0.1:7778/test/
        context.setHandler(handler);

        Server server = new Server(7778);
        server.setHandler(context);
        try
        {
            /* 启动服务端 */
            server.start();
            server.join();
        }
        catch (Exception e)
        {

            e.printStackTrace();
        }

    }
}
