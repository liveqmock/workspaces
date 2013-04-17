说明
功能说明：
1.石器的人民币充值赠送；
2.石器的游戏内邮件发送；
3.石器的金币资源发送；


配置说明,把该目录下的mq.properties拷贝到/var/config/xtask-wg1/ 下面
并根据实际情况进行配置的修改；

v1.0.1 2012-04-25
-----------------------------------------------------------
新增了对于赠送充值的处理，需要在mq.properties中追加节点

#人民币充值模块配置
#交换机名称定义
wg1.rmbcznotice.exchange.name=wg1.rmbcz.exchange
#队列名称&与交换机绑定路由KEY定义
task.wg1rmbcznotice.queue.name=task.wg1rmbcznotice.queue
task.wg1rmbcznotice.queue.binding.routingkey=rmbcz

------------------------------------------------------------