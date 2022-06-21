import com.google.common.base.Function;
import com.google.common.graph.Graph;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.*;
import java.util.Scanner;
import java.util.Set;


public class GUI {public static void main(String[] args) {

//    driver = new ChromeDriver();
    // driver.get(url);
    String a = null;
    final JFrame f = new JFrame("登录界面");
    //设置大小
    f.setSize(800, 400);
    f.setLocationRelativeTo(null);
    //加载默认字体
    loadIndyFont();

    //背景图设置
    ImageIcon img=new ImageIcon("yzm\\src\\images\\trs.png");
    JLabel label=new JLabel(img);
    label.setSize(img.getIconWidth(),img.getIconHeight());
    f.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
    //把窗口面板设定为内容面板并设为透明、流动布局
    JPanel pan=(JPanel)f.getContentPane();
    pan.setOpaque(false);
    pan.setLayout(new FlowLayout());

//                    固定坐标
//                    f.setLocation(200, 200);
    //f.setAlwaysOnTop(true);
    f.getContentPane().setLayout(null);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //测试按钮构造
    JButton testButton = new JButton("测试");
    testButton.setBounds(200, 170, 150, 50);
    testButton.setLocation(200, 170);
    //返回按钮构造
    JButton returnButton = new JButton("返回");
    returnButton.setBounds(200, 170, 100, 50);
    returnButton.setLocation(620, 20);
    //配置按钮构造
    JButton deployButton = new JButton("配置");
    deployButton.setBounds(200, 170, 100, 50);
    deployButton.setLocation(450, 20);
    //配置按钮实现
    deployButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            final JTextArea jta = new JTextArea(30, 1);
            //调用测试界面,新建一个脚本界面
            final JFrame f2 = new JFrame("脚本界面");
            //背景图设置
            ImageIcon img=new ImageIcon("yzm\\src\\images\\background2.jpg");
            JLabel label=new JLabel(img);
            label.setSize(img.getIconWidth(),img.getIconHeight());
            f2.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
            //把窗口面板设定为内容面板并设为透明、流动布局
            JPanel pan=(JPanel)f2.getContentPane();
            pan.setOpaque(false);
            pan.setLayout(new FlowLayout());
            //输出字段
            String[] Output;
            //界面大小，1500*1000
            f2.setSize(1500, 1000);
            f2.setLocationRelativeTo(null);
//          f2.setLocation(200, 200);
            //始终保持最前
            //f2.setAlwaysOnTop(true);
            f2.getContentPane().setLayout(null);
            f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //实例化文本框
            //final JTextArea jta = new JTextArea(30, 1);
            //在文本框上添加滚动条-测试框体
            jta.setFont(new Font("微软雅黑",  Font.TYPE1_FONT, 26));
            //设置脚本框背景颜色-测试框体
            jta.setBackground(new Color(193,210,240));
            //设置选中文字的颜色和背景色-测试框体
            jta.setSelectedTextColor((Color.red));
            jta.setSelectionColor(Color.green);
            //自动换行，暂时屏蔽
//            jta.setLineWrap(true);
            JScrollPane jsp = new JScrollPane(jta);
            //设置矩形大小.参数依次为(矩形左上角横坐标x,矩形左上角纵坐标y，矩形长度，矩形宽度)
            jsp.setBounds(100, 150, 1300, 500);
            //默认的设置是超过文本框才会显示滚动条，以下设置让滚动条一直显示
            jsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            jsp.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            //将配置内容展示到页面
            String docname = "请输入文件名";
            docname = "yzm\\src\\Deploy\\Deploy.txt";
            String line = null;
            File f = new File(docname);
            if (!f.exists()) {
                JOptionPane.showMessageDialog(null, "文件不存在", "提示", JOptionPane.WARNING_MESSAGE);
            } else {
                //清空文本域数据
                jta.setText(null);
                //读取数据文件
                File file2 = new File(docname);
                //----------------------------
                try {
                    try (Scanner sc = new Scanner(new FileReader(file2))) {
                        while (sc.hasNextLine()) {  //按行读取字符串
                            line = sc.nextLine() + "\n";
                            //System.out.println(line);
                            jta.append(line);
                        }
                    }
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
            //保存按钮
            JButton saveButton = new JButton("保存");
            saveButton.setBounds(200, 170, 140, 40);
            saveButton.setLocation(550, 750);
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //逐行转译JTextArea里输入的值。
                    String[] lineString = new String[10000];
                    String[] lineString_text = jta.getText().split("\n");
                    int g = 0;
                    for( int i = 0; i<=lineString_text.length - 1;i++){
                        lineString[i] = lineString_text[i];
                    }
                    String s = jta.getText();//获取文本框中内容
                    FileWriter out;
                    try {
                        out = new FileWriter("yzm\\src\\Deploy\\Deploy.txt");
                        out.write(s);//将文本内容保存到文件中
                        out.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });

            //取消按钮
            JButton calButton = new JButton("取消");
            calButton.setBounds(200, 170, 140, 40);
            calButton.setLocation(850, 750);
            calButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //取消按钮，返回前窗口
//                    f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            });

            //添加测试脚本页jframe资源
            //把滚动条添加到容器里面
            f2.add(jsp);
            //f2.add(jsp2);
            f2.add(saveButton);
            f2.add(calButton);
            //f2.revalidate();
            f2.setVisible(true);
            //退出窗口不关闭程序
            f2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            //逐行获取输入框内容

        }
    });
    //返回按钮暂无实现
    returnButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        }
    });
    //测试按钮逻辑实现
    testButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            //创建全局输入框，便于导入导出按钮设置对应文字数据
            final JTextArea jta = new JTextArea(30, 1);
            //调用测试界面,新建一个脚本界面
            final JFrame f2 = new JFrame("脚本界面");
            //背景图设置
            ImageIcon img=new ImageIcon("yzm\\src\\images\\background2.jpg");
            JLabel label=new JLabel(img);
            label.setSize(img.getIconWidth(),img.getIconHeight());
            f2.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
            //把窗口面板设定为内容面板并设为透明、流动布局
            JPanel pan=(JPanel)f2.getContentPane();
            pan.setOpaque(false);
            pan.setLayout(new FlowLayout());
            //输出字段
            String[] Output;
            //界面大小，1500*1000
            f2.setSize(1500, 1000);
            f2.setLocationRelativeTo(null);
//          f2.setLocation(200, 200);
            //始终保持最前
            //f2.setAlwaysOnTop(true);
            f2.getContentPane().setLayout(null);
            f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//            //添加脚本按钮
//            final JButton addButton = new JButton("添加");
//            addButton.setBounds(200, 170, 140, 40);
//            addButton.setLocation(100, 50);
//            //删除脚本按钮
//            JButton delButton = new JButton("删除");
//            delButton.setBounds(200, 170, 140, 40);
//            delButton.setLocation(300, 50);
            //取消按钮
            JButton calButton = new JButton("取消");
            calButton.setBounds(200, 170, 140, 40);
            calButton.setLocation(300, 50);
            //导入按钮
            JButton importButton = new JButton("导入");
            importButton.setBounds(200, 170, 140, 40);
            importButton.setLocation(500, 50);
            importButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String docname = "请输入文件名";
                    //输出数据存放
                    String line = null;
                    //手动输入文件名
                    docname = pause(docname);
                    //配置对应文件存放路径
                    docname = "yzm\\src\\Deploy\\" + docname + ".txt";
                   // docname = "d:\\" + docname + ".xml";
                    File f = new File(docname);
                    if (!f.exists()) {
                        JOptionPane.showMessageDialog(null, "文件不存在", "提示", JOptionPane.WARNING_MESSAGE);
                    } else {
                        //清空文本域数据
                        jta.setText(null);
                        //读取数据文件
                        File file2 = new File(docname);
                        //----------------------------
                        try {
                            try (Scanner sc = new Scanner(new FileReader(file2))) {
                                while (sc.hasNextLine()) {  //按行读取字符串
                                    line = sc.nextLine() + "\n";
                                    //System.out.println(line);
                                    jta.append(line);
                                }
                            }
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                    }
                }
            });
            //导出按钮
            JButton exportButton = new JButton("导出");
            exportButton.setBounds(200, 170, 140, 40);
            exportButton.setLocation(700, 50);
            exportButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //获取输入框所有文字
                    String str=jta.getText();
                    String docname = "请输入文件名";
                    //手动输入文件名
                    docname = pause(docname);
                    //配置对应文件存放路径
                    docname = "yzm\\src\\Deploy\\" + docname + ".txt";
                    try {
                        File writeName = new File(docname); // 相对路径，如果没有则要建立一个新的output.txt文件
                        if(!writeName.exists()) {
                            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
                        }
                        FileWriter writer = new FileWriter(writeName);
                        BufferedWriter out = new BufferedWriter(writer);
                        out.write(str);
                        out.flush(); // 把缓存区内容压入文件
                        out.close();
                    } catch (IOException d) {
                        d.printStackTrace();
                    }
                }
            });
            //实例化文本框
            //final JTextArea jta = new JTextArea(30, 1);
            //在文本框上添加滚动条
            final JTextArea jta2 = new JTextArea(30, 1);
            jta2.setFont(new Font("微软雅黑",  Font.TYPE1_FONT, 26));
            //设置选中文字的颜色和背景色
            jta2.setSelectedTextColor((Color.red));
            jta2.setSelectionColor(Color.green);
            //设置日志输出框背景颜色
            jta2.setBackground(new Color(193,210,240));
            jta2.setWrapStyleWord(true);
            //自动换行 暂时屏蔽
//            jta2.setLineWrap(true);
            JScrollPane jsp2 = new JScrollPane(jta2);
            //设置矩形大小.参数依次为(矩形左上角横坐标x,矩形左上角纵坐标y，矩形长度，矩形宽度)
            jsp2.setBounds(100, 700, 1100, 200);
            //默认的设置是超过文本框才会显示滚动条，以下设置让滚动条一直显示
            jsp2.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            jsp2.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            //实例化文本框
            //final JTextArea jta = new JTextArea(30, 1);
            //在文本框上添加滚动条
            jta.setFont(new Font("微软雅黑",  Font.TYPE1_FONT, 26));
            //设置脚本框背景颜色
            jta.setBackground(new Color(193,210,240));
            //设置选中文字的颜色和背景色
            jta.setSelectedTextColor((Color.red));
            jta.setSelectionColor(Color.green);
            //自动换行，暂时屏蔽
//            jta.setLineWrap(true);
            JScrollPane jsp = new JScrollPane(jta);
            //设置矩形大小.参数依次为(矩形左上角横坐标x,矩形左上角纵坐标y，矩形长度，矩形宽度)
            jsp.setBounds(100, 150, 1100, 500);
            //默认的设置是超过文本框才会显示滚动条，以下设置让滚动条一直显示
            jsp.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            jsp.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            //说明按钮
            JButton explainButton = new JButton("说明");
            explainButton.setBounds(200, 170, 140, 40);
            explainButton.setLocation(900, 50);
            explainButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    final JFrame f3 = new JFrame("提示");
                    f3.setSize(600, 500);
                    f3.setLocationRelativeTo(null);
//                    f3.getContentPane().setLayout(null);
                    f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    String message = null;
                    StringBuffer sb = null;
                    sb = new StringBuffer("<html>输入方法由：函数名，xpath，参数A，参数B构成<br/>" +
                            "目前支持函数：<br/>open,http(打开对应网站)<br/>input,xpath,输入参数(对应内容输入)<br/>" +
                            "click,xpath(点击对应按钮)<br/>switch(切换至活动页)<br>pagedown(页面下移)<br/>" +
                            "yzm(输入弹框，主要应用于验证码等，需要手动输入)<br/>" +
                            "for,time(for循环，time循环次数，以end脚本结尾)<br/>" +
                            "end(for循环，for脚本与end脚本间的语句 循环执行)<br/></html>");
                    JLabel label = new JLabel();
                    label.setFont(new Font("微软雅黑", Font.TYPE1_FONT, 20));
                    label.setText(sb.toString());
                    JPanel panel = new JPanel();
                    panel.add(label);
                    f3.add(panel);
                    f3.setVisible(true);
                    //退出窗口不关闭程序
                    f3.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//                    f3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            });
            //运行按钮
            JButton runButton = new JButton("运行");
            runButton.setBounds(200, 170, 140, 40);
            runButton.setLocation(100, 50);
            //运行按钮实现
            runButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //逐行转译JTextArea里输入的值。
                    String[] lineString = new String[10000];
                    String[] lineString_text = jta.getText().split("\n");
                    for( int i = 0; i<=lineString_text.length - 1;i++){
                        lineString[i] = lineString_text[i];
                    }
                    //中置字符串，存放for循环重复脚本数据
                    String[] repeat = new String[10000];
                    //定义selenuim-driver
                    WebDriver driver = null;
                    //-------------读取对应配置文件，可以活动配置-----------
                    //参数配置区域------------------------------
                    String ChromeDriver = null;   //chromedriver配置参数

                    //参数配置区域------------------------------
                    String docname = "请输入文件名";
                    //输出数据存放
                    String line = null;
                    //配置对应文件存放路径
                    docname = "yzm\\src\\Deploy\\Deploy.txt";
                    // docname = "d:\\" + docname + ".xml";
                    File f = new File(docname);
                    if (!f.exists()) {
                        JOptionPane.showMessageDialog(null, "文件不存在", "提示", JOptionPane.WARNING_MESSAGE);
                    } else {
                        //读取配置文件
                        File file2 = new File(docname);
                        //----------------------------
                        try {
                            try (Scanner sc = new Scanner(new FileReader(file2))) {
                                while (sc.hasNextLine()) {  //按行读取字符串
                                    line = sc.nextLine() + "\n";
                                    String[] part = line.split(",");
                                    if(part[0] == "chromedriver"){    //获取chromedriver参数配置
                                        ChromeDriver = part[1];
                                    }
                                }
                            }
                        } catch (FileNotFoundException fileNotFoundException) {
                            fileNotFoundException.printStackTrace();
                        }
                    }

                    //-------------读取对应配置文件，可以活动配置-----------

                    //message = pause(message);
                    //System.out.println(message);
//                    System.setProperty("webdriver.chrome.driver", message);

                    //System.setProperty("webdriver.chrome.driver", ChromeDriver);
                    System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
                    //清空日志记录
                    jta2.setText(null);
                    //

                    //输出分解，遍历文本框脚本内容
                    for(int i =0 ; i <= lineString.length ; i++) {
                        //根据"，"分割字段，分别存放
                        String x = lineString[i];
                        //将repeat附有前置脚本的值，直到for
                        String[] part = x.split(",");
                        //参数个数计数值
                        int j;
                        for ( j = 0; j < part.length; j++) {
//                            System.out.println(part[j]+"分割");
                        }
                    //函数实现，拆分后，以A（函数名），B（应用类型），C（Xpath），D（字段1），E（字段1）
//                        System.out.println(lineString);
                        //------------------------------------------------------------
                        //断言，失败不会中断自动化测试
//                        SoftAssert asert = new SoftAssert();


                        if(part[0].equals("open")){
                            String https; //地址，登录名，密码
                            https = part[1];
                            driver = new ChromeDriver();
                            String url = https;
                            driver.get(url);
                            driver.manage().window().maximize();
                            //等待程序响应
                            try {
                                waitForPageLoad(driver);
                            } catch (InterruptedException interruptedException) {
                                interruptedException.printStackTrace();
                            }
                        }
                        if(part[0].equals("input")){
                            String xpath,input; //地址，登录名，密码
                            xpath = part[1];
                            input = part[2];
                            driver.findElement(By.xpath(xpath)).sendKeys(input);
                            //等待程序响应
                            try {
                                waitForPageLoad(driver);
                            } catch (InterruptedException interruptedException) {
                                interruptedException.printStackTrace();
                            }
                        }
                        if(part[0].equals("click")){
                                String xpath, input; //地址，登录名，密码
                                xpath = part[1];
                                driver.findElement(By.xpath(xpath)).click();
                            //等待程序响应
                            try {
                                waitForPageLoad(driver);
                            } catch (InterruptedException interruptedException) {
                                interruptedException.printStackTrace();
                            }
                        }
                        if(part[0].equals("yzm")){
                            String word = "请输入验证码：";
                            String key = pause(word);
                            String xpath,input; //地址，登录名，密码
                            xpath = part[1];
                            input = key;
                            driver.findElement(By.xpath(xpath)).sendKeys(input);
                            //等待程序响应
                            try {
                                waitForPageLoad(driver);
                            } catch (InterruptedException interruptedException) {
                                interruptedException.printStackTrace();
                            }
                        }
                        if(part[0].equals("switch")){
                            Set<String> windows = driver.getWindowHandles();
                            //切换至最近的活跃windows窗口
                            driver.switchTo().window((String) windows.toArray()[windows.size()-1]);
                            //切换至最近的活跃frame窗口
//                            driver.switchTo().frame((String) windows.toArray()[windows.size()-1]);
                            //切换至最近的活跃alert弹框
//                            driver.switchTo().alert();
                            //等待程序响应
                            try {
                                waitForPageLoad(driver);
                            } catch (InterruptedException interruptedException) {
                                interruptedException.printStackTrace();
                            }
                        }
                        //翻页
                        if(part[0].equals("pagedown")) {
                            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 1000)");
                            //等待程序响应
                            try {
                                waitForPageLoad(driver);
                            } catch (InterruptedException interruptedException) {
                                interruptedException.printStackTrace();
                            }
                        }
                        //循环
                        if(part[0].equals("for")) {
                            //repeat[i]作为新的String字符串数组，组装成为新的脚本内容，且已经把前置的内容复制完成，目前 repeat[i]为for的脚本语句
                            //将循环次数part[1]转化为int类型，后续便于循环脚本
                            int time = Integer.parseInt(part[1]);
                            //循环获取脚本语句,从for脚本开始，获取end脚本位置
                            int script_line = 0;   //找到end脚本后置的脚本位置,script_line就是end后面第一位数组
                            int end_line = 0;       //找到end脚本前置的脚本位置,end_line就是end前面第一位数组
                            int repeat_num = i;
                            int mid = 0;  //中置状态值，不关注
                            int surplus = 0;  //循环值，表示for与end中间的脚本数，用于计算循环后数组长度
                            //
                            for(int z = 0; z <= i; z++){
                                repeat[z] = lineString[z];
                            }
                            //获取直至end前，前一行脚本值，得到script_line（end后一行脚本位置），end_line（end前一行脚本位置）
                            for(end_line = 0;end_line <= lineString.length; end_line ++){
                                if(lineString[end_line].equals("end")){
                                    script_line = end_line + 1;
                                    end_line = end_line - 1;
                                    //最后一句end前的脚本
                                    //System.out.println(lineString[end_line]+"------最后一句end前的脚本");
                                    break;
                                }
                            }
                            //添加循环区间脚本至repeat[i]字符串内，也就是repeat[i] = 循环前的值+循环值+循环值+...+循环值 ，次数=time（循环次）
                            for (int z = 1; z <= time; z++) {
                                //获取所有循环脚本的个数，可以计算循环后总计的脚本数
                                surplus = script_line - end_line;
                                //循环编辑后续脚本数，直到end前，将lineString[i]的值给repeat[i]
                                for(mid = i + 1 ;mid <= end_line; mid ++) {
                                    //将重复的循环数据装载进入repeat[repeat_num]
                                    repeat[repeat_num + 1] = lineString[mid];
                                    repeat_num = repeat_num + 1;
                                }
                            }
                            //把end后的脚本添加至repeat[i]
                            //由于String字符串数组长度问题，这里需要把数组长度减去循环的长度，这样不会有空赋值
                            for(int n = script_line ; n < lineString.length - (time*surplus) ; n ++){
                                //添加end脚本后面的脚本添加至repeat[i]字符串内
                                    repeat[repeat_num + 1] = lineString[n];
                                    repeat_num = repeat_num + 1;
                            }
                            //将脚本值repeat[i]全量复制给lineString[i]，保持前置已运行脚本顺序及数量不变情况下，无感插入循环脚本及循环尾部
                            for(int z = 0 ; z <= lineString.length - (time*surplus); z++){
                                lineString[z] = repeat[z];
                            }

                        }
                        //循环
                        if(part[0].equals("out")) {
                            System.out.println(part[1]);
                        }

                        //各脚本间的延时设置
                        new WebDriverWait(driver,2000);
//                        try {
//
//                            Thread.sleep(500);
//                        } catch (InterruptedException interruptedException) {
//                            interruptedException.printStackTrace();
//                        }
                        //输出日志
//                        Object log = lineString[i];
                        //System.out.println(line);
                        jta2.append(lineString[i]+"------执行成功"+ "\n");
                    }
                    //对转译字段做保存，添加判断标志位。

                    //添加selenuim封装
                }
            });
            //添加测试脚本页jframe资源
            //把滚动条添加到容器里面
            f2.add(jsp);
            f2.add(jsp2);
            //f2.revalidate();
            //上移
            f2.add(explainButton);
            //上移
            f2.add(runButton);
            //下移
            f2.add(calButton);
            //导入
            f2.add(importButton);
            //导出
            f2.add(exportButton);
            f2.setVisible(true);
            //退出窗口不关闭程序
            f2.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            //逐行获取输入框内容

        }

    });
    //展示按钮及首页
    f.add(deployButton);
    f.add(returnButton);
    f.add(testButton);
    f.add(Box.createGlue());
    f.setVisible(true);

}

//-------------------------------------------------------测试界面---------------------------------------------------------
    //字体预加载
    public static void loadIndyFont(){
        //字体
        UIManager.put("Button.font",new java.awt.Font("微软雅黑",Font.TYPE1_FONT,16));

    }



    //弹框暂停方法
    public static String pause(String message){
        //自定义输入框体大小方法，调用
        String yzm = (String) showInputDialog(message,JOptionPane.WARNING_MESSAGE,JOptionPane.OK_CANCEL_OPTION,"AutoTest",400,300);
        //String yzm=JOptionPane.showInputDialog(message);
        return yzm;
    }
    //自定义输入框体大小方法
    public static Object showInputDialog(Object message,int messageType,int optionType,String title,int width,int height){
        JOptionPane pane = new JOptionPane(message, messageType, optionType);
        pane.setWantsInput(true);
        JDialog dialog = pane.createDialog(title);
        dialog.setSize(width, height);
        dialog.show();
        dialog.dispose();
        Object value = pane.getInputValue();
        if(value == JOptionPane.UNINITIALIZED_VALUE)return null;
        return value;
    }
    //等待页面反应
    public static void waitForPageLoad(WebDriver driver) throws InterruptedException{
        Function<WebDriver,Boolean> waitFn = new Function<WebDriver,Boolean>(){
            @Override
            public Boolean apply(WebDriver driver){
                return ((JavascriptExecutor)driver).executeScript("return document.readyState")
                        .equals("complete");
            }
        };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(waitFn);
        Thread.sleep(500);
    }
    //切换至当前页
    public static void switch_to() throws InterruptedException {
        WebDriver driver = null;
        Set<String> windows = driver.getWindowHandles();
        driver.switchTo().window((String) windows.toArray()[windows.size()-1]);
    }
}



