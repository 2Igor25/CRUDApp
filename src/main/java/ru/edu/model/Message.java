package ru.edu.model;

public enum Message {

    ERROR_INPUT("Неправильный ввод. Повторите попытку!"),
    EMPTY_LIST("Список пуст"),
    SUCCESSFUL_OPERATION("Успешное выполнение"),
    ERROR("Ошибка!"),
    ID("Введите ID:");


    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
