package automation.pages;

import automation.helpers.World;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AndroidPages {

    private World world;

    public AndroidPages(World world) {
        this.world = world;
        PageFactory.initElements(new AppiumFieldDecorator(this.world.driver), this);
    }

    @AndroidFindBy(accessibility = "Preference")
    public MobileElement preference;

    @AndroidFindBy(accessibility = "3. Preference dependencies")
    public MobileElement PreferenceDependencies;

    @AndroidFindBy(id = "android:id/checkbox")
    public MobileElement checkBoxWifi;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.RelativeLayout")
    public MobileElement wifiSettings;

    @AndroidFindBy(id = "android:id/edit")
    public MobileElement textFieldWifiSettings;

    @AndroidFindBy(id = "android:id/button1")
    public MobileElement buttonOk;





    @AndroidFindBy(accessibility = "Views")
    public MobileElement views;
    @AndroidFindBy(accessibility = "Expandable Lists")
    public MobileElement expandableLists;
    @AndroidFindBy(accessibility = "1. Custom Adapter")
    public MobileElement customAdapter;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'People Names')]")
    public MobileElement peopleNames;
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Sample action')]")
    public MobileElement sampleAction;
    @AndroidFindBy(xpath = "/hierarchy/android.widget.Toast[contains(@text,'People Names: Group 0 clicked')]")
    public MobileElement toast;




    @AndroidFindBy(accessibility = "Drag and Drop")
    public MobileElement dragAndDrop;
    @AndroidFindBy(id = "io.appium.android.apis:id/drag_dot_1")
    public MobileElement firstDot;
    @AndroidFindBy(id = "io.appium.android.apis:id/drag_dot_2")
    public MobileElement secondDot;
    @AndroidFindBy(id = "io.appium.android.apis:id/drag_result_text")
    public MobileElement dragresult;



    @AndroidFindBy(xpath = "//android.widget.TextView")
    public List<MobileElement> textViewElements;
    @AndroidFindBy(accessibility = "Search View")
    public MobileElement searchView;
    @AndroidFindBy(accessibility = "Filter")
    public MobileElement filter;
    @AndroidFindBy(id = "android:id/search_src_text")
    public MobileElement textFieldSearch;
    @AndroidFindBy(id = "android:id/text1")
    public MobileElement searchResult;


    @AndroidFindBy(accessibility = "WebView")
    public MobileElement webView;
    @AndroidFindBy(accessibility = "i am a link")
    public MobileElement webViewLink;
    @AndroidFindBy(accessibility = "I am some other page content")
    public MobileElement webViewText;



}
