package cn.org.rapid_framework.generator;

public class GeneratorMain {
    /**
     * 请直接修改以下代码调用不同的方法以执行相关生成任务.
     */
    public static void main(String[] args) throws Exception {
        GeneratorFacade g = new GeneratorFacade();
        //删除生成器的输出目录
        g.deleteOutRootDir();
        //通过数据库表生成文件,template为模板的根目录
        g.generateByTable("sys_func", "template");
        g.generateByTable("sys_role", "template");
        g.generateByTable("sys_role_func", "template");
        g.generateByTable("sys_user", "template");
        g.generateByTable("sys_user_role", "template");
        //打开文件夹
        Runtime.getRuntime().exec("cmd.exe /c start " + GeneratorProperties.getRequiredProperty("outRoot"));
    }
}
