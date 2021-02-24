import com.UpYun;
import com.google.gson.Gson;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.codec.digest.DigestUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;


public class HelloSelenium3Test {

    static int SCROLL_COUNT = 10;

    static boolean test = false;

    static boolean sync = true;

    static Map<String, String> newsLink = new LinkedHashMap<>();
    static Map<String, String> articleLink = new LinkedHashMap<>();
    static List<Blog> blogLink = new ArrayList<>();
    static Map<String, String> bookLink = new LinkedHashMap<>();

    // 运行前先设置好以下三个参数
    private static final String BUCKET_NAME = "myfiledata";
    private static final String OPERATOR_NAME = "op1";
    private static final String OPERATOR_PWD = "Wli0d5U27a2rY3Ji6EXdX7C4yUbz0hwx";


    static UpYun upyun = new UpYun(BUCKET_NAME, OPERATOR_NAME, OPERATOR_PWD);

    static String html = "<html><style>body {width:85%;margin:auto;padding:20px}</style><body>_</body></html>";

    static void blogs() {

        blogLink.add(new Blog("https://afoo.me/posts.html", "h2 > a", "div.container > p"));
        for (int i = 2; i <= 46; i++) {
            blogLink.add(new Blog("https://afoo.me/pages/p" + i + ".html", "h2 > a", "div.container > p"));
        }

        blogLink.add(new Blog("http://www.ruanyifeng.com/blog/essays/", "li.module-list-item > a", "div#main-content > p"));
        blogLink.add(new Blog("http://www.ruanyifeng.com/blog/opinions/", "li.module-list-item > a", "div#main-content > p"));
        blogLink.add(new Blog("http://www.ruanyifeng.com/blog/computer/", "li.module-list-item > a", "div#main-content > p"));
        blogLink.add(new Blog("http://www.ruanyifeng.com/blog/industry/", "li.module-list-item > a", "div#main-content > p"));
        blogLink.add(new Blog("http://www.ruanyifeng.com/blog/startup/", "li.module-list-item > a", "div#main-content > p"));
        blogLink.add(new Blog("http://www.ruanyifeng.com/blog/translations/", "li.module-list-item > a", "div#main-content > p"));
        blogLink.add(new Blog("http://www.ruanyifeng.com/blog/literature/", "li.module-list-item > a", "div#main-content > p"));
        blogLink.add(new Blog("http://www.ruanyifeng.com/blog/notes/", "li.module-list-item > a", "div#main-content > p"));
        blogLink.add(new Blog("http://www.ruanyifeng.com/blog/misc/", "li.module-list-item > a", "div#main-content > p"));
        blogLink.add(new Blog("http://www.ruanyifeng.com/blog/mjos/", "li.module-list-item > a", "div#main-content > p"));

        blogLink.add(new Blog("http://www.yinwang.org/", "li.title > a", "div.inner > p"));
        blogLink.add(new Blog("https://thzt.github.io/archives/", "h2.post-title > a  > span", "div.post-body > p"));
        blogLink.add(new Blog("https://yihui.org/cn/", "div.archive > p > a", "article > p"));
        blogLink.add(new Blog("https://manateelazycat.github.io/index.html", "a.post-title", "div.content > p"));


        blogLink.add(new Blog("https://coolshell.cn", "h2.entry-title > a", ""));
        blogLink.add(new Blog("https://spring.io/blog/category/releases", "h2.blog--title > a", ""));
        blogLink.add(new Blog("https://spring.io/blog?page=2", "h2.blog--title > a", ""));
        blogLink.add(new Blog("https://netflixtechblog.com", "h3", ""));
        blogLink.add(new Blog("https://amazonaws-china.com/cn/blogs/china/", "h2.blog-post-title", ""));
        blogLink.add(new Blog("https://amazonaws-china.com/cn/blogs/china/page/2/", "h2.blog-post-title", ""));
        blogLink.add(new Blog("http://zhangyi.xyz/", "h1.post-title > a", ""));
        blogLink.add(new Blog("http://zhangyi.xyz/page/2/", "h1.post-title > a", ""));
        blogLink.add(new Blog("http://blog.devtang.com", "a.abstract-title", ""));
        blogLink.add(new Blog("http://blog.devtang.com/page/2/", "a.abstract-title", ""));
        blogLink.add(new Blog("https://onevcat.com/", "h1 > a", ""));
        blogLink.add(new Blog("https://www.topjavablogs.com/", "a.itemLink", ""));
        blogLink.add(new Blog("https://pingcap.com/blog-cn/", "h1.title > a", ""));
        blogLink.add(new Blog("https://blog.codingnow.com/", "h3.entry-header", ""));
        blogLink.add(new Blog("https://blogs.360.cn/", "h1.title > a", ""));
        blogLink.add(new Blog("https://codechina.org/", "ul.wp-block-latest-posts > li > a", ""));
        blogLink.add(new Blog("https://aijishu.com/", "h3.text-body", ""));
        blogLink.add(new Blog("https://tech.meituan.com/", "h2.post-title > a", ""));
        blogLink.add(new Blog("https://baotiao.github.io/", "h3.post-title", ""));
        blogLink.add(new Blog("https://skyao.io/post/", "h3.article-title", ""));
        blogLink.add(new Blog("https://yongyuan.name/blog/", "span.article", ""));
        blogLink.add(new Blog("http://zhangtielei.com/", "h2 > a", ""));
        blogLink.add(new Blog("https://liujiacai.net/", "a.post-link", ""));
        blogLink.add(new Blog("http://duanple.com/", "h2 > a", ""));
        blogLink.add(new Blog("https://colobu.com/", "a.article-title", ""));
        blogLink.add(new Blog("http://blog.fnil.net/", "h1.entry-title > a", ""));
        blogLink.add(new Blog("https://www.503error.com/", "h1.entry-title > a", ""));
        blogLink.add(new Blog("https://mccxj.github.io/", "div.nav > ul > li > a", ""));
        blogLink.add(new Blog("https://blog.cleancoder.com/", "ul > li > a", ""));
        blogLink.add(new Blog("https://blog.wangqi.love/", "a.post-title-link", ""));
        blogLink.add(new Blog("http://arthurchiao.art/index.html", "a.post-link", ""));
        blogLink.add(new Blog("https://encrt.com/allpost/", "td.td-left > a", ""));
        blogLink.add(new Blog("http://www.softwareishard.com/blog/", "h2 > a", ""));
        blogLink.add(new Blog("https://www.amusinganalyst.com/", "h3 > a", ""));
        blogLink.add(new Blog("https://tech.glowing.com/cn/", "h2.post-card-title", ""));
        blogLink.add(new Blog("http://blueskykong.com/", "h1.post-title > a", ""));
        blogLink.add(new Blog("http://itindex.net/", "h2 > a", ""));
        blogLink.add(new Blog("https://www.cnblogs.com/xiexj/", "a.postTitle2", ""));
        blogLink.add(new Blog("https://tech.youzan.com/", "h2.post-title > a", ""));
        blogLink.add(new Blog("https://lotabout.me/", "ul > li > a", ""));
        blogLink.add(new Blog("https://twobithistory.org/", "div.post-header > h1 > a.title-link", ""));
        blogLink.add(new Blog("https://einverne.github.io/archive.html", "div.page-header > ul > li > a", ""));
        blogLink.add(new Blog("https://happypeter.github.io/", "ul.posts > li > a", ""));
        blogLink.add(new Blog("https://huang-jerryc.com/archives/", "a.post-title", ""));
        blogLink.add(new Blog("https://www.duyidong.com/", "h2.post-title > a", ""));
        blogLink.add(new Blog("http://deliberate-software.com/page/post/", "li.sidebar_li > a", ""));
        blogLink.add(new Blog("https://thorstenball.com/blog/", "td > a", ""));
        blogLink.add(new Blog("http://www.plainionist.net/", "h1.post-title > a", ""));
        blogLink.add(new Blog("https://cnblogs.com/zhengyun_ustc/", "a.postTitle2", ""));
        blogLink.add(new Blog("https://begriffs.com/", "li.row > a", ""));
        blogLink.add(new Blog("https://boyter.org/", "body > ul > li > a", ""));
        blogLink.add(new Blog("http://www.skywind.me/blog/", "h2 > a.title", ""));
        blogLink.add(new Blog("https://tonsky.me/", "div.post > p > a", ""));
        blogLink.add(new Blog("https://freemind.pluskid.org/archive/", "article > h1 > a", ""));
        blogLink.add(new Blog("http://www.zreading.cn/", "h2.block-title > a", ""));
        blogLink.add(new Blog("https://qcrao.com/archives/", "ul.listing > li > a", ""));
        blogLink.add(new Blog("https://maintao.com/", "div > a", ""));
        blogLink.add(new Blog("https://blog.wutj.info/", "h3.entry-title > a", ""));
        blogLink.add(new Blog("https://www.barretlee.com/blog/archives/", "div.cate-detail > ul > li > a", ""));
        blogLink.add(new Blog("https://blog.youxu.info/archive.html", "div.well > ul > li > a", ""));
        blogLink.add(new Blog("https://martin.kleppmann.com/archive.html", "div#content > ul > li > a", ""));
        blogLink.add(new Blog("https://hawstein.com/archive/", "h2.post-title", ""));

    }


    static void news() {

        newsLink.put("https://36kr.com", "a.article-item-title");
        newsLink.put("https://www.pedaily.cn/", "div.txt > h3 > a");
        newsLink.put("https://www.huxiu.com", "h5.article-item__content__title");
        newsLink.put("https://www.ifanr.com", "a.js-title-transform");
        newsLink.put("http://www.iheima.com", "a.title");
        newsLink.put("https://www.donews.com/", "div.content > span.title");
        newsLink.put("https://www.cnbeta.com/", "div.item > dl > dt > a");
        newsLink.put("https://jishuin.proginn.com/", "div.article-title > a");
        newsLink.put("http://www.cbdio.com/node_2570.htm", "p.cb-media-title > a");
        newsLink.put("http://www.woshipm.com/", "h2.post-title > a");
        newsLink.put("http://www.kejilie.com", "h3.am_list_title > a");
        newsLink.put("https://www.geeksforgeeks.org/", "div.content > div.head > a");
        newsLink.put("https://z.itpub.net/", "li.has-img > h4");

    }

    static void article() {

        articleLink.put("https://toutiao.io#true", "div.content > h3 > a");
        articleLink.put("https://www.williamlong.info#true", "a.post-title");
        articleLink.put("https://readhub.cn/topics#true", "div#itemList > div > div > h2 > a");
        articleLink.put("https://news.sogou.com/sci#true", "h4.new-title > a");
        articleLink.put("https://insights.thoughtworks.cn", "h4.entry-title > a");
        articleLink.put("https://insights.thoughtworks.cn/page/2/", "h4.entry-title > a");
        articleLink.put("https://www.tuicool.com/ah", "div.aricle_item_info > div > object > a");
        articleLink.put("https://www.tuicool.com/ah/0/1?lang=1", "div.aricle_item_info > div > object > a");
        articleLink.put("https://juejin.cn#true", "div.title-row > span > a");
        articleLink.put("https://www.oschina.net/news/industry#true", "div.news-item > div > h3 > a");
        articleLink.put("https://www.oschina.net/blog#true", "div.blog-item > div > a");
        articleLink.put("https://www.oschina.net/translate#true", "div.translate-item > div > a");
        articleLink.put("https://dzone.com", "h3.article-title > a");
        articleLink.put("https://juejin.cn/tag/%E6%8E%98%E9%87%91%E7%BF%BB%E8%AF%91%E8%AE%A1%E5%88%92#true", "div.title-row > span > a");
        articleLink.put("http://www.geekpark.net#true", "h3.multiline-text-overflow");
        articleLink.put("https://blog.google/products/android/", "a > section > h3");
        articleLink.put("https://www.jdon.com", "h3.vid-name > a");
        articleLink.put("http://ifeve.com/", "h3.title > a");
        articleLink.put("http://ifeve.com/page/2/", "h3.title > a");
        articleLink.put("https://segmentfault.com/news", "h4.news__item-title");
        articleLink.put("https://www.tmtpost.com/lists/latest_list_new", "li.part_post > div.info > a > h3");
        articleLink.put("https://www.infoworld.com", "div.post-cont > h3 > a");
        articleLink.put("https://www.cnblogs.com", "a.post-item-title");
        articleLink.put("https://developer.ibm.com/zh/articles/", "h3.developer--card__title > span");
        articleLink.put("https://www.qbitai.com/", "div.text_box > h4 > a");
        articleLink.put("https://www.cncf.io/blog/", "p.archive-title > a");
        articleLink.put("https://draveness.me/", "article > a");
        articleLink.put("https://blog.jetbrains.com/idea/category/releases/", "article > h3");
        articleLink.put("https://hackernoon.com", "div.title-wrapper > h2 > a ");

    }

    static void books() {

        bookLink.put("https://scanlibs.com/category/books/", "h1.entry-title > a");

        for (int i = 2; i <= 5; i++) {
            bookLink.put("https://scanlibs.com/category/books/page/" + i + "/", "h1.entry-title > a");
        }


        bookLink.put("https://pragprog.com/titles/", "span.category-title-title");

        for (int i = 2; i <= 18; i++) {
            bookLink.put("https://pragprog.com/titles/page/" + i + "/", "span.category-title-title");
        }


        bookLink.put("https://www.epubit.com/books", "div.list-title");
        bookLink.put("https://www.tuicool.com/books", "div.title > a");
        bookLink.put("https://www.ituring.com.cn/book", "div.book-info > h4 > a");
        bookLink.put("https://www.ituring.com.cn/book?tab=book&sort=hot&page=1", "div.book-info > h4 > a");
        bookLink.put("https://www.ituring.com.cn/book?tab=book&sort=hot&page=2", "div.book-info > h4 > a");
        bookLink.put("https://www.manning.com/meap-catalog", "div.title");
        bookLink.put("https://www.freetechbooks.com/topics", "p.media-heading > a");

        for (int i = 2021; i > 2015; i--) {
            bookLink.put("http://www.oreilly.com.cn/index.php?func=completelist&pubyear=" + i, "a.tip");
        }

    }


    static {

        news();
        article();
        blogs();
        books();

    }


    @Test
    public void newsTest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = ((JavascriptExecutor) driver);

        List<String> news = new ArrayList<>();

        getData(driver, js, news, newsLink);


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String day = format.format(new Date());


        saveData(news, day, "news");


        driver.quit();
    }

    @Test
    public void bookTest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = ((JavascriptExecutor) driver);

        List<String> book = new ArrayList<>();


        getData(driver, js, book, bookLink);


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String day = format.format(new Date());


        saveData(book, day, "books");


        driver.quit();
    }

    @Test
    public void articleTest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = ((JavascriptExecutor) driver);

        List<String> article = new ArrayList<>();


        getData(driver, js, article, articleLink);


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String day = format.format(new Date());


        saveData(article, day, "articles");

        driver.quit();
    }


    @Test
    public void blogTest() throws InterruptedException {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.managed_default_content_settings.images", 2);
        options.setExperimentalOption("prefs", prefs);

        WebDriver driver = new ChromeDriver(options);
        JavascriptExecutor js = ((JavascriptExecutor) driver);


        List<Intent> blog = new ArrayList<>();

        getData2(driver, js, blog, blogLink);

        wanqu(driver, blog);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String day = format.format(new Date());


        saveData2(blog, day, "blogs");


        driver.quit();
    }


    private void getData(WebDriver driver, JavascriptExecutor js, List<String> article, Map<String, String> articleLink) throws InterruptedException {
        for (String key : articleLink.keySet()) {


            try {
                driver.get(key);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(key);
                continue;
            }


            if (key.endsWith("true")) {

                for (int i = 0; i < SCROLL_COUNT; i++) {

                    Thread.sleep(2000);

                    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                }
            }


            String pageSource = driver.getPageSource();
            Document doc = Jsoup.parse(pageSource);


            Elements select = doc.select(articleLink.get(key));
            for (Element element : select) {
                String text = element.text();
                if (text.equals("")) {
                    continue;
                }
                text = text.trim().replaceAll("\n", "");

                if (element.tagName().equals("a")) {
                    String href = element.attr("href");
                }

                article.add(text);

            }

            if (test) {
                break;
            }

        }
    }


    private void getData2(WebDriver driver, JavascriptExecutor js, List<Intent> article, List<Blog> blogData) throws InterruptedException {
        for (Blog key : blogData) {


            try {
                driver.get(key.access);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(key);
                continue;
            }


            if (key.access.endsWith("true")) {

                for (int i = 0; i < SCROLL_COUNT; i++) {

                    Thread.sleep(2000);

                    js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
                }
            }


            String pageSource = driver.getPageSource();
            Document doc = Jsoup.parse(pageSource);

            List<Intent> title = new ArrayList<>();

            Elements select = doc.select(key.select);
            for (Element element : select) {
                String text = element.text();
                if (text.equals("")) {
                    continue;
                }
                text = text.trim().replaceAll("\n", "");

                Intent intent = new Intent();
                intent.setName(text);

                String md5Hex = DigestUtils
                        .md5Hex(text).toUpperCase();
                intent.setUrl(md5Hex);


                if (element.tagName().equals("a")) {
                    String href = element.attr("href");
                    intent.setLink(href);
                }

                title.add(intent);

            }

            article.addAll(title);

            if (!key.url.equals("")) {

                int findCount = driver.findElements(By.cssSelector(key.select)).size();

                for (int i = 0; i < findCount; i++) {

                    String url = title.get(i).getUrl();

                    try {
                        String res = upyun.readFile("/blog/" + url + ".html");
                        continue;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    driver.findElements(By.cssSelector(key.select)).get(i).click();

                    StringBuilder sb = new StringBuilder();
                    Elements pdata = Jsoup.parse(driver.getPageSource()).select(key.url);
                    for (Element pdatum : pdata) {
                        sb.append("<p>");
                        sb.append(pdatum.text());
                        sb.append("</p>");
                    }


                    saveHtml(url, html.replace("_", sb.toString()));

                    driver.navigate().back();

                }
            }


            if (test) {
                break;
            }

        }
    }

    private void saveHtml(String key, String html) {

        try {

            if (sync) {
                upyun.writeFile("/blog/" + key + ".html", html);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void saveData(List<String> list, String day, String key) {
        try {
            String pathname = day + "-" + key + ".json";
            File file = new File(pathname);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.append(new Gson().toJson(list));
            fileWriter.flush();

            if (sync) {
                upyun.writeFile("/data/" + pathname, file);
            }


        } catch (Exception e) {
            e.printStackTrace();
            saveData(list, day, key);
        }


    }

    private void saveData2(List<Intent> list, String day, String key) {
        try {
            String pathname = day + "-" + key + ".json";
            File file = new File(pathname);
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.append(new Gson().toJson(list));
            fileWriter.flush();

            if (sync) {
                upyun.writeFile("/data/" + pathname, file);
            }

        } catch (Exception e) {
            e.printStackTrace();
            saveData2(list, day, key);
        }


    }


    static void wanqu(WebDriver driver, List<Intent> list) {
        driver.get("https://wanqu.co/issues/");

        List<WebElement> finds = driver.findElements(By.cssSelector("div.row > div > a"));

        for (int i = 0; i < finds.size(); i++) {

            finds = driver.findElements(By.cssSelector("div.row > div > a"));
            finds.get(i).click();

            String pageSource = driver.getPageSource();
            Document doc = Jsoup.parse(pageSource);
            Elements select = doc.select("h2.wq-header");


            for (Element element : select) {
                String text = element.text();

                Intent intent = new Intent();
                intent.setName(text);

                list.add(intent);

            }

            driver.navigate().back();

            if (test) {
                break;
            }

        }
    }
}