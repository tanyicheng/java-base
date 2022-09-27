package com.barrett.gof.多态;

public class Polymorphic {
    public static void main(String[] args) {
        Task task = new Task();
//        task.setWftKey("com.barrett.gof.多态.DuiGong");
        task.setWftKey("com.barrett.gof.多态.ChengPing");

        Action action = new Action();
        action.set(task.getWftKey());
        action.work();

        //获取类名
        String name = ChengPing.class.getSimpleName();
        System.out.println(name);
        System.out.println(new StringBuilder().append(Character.toLowerCase(name.charAt(0))).append(name.substring(1)).toString());
    }

    public String toLowerCaseFirstOne(String name){
        return new StringBuilder().append(Character.toLowerCase(name.charAt(0))).append(name.substring(1)).toString();
    }
}

class Action {
    public IAction action;

    public void set(String objKey) {
        Class<DuiGong> c = null;
        try {
            c = (Class<DuiGong>) Class.forName(objKey);
            action = c.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void work() {
        action.notice();
    }
}

//通用的任务类
class Task {
    public Action action;
    public String wftKey;

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getWftKey() {
        return wftKey;
    }

    public void setWftKey(String wftKey) {
        this.wftKey = wftKey;
    }

}

interface IAction {

    void notice();
}

//对公付款
class DuiGong implements IAction {

    @Override
    public void notice() {
        System.out.println("对公付款");
    }
}

//成品结批
class ChengPing implements IAction {

    @Override
    public void notice() {
        System.out.println("成品结批");
    }
}