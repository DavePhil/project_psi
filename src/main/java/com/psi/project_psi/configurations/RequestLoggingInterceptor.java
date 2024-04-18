package com.psi.project_psi.configurations;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import java.io.UnsupportedEncodingException;

public class RequestLoggingInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger("File");
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("Request URL: {} - Method: {} - Response Status: {}", request.getRequestURL(), request.getMethod(), response.getStatus());

        return true;
    }

//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        // Enregistrer les informations de la réponse
//        if (response != null) {
//            int status = response.getStatus();
//            logger.info("Response Status: {}", status);
//            String responseBody = getResponseBody(response);
//            logger.info("Response Body: {}", responseBody);
//        }
//    }
//
//    private String getResponseBody(HttpServletResponse response) {
//        ContentCachingResponseWrapper wrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
//        if (wrapper != null && wrapper.getContentSize() > 0) {
//            byte[] buf = wrapper.getContentAsByteArray();
//            if (buf.length > 0) {
//                try {
//                    return new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
//                } catch (UnsupportedEncodingException e) {
//                    logger.error("Error reading response body", e);
//                }
//            }
//        }
//        return null;
//    }

}
