/**
 * http://surenpi.com
 */
package org.suren.autotest.web.framework.invoker;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.suren.autotest.web.framework.settings.SettingUtil;

/**
 * 引擎相关的操作
 * @author suren
 * @date 2016年12月23日 下午10:46:18
 */
public class EngineInvoker
{
	/**
	 * 关闭当前窗口
	 * @param util
	 */
	public static void closeWin(SettingUtil util)
	{
		util.getEngine().getDriver().close();
	}
	
	/**
	 * 关闭url以指定字符串开头的window
	 * @param util
	 * @param startWith
	 */
	public static void closeWinByUrlStartWith(SettingUtil util, String[] params)
	{
		String startWith = params[0];
		WebDriver driver = util.getEngine().getDriver();
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> handleIt = handles.iterator();
		
		String currentHandle = driver.getWindowHandle();
		while(handleIt.hasNext())
		{
			String handle = handleIt.next();
			
			driver.switchTo().window(handle);
			
			if(driver.getCurrentUrl().startsWith(startWith))
			{
				driver.close();
				break;
			}
		}
		
		driver.switchTo().window(currentHandle);
	}
	
	/**
	 * 根据index来切换iframe
	 * @param util
	 * @param params
	 */
	public static void frameSwitchByIndex(SettingUtil util, String[] params)
	{
		String indexStr = params[0];
		int index = Integer.parseInt(indexStr);
		util.getEngine().getDriver().switchTo().frame(index);
	}
	
	/**
	 * 根据name或者id来切换iframe
	 * @param util
	 * @param params
	 */
	public static void frameSwitchByNameOrId(SettingUtil util, String[] params)
	{
		String nameOrId = params[0];
		util.getEngine().getDriver().switchTo().frame(nameOrId);
	}
}
