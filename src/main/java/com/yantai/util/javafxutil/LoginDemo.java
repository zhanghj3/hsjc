package com.yantai.util.javafxutil;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class LoginDemo extends Application {

    private Map map = null;
    private TextField e_name = null;
    private PasswordField e_password = null;
    private Stage e_stage = null;

    /**
     * 这个是准备的数据  用来模拟登录使用
     *
     * @throws Exception
     */
    @Override
    public void init() {
        map = new HashMap<String, String>();
        map.put("username", "password");
    }


    @Override
    public void start(Stage stage) throws Exception {
        this.e_stage = stage;
        //设置页面上的文本，输入框，按钮
        Text name = new Text("用户名：");
        Text password = new Text("密码：");
        this.e_name = new TextField();
        e_name.setPrefWidth(150);
        this.e_password = new PasswordField();
        Button clear = new Button("清除");
        Button login = new Button("登录");

        //网格布局
        GridPane gridPane = new GridPane();
        //设置网格位置
        gridPane.add(name, 0, 0);
        gridPane.add(e_name, 1, 0);
        gridPane.add(password, 0, 1);
        gridPane.add(e_password, 1, 1);
        gridPane.add(clear, 0, 2);
        gridPane.add(login, 1, 2);

        //设置单独组件的上下左右的间距
        GridPane.setMargin(login, new Insets(0, 0, 0, 160));
        //设置使用的网格居中显示
        gridPane.setAlignment(Pos.CENTER);
        //设置网格每行垂直间距
        gridPane.setVgap(10);
        //设置网格每列水平间距
        gridPane.setHgap(5);
        //将网格放到scene中
        Scene scene = new Scene(gridPane);
        //设置窗口
        stage.setScene(scene);
        stage.setTitle("布局类学习");
        stage.setHeight(500);
        stage.setWidth(800);
        stage.show();

        //绑定监听事件 clear清除
        //内部类使用外部类的全局变量 1.直接使用 2.OutClass.this.变量
        clear.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //单击清除用户名和密码
                e_name.setText("");
                e_password.setText("");
            }
        });
        //对登录按钮绑定监听事件
        login.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                String username = null;
                String password = null;
                //登录成功后
                Set set = map.entrySet();
                for (Object object : set) {
                    String[] strings = object.toString().split("=");
                    username = strings[0];
                    password = strings[1];
                }
                if (e_name.getText().equals(username) && e_password.getText().equals(password)) {
//                    e_stage.close();
//                    try {
//                        start(new Stage());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                    Button login1 = new Button("登陆成功！");
                    GridPane gridPane1 = new GridPane();
                    gridPane1.add(login1,0,0);
                    gridPane1.setAlignment(Pos.CENTER);
                    Stage stage2 = new Stage();
                    Scene scene = new Scene(gridPane1);
                    //设置窗口
                    stage2.setScene(scene);
                    stage2.setWidth(200);
                    stage2.setHeight(150);
                    stage2.show();
                } else {
                    System.out.println("密码输入错误");
                }
            }
        });
    }

    /**
     * 启动方法
     */
    public static void begin(){
        // 父类的静态方法
        launch();
    }
}