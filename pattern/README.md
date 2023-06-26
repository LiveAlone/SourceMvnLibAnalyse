## java Pattern Design 设计模式

1 抽象文档的设计方式（Abstract Document Design）
> 统一管理配置信息。抽象文档实现不同的接口来适配不同配类型转换。

2 抽象工厂
> 抽象一个工厂对象，创建一系列组件。不用关心具体工厂类型，实现组件类型。

3 Adapter
> 适配器模式，适配不同系统接口调用转换。完成不同系统，新老系统接口适配。

3 异步方法执行模式 async-method-invocation
> 调用线程不会阻塞， 处理多个独立的Task, 检索执行的结果。 指导所有的任务都完成。
> 异步多线程 监听对应的返回结果。

4 balking Pattern
> 运行object 状态不稳定, 通过State限定对象操作行为。 (!! State 注意并发)
