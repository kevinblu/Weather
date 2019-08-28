# Weather
之所以写这个应用，主要是为了学习Retrofit的网络请求，然后数据请求方是聚合科技的天气请求

## 里面主要的几个类：
一个是我的MainActivity，一个是Retrofit请求的接口WeatherService，一个是我的Javabean类Weather，里面解析了聚合科技的天气格式。
剩下一个是我使用RecyclerView的adapter类，里面把未来天气进行了一个布局。
## Json解析
首先是生成对应的Json解析的bean类，这里使用了一个名叫 “GsonFormat”的插件，它可以根据一条json数据快速帮你生成所需要的Javabean对象。
使用步骤：安装插件，重启IDE，新建一个Javabean类，然后右键->Generate->GsonFormat->将一条json数据拷贝至这时候弹出的框，
点击ok，就自动生成了一个符合该json数据格式的Javabean类。

Gson解析，这个是用的retrofit经常配套使用的一个依赖库，使用也非常简单。

```
Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("xx.xxx.xx.xxx/") //设置网络请求的Url地址，记住，后面一定是以斜杠为终止的
                .addConverterFactory(GsonConverterFactory.create()) //设置数据解析器
                .build();
```

25日更新
今天发现这个应用在模拟器上运行ok，但是在手机上却一直没反应，经过调查分析，发现这个问题应该应该有一部分是因为URL的中文解析问题，这里的中文解析默认是utf-8的，但我在代码中使用URLEncoder.encode(str,"utf-8")时，原先在模拟器上可以请求网络的app，反而请求失败了，原因是因为不支持该地名。
这里问题就来了，在网页请求中，中文的编码默认是utf-8，我提前将中文以utf-8的格式编码，并以get请求发出去，返回值竟然说不支持该地名，是因为我提前给它编码了，所有对方服务器不会再次对其解码么？
这个问题待解决
28日更新
我是个傻叉，我忘记了从Android P版本开始，就必须使用https协议访问网络，模拟器的版本还是22，可以直接访问，但我的测试真机却是去年才买的，Android版本都已经到9了，当然不能直接通过http访问，于是，困了我几天的问题，竟然是一个's'。。。。
