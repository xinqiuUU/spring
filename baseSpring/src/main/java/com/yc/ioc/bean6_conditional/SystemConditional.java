package com.yc.ioc.bean6_conditional;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class SystemConditional implements Condition {

    private Logger logger = Logger.getLogger(SystemConditional.class);
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        String userhome = env.getProperty("user.name");
        String userdir = env.getProperty("user.dir");
        String osname = env.getProperty("os.name");
        String javahome = env.getProperty("java.home");

        logger.info( userhome + "\t"+userdir + "\t"+osname + "\t"+javahome);
        if ( osname.contains("Windows")){
            return true;
        }else {
            return false;
        }


    }
}
