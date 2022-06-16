package com.example.gs.mvi;

/**
 * 作者    你的名字
 * 时间    2022/6/16 9:45
 * 文件    GsMVP+MVVM+MVI
 * 描述
 */
public class MviViewEvent {
    public static class ShowToast extends MviViewEvent{
        private String message;

        public ShowToast(String message){
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class ShowDialog extends MviViewEvent{
        private String message;

        public ShowDialog(String message){
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
