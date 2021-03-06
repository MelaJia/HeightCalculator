package cn.edu.gdmec.android.heightcalculator;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HeightCalculatorActivity extends AppCompatActivity {
    /**
     * 项目名称：个人标准计算器
     * 项目目标：
     * （1）、开发输入界面
     * （2）、进行事件处理
     * （3）处理计算结果
     * （4）发布到手机
     * */
    private TextView tv_result;
    private EditText et_weight;
    private CheckBox manBox;
    private CheckBox womanBox;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height_calculator);

        btn=findViewById(R.id.calculator);
        tv_result = (TextView) findViewById(R.id.result);
        et_weight=findViewById(R.id.weight);
        manBox=(CheckBox) findViewById(R.id.man);
        womanBox=(CheckBox) findViewById(R.id.woman);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //注册事件
        registerEvent();
    }

    //计算公式
    private double evaluateHeight(Double weight, String sex){
        double height;
        if (sex=="男"){
            height=170-(62-weight)/0.6;
        }else {
            height=158-(52-weight)/0.5;
        }
        return height;
    }
    //没有输入体重或者没有选择性别，消息提示
    private void showMessage(String message){
        android.app.AlertDialog dialog=new android.app.AlertDialog.Builder(this).create();
        dialog.setTitle("系统信息");
        dialog.setMessage(message);
        dialog.setButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialog.show();
    }



    private void registerEvent(){
        //注册按钮事件
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断是否已经填写体重
                if (!et_weight.getText().toString().trim().equals("")){
                    //判断是否已经选择性别
                    if (manBox.isChecked()||womanBox.isChecked()){
                        Double weight=Double.parseDouble(et_weight.getText().toString());
                        StringBuffer sb=new StringBuffer();
                        sb.append("--------评审结果--------\n\n");
                        if (manBox.isChecked()){
                            sb.append("男性标准身高：");
                            //执行运算结果
                            double result=evaluateHeight(weight,"男");
                            sb.append((int) result+"（厘米）");

                        }else if (womanBox.isChecked()){
                            sb.append("女性标准身高：");
                            //执行运算结果
                            double result=evaluateHeight(weight,"女");
                            sb.append((int) result+"（厘米）");

                        }
                        tv_result.setText(sb.toString());

                    }else{
                        showMessage("请选择性别");
                    }

                }else{
                    showMessage("请输入体重！");
                }
            }
        });
    }
    /**
     * 再按一次退出程序
     * */
    //记录第一次点击时间
    protected  long exitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK && event.getAction()==KeyEvent.ACTION_DOWN){
            if ((System.currentTimeMillis()-exitTime)>2000){
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime=System.currentTimeMillis();
            }else {
                HeightCalculatorActivity.this.finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 创建菜单
     * **/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE,1,Menu.NONE,"退出");
        return super.onCreateOptionsMenu(menu);
    }
    /**
     * 菜单事件
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
