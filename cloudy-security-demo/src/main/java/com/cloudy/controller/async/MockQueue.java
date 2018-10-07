package com.cloudy.controller.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by ljy_cloudy on 2018/10/6.
 */
@Component
public class MockQueue {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String placeOrder;
    private String completeOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) {
        new Thread(
                () -> {
                    logger.info("接受到下订单请求," + placeOrder);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.completeOrder = placeOrder;
                    logger.info("处理完成到下订单请求," + placeOrder);
                }
        ).start();

    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
