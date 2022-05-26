import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Set;

public class yzm_main {
    String word;
    String key;
    WebDriver driver = null;
    Set<String> windows = null;
    public  void quit_out() throws InterruptedException {    //退出当前页面
        driver.quit();
    }
    public  void switch_to() throws InterruptedException {    //切换至当前页
        windows = driver.getWindowHandles();
        driver.switchTo().window((String) windows.toArray()[windows.size()-1]);
    }
//    //获取键盘按键
//    public static String getKeybord() {
//        Scanner sc = new Scanner(System.in);
//        String message = sc.next();
//        return message;
//    }
    //中断自动化，弹出弹窗输入文字
    public String pause(String message){
        String yzm=JOptionPane.showInputDialog(message);
        return yzm;
    }


    public  void yzm() throws InterruptedException, AWTException {        //登录
        //设置浏览器的默认安装路径
        //新建一个Robot类的对象
        Robot robot=new Robot();
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chromedriver.exe");
        driver = new ChromeDriver();
        //driver 相当于chrome
        //打开chrome,对应地址https://csi.tmuyun.com
        String url = "http://web.trshz.com:8060/ids/admin/login.jsp";
        driver.get(url);

        driver.manage().window().maximize();
        waitForPageLoad(driver);
        switch_to();
        //输入对应登录账户，密码
        driver.findElement(By.xpath("//*[@id=\"loginKey\"]")).sendKeys("trsceshi");
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("trsadmin1!");
        waitForPageLoad(driver);
        word = "请输入验证码：";
        key = pause(word);
        //key  =JOptionPane.showInputDialog(word);
        System.out.println(key
        );
        driver.findElement(By.xpath("//*[@id=\"verifycode\"]")).sendKeys(key);
        waitForPageLoad(driver);
        driver.findElement(By.xpath("//*[@id=\"submitLoginBtn\"]")).click();




        //WebDriverWait.until用法
//        //查询页面title
//        ExpectedCondition<Boolean> title = ExpectedConditions.titleIs("t");
//        WebDriverWait WebDriverWait = new WebDriverWait(driver,3);
//        //查询页面元素是否可点击
//        WebDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"form\"]/span[1]")));
//        //new WebDriverWait(driver, 200).until(ExpectedConditions.titleIs("t"));

        //点击照相机这个工具
        driver.findElement(By.xpath("//*/span[@class='soutu-btn']")).click();
        //点击本地上传图片
        driver.findElement(By.xpath("//*/div[@class='upload-wrap']")).click();

        Thread.sleep(1000);
        //指定图片路径
        StringSelection selection=new StringSelection("D:\\Users\\86135\\eclipse-workspace\\workspace\\1045.jpg");
        //把图片路径复制到剪切板
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
        System.out.println("selection"+selection);
        //按下Ctrl+V
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        //释放Ctrl+V
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        Thread.sleep(2000);
        //点击回车
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);


        //----------
        //driver.close();
        switch_to();
        //等待页面反应
        waitForPageLoad(driver);
        //输入对应登录账户，密码
        //driver.findElement(By.id("loginKey")).sendKeys("yy@qq.com");
        //driver.findElement(By.id("password")).sendKeys("88387107Li");
        waitForPageLoad(driver);
    }

    public static void waitForPageLoad(WebDriver driver) throws InterruptedException{     //等待窗口反应
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



}
