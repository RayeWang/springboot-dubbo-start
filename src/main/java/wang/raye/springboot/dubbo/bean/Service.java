package wang.raye.springboot.dubbo.bean;

import com.alibaba.dubbo.config.ServiceConfig;

/**
 * 服务配置<dubbo:service />，取代此标签的
 * 
 * @author Raye
 * @since 2016年10月19日10:03:59
 */
public class Service<T> extends ServiceConfig<T>{
	public Service(Class<T> clz,T t){
		setInterface(clz);
		setRef(t);
	}
}
