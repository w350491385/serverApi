package com.xw.spring.jsonrpc;

import com.xw.spring.tag.JsonrpcTag;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by huangdongbin on 2018/4/10.
 */
public class JsonrpcNameSpaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("jsonrpc", new JsonrpcBeanDefinitionParser(JsonrpcTag.class,false));
    }
}
