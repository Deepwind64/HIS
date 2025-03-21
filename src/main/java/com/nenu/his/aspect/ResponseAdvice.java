package com.nenu.his.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nenu.his.common.result.Response;
import jakarta.annotation.Resource;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author deepwind
 */
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    /**
     * 返回对象处理器，自动包装为 Response 对象
     */
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType){
        // 添加不处理情况，false为不处理
        return true;
    }

//    @SneakyThrows(com.fasterxml.jackson.core.JsonProcessingException.class)
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<?
            extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof String) {
            // 由于字符串会被直接返回，需要手动转换为 json
            try {
                return objectMapper.writeValueAsString(Response.success(body));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        } else if (body instanceof Response) {
            // 如果被异常类封装过就不要再次封装
            return body;
        } else if (body instanceof org.springframework.core.io.Resource) {
            return body;
        } else {
            return Response.success(body);
        }
    }
}