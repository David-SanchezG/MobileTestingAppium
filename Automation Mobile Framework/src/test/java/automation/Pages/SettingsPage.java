package automation.Pages;

import automation.helpers.World;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class SettingsPage {

    private World world;

    public SettingsPage(World world) {
        this.world = world;
        PageFactory.initElements(new AppiumFieldDecorator(this.world.driver), this);
    }

    @AndroidFindBy(id = "row_icon_settings_title")
    @iOSXCUITFindBy(accessibility = "Datenschutzerklärung")
    public MobileElement btnDatenschutzerklärung;

    //second locator in Android is for version 9.0
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Datenschutzerklärung auxmoney App' or @text='Datenschutzerklärung auxmoney App']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Datenschutzerklärung auxmoney App']")
    public MobileElement titleDatenschutzerklärung;
    
    @AndroidFindBy(id = "alertTitle")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name='Fehlerbericht ausschalten?']")
    public MobileElement titleFehlerberichtAusschalten;

    @AndroidFindBy(xpath = "//android.widget.LinearLayout[1]/android.view.ViewGroup/android.widget.Switch[@text='OFF']")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeSwitch[@name='sdkSettings_cell_switch_1' and @value=0]")
    public MobileElement toggleCrashlyticsOff;


}
