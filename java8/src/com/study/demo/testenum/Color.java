package com.study.demo.testenum;

/**
 * @Classname Color
 * @Description 枚举类型的使用，当数据库有多个状态时，不需要再前端判断
 * @Date 2019/7/31 11:00
 * @Created by Ms.zheng
 */

public enum Color {
    RED(1,"hongse"),BLUE(2,"蓝色"),BLACK(3,"黑色");

    private int id;
    private String namre;

    Color(int id, String namre) {
        this.id = id;
        this.namre = namre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamre() {
        return namre;
    }

    public void setNamre(String namre) {
        this.namre = namre;
    }

    public static Color  getcolor( int id){

        Color[] values = Color.values();
        for (Color color: values){
          if (id==color.getId()){
              return color;
          }
        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println(getcolor(1).getNamre());
    }
}
