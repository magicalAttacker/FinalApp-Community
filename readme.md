#应用学习
这是一个用于学习的分享项目
在这里，你可以客制化出你自己的android大课设
首先，你需要得到专用的key来客制化你的应用
key的获取方式需要社会工程学技术来完成
得到key后，怎么用，需要怎样客制化你的资源文件如下F&Q

1. key的填入位置：java/ml/magicalattacker/finalapp/ui/notifications/NotificationsViewModel.java 第59行 final String key = "这里填入你的key";
2. 资源文件：你需要在该位置：app/src/main/res/drawable 文件夹中创建 a.jpg b.jpg c.jpg .. f.jpg 作为你的商品图片资源文件, 并在 app/src/main/java/ml/magicalattacker/finalapp/ui/home/HomeViewModel.java 文件中对应地修改你的商品内容