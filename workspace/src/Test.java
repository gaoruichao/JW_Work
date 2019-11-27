import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Test {
    public static void main(String[] args) {


        //全换为人名
        String[] names = { "李明辉", "郭天宇", "林志辉", "李长俊", "季寿孔", "李文轩", "张志贤", "彭东豫", "赵扬", "侯广鑫", "李鑫元", "徐元坤", "邵潇漩",
                "霍明昌","张瑞祥", "何琛选", "张方恒", "付饶", "孙佳琪", "管青青", "王雪梦", "董加硕", "张怡宁", "赵洁", "谢习娅", "任虹", "谷娟娟",
                "褚玉涵","庄敏", "刘兆钰", "宋天傲", "朱彤彤", "王雨欣", "王兆娣", "李华宇", "李笑", "邱莹莹", "刘帅帅", "王楠", "李鑫鑫", "赵树静",
                "刘亚芬","王超燕", "冯乐", "潘明欣", "周润恺", "张成达", "边易洲", "姜瑞临", "许浩君", "蒙景鸿", "李振洋", "马唯峻", "曲鲁辰", "傅立贤",
                "汪子恒","胡庆阳", "张端涌", "徐中堂", "李延杰", "李祎舟"};

        List<List<String>> gl = getGroup(names, 10);

        if(gl != null) {
            for (List<String> l : gl) {
                System.out.println(l);
            }
        }
    }

    /**
     * 获取随机数
     */
    public static int getRandom(int i) {
        Random r = new Random();
        return r.nextInt(i);
    }

    /**
     * 进行分组 els 需要进行分组的成员 groups 需要分成几组
     */
    public static List<List<String>> getGroup(String[] els, int groups) {
        // 判断验证
        if (els.length < (groups * 2)) {
            System.out.println("分组数过多! 最多只能分" + (els.length / 2) + "组");
            return null;
        }
        if (groups == 1) {
            System.out.println("分组数不能为1组");
            return null;
        }
        // 数据源的list
        List<String> list = new ArrayList<String>();
        // 作为结果返回的list
        List<List<String>> groupsList = new ArrayList<List<String>>();
        // List<Map<String, String>> groupsList = new ArrayList<List<String>>();
        // 往数据源里面添加数据
        for (int i = 0; i < els.length; i++) {
            list.add(els[i]);
        }
        // 随机打乱一下顺序
        Collections.shuffle(list);
        // 计算一下每组多少人
        int peoples = els.length / groups;
        // 分组开始
        for (int i = 0; i < groups; i++) {
            List<String> group = new ArrayList<String>();
            for (int j = 0; j < peoples; j++) {
                int random = getRandom(list.size());
                group.add(list.get(random));
                list.remove(random);
            }
            groupsList.add(group);
        }
        // 最后剩下的人再重新分配一遍
        for (int i = 0; i < list.size(); i++) {
            groupsList.get(i).add(list.get(i));
        }
        return groupsList;
    }
}
