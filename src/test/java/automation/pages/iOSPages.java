package automation.pages;

import automation.helpers.World;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class iOSPages {

    private World world;

    public iOSPages(World world) {
        this.world = world;
        PageFactory.initElements(new AppiumFieldDecorator(this.world.driver), this);
    }

    @iOSXCUITFindBy(accessibility = "Picker View")
    public MobileElement pickerView;

    @iOSXCUITFindBy(accessibility = "Red color component value")
    public MobileElement redColor;

    @iOSXCUITFindBy(accessibility = "Green color component value")
    public MobileElement greenColor;

    @iOSXCUITFindBy(accessibility = "Blue color component value")
    public MobileElement blueColor;



    @iOSXCUITFindBy(accessibility = "Stack Views")
    public MobileElement stackViews;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Detail\"]/following-sibling::XCUIElementTypeButton[@name=\"stepper increment\"]")
    public MobileElement plusDetail;

    @iOSXCUITFindBy(accessibility = "Further Detail")
    public MobileElement furtherDetailText;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Add/remove views\"]/following-sibling::XCUIElementTypeButton[@name=\"stepper increment\"]")
    public MobileElement plusView;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeNavigationBar[@name=\"Stack Views\"]/following-sibling::XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther")
    public List<MobileElement> listAreas;



    @iOSXCUITFindBy(accessibility = "Date Picker")
    public MobileElement datePicker;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypePickerWheel[1]")
    public MobileElement day;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypePickerWheel[2]")
    public MobileElement hour;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypePickerWheel[3]")
    public MobileElement minutes;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypePickerWheel[4]")
    public MobileElement dayPart;

    @iOSXCUITFindBy(xpath = "//XCUIElementTypeDatePicker/following-sibling::XCUIElementTypeStaticText")
    public MobileElement date;



}
