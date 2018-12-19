### 

### 线程之Callable和Future

---

java.util.concurrent.Callable

java.util.concurrent.Future



Callable接口代表一段可以调用并返回结果的代码, 使用泛型定义它的返回值;

Future接口表示异步任务, 提供了get()方法让我们可以等待Callable的结束并获取它的结果, 具体实现用FutureTask;

Callable用于产生结果, Future用于获取结果.



Future task的执行可以用Thread, 也可以用ExecutorService.



详见: com.example.controller.ThreadDemoController
