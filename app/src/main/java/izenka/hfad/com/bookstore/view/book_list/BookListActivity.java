package izenka.hfad.com.bookstore.view.book_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.List;

import izenka.hfad.com.bookstore.R;
import izenka.hfad.com.bookstore.controller.db_operations.Read;
import izenka.hfad.com.bookstore.model.db_classes.Book;
import izenka.hfad.com.bookstore.presenter.BookListPresenter;
import izenka.hfad.com.bookstore.view.book_list.recycler_view.BookListAdapter;
import stanford.androidlib.SimpleActivity;

public class BookListActivity extends SimpleActivity implements IBookListView {

    private BookListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        if (presenter == null) {
            presenter = new BookListPresenter(this, getIntent().getIntExtra("categoryID", 0));
        }
        presenter.onViewCreated();
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
    public void initViews() {
        $IB(R.id.imgBtnOrders).setOnClickListener(view -> presenter.onOrdersClicked(view));
        $IB(R.id.imgBtnBasket).setOnClickListener(view -> presenter.onBasketClicked(view));
        $IV(R.id.btnGoBack).setOnClickListener(view -> onBackPressed());
        $ET(R.id.etCategorySearch).setOnClickListener(view -> presenter.onSearchClicked());
        //TODO
//        View bookView = LayoutInflater.from(this).inflate(R.layout.book, null, false);
//        bookView.setOnClickListener(view -> {
//            presenter.onBookClicked(view);
//        });
    }


    @Override
    public void setCategoryName(int categoryID) {
        Read.setName(this, "category/" + categoryID + "/category_name/", R.id.tvCategory);
    }

    @Override
    public void showBookList(List<Book> bookList) {
        RecyclerView rvBookList = findViewById(R.id.rvBookList);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        rvBookList.setLayoutManager(manager);
        BookListAdapter adapter = new BookListAdapter(/*bookList,*/ presenter);
        rvBookList.setAdapter(adapter);
    }

}
