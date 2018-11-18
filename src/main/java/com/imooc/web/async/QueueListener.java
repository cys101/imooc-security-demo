package com.imooc.web.async;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent>{
	@Autowired
	private MockQueue mockQueue;
	
	@Autowired
	private DeferredResultHolder deferredResultHolder;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		new Thread(() ->{
			while (true) {
				if(StringUtils.isNotBlank(mockQueue.getCompleteOrder())){
					String orderId=mockQueue.getCompleteOrder();
					deferredResultHolder.getMap().get(orderId).setResult("place order success");
					mockQueue.setCompleteOrder(null);
				}else{
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				
			}
		}).start();
		
		
	}
    

}
