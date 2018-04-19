package com.xw.spring.jsonrpc;

import com.xw.spring.Constant;
import com.xw.spring.tag.MethodTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Created by huangdongbin on 2018/4/10.
 */
public class JsonrpcBeanDefinitionParser implements BeanDefinitionParser {

    private static Logger logger = LoggerFactory.getLogger(JsonrpcBeanDefinitionParser.class);

    private Class<?> clazz;
    private boolean required;

    public JsonrpcBeanDefinitionParser(Class<?> clazz, boolean required) {
        super();
        this.clazz = clazz;
        this.required = required;
    }

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        return parse(element, parserContext, clazz, required);
    }

    private BeanDefinition parse(Element element, ParserContext parserContext, Class<?> clazz2, boolean required2) {
        RootBeanDefinition rootBean = new RootBeanDefinition();
        rootBean.setBeanClass(clazz);
        rootBean.setLazyInit(false);
        String id = element.getAttribute("id");
        if (id == null || "".equals(id)) {
            String name = element.getAttribute(Constant.SERVICENAME);
            if (name == null || "".equals(name)) {
                name = element.getAttribute(Constant.INTERFACECLAZZ);
            }
            id = name;
            if (parserContext.getRegistry().containsBeanDefinition(name)) {
                logger.error("parse element id = " + name + " already exsits");
            }
        }
        if (id != null && id.length() > 0) {
            parserContext.getRegistry().registerBeanDefinition(id, rootBean);
            rootBean.getPropertyValues().add(Constant.ID, id);
        }
        rootBean.getPropertyValues().add(Constant.INTERFACECLAZZ, element.getAttribute(Constant.INTERFACECLAZZ));
        String clazz = element.getAttribute(Constant.CLAZZ);
        if (clazz != null && !"".equals(clazz)) {
            RootBeanDefinition childBeanDefinition = (RootBeanDefinition) BeanDefinitionBuilder.rootBeanDefinition(clazz).getBeanDefinition();
            BeanDefinitionHolder methodBeanDefinitionHolder = new BeanDefinitionHolder(
                    childBeanDefinition, clazz);
            rootBean.getPropertyValues().add(Constant.CLAZZ, methodBeanDefinitionHolder);
        }
        rootBean.getPropertyValues().add(Constant.SERVICENAME, element.getAttribute(Constant.SERVICENAME));
        rootBean.getPropertyValues().add(Constant.CHECK, element.getAttribute(Constant.CHECK));
        String refVal = element.getAttribute(Constant.REF);
        if (refVal != null && !"".equals(refVal)) {
            BeanDefinition bean = parserContext.getRegistry().getBeanDefinition(refVal);
            if (!bean.isSingleton()) {
                logger.error(" ref bean must be singleton");
            }
            rootBean.getPropertyValues().add(Constant.REF, new RuntimeBeanReference(refVal));
        }
        //获取map 键值对数据
        ManagedMap managedMap = addmethodParameters(id,element.getChildNodes());
        if (managedMap != null)
            rootBean.getPropertyValues().addPropertyValue(Constant.METHODTAGMAP, managedMap);
        return rootBean;
    }

    private ManagedMap addmethodParameters(String id, NodeList childNodes) {
        ManagedMap parameters = new ManagedMap();
        if (childNodes != null && childNodes.getLength() > 0) {
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node node = childNodes.item(i);
                if (node instanceof Element) {
                    if (Constant.METHODTAG.equals(node.getNodeName())) {
                        RootBeanDefinition methodBeanDefinition = new RootBeanDefinition();
                        methodBeanDefinition.setBeanClass(MethodTag.class);
                        methodBeanDefinition.setLazyInit(false);
                        String method = ((Element) node).getAttribute(Constant.METHODNAME);
                        methodBeanDefinition.getPropertyValues().add(Constant.METHODNAME,method);
                        methodBeanDefinition.getPropertyValues().add(Constant.CHECK,((Element) node).getAttribute(Constant.CHECK));
                        String name = id + "." + method;
                        BeanDefinitionHolder methodBeanDefinitionHolder = new BeanDefinitionHolder(
                                methodBeanDefinition, name);
                        parameters.put(method,methodBeanDefinitionHolder);//method重名的处理策略都一样
                    }
                }
            }
        }
        return parameters;
    }
}
