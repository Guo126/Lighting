package com.dianmo.flash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Graph {

    private Map<String, Vertex> vertexsMap;
    private int index = 0;
    private Edge [] road = new Edge[14];
    private Edge [] road1 = new Edge[14];
    private Edge [] road2 = new Edge[14];
    private String [] path = new String [14];
    private String [] path1 = new String [14];
    private String [] path2 = new String [14];
    private StringBuilder thePath = new StringBuilder();
    private int totalLen = 0 ,totalPo = 0;
    private int totalPri = 0;
    private  StringBuilder shortRoad = new StringBuilder();
    private  ArrayList<Edge> ar = new ArrayList<Edge>();
    boolean hasRoad =true;
    private int num = 0;
    private Edge temp;
    private StringBuilder thePath2 = new StringBuilder();

    private class Vertex {
        private String name;        //顶点名称
        private Edge next;          //下一段弧

        Vertex(String name, Edge next) {
            this.name = name;
            this.next = next;
        }
    }

    private class Edge {
        private String name;        //被指向顶点名称
        private int weight;         //弧的权值
        private int price;
        private int num;
        private Edge next;          //下一段弧
        private boolean isCheck;


        Edge(String name, int weight ,int price,int num,Edge next) {
            this.name = name;
            this.weight = weight;
            this.price = price;
            this.num = num;
            this.next = next;
            this.isCheck = false;
        }
    }




    public Graph() {
        this.vertexsMap = new HashMap<>();
        for(int i=0;i<13;i++){
            road[i] = new Edge("111",0,0,1,null);
            path[i] = " ";
            road1[i] = new Edge("111",0,0,1,null);
            path1[i] = " ";
            road2[i] = new Edge("111",0,0,1,null);
            path2[i] = " ";
        }
    }



    public void insertVertex(String vertexName) {                //添加顶点
        Vertex vertex = new Vertex(vertexName, null);
        vertexsMap.put(vertexName, vertex);
    }

    public void insertEdge(String begin, String end, int price,int weight) {           //添加弧
        Vertex beginVertex = vertexsMap.get(begin);
        if (beginVertex == null) {
            beginVertex = new Vertex(begin, null);
            vertexsMap.put(begin, beginVertex);
        }
        Edge edge = new Edge(end, weight, price,1,null);
        if (beginVertex.next == null) {
            beginVertex.next = edge;
        } else {
            Edge lastEdge = beginVertex.next;
            while (lastEdge.next != null) {
                lastEdge = lastEdge.next;
            }
            lastEdge.next = edge;
        }
    }




    public int[] getRoute(String start, String end) {
        int[] data = new int[2];
        findRoad(start);
        int o=0;
        for(int i = 0;i<13;i++) {
            if(road[i].isCheck&&road[i].name.equals(end)){
                data[0] =  road[i].weight;
                o=1;
                break;
            }
        }

        for(int i = 0;i<13;i++) {
            if(road1[i].isCheck&&road1[i].name.equals(end)){
                data[1] = road1[i].price;
                break;
            }
        }
        if(o==0){
            hasRoad = false;
            data[0] = 0;
        }
        return data;
    }

    public StringBuilder getPath(String end){
        thePath.append("#"+end);
        for(int i = 0;i<13;i++) {
            if(road[i].isCheck&&road[i].name.equals(end)){
                 getPath(path[i]);
                 break;
            }
        }
        return thePath;
    }

    public StringBuilder getPath2(String end){

        thePath2.append("#"+end);
        for(int i = 0;i<13;i++) {
            if(road2[i].name.equals(end)){
                 getPath2(path2[i]);
                 break;
            }
        }
        return thePath2;
    }

    public void getShort(String start,String end){
        for (String s : vertexsMap.keySet()) {
            if (s.equals(start)) {
                temp = vertexsMap.get(s).next;
                while (temp != null) {
                    if(!temp.isCheck){
                        temp = temp.next;
                    }
                    temp.isCheck = false;
                    int x=0;
                    for(int i = 0;i<13;i++) {
                        if (road2[i].name.equals(start)) {
                            totalPo = road2[i].num;
                            for(int m = 0;m<13;m++) {
                                if (road2[m].name.equals(temp.name)) {
                                    if((totalPo + temp.num) < road2[m].num){
                                        path2[m] = start;
                                    }
                                    road2[m].num = (totalPo + temp.num) < road2[m].num ? (totalPo + temp.num) : road2[m].num;
                                    x = 1;
                                }
                            }
                        }
                    }
                    if(x==0){
                        path2[index] = start;
                        road2[index].name = temp.name;
                        road2[index].next = temp.next;
                        road2[index].num =totalPo+temp.num;
                        road2[index].isCheck = temp.isCheck;
                        index+=1;
                    }
                    ar.add(temp);
                    temp = temp.next;
                }
            }
        }

    }

    public StringBuilder shortRoad(String start,String end){
        index=0;
        shortRoad.append(start+"#");
        getShort(start,end);
        while (!ar.isEmpty()) {
            getShort(ar.get(0).name,end);
            ar.remove(0);
        }
        for(int i = 0;i<road2.length;i++){
            if(end.equals(road2[i].name)){
                return getPath2(end);
            }
        }
        return  new StringBuilder();
    }


    public void findRoad(String start) {

        if (vertexsMap.keySet().contains(start)) {

            for (String s : vertexsMap.keySet()) {
                if (s.equals(start)) {
                    Edge temp = vertexsMap.get(s).next;
                    while (temp != null) {
                        if (temp.isCheck) {
                            temp = temp.next;
                        }
                        temp.isCheck = true;
                        int x = 0;
                        for (int i = 0; i < 13; i++) {
                            if (road[i].name.equals(start)) {
                                totalLen = road[i].weight;
                                for (int m = 0; m < 13; m++) {
                                    if (road[m].name.equals(temp.name)) {
                                        if ((totalLen + temp.weight) < road[m].weight) {
                                            path[m] = start;
                                        }
                                        road[m].weight = (totalLen + temp.weight) < road[m].weight ? (totalLen + temp.weight) : road[m].weight;
                                        x = 1;
                                    }
                                }
                            }

                            if (road1[i].name.equals(start)) {
                                totalPri = road1[i].price;
                                for (int m = 0; m < 13; m++) {
                                    if (road1[m].name.equals(temp.name)) {
                                        if ((totalPri + temp.weight) < road1[m].price) {
                                            path1[m] = start;
                                        }
                                        road1[m].price = (totalPri + temp.price) < road1[m].price ? (totalPri + temp.price) : road1[m].price;
                                        x = 1;
                                    }
                                }
                            }

                        }
                        if (x == 0) {
                            path[index] = start;
                            road[index].name = temp.name;
                            road[index].next = temp.next;
                            road[index].weight = totalLen + temp.weight;
                            road[index].isCheck = temp.isCheck;

                            path1[index] = start;
                            road1[index].name = temp.name;
                            road1[index].next = temp.next;
                            road1[index].price = totalPri + temp.price;
                            road1[index].isCheck = temp.isCheck;
                            index += 1;
                        }

                        findRoad(temp.name);
                        temp = temp.next;
                    }
                    break;
                }
            }

        } else {
                hasRoad = false;
        }
    }

}





