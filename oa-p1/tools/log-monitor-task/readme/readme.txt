#################################################################
#短信通道网关设置，发现报警后通过短信通知给具体的业务负责人
#################################################################
#MQ连接用户名
sms.mq.username=guest
#MQ连接密码
sms.mq.password=guest
#MQ连接虚拟目录名称
sms.mq.virtualhost=flume-test
#MQ连接主机名
sms.mq.host=192.168.10.196
#MQ连接端口
sms.mq.port=5672
#MQ连接缓存通道数量
sms.mq.channelcachesize=10

#################################################################
#数据库连接池配置
#################################################################
c3p0.maxPoolSize=200
c3p0.initialPoolSize=10
c3p0.minPoolSize=5
c3p0.acquireIncrement=1
#PisMonitor 监控数据库
db.normal.driver-url=jdbc:mysql://192.168.10.181/pis_monitor?autoReconnect=true&createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf-8
db.normal.driver-class=com.mysql.jdbc.Driver
db.normal.user=root
db.normal.password=root

#################################################################
#监控模块的启动监控时间
#################################################################
#业务日志监控预警任务
task.bizlogmonitortrackjob.cronexpression=0/5 * * * * ?



#################################################################
#自动封杀策略配置
#################################################################
#规则1: 单个IP，1分钟内10个帐号
rule1.ip.maxcount=10
#规则2: 单个IP，1小时内50个帐号
rule2.ip.maxcount=50
#规则3: 当天当同IP下登入失败次数大于等于100
rule3.ip.maxcount=100

#规则4: B段IP，1分钟内10个帐号
rule4.ip.b.maxcount=50
#规则5: C段IP，1分钟内10个帐号
rule5.ip.c.maxcount=50
