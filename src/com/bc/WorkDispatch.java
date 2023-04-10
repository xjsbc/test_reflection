package com.bc;

import java.util.Scanner;
import java.util.TreeSet;

public class WorkDispatch {
    static class Work{
        public String name;//作业名
        public double ctime;//到达时间
        public double stime;//服务时间
        public double ftime;//完成时间
        public double ztime;//周转时间
        public double dtime;//带权周转时间
        public double wtime;//等待时间
        public double rratio;//响应比

        public Work(){}
    }
    private static double sumztime,sumdtime;
    private static double avgztime,avgdtime;
    private static Scanner cin = new Scanner(System.in);

    public static void input(Work[] p, int n) {
        TreeSet treeSet = new TreeSet();
        treeSet.last();
        treeSet.lower();
        System.out.println("请输入作业信息: ");
        for(int i=0;i<n;i++) {
            Work work = new Work();
            p[i] = work;
            System.out.println("作业名: ");
            p[i].name = cin.next();
            System.out.println("到达时间: ");
            p[i].ctime = cin.nextDouble();
            System.out.println("服务时间: ");
            p[i].stime = cin.nextDouble();
        }
    }

    public static void datap(Work[] p,int n) {
        sumztime=sumdtime=0;
        p[0].ftime =p[0].ctime +p[0].stime ;
        for(int i=1;i<n;i++) {
            p[i].ftime=p[i-1].ftime+p[i].stime;
        }
        for(int j=0;j<n;j++) {
            p[j].ztime =p[j].ftime -p[j].ctime ;
            p[j].dtime =p[j].ztime /p[j].stime ;
            sumztime+=p[j].ztime;
            sumdtime+=p[j].dtime;
        }
        avgztime=sumztime/n;
        avgdtime=sumdtime/n;
    }

    public static void output(Work[] p,int n) {
        System.out.println("作业调度顺序: ");
        for(int k=0;k<n;k++) {
            System.out.print(p[k].name);
        }
        System.out.println();
        System.out.println("平均周转时间=" + avgztime);
        System.out.println("平均带权周转时间=" + avgdtime);
    }


    public static void sort(Work[] p,int n) {
        for(int i=n-1;i>=1;i--) {
            for(int j=0;j<i;j++) {
                if (p[j].ctime > p[j + 1].ctime) {
                    Work temp;
                    temp = p[j];
                    p[j] = p[j + 1];
                    p[j + 1] = temp;
                }
            }
        }
    }

    public static void fcfs(Work[] p,int n) {
        sort(p,n);
        datap(p,n);
        System.out.println("先来先服务算法");
        output(p,n);
    }

    public static void sjf(Work[] p,int n) {
        sort(p,n);
        for(int i=0;i<n-1;i++) {
            int k=0;
            if(i==0)
                p[i].ftime =p[i].ctime +p[i].stime ;
            else
                p[i].ftime =p[i].stime +p[i-1].ftime ;
            for(int j=i+1;j<n;j++) {
                if(p[j].ctime<=p[i].ftime )
                    k++;
            }
            double minstime=p[i+1].stime ;
            int ps=i+1;
            for(int m=i+1;m<i+k;m++) {
                if(p[m+1].stime<minstime) {
                    minstime=p[m+1].stime;
                    ps=m+1;
                }
            }
            Work temp;
            temp=p[i+1];
            p[i+1]=p[ps];
            p[ps]=temp;
        }
        datap(p,n);
        System.out.println("短作业优先算法: ");
        output(p,n);
    }

    public static void hrf(Work[] p,int n) {
        sort(p,n);
        for(int i=0;i<n-1;i++) {
            int k=0;
            if(i==0)
                p[i].ftime =p[i].ctime +p[i].stime ;
            else
                p[i].ftime =p[i].stime +p[i-1].ftime ;
            for(int j=i+1;j<n;j++) {
                if(p[j].ctime <=p[i].ftime )
                    k++;
            }
            double maxrratio=(p[i].ftime -p[i+1].ctime )/p[i+1].stime ;
            int ps=i+1;
            for(int m=i+1;m<i+k;m++) {
                if((p[i].ftime -p[m+1].ctime)/p[m+1].stime >=maxrratio) {
                    maxrratio=(p[i].ftime -p[m+1].ctime)/p[m+1].stime;
                    ps=m+1;
                }
            }
            Work temp;
            temp=p[i+1];
            p[i+1]=p[ps];
            p[ps]=temp;
        }
        datap(p,n);
        System.out.println("高响应比优先算法: ");
        output(p,n);
    }

    public static void main(String[] args) {
        int n;
        System.out.println("输入作业数目: ");
        n = cin.nextInt();
        Work[] works = new Work[n];
        input(works,n);
        fcfs(works,n);
        System.out.println("=======================================");
        sjf(works,n);
        System.out.println("=======================================");
        hrf(works,n);
    }
}