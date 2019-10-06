package com.design;

public class Lesson12 {
    public static void main(String[] args) {
        MediaPlay mediaPlay = new VideoPlay("射雕英雄传", "郭靖大战欧阳锋");
        MediaPlay mediaPlay1 = new BarrageRedVideoPlay(mediaPlay);
        mediaPlay.play(); // 不加装饰时
        mediaPlay1.play(); // 添加弹幕装饰时
    }
}
/**
 * 定义 媒体播放 抽象类，用于模拟多媒体播放功能（Component 抽象构件角色）
 */
abstract class MediaPlay {

    public abstract String getMediaName(); // 获取要播放的媒体文件名称

    public abstract int getMediaSeconds(); // 获取要播放的媒体文件的播放时长（s）

    public abstract String getMediaContent(); // 获取需要播放的媒体文件内容

    // 模拟媒体播放
    public final void play() {
        System.out.println("Media：" + getMediaName() + "(累计时长：" + getMediaSeconds() + " 秒) 正在播放");
        try {
            for (int i = 1; i <= getMediaSeconds(); i++) {
                Thread.sleep(1000);
                System.out.println("当前播放第 " + i + " 秒，" + getMediaContent() + "...");
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
/**
 * 视频播放类，模拟视频播放功能，属于真正被装饰的角色
 */
class VideoPlay extends MediaPlay {

    private String videoContent; // 播放的视频内容
    private String videoName; // 播放的视频名称

    public VideoPlay(String videoName, String videoContent) {
        this.videoName = videoName;
        this.videoContent = videoContent;
    }

    @Override
    public String getMediaName() { // 获取媒体文件（视频）名称
        return videoName;
    }

    @Override
    public int getMediaSeconds() { // 获取媒体文件的播放时长
        return videoContent.length();
    }

    @Override
    public String getMediaContent() { // 获取媒体文件的播放内容
        return videoContent;
    }
}
// 弹幕视频播放：定义弹幕播放的抽象角色，具体的弹幕实现交给子类
abstract class BarrageVideoPlay extends MediaPlay{

    protected MediaPlay mediaPlay;

    public BarrageVideoPlay(MediaPlay mediaPlay) {
        this.mediaPlay = mediaPlay;
    }
}
// 实现弹幕播放的类，具体的装饰器的实现类，这里我们使用红色字体进行弹幕视频播放
class BarrageRedVideoPlay extends BarrageVideoPlay{

    public BarrageRedVideoPlay(MediaPlay mediaPlay) {
        super(mediaPlay);
    }

    @Override
    public String getMediaName() {
        return mediaPlay.getMediaName() + "（已开启弹幕）";
    }

    @Override
    public int getMediaSeconds() {
        return mediaPlay.getMediaSeconds();
    }

    @Override
    public String getMediaContent() {
        return "---+++*** " + mediaPlay.getMediaContent() + "（弹幕中）---+++***";
    }
}