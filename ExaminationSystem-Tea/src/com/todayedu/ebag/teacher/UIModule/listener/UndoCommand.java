package com.todayedu.ebag.teacher.UIModule.listener;

public interface UndoCommand {
    public void undo();
    public void redo();
    public boolean canUndo();
    public boolean canRedo();
}
