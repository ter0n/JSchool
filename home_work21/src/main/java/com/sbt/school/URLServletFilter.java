package com.sbt.school;

import javax.servlet.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;

public class URLServletFilter implements Filter {

    HashMap<URL, String> urlDataHash= new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        String strURL = servletRequest.getParameter("url");
        if(strURL == null){
            servletResponse.getWriter().write("Введите параметр url" + "\n");
        } else {

            URL url = new URL(strURL);
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();

            int respCode = urlCon.getResponseCode();
            if (respCode / 100 == 2) {
                if (urlDataHash.containsKey(url)) {
                    servletResponse.getWriter().write(urlDataHash.get(url) + "\n");
                } else {
                    int contentLength = urlCon.getContentLength();
                    servletResponse.getWriter().write(contentLength + "\n");
                    if (contentLength == -1) {
                        servletResponse.getWriter().write("Длина содержимого не доступна" + "\n");
                    } else {
                        InputStream inputStream = urlCon.getInputStream();

                        BufferedReader br = null;

                        String s;
                        String dateTime = new Date().toString();

                        String allData = "Response code: " + respCode + "\n" + "Date/Time: " + dateTime + "\n";
                        try {
                            br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
                            while ((s = br.readLine()) != null) {
//                            servletResponse.getWriter().write(s + "\n");
                                allData = allData.concat(s);
                            }
                            br.close();
                            urlDataHash.put(url, allData);
                        } catch (MalformedURLException e) {
//                        throw new MalformedURLException();
                            servletResponse.getWriter().write("MalformedURLException" + "\n");
                        } catch (IOException e) {
//                        throw new IOException();
                            servletResponse.getWriter().write("IOException" + "\n");
                        }

                        inputStream.close();
                    }
                }
            }
        }
        servletResponse.getWriter().write("Введите параметр url" + "\n");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
