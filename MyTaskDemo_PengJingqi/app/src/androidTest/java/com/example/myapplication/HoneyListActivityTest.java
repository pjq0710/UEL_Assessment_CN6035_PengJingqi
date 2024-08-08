package com.example.myapplication;

import android.content.Intent;
import android.widget.ListView;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.matcher.ViewMatchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class HoneyListActivityTest {

    @Rule
    public ActivityScenarioRule<HoneyListActivity> activityRule =
            new ActivityScenarioRule<>(HoneyListActivity.class);

    @Before
    public void setUp() {
        // Start the activity
        Intent intent = new Intent(ApplicationProvider.getApplicationContext(), HoneyListActivity.class);
        activityRule.getScenario().onActivity(activity -> {
            activity.startActivity(intent);
        });
    }

    //2.点击开始按钮
    @Test
    public void testStartButtonClick() {
        onView(withId(R.id.start_button)).perform(click());
        onView(withId(R.id.listView)).check(matches(isDisplayed()));
    }

    @Test
    public void testHoneyListCount() {
        // 3.检查 ListView 项目数量是否为 10
        onView(withId(R.id.listView)).check((view, noViewFoundException) -> {
            ListView listView = (ListView) view;
            assert listView.getAdapter().getCount() == 10;
        });
    }

    @Test
    public void testAddToCart() {
        // 4.模拟点击第一项并加入购物车
        onView(withId(R.id.addToCartButton))
                .perform(click(1,90));
    }

    @Test
    public void testClearCart() {
        // 5.测试清空购物车
        onView(withId(R.id.clear_cart_button)).perform(click());
    }

    @Test
    public void testCalculateTotal() {
        // 6.测试计算总价，加入购物车后调用
        onView(withId(R.id.calculate_button)).perform(click());
    }

    @Test
    public void testConfirmOrder() {
        // 7.测试确认订单流程
        onView(withId(R.id.confirm_order_button)).perform(click());
        // 验证确认订单的 Dialog
        onView(withText("确认下单")).check(matches(isDisplayed()));
    }

    @Test
    public void testCancelOrder() {
        // 8.测试取消订单
        onView(withId(R.id.confirm_order_button)).perform(click());
        onView(withText("取消")).perform(click());
        // 验证取消后，确认对话框消失
        onView(withText("确认下单")).check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }

    @Test
    public void testBackToHome() {
        // 9.测试返回主页
        onView(withId(R.id.home_button)).perform(click());
        // 确保 Activity 正常关闭
        activityRule.getScenario().onActivity(activity -> {
            assert !activity.isFinishing();
        });
    }

    @Before
    public void setUpN() {
        // Start the activity
        activityRule.getScenario().onActivity(activity -> {
            // Simulate adding an item to the cart (item at position 0)
            activity.addToCart(0, 50); // Simulate adding an item with price 50
        });
    }

    @Test
    public void testCartCountUpdateAndClear() {
        // 10.Verify initial cart count is 1
        onView(withId(R.id.addToCartButton)).check(matches(withText("1")));

        // Click on clear cart button
        onView(withId(R.id.clear_cart_button)).perform(click());

        // Verify cart count is updated to 0
        onView(withId(R.id.addToCartButton)).check(matches(withText("0")));

    }
}
