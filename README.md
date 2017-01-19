【Android】透明状态栏在App中的实现

GitHub源码:[TransparentStatusbar](https://github.com/sodino/TransparentStatusBar)


--------------------------------
## 认识透明状态栏

从Android4.4开始引入了透明状态栏的新特性．
见下图，左边为传统的Android系统状态栏，右边为透明状态栏．　　　
　
![what is transparent status bar](./img/001.png)

* 正常显示状态栏的图标/文字　　
* 状态栏的背景是透明的，能透出应用的背景色．而不像之前一样是默认的黑色不可编辑．


--------------------------------
## 透明状态栏Api及特性

从Android 4.4(v19)开始，透明状态栏特性变化很频繁，直到Android 6.0(v23)才真正完善稳定．　　　
下表展示各版本所引入的新Api或特性．

|Version/level|Features |Description |
|:----|:---|:---| 
| 4.4/v19|WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS|状态栏是渐变色的半透明|　　
| 4.4_Watch/v20|OnApplyWindowInsetsListener|能够区分多个Inset事件与Rect信息(PS.系统状态栏属于插入区Inset的一种)|
| 5.0/v21|WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS<br/>View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN<br/>View.SYSTEM_UI_FLAG_LAYOUT_STABLE|允许自定义状态栏背景色了，但无法控制状态栏上的文字/图标颜色|
| 6.0/v23|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR|状态栏上的图标/文字颜色的亮色模式，即颜色是暗色|


--------------------------------
## 设置透明状态栏

根据多个版本间的Api及特性,Java代码如下:  
````
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // Android 5.0   
                int visibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;   
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    // Android 6.0   
                    // 亮色模式,避免系统状态栏的图标不可见   
                    // visibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;   
                }   
                window.getDecorView().setSystemUiVisibility(visibility);   
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                // 自定义状态栏背景色   
                window.setStatusBarColor(Color.TRANSPARENT);   
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                // Android 4.4   
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);   
            }   
````

> **注意：** 要设置透明状态栏的`Activity`其`theme`须是`NoTitleBar`.  
````
// AndroidManifest.xml   
     <activity android:name="MyActivity"   
                android:theme="@android:style/Theme.NoTitleBar"  
            />  
    // Or / 或       
    // MyActivity.java
    public void onCreate(Bundle bundle) {  
        super.onCreate(bundle);  
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
    }
````

代码执行后,界面显示效果如下图:      
![status_bar_space](./img/002.png)  
可以发现系统状态栏的区域已经消失,Activity的contentView顶上去占据了原来属于系统状态栏的区域.    
导致虽然`Back` `Title`虽然仍在`Titlebar`区域垂直居中,但视觉效果上受状态栏图标的影响,却不是垂直居中的效果.   

--------------------------------
## fitsSystemWindows : 我并不想用

对`View`添加 `android:fitsSystemWindows`标签可在Android 4.4及后续版本的Android中的。 System windows 顾名思义就是系统窗口，系统在这里显示系统一些属性和操作区域，比如 最上方的状态栏，以及没有实体按键的最下方的虚拟导航栏。


--------------------------------
## fitsSystemWindows



