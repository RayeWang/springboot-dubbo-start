package wang.raye.springboot.dubbo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;

import wang.raye.springboot.dubbo.bean.DubboConfig;
import wang.raye.springboot.dubbo.bean.Service;


/**
 * Dubbo的配置类，会从application.yml中读取application和register的相关信息
 * 服务提供者需要重写services方法，服务消费者需要重写references方法
 * 
 * @author Raye
 * @since 2016年10月19日17:33:23
 */
public class DubboAutoConfig {

	@Autowired
	private DubboConfig config;

	@Bean
	public ApplicationConfig getApplication() {
		if (config.getApplication() == null) {
			config.setApplication(new ApplicationConfig());
			config.getApplication().setName("dubbo-start");
			
		}
		return config.getApplication();
	}

//	public RegistryConfig getRegister() {
//		if (register == null) {
//			register = new RegistryConfig("zookeeper://127.0.0.1:2181");
//		}
//		List<Service> services = services();
//		if (services != null && !services.isEmpty()) {
//			for (Service service : services) {
//				service.setRegistry(register);
//				service.setApplication(getApplication());
//				service.setProvider(getProvider());
//				service.export();
//			}
//		}
//		return register;
//	}
	@Bean
	public RegistryConfig getRegister() {
		if (config.getRegister() == null) {
			config.setRegister(new RegistryConfig("zookeeper://127.0.0.1:2181"));
		}
		List<Service> services = services();
		if (services != null && !services.isEmpty()) {
			for (Service service : services) {
				service.setRegistry(config.getRegister());
				service.setApplication(getApplication());
				service.setProvider(config.getProvider());
				service.export();
			}
		}
		return config.getRegister();
	}

	/**
	 * 获取远程调用对象 替代xml <dubbo:reference  />  节点
	 * @param <T>
	 * @param interfaceClass 接口类 interface="xxx"
	 * @param id 引用id id="xxx"
	 * @return 远程对象
	 */
	public <T> T getReference(Class<T> interfaceClass,String id){
		ReferenceConfig<T> ref = new ReferenceConfig<T>();
		ref.setRegistry(getRegister());
		ref.setApplication(getApplication());
		ref.setInterface(interfaceClass);
		ref.setId(id);
		return ref.get();
	}
	/**
	 * 获取需要发布的服务
	 * 
	 * @return
	 */
	public List<Service> services() {
		return null;
	}

	
}
