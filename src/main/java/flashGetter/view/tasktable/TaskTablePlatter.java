package flashGetter.view.tasktable;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import flashGetter.view.ViewEvent;
import flashGetter.view.ViewEventDispatcher;
import flashGetter.view.ViewEventHandler;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class TaskTablePlatter extends JScrollPane implements ViewEventHandler<TaskTable>{
    
    private Map<Class<? extends TaskTable>, TaskTable> tables;
    
    public TaskTablePlatter() {
        
        ViewEventDispatcher.InnerClass.instance.register(this);
        
        tables = new HashMap<Class<? extends TaskTable>, TaskTable>();
        
        tables.put(DownloadingTable.class, new DownloadingTable());
        tables.put(DownloadedTable.class, new DownloadedTable());
        tables.put(DeletedTable.class, new DeletedTable());
        
        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
        
        setViewportView((tables.get(DownloadingTable.class)));
        
    }

    @Override
    public void invoke(ViewEvent event) {
        JTable table = tables.get(event.getTarget());
        setViewportView(table);
        table.repaint();
    }

    @Override
    public Class<TaskTable> getGroupClass() {
        return TaskTable.class;
    }

}
