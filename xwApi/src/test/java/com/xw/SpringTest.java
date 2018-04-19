package com.xw;

import com.xw.api.book.BookApiService;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;

/**
 * Created by huangdongbin on 2018/4/10.
 */
public class SpringTest {
    public static  void main(String[] args){
//        ParameterNameDiscoverer pnd=new DefaultParameterNameDiscoverer();
        ParameterNameDiscoverer pnd =  new LocalVariableTableParameterNameDiscoverer();
        Method[] methods = BookApiService.class.getMethods();
        String[] parameterNames=pnd.getParameterNames(methods[0]);
        System.out.print(parameterNames);
    }
}
