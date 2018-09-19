package cn.zhangxd.svca.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.zhangxd.svca.client.ServiceBClient;

@RefreshScope
@RestController
public class ServiceAController {

	@Value("${name:unknown}")
	private String name;

	@Autowired
	EurekaDiscoveryClient discoveryClient;
	@Autowired
	private ServiceBClient serviceBClient;

	@GetMapping(value = "/test")
	public String printServiceA() {
		ServiceInstance serviceInstance = discoveryClient.getInstances("svca-service").get(0);
		return serviceInstance.getServiceId() + " (" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + ")"
				+ "===>name:" + name + "<br/>" + serviceBClient.printServiceB();
		//return "Hello World! HaHaHa";
	}

	@GetMapping(path = "/current")
	public Principal getCurrentAccount(Principal principal) {
		return principal;
	}
}