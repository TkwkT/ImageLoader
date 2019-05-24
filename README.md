# ImageLoader
### 结构
#### ImageLoaderBuilder
ImageLoaderBuilder 用于进行各种需求的配置，例如加载错误时的图片等（还未完全，待扩展）
#### ImageLoader
ImageLoader 传入context，将Fragment或FragmentActivity的生命周期绑定到LifeManager上。
#### LifeManager
LifeManager 监听Fragment和FragmentActivity的生命周期变化。在fragment或fragmentActivity onDestroy时销毁。

cache包进行缓存，别的缓存策略还待扩展。
