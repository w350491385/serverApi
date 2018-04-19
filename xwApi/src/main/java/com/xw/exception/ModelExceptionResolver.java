package com.xw.exception;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.googlecode.jsonrpc4j.ErrorResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

public class ModelExceptionResolver implements ErrorResolver {

    Logger log = LoggerFactory.getLogger(ModelExceptionResolver.class);

    public static final ModelExceptionResolver INSTANCE = new ModelExceptionResolver();

    private ModelExceptionResolver() {
    }

    @Override
    public JsonError resolveError(Throwable t, Method method, List<JsonNode> arguments) {
        log.error("---new---用户端接口调用错误----------", t);
        if (t instanceof BaseException) {
            BaseException baseException = (BaseException) t;
            return new JsonError(baseException.getCode(), baseException.getMessage(), baseException.getStackTrace());
        }

        if (t instanceof SQLException) {
            SQLException me = (SQLException) t;
            return new JsonError(-31996, me.getMessage(), me.getStackTrace());
        }

        if (t instanceof IllegalArgumentException) { // 处理非法参数
            return new JsonError(-32602, t.getMessage(), t.getStackTrace());
        }

        if (t instanceof InvalidFormatException) { // 处理 Jsonrpc4j 遇到非法参数的情况, 比如：需要传入整数而传入了字符串
            return new JsonError(-32602, t.getMessage(), t.getStackTrace());
        }

        if (t instanceof Exception) { // 其它所有的 RuntimeException 返回： -32603（Internal error）
            return new JsonError(-32603, t.getMessage(), t.getStackTrace());
        }

        return null;
    }
}
