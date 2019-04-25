import core.F;
import core.F2;
import matrix.impl.DenseDoubleMatrix2D;
import numberAnalysis.DerivedFunction;
import numberAnalysis.SolveEquations;

import java.util.Arrays;

import static numberAnalysis.Integral.simpson38;
import static numberAnalysis.Integral.TrapezoidF;
import static numberAnalysis.OrdinaryDifferential.euler;
import static numberAnalysis.OrdinaryDifferential.rungeKutta;

public class Main {

    static class myF implements F {
        @Override
        public double f(double x) {
            return x * x;
        }
    }

    // 复合梯形积分测试
    public static void test2() {
        double res = TrapezoidF(new myF(), 1, 2, 4000);
        System.out.println(res);
    }

    // 辛普森积分测试
    public static void test3() {
        double res = simpson38(new myF(), 1, 2);
        System.out.println(res);
    }

    static class myF2 implements F2 {
        // x2 表示y 即为y'(x,y)函数
        @Override
        public double f(double x, double x2) {
            return x2 - 2 * x / x2;
        }
    }

    // 龙格-库塔常微分测试
    public static void test4() {
        double[] res = rungeKutta(new myF2(), 0, 1, 10, 1);
        System.out.println(res[10 - 1]);
    }

    static class myF3 implements F {
        @Override
        public double f(double x) {
            return x * x - 2 * x;
        }
    }

    // 二分法解方程测试
    public static void test5() throws Exception {
        double res = SolveEquations.bisect(new myF3(), 0.5, 5, 0.01);
        System.out.println(res);
    }

    // 连续求导测试
    public static void test6() {
        F f1 = new DerivedFunction(new myF3());
        F f2 = new DerivedFunction(f1);
        System.out.println(f1.f(1));
        System.out.println(f2.f(1));
    }

    // 牛顿法迭代求方程测试
    public static void test7() {
        double res = SolveEquations.newton(new myF3(), 0.5, 10);
        System.out.println(res);
    }

    public static void main(String[] args) throws Exception {
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
//        test7();
        DenseDoubleMatrix2D d = new DenseDoubleMatrix2D(new double[][]{
                {1, 3, 5, 7, 9},
                {1, 3, 5, 7, 9},
                {1, 3, 5, 7, 9}
        });
        System.out.println(d.getQuick(1, 1));
        System.out.println(d);
        d.setQuick(2, 4, 11);
        System.out.println(d);
    }
}
