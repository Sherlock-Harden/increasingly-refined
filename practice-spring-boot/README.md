# springboot源码分析

~~~java
	public SpringApplication(ResourceLoader resourceLoader, Class<?>... primarySources) {
		//配置 resource loader
    this.resourceLoader = resourceLoader;
    //判断 primarySources 是否为空
		Assert.notNull(primarySources, "PrimarySources must not be null");
		this.primarySources = new LinkedHashSet<>(Arrays.asList(primarySources));
    //判断 当前程序类型 （servlet，reactive，none）
		this.webApplicationType = WebApplicationType.deduceFromClasspath();
    //加载初始化容器对象实例
		setInitializers((Collection) getSpringFactoriesInstances(ApplicationContextInitializer.class));
    //加载监听器对象实例
		setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));
    //找到当前应用主类，并执行
		this.mainApplicationClass = deduceMainApplicationClass();
	}

~~~



~~~java
	public ConfigurableApplicationContext run(String... args) {
    //创建并启动记时监控类
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
    //初始化应用上下文
		ConfigurableApplicationContext context = null;
    //设置系统属性“java.awt.headless”的值，默认为true，用于运行headless服务器，进行简单的图像处理，多用于缺少显示屏、键盘、鼠标的系统配置，很多监控工具入jconsole需要将该值设置为true
		configureHeadlessProperty();
    //创建所有spring运行监听并发布应用启动事件，简单的话就是获取SpringApplicationRunlisterner类型的实例（Event PublishingRunListener对象），并封装进SpringApplicationRunListeners对象，然后返回这个SpringApplicationRunListeners对象，说的再简单点 getRunListeners就是准备好了运行时监听器EventPublishingRunListener。
		SpringApplicationRunListeners listeners = getRunListeners(args); //EventPublishingRunListener
		listeners.starting();
		try {
      //解析命令行参数值 （--key=value）
			ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
      //配置环境
			ConfigurableEnvironment environment = prepareEnvironment(listeners, applicationArguments);
			configureIgnoreBeanInfo(environment);
      //Banner图
			Banner printedBanner = printBanner(environment);
      //根据应用类型 配置 Application Context
			context = createApplicationContext();
      
			prepareContext(context, environment, listeners, applicationArguments, printedBanner);
			refreshContext(context);
			afterRefresh(context, applicationArguments);
			stopWatch.stop();
			if (this.logStartupInfo) {
				new StartupInfoLogger(this.mainApplicationClass).logStarted(getApplicationLog(), stopWatch);
			}
			listeners.started(context);
			callRunners(context, applicationArguments);
		}
		catch (Throwable ex) {
			handleRunFailure(context, ex, listeners);
			throw new IllegalStateException(ex);
		}

		try {
			listeners.running(context);
		}
		catch (Throwable ex) {
			handleRunFailure(context, ex, null);
			throw new IllegalStateException(ex);
		}
		return context;
	}
~~~



