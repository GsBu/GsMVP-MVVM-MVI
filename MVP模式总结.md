>这篇文章是我学习MVP模式时，总结网上多篇文章的结果。内容和图片大部分来自其他文章。如果侵犯了您的权益，请告知，我会及时修改、删除。

@[toc]
# 1 介绍
## 1.1 背景
&emsp;&emsp;MVP，全称 Model-View-Presenter，要说MVP那就不得不说一说它的前辈——MVC。<br/>
&emsp;&emsp;MVC（Model-View-Controller，模型-视图-控制器）模式是80年代Smalltalk-80出现的一种软件设计模式，后来得到了广泛的应用。MVP（Model-View-Presenter，模型-视图-表示器）模式则是由IBM开发出来的一个针对C++和Java的编程模型，大概出现于2000年，是MVC模式的一个变种。<br/>
&emsp;&emsp;对比：
![对比.png](https://imgconvert.csdnimg.cn/aHR0cHM6Ly91c2VyLWdvbGQtY2RuLnhpdHUuaW8vMjAxOS85LzIzLzE2ZDVkMDQ4NTA4YmVhMjk?x-oss-process=image/format,png#pic_center)
&emsp;&emsp;MVC与MVP的基本思想有相通的地方：Model提供数据，View负责显示，Controller/Presenter负责逻辑的处理。<br/>
&emsp;&emsp;MVC的Controller用于更新UI界面和数据实例。<br/>
&emsp;&emsp;MVP的Presenter是View与Model交互的中间纽带。<br/>
&emsp;&emsp;MVC主要目的是将Model和View的实现代码分离，从而使同一个程序可以使用不同的表现形式，Controller存在的目的则是确保Model和View的同步，一旦Model改变，View应该同步更新。例如：View层接受用户的输入，然后通过Controller修改对应的Model实例。同时，当Model实例的数据发生变化的时候需要修改UI界面，可以通过Controller更新界面。（View层也可以直接更新Model实例的数据，而不用每次都通过Controller，这样对于一些简单的数据更新工作会变得方便许多）。<br/>
&emsp;&emsp;MVP主要目的是隔离UI、逻辑（UI逻辑和业务逻辑）、数据。在MVP中View并不直接使用Model，它们之间的通信是通过Presenter来进行的，所有的交互都发生在Presenter内部。<br/>
&emsp;&emsp;MVP与MVC的区别：MVC中是允许Model和View进行交互的。而MVP中很明显，Model与View之间的交互由Presenter完成，还有一点就是Presenter与View之间的交互是通过接口的。<br/>
## 1.2 为什么用MVP？
&emsp;&emsp;Android的整个代码架构就是一个MVC。<br/>
&emsp;&emsp;Model：模型层，相当于JavaBean和我们的数据请求代码。<br/>
&emsp;&emsp;View：视图层，对应Android的layout.xml布局文件。<br/>
&emsp;&emsp;Controller：控制层，对应于Activity中对于UI 的各种操作。<br/>
&emsp;&emsp;看起来 MVC 架构很清晰，但是实际的开发中，请求的业务代码往往被丢到了Activity 里面，Android 中的 layout.xml 布局文件只能提供默认的UI设置，所以开发中视图层的变化以及操作也被丢到了 Activity 里面，再加上 Activity 本身承担着控制层的责任。所以Activity达成了MVC集合的成就，最终我们的Activity就变得越来越难看，代码从几百行变成了几千行，维护的成本也越来越高。<br/>
&emsp;&emsp;MVP 与 MVC 最大的不同，其实是 Activity 职责的变化，由原来的 Controller 变成了 View，不再管控制层的问题，只管如何去显示。控制层的角色就由我们的新人 Presenter 来担当，这种架构就解决了 Activity 过度耦合控制层和视图层的问题。
# 2 实现
&emsp;&emsp;MVP更多的是一种思想，而不是一种模式，每个开发者都可以按照自己的思路来实现具有个性化的MVP，所以不同的人写出的MVP可能会有一些差别。下面给大家介绍一下MVP不同的实现方式。
## 2.1 入门
### 2.1.1 方式一
&emsp;&emsp;存取用户信息的 MVP 小 Demo：可以从EditText读取用户信息并存取，也可以根据ID来从后台读出用户信息并显示。<br/>
&emsp;&emsp;原文：[MVP模式在Android开发中的应用](https://blog.csdn.net/vector_yi/article/details/24719873?utm_source=tuicool)
#### 2.1.1.1 界面：
![界面.png](https://imgconvert.csdnimg.cn/aHR0cHM6Ly91c2VyLWdvbGQtY2RuLnhpdHUuaW8vMjAxOS85LzIzLzE2ZDVkMDQ4NGY0MTBmYmM?x-oss-process=image/format,png#pic_center)
#### 2.1.1.2 目录结构：
![目录结构.png](https://imgconvert.csdnimg.cn/aHR0cHM6Ly91c2VyLWdvbGQtY2RuLnhpdHUuaW8vMjAxOS85LzIzLzE2ZDVkMDQ4NTU2ZjQ0MjQ?x-oss-process=image/format,png#pic_center)
#### 2.1.1.3 代码：
+ 首先我们需要一个UserBean，用来保存用户信息：
```java
public class UserBean {
    private String mFirstName;
    private String mLastName;
    public UserBean (String firstName, String lastName) {
        this.mFirstName = firstName;
        this.mLastName = lastName;
    }
    public String getFirstName() {
        return mFirstName;
    }
    public String getLastName() {
        return mLastName;
    }
}
```
+ 再来看看View接口，根据需求可知，View可以对ID、FirstName、LastName这三个EditText进行读操作，对FirstName和LastName进行写操作，由此定义IUserView接口：
```java
public interface IUserView {
    int getID();
    String getFristName();
    String getLastName();
    void setFirstName (String firstName);
    void setLastName (String lastName);
}
```
+ Model接口。同样，Model也需要对这三个字段进行读写操作，并存储在某个载体内(这不是我们所关心的，可以存在内存、文件、数据库或者远程服务器，但对于Presenter及View无影响)，定义IUserModel接口：
```java
public interface IUserModel {
    void setID (int id);
    void setFirstName (String firstName);
    void setLastName (String lastName);
    int getID();
    UserBean load (int id);//通过id读取user信息,返回一个 UserBean
}
```
+ Presenter。至此，Presenter就能通过接口与View及Model进行交互了：
```java
public class UserPresenter {
    private IUserView mUserView;
    private IUserModel mUserModel;

    public UserPresenter(IUserView view) {
        mUserView = view;
        mUserModel = new UserModel();
    }

    public void saveUser(int id, String firstName, String lastName) {
        mUserModel.setID(id);
        mUserModel.setFirstName(firstName);
        mUserModel.setLastName(lastName);
    }

    public void loadUser(int id) {
        UserBean user = mUserModel.load(id);
        mUserView.setFirstName(user.getFirstName());//通过调用IUserView的方法来更新显示
        mUserView.setLastName(user.getLastName());
    }
}
```
+ UserActivity。 UserActivity实现了IUserView及View.OnClickListener接口，同时有一个UserPresenter成员变量：
```java
public class UserActivity extends Activity implements OnClickListener,
        IUserView {
    private UserPresenter mUserPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   
        mUserPresenter = new UserPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveButton:
                mUserPresenter.saveUser(getID(), getFristName(),
                        getLastName());
                break;
            case R.id.loadButton:
                mUserPresenter.loadUser(getID());
                break;
            default:
                break;
        }
    }
}
```
&emsp;&emsp;可以看到，View只负责处理与用户进行交互，并把数据相关的逻辑操作都扔给了Presenter去做。而Presenter调用Model处理完数据之后，再通过IUserView更新View显示信息。
#### 2.1.1.4 总结：
&emsp;&emsp;这个示例中创建了Model、View的接口而没有给Presenter创建接口。三者的联系是：1、在View的实现类UserActivity中创建UserPresenter类型的成员变量；2、在UserPresenter类中有Model和View的接口的引用，并在构造方法中参数传递View的接口实现类的实例并创建了Model接口实现类的实例；3、Model处理好数据后直接返回给Presenter。
### 2.1.2 方式二
&emsp;&emsp;天气查询的 MVP 小 Demo：输入城市的代号，点击按钮获取城市的天气信息然后显示出来。<br/>
&emsp;&emsp;原文：[Android中的MVP](https://rocko.xyz/2015/02/06/Android%E4%B8%AD%E7%9A%84MVP/)
#### 2.1.2.1 页面：
![图片.png](https://imgconvert.csdnimg.cn/aHR0cHM6Ly91c2VyLWdvbGQtY2RuLnhpdHUuaW8vMjAxOS85LzIzLzE2ZDVkMDQ4NGYwMmRlODg?x-oss-process=image/format,png#pic_center)
#### 2.1.2.2 目录结构：
![目录结构.png](https://imgconvert.csdnimg.cn/aHR0cHM6Ly91c2VyLWdvbGQtY2RuLnhpdHUuaW8vMjAxOS85LzIzLzE2ZDVkMDQ4NTA3YTNkYjU?x-oss-process=image/format,png#pic_center)
#### 2.1.2.3 代码：
+ View的接口：
```java
public interface WeatherView {
    void showLoading();
    void hideLoading();
    void showError();
    void setWeatherInfo(Weather weather);
}
```
+ View的实现类：
```java
public class WeatherActivity extends BaseActivity implements WeatherView, View.OnClickListener {
    private WeatherPresenter weatherPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherPresenter = new WeatherPresenterImpl(this); //传入WeatherView
    }
}
```
+ Presenter的接口：
```java
public interface WeatherPresenter {
    /**
     * 获取天气的逻辑
     */
    void getWeather(String cityNO);
}
```
+ Presenter的实现类：
```java
public class WeatherPresenterImpl implements WeatherPresenter, OnWeatherListener {
    /*Presenter作为中间层，持有View和Model的引用*/
    private WeatherView weatherView;
    private WeatherModel weatherModel;

    public WeatherPresenterImpl(WeatherView weatherView) {
        this.weatherView = weatherView;
        weatherModel = new WeatherModelImpl();
    }

    @Override
    public void getWeather(String cityNO) {
        weatherView.showLoading();
        weatherModel.loadWeather(cityNO, this);
    }

    @Override
    public void onSuccess(Weather weather) {
        weatherView.hideLoading();
        weatherView.setWeatherInfo(weather);
    }

    @Override
    public void onError() {
        weatherView.hideLoading();
        weatherView.showError();
    }
}
```
+ Model的接口。WeatherModel：
```java
public interface WeatherModel {
    void loadWeather(String cityNO, OnWeatherListener listener);
}
```
+ OnWeatherListener回调接口。项目中在prestener文件夹下还有个OnWeatherListener文件，其在Presenter层实现，给Model层回调，更改View层的状态，确保Model层不直接操作View层。如果没有这一接口在WeatherPresenterImpl实现的话，WeatherPresenterImpl只有View和Model的引用那么Model怎么把结果告诉View呢？当然这只是一种解决方案，在实际项目中可以使用Dagger、EventBus、Otto等第三方框架结合进来达到更加松耦合的设计。
```java
public interface OnWeatherListener {
    /**
     * 成功时回调
     * @param weather
     */
    void onSuccess(Weather weather);
    /**
     * 失败时回调，简单处理，没做什么
     */
    void onError();
}
```
#### 2.1.2.4 总结：
&emsp;&emsp;这个示例中创建了Model、View、Presenter的接口。三者的联系是：1、在View的实现类WeatherActivity中创建Presenter接口的引用然后用实现类WeatherPresenterImpl创建实例；2、在WeatherPresenterImpl中有Model和View的接口的引用，并在构造方法中参数传递View的接口实现类的实例并创建了Model接口实现类的实例；3、Model处理好数据后通过回调通知Presenter。
### 2.1.3 方式三
&emsp;&emsp;下载文件小 Demo：点击按钮，下载一张图片，显示下载进度；下载完成后，在ImageView中显示这张图片。<br/>
&emsp;&emsp;原文：[Android MVP 十分钟入门](https://juejin.im/post/58870cc2128fe1006c46e39c)
#### 2.1.3.1 页面：
![页面.png](https://imgconvert.csdnimg.cn/aHR0cHM6Ly91c2VyLWdvbGQtY2RuLnhpdHUuaW8vMjAxOS85LzIzLzE2ZDVkMDQ4NTZlM2ZhYjU?x-oss-process=image/format,png#pic_center)
#### 2.1.3.2 目录结构：
![目录结构.png](https://imgconvert.csdnimg.cn/aHR0cHM6Ly91c2VyLWdvbGQtY2RuLnhpdHUuaW8vMjAxOS85LzIzLzE2ZDVkMDQ4ODBlOTA0ZjA?x-oss-process=image/format,png#pic_center)
#### 2.1.3.3 代码：
+ Model 接口定义所有需要实现的业务逻辑，在我们的下载任务中，业务逻辑只有一个，就是下载。因此Model 接口可以这么定义 ：
```java
public interface IDownloadModel {
    /**
     * 下载操作
     * @param url
     */
    void download(String url);
}
```
+ Model 具体实现：
```java
public class DownloadModel implements IDownloadModel {
    private IDowndownPresenter mIDowndownPresenter;
    private MyHandler mMyHandler = new MyHandler();

    public DownloadModel(IDowndownPresenter IDowndownPresenter) {
        mIDowndownPresenter = IDowndownPresenter;
    }

    @Override
    public void download(String url) {
        HttpUtil.HttpGet(url, new DownloadCallback(mMyHandler));
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 300:
                    int percent = msg.arg1;
                    if (percent < 100) {
                        mIDowndownPresenter.downloadProgress(percent);
                    } else {
                        mIDowndownPresenter.downloadSuccess(Constants.LOCAL_FILE_PATH);
                    }
                    break;
                case 404:
                    mIDowndownPresenter.downloadFail();
                    break;
                default:
                    break;
            }
        }
    }
}
```
+ Presenter 接口：
```java
public interface IDowndownPresenter {
    /**
     * 下载
     * @param url
     */
    void download(String url);

    /**
     * 下载成功
     * @param result
     */
    void downloadSuccess(String result);

    /**
     * 当前下载进度
     * @param progress
     */
    void downloadProgress(int progress);

    /**
     * 下载失败
     */
    void downloadFail();
}
```
+ Presenter 具体实现：
```java
public class DownloadPresenter implements IDowndownPresenter {
    private IDownloadView mIDownloadView;
    private IDownloadModel mIDownloadModel;

    public DownloadPresenter(IDownloadView IDownloadView) {
        mIDownloadView = IDownloadView;
        mIDownloadModel = new DownloadModel(this);
    }

    @Override
    public void download(String url) {
        mIDownloadView.showProgressBar(true);
        mIDownloadModel.download(url);
    }

    @Override
    public void downloadSuccess(String result) {
        mIDownloadView.showProgressBar(false);
        mIDownloadView.setView(result);
    }

    @Override
    public void downloadProgress(int progress) {
        mIDownloadView.setProcessProgress(progress);
    }

    @Override
    public void downloadFail() {
        mIDownloadView.showProgressBar(false);
        mIDownloadView.showFailToast();
    }
}
```
+ View接口：
```java
public interface IDownloadView {
    /**
     * 显示进度条
     * @param show
     */
    void showProgressBar(boolean show);

    /**
     * 设置进度条进度
     * @param progress
     */
    void setProcessProgress(int progress);

    /**
     * 根据数据设置view
     * @param result
     */
    void setView(String result);

    /**
     * 设置请求失败时的view
     */
    void showFailToast();
}
```
+ View具体实现：
```java
public class MVPActivity extends AppCompatActivity implements IDownloadView {
    private DownloadPresenter mDownloadPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        
        mDownloadPresenter = new DownloadPresenter(this);
    }
}
```
#### 2.1.3.4 总结：
&emsp;&emsp;这个示例中创建了Model、View、Presenter的接口。三者的联系是：1、在View的实现类MVPActivity 中创建Presenter的实现类DownloadPresenter类型的成员变量；2、在DownloadPresenter中有Model和View的接口的引用，并在构造方法中参数传递View接口实现类的实例并创建了Model接口实现类的实例；3、这个示例中Model持有Presenter的引用，处理完数据后直接调用Presenter的方法通知Presenter。
### 2.1.4 方式四
&emsp;&emsp;Google官方MVP示例：实现了一个类似记事本的功能。<br/>
&emsp;&emsp;项目地址：[Google官方MVP示例](https://github.com/googlesamples/android-architecture/tree/todo-mvp/)
#### 2.1.4.1 页面：
![页面.png](https://imgconvert.csdnimg.cn/aHR0cHM6Ly91c2VyLWdvbGQtY2RuLnhpdHUuaW8vMjAxOS85LzIzLzE2ZDVkMDQ4ODUyMDkxMDU?x-oss-process=image/format,png#pic_center)
#### 2.1.4.2 目录结构（按功能模块分Package）：
![目录结构.png](https://imgconvert.csdnimg.cn/aHR0cHM6Ly91c2VyLWdvbGQtY2RuLnhpdHUuaW8vMjAxOS85LzIzLzE2ZDVkMDQ4OTk2ZDlmNGM?x-oss-process=image/format,png#pic_center)
#### 2.1.4.3 代码：
&emsp;&emsp;项目中有两个基类：
+ BaseView：
```java
public interface BaseView<T> { 
    void setPresenter(T presenter); 
}
```
+ BasePresenter：
```java
public interface BasePresenter {
    void start();
}
```
&emsp;&emsp;各自简单的声明了一个方法，start()方法用来给Presenter做一些初始化的操作不用特别在意，BaseView声明的方法就很有意思了，setPresenter(T presenter) 很明显是给View绑定Presenter，而上面介绍的几个项目使用的方式都是给Presenter传入View的引用来完成View和Presenter绑定的操作，这里谷歌采用了相反的方式来操作，具体原因后面会解释。<br/>
&emsp;&emsp;单个模块的具体文件结构：
![文件结构.png](https://imgconvert.csdnimg.cn/aHR0cHM6Ly91c2VyLWdvbGQtY2RuLnhpdHUuaW8vMjAxOS85LzIzLzE2ZDVkMDQ4OWQxOTY2NmI?x-oss-process=image/format,png#pic_center)
&emsp;&emsp;有Activity，Fragment，Presenter。View哪里去了？ 其实谷歌的MVP是将Fragment作为View层来实现的，这一点在官方的Readme中也有说明，为什么要用Fragment？
> 1、官方认为Fragment和Activity相比更像是MVP中的View层，刚好可以满足MVP的View层的要求，Activity则作为最高指挥官用来创建和联系View和Presenter。<br/>
> 2、Fragment在平板或者屏幕上有多个View时更有优势。

&emsp;&emsp;Activity只作为创建和联系View和Presenter的存在，而Fragment作为显示UI的存在。Activity主指挥，Fragment主显示。这也是谷歌sample中的一贯做法。<br/>
&emsp;&emsp;View的问题解释完了，再看看TaskDetailContract 这种**Contract 接口，这也是官方独有的管理方法。<br/>
&emsp;&emsp;Google的 todomvp 工程中每个模块都会有一个 **Contract 接口，来看看他的代码：
```java
public interface TaskDetailContract {
    interface View extends BaseView<Presenter> {
        void setLoadingIndicator(boolean active);
        void showMissingTask();
        void hideTitle();
        void showTitle(String title);
        void hideDescription();
        void showDescription(String description);
        void showCompletionStatus(boolean complete);
        void showEditTask(String taskId);
        void showTaskDeleted();
        void showTaskMarkedComplete();
        void showTaskMarkedActive();
        boolean isActive();
    }
    interface Presenter extends BasePresenter {
        void editTask();
        void deleteTask();
        void completeTask();
        void activateTask();
    }
}
```
&emsp;&emsp;Contract其实就是一个包涵了Presenter和View的接口。Presenter实现的逻辑层方法，View实现的UI层的方法都能在Contract接口中一目了然，具体的Presenter和View的实现类都是通过实现Contract接口来完成。这种方式既方便了管理和维护，也给开发点了一个导航灯。<br/>
&emsp;&emsp;下面来看看Presenter如何引用到Fragment中以及View如何与Presenter建立联系。<br/>
&emsp;&emsp;TaskDetailActivity ：
```java
public class TaskDetailActivity extends AppCompatActivity {
    public static final String EXTRA_TASK_ID = "TASK_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taskdetail_act);

        // Get the requested task id
        String taskId = getIntent().getStringExtra(EXTRA_TASK_ID);

        // 实例化taskDetailFragment
        TaskDetailFragment taskDetailFragment = (TaskDetailFragment)getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if (taskDetailFragment == null) {
            //taskDetailFragment 添加到Activity
            taskDetailFragment = TaskDetailFragment.newInstance(taskId);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    taskDetailFragment, R.id.contentFrame);
        }

        // Create the presenter  初始化Presenter
        new TaskDetailPresenter(taskId, Injection.provideTasksRepository(getApplicationContext()),
                taskDetailFragment);
    }
}
```
&emsp;&emsp;TaskDetailActivity主要做了taskDetailFragment和TaskDetailPresenter的初始化操作。在做TaskDetailPresenter初始化时直接将taskDetailFragment作为参数传入，因为taskDetailFragment实现了View层的接口，看到这里有一个疑问，new TaskDetailPresenter()可以实例化一个Presenter，但是怎么传入到taskDetailFragment中呢？为了解开疑惑我们查看一下TaskDetailPresenterde 的构造方法：
```java
public class TaskDetailPresenter implements TaskDetailContract.Presenter {
    public TaskDetailPresenter(@Nullable String taskId,
                               @NonNull TasksRepository tasksRepository,
                               @NonNull TaskDetailContract.View taskDetailView) {
        this.mTaskId = taskId;
        mTasksRepository = checkNotNull(tasksRepository, "tasksRepository cannot be null!");
        mTaskDetailView = checkNotNull(taskDetailView, "taskDetailView cannot be null!");
        //实现绑定Presenter的关键方法
        mTaskDetailView.setPresenter(this);
    }
}
```
&emsp;&emsp;关键方法就是在BaseView中声明的void setPresenter(T presenter)方法实现的绑定，当然这里只是调用View的方法，具体的绑定还要追踪到实体类中，来看TaskDetailFragment：
```java
public class TaskDetailFragment extends Fragment implements TaskDetailContract.View {
    //声明了一个mPresenter
    private TaskDetailContract.Presenter mPresenter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.taskdetail_frag, container, false);
        return root;
    }

    @Override
    public void setPresenter(@NonNull TaskDetailContract.Presenter presenter) {
        //完成mPresenter的绑定
        mPresenter = checkNotNull(presenter);
    }
}
```
&emsp;&emsp;TaskDetailFragment 实现了TaskDetailContract接口中的View接口，这样在TaskDetailPresenter 构造方法中调用mTaskDetailView.setPresenter(this)方法后，实际调用的是TaskDetailFragment 中的setPresenter方法，TaskDetailPresenter 就成功绑定到了TaskDetailFragment 中。<br/>
&emsp;&emsp;Model层<br/>
&emsp;&emsp;该项目中Model层最大的特点是被赋予了数据获取的职责，与我们平常Model层只定义实体对象截然不同。实例中，数据的获取、存储、数据状态变化都是Model层的任务，Presenter会根据需要调用该层的数据处理逻辑并在需要时将回调传入。<br/>
&emsp;&emsp;我们来看TaskDetailPresenter 的 openTask() 方法：
```java
private void openTask() {
    // 判空处理
    if (null == mTaskId || mTaskId.isEmpty()) {
        mTaskDetailView.showMissingTask();
        return;
    }
    // 更新状态
    mTaskDetailView.setLoadingIndicator(true);
    // 获取该条Task数据
    mTasksRepository.getTask(mTaskId, new TasksDataSource.GetTaskCallback() {
        @Override
        public void onTaskLoaded(Task task) {
            // The view may not be able to handle UI updates anymore
            // View已经被用户回退
            if (!mTaskDetailView.isActive()) {
                return;
            }
            // 获取到task数据，并更新UI
            mTaskDetailView.setLoadingIndicator(false);
            if (null == task) {
                mTaskDetailView.showMissingTask();
            } else {
                showTask(task);
            }
        }

        @Override
        public void onDataNotAvailable() {
            // The view may not be able to handle UI updates anymore
            // 显示数据获取失败时的状态
            if (!mTaskDetailView.isActive()) {
                return;
            }
            mTaskDetailView.showMissingTask();
        }
    });
}
```
&emsp;&emsp;我们接着看 TasksRepository 中的getTask() 方法：
```java
@Override
public void getTask(@NonNull final String taskId, @NonNull final GetTaskCallback callback) {
    // 判空处理
    checkNotNull(taskId);
    checkNotNull(callback);

    // 获取缓存数据
    Task cachedTask = getTaskWithId(taskId);
    // Respond immediately with cache if available
    if (cachedTask != null) {
        callback.onTaskLoaded(cachedTask);
        return;
    }

    // Load from server/persisted if needed.
    // Is the task in the local data source? If not, query the network.
    // 从本地数据源（SQLite数据库）中获取
    mTasksLocalDataSource.getTask(taskId, new GetTaskCallback() {
        @Override
        public void onTaskLoaded(Task task) {
            // 成功，则回调
            callback.onTaskLoaded(task);
        }

        @Override
        public void onDataNotAvailable() {
            // 失败，则从远程数据源（网络）中获取
            mTasksRemoteDataSource.getTask(taskId, new GetTaskCallback() {
                @Override
                public void onTaskLoaded(Task task) {
                    // 回调成功时的方法
                    callback.onTaskLoaded(task);
                }

                @Override
                public void onDataNotAvailable() {
                    // 回调失败时的方法
                    callback.onDataNotAvailable();
                }
            });
        }
    });
}
```
&emsp;&emsp;我们发现 TasksRepository 维护了两个数据源，一个是本地（SQLite数据库），一个是远程（网络服务器）：
```java
private final TasksDataSource mTasksRemoteDataSource;
private final TasksDataSource mTasksLocalDataSource;
```
&emsp;&emsp;我们发现他们（包括TasksRepository类）都实现了 TasksDataSource 接口：
```java
public interface TasksDataSource {
    interface LoadTasksCallback {
        void onTasksLoaded(List<Task> tasks);
        void onDataNotAvailable();
    }
    interface GetTaskCallback {
        void onTaskLoaded(Task task);
        void onDataNotAvailable();
    }
    void getTasks(@NonNull LoadTasksCallback callback);
    void getTask(@NonNull String taskId, @NonNull GetTaskCallback callback);
    void saveTask(@NonNull Task task);
    void completeTask(@NonNull Task task);
    void completeTask(@NonNull String taskId);
    void activateTask(@NonNull Task task);
    void activateTask(@NonNull String taskId);
    void clearCompletedTasks();
    void refreshTasks();
    void deleteAllTasks();
    void deleteTask(@NonNull String taskId);
}
```
&emsp;&emsp;这样一来我们就很容易扩展新的数据源（获取数据的方式），毕竟我们在TaskDetailActivity中初始化TasksRepository就是调用的如下方法，其实我们很容易把FakeTasksRemoteDataSource替换为TasksRemoteDataSource，把TasksLocalDataSource 替换为TasksContentProviderDataSource，这就是针对接口编程的好处吧。
```java
public static TasksRepository provideTasksRepository(@NonNull Context context) {
    checkNotNull(context);
    ToDoDatabase database = ToDoDatabase.getInstance(context);
    return TasksRepository.getInstance(FakeTasksRemoteDataSource.getInstance(),
            TasksLocalDataSource.getInstance(new AppExecutors(),
                    database.taskDao()));
}
```
#### 2.1.4.4 总结：
&emsp;&emsp;这个示例中创建了View、Presenter的接口，两者被包含在Contract中具体功能一目了然，统一管理方便维护。Model层的接口是TasksDataSource，而且该示例中的Model层使用了单例模式，在Model具体的实现类中实现对数据的不同操作（增、删、改、查），所以项目中不同的功能使用的是同一个Model对象，这和其他示例有所区别（其他项目一般是一个功能对应一个Model）。三者的联系是：1、该示例中Activity只作为创建和联系View和Presenter而存在，将Fragment作为显示UI的存在。Activity主指挥，Fragment主显示；2、在TaskDetailPresenter 中有Model和View的引用，并在构造方法中参数传递View、Model接口实现类的实例并调用View的setPresenter方法把自身的引用传给View；3、这个示例的Activity在创建Presenter时获取Model的单例并传给Presenter ，Model处理好数据后通过回调通知Presenter。
#### 2.1.4.5 UML结构图：
![UML结构图.png](https://imgconvert.csdnimg.cn/aHR0cHM6Ly91c2VyLWdvbGQtY2RuLnhpdHUuaW8vMjAxOS85LzIzLzE2ZDVkMDQ4YWZkNjExZGI?x-oss-process=image/format,png#pic_center)
### 2.1.5 汇总
&emsp;&emsp;MVP更多的是一种思想，而不是一种固定的模式，所以不同的人有不同的实现方式。

>+ 接口方面：都会给Model、View创建接口，有些项目不会创建Presenter的接口，极个别的项目也会不给Model创建接口，Google官方示例中引入Contract统一管理View、Presenter接口。
>+ 三者的联系：1、在View的实现类Activity中包含Presenter成员变量（数据类型有些是接口有些是实现类）；2、在Presenter中有Model和View的接口的引用，并在构造方法中参数传递View接口实现类的实例并创建了Model接口实现类的实例；3、Model处理完数据后如果逻辑简单可以直接返回数据给Presenter，如果逻辑复杂或是耗时操作可以通过回调或者持有Presenter引用的方式通知Presenter，也可以使用Dagger、EventBus、Otto等第三方框架达到更加松耦合的设计。注：Google官方示例有所区别，需单独看待。
### 2.1.6 接口的必要性
&emsp;&emsp;可能有的人会问，为什么要写一个 View 的接口，直接把 Activity 本身传入到 Presenter 不行吗？这当然是可行的，这里使用接口主要是为了代码的复用，试想一下，如果直接传入 Activity，那么这个 Presenter 就只能为这一个 Activity 服务。举个例子，假设有个 App 已经开发完成了，可以在手机上正常使用，现在要求做平板上的适配，在平板上的界面显示效果有所变化，TextView 并不是直接在 Activity 中的，而是在 Fragment 里面，如果没有使用 View 的接口的话，那就需要再写一个针对 Fragment 的 Presenter，然后把整个过程再来一遍。但是使用 View 的接口就很简单了，直接让 Fragment 实现这个接口，然后复写接口里面的方法，Presenter 和 Model 层都不需要做任何改动。同理，Model 层也可以采用接口的方式来写。
### 2.1.7 目录结构
&emsp;&emsp;项目的目录结构也有不同的实现。有些是按模块分Package，在模块下面再去创建Model、View、Presenter的子Package，如图：
![按模块分.png](https://imgconvert.csdnimg.cn/aHR0cHM6Ly91c2VyLWdvbGQtY2RuLnhpdHUuaW8vMjAxOS85LzIzLzE2ZDVkMDQ4YjJlZjEwZGE?x-oss-process=image/format,png#pic_center)
&emsp;&emsp;当然也可以用Model、View、Presenter作为顶级的Package，然后把所有的模块的Model、View、Presenter类都到这三个顶级Package中，如图：
![M、V、P作为顶级目录.png](https://imgconvert.csdnimg.cn/aHR0cHM6Ly91c2VyLWdvbGQtY2RuLnhpdHUuaW8vMjAxOS85LzIzLzE2ZDVkMDQ4YjNhMmM4YTc?x-oss-process=image/format,png#pic_center)
## 2.2 进阶
### 2.2.1 内存泄漏
&emsp;&emsp;写MVP的时候，Presenter会持有View，如果Presenter有后台异步的长时间的动作，比如网络请求，这时如果返回退出了Activity，后台异步的动作不会立即停止，这里就会有内存泄漏的隐患。
&emsp;&emsp;解决方法：
>1. 在Presenter传入View实例引用时，通过 弱引用 进行封装。
>2. 在Presenter中提供 绑定（attach）与 解绑（detach）函数，以便调用者可以管理内存释放。

&emsp;&emsp;代码如下：
```java
/**
 * Presenter.java
 * 将传入的View接口实例，通过弱引用（WeakReference）把Presenter与View进行绑定。
 * @param view  界面更新接口实例
 */
public void attach(View view){
    aviewWeakReference=new WeakReference<>(view);
}
/**
 *  Presenter.java
 *  将Presenter与View进行解绑，并释放内存
 */
public void detach(){
    if(aviewWeakReference!=null){
        aviewWeakReference.clear();
        aviewWeakReference=null;
    }
}
/**
 *  Presenter.java
 *  或者简单的将View置空
 */
public void detach(){
    view = null;
}
/*************************分割线********************************/
/**
 * View.java
 * 退出时销毁Presentr持有的View
 */
@Override
protected void onDestroy() {
    presenter.detach();
    super.onDestroy();
}
```
&emsp;&emsp;虽然这里 Activity 不会内存泄漏了，但是当 Activity 退出之后，Model 中请求数据就没有意义了，所以还应该在 detach 方法中，把 Model中的获取数据的任务取消，避免造成资源浪费。如果没有取消，后台的延时操作返回时，这个时候view被销毁了，如果接着去调用View的方法就会抛出空指针异常。所以在后台的延时操作中需要考虑到这种可能产生空指针的情况，尤其是网络请求。所以Google官方的实例中也有对这种情况的处理，如下所示：<br/>
&emsp;&emsp;在View的接口中定义了一个isActive方法：
```java
public interface TaskDetailContract {
    interface View extends BaseView<Presenter> {
        boolean isActive();
    }
}
```
&emsp;&emsp;具体实现类：
```java
public class TaskDetailFragment extends Fragment implements TaskDetailContract.View {
    @Override
    public boolean isActive() {
        return isAdded();
    }
}
```
&emsp;&emsp;isAdded()方法如果返回true代表Fragment添加到了Activity，false代表没有添加，具体使用：
```java
mTasksRepository.getTask(mTaskId, new TasksDataSource.GetTaskCallback() {
    @Override
    public void onTaskLoaded(Task task) {
        // The view may not be able to handle UI updates anymore
        // View已经被用户回退
        if (!mTaskDetailView.isActive()) {
            return;
        }
        // 获取到task数据，并更新UI
        mTaskDetailView.setLoadingIndicator(false);
        if (null == task) {
            mTaskDetailView.showMissingTask();
        } else {
            showTask(task);
        }
    }

    @Override
    public void onDataNotAvailable() {
        // The view may not be able to handle UI updates anymore
        // 显示数据获取失败时的状态
        if (!mTaskDetailView.isActive()) {
            return;
        }
        mTaskDetailView.showMissingTask();
    }
});
```
### 2.2.2 MVP 的封装
&emsp;&emsp;很显然，MVP 的实现套路是大致相同的，如果在一个应用中，存在大量的 Activity 和 Fragment，并且都使用 MVP 的架构，那么难免会有很多重复工作，所以封装就很有必要性了。<br/>
&emsp;&emsp;首先 Model、View 和 Presenter 都可能会有一些通用性的操作，所以可以分别定义三个对应的底层接口。
```java
interface BaseModel {
}

interface BaseView {
    void showError(String msg);
}

public abstract class BasePresenter<V extends BaseView, M extends BaseModel> {
    private WeakReference weakReference;
    protected M model;

    public BasePresenter() {
        model = createModel();
    }

    /**
     * 将传入的View接口实例，通过弱引用（WeakReference）把Presenter与View进行绑定。
     * @param v  界面更新接口实例
     */
    public void attach(V v){
        weakReference = new WeakReference<>(v);
    }

    /**
     *  将Presenter与View进行解绑，并释放内存
     */
    public void detach(){
        if(weakReference!=null){
            weakReference.clear();
            weakReference = null;
        }
    }

    abstract M createModel();
}
```
&emsp;&emsp;这里的 View 层添加了一个通用的方法，显示错误信息，写在接口层，可以在实现处按照需求来显示，比如有的地方可能会是弹出一个 Toast，或者有的地方需要将错误信息显示在 TextView 中，Model 层也可以根据需要添加通用的方法，重点来看一下 Presenter 层。<br/>
&emsp;&emsp;这里的 BasePresenter 采用了泛型，为什么要这么做呢？主要是因为 Presenter 必须同时持有 View 和 Model 的引用，但是在底层接口中无法确定他们的类型，只能确定他们是 BaseView 和 BaseModel 的子类，所以采用泛型的方式来引用，就巧妙的解决了这个问题，在 BasePresenter 的子类中只要定义好 View 和 Model 的类型，就会自动引用他们的对象了。Presenter 中的通用的方法主要就是 attachView 和 detachView，分别用于创建 View 对象的弱引用和把弱引用的对象置为空。前面已经说过，这样是为了防止内存泄漏。Model 的对象可以在 Presenter 的构造方法中创建。另外，这里的 Presenter 也可以写成接口的形式，读者可以按照自己的喜好来选择。<br/>
&emsp;&emsp;然后看一下在业务代码中该如何使用 MVP 的封装，代码如下：
```java
interface TestContract {
    interface Model extends BaseModel {
        void getData1(Callback1 callback1);
        void getData2(Callback2 callback2);
        void getData3(Callback3 callback3);
    }

    interface View extends BaseView {
        void updateUI1();
        void updateUI2();
        void updateUI3();
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        abstract void request1();
        abstract void request2();
        void request3() {
            model.getData3(new Callback3() {
                @Override
                public void onResult(String text) {
                    view.updateUI3();
                }
            });
        }
    }
}
```
&emsp;&emsp;首先定义一个 Contract 契约接口，然后把 Model、View、和 Presenter 的子类分别放入 Contract 的内部，这里的一个 Contract 就对应一个页面（一个 Activity 或者一个 Fragment），放在 Contract 内部是为了让同一个页面的逻辑方法都放在一起，方便查看和修改。Presenter 中的 request3 方法演示了如何通过 Presenter 来进行 View 和 Model 的交互。<br/>
&emsp;&emsp;接下来要做的就是实现这三个模块的逻辑方法了，在 Activity 或 Fragment 中实现 TextContract.View 的接口，再分别创建两个类用来实现 TextContract.Model 和 TextContract.Presenter，复写里面的抽象方法就好了。<br/>
&emsp;&emsp;每一个Activity都需要 初始化 Presenter与调用其绑定、解绑的方法，编写一个BaseActivity类，向子类提供Presenter初始化的抽象函数，并在BaseActivity中onCreate()与onDestory中调用对应Presenter类的attach()与detach()方法。(Fragment同理)
```java
/**
 * BaseActivty：封装Presenter的绑定与解绑方法，减少相同冗余代码
 * @param <V> View界面
 * @param <P> Presenter
 */
public abstract class BaseAcitvity<V, P extends BasePresenter<V>> extends AppCompatActivity {
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        if(presenter!=null){
            presenter.attach((V) this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null) {
            presenter.detach();
            presenter = null;
        }
    }

    /**
     * 创建继承于BasePresenter的子类
     * @return
     */
    protected abstract P createPresenter();
}
```
### 2.2.3 引入RxJava
&emsp;&emsp;Model层中的方法一般都会传入一个回调接口，这是因为获取数据往往是异步的，在获取的数据时需要用回调接口通知Presenter来更新View。<br/>
&emsp;&emsp;如果想要避免回调接口，可以采用RxJava的方式来Model获取的数据直接返回一个 Observable，接下来用RxJava的方式来改造的例子：
```java
public class HttpModel {
    public Observable<String> request() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Thread.sleep(2000);
                emitter.onNext("从网络获取到的数据");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
    }
}

public class Presenter {
    private MVPView view;
    private HttpModel model;

    public Presenter(MVPView view) {
        this.view = view;
        model = new HttpModel();
    }

    private Disposable disposable;

    public void request() {
        disposable = model.request()
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        view.updateTv(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }

    public void detachView() {
        view = null;
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
```
&emsp;&emsp;Model的 request方法直接返回一个Observable，然后在Presenter中调用subscribe方法来通知View更新，这样就避免了使用回调接口。
# 3 优缺点
## 3.1 优点
1. 降低耦合度，实现了Model和View真正的完全分离，可以修改View而不影响Model。
2. 模块职责划分明显，层次清晰。
3. 隐藏数据。
4. Presenter可以复用，一个Presenter可以用于多个View，而不需要更改Presenter的逻辑（当然是在View的改动不影响业务逻辑的前提下）。
5. 利于测试驱动开发。以前的Android开发是难以进行单元测试的（虽然很多Android开发者都没有写过测试用例，但是随着项目变得越来越复杂，没有测试是很难保证软件质量的；而且近几年来Android上的测试框架已经有了长足的发展——开始写测试用例吧），在使用MVP的项目中Presenter对View是通过接口进行，在对Presenter进行不依赖UI环境的单元测试的时候。可以通过Mock一个View对象，这个对象只需要实现了View的接口即可。然后依赖注入到Presenter中，单元测试的时候就可以完整的测试Presenter应用逻辑的正确性。
6. View可以进行组件化。在MVP当中，View不依赖Model。这样就可以让View从特定的业务场景中脱离出来，可以说View可以做到对业务完全无知。它只需要提供一系列接口提供给上层操作。这样就可以做到高度可复用的View组件。
7. 代码灵活性。
## 3.2 缺点
1. Presenter中除了应用逻辑以外，还有大量的View->Model，Model->View的手动同步逻辑，造成Presenter比较笨重，维护起来会比较困难。
2. 由于对视图的渲染放在了Presenter中，所以视图和Presenter的交互会过于频繁。
3. 如果Presenter过多地渲染了视图，往往会使得它与特定的视图的联系过于紧密。一旦视图需要变更，那么Presenter也需要变更了。
4. 额外的代码复杂度及学习成本。
# 4 参考文档
1. [Android MVP 详解（上）](https://www.jianshu.com/p/9a6845b26856)
2. [Android MVP 详解（下）](https://www.jianshu.com/p/0590f530c617)
3. [Android MVP 模式](https://github.com/kaedea/android-mvp-pattern/blob/master/doc/readme-cn.md)
4. [Android MVP 十分钟入门](https://juejin.im/post/58870cc2128fe1006c46e39c)
5. [ANDROID MVP 模式  简单易懂的介绍方式](https://zhuanlan.zhihu.com/p/20312610)
6. [Android MVP 架构的自述](https://blog.csdn.net/dantestones/article/details/50899235)
7. [MVP模式从入门到精通](https://blog.csdn.net/qq_31852701/article/details/52946127)
8. [浅谈 MVP in Android](https://blog.csdn.net/lmj623565791/article/details/46596109)
9. [一篇文章彻底搞懂 MVP](https://www.jianshu.com/p/ef4e61cd659d)
10. [如何更高效的使用MVP以及官方MVP架构解析](https://blog.csdn.net/DanteStones/article/details/51445208)
11. [Android架构：2018 主流大厂MVP模式是怎样](https://blog.csdn.net/qwe851023/article/details/83417780)
12. [Google 官网MVP解读](https://www.kancloud.cn/alex_wsc/android_library_framework/452692)
13. [Android官方MVP架构项目解析](https://www.jianshu.com/p/389c9ae1a82c)
# 5 源码
源码地址：[GsMVP](https://github.com/GsBu/GsMVP)

