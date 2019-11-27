public class Test{
    public static void main(String[] args)
    {
        int n=10000;//输入n 即输入n利用公式近似计算Π
        int i=10000;//定义Int类型 i 公式中的次幂
        double j=0;//定义一个double类型 j 用于代替公式中-1的i+1次方
        double h=0;//定义double 类型 h 用于代替公式中2*i-1

        for (n=10000;n<=100000;n+=10000){
            double k=0;//定义double 类型 k 用于代替公式中的 累加和
            for (i=1;i<=n;i++){//for 循环 来累加 单个公式的和，并利用循环更新次幂，分母，分子的数值
                j=Math.pow(-1,i+1);//更新 分子
                h=2*i-1;//更新分母
                k+=j/h;
            }//进行单部分公式的累加
            double f=4*k;
            System.out.println(f);//输出K值 最后循环结果 语句放在For循环外 k值即为利用公式所求的Π的近似值
        }
    }
}

