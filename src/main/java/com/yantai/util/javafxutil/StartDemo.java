package com.yantai.util.javafxutil;


import com.yantai.test.Test;
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

public class StartDemo extends Application {

    @Override
    public void start(final Stage stage) throws Exception {
        //设置启动按钮
        Button start = new Button("开始");
        //网格布局
        GridPane gridPane = new GridPane();
        //设置网格位置
        gridPane.add(start, 0, 0);
        //设置使用的网格居中显示
        gridPane.setAlignment(Pos.CENTER);
        //将网格放到scene中
        Scene scene = new Scene(gridPane);
        //设置窗口
        stage.setScene(scene);
        stage.setTitle("张文杰专用");
        stage.setHeight(200);
        stage.setWidth(300);
        stage.show();

        //对登录按钮绑定监听事件
        start.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                // 关闭开始窗口
                stage.close();
                // 执行功能
                Test.sayHi();
                Button end = new Button("执行结束！");
                GridPane gridPane = new GridPane();
                gridPane.add(end, 0, 0);
                gridPane.setAlignment(Pos.CENTER);
                final Stage stage2 = new Stage();
                Scene scene = new Scene(gridPane);
                //设置窗口
                stage2.setScene(scene);
                stage2.setTitle("张文杰专用");
                stage2.setHeight(200);
                stage2.setWidth(300);
                stage2.show();

                end.setOnAction(new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent event) {
                        // 执行功能
                        Test.sayHi();
                        // 关闭窗口
                        stage2.close();
                    }
                });
            }
        });
    }

    /**
     * 启动方法
     */
    public static void begin() {
        // 父类的静态方法
        launch();
    }

    public static void main(String[] args) {
        begin();
    }
}