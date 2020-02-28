package K_means;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Algorithm {
    private Integer K = 2;//聚类中心个数
    private ArrayList<Node>nodeToParse = new ArrayList<>();
    private ArrayList<Node>result1 = new ArrayList<>();
    private ArrayList<Node>result2 = new ArrayList<>();

    private double distance(Node node1, Node node2){
        double a = Math.abs(node1.x1 - node2.x1);
        double b = Math.abs(node1.x2 - node2.x2);
        a = Math.pow(a,2);
        b = Math.pow(b,2);
        return Math.sqrt(a+b);
    }

    private Node center(ArrayList<Node>calculate){
        double size = calculate.size();
        double x1_sum = 0;
        double x2_sum = 0;
        for(int i=0;i<size;i++){
            x1_sum += calculate.get(i).x1;
            x2_sum += calculate.get(i).x2;
        }
        x1_sum = x1_sum/size;
        x2_sum = x2_sum/size;

        Node result = new Node(x1_sum,x2_sum,-1);
        return result;
    }

    public static void main(String[] args) throws IOException {
        Algorithm K_means = new Algorithm();
        Scanner sc = new Scanner(System.in);

        System.out.println(K_means.K);
        //假定有20个点，读入这20个点
        for(int i=0; i<20;i++){
            int x1 = sc.nextInt();
            int x2 = sc.nextInt();
            int nodeID = i+1;
            System.out.println("Node " + nodeID + " recorded.");
            Node tmp = new Node(x1, x2, nodeID);
            K_means.nodeToParse.add(tmp);
        }
        System.out.println("Sum of nodes is: " + K_means.nodeToParse.size());

        //根据要分类的数目，设置中心点
        Node anchor1 = K_means.nodeToParse.get(0);
        Node anchor2 = K_means.nodeToParse.get(1);
        Node anchor1_hat;
        Node anchor2_hat;

        while(true) {

            for (int i = 0; i < K_means.nodeToParse.size(); i++) {
                double distance1 = K_means.distance(anchor1, K_means.nodeToParse.get(i));
                double distance2 = K_means.distance(anchor2, K_means.nodeToParse.get(i));

                //如果离聚类中心1更近
                if (distance1 <= distance2) {
                    K_means.result1.add(K_means.nodeToParse.get(i));
                } else {
                    K_means.result2.add(K_means.nodeToParse.get(i));
                }

            }

            anchor1_hat = K_means.center(K_means.result1);
            anchor2_hat = K_means.center(K_means.result2);

            if(anchor1.x1 == anchor1_hat.x1 && anchor2.x1 == anchor2_hat.x1 &&
                    anchor1.x2 == anchor1_hat.x2 && anchor2.x2 == anchor2_hat.x2){
                break;
            }
            else{
                anchor1 = anchor1_hat;
                anchor2 = anchor2_hat;
                K_means.result1.clear();
                K_means.result2.clear();
            }


        }

        for(int i=0; i<K_means.result1.size();i++){
            System.out.println(K_means.result1.get(i).nodeID);
        }

    }
}
