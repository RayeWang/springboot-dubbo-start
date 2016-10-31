package wang.raye.springboot.dubbo.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
/**
 * 读取Dubbo在application.yml中的配置
 * @author Raye
 * @since 2016年10月25日09:07:16
 */
@Component
@ConfigurationProperties(prefix = "dubbo")
public class DubboConfig {
	private ApplicationConfig application;
	private RegistryConfig register;
	private ProtocolConfig protocol;
	private ProviderConfig provider;
	public ApplicationConfig getApplication() {
		return application;
	}
	public void setApplication(ApplicationConfig application) {
		this.application = application;
	}
	public RegistryConfig getRegister() {
		return register;
	}
	public void setRegister(RegistryConfig register) {
		this.register = register;
	}
	public ProtocolConfig getProtocol() {
		return protocol;
	}
	public void setProtocol(ProtocolConfig protocol) {
		this.protocol = protocol;
	}
	public ProviderConfig getProvider() {
		if(protocol ==  null){
			protocol = new ProtocolConfig();
			protocol.setPort(20880);
		}
		return provider;
	}
	public void setProvider(ProviderConfig provider) {
		this.provider = provider;
	}
	
	
}
