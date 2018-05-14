package izenka.hfad.com.bookstore.view.main_menu;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.util.HashSet;
import java.util.Set;

import izenka.hfad.com.bookstore.R;
import izenka.hfad.com.bookstore.presenter.MainMenuPresenter;
import stanford.androidlib.SimpleActivity;

public class MainMenuActivity extends SimpleActivity implements IMainMenuView {

    public static Set<String> stringSet = new HashSet<>();
    public static Set<String> ordersSet = new HashSet<>();

    private MainMenuPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        if (presenter == null) {
            presenter = new MainMenuPresenter(this);
        }
        presenter.onViewCreated();
    }

    @Override
    public void initViews() {
        $TV(R.id.tvRunningLine).setSelected(true);
        $IB(R.id.imgBtnOrders).setOnClickListener(view -> presenter.onOrdersClicked(view));
        $IB(R.id.imgBtnBasket).setOnClickListener(view -> presenter.onBasketClicked(view));
        $ET(R.id.etSearch).setOnClickListener(view -> presenter.onSearchClicked());
        $B(R.id.btnForeign).setOnClickListener(view -> presenter.onCategoryClicked(view));
        $B(R.id.btnKid).setOnClickListener(view -> presenter.onCategoryClicked(view));
        $B(R.id.btnBusiness).setOnClickListener(view -> presenter.onCategoryClicked(view));
        $B(R.id.btnFiction).setOnClickListener(view -> presenter.onCategoryClicked(view));
        $B(R.id.btnStudy).setOnClickListener(view -> presenter.onCategoryClicked(view));
        $B(R.id.btnNonfiction).setOnClickListener(view -> presenter.onCategoryClicked(view));
        $B(R.id.btnScan).setOnClickListener(view -> presenter.onScanClicked(view));
    }

    @Override
    public void startActivityWithAnimation(View view, Class activity) {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        view.startAnimation(anim);
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }

    @Override
    public void startActivity(Intent intent, Class activity) {
        intent.setClass(this, activity);
        startActivity(intent);
    }

    @Override
    public void startActivityWithAnimation(View view, Intent intent, Class activity) {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        view.startAnimation(anim);
        intent.setClass(this, activity);
        startActivity(intent);
    }

    @Override
    public void showCategoriesNames() {
        Button[] btnArray = {
                $B(R.id.btnForeign),
                $B(R.id.btnKid),
                $B(R.id.btnBusiness),
                $B(R.id.btnFiction),
                $B(R.id.btnStudy),
                $B(R.id.btnNonfiction)
        };

        String[] stringArray = getResources().getStringArray(R.array.categoriesNames);

        for (int i = 0; i < btnArray.length; i++) {
            btnArray[i].setText(stringArray[i]);
            btnArray[i].setTextSize(36);
            btnArray[i].setTypeface(Typeface.createFromAsset(getAssets(), "fonts/5.ttf"));
        }
    }
}