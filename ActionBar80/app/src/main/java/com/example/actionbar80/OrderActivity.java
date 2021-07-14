package com.example.actionbar80;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class OrderActivity extends AppCompatActivity {
    private ShareActionProvider shareActionProvider = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        // 가급적이면 확장패키지로 사용
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_share);
        shareActionProvider =
                (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        setShareActionIntent("주문하시겠습니까?");
        return super.onCreateOptionsMenu(menu);
    }
    private void setShareActionIntent(String text){
        // 주문하기는 Intent로 처리해보도록 하자.
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        // 주문에 대한 설명 메세지 넘기기
        shareActionProvider.setShareIntent(intent);
        intent.putExtra(Intent.EXTRA_TEXT, text);
    }
    // 버튼이 눌렸을 때의 이벤트 처리
    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch (menuItem.getItemId()){
            case R.id.create_order:
                Intent intent = new Intent(this, OrderActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}