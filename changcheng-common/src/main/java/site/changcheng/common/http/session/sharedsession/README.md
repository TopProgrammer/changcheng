共享session
===========================

# 共享session

将session序列化到共享存储中，每次接到请求时，从共享存储中抓取session，反序列化为本地session。

# 配置

在web.xml中配置过滤器MemcachedSessionFilter（注意filter的配置顺序，确保在使用session之前，经过过滤器）。

## 共享session过滤器的的初始化参数

共享session的过滤器均继承自[SharedSessionFilter类][1]，共有的初始化参数包括：

* sharedSessionTimeout: 用于控制session的有效期，单位为秒，默认值为30分钟。
* sharedSessionCacheKeyPrefix: 用于设置memcached中session对象key的前缀，默认值为"session_"
* sharedSessionIdCookieName: 指定用于记录sessionId的cookie的名字，默认值为"sessId"
* sharedSessionIdCookieSecure: 是否设置session cookie的Secure属性，默认为true
* sharedSessionIdCookieHttpOnly: 是否设置session cookie的HttpOnly属性，默认为true
* sharedSessionIdCookieDomain: 设置session cookie的domain属性，默认为当前网站域名
* sharedSessionIdCookiePath: 设置session cookie的path属性，默认为"/"

## 基于memcached的共享session过滤器的初始化参数

使用memcached存储共享session数据。

### MemcachedSessionFilter

基于Memcached的共享session。当session中包含有属性时，会在请求结束后，将数据写入到memcached中。

可以使用以下初始化参数：

* sharedSessionMemcachedClientBeanId: 配置于spring中的MemCachedClient对象的bean的id，默认为"memcachedClient"

# 限制

* 不支持SessionContext
* 监听session属性的变动，不支持HttpSessionAttributeListener

# TODO

* 使用session的时候才从共享存储中获取session
* 实现对session属性变动的监听
* 调试日志还没有实现




[1]:    ./SharedSessionFilter.java