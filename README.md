
1.系统技术  

    1.jooq + spring + jsonrpc + maven + quartz + c3p0 + redis + solr + activemq
	
	2.系统带有数据库文件，在目录xwCore\src\main\resources\db\migration下，可以直接运行

2.整体项目结构分三层：

    1.web api服务对外
	
        1).Api层 向外提供服务接口,使用自定义的jsonrpc标签进行服务配置表示控制层 jsonrpc 对是否登录进行验证
		
        2).server层处理系统业务，并且可以开启事物，详见事物规则 使用spring的@Service注解来标记业务层
		
        3).dao层jooq操作数据库 使用spring的@Repository注解来标记持久层
		
    2.task任务
	
        1).定时任务，系统启动时，连接数据库进行任务加载以及初始化
		
        2).server层处理定时任务业务,并且可以开启事物，详见事物规则 使用spring的@Service注解来标记业务层
		
        3).dao层jooq操作数据库 使用spring的@Repository注解来标记持久层
				
    3.xwPayBack支付回调
	
        1).回调支付服务必须继承com.pay.DefaultServiceProcessor
		
        2).服务名称就是bean id
           <bean id="defaultServiceProcessor" class="com.pay.DefaultServiceProcessor"/>

3.maven命令

1).使用如下命令可以进行根据不同的环境打包

    1.develop 开发环境 (默认)
	
    2.test 测试环境
	
    3.product 生产环境

    mvn clean package -Dmaven.test.skip -Pdevelop

2).使用如下命令，进行sql生成到数据库

  1.mvn flyway:migrate 按sql生成数据库表
  
  2.mvn flyway:clean 清除数据库表
  
3).使用如下命令 按数据库表生成java代码

    mvn jooq-codegen:generate

4.标签使用原则

    1).jsonrpc配置
	
        <bean id="bookApiService" class="com.xw.api.book.BookApiService"/>
        <xw:jsonrpc serviceName="book" ref="bookApiService" check="true">
            <xw:method methodName="add" check="true"/>
        </xw:jsonrpc>
		
        或者
		
        <xw:jsonrpc serviceName="book" clazz="com.xw.api.book.BookApiService" check="true">
            <xw:method methodName="add" check="true"/>
        </xw:jsonrpc>

        ref和clazz 必须配置一个，否则报错，当俩个都存在时优先使用ref,配置clazz时spring生成的对象全是默认参数,如果需要自定义spring对象生成参数就可以使用ref结合bean标签使用

        serviceName：服务名称,ref:引用的服务 check:检查session是否登录存在(参数名称：sessionId,session,seissionid),默认情况下当前服务都需要检查session登录情况
        method:针对当前服务下的 methodName 进行配置是否检查session登录,只针对方法名，不针对方法名相同，参数不同的配置

2).当需要自定义异常处理器市,需要配置下面的标，并且和异常自定义的异常处理器一起使用

    <bean id="jsonRpcMultiServerFactory" class="com.xw.spring.factory.JsonRpcMultiServerFactory"/>
	
    <bean id="modelExceptionResolver" class="com.xw.exception.ModelExceptionResolver"/><!-- 实现com.googlecode.jsonrpc4j.ErrorResolver接口的bean -->

3).当需要验证session是否已登录的情况,需要如下配置：

    例如：
	
    <bean id="applicationContextUtils" class="com.utils.spring.ApplicationContextUtils"/>
	
    <bean id="checkPowerFilter" class="com.xw.common.CheckPowerFilter"/><!-- 实现com.xw.verification.PermissionAuthFilter接口的bean -->
4).任务初始化标签

    <!-- 任务初始化容器 -->
	
    <bean class="com.xw.task.init.TaskInitContainer"/>
	
    <!--  applicationContext 配置 -->
	
    <bean class="com.utils.spring.ApplicationContextUtils"/>

5.quartz任务使用

    1）.所有任务必须继承com.xw.task.task.AbstractTask
	
    2).任务里面可以使用spring的注解进行对象注入
	
    3).实现com.xw.task.server.TaskDataService(此接口属于系统接口,不做任务业务实现)的接口对系统的任务进行获取
	
    4).任务可以在task_data表里面修改，name,groupName,triggerName字段不能修改,当task记录修改之后，必须更新updateTime时间，现在只支持corn,status修改后任务变更 status:0:正常运行;1:删除;2:停止运行
	
    5).支持第项功能，必须在spring中配置com.xw.task.common.TaskChangeListener对象,默认延迟120秒执行，之后每个30秒查询一次数据库task_data表中的数据变化，也可配置initialDelay,delay属性
	
    例如：
	
        <bean class="com.xw.task.common.TaskChangeListener">
            <property name="initialDelay" value="100"/>
            <property name="delay" value="20"/>
        </bean>

6.请求报文格式：

 例如：
 
    1.进行sessionId验证：
	
    {"jsonrpc":"2.0","method":"book_add","params":{"sessionid":"XWKJxwkj1234","userId":2018,"type":1,"desc":"测试"},"id":753428846}
	
    {"jsonrpc":"2.0","method":"book_add","params":{"session":"XWKJxwkj1234","userId":2018,"type":1,"desc":"测试"},"id":753428846}
	
    {"jsonrpc":"2.0","method":"book_add","params":{"sessionId":"XWKJxwkj1234","userId":2018,"type":1,"desc":"测试"},"id":753428846}
	
    2.不进行session验证
	
    {"jsonrpc":"2.0","method":"book_add","params":{"userId":2018,"type":1,"desc":"测试"},"id":753428846}
	

    Api层服务接口定义如下：不需要加入任何注解
	
     public int add(int userId,int type, String desc){
	 
         return 0;
		 
     }

7.变量命名规则

 1).数据库字段都必须是用小写，多个单词用_隔开
 
 2).bean 对象属性必须使用驼峰式命名
 
 3).数据库表与bean对象属性复制时，相对应的类型需相同或者 数据库表字段类型是bean对象属性的同类或者子类.（ BeanUtils.copyProperties(source,target)）

7.项目模块介绍

1).xwCommon:公共工具类库

2).xwApi:服务接口暴露 ,业务实现(war)

3).xwConfig:系统配置

4).xwTask:系统任务,任务处理(war)

5).xwCore:按sql生成数据库表,按数据库表生成java类的文件，以及业务代码编写

6).xwSolr:搜索模块

7).xwMq:消息队列模块

8).xwPayBack:支付回调模块(war)