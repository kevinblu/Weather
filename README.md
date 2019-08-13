# Weather
之所以写这个应用，主要是为了学习Retrofit的网络请求，然后数据请求方是聚合科技的天气请求
## 给聚合科技打一波广告（自来水）
作为网上非常容易获取到的数据，而且里面api接口很多是免费的，也有测试案例，清晰明了。
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
之后就与网上的Retrofit请求一样了，我这也不过多的讲，可以参考下面的链接。
https://blog.csdn.net/carson_ho/article/details/73732076
