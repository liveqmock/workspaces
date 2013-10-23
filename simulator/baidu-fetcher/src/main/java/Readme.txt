====================当前版本信息====================
当前版本：V1.0.2

发布日期：2013-08-05

====================================================

====================修改历史====================

V1.0.1  2013-07-31  修改CommercialClient初始化方法，用户名、密码、token不在放置于配置文件中，方便个性化订制。
V1.0.2  添加mcc token支持，新增target字段

================================================

发布介绍:

此客户端库,目前支持百度商业推广API V3版本功能，以及部分百度商业推广API V2版本功能。所有的客户端请求代理类请参考java-docs目录下的
文档，本客户端库的使用请参考样例工程CommercialClientDemo。

本客户端库旨在提供百度商业推广API以Json方式调用封装以及代理类生成，并不提供客户端业务逻辑。

使用过程中如果遇到任何问题，请联系apihelp@baidu.com并在标题注明《Baidu API Client V3使用过程问题求助》

注意事项:


1. 如何初始化CommercialClient
	使用者需要new一个CommercialClient对象，形如new CommercialClient("Username","Password","Token","Service Name", "Method Name")。针对V2和V3都有的Service，调用方式一样。
	格式参考CommercialClient client = new CommercialClient("your username","your password","your token","AdgroupService", "getAllAdgroupId");

2. 如何调用API进行请求？
	使用CommercialClient对象的execute方法，参数为要请求方法的Request类，如
	CommercialClient client = new CommercialClient("your username","your password","your token","AdgroupService", "getAllAdgroupId");
	GetAllAdgroupIdRequest req = new GetAllAdgroupIdRequest();
	JsonEnvelop<ResHeader, ?> env = client.execute(req);

3. 如何取得调用结果 ?
	使用JsonEnvelop<ResHeader, ?>对象来获得请求结果，如
	CommercialClient client = new CommercialClient("your username","your password","your token","AdgroupService", "getAllAdgroupId");
	GetAllAdgroupIdRequest req = new GetAllAdgroupIdRequest()
	JsonEnvelop<ResHeader, ?> env = client.execute(req);
	ResHeader header = env.getHeader();
	GetAllAdgroupIdResponse res = (GetAllAdgroupIdResponse) env.getBody();
	ResHeader中记录本次请求的状态，如失败，失败信息记录在failures属性中，Failure信息参见java-docs com.baidu.api.core.Failure描述。
	GetAllAdgroupIdResponse记录了本次请求返回的数据。
4. 本客户端库的运行环境
	请将lib子目录所有的jar包、CommercialClient-1.0.1.jar放置于您的classpath中，请在jdk1.5及以上环境下使用本客户端。
	
