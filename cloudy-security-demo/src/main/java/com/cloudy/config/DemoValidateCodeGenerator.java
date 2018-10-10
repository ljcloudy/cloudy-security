package com.cloudy.config;

import com.cloudy.security.core.validate.code.image.ImageCode;
import com.cloudy.security.core.validate.code.ValidateCodeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.image.BufferedImage;

/**
 * Created by ljy_cloudy on 2018/10/9.
 */
//@Component("imageCodeGenerator")
public class DemoValidateCodeGenerator implements ValidateCodeGenerator {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public ImageCode generate(ServletWebRequest request) {
        logger.info("DemoValidateCodeGenerator 生效！！");
        return new ImageCode(new BufferedImage(70,23, BufferedImage.TYPE_INT_RGB),"1232",60);
    }
}
