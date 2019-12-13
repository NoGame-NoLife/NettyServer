#NettyServer
>Bean管理:    
>>类添加`@Service`,`@ServiceBean`注解  
>>获取: `bean = SystemManager.getService(bean.class)`  

>消息类型添加:
>>类添加 `@MessageUsage(usage="usageName")`  
>>系统会按`usageName`的值查找实现了`BaseDeal`接口,且名称为`"usageName"`+`"Deal"`的bean类传入消息内容进行业务处理


  





