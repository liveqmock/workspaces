package com.iwgame.iwcloud.baidu.task.version;

import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 类说明
 * 
 * @简述： 抽象的版本配置类,用于配置QName和服务地址
 * @作者： jjwu
 * @版本： 1.0
 * @邮箱： wujunjie@iwgame.com
 * @修改时间：2012-6-27 下午05:53:25
 */
public abstract class Version {
    protected static final Log log = LogFactory.getLog(Version.class);

    private static Map<String, Version> verions = new HashMap<String, Version>();

    public abstract <T> QName getQName(Class<T> cls);

    public abstract <T> String getAddr(Class<T> cls);

    public abstract String getVersion();

    public abstract String getHeaderNameSpace();

    protected static void addVersion(Version v) {
        verions.put(v.getVersion().toUpperCase(), v);
    }

    public static Version getVersion(String key) {
        return verions.get(key.toUpperCase());
    }

    protected Version() {
        Version.addVersion(this);
        log.info("Version [" + this.getVersion() + "] has been loaded.");
    }
}
