package com.yantai.util.javafxutil;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AlertDemo extends Application {

    private Stage e_stage = null;

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println(Thread.currentThread().getName());
        this.e_stage = stage;
        // getParameters().getRaw().get(0) 接收launch()传过来的参数
        Button btn = new Button(getParameters().getRaw().get(0));
        GridPane gridPane1 = new GridPane();
        gridPane1.add(btn,0,0);
        gridPane1.setAlignment(Pos.CENTER);
        Scene scene = new Scene(gridPane1);
        //设置窗口
        e_stage.setScene(scene);
        e_stage.setWidth(330);
        e_stage.setHeight(200);
        e_stage.setTitle("提示信息：");
        e_stage.show();

        //确认按钮触发事件
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                //关闭本窗口
                e_stage.close();
            }
        });
    }

    /**
     * 启动方法，传入报错信息
     */
    public void begin(String args) {
        // 父类的静态方法
        launch(args);
    }
}