连接池设计
==========

项目使用的连接池是[Apache Commons Pool](http://commons.apache.org/proper/commons-pool/index.html)

##Apache Commons Pool接口介绍
Apache Commons Pool三个关键接口实现是ObjectPool，PooledObjectFactory和PooledObject。

###接口职责定义

* ObjectPool定义了对象池要实现的功能

* PooledObjectFactory定义了被池化的对象的创建，初始化，激活，钝化以及销毁功能

* PooledObject定义了被池化对象的一些附加信息(创建时间，池中状态)

流程：PooledObjectFactory创建的对象经过PooledObject的包装然后放到ObjectPool里面来

####ObjectPool接口定义

	//从池中获取对象
	T borrowObject() throws Exception, NoSuchElementException,IllegalStateException;
	//将对象放回池中
	void returnObject(T obj) throws Exception;
	//废弃对象
	void invalidateObject(T obj) throws Exception;
	//添加对象
	void addObject() throws Exception, IllegalStateException,UnsupportedOperationException;
	//获取对象个数
	int getNumIdle();
	//获取活跃对象个数
	int getNumActive();
	//清除池，池可用
	void clear() throws Exception, UnsupportedOperationException;
	//关闭池，池不可用
	void close();

####PooledObjectFactory接口定义

	//创建一个新对象;当对象池中的对象个数不足时,将会使用此方法来"输出"一个新的"对象",并交付给对象池管理
    //@return a {@code PooledObject} wrapping an instance that can be served by the pool
	PooledObject<T> makeObject() throws Exception;
	//销毁对象,如果对象池中检测到某个"对象"idle的时间超时,或者操作者向对象池"归还对象"时检测到"对象"已经无效,那么此时将会导致"对象销毁";"销毁对象"的操作设计相差甚远,但是必须明确:当调用此方法时,"对象"的生命周期必须结束.如果object是线程,那么此时线程必须退出;如果object是socket操作,那么此时socket必须关闭;如果object是文件流操作,那么此时"数据flush"且正常关闭.
	void destroyObject(PooledObject<T> p) throws Exception;
	//检测对象是否"有效";Pool中不能保存无效的"对象",因此"后台检测线程"会周期性的检测Pool中"对象"的有效性,如果对象无效则会导致此对象从Pool中移除,并destroy;此外在调用者从Pool获取一个"对象"时,也会检测"对象"的有效性,确保不能讲"无效"的对象输出给调用者;当调用者使用完毕将"对象归还"到Pool时,仍然会检测对象的有效性.所谓有效性,就是此"对象"的状态是否符合预期,是否可以对调用者直接使用;如果对象是Socket,那么它的有效性就是socket的通道是否畅通/阻塞是否超时等.
	boolean validateObject(PooledObject<T> p);
	// "激活"对象,当Pool中决定移除一个对象交付给调用者时额外的"激活"操作,比如可以在activateObject方法中"重置"参数列表让调用者使用时感觉像一个"新创建"的对象一样;如果object是一个线程,可以在"激活"操作中重置"线程中断标记",或者让线程从阻塞中唤醒等;如果object是一个socket,那么可以在"激活操作"中刷新通道,或者对socket进行链接重建(假如socket意外关闭)等.
	void activateObject(PooledObject<T> p) throws Exception;
	//"钝化"对象,当调用者"归还对象"时,Pool将会"钝化对象";钝化的言外之意,就是此"对象"暂且需要"休息"一下.如果object是一个socket,那么可以passivateObject中清除buffer,将socket阻塞;如果object是一个线程,可以在"钝化"操作中将线程sleep或者将线程中的某个对象wait.需要注意的时,activateObject和passivateObject两个方法需要对应,避免死锁或者"对象"状态的混乱.
	void passivateObject(PooledObject<T> p) throws Exception;

####PooledObject接口

	T getObject();
	long getCreateTime();
	long getActiveTimeMillis();
	long getIdleTimeMillis();
	long getLastBorrowTime();
	long getLastReturnTime();
	long getLastUsedTime();
	int compareTo(PooledObject<T> other);
	boolean equals(Object obj);
	int hashCode();
	String toString();
	//后台清理线程
	boolean startEvictionTest();
	boolean endEvictionTest(Deque<PooledObject<T>> idleQueue);
	boolean allocate();
	boolean deallocate();
	void invalidate()
	void setLogAbandoned(boolean logAbandoned);
	void use();
	void printStackTrace(PrintWriter writer);
	PooledObjectState getState();
	//自动补偿功能
	void markAbandoned();
	void markReturning();

####GenericObjectPool是一般意义上的对象池

	//默认出队方式
	private volatile boolean lifo = BaseObjectPoolConfig.DEFAULT_LIFO; 
	//后台清理逻辑
	class Evictor extends TimerTask
	//所有对象列表
	private final Map<T, PooledObject<T>> allObjects = new ConcurrentHashMap<T, PooledObject<T>>();
	//可用对象列表【双向链表】
	private final LinkedBlockingDeque<PooledObject<T>> idleObjects = new LinkedBlockingDeque<PooledObject<T>>();
	
	borrowObject
	    public T borrowObject(long borrowMaxWaitMillis) throws Exception {
	        assertOpen();
	//是否在获取对象的时候检查对象，开启的话则检查【主要是检查过期】
	        AbandonedConfig ac = this.abandonedConfig;
	        if (ac != null && ac.getRemoveAbandonedOnBorrow() &&
	                (getNumIdle() < 2) &&
	                (getNumActive() > getMaxTotal() - 3) ) {
	            removeAbandoned(ac);
	        }
	 
	        PooledObject<T> p = null;
	 
	        // Get local copy of current config so it is consistent for entire
	        // method execution
	//当池耗尽的时候是否block，如果block的话则会idleObjects.pollFirst(borrowMaxWaitMillis,TimeUnit.MILLISECONDS);否则直接throw new NoSuchElementException("Pool exhausted");
	        boolean blockWhenExhausted = getBlockWhenExhausted();
	 
	        boolean create;
	        long waitTime = 0;
	 
	        while (p == null) {
	            create = false;
	            if (blockWhenExhausted) {
	                p = idleObjects.pollFirst();
	                if (p == null) {
	                    create = true;
	                    p = create();
	                }
	                if (p == null) {
	                    if (borrowMaxWaitMillis < 0) {
	                        p = idleObjects.takeFirst();
	                    } else {
	                        waitTime = System.currentTimeMillis();
	                        p = idleObjects.pollFirst(borrowMaxWaitMillis,
	                                TimeUnit.MILLISECONDS);
	                        waitTime = System.currentTimeMillis() - waitTime;
	                    }
	                }
	                if (p == null) {
	                    throw new NoSuchElementException(
	                            "Timeout waiting for idle object");
	                }
	                if (!p.allocate()) {
	                    p = null;
	                }
	            } else {
	                p = idleObjects.pollFirst();
	                if (p == null) {
	                    create = true;
	                    p = create();
	                }
	                if (p == null) {
	                    throw new NoSuchElementException("Pool exhausted");
	                }
	                if (!p.allocate()) {
	                    p = null;
	                }
	            }
	 
	            if (p != null) {
	                try {
	//激活对象
	                    factory.activateObject(p);
	                } catch (Exception e) {
	                    try {
	                        destroy(p);
	                    } catch (Exception e1) {
	                        // Ignore - activation failure is more important
	                    }
	                    p = null;
	                    if (create) {
	                        NoSuchElementException nsee = new NoSuchElementException(
	                                "Unable to activate object");
	                        nsee.initCause(e);
	                        throw nsee;
	                    }
	                }
	//如果获取对象是检查则validateObject
	                if (p != null && getTestOnBorrow()) {
	                    boolean validate = false;
	                    Throwable validationThrowable = null;
	                    try {
	                        validate = factory.validateObject(p);
	                    } catch (Throwable t) {
	                        PoolUtils.checkRethrow(t);
	                        validationThrowable = t;
	                    }
	//检查不通过则destroy
	                    if (!validate) {
	                        try {
	                            destroy(p);
	                            destroyedByBorrowValidationCount.incrementAndGet();
	                        } catch (Exception e) {
	                            // Ignore - validation failure is more important
	                        }
	                        p = null;
	                        if (create) {
	                            NoSuchElementException nsee = new NoSuchElementException(
	                                    "Unable to validate object");
	                            nsee.initCause(validationThrowable);
	                            throw nsee;
	                        }
	                    }
	                }
	            }
	        }
	 
	        updateStatsBorrow(p, waitTime);
	 
	        return p.getObject();
	    }
	
	returnObject
	    public void returnObject(T obj) {
	        PooledObject<T> p = allObjects.get(obj);
	 
	        if (!isAbandonedConfig()) {
	            if (p == null) {
	                throw new IllegalStateException(
	                        "Returned object not currently part of this pool");
	            }
	        } else {
	            if (p == null) {
	                return;  // Object was abandoned and removed
	            } else {
	                // Make sure object is not being reclaimed
	                synchronized(p) {
	                    final PooledObjectState state = p.getState();
	                    if (state == PooledObjectState.ABANDONED ||
	                            state == PooledObjectState.INVALID) {
	                        return;
	                    } else {
	                        p.markReturning(); // Keep from being marked abandoned
	                    }
	                }
	            }
	        }
	 
	        long activeTime = p.getActiveTimeMillis();
	//验证合格
	        if (getTestOnReturn()) {
	            if (!factory.validateObject(p)) {
	                try {
	                    destroy(p);
	                } catch (Exception e) {
	                    swallowException(e);
	                }
	                updateStatsReturn(activeTime);
	                return;
	            }
	        }
	//钝化
	        try {
	            factory.passivateObject(p);
	        } catch (Exception e1) {
	            swallowException(e1);
	            try {
	                destroy(p);
	            } catch (Exception e) {
	                swallowException(e);
	            }
	            updateStatsReturn(activeTime);
	            return;
	        }
	 
	        if (!p.deallocate()) {
	            throw new IllegalStateException(
	                    "Object has already been retured to this pool or is invalid");
	        }
	//池大小
	        int maxIdleSave = getMaxIdle();
	        if (isClosed() || maxIdleSave > -1 && maxIdleSave <= idleObjects.size()) {
	            try {
	                destroy(p);
	            } catch (Exception e) {
	                swallowException(e);
	            }
	        } else {
	            if (getLifo()) {
	                idleObjects.addFirst(p);
	            } else {
	                idleObjects.addLast(p);
	            }
	        }
	        updateStatsReturn(activeTime);
	    }


###参考资料

http://www.cnblogs.com/tommyli/p/3510095.html

